/*
package de.thedon.oresandtools.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.entity.ModBlockEntities;
import de.thedon.oresandtools.block.entity.ValyrianChestBlockEntity;
import de.thedon.oresandtools.screen.custom.ValyrianChestMenu;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
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
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public class ValyrianChestBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<ValyrianChestBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("open_sound").forGetter(ValyrianChestBlock::getOpenChestSound), BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("close_sound").forGetter(ValyrianChestBlock::getCloseChestSound), propertiesCodec()).apply(instance, (openSound, closeSound, properties) -> new ValyrianChestBlock(ModBlockEntities.VALYRIAN_CHEST::get, openSound, closeSound, properties)));
    public static final EnumProperty<Direction> FACING;
    public static final EnumProperty<ChestType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    public static final int EVENT_SET_OPEN_COUNT = 1;
    private static final VoxelShape SHAPE;
    private static final Map<Direction, VoxelShape> HALF_SHAPES;
    private final SoundEvent openSound;
    private final SoundEvent closeSound;
    protected final Supplier<BlockEntityType<? extends ValyrianChestBlockEntity>> blockEntityType;
    private static final DoubleBlockCombiner.Combiner<ValyrianChestBlockEntity, Optional<Container>> CHEST_COMBINER;
    private static final DoubleBlockCombiner.Combiner<ValyrianChestBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER;

    @Override
    public @NotNull MapCodec<? extends ValyrianChestBlock> codec() {
        return CODEC;
    }

    public ValyrianChestBlock(Supplier<BlockEntityType<? extends ValyrianChestBlockEntity>> blockEntityType, SoundEvent openSound, SoundEvent closeSound, BlockBehaviour.Properties properties) {
        super(properties);
        this.openSound = openSound;
        this.closeSound = closeSound;
        this.blockEntityType = blockEntityType;
        this.registerDefaultState((((this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(TYPE, ChestType.SINGLE)).setValue(WATERLOGGED, false));
    }

    public static DoubleBlockCombiner.BlockType getBlockType(BlockState state) {
        ChestType chesttype = state.getValue(TYPE);
        if (chesttype == ChestType.SINGLE) {
            return DoubleBlockCombiner.BlockType.SINGLE;
        } else {
            return chesttype == ChestType.RIGHT ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    protected @NotNull BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (this.chestCanConnectTo(neighborState) && direction.getAxis().isHorizontal()) {
            ChestType chesttype = neighborState.getValue(TYPE);
            if (state.getValue(TYPE) == ChestType.SINGLE && chesttype != ChestType.SINGLE && state.getValue(FACING) == neighborState.getValue(FACING) && getConnectedDirection(neighborState) == direction.getOpposite()) {
                return state.setValue(TYPE, chesttype.getOpposite());
            }
        } else if (getConnectedDirection(state) == direction) {
            return state.setValue(TYPE, ChestType.SINGLE);
        }

        return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    public boolean chestCanConnectTo(BlockState state) {
        return state.is(this);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape;
        switch (state.getValue(TYPE)) {
            case SINGLE -> shape = SHAPE;
            case LEFT, RIGHT -> shape = HALF_SHAPES.get(getConnectedDirection(state));
            default -> throw new MatchException(null, null);
        }

        return shape;
    }

    public static Direction getConnectedDirection(BlockState state) {
        Direction direction = state.getValue(FACING);
        return state.getValue(TYPE) == ChestType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
    }

    public static BlockPos getConnectedBlockPos(BlockPos pos, BlockState state) {
        Direction direction = getConnectedDirection(state);
        return pos.relative(direction);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        ChestType chesttype = ChestType.SINGLE;
        Direction direction = context.getHorizontalDirection().getOpposite();
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = context.isSecondaryUseActive();
        Direction direction1 = context.getClickedFace();
        if (direction1.getAxis().isHorizontal() && flag) {
            Direction direction2 = this.candidatePartnerFacing(context.getLevel(), context.getClickedPos(), direction1.getOpposite());
            if (direction2 != null && direction2.getAxis() != direction1.getAxis()) {
                direction = direction2;
                chesttype = direction2.getCounterClockWise() == direction1.getOpposite() ? ChestType.RIGHT : ChestType.LEFT;
            }
        }

        if (chesttype == ChestType.SINGLE && !flag) {
            chesttype = this.getChestType(context.getLevel(), context.getClickedPos(), direction);
        }

        return ((this.defaultBlockState().setValue(FACING, direction)).setValue(TYPE, chesttype)).setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER);
    }

    protected ChestType getChestType(Level level, BlockPos pos, Direction direction) {
        if (direction == this.candidatePartnerFacing(level, pos, direction.getClockWise())) {
            return ChestType.LEFT;
        } else {
            return direction == this.candidatePartnerFacing(level, pos, direction.getCounterClockWise()) ? ChestType.RIGHT : ChestType.SINGLE;
        }
    }

    @Override
    protected @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    private Direction candidatePartnerFacing(Level level, BlockPos pos, Direction direction) {
        BlockState blockstate = level.getBlockState(pos.relative(direction));
        return this.chestCanConnectTo(blockstate) && blockstate.getValue(TYPE) == ChestType.SINGLE ? (Direction)blockstate.getValue(FACING) : null;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level instanceof ServerLevel serverlevel) {
            MenuProvider menuprovider = this.getMenuProvider(state, level, pos);
            if (menuprovider != null) {
                player.openMenu(menuprovider);
                player.awardStat(this.getOpenChestStat());
                PiglinAi.angerNearbyPiglins(serverlevel, player, true);
            }
        }

        return InteractionResult.SUCCESS;
    }

    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }

    public BlockEntityType<? extends ValyrianChestBlockEntity> blockEntityType() {
        return this.blockEntityType.get();
    }

    @Nullable
    public static Container getContainer(ValyrianChestBlock chest, BlockState state, Level level, BlockPos pos, boolean override) {
        return chest.combine(state, level, pos, override).apply(CHEST_COMBINER).orElse(null);
    }

    @ParametersAreNonnullByDefault
    public DoubleBlockCombiner.NeighborCombineResult<? extends ValyrianChestBlockEntity> combine(BlockState state, Level level, BlockPos pos, boolean override) {
        BiPredicate<LevelAccessor, BlockPos> bipredicate;

        if (override) {
            bipredicate = (levelAccessor, blockPos) -> false;
        } else {
            bipredicate = ValyrianChestBlock::isChestBlockedAt;
        }

        return DoubleBlockCombiner.combineWithNeigbour(this.blockEntityType.get(), ValyrianChestBlock::getBlockType, ValyrianChestBlock::getConnectedDirection, FACING, state, level, pos, bipredicate);
    }

    @Override
    @Nullable
    @ParametersAreNonnullByDefault
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return this.combine(state, level, pos, false).apply(MENU_PROVIDER_COMBINER).orElse(null);
    }

    public static DoubleBlockCombiner.Combiner<ValyrianChestBlockEntity, Float2FloatFunction> opennessCombiner(final LidBlockEntity lid) {
        return new DoubleBlockCombiner.Combiner<>() {
            @ParametersAreNonnullByDefault
            public @NotNull Float2FloatFunction acceptDouble(ValyrianChestBlockEntity first, ValyrianChestBlockEntity second) {
                return ticks -> Math.max(first.getOpenNess(ticks), second.getOpenNess(ticks));
            }

            @ParametersAreNonnullByDefault
            public @NotNull Float2FloatFunction acceptSingle(ValyrianChestBlockEntity entity) {
                Objects.requireNonNull(entity);
                return entity::getOpenNess;
            }

            public @NotNull Float2FloatFunction acceptNone() {
                Objects.requireNonNull(lid);
                return lid::getOpenNess;
            }
        };
    }

    @Override
    @ParametersAreNonnullByDefault
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ValyrianChestBlockEntity(pos, state);
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide() ? createTickerHelper(blockEntityType, this.blockEntityType(), ValyrianChestBlockEntity::lidAnimateTick) : null;
    }

    public static boolean isChestBlockedAt(LevelAccessor level, BlockPos pos) {
        return isBlockedChestByBlock(level, pos) || isCatSittingOnChest(level, pos);
    }

    private static boolean isBlockedChestByBlock(BlockGetter level, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return level.getBlockState(blockpos).isRedstoneConductor(level, blockpos);
    }

    private static boolean isCatSittingOnChest(LevelAccessor level, BlockPos pos) {
        List<Cat> list = level.getEntitiesOfClass(Cat.class, new AABB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1));
        if (!list.isEmpty()) {
            for(Cat cat : list) {
                if (cat.isInSittingPose()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer(getContainer(this, state, level, pos, false));
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        BlockState rotated = state.rotate(mirror.getRotation(state.getValue(FACING)));
        return mirror == Mirror.NONE ? rotated : rotated.setValue(TYPE, rotated.getValue(TYPE).getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE, WATERLOGGED);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof ValyrianChestBlockEntity valyrianChestBlockEntity) {
            valyrianChestBlockEntity.recheckOpen();
        }
    }

    public SoundEvent getOpenChestSound() {
        return this.openSound;
    }

    public SoundEvent getCloseChestSound() {
        return this.closeSound;
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
        TYPE = BlockStateProperties.CHEST_TYPE;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        SHAPE = Block.column(14.0F, 0.0F, 14.0F);
        HALF_SHAPES = Shapes.rotateHorizontal(Block.boxZ(14.0F, 0.0F, 14.0F, 0.0F, 15.0F));
        CHEST_COMBINER = new DoubleBlockCombiner.Combiner<>() {
            public @NotNull Optional<Container> acceptDouble(@NotNull ValyrianChestBlockEntity first, @NotNull ValyrianChestBlockEntity second) {
                return Optional.of(new CompoundContainer(first, second));
            }

            public @NotNull Optional<Container> acceptSingle(@NotNull ValyrianChestBlockEntity entity) {
                return Optional.of(entity);
            }

            public @NotNull Optional<Container> acceptNone() {
                return Optional.empty();
            }
        };
        MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<>() {
            public @NotNull Optional<MenuProvider> acceptDouble(final @NotNull ValyrianChestBlockEntity first, final @NotNull ValyrianChestBlockEntity second) {
                final Container container = new CompoundContainer(first, second);
                return Optional.of(new MenuProvider() {
                    @Nullable
                    public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory, @NotNull Player player) {
                        if (first.canOpen(player) && second.canOpen(player)) {
                            first.unpackLootTable(inventory.player);
                            second.unpackLootTable(inventory.player);
                            return ValyrianChestMenu.menu9x12(containerId, inventory, container);
                        } else {
                            return null;
                        }
                    }

                    public @NotNull Component getDisplayName() {
                        if (first.hasCustomName()) {
                            return first.getDisplayName();
                        } else {
                            return (second.hasCustomName() ? second.getDisplayName() : Component.translatable("container." + OresAndToolsMod.MOD_ID + ".large_valyrian_chest"));
                        }
                    }
                });
            }

            public @NotNull Optional<MenuProvider> acceptSingle(@NotNull ValyrianChestBlockEntity single) {
                return Optional.of(single);
            }

            public @NotNull Optional<MenuProvider> acceptNone() {
                return Optional.empty();
            }
        };
    }
}
*/
