package de.thedon.oresandtools.item;

import de.thedon.oresandtools.util.ModTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterials {
    public static final ToolMaterial STEEL = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL,
            500, 6.5f, 2.5f, 12, ModTags.Items.STEEL_REPAIRABLE);

    public static final ToolMaterial ENDERITE = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_ENDERITE_TOOL,
            4000, 10f, 6f, 25, ModTags.Items.ENDERITE_REPAIRABLE);

    public static final ToolMaterial HARDENED_DIAMOND = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_HARDENED_DIAMOND_TOOL,
            7000, 8f, 3f, 10, ModTags.Items.HARDENED_DIAMOND_REPAIRABLE);

    public static final ToolMaterial HOT_HARDENED_DIAMOND = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_HOT_HARDENED_DIAMOND_TOOL,
            5500, 8f, 3f, 11, ModTags.Items.HOT_HARDENED_DIAMOND_REPAIRABLE);

    public static final ToolMaterial EMERALD = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_EMERALD_TOOL,
            1561, 12f, 3f, 14, ModTags.Items.EMERALD_REPAIRABLE);

    public static final ToolMaterial OBSIDIAN = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_OBSIDIAN_TOOL,
            6500, 6f, 3f, 8, ModTags.Items.OBSIDIAN_REPAIRABLE);
}
