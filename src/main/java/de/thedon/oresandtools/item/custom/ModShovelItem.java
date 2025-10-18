package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ModShovelItem extends ShovelItem {
    private final boolean withToolTip;

    public ModShovelItem(ToolMaterial material, Properties properties) {
        this(material, properties, false);
    }

    public ModShovelItem(ToolMaterial material, Properties properties, boolean withToolTip) {
        this(material, 1.5f, -3f, properties, withToolTip);
    }

    public ModShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        this(material, attackDamage, attackSpeed, properties, false);
    }

    public ModShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties, boolean withToolTip) {
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
