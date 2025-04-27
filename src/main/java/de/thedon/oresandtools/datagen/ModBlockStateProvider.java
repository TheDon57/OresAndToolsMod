package de.thedon.oresandtools.datagen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, OresAndToolsMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        /* NORMAL BLOCKS */
        blockWithItem(ModBlocks.HARDENED_DIAMOND_BLOCK);
        blockWithItem(ModBlocks.STEEL_BLOCK);
        /* ORES */
        blockWithItem(ModBlocks.VALYRIAN_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_VALYRIAN_ORE);
        blockWithItem(ModBlocks.ENDSTONE_VALYRIAN_ORE);
        blockWithItem(ModBlocks.OBSIDIAN_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_OBSIDIAN_ORE);
        blockWithItem(ModBlocks.XP_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_XP_ORE);
        blockWithItem(ModBlocks.URANIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_URANIUM_ORE);
        /* MOLTEN BLOCKS */
        blockWithItem(ModBlocks.MOLTEN_COPPER_ORE);
        blockWithItem(ModBlocks.MOLTEN_IRON_ORE);
        blockWithItem(ModBlocks.MOLTEN_GOLD_ORE);
        blockWithItem(ModBlocks.MOLTEN_URANIUM_ORE);
        blockWithItem(ModBlocks.MOLTEN_STONE);
        blockWithItem(ModBlocks.MOLTEN_SAND);
    }

    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
