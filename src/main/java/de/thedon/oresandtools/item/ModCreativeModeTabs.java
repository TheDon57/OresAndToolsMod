package de.thedon.oresandtools.item;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OresAndToolsMod.MOD_ID);

    public static Supplier<CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register("oat_main_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + OresAndToolsMod.MOD_ID + ".oat_main_tab"))
            .icon(() -> new ItemStack(ModItems.HARDENED_DIAMOND.get()))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.STEEL_CHUNK);
                output.accept(ModItems.STEEL_INGOT);
                output.accept(ModItems.ENDERITE_DUST);
                output.accept(ModItems.ENDERITE_INGOT);
                output.accept(ModItems.DRAGONS_APPLE);
                output.accept(ModItems.RAW_URANIUM);
                output.accept(ModItems.URANIUM_INGOT);
                output.accept(ModItems.IMPROVISED_REACTOR);
                output.accept(ModItems.HARDENED_DIAMOND);
                output.accept(ModItems.HOT_HARDENED_DIAMOND);
                output.accept(ModItems.OBSIDIAN_SHARD);
                output.accept(ModItems.ENDERITE_LEATHER);
                output.accept(ModItems.SHULKER_BACKPACK);

                output.accept(ModBlocks.STEEL_BLOCK);
                output.accept(ModBlocks.HARDENED_DIAMOND_BLOCK);
                output.accept(ModBlocks.ENDERITE_CHEST);
                output.accept(ModBlocks.ENDERITE_ORE);
                output.accept(ModBlocks.DEEPSLATE_ENDERITE_ORE);
                output.accept(ModBlocks.ENDSTONE_ENDERITE_ORE);
                output.accept(ModBlocks.OBSIDIAN_ORE);
                output.accept(ModBlocks.DEEPSLATE_OBSIDIAN_ORE);
                output.accept(ModBlocks.XP_ORE);
                output.accept(ModBlocks.DEEPSLATE_XP_ORE);
                output.accept(ModBlocks.URANIUM_ORE);
                output.accept(ModBlocks.DEEPSLATE_URANIUM_ORE);
            }).build());
    public static Supplier<CreativeModeTab> TOOLS_TAB = CREATIVE_MODE_TABS.register("oat_tools_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + OresAndToolsMod.MOD_ID + ".oat_tools_tab"))
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, "oat_main_tab"))
            .icon(() -> new ItemStack(ModItems.HARDENED_DIAMOND_AXE.get()))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.STEEL_SHOVEL);
                output.accept(ModItems.STEEL_PICKAXE);
                output.accept(ModItems.STEEL_AXE);
                output.accept(ModItems.STEEL_HOE);
                output.accept(ModItems.ENDERITE_SHOVEL);
                output.accept(ModItems.ENDERITE_PICKAXE);
                output.accept(ModItems.ENDERITE_AXE);
                output.accept(ModItems.ENDERITE_HOE);
                output.accept(ModItems.HARDENED_DIAMOND_SHOVEL);
                output.accept(ModItems.HARDENED_DIAMOND_PICKAXE);
                output.accept(ModItems.HARDENED_DIAMOND_AXE);
                output.accept(ModItems.HARDENED_DIAMOND_HOE);
                output.accept(ModItems.HOT_HARDENED_DIAMOND_SHOVEL);
                output.accept(ModItems.HOT_HARDENED_DIAMOND_PICKAXE);
                output.accept(ModItems.HOT_HARDENED_DIAMOND_AXE);
                output.accept(ModItems.HOT_HARDENED_DIAMOND_HOE);
                output.accept(ModItems.EMERALD_SHOVEL);
                output.accept(ModItems.EMERALD_PICKAXE);
                output.accept(ModItems.EMERALD_AXE);
                output.accept(ModItems.EMERALD_HOE);
                output.accept(ModItems.OBSIDIAN_SHOVEL);
                output.accept(ModItems.OBSIDIAN_PICKAXE);
                output.accept(ModItems.OBSIDIAN_AXE);
                output.accept(ModItems.OBSIDIAN_HOE);
            }).build());
    public static Supplier<CreativeModeTab> COMBAT_TAB = CREATIVE_MODE_TABS.register("oat_combat_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + OresAndToolsMod.MOD_ID + ".oat_combat_tab"))
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, "oat_main_tab"),
                            ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, "oat_tools_tab"))
            .icon(() -> new ItemStack(ModItems.HARDENED_DIAMOND_SWORD.get()))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.STEEL_SWORD);
                output.accept(ModItems.HARDENED_DIAMOND_SWORD);
                output.accept(ModItems.HOT_HARDENED_DIAMOND_SWORD);
                output.accept(ModItems.ENDERITE_SWORD);
                output.accept(ModItems.EMERALD_SWORD);
                output.accept(ModItems.OBSIDIAN_SWORD);
                output.accept(ModItems.ENDERITE_BOW);
                output.accept(ModItems.OBSIDIAN_SHIELD);
                output.accept(ModItems.STEEL_HELMET);
                output.accept(ModItems.STEEL_CHESTPLATE);
                output.accept(ModItems.STEEL_LEGGINGS);
                output.accept(ModItems.STEEL_BOOTS);
                output.accept(ModItems.ENDERITE_HELMET);
                output.accept(ModItems.ENDERITE_CHESTPLATE);
                output.accept(ModItems.ENDERITE_LEGGINGS);
                output.accept(ModItems.ENDERITE_BOOTS);
                output.accept(ModItems.HARDENED_DIAMOND_HELMET);
                output.accept(ModItems.HARDENED_DIAMOND_CHESTPLATE);
                output.accept(ModItems.HARDENED_DIAMOND_LEGGINGS);
                output.accept(ModItems.HARDENED_DIAMOND_BOOTS);
                output.accept(ModItems.HOT_HARDENED_DIAMOND_HELMET);
                output.accept(ModItems.HOT_HARDENED_DIAMOND_CHESTPLATE);
                output.accept(ModItems.HOT_HARDENED_DIAMOND_LEGGINGS);
                output.accept(ModItems.HOT_HARDENED_DIAMOND_BOOTS);
                output.accept(ModItems.EMERALD_HELMET);
                output.accept(ModItems.EMERALD_CHESTPLATE);
                output.accept(ModItems.EMERALD_LEGGINGS);
                output.accept(ModItems.EMERALD_BOOTS);
                output.accept(ModItems.OBSIDIAN_HELMET);
                output.accept(ModItems.OBSIDIAN_CHESTPLATE);
                output.accept(ModItems.OBSIDIAN_LEGGINGS);
                output.accept(ModItems.OBSIDIAN_BOOTS);
            }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
