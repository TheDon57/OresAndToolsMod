package de.thedon.oresandtools.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ModBowItem extends BowItem {
    Item repairItem;
    float damageMultiplier;
    // default == 72000
    int useDuration;
    // default == 15
    int range;

    public ModBowItem(Item repairItem, float damageMultiplier, int useDuration, int range, Properties builder) {
        super(builder);
        this.repairItem = repairItem;
        this.damageMultiplier = damageMultiplier;
        this.useDuration = useDuration;
        this.range = range;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isValidRepairItem(ItemStack pStack, ItemStack pRepairCandidate) {
        return (repairItem == pRepairCandidate.getItem()) || super.isValidRepairItem(pStack, pRepairCandidate);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player player) {
            ItemStack itemstack = player.getProjectile(pStack);
            if (!itemstack.isEmpty()) {
                int i = this.getUseDuration(pStack, pEntityLiving) - pTimeLeft;
                i = EventHooks.onArrowLoose(pStack, pLevel, player, i, true);
                if (i < 0) return;

                float f = getPowerForTime(i) * damageMultiplier;
                if (!((double)f < 0.1)) {
                    List<ItemStack> list = draw(pStack, itemstack, player);
                    if (pLevel instanceof ServerLevel serverlevel && !list.isEmpty()) {
                        this.shoot(serverlevel, player, player.getUsedItemHand(), pStack, list, f * 3.0F, 1.0F, f == 1.0F, null);
                    }

                    pLevel.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );
                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public int getUseDuration(ItemStack pStack, LivingEntity pEntity) {
        return useDuration;
    }

    @Override
    public int getDefaultProjectileRange() {
        return range;
    }
}
