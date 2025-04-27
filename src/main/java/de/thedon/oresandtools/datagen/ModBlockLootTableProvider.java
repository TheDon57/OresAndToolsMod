package de.thedon.oresandtools.datagen;

import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        /* NORMAL BLOCKS */
        dropSelf(ModBlocks.HARDENED_DIAMOND_BLOCK.get());
        dropSelf(ModBlocks.STEEL_BLOCK.get());
        dropSelf(ModBlocks.VALYRIAN_CHEST.get());
        /* ORES */
        add(ModBlocks.VALYRIAN_ORE.get(), this::createValyrianOreDrops);
        add(ModBlocks.DEEPSLATE_VALYRIAN_ORE.get(), this::createValyrianOreDrops);
        add(ModBlocks.ENDSTONE_VALYRIAN_ORE.get(), this::createValyrianOreDrops);
        add(ModBlocks.OBSIDIAN_ORE.get(), this::createObsidianOreDrops);
        add(ModBlocks.DEEPSLATE_OBSIDIAN_ORE.get(), this::createObsidianOreDrops);
        add(ModBlocks.XP_ORE.get(), (pBlock) -> createSingleItemTableWithSilkTouch(pBlock, Items.COBBLESTONE));
        add(ModBlocks.DEEPSLATE_XP_ORE.get(), (pBlock) -> createSingleItemTableWithSilkTouch(pBlock, Items.COBBLED_DEEPSLATE));
        add(ModBlocks.URANIUM_ORE.get(), (pBlock) -> createOreDrop(pBlock, ModItems.RAW_URANIUM.get()));
        add(ModBlocks.DEEPSLATE_URANIUM_ORE.get(), (pBlock) -> createOreDrop(pBlock, ModItems.RAW_URANIUM.get()));
        /* MOLTEN BLOCKS */
        add(ModBlocks.MOLTEN_COPPER_ORE.get(), (pBlock) -> createOreDrop(Blocks.COPPER_ORE, Items.COPPER_INGOT));
        add(ModBlocks.MOLTEN_IRON_ORE.get(), (pBlock) -> createOreDrop(Blocks.IRON_ORE, Items.IRON_INGOT));
        add(ModBlocks.MOLTEN_GOLD_ORE.get(), (pBlock) -> createOreDrop(Blocks.GOLD_ORE, Items.GOLD_INGOT));
        add(ModBlocks.MOLTEN_URANIUM_ORE.get(), (pBlock) -> createOreDrop(ModBlocks.URANIUM_ORE.get(), ModItems.URANIUM_INGOT.get()));
        add(ModBlocks.MOLTEN_STONE.get(), (pBlock) -> createOreDrop(Blocks.STONE, Items.STONE));
        add(ModBlocks.MOLTEN_SAND.get(), (pBlock) -> createOreDrop(Blocks.GLASS, Items.GLASS));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected LootTable.Builder createValyrianOreDrops(Block pBlock) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(ModItems.VALYRIAN_DUST.get())
                                .apply(SetItemCountFunction.setCount(BinomialDistributionGenerator.binomial(3, 0.5F)))
                                .when(LootItemRandomChanceCondition.randomChance(0.25F)))
                        .append(LootItem.lootTableItem(ModItems.VALYRIAN_DUST.get())
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    protected LootTable.Builder createObsidianOreDrops(Block pBlock) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(
                        ModItems.OBSIDIAN_SHARD.get())
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 5.0F)))
                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }
}
