package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.item.inventory.BackpackContainer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BackpackItem extends ModItem {
    public BackpackItem(Properties properties, boolean withToolTip) {
        super(properties.stacksTo(1), withToolTip);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        if (!level.isClientSide()) {
            Item item = itemstack.getItem();
            if (item instanceof BackpackItem) {
                player.openMenu(new BackpackContainer(itemstack));
                return InteractionResult.PASS;
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean canFitInsideContainerItems() {
        return false;
    }
}
