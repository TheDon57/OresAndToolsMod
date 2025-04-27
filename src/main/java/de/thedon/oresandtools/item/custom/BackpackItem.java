package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.inventory.BackpackItemStackHandler;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BackpackItem extends ModItem {
    public BackpackItem(Properties pProperties, boolean withToolTip) {
        super(pProperties.stacksTo(1), withToolTip);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide()) {
            Item item = itemstack.getItem();
            if (item instanceof BackpackItem) {
                pPlayer.openMenu(new BackpackItemStackHandler(itemstack));
                return InteractionResultHolder.pass(itemstack);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }
}
