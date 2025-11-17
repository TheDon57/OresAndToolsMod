package de.thedon.oresandtools.item;

import de.thedon.oresandtools.util.ModTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterials {
    public static final ToolMaterial STEEL;
    public static final ToolMaterial ENDERITE;
    public static final ToolMaterial H_DIAMOND;
    public static final ToolMaterial MOLTEN;
    public static final ToolMaterial EMERALD;
    public static final ToolMaterial OBSIDIAN;

    static {
        STEEL = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL, 500, 6.5f, 2.5f, 12, ModTags.Items.STEEL_REPAIR_MATERIALS);
        EMERALD = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_EMERALD_TOOL, 991, 9.0f, 3.0f, 12, ModTags.Items.EMERALD_REPAIR_MATERIALS);
        MOLTEN = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_MOLTEN_TOOL, 1851, 10.0f, 5.0f, 15, ModTags.Items.MOLTEN_REPAIR_MATERIALS);
        H_DIAMOND = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_H_DIAMOND_TOOL, 2241, 8.0f, 3.0f, 10, ModTags.Items.H_DIAMOND_REPAIR_MATERIALS);
        OBSIDIAN = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_OBSIDIAN_TOOL, 3121, 6.0f, 2.0f, 8, ModTags.Items.OBSIDIAN_REPAIR_MATERIALS);
        ENDERITE = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_ENDERITE_TOOL, 4081, 11.0f, 5.0f, 20, ModTags.Items.ENDERITE_REPAIR_MATERIALS);
    }
}
