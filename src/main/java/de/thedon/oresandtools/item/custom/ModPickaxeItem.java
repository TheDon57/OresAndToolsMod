package de.thedon.oresandtools.item.custom;

import net.minecraft.world.item.ToolMaterial;

public class ModPickaxeItem extends ModItem {

    public ModPickaxeItem(ToolMaterial material, Properties properties) {
        this(material, properties, false);
    }

    public ModPickaxeItem(ToolMaterial material, Properties properties, boolean withToolTip) {
        this(material, 1f, -2.8f, properties, withToolTip);
    }

    public ModPickaxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        this(material, attackDamage, attackSpeed, properties, false);
    }

    public ModPickaxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties, boolean withToolTip) {
        super(properties.pickaxe(material, attackDamage, attackSpeed), withToolTip);
    }
}
