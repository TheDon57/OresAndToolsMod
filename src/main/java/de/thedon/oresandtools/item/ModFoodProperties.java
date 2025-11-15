package de.thedon.oresandtools.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoodProperties {
    public static final FoodProperties DRAGONS_APPLE = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(2.5F)
            .alwaysEdible()
            .build();

    public static final Consumable DRAGONS_APPLE_EFFECT = Consumables
            .defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1800, 1), 1.0F))
            .build();
}
