package de.thedon.oresandtools.datagen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OresAndToolsMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        /* NORMAL ITEMS */
        basicItem(ModItems.HARDENED_DIAMOND.get());
        basicItem(ModItems.HEATING_HARDENED_DIAMOND_1.get());
        basicItem(ModItems.HEATING_HARDENED_DIAMOND_2.get());
        basicItem(ModItems.HEATING_HARDENED_DIAMOND_3.get());
        basicItem(ModItems.HOT_HARDENED_DIAMOND.get());
        basicItem(ModItems.VALYRIAN_DUST.get());
        basicItem(ModItems.VALYRIAN_INGOT.get());
        basicItem(ModItems.OBSIDIAN_SHARD.get());
        basicItem(ModItems.DRAGONS_APPLE.get());
        basicItem(ModItems.STEEL_CHUNK.get());
        basicItem(ModItems.STEEL_INGOT.get());
        basicItem(ModItems.RAW_URANIUM.get());
        basicItem(ModItems.URANIUM_INGOT.get());
        basicItem(ModItems.IMPROVISED_REACTOR.get());
        basicItem(ModItems.VALYRIAN_LEATHER.get());
        basicItem(ModItems.SHULKER_BACKPACK.get());
        /* TOOLS */
        handheldItem(ModItems.COPPER_SHOVEL);
        handheldItem(ModItems.COPPER_PICKAXE);
        handheldItem(ModItems.COPPER_AXE);
        handheldItem(ModItems.COPPER_HOE);
        handheldItem(ModItems.COPPER_SWORD);
        handheldItem(ModItems.STEEL_SHOVEL);
        handheldItem(ModItems.STEEL_PICKAXE);
        handheldItem(ModItems.STEEL_AXE);
        handheldItem(ModItems.STEEL_HOE);
        handheldItem(ModItems.STEEL_SWORD);
        handheldItem(ModItems.VALYRIAN_SHOVEL);
        handheldItem(ModItems.VALYRIAN_PICKAXE);
        handheldItem(ModItems.VALYRIAN_AXE);
        handheldItem(ModItems.VALYRIAN_HOE);
        handheldItem(ModItems.VALYRIAN_SWORD);
        handheldItem(ModItems.HARDENED_DIAMOND_SHOVEL);
        handheldItem(ModItems.HARDENED_DIAMOND_PICKAXE);
        handheldItem(ModItems.HARDENED_DIAMOND_AXE);
        handheldItem(ModItems.HARDENED_DIAMOND_HOE);
        handheldItem(ModItems.HARDENED_DIAMOND_SWORD);
        handheldItem(ModItems.HOT_HARDENED_DIAMOND_SHOVEL);
        handheldItem(ModItems.HOT_HARDENED_DIAMOND_PICKAXE);
        handheldItem(ModItems.HOT_HARDENED_DIAMOND_AXE);
        handheldItem(ModItems.HOT_HARDENED_DIAMOND_HOE);
        handheldItem(ModItems.HOT_HARDENED_DIAMOND_SWORD);
        handheldItem(ModItems.EMERALD_SHOVEL);
        handheldItem(ModItems.EMERALD_PICKAXE);
        handheldItem(ModItems.EMERALD_AXE);
        handheldItem(ModItems.EMERALD_HOE);
        handheldItem(ModItems.EMERALD_SWORD);
        handheldItem(ModItems.OBSIDIAN_SHOVEL);
        handheldItem(ModItems.OBSIDIAN_PICKAXE);
        handheldItem(ModItems.OBSIDIAN_AXE);
        handheldItem(ModItems.OBSIDIAN_HOE);
        handheldItem(ModItems.OBSIDIAN_SWORD);
        /* ARMOR */
        trimmedArmorItem(ModItems.COPPER_HELMET);
        trimmedArmorItem(ModItems.COPPER_CHESTPLATE);
        trimmedArmorItem(ModItems.COPPER_LEGGINGS);
        trimmedArmorItem(ModItems.COPPER_BOOTS);
        trimmedArmorItem(ModItems.STEEL_HELMET);
        trimmedArmorItem(ModItems.STEEL_CHESTPLATE);
        trimmedArmorItem(ModItems.STEEL_LEGGINGS);
        trimmedArmorItem(ModItems.STEEL_BOOTS);
        trimmedArmorItem(ModItems.VALYRIAN_HELMET);
        trimmedArmorItem(ModItems.VALYRIAN_CHESTPLATE);
        trimmedArmorItem(ModItems.VALYRIAN_LEGGINGS);
        trimmedArmorItem(ModItems.VALYRIAN_BOOTS);
        trimmedArmorItem(ModItems.HARDENED_DIAMOND_HELMET);
        trimmedArmorItem(ModItems.HARDENED_DIAMOND_CHESTPLATE);
        trimmedArmorItem(ModItems.HARDENED_DIAMOND_LEGGINGS);
        trimmedArmorItem(ModItems.HARDENED_DIAMOND_BOOTS);
        trimmedArmorItem(ModItems.HOT_HARDENED_DIAMOND_HELMET);
        trimmedArmorItem(ModItems.HOT_HARDENED_DIAMOND_CHESTPLATE);
        trimmedArmorItem(ModItems.HOT_HARDENED_DIAMOND_LEGGINGS);
        trimmedArmorItem(ModItems.HOT_HARDENED_DIAMOND_BOOTS);
        trimmedArmorItem(ModItems.EMERALD_HELMET);
        trimmedArmorItem(ModItems.EMERALD_CHESTPLATE);
        trimmedArmorItem(ModItems.EMERALD_LEGGINGS);
        trimmedArmorItem(ModItems.EMERALD_BOOTS);
        trimmedArmorItem(ModItems.OBSIDIAN_HELMET);
        trimmedArmorItem(ModItems.OBSIDIAN_CHESTPLATE);
        trimmedArmorItem(ModItems.OBSIDIAN_LEGGINGS);
        trimmedArmorItem(ModItems.OBSIDIAN_BOOTS);
    }

    @SuppressWarnings("UnusedReturnValue")
    public @NotNull ItemModelBuilder handheldItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(), ResourceLocation.parse("item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(
                        OresAndToolsMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(DeferredItem<Item> itemRegistryObject) {
        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
}
