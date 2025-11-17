package de.thedon.oresandtools.datagen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, OresAndToolsMod.MOD_ID);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.HARDENED_DIAMOND_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.ENDERITE_CHEST.get())
                .add(ModBlocks.ENDERITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_ENDERITE_ORE.get())
                .add(ModBlocks.ENDSTONE_ENDERITE_ORE.get())
                .add(ModBlocks.OBSIDIAN_ORE.get())
                .add(ModBlocks.DEEPSLATE_OBSIDIAN_ORE.get())
                .add(ModBlocks.XP_ORE.get())
                .add(ModBlocks.DEEPSLATE_XP_ORE.get())
                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get())
                .add(ModBlocks.MOLTEN_COPPER_ORE.get())
                .add(ModBlocks.MOLTEN_IRON_ORE.get())
                .add(ModBlocks.MOLTEN_GOLD_ORE.get())
                .add(ModBlocks.MOLTEN_URANIUM_ORE.get())
                .add(ModBlocks.MOLTEN_STONE.get());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.MOLTEN_SAND.get());

        tag(ModTags.Blocks.MELTABLE_TO_STONE)
                .add(Blocks.STONE)
                .add(Blocks.COBBLESTONE);

        tag(ModTags.Blocks.ENDERITE_ORES)
                .add(ModBlocks.ENDERITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_ENDERITE_ORE.get());

        tag(ModTags.Blocks.OBSIDIAN_ORES)
                .add(ModBlocks.OBSIDIAN_ORE.get())
                .add(ModBlocks.DEEPSLATE_OBSIDIAN_ORE.get());

        tag(ModTags.Blocks.XP_ORES)
                .add(ModBlocks.XP_ORE.get())
                .add(ModBlocks.DEEPSLATE_XP_ORE.get());

        tag(ModTags.Blocks.URANIUM_ORES)
                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.HARDENED_DIAMOND_BLOCK.get())
                .add(ModBlocks.ENDERITE_CHEST.get())
                .add(ModBlocks.ENDERITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_ENDERITE_ORE.get())
                .add(ModBlocks.ENDSTONE_ENDERITE_ORE.get())
                .add(ModBlocks.OBSIDIAN_ORE.get())
                .add(ModBlocks.DEEPSLATE_OBSIDIAN_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.URANIUM_ORE.get())
                .add(ModBlocks.MOLTEN_URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_URANIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_XP_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.XP_ORE.get())
                .add(ModBlocks.MOLTEN_IRON_ORE.get())
                .add(ModBlocks.MOLTEN_GOLD_ORE.get());

        tag(ModTags.Blocks.NEEDS_STEEL_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_STEEL_TOOL);

        tag(ModTags.Blocks.NEEDS_ENDERITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_ENDERITE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_ENDERITE_TOOL);

        tag(ModTags.Blocks.NEEDS_HARDENED_DIAMOND_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_H_DIAMOND_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_HARDENED_DIAMOND_TOOL);

        tag(ModTags.Blocks.NEEDS_MOLTEN_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_MOLTEN_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_MOLTEN_TOOL);

        tag(ModTags.Blocks.NEEDS_EMERALD_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_EMERALD_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_EMERALD_TOOL);

        tag(ModTags.Blocks.NEEDS_OBSIDIAN_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        tag(ModTags.Blocks.INCORRECT_FOR_OBSIDIAN_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_OBSIDIAN_TOOL)
                .add(Blocks.OBSIDIAN);
    }
}
