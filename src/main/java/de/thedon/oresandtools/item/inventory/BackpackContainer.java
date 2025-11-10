package de.thedon.oresandtools.item.inventory;

import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.screen.custom.BackpackMenu;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.TransferPreconditions;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.SnapshotJournal;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BackpackContainer implements Container, MenuProvider, Nameable {
    private final ItemStack stack;
    @Nullable
    private Component name;
    private NonNullList<ItemStack> items;

    public BackpackContainer(ItemStack stack) {
        this.stack = stack;
        this.name = stack.get(DataComponents.CUSTOM_NAME);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @Nullable AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new BackpackMenu(id, inventory, this);
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

    @Override
    public int getContainerSize() {
        return 54;
    }

    protected NonNullList<ItemStack> getItems() {
        if (items == null) {
            items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
            ItemContainerContents contents = stack.get(DataComponents.CONTAINER);
            if (contents != null) {
                contents.copyInto(items);
            }

        }

        return items;
    }

    protected void setItems(NonNullList<ItemStack> items) {
        stack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(items));
    }

    public boolean isEmpty() {
        for(ItemStack itemstack : this.getItems()) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public @NotNull ItemStack getItem(int index) {
        return this.getItems().get(index);
    }

    public @NotNull ItemStack removeItem(int index, int amount) {
        ItemStack itemstack = ContainerHelper.removeItem(this.getItems(), index, amount);
        if (!itemstack.isEmpty()) {
            this.setChanged();
        }

        return itemstack;
    }

    public @NotNull ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.getItems(), index);
    }

    public void setItem(int index, @NotNull ItemStack stack) {
        this.setItem(index, stack, false);
    }

    @Override
    public void setChanged() {
        setItems(getItems());
        items = null;
    }

    public void setItem(int index, @NotNull ItemStack stack, boolean insideTransaction) {
        this.getItems().set(index, stack);

        stack.limitSize(this.getMaxStackSize(stack));
        if (!insideTransaction) {
            this.setChanged();
        }
    }

    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    public void clearContent() {
        this.getItems().clear();
    }
}
