package de.thedon.oresandtools.item;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final Holder<ArmorMaterial> COPPER = register("copper", protectionForType(1, 3, 4, 2, 4), 17, 0.0f, 0.0f, SoundEvents.ARMOR_EQUIP_GOLD, () -> Items.COPPER_INGOT);
    public static final Holder<ArmorMaterial> STEEL = register("steel", protectionForType(2, 5, 7, 3, 6), 9, 0.0f, 0.0f, SoundEvents.ARMOR_EQUIP_IRON, ModItems.STEEL_INGOT);
    public static final Holder<ArmorMaterial> VALYRIAN = register("valyrian", protectionForType(4, 7, 10, 4, 15), 25, 3.0f, 0.0f, SoundEvents.ARMOR_EQUIP_GOLD, ModItems.VALYRIAN_INGOT);
    public static final Holder<ArmorMaterial> H_DIAMOND = register("hardened_diamond", protectionForType(3, 6, 8, 3, 11), 10, 2.0f, 0.0f, SoundEvents.ARMOR_EQUIP_DIAMOND, ModItems.HARDENED_DIAMOND);
    public static final Holder<ArmorMaterial> HOT_H_DIAMOND = register("hot_hardened_diamond", protectionForType(3, 6, 8, 3, 11), 11, 2.0f, 0.0f, SoundEvents.ARMOR_EQUIP_DIAMOND, ModItems.HOT_HARDENED_DIAMOND);
    public static final Holder<ArmorMaterial> EMERALD = register("emerald", protectionForType(3, 6, 8, 3, 11), 14, 2.5f, 0.0f, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Items.EMERALD);
    public static final Holder<ArmorMaterial> OBSIDIAN = register("obsidian", protectionForType(3, 7, 9, 4, 13), 9, 1.5f, 0.5f, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Items.OBSIDIAN);

    private static final LinkedHashMap<Holder<ArmorMaterial>, Integer> durabilityMultipliers = Util.make(new LinkedHashMap<>(), (map) -> {
        map.put(COPPER, 10);
        map.put(STEEL, 20);
        map.put(VALYRIAN, 50);
        map.put(H_DIAMOND, 65);
        map.put(HOT_H_DIAMOND, 59);
        map.put(EMERALD, 33);
        map.put(OBSIDIAN, 62);
    });

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance,
                                                  Holder<SoundEvent> equipSound, Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, name);
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }

    private static EnumMap<ArmorItem.Type, Integer> protectionForType(int boots, int leggings, int chestplate, int helmet, int body) {
        return Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
            type.put(ArmorItem.Type.BOOTS, boots);
            type.put(ArmorItem.Type.LEGGINGS, leggings);
            type.put(ArmorItem.Type.CHESTPLATE, chestplate);
            type.put(ArmorItem.Type.HELMET, helmet);
            type.put(ArmorItem.Type.BODY, body);
        });
    }

    public static int durabilityMultiplier(Holder<ArmorMaterial> armorMaterial) {
        return durabilityMultipliers.get(armorMaterial);
    }
}
