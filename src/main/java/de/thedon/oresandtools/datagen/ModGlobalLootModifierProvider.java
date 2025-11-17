package de.thedon.oresandtools.datagen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.loot.ItemLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider  extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, OresAndToolsMod.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("enderite_upgrade_from_end_city_treasure",
                new ItemLootModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(BuiltInLootTables.END_CITY_TREASURE.location()).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE.get()));
    }
}
