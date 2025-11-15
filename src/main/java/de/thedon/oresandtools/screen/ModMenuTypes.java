package de.thedon.oresandtools.screen;

import de.thedon.oresandtools.OresAndToolsMod;
import de.thedon.oresandtools.screen.custom.BackpackMenu;
import de.thedon.oresandtools.screen.custom.EnderiteChestMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(BuiltInRegistries.MENU, OresAndToolsMod.MOD_ID);

    public static final Supplier<MenuType<BackpackMenu>> BACKPACK_MENU = registerMenuType("backpack_menu", BackpackMenu::new);
    public static final Supplier<MenuType<EnderiteChestMenu>> ENDERITE_CHEST_MENU_6x9 = registerMenuType("enderite_chest_menu_6x9", EnderiteChestMenu::menu6x9);
    public static final Supplier<MenuType<EnderiteChestMenu>> ENDERITE_CHEST_MENU_9x12 = registerMenuType("enderite_chest_menu_9x12", EnderiteChestMenu::menu9x12);

    private static <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
