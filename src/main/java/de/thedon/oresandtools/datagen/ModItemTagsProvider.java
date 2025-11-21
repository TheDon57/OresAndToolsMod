package de.thedon.oresandtools.datagen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider, OresAndToolsMod.MOD_ID);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(ModTags.Items.DRAGON_APPLE_MATERIALS)
                .add(ModItems.ENDERITE_DUST.get())
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
                .add(Items.NETHERITE_INGOT);

        tag(ModTags.Items.STEEL_CHUNK_MATERIALS)
                .add(Items.COAL)
                .add(Items.CHARCOAL)
                .add(Items.RAW_IRON)
                .add(Items.IRON_NUGGET);

        tag(ModTags.Items.ENDERITE_INGOT_MATERIALS)
                .add(ModItems.ENDERITE_DUST.get())
                .add(ModItems.STEEL_INGOT.get());

        tag(ModTags.Items.ENDERITE_LEATHER_MATERIALS)
                .add(ModItems.ENDERITE_DUST.get())
                .add(Items.LEATHER);

        tag(ModTags.Items.SHULKER_BACKPACK_MATERIALS)
                .add(ModItems.ENDERITE_LEATHER.get())
                .add(ModItems.ENDERITE_INGOT.get())
                .add(Items.SHULKER_BOX)
                .add(Items.ENDER_EYE);

        tag(ModTags.Items.ENDERITE_CHEST_MATERIALS)
                .add(ModItems.STEEL_INGOT.get())
                .add(ModItems.ENDERITE_INGOT.get())
                .add(Items.CHEST);

        tag(ModTags.Items.ENDERITE_CHEST_UPGRADE_MATERIALS)
                .addTag(ModTags.Items.ENDERITE_CHEST_MATERIALS)
                .remove(Items.CHEST);

        tag(ModTags.Items.IGNITION_ITEMS)
                .add(ModItems.MOLTEN_INGOT.get())
                .add(ModItems.MOLTEN_AXE.get())
                .add(ModItems.MOLTEN_HOE.get())
                .add(ModItems.MOLTEN_PICKAXE.get())
                .add(ModItems.MOLTEN_SHOVEL.get())
                .add(ModItems.MOLTEN_SWORD.get());

        tag(ModTags.Items.MOLTEN_ARMOR_SET)
                .add(ModItems.MOLTEN_HELMET.get())
                .add(ModItems.MOLTEN_CHESTPLATE.get())
                .add(ModItems.MOLTEN_LEGGINGS.get())
                .add(ModItems.MOLTEN_BOOTS.get());

        tag(ModTags.Items.ENDERITE_ARMOR_SET)
                .add(ModItems.ENDERITE_HELMET.get())
                .add(ModItems.ENDERITE_CHESTPLATE.get())
                .add(ModItems.ENDERITE_LEGGINGS.get())
                .add(ModItems.ENDERITE_BOOTS.get());

        tag(ModTags.Items.STEEL_TOOL_MATERIALS)
                .add(ModItems.STEEL_INGOT.get());

        tag(ModTags.Items.EMERALD_TOOL_MATERIALS)
                .add(Items.EMERALD);

        tag(ModTags.Items.MOLTEN_TOOL_MATERIALS)
                .add(ModItems.MOLTEN_INGOT.get());

        tag(ModTags.Items.H_DIAMOND_TOOL_MATERIALS)
                .add(ModItems.HARDENED_DIAMOND.get());

        tag(ModTags.Items.OBSIDIAN_TOOL_MATERIALS)
                .add(Items.OBSIDIAN);

        tag(ModTags.Items.ENDERITE_TOOL_MATERIALS)
                .add(ModItems.ENDERITE_INGOT.get());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.STEEL_HELMET.get())
                .add(ModItems.STEEL_CHESTPLATE.get())
                .add(ModItems.STEEL_LEGGINGS.get())
                .add(ModItems.STEEL_BOOTS.get())
                .add(ModItems.EMERALD_HELMET.get())
                .add(ModItems.EMERALD_CHESTPLATE.get())
                .add(ModItems.EMERALD_LEGGINGS.get())
                .add(ModItems.EMERALD_BOOTS.get())
                .add(ModItems.MOLTEN_HELMET.get())
                .add(ModItems.MOLTEN_CHESTPLATE.get())
                .add(ModItems.MOLTEN_LEGGINGS.get())
                .add(ModItems.MOLTEN_BOOTS.get())
                .add(ModItems.HARDENED_DIAMOND_HELMET.get())
                .add(ModItems.HARDENED_DIAMOND_CHESTPLATE.get())
                .add(ModItems.HARDENED_DIAMOND_LEGGINGS.get())
                .add(ModItems.HARDENED_DIAMOND_BOOTS.get())
                .add(ModItems.OBSIDIAN_HELMET.get())
                .add(ModItems.OBSIDIAN_CHESTPLATE.get())
                .add(ModItems.OBSIDIAN_LEGGINGS.get())
                .add(ModItems.OBSIDIAN_BOOTS.get())
                .add(ModItems.ENDERITE_HELMET.get())
                .add(ModItems.ENDERITE_CHESTPLATE.get())
                .add(ModItems.ENDERITE_LEGGINGS.get())
                .add(ModItems.ENDERITE_BOOTS.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.STEEL_SHOVEL.get())
                .add(ModItems.EMERALD_SHOVEL.get())
                .add(ModItems.MOLTEN_SHOVEL.get())
                .add(ModItems.HARDENED_DIAMOND_SHOVEL.get())
                .add(ModItems.OBSIDIAN_SHOVEL.get())
                .add(ModItems.ENDERITE_SHOVEL.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.STEEL_PICKAXE.get())
                .add(ModItems.EMERALD_PICKAXE.get())
                .add(ModItems.MOLTEN_PICKAXE.get())
                .add(ModItems.HARDENED_DIAMOND_PICKAXE.get())
                .add(ModItems.OBSIDIAN_PICKAXE.get())
                .add(ModItems.ENDERITE_PICKAXE.get());

        tag(ItemTags.AXES)
                .add(ModItems.STEEL_AXE.get())
                .add(ModItems.EMERALD_AXE.get())
                .add(ModItems.MOLTEN_AXE.get())
                .add(ModItems.HARDENED_DIAMOND_AXE.get())
                .add(ModItems.OBSIDIAN_AXE.get())
                .add(ModItems.ENDERITE_AXE.get());

        tag(ItemTags.HOES)
                .add(ModItems.STEEL_HOE.get())
                .add(ModItems.EMERALD_HOE.get())
                .add(ModItems.MOLTEN_HOE.get())
                .add(ModItems.HARDENED_DIAMOND_HOE.get())
                .add(ModItems.OBSIDIAN_HOE.get())
                .add(ModItems.ENDERITE_HOE.get());

        tag(ItemTags.SWORDS)
                .add(ModItems.STEEL_SWORD.get())
                .add(ModItems.EMERALD_SWORD.get())
                .add(ModItems.MOLTEN_SWORD.get())
                .add(ModItems.HARDENED_DIAMOND_SWORD.get())
                .add(ModItems.OBSIDIAN_SWORD.get())
                .add(ModItems.ENDERITE_SWORD.get());

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.STEEL_HELMET.get())
                .add(ModItems.EMERALD_HELMET.get())
                .add(ModItems.MOLTEN_HELMET.get())
                .add(ModItems.HARDENED_DIAMOND_HELMET.get())
                .add(ModItems.OBSIDIAN_HELMET.get())
                .add(ModItems.ENDERITE_HELMET.get());

        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.STEEL_CHESTPLATE.get())
                .add(ModItems.EMERALD_CHESTPLATE.get())
                .add(ModItems.MOLTEN_CHESTPLATE.get())
                .add(ModItems.HARDENED_DIAMOND_CHESTPLATE.get())
                .add(ModItems.OBSIDIAN_CHESTPLATE.get())
                .add(ModItems.ENDERITE_CHESTPLATE.get());

        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.STEEL_LEGGINGS.get())
                .add(ModItems.EMERALD_LEGGINGS.get())
                .add(ModItems.MOLTEN_LEGGINGS.get())
                .add(ModItems.HARDENED_DIAMOND_LEGGINGS.get())
                .add(ModItems.OBSIDIAN_LEGGINGS.get())
                .add(ModItems.ENDERITE_LEGGINGS.get());

        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.STEEL_BOOTS.get())
                .add(ModItems.EMERALD_BOOTS.get())
                .add(ModItems.MOLTEN_BOOTS.get())
                .add(ModItems.HARDENED_DIAMOND_BOOTS.get())
                .add(ModItems.OBSIDIAN_BOOTS.get())
                .add(ModItems.ENDERITE_BOOTS.get());

        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.STEEL_INGOT.get())
                .add(ModItems.HARDENED_DIAMOND.get())
                .add(ModItems.ENDERITE_INGOT.get());
    }

}
