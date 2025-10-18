package de.thedon.oresandtools.block;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.custom.ValyrianChestBlock;
import de.thedon.oresandtools.block.custom.XpOreBlock;
import de.thedon.oresandtools.block.entity.ModBlockEntities;
import de.thedon.oresandtools.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(OresAndToolsMod.MOD_ID);

    /* NORMAL BLOCKS */
    public static final DeferredBlock<Block> HARDENED_DIAMOND_BLOCK = registerBlock("hardened_diamond_block", properties -> new Block(properties.strength(25f).requiresCorrectToolForDrops().mapColor(MapColor.DIAMOND).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> STEEL_BLOCK = registerBlock("steel_block", properties -> new Block(properties.strength(8f, 10f).requiresCorrectToolForDrops().mapColor(MapColor.METAL).sound(SoundType.METAL)));
    public static final DeferredBlock<Block> VALYRIAN_CHEST = registerBlock("valyrian_chest", properties -> new ValyrianChestBlock(properties.strength(5f).requiresCorrectToolForDrops().mapColor(MapColor.DIAMOND).sound(SoundType.METAL), ModBlockEntities.VALYRIAN_CHEST::get));

    /* ORES */
    public static final DeferredBlock<Block> VALYRIAN_ORE = registerBlock("valyrian_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties.strength(3f, 3f).requiresCorrectToolForDrops().mapColor(MapColor.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_VALYRIAN_ORE = registerBlock("deepslate_valyrian_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties.strength(4.5f, 3f).requiresCorrectToolForDrops().mapColor(MapColor.DEEPSLATE).sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> ENDSTONE_VALYRIAN_ORE = registerBlock("endstone_valyrian_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties.strength(3.5f, 3f).requiresCorrectToolForDrops().mapColor(MapColor.SAND).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> OBSIDIAN_ORE = registerBlock("obsidian_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties.strength(5f, 3f).requiresCorrectToolForDrops().mapColor(MapColor.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_OBSIDIAN_ORE = registerBlock("deepslate_obsidian_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 7), properties.strength(6.5f, 3f).requiresCorrectToolForDrops().mapColor(MapColor.DEEPSLATE).sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> XP_ORE = registerBlock("xp_ore", properties -> new XpOreBlock(UniformInt.of(12, 20), properties.strength(3f, 3f).requiresCorrectToolForDrops().lightLevel(getLightValueLit(8)).mapColor(MapColor.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_XP_ORE = registerBlock("deepslate_xp_ore", properties -> new XpOreBlock(UniformInt.of(12, 20), properties.strength(4.5f, 3f).requiresCorrectToolForDrops().lightLevel(getLightValueLit(8)).mapColor(MapColor.DEEPSLATE).sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> URANIUM_ORE = registerBlock("uranium_ore", properties -> new Block(properties.strength(3f, 3f).requiresCorrectToolForDrops().mapColor(MapColor.STONE).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_URANIUM_ORE = registerBlock("deepslate_uranium_ore", properties -> new Block(properties.strength(4.5f, 3f).requiresCorrectToolForDrops().mapColor(MapColor.DEEPSLATE).sound(SoundType.DEEPSLATE)));

    /* MOLTEN BLOCKS */
    public static final DeferredBlock<Block> MOLTEN_COPPER_ORE = registerBlock("molten_copper_ore", properties -> new Block(properties.strength(3f, 3f).requiresCorrectToolForDrops().noTerrainParticles().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MOLTEN_IRON_ORE = registerBlock("molten_iron_ore", properties -> new Block(properties.strength(3f, 3f).requiresCorrectToolForDrops().noTerrainParticles().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MOLTEN_GOLD_ORE = registerBlock("molten_gold_ore", properties -> new Block(properties.strength(3f, 3f).requiresCorrectToolForDrops().noTerrainParticles().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MOLTEN_URANIUM_ORE = registerBlock("molten_uranium_ore", properties -> new Block(properties.strength(3f, 3f).requiresCorrectToolForDrops().noTerrainParticles().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MOLTEN_STONE = registerBlock("molten_stone", properties -> new Block(properties.strength(1.5f, 6f).requiresCorrectToolForDrops().noTerrainParticles().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> MOLTEN_SAND = registerBlock("molten_sand", properties -> new Block(properties.strength(0.5f, 0.5f).noTerrainParticles().sound(SoundType.SAND)));


    @SuppressWarnings("SameParameterValue")
    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
