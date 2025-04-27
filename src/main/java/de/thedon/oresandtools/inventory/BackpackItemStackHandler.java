package de.thedon.oresandtools.inventory;

import de.thedon.oresandtools.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BackpackItemStackHandler implements IItemHandlerModifiable, MenuProvider, Nameable {
    private final ItemStack stack;
    @Nullable
    private Component name;

    public BackpackItemStackHandler(ItemStack stack) {
        this.stack = stack;
        this.name = stack.get(DataComponents.CUSTOM_NAME);
    }

    @Override
    public int getSlots(){
        return 54;
    }

    @Override
    @NotNull
    public ItemStack getStackInSlot(int slot) {
        validateSlotIndex(slot);
        return getItemList().get(slot);
    }

    @Override
    @NotNull
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        if (!isItemValid(slot, stack))
            return stack;

        validateSlotIndex(slot);

        NonNullList<ItemStack> itemStacks = getItemList();
        ItemStack existing = itemStacks.get(slot);

        int limit = Math.min(getSlotLimit(slot), stack.getMaxStackSize());

        if (!existing.isEmpty()) {
            if (!ItemStack.isSameItemSameComponents(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate) {
            if (existing.isEmpty()) {
                itemStacks.set(slot, reachedLimit ? stack.copyWithCount(limit) : stack);
            } else {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
            setItemList(itemStacks);
        }

        return reachedLimit ? stack.copyWithCount(stack.getCount()- limit) : ItemStack.EMPTY;
    }

    @Override
    @NotNull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        NonNullList<ItemStack> itemStacks = getItemList();
        if (amount == 0)
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = itemStacks.get(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract) {
            if (!simulate) {
                itemStacks.set(slot, ItemStack.EMPTY);
                setItemList(itemStacks);
                return existing;
            } else {
                return existing.copy();
            }
        } else {
            if (!simulate) {
                itemStacks.set(slot, existing.copyWithCount(existing.getCount() - toExtract));
                setItemList(itemStacks);
            }

            return existing.copyWithCount(toExtract);
        }
    }

    private void validateSlotIndex(int slot) {
        if (slot < 0 || slot >= getSlots())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + getSlots() + ")");
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return stack.getItem().canFitInsideContainerItems();
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {
        validateSlotIndex(slot);
        if (!isItemValid(slot, stack)) throw new RuntimeException("Invalid stack " + stack + " for slot " + slot + ")");
        NonNullList<ItemStack> itemStacks = getItemList();
        itemStacks.set(slot, stack);
        setItemList(itemStacks);
    }

    private NonNullList<ItemStack> getItemList() {
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(getSlots(), ItemStack.EMPTY);
        ItemContainerContents contents = stack.get(DataComponents.CONTAINER);
        if (contents != null) {
            contents.copyInto(itemStacks);
        }
        return itemStacks;
    }

    private void setItemList(NonNullList<ItemStack> itemStacks) {
        stack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(itemStacks));
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pId, @NotNull Inventory pInventory, @NotNull Player pPlayer) {
        return new BackpackMenu(pId, pInventory, this);
    }

    @Override
    public boolean hasCustomName() {
        return Nameable.super.hasCustomName();
    }

    @Override
    public @NotNull Component getName() {
        return this.name != null ? this.name : this.getDefaultName();
    }

    @Nullable
    public Component getCustomName() { return this.name; }

    public @NotNull Component getDefaultName() {
        return Component.translatable(ModItems.SHULKER_BACKPACK.get().getDescriptionId());
    }

    @Override
    public @NotNull Component getDisplayName() {
        return this.getName();
    }
}
