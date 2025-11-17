package de.thedon.oresandtools.item.equipment;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;

public interface ModEquipmentAssets {
    ResourceKey<EquipmentAsset> STEEL = createId("steel");
    ResourceKey<EquipmentAsset> ENDERITE = createId("enderite");
    ResourceKey<EquipmentAsset> H_DIAMOND = createId("hardened_diamond");
    ResourceKey<EquipmentAsset> MOLTEN = createId("molten");
    ResourceKey<EquipmentAsset> EMERALD = createId("emerald");
    ResourceKey<EquipmentAsset> OBSIDIAN = createId("obsidian");

    static ResourceKey<EquipmentAsset> createId(String id) {
        return ResourceKey.create(
                ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("equipment_asset")),
                ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, id));
    }
}
