package de.thedon.oresandtools.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

import java.util.List;
import java.util.Optional;

public class ModShieldItem extends ShieldItem {

    public ModShieldItem(int durability, TagKey<Item> repairItems, Properties properties) {
        this(properties
                .durability(durability)
                .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                .repairable(repairItems)
                .equippableUnswappable(EquipmentSlot.OFFHAND)
                .component(DataComponents.BLOCKS_ATTACKS, new BlocksAttacks(
                        1.0F,
                        0.9F,
                        List.of(new BlocksAttacks.DamageReduction(
                                100.0F,
                                Optional.empty(),
                                0.0F,
                                1.0F)),
                        new BlocksAttacks.ItemDamageFunction(
                                6.0F,
                                1.0F,
                                0.6F),
                        Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                        Optional.of(SoundEvents.SHIELD_BLOCK),
                        Optional.of(SoundEvents.SHIELD_BREAK)))
                .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK));
    }

    public ModShieldItem(Properties properties) {
        super(properties);
    }
}
