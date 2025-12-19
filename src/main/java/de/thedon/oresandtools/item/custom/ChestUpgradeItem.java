package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.block.custom.EnderiteChestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChestUpgradeItem extends Item {
    private static final Block UPGRADE_TARGET = ModBlocks.ENDERITE_CHEST.get();
    private static final List<Block> UPGRADABLES = List.of(
            Blocks.CHEST,
            Blocks.COPPER_CHEST,
            Blocks.WAXED_COPPER_CHEST,
            Blocks.OXIDIZED_COPPER_CHEST,
            Blocks.EXPOSED_COPPER_CHEST,
            Blocks.WEATHERED_COPPER_CHEST,
            Blocks.WAXED_EXPOSED_COPPER_CHEST,
            Blocks.WAXED_OXIDIZED_COPPER_CHEST,
            Blocks.WAXED_WEATHERED_COPPER_CHEST
    );

    public ChestUpgradeItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        if (state.getBlock() instanceof ChestBlock baseChestBlock && UPGRADABLES.contains(baseChestBlock)) {
            BlockPos neighborPos = ChestBlock.getConnectedBlockPos(pos, state);

            upgradeChest(level, pos);
            if (level.getBlockState(neighborPos).getBlock() instanceof ChestBlock) {
                upgradeChest(level, neighborPos);
            }

            context.getItemInHand().shrink(1);
        }

        return InteractionResult.SUCCESS;
    }

    protected static void upgradeChest(Level level, BlockPos pos) {
        BlockState currentState = level.getBlockState(pos);
        BlockState upgradedState = UPGRADE_TARGET.defaultBlockState()
                .setValue(EnderiteChestBlock.TYPE, currentState.getValue(EnderiteChestBlock.TYPE))
                .setValue(EnderiteChestBlock.FACING, currentState.getValue(EnderiteChestBlock.FACING))
                .setValue(EnderiteChestBlock.WATERLOGGED, currentState.getValue(EnderiteChestBlock.WATERLOGGED));

        NonNullList<ItemStack> items = null;
        var optionalChest = level.getBlockEntity(pos, BlockEntityType.CHEST);
        if (optionalChest.isPresent()) {
            ChestBlockEntity chestBlockEntity = optionalChest.get();
            items = NonNullList.withSize(chestBlockEntity.getContainerSize(), ItemStack.EMPTY);
            for (int i = 0; i < chestBlockEntity.getContainerSize(); i++) {
                items.set(i, chestBlockEntity.getItem(i));
            }
            chestBlockEntity.clearContent();
        }

        level.setBlockAndUpdate(pos, upgradedState);

        Block block = level.getBlockState(pos).getBlock();
        if (block instanceof ChestBlock chestBlock && chestBlock.equals(UPGRADE_TARGET)) {
            Container container = ChestBlock.getContainer(chestBlock, level.getBlockState(pos), level, pos, true);
            if (container != null && items != null) {
                boolean isFull = false;
                for (ItemStack itemStack : items) {
                    boolean inserted = false;
                    if (!isFull) {
                        for (int i = 0; i < container.getContainerSize(); i++) {
                            if (container.getItem(i) == ItemStack.EMPTY) {
                                container.setItem(i, itemStack);
                                inserted = true;
                                break;
                            }
                        }
                    }

                    if (!inserted) {
                        isFull = true;
                        level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), itemStack));
                    }
                }
            }
        }
    }
}
