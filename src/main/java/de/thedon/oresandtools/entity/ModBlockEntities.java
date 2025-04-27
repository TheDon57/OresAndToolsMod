package de.thedon.oresandtools.entity;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, OresAndToolsMod.MOD_ID);

    public static final Supplier<BlockEntityType<ValyrianChestBlockEntity>> VALYRIAN_CHEST =
            BLOCK_ENTITIES.register("valyrian_chest", () -> BlockEntityType.Builder.of(
                    ValyrianChestBlockEntity::new, ModBlocks.VALYRIAN_CHEST.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
