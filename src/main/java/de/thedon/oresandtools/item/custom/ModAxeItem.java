package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ModAxeItem extends AxeItem {
    private final boolean withToolTip;

    public ModAxeItem(ToolMaterial material, Properties properties) {
        this(material, properties, false);
    }

    public ModAxeItem(ToolMaterial material, Properties properties, boolean withToolTip) {
        this(material, 6f, -3.1f, properties, withToolTip);
    }

    public ModAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        this(material, attackDamage, attackSpeed, properties, false);
    }

    public ModAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties, boolean withToolTip) {
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
