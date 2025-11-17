package de.thedon.oresandtools.item.equipment;

import com.google.common.collect.Maps;
import de.thedon.oresandtools.util.ModTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public class ModArmorMaterials {
    public static final ArmorMaterial STEEL;
    public static final ArmorMaterial ENDERITE;
    public static final ArmorMaterial H_DIAMOND;
    public static final ArmorMaterial MOLTEN;
    public static final ArmorMaterial EMERALD;
    public static final ArmorMaterial OBSIDIAN;

    private static Map<ArmorType, Integer> makeDefense(int boots, int leggings, int chestplate, int helmet, int body) {
        return Maps.newEnumMap(
                Map.of(
                        ArmorType.BOOTS, boots,
                        ArmorType.LEGGINGS, leggings,
                        ArmorType.CHESTPLATE, chestplate,
                        ArmorType.HELMET, helmet,
                        ArmorType.BODY, body
                )
        );
    }

    static {
        STEEL = new ArmorMaterial(20, makeDefense(2, 5, 7, 3, 6),
                9, SoundEvents.ARMOR_EQUIP_IRON, 0.0f, 0.0f, ModTags.Items.STEEL_REPAIR_MATERIALS, ModEquipmentAssets.STEEL);

        EMERALD = new ArmorMaterial(28, makeDefense(3, 6, 8, 3, 11),
                12, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0f, 0.0f, ModTags.Items.EMERALD_REPAIR_MATERIALS, ModEquipmentAssets.EMERALD);

        MOLTEN = new ArmorMaterial(35, makeDefense(3, 6, 8, 3, 11),
                15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0f, 0.0f, ModTags.Items.MOLTEN_REPAIR_MATERIALS, ModEquipmentAssets.MOLTEN);

        H_DIAMOND = new ArmorMaterial(39, makeDefense(3, 6, 8, 3, 11),
                10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0f, 0.1f, ModTags.Items.H_DIAMOND_REPAIR_MATERIALS, ModEquipmentAssets.H_DIAMOND);

        OBSIDIAN = new ArmorMaterial(43, makeDefense(3, 6, 7, 3, 7),
                9, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0f, 0.5f, ModTags.Items.OBSIDIAN_REPAIR_MATERIALS, ModEquipmentAssets.OBSIDIAN);

        ENDERITE = new ArmorMaterial(50, makeDefense(4, 7, 10, 4, 15),
                20, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0f, 0.2f, ModTags.Items.ENDERITE_REPAIR_MATERIALS, ModEquipmentAssets.ENDERITE);
    }
}
