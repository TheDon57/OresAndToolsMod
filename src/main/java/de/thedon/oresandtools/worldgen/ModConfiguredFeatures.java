package de.thedon.oresandtools.worldgen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_OBSIDIAN_LARGE_KEY = registerKey("ore_obsidian_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_OBSIDIAN_MIDDLE_KEY = registerKey("ore_obsidian_middle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_OBSIDIAN_SMALL_KEY = registerKey("ore_obsidian_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_URANIUM_LARGE_KEY = registerKey("ore_uranium_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_URANIUM_MIDDLE_KEY = registerKey("ore_uranium_middle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_URANIUM_SMALL_KEY = registerKey("ore_uranium_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ENDERITE_LARGE_KEY = registerKey("ore_enderite_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ENDERITE_MIDDLE_KEY = registerKey("ore_enderite_middle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_ENDERITE_SMALL_KEY = registerKey("ore_enderite_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_END_ENDERITE_LARGE_KEY = registerKey("ore_end_enderite_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_END_ENDERITE_MIDDLE_KEY = registerKey("ore_end_enderite_middle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_END_ENDERITE_SMALL_KEY = registerKey("ore_end_enderite_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_XP_LARGE_KEY = registerKey("ore_xp_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_XP_MIDDLE_KEY = registerKey("ore_xp_middle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_XP_SMALL_KEY = registerKey("ore_xp_small");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest endstoneReplaceables = new TagMatchTest(Tags.Blocks.END_STONES);

        List<OreConfiguration.TargetBlockState> obsidianOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.OBSIDIAN_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_OBSIDIAN_ORE.get().defaultBlockState())
        );
        register(context, ORE_OBSIDIAN_LARGE_KEY, Feature.ORE, new OreConfiguration(obsidianOres, 8));
        register(context, ORE_OBSIDIAN_MIDDLE_KEY, Feature.ORE, new OreConfiguration(obsidianOres, 5));
        register(context, ORE_OBSIDIAN_SMALL_KEY, Feature.ORE, new OreConfiguration(obsidianOres, 2));

        List<OreConfiguration.TargetBlockState> uraniumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.URANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_URANIUM_ORE.get().defaultBlockState())
        );
        register(context, ORE_URANIUM_LARGE_KEY, Feature.ORE, new OreConfiguration(uraniumOres, 4));
        register(context, ORE_URANIUM_MIDDLE_KEY, Feature.ORE, new OreConfiguration(uraniumOres, 2));
        register(context, ORE_URANIUM_SMALL_KEY, Feature.ORE, new OreConfiguration(uraniumOres, 1));

        List<OreConfiguration.TargetBlockState> enderiteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.ENDERITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_ENDERITE_ORE.get().defaultBlockState())
        );
        register(context, ORE_ENDERITE_LARGE_KEY, Feature.ORE, new OreConfiguration(enderiteOres, 4));
        register(context, ORE_ENDERITE_MIDDLE_KEY, Feature.ORE, new OreConfiguration(enderiteOres, 2));
        register(context, ORE_ENDERITE_SMALL_KEY, Feature.ORE, new OreConfiguration(enderiteOres, 1));

        register(context, ORE_END_ENDERITE_LARGE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceables, ModBlocks.ENDSTONE_ENDERITE_ORE.get().defaultBlockState(), 4));
        register(context, ORE_END_ENDERITE_MIDDLE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceables, ModBlocks.ENDSTONE_ENDERITE_ORE.get().defaultBlockState(), 2));
        register(context, ORE_END_ENDERITE_SMALL_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceables, ModBlocks.ENDSTONE_ENDERITE_ORE.get().defaultBlockState(), 1));

        List<OreConfiguration.TargetBlockState> xpOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.XP_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_XP_ORE.get().defaultBlockState())
        );
        register(context, ORE_XP_LARGE_KEY, Feature.ORE, new OreConfiguration(xpOres, 9));
        register(context, ORE_XP_MIDDLE_KEY, Feature.ORE, new OreConfiguration(xpOres, 6));
        register(context, ORE_XP_SMALL_KEY, Feature.ORE, new OreConfiguration(xpOres, 3));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
