package de.thedon.oresandtools.item.custom;

import net.minecraft.world.item.ToolMaterial;

public class ModSwordItem extends ModItem {

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
        super(properties.sword(material, attackDamage, attackSpeed), withToolTip);
    }
}
