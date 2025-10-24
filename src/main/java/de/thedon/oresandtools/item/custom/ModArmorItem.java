package de.thedon.oresandtools.item.custom;

import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class ModArmorItem extends ModItem {

    public ModArmorItem(ArmorMaterial material, ArmorType type, Properties properties) {
        this(material, type, properties, false);
    }

    public ModArmorItem(ArmorMaterial material, ArmorType type, Properties properties, boolean withToolTip) {
        super(properties.humanoidArmor(material, type), withToolTip);
    }
}
