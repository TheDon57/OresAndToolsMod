/*
package de.thedon.oresandtools.block.entity;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.custom.ValyrianChestBlock;
import de.thedon.oresandtools.screen.custom.ValyrianChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;

public class ValyrianChestBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private static final int EVENT_SET_OPEN_COUNT = 1;
    private static final Component DEFAULT_NAME = Component.translatable("block." + OresAndToolsMod.MOD_ID + ".valyrian_chest");
    private static final int ITEMS_SIZE = 54;
    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounter;
    private final ChestLidController chestLidController;

    public ValyrianChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(ModBlockEntities.VALYRIAN_CHEST.get(), blockPos, blockState);
    }

    public ValyrianChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        this.items = NonNullList.withSize(ITEMS_SIZE, ItemStack.EMPTY);
        this.openersCounter = new ContainerOpenersCounter() {
            @ParametersAreNonnullByDefault
            protected void onOpen(Level level, BlockPos pos, BlockState state) {
                Block block = state.getBlock();
                if (block instanceof ValyrianChestBlock chestBlock) {
                    ValyrianChestBlockEntity.playSound(level, pos, state, chestBlock.getOpenChestSound());
                }
            }

            @ParametersAreNonnullByDefault
            protected void onClose(Level level, BlockPos pos, BlockState state) {
                Block block = state.getBlock();
                if (block instanceof ValyrianChestBlock chestBlock) {
                    ValyrianChestBlockEntity.playSound(level, pos, state, chestBlock.getCloseChestSound());
                }
            }

            @ParametersAreNonnullByDefault
            protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int eventId, int eventParam) {
                ValyrianChestBlockEntity.this.signalOpenCount(level, pos, state, eventId, eventParam);
            }

            public boolean isOwnContainer(@NotNull Player player) {
                if (!(player.containerMenu instanceof ValyrianChestMenu)) {
                    return false;
                } else {
                    Container container = ((ValyrianChestMenu)player.containerMenu).getContainer();
                    return container == ValyrianChestBlockEntity.this || container instanceof CompoundContainer && ((CompoundContainer)container).contains(ValyrianChestBlockEntity.this);
                }
            }
        };
        this.chestLidController = new ChestLidController();
    }

    @Override
    public int getContainerSize() {
        return ITEMS_SIZE;
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return DEFAULT_NAME;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(input)) {
            ContainerHelper.loadAllItems(input, this.items);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (!this.trySaveLootTable(output)) {
            ContainerHelper.saveAllItems(output, this.items);
        }
    }

    public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, ValyrianChestBlockEntity blockEntity) {
        blockEntity.chestLidController.tickLid();
    }

    static void playSound(Level level, BlockPos pos, BlockState state, SoundEvent sound) {
        ChestType chesttype = state.getValue(ChestBlock.TYPE);
        if (chesttype != ChestType.LEFT) {
            double d0 = (double)pos.getX() + 0.5;
            double d1 = (double)pos.getY() + 0.5;
            double d2 = (double)pos.getZ() + 0.5;
            if (chesttype == ChestType.RIGHT) {
                Direction direction = ChestBlock.getConnectedDirection(state);
                d0 += (double)direction.getStepX() * 0.5;
                d2 += (double)direction.getStepZ() * 0.5;
            }

            level.playSound(null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.chestLidController.shouldBeOpen(type > 0);
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    @Override
    public void startOpen(@NotNull ContainerUser user) {
        if (!this.remove && !user.getLivingEntity().isSpectator()) {
            this.openersCounter.incrementOpeners(user.getLivingEntity(), this.getLevel(), this.getBlockPos(), this.getBlockState(), user.getContainerInteractionRange());
        }
    }

    @Override
    public void stopOpen(@NotNull ContainerUser user) {
        if (!this.remove && !user.getLivingEntity().isSpectator()) {
            this.openersCounter.decrementOpeners(user.getLivingEntity(), Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public @NotNull List<ContainerUser> getEntitiesWithContainerOpen() {
        return this.openersCounter.getEntitiesWithContainerOpen(this.getLevel(), this.getBlockPos());
    }

    @Override
    public @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> items) {
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < items.size(); i++) {
            if (i < this.items.size()) {
                this.getItems().set(i, items.get(i));
            }
        }
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return this.chestLidController.getOpenness(partialTicks);
    }

    public static int getOpenCount(BlockGetter blockGetter, BlockPos blockPos) {
        BlockState blockstate = blockGetter.getBlockState(blockPos);
        if (blockstate.hasBlockEntity()) {
            BlockEntity blockentity = blockGetter.getBlockEntity(blockPos);
            if (blockentity instanceof ValyrianChestBlockEntity) {
                return ((ValyrianChestBlockEntity) blockentity).openersCounter.getOpenerCount();
            }
        }

        return 0;
    }

    public static void swapContents(ValyrianChestBlockEntity chest, ValyrianChestBlockEntity otherChest) {
        NonNullList<ItemStack> nonnulllist = chest.getItems();
        chest.setItems(otherChest.getItems());
        otherChest.setItems(nonnulllist);
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory player) {
        return ValyrianChestMenu.menu6x9(id, player, this);
    }

    @Override
    public void setBlockState(@NotNull BlockState state) {
        BlockState oldState = this.getBlockState();
        super.setBlockState(state);
        if (oldState.getValue(ValyrianChestBlock.FACING) != state.getValue(ValyrianChestBlock.FACING) || oldState.getValue(ValyrianChestBlock.TYPE) != state.getValue(ValyrianChestBlock.TYPE)) {
            this.invalidateCapabilities();
        }

    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    protected void signalOpenCount(Level level, BlockPos blockPos, BlockState blockState, int previousCount, int newCount) {
        Block block = blockState.getBlock();
        level.blockEvent(blockPos, block, 1, newCount);
    }
}
*/
