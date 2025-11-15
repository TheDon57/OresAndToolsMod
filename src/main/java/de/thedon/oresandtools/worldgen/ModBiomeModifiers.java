package de.thedon.oresandtools.worldgen;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_OBSIDIAN_ORE = registerKey("add_obsidian_ore");
    public static final ResourceKey<BiomeModifier> ADD_URANIUM_ORE = registerKey("add_uranium_ore");
    public static final ResourceKey<BiomeModifier> ADD_ENDERITE_ORE = registerKey("add_enderite_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_ENDERITE_ORE = registerKey("add_end_enderite_ore");
    public static final ResourceKey<BiomeModifier> ADD_XP_ORE = registerKey("add_xp_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_OBSIDIAN_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_OBSIDIAN_LARGE_PLACED_KEY),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.ORE_OBSIDIAN_MIDDLE_PLACED_KEY),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.ORE_OBSIDIAN_SMALL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_URANIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_URANIUM_LARGE_PLACED_KEY),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.ORE_URANIUM_MIDDLE_PLACED_KEY),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.ORE_URANIUM_SMALL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_ENDERITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_ENDERITE_LARGE_PLACED_KEY),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.ORE_ENDERITE_MIDDLE_PLACED_KEY),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.ORE_ENDERITE_SMALL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_END_ENDERITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_END_ENDERITE_LARGE_PLACED_KEY),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.ORE_END_ENDERITE_MIDDLE_PLACED_KEY),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.ORE_END_ENDERITE_SMALL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_XP_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ORE_XP_LARGE_PLACED_KEY),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.ORE_XP_MIDDLE_PLACED_KEY),
                                 placedFeatures.getOrThrow(ModPlacedFeatures.ORE_XP_SMALL_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, name));
    }
}
