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
        super(builder.repairable(repairItem));
        this.repairItem = repairItem;
        this.damageMultiplier = damageMultiplier;
        this.useDuration = useDuration;
        this.range = range;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            ItemStack itemstack = player.getProjectile(stack);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                int i = this.getUseDuration(stack, entity) - timeLeft;
                i = EventHooks.onArrowLoose(stack, level, player, i, !itemstack.isEmpty());
                if (i < 0) {
                    return false;
                } else {
                    float f = getPowerForTime(i) * damageMultiplier;
                    if ((double)f < 0.1) {
                        return false;
                    } else {
                        List<ItemStack> list = draw(stack, itemstack, player);
                        if (level instanceof ServerLevel serverlevel) {
                            if (!list.isEmpty()) {
                                this.shoot(serverlevel, player, player.getUsedItemHand(), stack, list, f * 3.0F, 1.0F, f == 1.0F, null);
                            }
                        }

                        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                        player.awardStat(Stats.ITEM_USED.get(this));
                        return true;
                    }
                }
            }
        }

        return false;
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
