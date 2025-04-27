package de.thedon.oresandtools.datagen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput pRecipeOutput) {
        /* NORMAL ITEMS */
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.DRAGONS_APPLE.get())
                .define('V', ModItems.VALYRIAN_DUST.get())
                .define('O', ModItems.OBSIDIAN_SHARD.get())
                .define('A', Items.APPLE)
                .pattern("VOV")
                .pattern("OAO")
                .pattern("VOV")
                .unlockedBy(getHasName(ModItems.DRAGONS_APPLE.get()), has(ModTags.Items.DRAGON_APPLE_MATERIALS))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HARDENED_DIAMOND.get())
                .define('O', Blocks.OBSIDIAN)
                .define('D', Items.DIAMOND)
                .pattern("ODO")
                .pattern("DOD")
                .pattern("ODO")
                .unlockedBy(getHasName(ModItems.HARDENED_DIAMOND.get()), has(ModTags.Items.HARDENED_DIAMOND_MATERIALS))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, getItemName(ModItems.HARDENED_DIAMOND.get()) + "_shaped"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IMPROVISED_REACTOR.get())
                .define('C', ItemTags.COALS)
                .define('U', ModItems.URANIUM_INGOT.get())
                .define('B', Items.WATER_BUCKET)
                .pattern("C")
                .pattern("U")
                .pattern("B")
                .unlockedBy(getHasName(ModItems.IMPROVISED_REACTOR.get()), has(ModTags.Items.IMPROVISED_REACTOR_MATERIALS))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_SHIELD.get())
                .define('O', Blocks.OBSIDIAN)
                .define('D', Items.DIAMOND)
                .pattern("ODO")
                .pattern("OOO")
                .pattern(" O ")
                .unlockedBy(getHasName(ModItems.OBSIDIAN_SHIELD.get()), has(ModTags.Items.OBSIDIAN_SHIELD_MATERIALS))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_CHUNK.get())
                .define('C', ItemTags.COALS)
                .define('I', Items.RAW_IRON)
                .pattern("CI")
                .pattern("IC")
                .unlockedBy(getHasName(ModItems.STEEL_CHUNK.get()), has(ModTags.Items.STEEL_CHUNK_MATERIALS))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_CHUNK.get())
                .define('C', ItemTags.COALS)
                .define('I', Items.IRON_NUGGET)
                .pattern("III")
                .pattern("ICI")
                .pattern("III")
                .unlockedBy(getHasName(ModItems.STEEL_CHUNK.get()), has(ModTags.Items.STEEL_CHUNK_MATERIALS))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, getItemName(ModItems.STEEL_CHUNK.get()) + "_2"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.VALYRIAN_BOW.get())
                .define('V', ModItems.VALYRIAN_INGOT.get())
                .define('S', Items.STRING)
                .pattern(" VS")
                .pattern("V S")
                .pattern(" VS")
                .unlockedBy(getHasName(ModItems.VALYRIAN_BOW.get()), has(ModItems.VALYRIAN_DUST.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VALYRIAN_INGOT.get())
                .define('V', ModItems.VALYRIAN_DUST.get())
                .define('S', ModItems.STEEL_INGOT.get())
                .pattern("VVV")
                .pattern("VSV")
                .pattern("VVV")
                .unlockedBy(getHasName(ModItems.VALYRIAN_INGOT.get()), has(ModTags.Items.VALYRIAN_INGOT_MATERIALS))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.VALYRIAN_LEATHER.get())
                .define('V', ModItems.VALYRIAN_DUST.get())
                .define('L', Items.LEATHER)
                .pattern("VVV")
                .pattern("VLV")
                .pattern("VVV")
                .unlockedBy(getHasName(ModItems.VALYRIAN_LEATHER.get()), has(ModTags.Items.VALYRIAN_LEATHER_MATERIALS))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SHULKER_BACKPACK.get())
                .define('L', ModItems.VALYRIAN_LEATHER.get())
                .define('I', ModItems.VALYRIAN_INGOT.get())
                .define('S', Blocks.SHULKER_BOX)
                .define('E', Items.ENDER_EYE)
                .pattern("LSL")
                .pattern("IEI")
                .pattern("LSL")
                .unlockedBy(getHasName(ModItems.SHULKER_BACKPACK.get()), has(ModTags.Items.SHULKER_BACKPACK_MATERIALS))
                .save(pRecipeOutput);

        /* NINE BLOCK STORAGE */
        nineBlockStorageRecipes(pRecipeOutput, RecipeCategory.MISC, ModItems.HARDENED_DIAMOND.get(),
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.HARDENED_DIAMOND_BLOCK.get());
        nineBlockStorageRecipes(pRecipeOutput, RecipeCategory.MISC, ModItems.STEEL_INGOT.get(),
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_BLOCK.get());
        nineBlockStorageRecipes(pRecipeOutput, RecipeCategory.MISC, ModItems.OBSIDIAN_SHARD.get(),
                RecipeCategory.BUILDING_BLOCKS, Blocks.OBSIDIAN);

        /* SMELTING & BLASTING */
        oreSmelting(pRecipeOutput, List.of(ModBlocks.OBSIDIAN_ORE.get()), RecipeCategory.BUILDING_BLOCKS,
                Blocks.OBSIDIAN, 5.0F, 500, "obsidian");
        oreBlasting(pRecipeOutput, List.of(ModBlocks.OBSIDIAN_ORE.get()), RecipeCategory.BUILDING_BLOCKS,
                Blocks.OBSIDIAN, 3.0F, 300, "obsidian");
        oreBlasting(pRecipeOutput, List.of(ModItems.STEEL_CHUNK.get()), RecipeCategory.BUILDING_BLOCKS,
                ModItems.STEEL_INGOT.get(), 1.5F, 150, "steel_ingot");
        oreSmelting(pRecipeOutput, List.of(ModItems.RAW_URANIUM.get(), ModBlocks.URANIUM_ORE.get(), ModBlocks.DEEPSLATE_URANIUM_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, ModItems.URANIUM_INGOT.get(), 1.0F, 200, "uranium_ingot");
        oreBlasting(pRecipeOutput, List.of(ModItems.RAW_URANIUM.get(), ModBlocks.URANIUM_ORE.get(), ModBlocks.DEEPSLATE_URANIUM_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, ModItems.URANIUM_INGOT.get(), 0.75F, 100, "uranium_ingot");
        oreSmelting(pRecipeOutput, List.of(ModBlocks.VALYRIAN_ORE.get(), ModBlocks.DEEPSLATE_VALYRIAN_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, ModItems.VALYRIAN_DUST.get(), 3.0F, 180, "valyrian_dust");
        oreBlasting(pRecipeOutput, List.of(ModBlocks.VALYRIAN_ORE.get(), ModBlocks.DEEPSLATE_VALYRIAN_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, ModItems.VALYRIAN_DUST.get(), 2.0F, 90, "valyrian_dust");
        oreSmelting(pRecipeOutput, List.of(ModBlocks.XP_ORE.get(), ModBlocks.DEEPSLATE_XP_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, Blocks.STONE, 10.0F, 200, "xp");
        oreBlasting(pRecipeOutput, List.of(ModBlocks.XP_ORE.get(), ModBlocks.DEEPSLATE_XP_ORE.get()),
                RecipeCategory.BUILDING_BLOCKS, Blocks.STONE, 5.0F, 200, "xp");

        /* TOOLS */
        toolSetRecipes(pRecipeOutput, Items.COPPER_INGOT,
                ModItems.COPPER_AXE.get(),
                ModItems.COPPER_HOE.get(),
                ModItems.COPPER_PICKAXE.get(),
                ModItems.COPPER_SHOVEL.get(),
                ModItems.COPPER_SWORD.get());
        toolSetRecipes(pRecipeOutput, Items.EMERALD,
                ModItems.EMERALD_AXE.get(),
                ModItems.EMERALD_HOE.get(),
                ModItems.EMERALD_PICKAXE.get(),
                ModItems.EMERALD_SHOVEL.get(),
                ModItems.EMERALD_SWORD.get());
        toolSetRecipes(pRecipeOutput, ModItems.HARDENED_DIAMOND.get(),
                ModItems.HARDENED_DIAMOND_AXE.get(),
                ModItems.HARDENED_DIAMOND_HOE.get(),
                ModItems.HARDENED_DIAMOND_PICKAXE.get(),
                ModItems.HARDENED_DIAMOND_SHOVEL.get(),
                ModItems.HARDENED_DIAMOND_SWORD.get());
        toolSetRecipes(pRecipeOutput, ModItems.HOT_HARDENED_DIAMOND.get(),
                ModItems.HOT_HARDENED_DIAMOND_AXE.get(),
                ModItems.HOT_HARDENED_DIAMOND_HOE.get(),
                ModItems.HOT_HARDENED_DIAMOND_PICKAXE.get(),
                ModItems.HOT_HARDENED_DIAMOND_SHOVEL.get(),
                ModItems.HOT_HARDENED_DIAMOND_SWORD.get());
        toolSetRecipes(pRecipeOutput, Blocks.OBSIDIAN.asItem(),
                ModItems.OBSIDIAN_AXE.get(),
                ModItems.OBSIDIAN_HOE.get(),
                ModItems.OBSIDIAN_PICKAXE.get(),
                ModItems.OBSIDIAN_SHOVEL.get(),
                ModItems.OBSIDIAN_SWORD.get());
        toolSetRecipes(pRecipeOutput, ModItems.STEEL_INGOT.get(),
                ModItems.STEEL_AXE.get(),
                ModItems.STEEL_HOE.get(),
                ModItems.STEEL_PICKAXE.get(),
                ModItems.STEEL_SHOVEL.get(),
                ModItems.STEEL_SWORD.get());
        toolSetRecipes(pRecipeOutput, ModItems.VALYRIAN_INGOT.get(),
                ModItems.VALYRIAN_AXE.get(),
                ModItems.VALYRIAN_HOE.get(),
                ModItems.VALYRIAN_PICKAXE.get(),
                ModItems.VALYRIAN_SHOVEL.get(),
                ModItems.VALYRIAN_SWORD.get());

        /* ARMOR */
        armorSetRecipes(pRecipeOutput, Items.COPPER_INGOT,
                ModItems.COPPER_HELMET.get(),
                ModItems.COPPER_CHESTPLATE.get(),
                ModItems.COPPER_LEGGINGS.get(),
                ModItems.COPPER_BOOTS.get());
        armorSetRecipes(pRecipeOutput, Items.EMERALD,
                ModItems.EMERALD_HELMET.get(),
                ModItems.EMERALD_CHESTPLATE.get(),
                ModItems.EMERALD_LEGGINGS.get(),
                ModItems.EMERALD_BOOTS.get());
        armorSetRecipes(pRecipeOutput, ModItems.HARDENED_DIAMOND.get(),
                ModItems.HARDENED_DIAMOND_HELMET.get(),
                ModItems.HARDENED_DIAMOND_CHESTPLATE.get(),
                ModItems.HARDENED_DIAMOND_LEGGINGS.get(),
                ModItems.HARDENED_DIAMOND_BOOTS.get());
        armorSetRecipes(pRecipeOutput, ModItems.HOT_HARDENED_DIAMOND.get(),
                ModItems.HOT_HARDENED_DIAMOND_HELMET.get(),
                ModItems.HOT_HARDENED_DIAMOND_CHESTPLATE.get(),
                ModItems.HOT_HARDENED_DIAMOND_LEGGINGS.get(),
                ModItems.HOT_HARDENED_DIAMOND_BOOTS.get());
        armorSetRecipes(pRecipeOutput, Items.OBSIDIAN,
                ModItems.OBSIDIAN_HELMET.get(),
                ModItems.OBSIDIAN_CHESTPLATE.get(),
                ModItems.OBSIDIAN_LEGGINGS.get(),
                ModItems.OBSIDIAN_BOOTS.get());
        armorSetRecipes(pRecipeOutput, ModItems.STEEL_INGOT.get(),
                ModItems.STEEL_HELMET.get(),
                ModItems.STEEL_CHESTPLATE.get(),
                ModItems.STEEL_LEGGINGS.get(),
                ModItems.STEEL_BOOTS.get());
        armorSetRecipes(pRecipeOutput, ModItems.VALYRIAN_INGOT.get(),
                ModItems.VALYRIAN_HELMET.get(),
                ModItems.VALYRIAN_CHESTPLATE.get(),
                ModItems.VALYRIAN_LEGGINGS.get(),
                ModItems.VALYRIAN_BOOTS.get());
    }
    
    @ParametersAreNonnullByDefault
    protected static void nineBlockStorageRecipes(RecipeOutput pRecipeOutput, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked) {
        nineBlockStorageRecipes(pRecipeOutput, pUnpackedCategory, pUnpacked, pPackedCategory,
                pPacked, getSimpleRecipeName(pPacked), (String)null, getSimpleRecipeName(pUnpacked), (String)null);
    }

    protected static void nineBlockStorageRecipes(RecipeOutput pRecipeOutput, RecipeCategory pUnpackedCategory, ItemLike pUnpacked, RecipeCategory pPackedCategory, ItemLike pPacked, String pPackedName, @Nullable String pPackedGroup, String pUnpackedName, @Nullable String pUnpackedGroup) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 9)
                .requires(pPacked)
                .group(pUnpackedGroup)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, pUnpackedName));
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked)
                .define('#', pUnpacked)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(pPackedGroup)
                .unlockedBy(getHasName(pUnpacked), has(pUnpacked))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, pPackedName));
    }

    @ParametersAreNonnullByDefault
    protected static void oreSmelting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory,
                pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    @ParametersAreNonnullByDefault
    protected static void oreBlasting(RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory,
                pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> pFactory,
                                                                       @NotNull List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(
                    Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, pFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike)));
        }
    }

    protected static void toolSetRecipes(RecipeOutput pRecipeOutput, Item pMaterial,
                                         ItemLike pAxe, ItemLike pHoe, ItemLike pPickaxe, ItemLike pShovel, ItemLike pSword) {
        axeBuilder(pAxe, pMaterial).save(pRecipeOutput);
        hoeBuilder(pHoe, pMaterial).save(pRecipeOutput);
        pickaxeBuilder(pPickaxe, pMaterial).save(pRecipeOutput);
        shovelBuilder(pShovel, pMaterial).save(pRecipeOutput);
        swordBuilder(pSword, pMaterial).save(pRecipeOutput);
    }

    protected static void armorSetRecipes(RecipeOutput pRecipeOutput, Item pMaterial,
                                         ItemLike pHelmet, ItemLike pChestplate, ItemLike pLeggings, ItemLike pBoots) {
        helmetBuilder(pHelmet, pMaterial).save(pRecipeOutput);
        chestplateBuilder(pChestplate, pMaterial).save(pRecipeOutput);
        leggingsBuilder(pLeggings, pMaterial).save(pRecipeOutput);
        bootsBuilder(pBoots, pMaterial).save(pRecipeOutput);
    }

    protected static RecipeBuilder axeBuilder(ItemLike pAxe, Item pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pAxe)
                .define('#', pMaterial)
                .define('S', Items.STICK)
                .pattern("##")
                .pattern("#S")
                .pattern(" S")
                .unlockedBy(getHasName(pMaterial), has(pMaterial));
    }

    protected static RecipeBuilder hoeBuilder(ItemLike pHoe, Item pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pHoe)
                .define('#', pMaterial)
                .define('S', Items.STICK)
                .pattern("##")
                .pattern(" S")
                .pattern(" S")
                .unlockedBy(getHasName(pMaterial), has(pMaterial));
    }

    protected static RecipeBuilder pickaxeBuilder(ItemLike pPickaxe, Item pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pPickaxe)
                .define('#', pMaterial)
                .define('S', Items.STICK)
                .pattern("###")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy(getHasName(pMaterial), has(pMaterial));
    }

    protected static RecipeBuilder shovelBuilder(ItemLike pShovel, Item pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pShovel)
                .define('#', pMaterial)
                .define('S', Items.STICK)
                .pattern("#")
                .pattern("S")
                .pattern("S")
                .unlockedBy(getHasName(pMaterial), has(pMaterial));
    }

    protected static RecipeBuilder swordBuilder(ItemLike pSword, Item pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pSword)
                .define('#', pMaterial)
                .define('S', Items.STICK)
                .pattern("#")
                .pattern("#")
                .pattern("S")
                .unlockedBy(getHasName(pMaterial), has(pMaterial));
    }

    protected static RecipeBuilder helmetBuilder(ItemLike pHelmet, Item pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pHelmet)
                .define('#', pMaterial)
                .pattern("###")
                .pattern("# #")
                .unlockedBy(getHasName(pMaterial), has(pMaterial));
    }

    protected static RecipeBuilder chestplateBuilder(ItemLike pChestplate, Item pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pChestplate)
                .define('#', pMaterial)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(pMaterial), has(pMaterial));
    }

    protected static RecipeBuilder leggingsBuilder(ItemLike pLeggings, Item pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pLeggings)
                .define('#', pMaterial)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(pMaterial), has(pMaterial));
    }

    protected static RecipeBuilder bootsBuilder(ItemLike pBoots, Item pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pBoots)
                .define('#', pMaterial)
                .pattern("# #")
                .pattern("# #")
                .unlockedBy(getHasName(pMaterial), has(pMaterial));
    }
}
