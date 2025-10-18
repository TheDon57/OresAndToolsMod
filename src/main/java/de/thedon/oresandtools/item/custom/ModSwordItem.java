package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ModSwordItem extends SwordItem {
    private final boolean withToolTip;

    public ModSwordItem(ToolMaterial material, Properties properties) {
        this(material, properties, false);
    }

    public ModSwordItem(ToolMaterial material, Properties properties, boolean withToolTip) {
        this(material, 3, -2.4f, properties, withToolTip);
    }

    public ModSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Properties properties) {
        this(material, attackDamage, attackSpeed, properties, false);
    }

    public ModSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Properties properties, boolean withToolTip) {
        super(material, attackDamage, attackSpeed, properties);
        this.withToolTip = withToolTip;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (withToolTip) {
            if (Screen.hasShiftDown()) {
                tooltipComponents.add(Component.translatable("tooltip." + this.getDescriptionId().substring(5)));
            } else {
                tooltipComponents.add(Component.translatable("tooltip." + OresAndToolsMod.MOD_ID + ".hold_shift"));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
