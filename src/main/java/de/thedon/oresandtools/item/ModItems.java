package de.thedon.oresandtools.item;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.item.custom.*;
import de.thedon.oresandtools.item.equipment.ModArmorMaterials;
import de.thedon.oresandtools.util.ModTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(OresAndToolsMod.MOD_ID);

    public static final DeferredItem<Item> STEEL_CHUNK = ITEMS.registerItem("steel_chunk", Item::new);
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.registerItem("steel_ingot", Item::new);
    public static final DeferredItem<Item> HARDENED_DIAMOND = ITEMS.registerItem("hardened_diamond", properties -> new ModItem(properties.fireResistant(), true));
    public static final DeferredItem<Item> HEATING_INGOT_1 = ITEMS.registerItem("heating_ingot_1",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> HEATING_INGOT_2 = ITEMS.registerItem("heating_ingot_2",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> HEATING_INGOT_3 = ITEMS.registerItem("heating_ingot_3",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> MOLTEN_INGOT = ITEMS.registerItem("molten_ingot", properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_SHARD = ITEMS.registerItem("obsidian_shard",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> DRAGONS_APPLE = ITEMS.registerItem("dragons_apple", properties -> new ModItem(properties.food(ModFoodProperties.DRAGONS_APPLE, ModFoodProperties.DRAGONS_APPLE_EFFECT), true));
    public static final DeferredItem<Item> ENDERITE_DUST = ITEMS.registerItem("enderite_dust",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> ENDERITE_INGOT = ITEMS.registerItem("enderite_ingot",properties -> new Item(properties.fireResistant()));
    public static final DeferredItem<Item> ENDERITE_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("enderite_upgrade_smithing_template", properties -> ModSmithingTemplateItem.createEnderiteUpgradeTemplate(properties.rarity(Rarity.RARE)));
    public static final DeferredItem<Item> RAW_URANIUM = ITEMS.registerItem("raw_uranium", properties -> new ModItem(properties, true));
    public static final DeferredItem<Item> URANIUM_INGOT = ITEMS.registerItem("uranium_ingot", properties -> new ModItem(properties, true));
    public static final DeferredItem<Item> IMPROVISED_REACTOR = ITEMS.registerItem("improvised_reactor", ImprovisedReactorItem::new);
    public static final DeferredItem<Item> ENDERITE_LEATHER = ITEMS.registerItem("enderite_leather", Item::new);
    public static final DeferredItem<Item> SHULKER_BACKPACK = ITEMS.registerItem("shulker_backpack", properties -> new BackpackItem(properties, false));

    /* TOOLS */
    public static final DeferredItem<Item> STEEL_SHOVEL = ITEMS.registerItem("steel_shovel", properties -> new ModShovelItem(ModToolMaterials.STEEL, properties));
    public static final DeferredItem<Item> STEEL_PICKAXE = ITEMS.registerItem("steel_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.STEEL, properties));
    public static final DeferredItem<Item> STEEL_AXE = ITEMS.registerItem("steel_axe", properties -> new ModAxeItem(ModToolMaterials.STEEL, 6f, -3.1f, properties));
    public static final DeferredItem<Item> STEEL_HOE = ITEMS.registerItem("steel_hoe", properties -> new ModHoeItem(ModToolMaterials.STEEL, -3, -1f, properties));
    public static final DeferredItem<Item> STEEL_SWORD = ITEMS.registerItem("steel_sword", properties -> new ModSwordItem(ModToolMaterials.STEEL, properties));
    public static final DeferredItem<Item> EMERALD_SHOVEL = ITEMS.registerItem("emerald_shovel", properties -> new ModShovelItem(ModToolMaterials.EMERALD, properties));
    public static final DeferredItem<Item> EMERALD_PICKAXE = ITEMS.registerItem("emerald_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.EMERALD, properties));
    public static final DeferredItem<Item> EMERALD_AXE = ITEMS.registerItem("emerald_axe", properties -> new ModAxeItem(ModToolMaterials.EMERALD, 5.5f, -3.1f, properties));
    public static final DeferredItem<Item> EMERALD_HOE = ITEMS.registerItem("emerald_hoe", properties -> new ModHoeItem(ModToolMaterials.EMERALD, -3, 0f, properties));
    public static final DeferredItem<Item> EMERALD_SWORD = ITEMS.registerItem("emerald_sword", properties -> new ModSwordItem(ModToolMaterials.EMERALD, properties));
    public static final DeferredItem<Item> MOLTEN_SHOVEL = ITEMS.registerItem("molten_shovel", properties -> new ModShovelItem(ModToolMaterials.MOLTEN, properties.fireResistant(), true));
    public static final DeferredItem<Item> MOLTEN_PICKAXE = ITEMS.registerItem("molten_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.MOLTEN, properties.fireResistant(), true));
    public static final DeferredItem<Item> MOLTEN_AXE = ITEMS.registerItem("molten_axe", properties -> new ModAxeItem(ModToolMaterials.MOLTEN, 6f, -3.1f, properties.fireResistant(), true));
    public static final DeferredItem<Item> MOLTEN_HOE = ITEMS.registerItem("molten_hoe", properties -> new ModHoeItem(ModToolMaterials.MOLTEN, -3, 1f, properties.fireResistant(), true));
    public static final DeferredItem<Item> MOLTEN_SWORD = ITEMS.registerItem("molten_sword", properties -> new ModSwordItem(ModToolMaterials.MOLTEN, properties.fireResistant(), true));
    public static final DeferredItem<Item> HARDENED_DIAMOND_SHOVEL = ITEMS.registerItem("hardened_diamond_shovel", properties -> new ModShovelItem(ModToolMaterials.H_DIAMOND, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_PICKAXE = ITEMS.registerItem("hardened_diamond_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.H_DIAMOND, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_AXE = ITEMS.registerItem("hardened_diamond_axe", properties -> new ModAxeItem(ModToolMaterials.H_DIAMOND, 6f, -3.1f, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_HOE = ITEMS.registerItem("hardened_diamond_hoe", properties -> new ModHoeItem(ModToolMaterials.H_DIAMOND, -3, 1f, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_SWORD = ITEMS.registerItem("hardened_diamond_sword", properties -> new ModSwordItem(ModToolMaterials.H_DIAMOND, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_SHOVEL = ITEMS.registerItem("obsidian_shovel", properties -> new ModShovelItem(ModToolMaterials.OBSIDIAN, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_PICKAXE = ITEMS.registerItem("obsidian_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.OBSIDIAN, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_AXE = ITEMS.registerItem("obsidian_axe", properties -> new ModAxeItem(ModToolMaterials.OBSIDIAN, 5f, -3.1f, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_HOE = ITEMS.registerItem("obsidian_hoe", properties -> new ModHoeItem(ModToolMaterials.OBSIDIAN, -3, -1f, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_SWORD = ITEMS.registerItem("obsidian_sword", properties -> new ModSwordItem(ModToolMaterials.OBSIDIAN, properties.fireResistant()));
    public static final DeferredItem<Item> ENDERITE_SHOVEL = ITEMS.registerItem("enderite_shovel", properties -> new ModShovelItem(ModToolMaterials.ENDERITE, properties.fireResistant()));
    public static final DeferredItem<Item> ENDERITE_PICKAXE = ITEMS.registerItem("enderite_pickaxe", properties -> new ModPickaxeItem(ModToolMaterials.ENDERITE, properties.fireResistant()));
    public static final DeferredItem<Item> ENDERITE_AXE = ITEMS.registerItem("enderite_axe", properties -> new ModAxeItem(ModToolMaterials.ENDERITE, 4f, -3.1f, properties.fireResistant()));
    public static final DeferredItem<Item> ENDERITE_HOE = ITEMS.registerItem("enderite_hoe", properties -> new ModHoeItem(ModToolMaterials.ENDERITE, -3, 1f, properties.fireResistant()));
    public static final DeferredItem<Item> ENDERITE_SWORD = ITEMS.registerItem("enderite_sword", properties -> new ModSwordItem(ModToolMaterials.ENDERITE, properties.fireResistant()));

    /* ARMOR */
    public static final DeferredItem<Item> STEEL_HELMET = ITEMS.registerItem("steel_helmet", properties -> new ModArmorItem(ModArmorMaterials.STEEL, ArmorType.HELMET, properties));
    public static final DeferredItem<Item> STEEL_CHESTPLATE = ITEMS.registerItem("steel_chestplate", properties -> new ModArmorItem(ModArmorMaterials.STEEL, ArmorType.CHESTPLATE, properties));
    public static final DeferredItem<Item> STEEL_LEGGINGS = ITEMS.registerItem("steel_leggings", properties -> new ModArmorItem(ModArmorMaterials.STEEL, ArmorType.LEGGINGS, properties));
    public static final DeferredItem<Item> STEEL_BOOTS = ITEMS.registerItem("steel_boots", properties -> new ModArmorItem(ModArmorMaterials.STEEL, ArmorType.BOOTS, properties));
    public static final DeferredItem<Item> EMERALD_HELMET = ITEMS.registerItem("emerald_helmet", properties -> new ModArmorItem(ModArmorMaterials.EMERALD, ArmorType.HELMET, properties));
    public static final DeferredItem<Item> EMERALD_CHESTPLATE = ITEMS.registerItem("emerald_chestplate", properties -> new ModArmorItem(ModArmorMaterials.EMERALD, ArmorType.CHESTPLATE, properties));
    public static final DeferredItem<Item> EMERALD_LEGGINGS = ITEMS.registerItem("emerald_leggings", properties -> new ModArmorItem(ModArmorMaterials.EMERALD, ArmorType.LEGGINGS, properties));
    public static final DeferredItem<Item> EMERALD_BOOTS = ITEMS.registerItem("emerald_boots", properties -> new ModArmorItem(ModArmorMaterials.EMERALD, ArmorType.BOOTS, properties));
    public static final DeferredItem<Item> MOLTEN_HELMET = ITEMS.registerItem("molten_helmet", properties -> new ModArmorItem(ModArmorMaterials.MOLTEN, ArmorType.HELMET, properties.fireResistant(), true));
    public static final DeferredItem<Item> MOLTEN_CHESTPLATE = ITEMS.registerItem("molten_chestplate", properties -> new ModArmorItem(ModArmorMaterials.MOLTEN, ArmorType.CHESTPLATE, properties.fireResistant(), true));
    public static final DeferredItem<Item> MOLTEN_LEGGINGS = ITEMS.registerItem("molten_leggings", properties -> new ModArmorItem(ModArmorMaterials.MOLTEN, ArmorType.LEGGINGS, properties.fireResistant(), true));
    public static final DeferredItem<Item> MOLTEN_BOOTS = ITEMS.registerItem("molten_boots", properties -> new ModArmorItem(ModArmorMaterials.MOLTEN, ArmorType.BOOTS, properties.fireResistant(), true));
    public static final DeferredItem<Item> HARDENED_DIAMOND_HELMET = ITEMS.registerItem("hardened_diamond_helmet", properties -> new ModArmorItem(ModArmorMaterials.H_DIAMOND, ArmorType.HELMET, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_CHESTPLATE = ITEMS.registerItem("hardened_diamond_chestplate", properties -> new ModArmorItem(ModArmorMaterials.H_DIAMOND, ArmorType.CHESTPLATE, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_LEGGINGS = ITEMS.registerItem("hardened_diamond_leggings", properties -> new ModArmorItem(ModArmorMaterials.H_DIAMOND, ArmorType.LEGGINGS, properties.fireResistant()));
    public static final DeferredItem<Item> HARDENED_DIAMOND_BOOTS = ITEMS.registerItem("hardened_diamond_boots", properties -> new ModArmorItem(ModArmorMaterials.H_DIAMOND, ArmorType.BOOTS, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_HELMET = ITEMS.registerItem("obsidian_helmet", properties -> new ModArmorItem(ModArmorMaterials.OBSIDIAN, ArmorType.HELMET, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_CHESTPLATE = ITEMS.registerItem("obsidian_chestplate", properties -> new ModArmorItem(ModArmorMaterials.OBSIDIAN, ArmorType.CHESTPLATE, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_LEGGINGS = ITEMS.registerItem("obsidian_leggings", properties -> new ModArmorItem(ModArmorMaterials.OBSIDIAN, ArmorType.LEGGINGS, properties.fireResistant()));
    public static final DeferredItem<Item> OBSIDIAN_BOOTS = ITEMS.registerItem("obsidian_boots", properties -> new ModArmorItem(ModArmorMaterials.OBSIDIAN, ArmorType.BOOTS, properties.fireResistant()));
    public static final DeferredItem<Item> ENDERITE_HELMET = ITEMS.registerItem("enderite_helmet", properties -> new ModArmorItem(ModArmorMaterials.ENDERITE, ArmorType.HELMET, properties.fireResistant(), true));
    public static final DeferredItem<Item> ENDERITE_CHESTPLATE = ITEMS.registerItem("enderite_chestplate", properties -> new ModArmorItem(ModArmorMaterials.ENDERITE, ArmorType.CHESTPLATE, properties.fireResistant(), true));
    public static final DeferredItem<Item> ENDERITE_LEGGINGS = ITEMS.registerItem("enderite_leggings", properties -> new ModArmorItem(ModArmorMaterials.ENDERITE, ArmorType.LEGGINGS, properties.fireResistant(), true));
    public static final DeferredItem<Item> ENDERITE_BOOTS = ITEMS.registerItem("enderite_boots", properties -> new ModArmorItem(ModArmorMaterials.ENDERITE, ArmorType.BOOTS, properties.fireResistant(), true));

    /* BOWS */
    public static final DeferredItem<Item> ENDERITE_BOW = ITEMS.registerItem("enderite_bow", properties -> new ModBowItem(ENDERITE_INGOT.get(), 1.1f, 60000, 20, properties.durability(3225)));

    /* SHIELDS */
    public static final DeferredItem<Item> OBSIDIAN_SHIELD = ITEMS.registerItem("obsidian_shield", properties -> new ModShieldItem(1250, ModTags.Items.OBSIDIAN_REPAIR_MATERIALS, properties.fireResistant()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
