package de.thedon.oresandtools.item;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.item.custom.*;
import de.thedon.oresandtools.item.equipment.ModArmorMaterials;
import de.thedon.oresandtools.util.ModTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(OresAndToolsMod.MOD_ID);

    public static final DeferredItem<Item> HARDENED_DIAMOND = ITEMS.registerItem("hardened_diamond", properties -> new ModItem(properties.fireResistant(), true));
    public static final DeferredItem<Item> HEATING_HARDENED_DIAMOND_1 = ITEMS.registerItem("heating_hardened_diamond_1",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> HEATING_HARDENED_DIAMOND_2 = ITEMS.registerItem("heating_hardened_diamond_2",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> HEATING_HARDENED_DIAMOND_3 = ITEMS.registerItem("heating_hardened_diamond_3",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> HOT_HARDENED_DIAMOND = ITEMS.registerItem("hot_hardened_diamond",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> VALYRIAN_DUST = ITEMS.registerItem("valyrian_dust",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_SHARD = ITEMS.registerItem("obsidian_shard",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> DRAGONS_APPLE = ITEMS.registerItem("dragons_apple", properties -> new ModItem(properties.food(ModFoodProperties.DRAGONS_APPLE, ModFoodProperties.DRAGONS_APPLE_EFFECT), true));
    public static final DeferredItem<Item> STEEL_CHUNK = ITEMS.registerItem("steel_chunk", Item::new);
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.registerItem("steel_ingot", Item::new);
    public static final DeferredItem<Item> VALYRIAN_INGOT = ITEMS.registerItem("valyrian_ingot",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> RAW_URANIUM = ITEMS.registerItem("raw_uranium", properties -> new ModItem(properties, true));
    public static final DeferredItem<Item> URANIUM_INGOT = ITEMS.registerItem("uranium_ingot", properties -> new ModItem(properties, true));
    public static final DeferredItem<Item> IMPROVISED_REACTOR = ITEMS.registerItem("improvised_reactor", ImprovisedReactorItem::new);
    public static final DeferredItem<Item> VALYRIAN_LEATHER = ITEMS.registerItem("valyrian_leather", Item::new);
    public static final DeferredItem<Item> SHULKER_BACKPACK = ITEMS.registerItem("shulker_backpack", properties -> new BackpackItem(properties, false));

    /* TOOLS */
    public static final DeferredItem<Item> COPPER_SHOVEL = ITEMS.registerItem("copper_shovel", properties -> new ModShovelItem(ModToolMaterials.COPPER, properties));
    public static final DeferredItem<Item> COPPER_PICKAXE = ITEMS.registerItem("copper_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.COPPER, properties));
    public static final DeferredItem<Item> COPPER_AXE = ITEMS.registerItem("copper_axe", properties -> new ModAxeItem(ModToolMaterials.COPPER, 6f, -3.1f, properties));
    public static final DeferredItem<Item> COPPER_HOE = ITEMS.registerItem("copper_hoe", properties -> new ModHoeItem(ModToolMaterials.COPPER, -3, -1f, properties));
    public static final DeferredItem<Item> COPPER_SWORD = ITEMS.registerItem("copper_sword", properties -> new ModSwordItem(ModToolMaterials.COPPER, properties));
    public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.registerItem("steel_shovel", properties -> new ModShovelItem(ModToolMaterials.STEEL, properties));
    public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.registerItem("steel_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.STEEL, properties));
    public static final DeferredItem<Item> STEEL_AXE = ITEMS.registerItem("steel_axe", properties -> new ModAxeItem(ModToolMaterials.STEEL, 6f, -3.1f, properties));
    public static final DeferredItem<Item> STEEL_HOE = ITEMS.registerItem("steel_hoe", properties -> new ModHoeItem(ModToolMaterials.STEEL, -3, -1f, properties));
    public static final DeferredItem<Item> STEEL_SWORD = ITEMS.registerItem("steel_sword", properties -> new ModSwordItem(ModToolMaterials.STEEL, properties));
    public static final DeferredItem<Item> VALYRIAN_SHOVEL = ITEMS.registerItem("valyrian_shovel", properties -> new ModShovelItem(ModToolMaterials.VALYRIAN, properties.fireResistant()));
    public static final DeferredItem<Item> VALYRIAN_PICKAXE = ITEMS.registerItem("valyrian_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.VALYRIAN, properties.fireResistant()));
    public static final DeferredItem<Item> VALYRIAN_AXE = ITEMS.registerItem("valyrian_axe", properties -> new ModAxeItem(ModToolMaterials.VALYRIAN, 4f, -3.1f, properties.fireResistant()));
    public static final DeferredItem<Item> VALYRIAN_HOE = ITEMS.registerItem("valyrian_hoe", properties -> new ModHoeItem(ModToolMaterials.VALYRIAN, -3, 1f, properties.fireResistant()));
    public static final DeferredItem<Item> VALYRIAN_SWORD = ITEMS.registerItem("valyrian_sword", properties -> new ModSwordItem(ModToolMaterials.VALYRIAN, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_SHOVEL = ITEMS.registerItem("hardened_diamond_shovel", properties -> new ModShovelItem(ModToolMaterials.HARDENED_DIAMOND, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_PICKAXE = ITEMS.registerItem("hardened_diamond_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.HARDENED_DIAMOND, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_AXE = ITEMS.registerItem("hardened_diamond_axe", properties -> new ModAxeItem(ModToolMaterials.HARDENED_DIAMOND, 6f, -3.1f, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_HOE = ITEMS.registerItem("hardened_diamond_hoe", properties -> new ModHoeItem(ModToolMaterials.HARDENED_DIAMOND, -3, 1f, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_SWORD = ITEMS.registerItem("hardened_diamond_sword", properties -> new ModSwordItem(ModToolMaterials.HARDENED_DIAMOND, properties.fireResistant()));
    public static final DeferredItem<Item> HOT_HARDENED_DIAMOND_SHOVEL = ITEMS.registerItem("hot_hardened_diamond_shovel", properties -> new ModShovelItem(ModToolMaterials.HOT_HARDENED_DIAMOND, properties.fireResistant(), true));
    public static final DeferredItem<Item> HOT_HARDENED_DIAMOND_PICKAXE = ITEMS.registerItem("hot_hardened_diamond_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.HOT_HARDENED_DIAMOND, properties.fireResistant(), true));
    public static final DeferredItem<Item> HOT_HARDENED_DIAMOND_AXE = ITEMS.registerItem("hot_hardened_diamond_axe", properties -> new ModAxeItem(ModToolMaterials.HOT_HARDENED_DIAMOND, 6f, -3.1f, properties.fireResistant(), true));
    public static final DeferredItem<Item> HOT_HARDENED_DIAMOND_HOE = ITEMS.registerItem("hot_hardened_diamond_hoe", properties -> new ModHoeItem(ModToolMaterials.HOT_HARDENED_DIAMOND, -3, 1f, properties.fireResistant(), true));
    public static final DeferredItem<Item> HOT_HARDENED_DIAMOND_SWORD = ITEMS.registerItem("hot_hardened_diamond_sword", properties -> new ModSwordItem(ModToolMaterials.HOT_HARDENED_DIAMOND, properties.fireResistant(), true));
    public static final DeferredItem<Item> EMERALD_SHOVEL = ITEMS.registerItem("emerald_shovel", properties -> new ModShovelItem(ModToolMaterials.EMERALD, properties));
    public static final DeferredItem<Item> EMERALD_PICKAXE = ITEMS.registerItem("emerald_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.EMERALD, properties));
    public static final DeferredItem<Item> EMERALD_AXE = ITEMS.registerItem("emerald_axe", properties -> new ModAxeItem(ModToolMaterials.EMERALD, 5.5f, -3.1f, properties));
    public static final DeferredItem<Item> EMERALD_HOE = ITEMS.registerItem("emerald_hoe", properties -> new ModHoeItem(ModToolMaterials.EMERALD, -3, 0f, properties));
    public static final DeferredItem<Item> EMERALD_SWORD = ITEMS.registerItem("emerald_sword", properties -> new ModSwordItem(ModToolMaterials.EMERALD, properties));
    public static final DeferredItem<Item> OBSIDIAN_SHOVEL = ITEMS.registerItem("obsidian_shovel", properties -> new ModShovelItem(ModToolMaterials.OBSIDIAN, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_PICKAXE = ITEMS.registerItem("obsidian_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.OBSIDIAN, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_AXE = ITEMS.registerItem("obsidian_axe", properties -> new ModAxeItem(ModToolMaterials.OBSIDIAN, 5f, -3.1f, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_HOE = ITEMS.registerItem("obsidian_hoe", properties -> new ModHoeItem(ModToolMaterials.OBSIDIAN, -3, -1f, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_SWORD = ITEMS.registerItem("obsidian_sword", properties -> new ModSwordItem(ModToolMaterials.OBSIDIAN, properties.fireResistant()));

    /* ARMOR */
    public static final DeferredItem<Item> COPPER_HELMET = ITEMS.registerItem("copper_helmet", properties -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorType.HELMET, properties));
    public static final DeferredItem<Item> COPPER_CHESTPLATE = ITEMS.registerItem("copper_chestplate", properties -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorType.CHESTPLATE, properties));
    public static final DeferredItem<Item> COPPER_LEGGINGS = ITEMS.registerItem("copper_leggings", properties -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorType.LEGGINGS, properties));
    public static final DeferredItem<Item> COPPER_BOOTS = ITEMS.registerItem("copper_boots", properties -> new ModArmorItem(ModArmorMaterials.COPPER, ArmorType.BOOTS, properties));
    public static final DeferredItem<Item> STEEL_HELMET = ITEMS.registerItem("steel_helmet", properties -> new ModArmorItem(ModArmorMaterials.STEEL, ArmorType.HELMET, properties));
    public static final DeferredItem<Item> STEEL_CHESTPLATE = ITEMS.registerItem("steel_chestplate", properties -> new ModArmorItem(ModArmorMaterials.STEEL, ArmorType.CHESTPLATE, properties));
    public static final DeferredItem<Item> STEEL_LEGGINGS = ITEMS.registerItem("steel_leggings", properties -> new ModArmorItem(ModArmorMaterials.STEEL, ArmorType.LEGGINGS, properties));
    public static final DeferredItem<Item> STEEL_BOOTS = ITEMS.registerItem("steel_boots", properties -> new ModArmorItem(ModArmorMaterials.STEEL, ArmorType.BOOTS, properties));
    public static final DeferredItem<Item> VALYRIAN_HELMET = ITEMS.registerItem("valyrian_helmet", properties -> new ModArmorItem(ModArmorMaterials.VALYRIAN, ArmorType.HELMET, properties.fireResistant(), true));
    public static final DeferredItem<Item> VALYRIAN_CHESTPLATE = ITEMS.registerItem("valyrian_chestplate", properties -> new ModArmorItem(ModArmorMaterials.VALYRIAN, ArmorType.CHESTPLATE, properties.fireResistant(), true));
    public static final DeferredItem<Item> VALYRIAN_LEGGINGS = ITEMS.registerItem("valyrian_leggings", properties -> new ModArmorItem(ModArmorMaterials.VALYRIAN, ArmorType.LEGGINGS, properties.fireResistant(), true));
    public static final DeferredItem<Item> VALYRIAN_BOOTS = ITEMS.registerItem("valyrian_boots", properties -> new ModArmorItem(ModArmorMaterials.VALYRIAN, ArmorType.BOOTS, properties.fireResistant(), true));
    public static final DeferredItem<Item> HARDENED_DIAMOND_HELMET = ITEMS.registerItem("hardened_diamond_helmet", properties -> new ModArmorItem(ModArmorMaterials.H_DIAMOND, ArmorType.HELMET, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_CHESTPLATE = ITEMS.registerItem("hardened_diamond_chestplate", properties -> new ModArmorItem(ModArmorMaterials.H_DIAMOND, ArmorType.CHESTPLATE, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_LEGGINGS = ITEMS.registerItem("hardened_diamond_leggings", properties -> new ModArmorItem(ModArmorMaterials.H_DIAMOND, ArmorType.LEGGINGS, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_BOOTS = ITEMS.registerItem("hardened_diamond_boots", properties -> new ModArmorItem(ModArmorMaterials.H_DIAMOND, ArmorType.BOOTS, properties.fireResistant()));
    public static final DeferredItem<Item> HOT_HARDENED_DIAMOND_HELMET = ITEMS.registerItem("hot_hardened_diamond_helmet", properties -> new ModArmorItem(ModArmorMaterials.HOT_H_DIAMOND, ArmorType.HELMET, properties.fireResistant(), true));
    public static final DeferredItem<Item> HOT_HARDENED_DIAMOND_CHESTPLATE = ITEMS.registerItem("hot_hardened_diamond_chestplate", properties -> new ModArmorItem(ModArmorMaterials.HOT_H_DIAMOND, ArmorType.CHESTPLATE, properties.fireResistant(), true));
    public static final DeferredItem<Item> HOT_HARDENED_DIAMOND_LEGGINGS = ITEMS.registerItem("hot_hardened_diamond_leggings", properties -> new ModArmorItem(ModArmorMaterials.HOT_H_DIAMOND, ArmorType.LEGGINGS, properties.fireResistant(), true));
    public static final DeferredItem<Item> HOT_HARDENED_DIAMOND_BOOTS = ITEMS.registerItem("hot_hardened_diamond_boots", properties -> new ModArmorItem(ModArmorMaterials.HOT_H_DIAMOND, ArmorType.BOOTS, properties.fireResistant(), true));
    public static final DeferredItem<Item> EMERALD_HELMET = ITEMS.registerItem("emerald_helmet", properties -> new ModArmorItem(ModArmorMaterials.EMERALD, ArmorType.HELMET, properties));
    public static final DeferredItem<Item> EMERALD_CHESTPLATE = ITEMS.registerItem("emerald_chestplate", properties -> new ModArmorItem(ModArmorMaterials.EMERALD, ArmorType.CHESTPLATE, properties));
    public static final DeferredItem<Item> EMERALD_LEGGINGS = ITEMS.registerItem("emerald_leggings", properties -> new ModArmorItem(ModArmorMaterials.EMERALD, ArmorType.LEGGINGS, properties));
    public static final DeferredItem<Item> EMERALD_BOOTS = ITEMS.registerItem("emerald_boots", properties -> new ModArmorItem(ModArmorMaterials.EMERALD, ArmorType.BOOTS, properties));
    public static final DeferredItem<Item> OBSIDIAN_HELMET = ITEMS.registerItem("obsidian_helmet", properties -> new ModArmorItem(ModArmorMaterials.OBSIDIAN, ArmorType.HELMET, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_CHESTPLATE = ITEMS.registerItem("obsidian_chestplate", properties -> new ModArmorItem(ModArmorMaterials.OBSIDIAN, ArmorType.CHESTPLATE, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_LEGGINGS = ITEMS.registerItem("obsidian_leggings", properties -> new ModArmorItem(ModArmorMaterials.OBSIDIAN, ArmorType.LEGGINGS, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_BOOTS = ITEMS.registerItem("obsidian_boots", properties -> new ModArmorItem(ModArmorMaterials.OBSIDIAN, ArmorType.BOOTS, properties.fireResistant()));

    /* BOWS */
    public static final DeferredItem<Item> VALYRIAN_BOW = ITEMS.registerItem("valyrian_bow", properties -> new ModBowItem(VALYRIAN_INGOT.get(), 1.1f, 60000, 20, properties.durability(3225)));

    /* SHIELDS */
    public static final DeferredItem<Item> OBSIDIAN_SHIELD = ITEMS.registerItem("obsidian_shield", properties -> new ModShieldItem(1250, ModTags.Items.OBSIDIAN_REPAIRABLE, properties.fireResistant()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
