package de.thedon.oresandtools.datagen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.item.equipment.ModEquipmentAssets;
import de.thedon.oresandtools.render.ObsidianShieldSpecialRenderer;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.stream.Stream;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, OresAndToolsMod.MOD_ID);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        /* BLOCKS */
        blockModels.createTrivialCube(ModBlocks.HARDENED_DIAMOND_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.STEEL_BLOCK.get());

        /* ORES */
        blockModels.createTrivialCube(ModBlocks.ENDERITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_ENDERITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ENDSTONE_ENDERITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.OBSIDIAN_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_OBSIDIAN_ORE.get());
        blockModels.createTrivialCube(ModBlocks.XP_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_XP_ORE.get());
        blockModels.createTrivialCube(ModBlocks.URANIUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_URANIUM_ORE.get());

        /* MOLTEN BLOCKS */
        blockModels.createTrivialCube(ModBlocks.MOLTEN_COPPER_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MOLTEN_IRON_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MOLTEN_GOLD_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MOLTEN_URANIUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MOLTEN_STONE.get());
        blockModels.createTrivialCube(ModBlocks.MOLTEN_SAND.get());

        /* CHESTS */
        blockModels.createChest(ModBlocks.ENDERITE_CHEST.get(), ModBlocks.ENDERITE_CHEST.get(), ResourceLocation.withDefaultNamespace("enderite"), false);


        /* ITEMS */
        itemModels.generateFlatItem(ModItems.HARDENED_DIAMOND.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.HEATING_HARDENED_DIAMOND_1.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.HEATING_HARDENED_DIAMOND_2.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.HEATING_HARDENED_DIAMOND_3.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.HOT_HARDENED_DIAMOND.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ENDERITE_DUST.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ENDERITE_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.OBSIDIAN_SHARD.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.DRAGONS_APPLE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.STEEL_CHUNK.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.STEEL_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_URANIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.URANIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.IMPROVISED_REACTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ENDERITE_LEATHER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.SHULKER_BACKPACK.get(), ModelTemplates.FLAT_ITEM);

        /* TOOLS */
        itemModels.generateFlatItem(ModItems.STEEL_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.STEEL_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.STEEL_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.STEEL_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.STEEL_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ENDERITE_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ENDERITE_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ENDERITE_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ENDERITE_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.ENDERITE_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.HARDENED_DIAMOND_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.HARDENED_DIAMOND_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.HARDENED_DIAMOND_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.HARDENED_DIAMOND_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.HARDENED_DIAMOND_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.HOT_HARDENED_DIAMOND_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.HOT_HARDENED_DIAMOND_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.HOT_HARDENED_DIAMOND_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.HOT_HARDENED_DIAMOND_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.HOT_HARDENED_DIAMOND_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EMERALD_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.OBSIDIAN_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.OBSIDIAN_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.OBSIDIAN_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.OBSIDIAN_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.OBSIDIAN_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        /* ARMOR */
        itemModels.generateTrimmableItem(ModItems.STEEL_HELMET.get(), ModEquipmentAssets.STEEL, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ModItems.STEEL_CHESTPLATE.get(), ModEquipmentAssets.STEEL, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ModItems.STEEL_LEGGINGS.get(), ModEquipmentAssets.STEEL, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ModItems.STEEL_BOOTS.get(), ModEquipmentAssets.STEEL, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModels.generateTrimmableItem(ModItems.ENDERITE_HELMET.get(), ModEquipmentAssets.ENDERITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ModItems.ENDERITE_CHESTPLATE.get(), ModEquipmentAssets.ENDERITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ModItems.ENDERITE_LEGGINGS.get(), ModEquipmentAssets.ENDERITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ModItems.ENDERITE_BOOTS.get(), ModEquipmentAssets.ENDERITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModels.generateTrimmableItem(ModItems.HARDENED_DIAMOND_HELMET.get(), ModEquipmentAssets.H_DIAMOND, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ModItems.HARDENED_DIAMOND_CHESTPLATE.get(), ModEquipmentAssets.H_DIAMOND, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ModItems.HARDENED_DIAMOND_LEGGINGS.get(), ModEquipmentAssets.H_DIAMOND, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ModItems.HARDENED_DIAMOND_BOOTS.get(), ModEquipmentAssets.H_DIAMOND, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModels.generateTrimmableItem(ModItems.HOT_HARDENED_DIAMOND_HELMET.get(), ModEquipmentAssets.HOT_H_DIAMOND, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ModItems.HOT_HARDENED_DIAMOND_CHESTPLATE.get(), ModEquipmentAssets.HOT_H_DIAMOND, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ModItems.HOT_HARDENED_DIAMOND_LEGGINGS.get(), ModEquipmentAssets.HOT_H_DIAMOND, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ModItems.HOT_HARDENED_DIAMOND_BOOTS.get(), ModEquipmentAssets.HOT_H_DIAMOND, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModels.generateTrimmableItem(ModItems.EMERALD_HELMET.get(), ModEquipmentAssets.EMERALD, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ModItems.EMERALD_CHESTPLATE.get(), ModEquipmentAssets.EMERALD, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ModItems.EMERALD_LEGGINGS.get(), ModEquipmentAssets.EMERALD, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ModItems.EMERALD_BOOTS.get(), ModEquipmentAssets.EMERALD, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModels.generateTrimmableItem(ModItems.OBSIDIAN_HELMET.get(), ModEquipmentAssets.OBSIDIAN, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModels.generateTrimmableItem(ModItems.OBSIDIAN_CHESTPLATE.get(), ModEquipmentAssets.OBSIDIAN, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModels.generateTrimmableItem(ModItems.OBSIDIAN_LEGGINGS.get(), ModEquipmentAssets.OBSIDIAN, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModels.generateTrimmableItem(ModItems.OBSIDIAN_BOOTS.get(), ModEquipmentAssets.OBSIDIAN, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);

        /* BOWS */
        itemModels.createFlatItemModel(ModItems.ENDERITE_BOW.get(), ModelTemplates.BOW);
        itemModels.generateBow(ModItems.ENDERITE_BOW.get());

        /* SHIELDS */
        generateShield(itemModels, ModItems.OBSIDIAN_SHIELD.get());
    }

    @Override
    protected @NotNull Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream();
    }

    @Override
    protected @NotNull Stream<? extends Holder<Item>> getKnownItems() {
        return ModItems.ITEMS.getEntries().stream();
    }

    public void generateShield(ItemModelGenerators itemModels, Item shieldItem) {
        ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(shieldItem), new ObsidianShieldSpecialRenderer.Unbaked());
        ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(shieldItem, "_blocking"), new ObsidianShieldSpecialRenderer.Unbaked());
        itemModels.generateBooleanDispatch(shieldItem, ItemModelUtils.isUsingItem(), itemmodel$unbaked1, itemmodel$unbaked);
    }
}
