package de.thedon.oresandtools.block;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.entity.ModBlockEntities;
import de.thedon.oresandtools.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(OresAndToolsMod.MOD_ID);

    /* NORMAL BLOCKS */
    public static final DeferredBlock<Block> HARDENED_DIAMOND_BLOCK = registerBlock("hardened_diamond_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(25f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(8f, 10f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
    public static final DeferredBlock<Block> VALYRIAN_CHEST = registerBlock("valyrian_chest", () -> new ValyrianChestBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST).strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL), ModBlockEntities.VALYRIAN_CHEST::get));

    /* ORES */
    public static final DeferredBlock<Block> VALYRIAN_ORE = registerBlock("valyrian_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f, 3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_VALYRIAN_ORE = registerBlock("deepslate_valyrian_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(VALYRIAN_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5f, 3f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> ENDSTONE_VALYRIAN_ORE = registerBlock("endstone_valyrian_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(VALYRIAN_ORE.get()).mapColor(MapColor.SAND).strength(3.5f, 3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> OBSIDIAN_ORE = registerBlock("obsidian_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(5f, 3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_OBSIDIAN_ORE = registerBlock("deepslate_obsidian_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(OBSIDIAN_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(6.5f, 3f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> XP_ORE = registerBlock("xp_ore", () -> new XpOreBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f, 3f).requiresCorrectToolForDrops().lightLevel(getLightValueLit(8)).sound(SoundType.STONE), UniformInt.of(12, 20)));
    public static final DeferredBlock<Block> DEEPSLATE_XP_ORE = registerBlock("deepslate_xp_ore", () -> new XpOreBlock(BlockBehaviour.Properties.ofFullCopy(XP_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5f, 3f).requiresCorrectToolForDrops().lightLevel(getLightValueLit(8)).sound(SoundType.DEEPSLATE), UniformInt.of(12, 20)));
    public static final DeferredBlock<Block> URANIUM_ORE = registerBlock("uranium_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f, 3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_URANIUM_ORE = registerBlock("deepslate_uranium_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(URANIUM_ORE.get()).mapColor(MapColor.DEEPSLATE).strength(4.5f, 3f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    /* MOLTEN BLOCKS */
    public static final DeferredBlock<Block> MOLTEN_COPPER_ORE = registerBlock("molten_copper_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f, 3f).requiresCorrectToolForDrops().noTerrainParticles()));
    public static final DeferredBlock<Block> MOLTEN_IRON_ORE = registerBlock("molten_iron_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f, 3f).requiresCorrectToolForDrops().noTerrainParticles()));
    public static final DeferredBlock<Block> MOLTEN_GOLD_ORE = registerBlock("molten_gold_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f, 3f).requiresCorrectToolForDrops().noTerrainParticles()));
    public static final DeferredBlock<Block> MOLTEN_URANIUM_ORE = registerBlock("molten_uranium_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f, 3f).requiresCorrectToolForDrops().noTerrainParticles()));
    public static final DeferredBlock<Block> MOLTEN_STONE = registerBlock("molten_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(1.5f, 6f).requiresCorrectToolForDrops().noTerrainParticles()));
    public static final DeferredBlock<Block> MOLTEN_SAND = registerBlock("molten_sand", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).strength(0.5f, 0.5f).noTerrainParticles()));


    @SuppressWarnings("SameParameterValue")
    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
