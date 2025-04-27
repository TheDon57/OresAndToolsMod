package de.thedon.oresandtools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import javax.annotation.ParametersAreNonnullByDefault;

public class XpOreBlock extends RedStoneOreBlock {
    public static final Vector3f XP_PARTICLE_COLOR = new Vector3f(0.5F, 1.0F, 0.0F);
    public static final DustParticleOptions XP = new DustParticleOptions(XP_PARTICLE_COLOR, 1.0F);
    private final IntProvider xpRange;

    public XpOreBlock(BlockBehaviour.Properties pProperties, IntProvider pXpRange) {
        super(pProperties);
        this.xpRange = pXpRange;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        interact(pState, pLevel, pPos);
//        super.attack(pState, pLevel, pPos, pPlayer);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pEntity.isSteppingCarefully()) {
            interact(pState, pLevel, pPos);
        }
//        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected @NotNull ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (pLevel.isClientSide) {
            spawnParticles(pLevel, pPos);
        } else {
            interact(pState, pLevel, pPos);
        }

        return pStack.getItem() instanceof BlockItem && new BlockPlaceContext(pPlayer, pHand, pStack, pHitResult).canPlace()
                ? ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION
                : ItemInteractionResult.SUCCESS;
    }

    private static void interact(BlockState pState, Level pLevel, BlockPos pPos) {
        spawnParticles(pLevel, pPos);
        if (!pState.getValue(LIT)) {
            pLevel.setBlock(pPos, pState.setValue(LIT, true), 3);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public int getExpDrop(BlockState state, LevelAccessor level, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity breaker, ItemStack tool) {
        HolderLookup<Enchantment> holderLookup = level.holderLookup(Registries.ENCHANTMENT);
        boolean hasSilkTouch = tool.getTagEnchantments().keySet().contains(holderLookup.getOrThrow(Enchantments.SILK_TOUCH));
        return hasSilkTouch ? xpRange.sample(level.getRandom()) : 0;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LIT)) {
            spawnParticles(pLevel, pPos);
        }
    }

    private static void spawnParticles(Level pLevel, BlockPos pPos) {
        RandomSource randomsource = pLevel.random;

        for(Direction direction : Direction.values()) {
            BlockPos blockpos = pPos.relative(direction);
            if (!pLevel.getBlockState(blockpos).isSolidRender(pLevel, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)randomsource.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)randomsource.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)randomsource.nextFloat();
                pLevel.addParticle(XP, (double)pPos.getX() + d1, (double)pPos.getY() + d2, (double)pPos.getZ() + d3, 0.0D, 0.0D, 0.0D);
            }
        }

    }
}
