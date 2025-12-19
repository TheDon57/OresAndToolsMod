package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class ModSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting DESCRIPTION_FORMAT;
    private static final Component ENDERITE_UPGRADE_APPLIES_TO;
    private static final Component ENDERITE_UPGRADE_INGREDIENTS;
    private static final Component ENDERITE_UPGRADE_BASE_SLOT_DESCRIPTION;
    private static final Component ENDERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION;
    private static final ResourceLocation EMPTY_SLOT_HELMET;
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE;
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS;
    private static final ResourceLocation EMPTY_SLOT_BOOTS;
    private static final ResourceLocation EMPTY_SLOT_HOE;
    private static final ResourceLocation EMPTY_SLOT_AXE;
    private static final ResourceLocation EMPTY_SLOT_SWORD;
    private static final ResourceLocation EMPTY_SLOT_SHOVEL;
    private static final ResourceLocation EMPTY_SLOT_PICKAXE;
    private static final ResourceLocation EMPTY_SLOT_INGOT;
    
    public ModSmithingTemplateItem(Component appliesTo, Component ingredients, Component baseSlotDescription, Component additionsSlotDescription, List<ResourceLocation> baseSlotEmptyIcons, List<ResourceLocation> additionalSlotEmptyIcons, Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, baseSlotEmptyIcons, additionalSlotEmptyIcons, properties);
    }

    public static ModSmithingTemplateItem createEnderiteUpgradeTemplate(Item.Properties properties) {
        return new ModSmithingTemplateItem(ENDERITE_UPGRADE_APPLIES_TO, ENDERITE_UPGRADE_INGREDIENTS, ENDERITE_UPGRADE_BASE_SLOT_DESCRIPTION, ENDERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createEnderiteUpgradeIconList(), createEnderiteUpgradeMaterialList(), properties);
    }

    private static List<ResourceLocation> createEnderiteUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createEnderiteUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
    
    static {
        DESCRIPTION_FORMAT = ChatFormatting.BLUE;
        ENDERITE_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID,"smithing_template.enderite_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
        ENDERITE_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID,"smithing_template.enderite_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
        ENDERITE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID,"smithing_template.enderite_upgrade.base_slot_description")));
        ENDERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID,"smithing_template.enderite_upgrade.additions_slot_description")));
        EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("container/slot/helmet");
        EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("container/slot/chestplate");
        EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("container/slot/leggings");
        EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("container/slot/boots");
        EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("container/slot/hoe");
        EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("container/slot/axe");
        EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("container/slot/sword");
        EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("container/slot/shovel");
        EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("container/slot/pickaxe");
        EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("container/slot/ingot");
    }
}
