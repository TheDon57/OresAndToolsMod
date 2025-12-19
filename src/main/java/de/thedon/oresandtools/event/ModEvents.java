package de.thedon.oresandtools.event;

import de.thedon.oresandtools.Config;
import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.item.custom.HammerItem;
import de.thedon.oresandtools.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.TriState;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.*;

public class ModEvents {
    @EventBusSubscriber(modid = OresAndToolsMod.MOD_ID)
    public static class NeoForgeEvents {
        private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

        @SubscribeEvent
        public static void onHammerUsage(BlockEvent.BreakEvent event) {
            Player player = event.getPlayer();
            ItemStack mainHandItem = player.getMainHandItem();

            if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
                BlockPos initialBlockPos = event.getPos();
                if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                    return;
                }

                for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                    if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                        continue;
                    }

                    HARVESTED_BLOCKS.add(pos);
                    serverPlayer.gameMode.destroyBlock(pos);
                    HARVESTED_BLOCKS.remove(pos);
                }
            }
        }

        @SubscribeEvent
        public static void onLivingShieldBlocked(LivingShieldBlockEvent event) {
            if (event.getEntity() instanceof Player player) {
                ItemStack useItem = player.getUseItem();
                if (player.isBlocking() && (useItem.getItem() == ModItems.OBSIDIAN_SHIELD.get())) {
                    DamageSource source = event.getDamageSource();
                    if (source.is(DamageTypes.ARROW) || source.is(DamageTypes.EXPLOSION) || source.is(DamageTypes.MAGIC)) {
                        useItem.setDamageValue((int)event.getBlockedDamage());
                    }
                }
            }
        }
    
        private static int ticksBeforeNextDamageByUran = Config.uraniumDamageTickrate;
        @SubscribeEvent
        public static void onPostPlayerTick(PlayerTickEvent.Post event) {
            Player player = event.getEntity();
            if (!player.isCreative() && !player.isSpectator()) {
                if (ticksBeforeNextDamageByUran == 0) {
                    if (player.getMainHandItem().getItem() == ModItems.URANIUM_INGOT.get()) {
                        player.setHealth(player.getHealth() - Config.uraniumDamageAmount);
                        player.animateHurt(1.0F);
                    }
                    ticksBeforeNextDamageByUran = Config.uraniumDamageTickrate;
                } else {
                    ticksBeforeNextDamageByUran--;
                }
            }
        }
    
        @SubscribeEvent
        public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
            if (event.getEntity() instanceof Player player) {
                EquipmentSlot[] equipmentSlots = new EquipmentSlot[]{
                        EquipmentSlot.HEAD,
                        EquipmentSlot.CHEST,
                        EquipmentSlot.LEGS,
                        EquipmentSlot.FEET
                };

                List<ItemStack> armorItems = Arrays.stream(equipmentSlots)
                        .map(slot -> player.getInventory().getItem(slot.getIndex(36)))
                        .toList();

                if (armorItems.size() == 4 && !Config.disableSetBonuses) {
                    if (armorItems.stream().allMatch(stack -> stack.is(ModTags.Items.ENDERITE_ARMOR_SET))) {
                        event.setCanceled(event.getSource().is(DamageTypes.ON_FIRE) || event.getSource().is(DamageTypes.IN_FIRE));
                        player.clearFire();
                    }
                    if (armorItems.stream().allMatch(stack -> stack.is(ModTags.Items.MOLTEN_ARMOR_SET))) {
                        DamageSource source = event.getSource();
                        if (source.getDirectEntity() instanceof LivingEntity living && source.is(DamageTypes.MOB_ATTACK)) {
                            living.igniteForTicks(Config.hotDiaFireReflectDuration);
                            living.hurt(event.getSource(), event.getAmount() * (float)Config.hotDiaDamageReflectPercentage);
                        }
                    }
                }
            }
        }
    
        @SubscribeEvent
        public static void onAttackEntity(AttackEntityEvent event) {
            ItemStack item = event.getEntity().getMainHandItem();
            if (item.is(ModTags.Items.IGNITION_ITEMS) && !Config.disableHotDiaFireAspect) {
                if (event.getTarget().isAlive() && event.getTarget() instanceof LivingEntity target) {
                    if (target instanceof Zombie || target instanceof AbstractSkeleton) {
                        target.setRemainingFireTicks(Config.hotDiaFireAspectDuration);
                    }
                }
            }
        }
    
        @SubscribeEvent
        public static void onBlockBreakByHotDiaTool(BlockEvent.BreakEvent event) {
            Player player = event.getPlayer();
            if (!player.isCreative() && !player.isSpectator()) {
                if (player.getMainHandItem().is(ModTags.Items.IGNITION_ITEMS)) {
                    ItemEnchantments enchantments = player.getMainHandItem().getTagEnchantments();
                    HolderLookup<Enchantment> holderLookup = event.getLevel().holderLookup(Registries.ENCHANTMENT);

                    int fortuneLevel = 0;
                    Holder<Enchantment> fortune = holderLookup.getOrThrow(Enchantments.FORTUNE);
                    if (enchantments.keySet().contains(fortune)) {
                        fortuneLevel = enchantments.getLevel(fortune);
                    }

                    Holder<Enchantment> silkTouch = holderLookup.getOrThrow(Enchantments.SILK_TOUCH);
                    if (!enchantments.keySet().contains(silkTouch)) {
                        if (event.getLevel() instanceof Level level) {
                            BlockState blockState = event.getState();
                            BlockPos pos = event.getPos();

                            if (blockState.is(BlockTags.COPPER_ORES)) {
                                doBlockDrops(level, ModBlocks.MOLTEN_COPPER_ORE.get(), pos, Items.COPPER_INGOT, fortuneLevel);
                            } else if (blockState.is(BlockTags.IRON_ORES)) {
                                doBlockDrops(level, ModBlocks.MOLTEN_IRON_ORE.get(), pos, Items.IRON_INGOT, fortuneLevel);
                            } else if (blockState.is(BlockTags.GOLD_ORES)) {
                                doBlockDrops(level, ModBlocks.MOLTEN_GOLD_ORE.get(), pos, Items.GOLD_INGOT, fortuneLevel);
                            } else if (blockState.is(ModTags.Blocks.URANIUM_ORES)) {
                                doBlockDrops(level, ModBlocks.MOLTEN_URANIUM_ORE.get(), pos, ModItems.URANIUM_INGOT.get(), fortuneLevel);
                            } else if (blockState.is(ModTags.Blocks.MELTABLE_TO_STONE)) {
                                doBlockDrops(level, ModBlocks.MOLTEN_STONE.get(), pos, null, fortuneLevel);
                            } else if (blockState.is(BlockTags.SAND)) {
                                doBlockDrops(level, ModBlocks.MOLTEN_SAND.get(), pos, null, fortuneLevel);
                            } else {
                                return;
                            }

                            Block block = event.getState().getBlock();
                            BlockEntity blockEntity = level.getBlockEntity(event.getPos());
                            int xpAmount = block.getExpDrop(blockState, level, event.getPos(), blockEntity, player, player.getMainHandItem());
                            if (xpAmount == 0) {
                                xpAmount = UniformInt.of(2,6).sample(level.getRandom());
                            }
                            level.addFreshEntity(new ExperienceOrb(level, pos.getX(), pos.getY(), pos.getZ(), xpAmount));
                        }
                    }
                }
            }
        }
    
        private static void doBlockDrops(Level level, Block replacement, BlockPos pos, Item drop, int fortuneLevel) {
            BlockState state = replacement.defaultBlockState();
            level.setBlock(pos, state, 4);
            level.destroyBlock(pos, true);
            if (drop != null) {
                int x = pos.getX();
                int y = pos.getY();
                int z = pos.getZ();
                if (Math.random() * (fortuneLevel + 1) > 1.5) {
                    if (Math.random() * (fortuneLevel + 1) > 2.5) {
                        level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(drop, 2)));
                    } else {
                        level.addFreshEntity(new ItemEntity(level, x, y, z, new ItemStack(drop)));
                    }
                }
            }
        }
    
    
        private static final ArrayList<ItemEntity> DROPPED_INGOTS = new ArrayList<>();
    
        @SubscribeEvent
        public static void onItemToss(ItemTossEvent event) {
            ItemEntity itemEntity = event.getEntity();
            if (itemEntity.getItem().getItem() == Items.NETHERITE_INGOT) {
                DROPPED_INGOTS.add(itemEntity);
            }
        }

        @SubscribeEvent
        public static void onItemExpire(ItemExpireEvent event) {
            ItemEntity itemEntity = event.getEntity();
            if (itemEntity.isInLava()) {
                Item item = itemEntity.getItem().getItem();
                if (item == Items.NETHERITE_INGOT ||
                    item == ModItems.MOLTEN_INGOT.get() ||
                    item == ModItems.HEATING_INGOT_1.get() ||
                    item == ModItems.HEATING_INGOT_2.get() ||
                    item == ModItems.HEATING_INGOT_3.get()) {
                    event.addExtraLife(1000);
                }
            }
        }
    
        @SubscribeEvent
        public static void onPreLevelTick(LevelTickEvent.Pre event) {
            for (ItemEntity ingot : DROPPED_INGOTS) {
                int count = ingot.getItem().getCount();
                if (ingot.isInLava()) {
                    if (ingot.getAge() >= 300) {
                        ingot.setItem(new ItemStack(ModItems.MOLTEN_INGOT.get(), count));
                    }
                    else if (ingot.getAge() >= 225) {
                        ingot.setItem(new ItemStack(ModItems.HEATING_INGOT_3.get(), count));
                    }
                    else if (ingot.getAge() >= 150) {
                        ingot.setItem(new ItemStack(ModItems.HEATING_INGOT_2.get(), count));
                    }
                    else if (ingot.getAge() >= 75) {
                        ingot.setItem(new ItemStack(ModItems.HEATING_INGOT_1.get(), count));
                    }
                } else {
                    if (ingot.getAge() >= 6000) {
                        ingot.remove(Entity.RemovalReason.DISCARDED);
                    }
                    else if (!ingot.isInLava() && ingot.getItem().getItem() != ModItems.MOLTEN_INGOT.get()) {
                        ingot.setItem(new ItemStack(Items.NETHERITE_INGOT, count));
                    }
                }
            }
        }
    
        @SubscribeEvent
        public static void onPreItemEntityPickup(ItemEntityPickupEvent.Pre event) {
            Item item = event.getItemEntity().getItem().getItem();
            if (item == ModItems.HEATING_INGOT_1.get() ||
                item == ModItems.HEATING_INGOT_2.get() ||
                item == ModItems.HEATING_INGOT_3.get()) {
                event.setCanPickup(TriState.FALSE);
            }
        }

        @SubscribeEvent
        public static void onPostItemEntityPickup(ItemEntityPickupEvent.Post event) {
            Item item = event.getOriginalStack().getItem();
            if (item == ModItems.MOLTEN_INGOT.get() || item == Items.NETHERITE_INGOT) {
                DROPPED_INGOTS.remove(event.getItemEntity());
            }
        }
    }

//    @EventBusSubscriber(modid = OresAndToolsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
//    public static class ModEventBusEvents {
//
//    }
}
