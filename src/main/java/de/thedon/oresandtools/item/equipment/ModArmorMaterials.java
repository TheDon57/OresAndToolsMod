package de.thedon.oresandtools.item.equipment;

import de.thedon.oresandtools.util.ModTags;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;

public class ModArmorMaterials {
    public static final ArmorMaterial COPPER = new ArmorMaterial(10, protectionForType(1, 3, 4, 2, 4),
            17, SoundEvents.ARMOR_EQUIP_GOLD, 0.0f, 0.0f, ModTags.Items.COPPER_REPAIRABLE, ModEquipmentAssets.COPPER);

    public static final ArmorMaterial STEEL = new ArmorMaterial(20, protectionForType(2, 5, 7, 3, 6),
            9, SoundEvents.ARMOR_EQUIP_IRON, 0.0f, 0.0f, ModTags.Items.STEEL_REPAIRABLE, ModEquipmentAssets.STEEL);

    public static final ArmorMaterial VALYRIAN = new ArmorMaterial(50, protectionForType(4, 7, 10, 4, 15),
            25, SoundEvents.ARMOR_EQUIP_GOLD, 3.0f, 0.0f, ModTags.Items.VALYRIAN_REPAIRABLE, ModEquipmentAssets.VALYRIAN);

    public static final ArmorMaterial H_DIAMOND = new ArmorMaterial(65, protectionForType(3, 6, 8, 3, 11),
            10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0f, 0.0f, ModTags.Items.HARDENED_DIAMOND_REPAIRABLE, ModEquipmentAssets.H_DIAMOND);

    public static final ArmorMaterial HOT_H_DIAMOND = new ArmorMaterial(59, protectionForType(3, 6, 8, 3, 11),
            11, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0f, 0.0f, ModTags.Items.HOT_HARDENED_DIAMOND_REPAIRABLE, ModEquipmentAssets.HOT_H_DIAMOND);

    public static final ArmorMaterial EMERALD = new ArmorMaterial(33, protectionForType(3, 6, 8, 3, 11),
            14, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.5f, 0.0f, ModTags.Items.EMERALD_REPAIRABLE, ModEquipmentAssets.EMERALD);

    public static final ArmorMaterial OBSIDIAN = new ArmorMaterial(62, protectionForType(3, 7, 9, 4, 13),
            9, SoundEvents.ARMOR_EQUIP_NETHERITE, 1.5f, 0.5f, ModTags.Items.OBSIDIAN_REPAIRABLE, ModEquipmentAssets.OBSIDIAN);

    private static EnumMap<ArmorType, Integer> protectionForType(int boots, int leggings, int chestplate, int helmet, int body) {
        return Util.make(new EnumMap<>(ArmorType.class), (type) -> {
            type.put(ArmorType.BOOTS, boots);
            type.put(ArmorType.LEGGINGS, leggings);
            type.put(ArmorType.CHESTPLATE, chestplate);
            type.put(ArmorType.HELMET, helmet);
            type.put(ArmorType.BODY, body);
        });
    }
}
