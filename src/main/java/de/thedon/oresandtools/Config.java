package de.thedon.oresandtools;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = OresAndToolsMod.MOD_ID)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue IMPRO_REACTOR_BURN_TIME = BUILDER
            .comment("Burn time of the improvised reactor in the furnace")
            .defineInRange("impro_reactor_burn_time", 204800, 1200, Integer.MAX_VALUE);

    private static final ModConfigSpec.IntValue URANIUM_DAMAGE_TICKRATE = BUILDER
            .comment("After how many ticks the player will get damage from the uranium ingot")
            .defineInRange("uranium_damage_tickrate", 100, 50, 1200);

    private static final ModConfigSpec.IntValue URANIUM_DAMAGE_AMOUNT = BUILDER
            .comment("How much damage the player will get damage from the uranium ingot in the specified tickrate")
            .defineInRange("uranium_damage_amount", 3, 1, 10);

    private static final ModConfigSpec.BooleanValue DISABLE_SET_BONUSES = BUILDER
            .comment("Disables all armor set bonuses")
            .define("disable_set_bonuses", false);

    private static final ModConfigSpec.DoubleValue HOT_DIA_DAMAGE_REFLECT_PERCENTAGE = BUILDER
            .comment("How much of the damage is reflected to the attacking enemy when you wear full hot diamond armor")
            .defineInRange("hot_dia_damage_reflect_percentage", 0.25, 0, 1.0);

    private static final ModConfigSpec.IntValue HOT_DIA_FIRE_REFLECT_DURATION = BUILDER
            .comment("How long enemies will burn if they hit you when you wear full hot diamond armor")
            .defineInRange("hot_dia_fire_reflect_duration", 50, 0, 100);

    private static final ModConfigSpec.BooleanValue DISABLE_HOT_DIA_FIRE_ASPECT = BUILDER
            .comment("Disables the fire aspect effect for hot dia tool or sword")
            .define("disable_hot_dia_fire_aspect", false);

    private static final ModConfigSpec.IntValue HOT_DIA_FIRE_ASPECT_DURATION = BUILDER
            .comment("How long enemies will burn if you them with hot dia tool or sword")
            .defineInRange("hot_dia_fire_aspect_duration", 50, 1, Integer.MAX_VALUE);

    private static final ModConfigSpec.DoubleValue VILLAGER_EMERALD_DROP_CHANCE = BUILDER
            .comment("Probability that a villager will drop a emerald if killy be emerald sword")
            .defineInRange("villager_emerald_drop_chance", 0.15, 0.01, 1.0);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static int improReactorBurnTime;
    public static int uraniumDamageTickrate;
    public static int uraniumDamageAmount;
    public static boolean disableSetBonuses;
    public static double hotDiaDamageReflectPercentage;
    public static int hotDiaFireReflectDuration;
    public static boolean disableHotDiaFireAspect;
    public static int hotDiaFireAspectDuration;
    public static double villagerEmeraldDropChance;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        improReactorBurnTime = IMPRO_REACTOR_BURN_TIME.get();
        uraniumDamageTickrate = URANIUM_DAMAGE_TICKRATE.get();
        uraniumDamageAmount = URANIUM_DAMAGE_AMOUNT.get();
        disableSetBonuses = DISABLE_SET_BONUSES.get();
        hotDiaDamageReflectPercentage = HOT_DIA_DAMAGE_REFLECT_PERCENTAGE.get();
        hotDiaFireReflectDuration = HOT_DIA_FIRE_REFLECT_DURATION.get();
        disableHotDiaFireAspect = DISABLE_HOT_DIA_FIRE_ASPECT.get();
        hotDiaFireAspectDuration = HOT_DIA_FIRE_ASPECT_DURATION.get();
        villagerEmeraldDropChance = VILLAGER_EMERALD_DROP_CHANCE.get();
    }
}
