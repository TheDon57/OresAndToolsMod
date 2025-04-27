package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.item.ModArmorMaterials;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ModArmorItem extends ArmorItem {
    private final boolean withToolTip;

    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        this(material, type, properties.durability(type.getDurability(ModArmorMaterials.durabilityMultiplier(material))), false);
    }

    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, boolean withToolTip) {
        super(material, type, properties.durability(type.getDurability(ModArmorMaterials.durabilityMultiplier(material))));
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
