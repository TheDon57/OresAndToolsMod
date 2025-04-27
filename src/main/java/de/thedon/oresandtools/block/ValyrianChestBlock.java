package de.thedon.oresandtools.block;

import com.mojang.serialization.MapCodec;
import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.entity.ModBlockEntities;
import de.thedon.oresandtools.entity.ValyrianChestBlockEntity;
import de.thedon.oresandtools.inventory.ValyrianChestMenu;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class ValyrianChestBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<ValyrianChestBlock> CODEC = simpleCodec(properties -> new ValyrianChestBlock(properties, ModBlockEntities.VALYRIAN_CHEST::get));
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final int EVENT_SET_OPEN_COUNT = 1;
    protected static final int AABB_OFFSET = 1;
    protected static final int AABB_HEIGHT = 14;
    protected static final VoxelShape NORTH_AABB = Block.box(1.0, 0.0, 0.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape SOUTH_AABB = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 16.0);
    protected static final VoxelShape WEST_AABB = Block.box(0.0, 0.0, 1.0, 15.0, 14.0, 15.0);
    protected static final VoxelShape EAST_AABB = Block.box(1.0, 0.0, 1.0, 16.0, 14.0, 15.0);
    protected static final VoxelShape AABB = Block.box(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);

    private static final DoubleBlockCombiner.Combiner<ValyrianChestBlockEntity, Optional<Container>> CHEST_COMBINER = new DoubleBlockCombiner.Combiner<>() {
        public @NotNull Optional<Container> acceptDouble(@NotNull ValyrianChestBlockEntity pFirst, @NotNull ValyrianChestBlockEntity pSecond) {
            return Optional.of(new CompoundContainer(pFirst, pSecond));
        }

        public @NotNull Optional<Container> acceptSingle(@NotNull ValyrianChestBlockEntity pEntity) {
            return Optional.of(pEntity);
        }

        public @NotNull Optional<Container> acceptNone() {
            return Optional.empty();
        }
    };

    private static final DoubleBlockCombiner.Combiner<ValyrianChestBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<>() {
        public @NotNull Optional<MenuProvider> acceptDouble(final @NotNull ValyrianChestBlockEntity pFirst, final @NotNull ValyrianChestBlockEntity pSecond) {
            final Container container = new CompoundContainer(pFirst, pSecond);
            return Optional.of(new MenuProvider() {
                @Nullable
                public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory, @NotNull Player pPlayer) {
                    if (pFirst.canOpen(pPlayer) && pSecond.canOpen(pPlayer)) {
                        pFirst.unpackLootTable(pInventory.player);
                        pSecond.unpackLootTable(pInventory.player);
                        return ValyrianChestMenu.menu9x12(pContainerId, pInventory, container);
                    } else {
                        return null;
                    }
                }

                public @NotNull Component getDisplayName() {
                    if (pFirst.hasCustomName()) {
                        return pFirst.getDisplayName();
                    } else {
                        return (pSecond.hasCustomName() ? pSecond.getDisplayName() : Component.translatable("container." + OresAndToolsMod.MOD_ID + ".large_valyrian_chest"));
                    }
                }
            });
        }

        public @NotNull Optional<MenuProvider> acceptSingle(@NotNull ValyrianChestBlockEntity pEntity) {
            return Optional.of(pEntity);
        }

        public @NotNull Optional<MenuProvider> acceptNone() {
            return Optional.empty();
        }
    };

    protected final Supplier<BlockEntityType<? extends ValyrianChestBlockEntity>> blockEntityType;

    public ValyrianChestBlock(BlockBehaviour.Properties pProperties, Supplier<BlockEntityType<? extends ValyrianChestBlockEntity>> pBlockEntityType) {
        super(pProperties);
        this.blockEntityType = pBlockEntityType;

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED, Boolean.FALSE));
    }

    public static DoubleBlockCombiner.BlockType getBlockType(BlockState pState) {
        ChestType chesttype = pState.getValue(TYPE);
        if (chesttype == ChestType.SINGLE) {
            return DoubleBlockCombiner.BlockType.SINGLE;
        } else {
            return chesttype == ChestType.RIGHT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
        }
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected @NotNull BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pState.getValue(WATERLOGGED)) {
            pLevel.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }

        if (pFacingState.is(this) && pFacing.getAxis().isHorizontal()) {
            ChestType chesttype = pFacingState.getValue(TYPE);
            if (pState.getValue(TYPE) == ChestType.SINGLE
                    && chesttype != ChestType.SINGLE
                    && pState.getValue(FACING) == pFacingState.getValue(FACING)
                    && getConnectedDirection(pFacingState) == pFacing.getOpposite()) {
                return pState.setValue(TYPE, chesttype.getOpposite());
            }
        } else if (getConnectedDirection(pState) == pFacing) {
            return pState.setValue(TYPE, ChestType.SINGLE);
        }

        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(TYPE) == ChestType.SINGLE) {
            return AABB;
        } else {
            return switch (getConnectedDirection(pState)) {
                case NORTH -> NORTH_AABB;
                case SOUTH -> SOUTH_AABB;
                case WEST -> WEST_AABB;
                case EAST -> EAST_AABB;
                default -> NORTH_AABB;
            };
        }
    }

    public static Direction getConnectedDirection(BlockState pState) {
        Direction direction = pState.getValue(FACING);
        return pState.getValue(TYPE) == ChestType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        ChestType chesttype = ChestType.SINGLE;
        Direction direction = pContext.getHorizontalDirection().getOpposite();
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        boolean flag = pContext.isSecondaryUseActive();
        Direction direction1 = pContext.getClickedFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.candidatePartnerFacing(pContext, direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
                chesttype = direction2.getCounterClockWise() == direction1.getOpposite() ? ChestType.RIGHT : ChestType.LEFT;
            }
        }

        if (chesttype == ChestType.SINGLE && !flag) {
            if (direction == this.candidatePartnerFacing(pContext, direction.getClockWise())) {
                chesttype = ChestType.LEFT;
            } else if (direction == this.candidatePartnerFacing(pContext, direction.getCounterClockWise())) {
                chesttype = ChestType.RIGHT;
            }
        }

        return this.defaultBlockState()
                .setValue(FACING, direction)
                .setValue(TYPE, chesttype)
                .setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    @Override
    protected @NotNull FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }

    @Nullable
    private Direction candidatePartnerFacing(BlockPlaceContext pContext, Direction pDirection) {
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos().relative(pDirection));
        return blockstate.is(this) && blockstate.getValue(TYPE) == ChestType.SINGLE ? blockstate.getValue(FACING) : null;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        Containers.dropContentsOnDestroy(pState, pNewState, pLevel, pPos);
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected @NotNull InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            MenuProvider menuprovider = this.getMenuProvider(pState, pLevel, pPos);
            if (menuprovider != null) {
                pPlayer.openMenu(menuprovider);
                pPlayer.awardStat(this.getOpenChestStat());
                PiglinAi.angerNearbyPiglins(pPlayer, true);
            }

            return InteractionResult.CONSUME;
        }
    }

    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }

    public BlockEntityType<? extends ValyrianChestBlockEntity> blockEntityType() {
        return this.blockEntityType.get();
    }

    @Nullable
    public static Container getContainer(ValyrianChestBlock pChest, BlockState pState, Level pLevel, BlockPos pPos, boolean pOverride) {
        return pChest.combine(pState, pLevel, pPos, pOverride).apply(CHEST_COMBINER).orElse(null);
    }

    public DoubleBlockCombiner.NeighborCombineResult<? extends ValyrianChestBlockEntity> combine(BlockState pState, Level pLevel, BlockPos pPos, boolean pOverride) {
        BiPredicate<LevelAccessor, BlockPos> bipredicate;

        if (pOverride) {
            bipredicate = (levelAccessor, blockPos) -> false;
        } else {
            bipredicate = ValyrianChestBlock::isChestBlockedAt;
        }

        return DoubleBlockCombiner.combineWithNeigbour(
                this.blockEntityType.get(), ValyrianChestBlock::getBlockType, ValyrianChestBlock::getConnectedDirection, FACING, pState, pLevel, pPos, bipredicate
        );
    }

    @Override
    @Nullable
    @ParametersAreNonnullByDefault
    protected MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return this.combine(pState, pLevel, pPos, false).apply(MENU_PROVIDER_COMBINER).orElse(null);
    }

    public static DoubleBlockCombiner.Combiner<ValyrianChestBlockEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity pLid) {
        return new DoubleBlockCombiner.Combiner<ValyrianChestBlockEntity, Float2FloatFunction>() {
            @ParametersAreNonnullByDefault
            public @NotNull Float2FloatFunction acceptDouble(ValyrianChestBlockEntity pFirst, ValyrianChestBlockEntity pSecond) {
                return ticks -> Math.max(pFirst.getOpenNess(ticks), pSecond.getOpenNess(ticks));
            }

            @ParametersAreNonnullByDefault
            public @NotNull Float2FloatFunction acceptSingle(ValyrianChestBlockEntity pEntity) {
                return pEntity::getOpenNess;
            }

            public @NotNull Float2FloatFunction acceptNone() {
                return pLid::getOpenNess;
            }
        };
    }

    @Override
    @ParametersAreNonnullByDefault
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ValyrianChestBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? createTickerHelper(pBlockEntityType, this.blockEntityType(), ValyrianChestBlockEntity::lidAnimateTick) : null;
    }

    public static boolean isChestBlockedAt(LevelAccessor pLevelAccessor, BlockPos pBlockPos) {
        return isBlockedChestByBlock(pLevelAccessor, pBlockPos) || isCatSittingOnChest(pLevelAccessor, pBlockPos);
    }

    private static boolean isBlockedChestByBlock(BlockGetter pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.above();
        return pLevel.getBlockState(blockpos).isRedstoneConductor(pLevel, blockpos);
    }

    private static boolean isCatSittingOnChest(LevelAccessor pLevel, BlockPos pPos) {
        List<Cat> list = pLevel.getEntitiesOfClass(Cat.class, new AABB((double)pPos.getX(), (double)(pPos.getY() + 1),
                (double)pPos.getZ(), (double)(pPos.getX() + 1), (double)(pPos.getY() + 2), (double)(pPos.getZ() + 1)));

        if (!list.isEmpty()) {
            for (Cat cat : list) {
                if (cat.isInSittingPose()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState pState) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected int getAnalogOutputSignal(BlockState pBlockState, Level pLevel, BlockPos pPos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, pBlockState, pLevel, pPos, false));
    }

    @Override
    public @NotNull BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(BlockState pState, Mirror pMirror) {
        BlockState rotated = pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
        return pMirror == Mirror.NONE ? rotated : rotated.setValue(TYPE, rotated.getValue(TYPE).getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected boolean isPathfindable(BlockState pState, PathComputationType pPathComputationType) {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof ValyrianChestBlockEntity valyrianChestBlockEntity) {
            valyrianChestBlockEntity.recheckOpen();
        }
    }
}
