package de.thedon.oresandtools.worldgen;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> ORE_OBSIDIAN_LARGE_PLACED_KEY = createKey("ore_obsidian_large");
    public static final ResourceKey<PlacedFeature> ORE_OBSIDIAN_MIDDLE_PLACED_KEY = createKey("ore_obsidian_middle");
    public static final ResourceKey<PlacedFeature> ORE_OBSIDIAN_SMALL_PLACED_KEY = createKey("ore_obsidian_small");
    public static final ResourceKey<PlacedFeature> ORE_URANIUM_LARGE_PLACED_KEY = createKey("ore_uranium_large");
    public static final ResourceKey<PlacedFeature> ORE_URANIUM_MIDDLE_PLACED_KEY = createKey("ore_uranium_middle");
    public static final ResourceKey<PlacedFeature> ORE_URANIUM_SMALL_PLACED_KEY = createKey("ore_uranium_small");
    public static final ResourceKey<PlacedFeature> ORE_VALYRIAN_LARGE_PLACED_KEY = createKey("ore_valyrian_large");
    public static final ResourceKey<PlacedFeature> ORE_VALYRIAN_MIDDLE_PLACED_KEY = createKey("ore_valyrian_middle");
    public static final ResourceKey<PlacedFeature> ORE_VALYRIAN_SMALL_PLACED_KEY = createKey("ore_valyrian_small");
    public static final ResourceKey<PlacedFeature> ORE_END_VALYRIAN_LARGE_PLACED_KEY = createKey("ore_end_valyrian_large");
    public static final ResourceKey<PlacedFeature> ORE_END_VALYRIAN_MIDDLE_PLACED_KEY = createKey("ore_end_valyrian_middle");
    public static final ResourceKey<PlacedFeature> ORE_END_VALYRIAN_SMALL_PLACED_KEY = createKey("ore_end_valyrian_small");
    public static final ResourceKey<PlacedFeature> ORE_XP_LARGE_PLACED_KEY = createKey("ore_xp_large");
    public static final ResourceKey<PlacedFeature> ORE_XP_MIDDLE_PLACED_KEY = createKey("ore_xp_middle");
    public static final ResourceKey<PlacedFeature> ORE_XP_SMALL_PLACED_KEY = createKey("ore_xp_small");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, ORE_OBSIDIAN_LARGE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_OBSIDIAN_LARGE_KEY),
                ModOrePlacements.commonOrePlacement(4,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_OBSIDIAN_MIDDLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_OBSIDIAN_MIDDLE_KEY),
                ModOrePlacements.commonOrePlacement(8,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_OBSIDIAN_SMALL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_OBSIDIAN_SMALL_KEY),
                ModOrePlacements.commonOrePlacement(16,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, ORE_URANIUM_LARGE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_URANIUM_LARGE_KEY),
                ModOrePlacements.commonOrePlacement(4,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_URANIUM_MIDDLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_URANIUM_MIDDLE_KEY),
                ModOrePlacements.commonOrePlacement(8,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_URANIUM_SMALL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_URANIUM_SMALL_KEY),
                ModOrePlacements.commonOrePlacement(12,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, ORE_VALYRIAN_LARGE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_VALYRIAN_LARGE_KEY),
                ModOrePlacements.rareOrePlacement(7,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_VALYRIAN_MIDDLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_VALYRIAN_MIDDLE_KEY),
                ModOrePlacements.commonOrePlacement(2,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));
        register(context, ORE_VALYRIAN_SMALL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_VALYRIAN_SMALL_KEY),
                ModOrePlacements.commonOrePlacement(8,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, ORE_END_VALYRIAN_LARGE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_END_VALYRIAN_LARGE_KEY),
                ModOrePlacements.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(80))));
        register(context, ORE_END_VALYRIAN_MIDDLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_END_VALYRIAN_MIDDLE_KEY),
                ModOrePlacements.commonOrePlacement(8,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(80))));
        register(context, ORE_END_VALYRIAN_SMALL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_END_VALYRIAN_SMALL_KEY),
                ModOrePlacements.commonOrePlacement(12,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(80))));

        register(context, ORE_XP_LARGE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_XP_LARGE_KEY),
                ModOrePlacements.commonOrePlacement(6,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.absolute(72))));
        register(context, ORE_XP_MIDDLE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_XP_MIDDLE_KEY),
                ModOrePlacements.commonOrePlacement(12,
                        HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-24), VerticalAnchor.absolute(92))));
        register(context, ORE_XP_SMALL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORE_XP_SMALL_KEY),
                ModOrePlacements.commonOrePlacement(24,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(292))));

    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }


}
