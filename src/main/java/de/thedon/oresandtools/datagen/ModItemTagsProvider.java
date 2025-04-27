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
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, lookupCompletableFuture, OresAndToolsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider pProvider) {
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
    }
}
