package de.thedon.oresandtools.event;

import de.thedon.oresandtools.Config;
import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.item.ModArmorMaterials;
import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.item.ModToolTiers;
import de.thedon.oresandtools.item.custom.ModArmorItem;
import de.thedon.oresandtools.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
import java.util.List;

public class ModEvents {
    @EventBusSubscriber(modid = OresAndToolsMod.MOD_ID)
    public static class NeoForgeEvents {
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
                if (player.getArmorSlots() instanceof List<ItemStack> armorItems) {
                    if (armorItems.size() == 4 && !Config.disableSetBonuses) {
                        boolean allValyrian = true;
                        boolean allHotDiamond = true;
                        for (ItemStack itemStack : armorItems) {
                            if (itemStack.getItem() instanceof ModArmorItem armorItem) {
                                if (armorItem.getMaterial() != ModArmorMaterials.VALYRIAN) {
                                    allValyrian = false;
                                }
                                if (armorItem.getMaterial() != ModArmorMaterials.HOT_H_DIAMOND) {
                                    allHotDiamond = false;
                                }
                            } else {
                                allValyrian = false;
                                allHotDiamond = false;
                            }
                        }
                        if (allValyrian) {
                            event.setCanceled(event.getSource().is(DamageTypes.ON_FIRE) || event.getSource().is(DamageTypes.IN_FIRE));
                            player.extinguishFire();
                        }
                        if (allHotDiamond) {
                            Entity source = event.getSource().getEntity();
                            if (source instanceof LivingEntity living) {
                                living.setRemainingFireTicks(Config.hotDiaFireReflectDuration);
                                living.hurt(event.getSource(), event.getAmount());
                            }
                        }
                    }
                }
            }
        }
    
        @SubscribeEvent
        public static void onAttackEntity(AttackEntityEvent event) {
            if (event.getEntity().getMainHandItem().getItem() instanceof TieredItem tieredItem) {
                if (tieredItem.getTier() == ModToolTiers.HOT_HARDENED_DIAMOND && !Config.disableHotDiaFireAspect) {
                    if (event.getTarget().isAlive()) {
                        if (event.getTarget() instanceof LivingEntity target) {
                            if (target instanceof Zombie || target instanceof AbstractSkeleton) {
                                target.setRemainingFireTicks(Config.hotDiaFireAspectDuration);
                            }
                        }
                    }
                }
            }
        }
    
        @SubscribeEvent
        public static void onBlockBreakByHotDiaTool(BlockEvent.BreakEvent event) {
            RegistryAccess registryAccess = event.getLevel().registryAccess();
            Player player = event.getPlayer();
            if (!player.isCreative() && !player.isSpectator()) {
                if (player.getMainHandItem().getItem() instanceof TieredItem tieredItem) {
                    if (tieredItem.getTier() == ModToolTiers.HOT_HARDENED_DIAMOND) {
                        ItemEnchantments enchantments = player.getMainHandItem().getTagEnchantments();
                        HolderLookup<Enchantment> holderLookup = event.getLevel().holderLookup(Registries.ENCHANTMENT);

                        int fortuneLevel = 0;
                        Holder<Enchantment> fortune = holderLookup.getOrThrow(Enchantments.FORTUNE);
                        if (!enchantments.keySet().contains(fortune)) {
                            fortuneLevel = enchantments.getLevel(fortune);
                        }

                        Holder<Enchantment> silkTouch = holderLookup.getOrThrow(Enchantments.SILK_TOUCH);
                        if (!enchantments.keySet().contains(silkTouch)) {
                            if (event.getLevel() instanceof Level level) {
                                BlockState blockState = event.getState();
                                Block block = event.getState().getBlock();
                                BlockEntity blockEntity = level.getBlockEntity(event.getPos());
                                int xpAmount = block.getExpDrop(blockState, level, event.getPos(), blockEntity, player, player.getMainHandItem());
                                if (xpAmount == 0) {
                                    xpAmount = UniformInt.of(2,6).sample(level.getRandom());
                                }
                                BlockPos pos = event.getPos();
                                level.addFreshEntity(new ExperienceOrb(level, pos.getX(), pos.getY(), pos.getZ(), xpAmount));
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
                                }
                            }
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
    
        @SubscribeEvent
        public static void onLivingDrops(LivingDropsEvent event) {
            if (Math.random() < Config.villagerEmeraldDropChance) {
                if (event.getSource().getEntity() instanceof Player player) {
                    if (player.getMainHandItem().getItem() == ModItems.EMERALD_SWORD.get()) {
                        LivingEntity entity = event.getEntity();
                        if (entity instanceof Villager) {
                            Level level = entity.getCommandSenderWorld();
                            event.getDrops().add(new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(),
                                new ItemStack(Items.EMERALD)));
                        }
                    }
                }
            }
        }
    
    
        private static final ArrayList<ItemEntity> droppedDiamonds = new ArrayList<>();
    
        @SubscribeEvent
        public static void onItemToss(ItemTossEvent event) {
            ItemEntity itemEntity = event.getEntity();
            if (itemEntity.getItem().getItem() == ModItems.HARDENED_DIAMOND.get()) {
                droppedDiamonds.add(itemEntity);
            }
        }

        @SubscribeEvent
        public static void onItemExpire(ItemExpireEvent event) {
            ItemEntity itemEntity = event.getEntity();
            if (itemEntity.isInLava()) {
                Item item = itemEntity.getItem().getItem();
                if (item == ModItems.HARDENED_DIAMOND.get() ||
                    item == ModItems.HOT_HARDENED_DIAMOND.get() ||
                    item == ModItems.HEATING_HARDENED_DIAMOND_1.get() ||
                    item == ModItems.HEATING_HARDENED_DIAMOND_2.get() ||
                    item == ModItems.HEATING_HARDENED_DIAMOND_3.get()) {
                    event.addExtraLife(1000);
                }
            }
        }
    
        @SubscribeEvent
        public static void onPreLevelTick(LevelTickEvent.Pre event) {
            for (ItemEntity diamond : droppedDiamonds) {
                int count = diamond.getItem().getCount();
                if (diamond.isInLava()) {
                    if (diamond.getAge() >= 300) {
                        diamond.setItem(new ItemStack(ModItems.HOT_HARDENED_DIAMOND.get(), count));
                    }
                    else if (diamond.getAge() >= 225) {
                        diamond.setItem(new ItemStack(ModItems.HEATING_HARDENED_DIAMOND_3.get(), count));
                    }
                    else if (diamond.getAge() >= 150) {
                        diamond.setItem(new ItemStack(ModItems.HEATING_HARDENED_DIAMOND_2.get(), count));
                    }
                    else if (diamond.getAge() >= 75) {
                        diamond.setItem(new ItemStack(ModItems.HEATING_HARDENED_DIAMOND_1.get(), count));
                    }
                } else {
                    if (diamond.getAge() >= 6000) {
                        diamond.remove(Entity.RemovalReason.DISCARDED);
                    }
                    else if (!diamond.isInLava() && diamond.getItem().getItem() != ModItems.HOT_HARDENED_DIAMOND.get()) {
                        diamond.setItem(new ItemStack(ModItems.HARDENED_DIAMOND.get(), count));
                    }
                }
            }
        }
    
        @SubscribeEvent
        public static void onPreItemEntityPickup(ItemEntityPickupEvent.Pre event) {
            Item item = event.getItemEntity().getItem().getItem();
            if (item == ModItems.HEATING_HARDENED_DIAMOND_1.get() ||
                item == ModItems.HEATING_HARDENED_DIAMOND_2.get() ||
                item == ModItems.HEATING_HARDENED_DIAMOND_3.get()) {
                event.setCanPickup(TriState.FALSE);
            }
            if (item == ModItems.HOT_HARDENED_DIAMOND.get() || item == ModItems.HARDENED_DIAMOND.get()) {
                droppedDiamonds.remove(event.getItemEntity());
            }
        }        
    }

//    @EventBusSubscriber(modid = OresAndToolsMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
//    public static class ModEventBusEvents {
//
//    }
}
