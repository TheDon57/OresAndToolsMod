package de.thedon.oresandtools.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.entity.ModBlockEntities;
import de.thedon.oresandtools.block.entity.EnderiteChestBlockEntity;
import de.thedon.oresandtools.screen.custom.EnderiteChestMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.function.Supplier;

public class EnderiteChestBlock extends ChestBlock {
    public static final MapCodec<EnderiteChestBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("open_sound").forGetter(EnderiteChestBlock::getOpenChestSound), BuiltInRegistries.SOUND_EVENT.byNameCodec().fieldOf("close_sound").forGetter(EnderiteChestBlock::getCloseChestSound), propertiesCodec()).apply(instance, (openSound, closeSound, properties) -> new EnderiteChestBlock(ModBlockEntities.ENDERITE_CHEST::get, openSound, closeSound, properties)));
    private static final DoubleBlockCombiner.Combiner<ChestBlockEntity, Optional<MenuProvider>> MENU_PROVIDER_COMBINER;

    @Override
    public @NotNull MapCodec<? extends ChestBlock> codec() {
        return CODEC;
    }

    public EnderiteChestBlock(Supplier<BlockEntityType<? extends ChestBlockEntity>> blockEntityType, SoundEvent openSound, SoundEvent closeSound, Properties properties) {
        super(blockEntityType, openSound, closeSound, properties);
    }

    @Override
    @Nullable
    @ParametersAreNonnullByDefault
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return (this.combine(state, level, pos, false).apply(MENU_PROVIDER_COMBINER)).orElse(null);
    }

    @Override
    @ParametersAreNonnullByDefault
    public @NotNull BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EnderiteChestBlockEntity(pos, state);
    }

    static {
        MENU_PROVIDER_COMBINER = new DoubleBlockCombiner.Combiner<>() {
            @ParametersAreNonnullByDefault
            public @NotNull Optional<MenuProvider> acceptDouble(final ChestBlockEntity first, final ChestBlockEntity second) {
                final Container container = new CompoundContainer(first, second);
                return Optional.of(new MenuProvider() {
                    @Nullable
                    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
                        if (first.canOpen(player) && second.canOpen(player)) {
                            first.unpackLootTable(inventory.player);
                            second.unpackLootTable(inventory.player);
                            return EnderiteChestMenu.menu9x12(containerId, inventory, container);
                        } else {
                            return null;
                        }
                    }

                    public @NotNull Component getDisplayName() {
                        if (first.hasCustomName()) {
                            return first.getDisplayName();
                        } else {
                            return (second.hasCustomName() ? second.getDisplayName() : Component.translatable("container." + OresAndToolsMod.MOD_ID + ".large_enderite_chest"));
                        }
                    }
                });
            }

            public @NotNull Optional<MenuProvider> acceptSingle(@NotNull ChestBlockEntity single) {
                return Optional.of(single);
            }


            public @NotNull Optional<MenuProvider> acceptNone() {
                return Optional.empty();
            }
        };
    }
}
