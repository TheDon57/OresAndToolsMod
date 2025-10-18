package de.thedon.oresandtools.screen.custom;

import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.item.custom.BackpackItemStackHandler;
import de.thedon.oresandtools.screen.ModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class BackpackMenu extends AbstractContainerMenu {
    private static final int ROWS = 6;
    private final BackpackItemStackHandler inventory;

    public BackpackMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf pExtraData) {
        this(pContainerId, pPlayerInventory, new BackpackItemStackHandler(new ItemStack(ModItems.SHULKER_BACKPACK.get())));
    }

    public BackpackMenu(int containerId, Inventory playerInventory, BackpackItemStackHandler inventory) {
        super(ModMenuTypes.BACKPACK_MENU.get(), containerId);
        this.inventory = inventory;
        int i = (ROWS - 4) * 18;

        checkContainerSize(this.inventory, ROWS * 9);

        for(int j = 0; j < ROWS; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new SlotItemHandler(this.inventory, k + j * 9, 8 + k * 18, 18 + j * 18));
            }
        }

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(playerInventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 161 + i));
        }
    }

    protected static void checkContainerSize(IItemHandler itemHandler, int minSize) {
        int i = itemHandler.getSlots();
        if (i < minSize) {
            throw new IllegalArgumentException("Container size " + i + " is smaller than expected " + minSize);
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < ROWS * 9) {
                if (!this.moveItemStackTo(itemstack1, ROWS * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, ROWS * 9, false)) {
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
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    public int getRowCount() {
        return ROWS;
    }

}
