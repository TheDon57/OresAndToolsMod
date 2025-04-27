package de.thedon.oresandtools.inventory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ValyrianChestMenu extends AbstractContainerMenu {
    private final Container container;
    private final int rows;
    private final int columns;

    private ValyrianChestMenu(MenuType<?> pType, int pContainerId, Inventory pPlayerInventory, int pRows, int pColumns) {
        this(pType, pContainerId, pPlayerInventory, new SimpleContainer(pRows * pColumns), pRows, pColumns);
    }

    public static ValyrianChestMenu menu6x9(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf pExtraData) {
        return new ValyrianChestMenu(ModMenuTypes.VALYRIAN_CHEST_MENU_6x9.get(), pContainerId, pPlayerInventory, 6, 9);
    }

    public static ValyrianChestMenu menu9x12(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf pExtraData) {
        return new ValyrianChestMenu(ModMenuTypes.VALYRIAN_CHEST_MENU_9x12.get(), pContainerId, pPlayerInventory, 9, 12);
    }

    public static ValyrianChestMenu menu6x9(int pContainerId, Inventory pPlayerInventory, Container pInventory) {
        return new ValyrianChestMenu(ModMenuTypes.VALYRIAN_CHEST_MENU_6x9.get(), pContainerId, pPlayerInventory, pInventory, 6, 9);
    }

    public static ValyrianChestMenu menu9x12(int pContainerId, Inventory pPlayerInventory, Container pInventory) {
        return new ValyrianChestMenu(ModMenuTypes.VALYRIAN_CHEST_MENU_9x12.get(), pContainerId, pPlayerInventory, pInventory, 9, 12);
    }

    public ValyrianChestMenu(MenuType<?> pType, int pContainerId, Inventory pPlayerInventory, Container pInventory, int pRows, int pColumns) {
        super(pType, pContainerId);

        checkContainerSize(pInventory, pRows * pColumns);

        this.container = pInventory;
        this.rows = pRows;
        this.columns = pColumns;

        pInventory.startOpen(pPlayerInventory.player);

        int i = (this.rows - 4) * 18;
        int chestSlotsYOffset = is6x9() ? 18 : 8;
        int invSlotsXOffset = is6x9() ? 8 : 35;
        int invSlotsYOffset = is6x9() ? 103 : 83;
        int barSlotsYOffset = is6x9() ? 161 : 141;

        for(int j = 0; j < this.rows; ++j) {
            for(int k = 0; k < this.columns; ++k) {
                this.addSlot(new Slot(pInventory, k + j * this.columns, 8 + k * 18, chestSlotsYOffset + j * 18));
            }
        }

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(pPlayerInventory, j1 + l * 9 + 9, invSlotsXOffset + j1 * 18, invSlotsYOffset + l * 18 + i));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(pPlayerInventory, i1, invSlotsXOffset + i1 * 18, barSlotsYOffset + i));
        }
    }

    private boolean is6x9() {
        return this.rows * this.columns == 54;
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return this.container.stillValid(pPlayer);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex < this.rows * this.columns) {
                if (!this.moveItemStackTo(itemstack1, this.rows * this.columns, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.rows * this.columns, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void removed(@NotNull Player pPlayer) {
        super.removed(pPlayer);
        this.container.stopOpen(pPlayer);
    }

    public Container getContainer() {
        return this.container;
    }

    public int getRowCount() {
        return this.rows;
    }
}
