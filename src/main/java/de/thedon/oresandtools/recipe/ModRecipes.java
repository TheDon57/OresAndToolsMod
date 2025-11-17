package de.thedon.oresandtools.recipe;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, OresAndToolsMod.MOD_ID);

    public static final Supplier<RecipeSerializer<ObsidianShieldDecorationRecipe>> OBSIDIAN_SHIELD_DECORATION = register("crafting_special_obsidian_shielddecoration", new CustomRecipe.Serializer<>(ObsidianShieldDecorationRecipe::new));

    private static <T extends Recipe<?>> Supplier<RecipeSerializer<T>> register(String name, RecipeSerializer<T> serializer) {
        return RECIPE_SERIALIZERS.register(name, () -> serializer);
    }

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
