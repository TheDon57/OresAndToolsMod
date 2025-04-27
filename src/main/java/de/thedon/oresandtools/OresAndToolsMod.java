package de.thedon.oresandtools;

import com.mojang.logging.LogUtils;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.entity.ModBlockEntities;
import de.thedon.oresandtools.inventory.ModMenuTypes;
import de.thedon.oresandtools.inventory.BackpackScreen;
import de.thedon.oresandtools.inventory.ValyrianChestScreen;
import de.thedon.oresandtools.item.ModCreativeModeTabs;
import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.render.ModBEWLRenderer;
import de.thedon.oresandtools.render.ValyrianChestRenderer;
import de.thedon.oresandtools.util.PropertyRegistration;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(OresAndToolsMod.MOD_ID)
public class OresAndToolsMod {
    public static final String MOD_ID = "oresandtools";
    private static final Logger LOGGER = LogUtils.getLogger();

    public OresAndToolsMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);

        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            PropertyRegistration.registerProperties();
        }

        @SubscribeEvent
        public static void onRegisterEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.VALYRIAN_CHEST.get(), ValyrianChestRenderer::new);
        }

        @SubscribeEvent
        public static void onRegisterMenuScreens(final RegisterMenuScreensEvent event) {
            event.register(ModMenuTypes.BACKPACK_MENU.get(), BackpackScreen::new);
            event.register(ModMenuTypes.VALYRIAN_CHEST_MENU_6x9.get(), ValyrianChestScreen::screen6x9);
            event.register(ModMenuTypes.VALYRIAN_CHEST_MENU_9x12.get(), ValyrianChestScreen::screen9x12);
        }

        @SubscribeEvent
        public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
            ModBEWLRenderer.registerItem(event, ModBlocks.VALYRIAN_CHEST.asItem());
            ModBEWLRenderer.registerItem(event, ModItems.OBSIDIAN_SHIELD.get());
        }
    }
}
