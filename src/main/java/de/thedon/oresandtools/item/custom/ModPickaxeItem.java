package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ModPickaxeItem extends PickaxeItem {
    private final boolean withToolTip;

    public ModPickaxeItem(Tier tier, Properties properties) {
        this(tier, properties, false);
    }

    public ModPickaxeItem(Tier tier, Properties properties, boolean withToolTip) {
        this(tier, 1f, -2.8f, properties, withToolTip);
    }

    public ModPickaxeItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        this(tier, attackDamage, attackSpeed, properties, false);
    }

    public ModPickaxeItem(Tier tier, float attackDamage, float attackSpeed, Properties properties, boolean withToolTip) {
        super(tier, properties.attributes(PickaxeItem.createAttributes(tier, attackDamage, attackSpeed)));
        this.withToolTip = withToolTip;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (withToolTip) {
            if (Screen.hasShiftDown()) {
                pTooltipComponents.add(Component.translatable("tooltip." + this.getDescriptionId().substring(5)));
            } else {
                pTooltipComponents.add(Component.translatable("tooltip." + OresAndToolsMod.MOD_ID + ".hold_shift"));
            }
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
