package de.thedon.oresandtools.item;

import de.thedon.oresandtools.util.ModTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier COPPER = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_COPPER_TOOL,
            180, 9f, 1f, 17, () -> Ingredient.of(Items.COPPER_INGOT));

    public static final Tier STEEL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL,
            500, 6.5f, 2.5f, 12, () -> Ingredient.of(Items.COPPER_INGOT));

    public static final Tier VALYRIAN = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_VALYRIAN_TOOL,
            4000, 10f, 6f, 25, () -> Ingredient.of(Items.COPPER_INGOT));

    public static final Tier HARDENED_DIAMOND = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_HARDENED_DIAMOND_TOOL,
            7000, 8f, 3f, 10, () -> Ingredient.of(Items.COPPER_INGOT));

    public static final Tier HOT_HARDENED_DIAMOND = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_HOT_HARDENED_DIAMOND_TOOL,
            5500, 8f, 3f, 11, () -> Ingredient.of(Items.COPPER_INGOT));

    public static final Tier EMERALD = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_EMERALD_TOOL,
            1561, 12f, 3f, 14, () -> Ingredient.of(Items.COPPER_INGOT));

    public static final Tier OBSIDIAN = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_OBSIDIAN_TOOL,
            6500, 6f, 3f, 8, () -> Ingredient.of(Items.COPPER_INGOT));
}
