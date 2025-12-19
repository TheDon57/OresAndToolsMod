package de.thedon.oresandtools.datagen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider) {
            super(packOutput, provider);
        }

        @Override
        protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.@NotNull Provider provider, @NotNull RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public @NotNull String getName() {
            return "Ores and Tools Mod Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        /* NORMAL ITEMS */
        shaped(RecipeCategory.FOOD, ModItems.DRAGONS_APPLE.get())
                .define('V', ModItems.ENDERITE_DUST.get())
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('A', Items.APPLE)
                .pattern("VOV")
                .pattern("OAO")
                .pattern("VOV")
                .unlockedBy(getHasMaterials(ModItems.DRAGONS_APPLE.get()), has(ModTags.Items.DRAGON_APPLE_MATERIALS))
                .save(output);
        shaped(RecipeCategory.MISC, ModItems.HARDENED_DIAMOND.get())
                .define('O', Blocks.OBSIDIAN)
                .define('D', Items.DIAMOND)
                .pattern("ODO")
                .pattern("DOD")
                .pattern("ODO")
                .unlockedBy(getHasMaterials(ModItems.HARDENED_DIAMOND.get()), has(ModTags.Items.HARDENED_DIAMOND_MATERIALS))
                .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, getItemName(ModItems.HARDENED_DIAMOND.get()) + "_shaped")));
        shaped(RecipeCategory.MISC, ModItems.IMPROVISED_REACTOR.get())
                .define('C', ItemTags.COALS)
                .define('U', ModItems.URANIUM_INGOT.get())
                .define('B', Items.WATER_BUCKET)
                .pattern("C")
                .pattern("U")
                .pattern("B")
                .unlockedBy(getHasMaterials(ModItems.IMPROVISED_REACTOR.get()), has(ModTags.Items.IMPROVISED_REACTOR_MATERIALS))
                .save(output);
        shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_SHIELD.get())
                .define('O', Blocks.OBSIDIAN)
                .define('N', Items.NETHERITE_INGOT)
                .pattern("ONO")
                .pattern("OOO")
                .pattern(" O ")
                .unlockedBy(getHasMaterials(ModItems.OBSIDIAN_SHIELD.get()), has(ModTags.Items.OBSIDIAN_SHIELD_MATERIALS))
                .save(output);
        shaped(RecipeCategory.MISC, ModItems.STEEL_CHUNK.get(), 2)
                .define('C', ItemTags.COALS)
                .define('I', Items.RAW_IRON)
                .pattern("CI")
                .pattern("IC")
                .unlockedBy(getHasMaterials(ModItems.STEEL_CHUNK.get()), has(ModTags.Items.STEEL_CHUNK_MATERIALS))
                .save(output);
        shaped(RecipeCategory.MISC, ModItems.STEEL_CHUNK.get())
                .define('C', ItemTags.COALS)
                .define('I', Items.IRON_NUGGET)
                .pattern("III")
                .pattern("ICI")
                .pattern("III")
                .unlockedBy(getHasMaterials(ModItems.STEEL_CHUNK.get()), has(ModTags.Items.STEEL_CHUNK_MATERIALS))
                .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, getItemName(ModItems.STEEL_CHUNK.get()) + "_2")));
        shaped(RecipeCategory.COMBAT, ModItems.ENDERITE_BOW.get())
                .define('V', ModItems.ENDERITE_INGOT.get())
                .define('S', Items.STRING)
                .pattern(" VS")
                .pattern("V S")
                .pattern(" VS")
                .unlockedBy(getHasMaterials(ModItems.ENDERITE_BOW.get()), has(ModTags.Items.ENDERITE_TOOL_MATERIALS))
                .save(output);
        shaped(RecipeCategory.MISC, ModItems.ENDERITE_INGOT.get())
                .define('V', ModItems.ENDERITE_DUST.get())
                .define('S', ModItems.STEEL_INGOT.get())
                .pattern("VVV")
                .pattern("VSV")
                .pattern("VVV")
                .unlockedBy(getHasMaterials(ModItems.ENDERITE_INGOT.get()), has(ModTags.Items.ENDERITE_INGOT_MATERIALS))
                .save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, getItemName(ModItems.ENDERITE_INGOT.get()) + "_2")));
        shaped(RecipeCategory.MISC, ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE.get(), 2)
                .define('T', ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE.get())
                .define('E', Blocks.END_STONE)
                .define('N', Items.NETHERITE_INGOT)
                .pattern("T")
                .pattern("E")
                .pattern("N")
                .unlockedBy(getHasMaterials(ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE.get()), has(ModTags.Items.ENDERITE_TOOL_MATERIALS))
                .save(output);
        shaped(RecipeCategory.MISC, ModItems.ENDERITE_LEATHER.get())
                .define('V', ModItems.ENDERITE_DUST.get())
                .define('L', Items.LEATHER)
                .pattern("VVV")
                .pattern("VLV")
                .pattern("VVV")
                .unlockedBy(getHasMaterials(ModItems.ENDERITE_LEATHER.get()), has(ModTags.Items.ENDERITE_LEATHER_MATERIALS))
                .save(output);
        shaped(RecipeCategory.MISC, ModItems.SHULKER_BACKPACK.get())
                .define('L', ModItems.ENDERITE_LEATHER.get())
                .define('I', ModItems.ENDERITE_INGOT.get())
                .define('S', Blocks.SHULKER_BOX)
                .define('E', Items.ENDER_EYE)
                .pattern("LSL")
                .pattern("IEI")
                .pattern("LSL")
                .unlockedBy(getHasMaterials(ModItems.SHULKER_BACKPACK.get()), has(ModTags.Items.SHULKER_BACKPACK_MATERIALS))
                .save(output);
        shaped(RecipeCategory.MISC, ModItems.ENDERITE_CHEST_UPGRADE.get())
                .define('S', ModItems.STEEL_INGOT.get())
                .define('V', ModItems.ENDERITE_INGOT.get())
                .pattern("SVS")
                .pattern("S S")
                .pattern("SSS")
                .unlockedBy(getHasMaterials(ModItems.ENDERITE_CHEST_UPGRADE.get()), has(ModTags.Items.ENDERITE_CHEST_UPGRADE_MATERIALS))
                .save(output);
        shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CRYING_OBSIDIAN)
                .define('S', ModItems.OBSIDIAN_SHARD.get())
                .define('O', Blocks.OBSIDIAN)
                .pattern("SSS")
                .pattern("SOS")
                .pattern("SSS")
                .unlockedBy(getHasMaterials(Blocks.CRYING_OBSIDIAN), has(Blocks.OBSIDIAN))
                .save(output);

        /* NINE BLOCK STORAGE */
        nineBlockStorageRecipes(output, RecipeCategory.MISC, ModItems.HARDENED_DIAMOND.get(),
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.HARDENED_DIAMOND_BLOCK.get());
        nineBlockStorageRecipes(output, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(),
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK.get());
        nineBlockStorageRecipes(output, RecipeCategory.MISC, ModItems.ENDERITE_INGOT.get(),
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERITE_BLOCK.get());
        nineBlockStorageRecipes(output, RecipeCategory.MISC, ModItems.OBSIDIAN_SHARD.get(),
                RecipeCategory.BUILDING_BLOCKS, Blocks.OBSIDIAN);

        /* CHESTS */
        shaped(RecipeCategory.MISC, ModBlocks.ENDERITE_CHEST.get())
                .define('S', ModItems.STEEL_INGOT.get())
                .define('V', ModItems.ENDERITE_INGOT.get())
                .define('C', Blocks.CHEST)
                .pattern("SVS")
                .pattern("SCS")
                .pattern("SSS")
                .unlockedBy(getHasMaterials(ModBlocks.ENDERITE_CHEST.get()), has(ModTags.Items.ENDERITE_CHEST_MATERIALS))
                .save(output);

        /* SMELTING & BLASTING */
        oreSmelting(output, List.of(ModBlocks.OBSIDIAN_ORE.get()), RecipeCategory.BUILDING_BLOCKS,
                Blocks.OBSIDIAN, 5.0F, 500, "obsidian");
        oreBlasting(output, List.of(ModBlocks.OBSIDIAN_ORE.get()), RecipeCategory.BUILDING_BLOCKS,
                Blocks.OBSIDIAN, 3.0F, 300, "obsidian");
        oreBlasting(output, List.of(ModItems.STEEL_CHUNK.get()), RecipeCategory.BUILDING_BLOCKS,
                ModItems.STEEL_INGOT.get(), 1.5F, 150, "steel_ingot");
        oreSmelting(output, List.of(ModItems.RAW_URANIUM.get(), ModBlocks.URANIUM_ORE.get(), ModBlocks.DEEPSLATE_URANIUM_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, ModItems.URANIUM_INGOT.get(), 1.0F, 200, "uranium_ingot");
        oreBlasting(output, List.of(ModItems.RAW_URANIUM.get(), ModBlocks.URANIUM_ORE.get(), ModBlocks.DEEPSLATE_URANIUM_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, ModItems.URANIUM_INGOT.get(), 0.75F, 100, "uranium_ingot");
        oreSmelting(output, List.of(ModBlocks.ENDERITE_ORE.get(), ModBlocks.DEEPSLATE_ENDERITE_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, ModItems.ENDERITE_DUST.get(), 3.0F, 180, "enderite_dust");
        oreBlasting(output, List.of(ModBlocks.ENDERITE_ORE.get(), ModBlocks.DEEPSLATE_ENDERITE_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, ModItems.ENDERITE_DUST.get(), 2.0F, 90, "enderite_dust");
        oreSmelting(output, List.of(ModBlocks.XP_ORE.get(), ModBlocks.DEEPSLATE_XP_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, Blocks.STONE, 10.0F, 200, "xp");
        oreBlasting(output, List.of(ModBlocks.XP_ORE.get(), ModBlocks.DEEPSLATE_XP_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, Blocks.STONE, 5.0F, 200, "xp");

        /* TOOLS */
        hammerBuilder(ModItems.WOODEN_HAMMER.get(), ItemTags.WOODEN_TOOL_MATERIALS).save(output);
        hammerBuilder(ModItems.COPPER_HAMMER.get(), ItemTags.COPPER_TOOL_MATERIALS).save(output);
        hammerBuilder(ModItems.STONE_HAMMER.get(), ItemTags.STONE_TOOL_MATERIALS).save(output);
        hammerBuilder(ModItems.GOLDEN_HAMMER.get(), ItemTags.GOLD_TOOL_MATERIALS).save(output);
        hammerBuilder(ModItems.IRON_HAMMER.get(), ItemTags.IRON_TOOL_MATERIALS).save(output);
        hammerBuilder(ModItems.DIAMOND_HAMMER.get(), ItemTags.DIAMOND_TOOL_MATERIALS).save(output);
        
        toolSetRecipes(output, ModTags.Items.STEEL_TOOL_MATERIALS,
                ModItems.STEEL_AXE.get(),
                ModItems.STEEL_HOE.get(),
                ModItems.STEEL_PICKAXE.get(),
                ModItems.STEEL_SHOVEL.get(),
                ModItems.STEEL_HAMMER.get(),
                ModItems.STEEL_SWORD.get());
        toolSetRecipes(output, ModTags.Items.EMERALD_TOOL_MATERIALS,
                ModItems.EMERALD_AXE.get(),
                ModItems.EMERALD_HOE.get(),
                ModItems.EMERALD_PICKAXE.get(),
                ModItems.EMERALD_SHOVEL.get(),
                ModItems.EMERALD_HAMMER.get(),
                ModItems.EMERALD_SWORD.get());
        toolSetRecipes(output, ModTags.Items.H_DIAMOND_TOOL_MATERIALS,
                ModItems.HARDENED_DIAMOND_AXE.get(),
                ModItems.HARDENED_DIAMOND_HOE.get(),
                ModItems.HARDENED_DIAMOND_PICKAXE.get(),
                ModItems.HARDENED_DIAMOND_SHOVEL.get(),
                ModItems.HARDENED_DIAMOND_HAMMER.get(),
                ModItems.HARDENED_DIAMOND_SWORD.get());
        toolSetRecipes(output, ModTags.Items.OBSIDIAN_TOOL_MATERIALS,
                ModItems.OBSIDIAN_AXE.get(),
                ModItems.OBSIDIAN_HOE.get(),
                ModItems.OBSIDIAN_PICKAXE.get(),
                ModItems.OBSIDIAN_SHOVEL.get(),
                ModItems.OBSIDIAN_HAMMER.get(),
                ModItems.OBSIDIAN_SWORD.get());
        
        netheriteSmithing(ModItems.DIAMOND_HAMMER.get(), RecipeCategory.TOOLS, ModItems.NETHERITE_HAMMER.get());

        moltenSmithing(Items.DIAMOND_SHOVEL, RecipeCategory.TOOLS, ModItems.MOLTEN_SHOVEL.get());
        moltenSmithing(Items.DIAMOND_PICKAXE, RecipeCategory.TOOLS, ModItems.MOLTEN_PICKAXE.get());
        moltenSmithing(Items.DIAMOND_AXE, RecipeCategory.TOOLS, ModItems.MOLTEN_AXE.get());
        moltenSmithing(Items.DIAMOND_HOE, RecipeCategory.TOOLS, ModItems.MOLTEN_HOE.get());
        moltenSmithing(ModItems.DIAMOND_HAMMER.get(), RecipeCategory.TOOLS, ModItems.MOLTEN_HAMMER.get());
        moltenSmithing(Items.DIAMOND_SWORD, RecipeCategory.COMBAT, ModItems.MOLTEN_SWORD.get());

        enderiteSmithing(Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, ModItems.ENDERITE_SHOVEL.get());
        enderiteSmithing(Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, ModItems.ENDERITE_PICKAXE.get());
        enderiteSmithing(Items.NETHERITE_AXE, RecipeCategory.TOOLS, ModItems.ENDERITE_AXE.get());
        enderiteSmithing(Items.NETHERITE_HOE, RecipeCategory.TOOLS, ModItems.ENDERITE_HOE.get());
        enderiteSmithing(ModItems.NETHERITE_HAMMER.get(), RecipeCategory.TOOLS, ModItems.ENDERITE_HAMMER.get());
        enderiteSmithing(Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.ENDERITE_SWORD.get());

        /* ARMOR */
        armorSetRecipes(output, ModTags.Items.EMERALD_TOOL_MATERIALS,
                ModItems.EMERALD_HELMET.get(),
                ModItems.EMERALD_CHESTPLATE.get(),
                ModItems.EMERALD_LEGGINGS.get(),
                ModItems.EMERALD_BOOTS.get());
        armorSetRecipes(output, ModTags.Items.H_DIAMOND_TOOL_MATERIALS,
                ModItems.HARDENED_DIAMOND_HELMET.get(),
                ModItems.HARDENED_DIAMOND_CHESTPLATE.get(),
                ModItems.HARDENED_DIAMOND_LEGGINGS.get(),
                ModItems.HARDENED_DIAMOND_BOOTS.get());
        armorSetRecipes(output, ModTags.Items.OBSIDIAN_TOOL_MATERIALS,
                ModItems.OBSIDIAN_HELMET.get(),
                ModItems.OBSIDIAN_CHESTPLATE.get(),
                ModItems.OBSIDIAN_LEGGINGS.get(),
                ModItems.OBSIDIAN_BOOTS.get());
        armorSetRecipes(output, ModTags.Items.STEEL_TOOL_MATERIALS,
                ModItems.STEEL_HELMET.get(),
                ModItems.STEEL_CHESTPLATE.get(),
                ModItems.STEEL_LEGGINGS.get(),
                ModItems.STEEL_BOOTS.get());

        moltenSmithing(Items.DIAMOND_HELMET, RecipeCategory.COMBAT, ModItems.MOLTEN_HELMET.get());
        moltenSmithing(Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, ModItems.MOLTEN_CHESTPLATE.get());
        moltenSmithing(Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, ModItems.MOLTEN_LEGGINGS.get());
        moltenSmithing(Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, ModItems.MOLTEN_BOOTS.get());

        enderiteSmithing(Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.ENDERITE_HELMET.get());
        enderiteSmithing(Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.ENDERITE_CHESTPLATE.get());
        enderiteSmithing(Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.ENDERITE_LEGGINGS.get());
        enderiteSmithing(Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.ENDERITE_BOOTS.get());
    }
    
    @ParametersAreNonnullByDefault
    protected void nineBlockStorageRecipes(RecipeOutput recipeOutput, RecipeCategory unpackedCategory, ItemLike unpacked, RecipeCategory packedCategory, ItemLike packed) {
        nineBlockStorageRecipes(recipeOutput, unpackedCategory, unpacked, packedCategory,
                packed, getSimpleRecipeName(packed), null, getSimpleRecipeName(unpacked), null);
    }

    protected void nineBlockStorageRecipes(RecipeOutput recipeOutput, RecipeCategory unpackedCategory, ItemLike unpacked, RecipeCategory packedCategory, ItemLike packed, String packedName, @Nullable String packedGroup, String unpackedName, @Nullable String unpackedGroup) {
        shapeless(unpackedCategory, unpacked, 9)
                .requires(packed)
                .group(unpackedGroup)
                .unlockedBy(getHasName(packed), has(packed))
                .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, unpackedName)));
        shaped(packedCategory, packed)
                .define('#', unpacked)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(packedGroup)
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(recipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, packedName)));
    }

    @ParametersAreNonnullByDefault
    protected void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category,
                result, experience, cookingTime, group, "_from_smelting");
    }

    @ParametersAreNonnullByDefault
    protected void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category,
                result, experience, cookingTime, group, "_from_blasting");
    }

    protected <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> pFactory,
                                                                @NotNull List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(
                    Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, pFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike))));
        }
    }

    protected void moltenSmithing(Item ingredientItem, RecipeCategory category, Item resultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ingredientItem),
                        this.tag(ModTags.Items.MOLTEN_TOOL_MATERIALS),
                        category,
                        resultItem
                )
                .unlocks(getHasName(ModItems.MOLTEN_INGOT), this.has(ModTags.Items.MOLTEN_TOOL_MATERIALS))
                .save(this.output, getItemName(resultItem) + "_smithing");
    }

    protected void enderiteSmithing(Item ingredientItem, RecipeCategory category, Item resultItem) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.ENDERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ingredientItem),
                        this.tag(ModTags.Items.ENDERITE_TOOL_MATERIALS),
                        category,
                        resultItem
                )
                .unlocks(getHasName(ModItems.ENDERITE_INGOT), this.has(ModTags.Items.ENDERITE_TOOL_MATERIALS))
                .save(this.output, getItemName(resultItem) + "_smithing");
    }

    protected void toolSetRecipes(RecipeOutput recipeOutput, TagKey<Item> material,
                                  ItemLike axe, ItemLike hoe, ItemLike pickaxe, ItemLike shovel, ItemLike hammer, ItemLike sword) {
        axeBuilder(axe, material).save(recipeOutput);
        hoeBuilder(hoe, material).save(recipeOutput);
        pickaxeBuilder(pickaxe, material).save(recipeOutput);
        shovelBuilder(shovel, material).save(recipeOutput);
        hammerBuilder(hammer, material).save(recipeOutput);
        swordBuilder(sword, material).save(recipeOutput);
    }

    protected void armorSetRecipes(RecipeOutput recipeOutput, TagKey<Item> material,
                                   ItemLike helmet, ItemLike chestplate, ItemLike leggings, ItemLike boots) {
        helmetBuilder(helmet, material).save(recipeOutput);
        chestplateBuilder(chestplate, material).save(recipeOutput);
        leggingsBuilder(leggings, material).save(recipeOutput);
        bootsBuilder(boots, material).save(recipeOutput);
    }

    protected RecipeBuilder axeBuilder(ItemLike axe, TagKey<Item> material) {
        return shaped(RecipeCategory.TOOLS, axe)
                .define('#', material)
                .define('S', Items.STICK)
                .pattern("##")
                .pattern("#S")
                .pattern(" S")
                .unlockedBy(getHasName(material), has(material));
    }

    protected RecipeBuilder hoeBuilder(ItemLike hoe, TagKey<Item> material) {
        return shaped(RecipeCategory.TOOLS, hoe)
                .define('#', material)
                .define('S', Items.STICK)
                .pattern("##")
                .pattern(" S")
                .pattern(" S")
                .unlockedBy(getHasName(material), has(material));
    }

    protected RecipeBuilder pickaxeBuilder(ItemLike pickaxe, TagKey<Item> material) {
        return shaped(RecipeCategory.TOOLS, pickaxe)
                .define('#', material)
                .define('S', Items.STICK)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy(getHasName(material), has(material));
    }

    protected RecipeBuilder shovelBuilder(ItemLike shovel, TagKey<Item> material) {
        return shaped(RecipeCategory.TOOLS, shovel)
                .define('#', material)
                .define('S', Items.STICK)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .unlockedBy(getHasName(material), has(material));
    }

    protected RecipeBuilder hammerBuilder(ItemLike hammer, TagKey<Item> material) {
        return shaped(RecipeCategory.TOOLS, hammer)
                .define('#', material)
                .define('S', Items.STICK)
                .pattern("###")
                .pattern("###")
                .pattern(" S ")
                .unlockedBy(getHasName(material), has(material));
    }

    protected RecipeBuilder swordBuilder(ItemLike sword, TagKey<Item> material) {
        return shaped(RecipeCategory.COMBAT, sword)
                .define('#', material)
                .define('S', Items.STICK)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .unlockedBy(getHasName(material), has(material));
    }

    protected RecipeBuilder helmetBuilder(ItemLike helmet, TagKey<Item> material) {
        return shaped(RecipeCategory.COMBAT, helmet)
                .define('#', material)
                .pattern("###")
                .pattern("# #")
                .unlockedBy(getHasName(material), has(material));
    }

    protected RecipeBuilder chestplateBuilder(ItemLike chestplate, TagKey<Item> material) {
        return shaped(RecipeCategory.COMBAT, chestplate)
                .define('#', material)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(material), has(material));
    }

    protected RecipeBuilder leggingsBuilder(ItemLike leggings, TagKey<Item> material) {
        return shaped(RecipeCategory.COMBAT, leggings)
                .define('#', material)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(material), has(material));
    }

    protected RecipeBuilder bootsBuilder(ItemLike boots, TagKey<Item> material) {
        return shaped(RecipeCategory.COMBAT, boots)
                .define('#', material)
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(material), has(material));
    }

    protected static String getHasMaterials(ItemLike item) {
        return getHasName(item) + "_materials";
    }
    
    protected static String getHasName(TagKey<?> tagKey) {
        return "has_" + tagKey.location().getPath();
    }
}
