package de.thedon.oresandtools.datagen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> lookupCompletableFuture) {
        super(packOutput, lookupProvider, lookupCompletableFuture, OresAndToolsMod.MOD_ID);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(ModTags.Items.DRAGON_APPLE_MATERIALS)
                .add(ModItems.VALYRIAN_DUST.get())
                .add(ModItems.OBSIDIAN_SHARD.get())
                .add(Items.APPLE);

        tag(ModTags.Items.HARDENED_DIAMOND_MATERIALS)
                .add(Items.OBSIDIAN)
                .add(Items.DIAMOND);

        tag(ModTags.Items.IMPROVISED_REACTOR_MATERIALS)
                .add(Items.COAL)
                .add(Items.CHARCOAL)
                .add(Items.WATER_BUCKET)
                .add(ModItems.URANIUM_INGOT.get());

        tag(ModTags.Items.OBSIDIAN_SHIELD_MATERIALS)
                .add(Items.OBSIDIAN)
                .add(Items.DIAMOND);

        tag(ModTags.Items.STEEL_CHUNK_MATERIALS)
                .add(Items.COAL)
                .add(Items.CHARCOAL)
                .add(Items.RAW_IRON)
                .add(Items.IRON_NUGGET);

        tag(ModTags.Items.VALYRIAN_INGOT_MATERIALS)
                .add(ModItems.VALYRIAN_DUST.get())
                .add(ModItems.STEEL_INGOT.get());

        tag(ModTags.Items.VALYRIAN_LEATHER_MATERIALS)
                .add(ModItems.VALYRIAN_DUST.get())
                .add(Items.LEATHER);

        tag(ModTags.Items.SHULKER_BACKPACK_MATERIALS)
                .add(ModItems.VALYRIAN_LEATHER.get())
                .add(ModItems.VALYRIAN_INGOT.get())
                .add(Items.SHULKER_BOX)
                .add(Items.ENDER_EYE);

        tag(ModTags.Items.VALYRIAN_CHEST_MATERIALS)
                .add(ModItems.VALYRIAN_INGOT.get())
                .add(Items.CHEST);

        tag(ModTags.Items.IGNITION_ITEMS)
                .add(ModItems.HOT_HARDENED_DIAMOND.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_AXE.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_HOE.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_PICKAXE.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_SHOVEL.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_SWORD.get());

        tag(ModTags.Items.VALYRIAN_ARMOR_SET)
                .add(ModItems.VALYRIAN_HELMET.get())
                .add(ModItems.VALYRIAN_CHESTPLATE.get())
                .add(ModItems.VALYRIAN_LEGGINGS.get())
                .add(ModItems.VALYRIAN_BOOTS.get());

        tag(ModTags.Items.HOT_HARDENED_DIAMOND_ARMOR_SET)
                .add(ModItems.HOT_HARDENED_DIAMOND_HELMET.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_CHESTPLATE.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_LEGGINGS.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_BOOTS.get());

        tag(ModTags.Items.COPPER_REPAIRABLE)
                .add(Items.COPPER_INGOT);

        tag(ModTags.Items.STEEL_REPAIRABLE)
                .add(ModItems.STEEL_INGOT.get());

        tag(ModTags.Items.VALYRIAN_REPAIRABLE)
                .add(ModItems.VALYRIAN_INGOT.get());

        tag(ModTags.Items.HARDENED_DIAMOND_REPAIRABLE)
                .add(ModItems.HARDENED_DIAMOND.get());

        tag(ModTags.Items.HOT_HARDENED_DIAMOND_REPAIRABLE)
                .add(ModItems.HOT_HARDENED_DIAMOND.get());

        tag(ModTags.Items.EMERALD_REPAIRABLE)
                .add(Items.EMERALD);

        tag(ModTags.Items.OBSIDIAN_REPAIRABLE)
                .add(Items.OBSIDIAN);

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.COPPER_HELMET.get())
                .add(ModItems.COPPER_CHESTPLATE.get())
                .add(ModItems.COPPER_LEGGINGS.get())
                .add(ModItems.COPPER_BOOTS.get())
                .add(ModItems.STEEL_HELMET.get())
                .add(ModItems.STEEL_CHESTPLATE.get())
                .add(ModItems.STEEL_LEGGINGS.get())
                .add(ModItems.STEEL_BOOTS.get())
                .add(ModItems.VALYRIAN_HELMET.get())
                .add(ModItems.VALYRIAN_CHESTPLATE.get())
                .add(ModItems.VALYRIAN_LEGGINGS.get())
                .add(ModItems.VALYRIAN_BOOTS.get())
                .add(ModItems.HARDENED_DIAMOND_HELMET.get())
                .add(ModItems.HARDENED_DIAMOND_CHESTPLATE.get())
                .add(ModItems.HARDENED_DIAMOND_LEGGINGS.get())
                .add(ModItems.HARDENED_DIAMOND_BOOTS.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_HELMET.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_CHESTPLATE.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_LEGGINGS.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_BOOTS.get())
                .add(ModItems.EMERALD_HELMET.get())
                .add(ModItems.EMERALD_CHESTPLATE.get())
                .add(ModItems.EMERALD_LEGGINGS.get())
                .add(ModItems.EMERALD_BOOTS.get())
                .add(ModItems.OBSIDIAN_HELMET.get())
                .add(ModItems.OBSIDIAN_CHESTPLATE.get())
                .add(ModItems.OBSIDIAN_LEGGINGS.get())
                .add(ModItems.OBSIDIAN_BOOTS.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.COPPER_SHOVEL.get())
                .add(ModItems.STEEL_SHOVEL.get())
                .add(ModItems.HARDENED_DIAMOND_SHOVEL.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_SHOVEL.get())
                .add(ModItems.VALYRIAN_SHOVEL.get())
                .add(ModItems.EMERALD_SHOVEL.get())
                .add(ModItems.OBSIDIAN_SHOVEL.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.COPPER_PICKAXE.get())
                .add(ModItems.STEEL_PICKAXE.get())
                .add(ModItems.HARDENED_DIAMOND_PICKAXE.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_PICKAXE.get())
                .add(ModItems.VALYRIAN_PICKAXE.get())
                .add(ModItems.EMERALD_PICKAXE.get())
                .add(ModItems.OBSIDIAN_PICKAXE.get());

        tag(ItemTags.AXES)
                .add(ModItems.COPPER_AXE.get())
                .add(ModItems.STEEL_AXE.get())
                .add(ModItems.HARDENED_DIAMOND_AXE.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_AXE.get())
                .add(ModItems.VALYRIAN_AXE.get())
                .add(ModItems.EMERALD_AXE.get())
                .add(ModItems.OBSIDIAN_AXE.get());

        tag(ItemTags.HOES)
                .add(ModItems.COPPER_HOE.get())
                .add(ModItems.STEEL_HOE.get())
                .add(ModItems.HARDENED_DIAMOND_HOE.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_HOE.get())
                .add(ModItems.VALYRIAN_HOE.get())
                .add(ModItems.EMERALD_HOE.get())
                .add(ModItems.OBSIDIAN_HOE.get());

        tag(ItemTags.SWORDS)
                .add(ModItems.COPPER_SWORD.get())
                .add(ModItems.STEEL_SWORD.get())
                .add(ModItems.HARDENED_DIAMOND_SWORD.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_SWORD.get())
                .add(ModItems.VALYRIAN_SWORD.get())
                .add(ModItems.EMERALD_SWORD.get())
                .add(ModItems.OBSIDIAN_SWORD.get());

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.COPPER_HELMET.get())
                .add(ModItems.STEEL_HELMET.get())
                .add(ModItems.HARDENED_DIAMOND_HELMET.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_HELMET.get())
                .add(ModItems.VALYRIAN_HELMET.get())
                .add(ModItems.EMERALD_HELMET.get())
                .add(ModItems.OBSIDIAN_HELMET.get());

        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.COPPER_CHESTPLATE.get())
                .add(ModItems.STEEL_CHESTPLATE.get())
                .add(ModItems.HARDENED_DIAMOND_CHESTPLATE.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_CHESTPLATE.get())
                .add(ModItems.VALYRIAN_CHESTPLATE.get())
                .add(ModItems.EMERALD_CHESTPLATE.get())
                .add(ModItems.OBSIDIAN_CHESTPLATE.get());

        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.COPPER_LEGGINGS.get())
                .add(ModItems.STEEL_LEGGINGS.get())
                .add(ModItems.HARDENED_DIAMOND_LEGGINGS.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_LEGGINGS.get())
                .add(ModItems.VALYRIAN_LEGGINGS.get())
                .add(ModItems.EMERALD_LEGGINGS.get())
                .add(ModItems.OBSIDIAN_LEGGINGS.get());

        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.COPPER_BOOTS.get())
                .add(ModItems.STEEL_BOOTS.get())
                .add(ModItems.HARDENED_DIAMOND_BOOTS.get())
                .add(ModItems.HOT_HARDENED_DIAMOND_BOOTS.get())
                .add(ModItems.VALYRIAN_BOOTS.get())
                .add(ModItems.EMERALD_BOOTS.get())
                .add(ModItems.OBSIDIAN_BOOTS.get());
    }

}
