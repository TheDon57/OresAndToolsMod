package de.thedon.oresandtools.util;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> MELTABLE_TO_STONE = createTag("meltable_stones");
        public static final TagKey<Block> VALYRIAN_ORES = createTag("valyrian_ores");
        public static final TagKey<Block> OBSIDIAN_ORES = createTag("obsidian_ores");
        public static final TagKey<Block> XP_ORES = createTag("xp_ores");
        public static final TagKey<Block> URANIUM_ORES = createTag("uranium_ores");

        public static final TagKey<Block> NEEDS_COPPER_TOOL = createTag("needs_copper_tool");
        public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = createTag("incorrect_for_copper_tool");

        public static final TagKey<Block> NEEDS_STEEL_TOOL = createTag("needs_steel_tool");
        public static final TagKey<Block> INCORRECT_FOR_STEEL_TOOL = createTag("incorrect_for_steel_tool");

        public static final TagKey<Block> NEEDS_VALYRIAN_TOOL = createTag("needs_valyrian_tool");
        public static final TagKey<Block> INCORRECT_FOR_VALYRIAN_TOOL = createTag("incorrect_for_valyrian_tool");

        public static final TagKey<Block> NEEDS_HARDENED_DIAMOND_TOOL = createTag("needs_hardened_diamond_tool");
        public static final TagKey<Block> INCORRECT_FOR_HARDENED_DIAMOND_TOOL = createTag("incorrect_for_hardened_diamond_tool");

        public static final TagKey<Block> NEEDS_HOT_HARDENED_DIAMOND_TOOL = createTag("needs_hot_hardened_diamond_tool");
        public static final TagKey<Block> INCORRECT_FOR_HOT_HARDENED_DIAMOND_TOOL = createTag("incorrect_for_hot_hardened_diamond_tool");

        public static final TagKey<Block> NEEDS_EMERALD_TOOL = createTag("needs_emerald_tool");
        public static final TagKey<Block> INCORRECT_FOR_EMERALD_TOOL = createTag("incorrect_for_emerald_tool");

        public static final TagKey<Block> NEEDS_OBSIDIAN_TOOL = createTag("needs_obsidian_tool");
        public static final TagKey<Block> INCORRECT_FOR_OBSIDIAN_TOOL = createTag("incorrect_for_obsidian_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> DRAGON_APPLE_MATERIALS = createTag("dragon_apple_materials");
        public static final TagKey<Item> HARDENED_DIAMOND_MATERIALS = createTag("hardened_diamond_materials");
        public static final TagKey<Item> IMPROVISED_REACTOR_MATERIALS = createTag("improvised_reactor_materials");
        public static final TagKey<Item> OBSIDIAN_SHIELD_MATERIALS = createTag("obsidian_shield_materials");
        public static final TagKey<Item> STEEL_CHUNK_MATERIALS = createTag("steel_chunk_materials");
        public static final TagKey<Item> VALYRIAN_INGOT_MATERIALS = createTag("valyrian_ingot_materials");
        public static final TagKey<Item> VALYRIAN_LEATHER_MATERIALS = createTag("valyrian_leather_materials");
        public static final TagKey<Item> SHULKER_BACKPACK_MATERIALS = createTag("shulker_backpack_materials");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, name));
        }
    }
}
