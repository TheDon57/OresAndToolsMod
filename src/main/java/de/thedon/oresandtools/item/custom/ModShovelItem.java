package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ModShovelItem extends ShovelItem {
    private final boolean withToolTip;

    public ModShovelItem(Tier tier, Properties properties) {
        this(tier, properties, false);
    }

    public ModShovelItem(Tier tier, Properties properties, boolean withToolTip) {
        this(tier, 1.5f, -3f, properties, withToolTip);
    }

    public ModShovelItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
            this(tier, attackDamage, attackSpeed, properties, false);
        }

    public ModShovelItem(Tier tier, float attackDamage, float attackSpeed, Properties properties, boolean withToolTip) {
        super(tier, properties.attributes(ShovelItem.createAttributes(tier, attackDamage, attackSpeed)));
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
