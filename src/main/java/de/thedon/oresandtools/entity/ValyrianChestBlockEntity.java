package de.thedon.oresandtools.entity;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.inventory.ValyrianChestMenu;
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
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

public class ValyrianChestBlockEntity extends RandomizableContainerBlockEntity implements LidBlockEntity {
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @ParametersAreNonnullByDefault
        protected void onOpen(Level pLevel, BlockPos pPos, BlockState pState) {
            ValyrianChestBlockEntity.playSound(pLevel, pPos, pState, SoundEvents.CHEST_OPEN);
        }

        @ParametersAreNonnullByDefault
        protected void onClose(Level pLevel, BlockPos pPos, BlockState pState) {
            ValyrianChestBlockEntity.playSound(pLevel, pPos, pState, SoundEvents.CHEST_CLOSE);
        }

        @ParametersAreNonnullByDefault
        protected void openerCountChanged(Level pLevel, BlockPos pPos, BlockState pState, int pEventId, int pEventParam) {
            ValyrianChestBlockEntity.this.signalOpenCount(pLevel, pPos, pState, pEventId, pEventParam);
        }

        protected boolean isOwnContainer(Player player) {
            if (!(player.containerMenu instanceof ValyrianChestMenu)) {
                return false;
            } else {
                Container container = ((ValyrianChestMenu) player.containerMenu).getContainer();
                return container instanceof ValyrianChestBlockEntity
                    || container instanceof CompoundContainer && ((CompoundContainer) container).contains(ValyrianChestBlockEntity.this);
            }
        }
    };

    private static final int EVENT_SET_OPEN_COUNT = 1;
    private NonNullList<ItemStack> items;

    private final ChestLidController chestLidController = new ChestLidController();

    public ValyrianChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(ModBlockEntities.VALYRIAN_CHEST.get(), blockPos, blockState);
    }

    public ValyrianChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        this.items = NonNullList.withSize(54, ItemStack.EMPTY);
    }

    @Override
    public int getContainerSize() {
        return this.getItems().size();
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable("block." + OresAndToolsMod.MOD_ID + ".valyrian_chest");
    }

    @Override
    @ParametersAreNonnullByDefault
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);

        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

        if (!this.tryLoadLootTable(pTag)) {
            ContainerHelper.loadAllItems(pTag, this.items, pRegistries);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);

        if (!this.trySaveLootTable(pTag)) {
            ContainerHelper.saveAllItems(pTag, this.items, pRegistries);
        }
    }

    public static void lidAnimateTick(Level pLevel, BlockPos pPos, BlockState pState, ValyrianChestBlockEntity pBlockEntity) {
        pBlockEntity.chestLidController.tickLid();
    }

    static void playSound(Level pLevel, BlockPos pPos, BlockState pState, SoundEvent pSound) {
        ChestType chesttype = pState.getValue(ChestBlock.TYPE);
        if (chesttype != ChestType.LEFT) {
            double d0 = (double)pPos.getX() + 0.5;
            double d1 = (double)pPos.getY() + 0.5;
            double d2 = (double)pPos.getZ() + 0.5;
            if (chesttype == ChestType.RIGHT) {
                Direction direction = ChestBlock.getConnectedDirection(pState);
                d0 += (double)direction.getStepX() * 0.5;
                d2 += (double)direction.getStepZ() * 0.5;
            }

            pLevel.playSound(null, d0, d1, d2, pSound, SoundSource.BLOCKS, 0.5F, pLevel.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    @Override
    public boolean triggerEvent(int pId, int pType) {
        if (pId == 1) {
            this.chestLidController.shouldBeOpen(pType > 0);
            return true;
        } else {
            return super.triggerEvent(pId, pType);
        }
    }

    @Override
    public void startOpen(@NotNull Player pPlayer) {
        if (!this.remove && !pPlayer.isSpectator()) {
            this.openersCounter.incrementOpeners(pPlayer, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(@NotNull Player pPlayer) {
        if (!this.remove && !pPlayer.isSpectator()) {
            this.openersCounter.decrementOpeners(pPlayer, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = NonNullList.<ItemStack>withSize(this.getContainerSize(), ItemStack.EMPTY);

        for (int i = 0; i < itemsIn.size(); i++) {
            if (i < this.items.size()) {
                this.getItems().set(i, itemsIn.get(i));
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

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int pId, @NotNull Inventory pPlayer) {
        return ValyrianChestMenu.menu6x9(pId, pPlayer, this);
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
