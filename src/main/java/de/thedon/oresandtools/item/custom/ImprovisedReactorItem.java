package de.thedon.oresandtools.item.custom;

import de.thedon.oresandtools.Config;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.FuelValues;
import org.jetbrains.annotations.NotNull;

public class ImprovisedReactorItem extends Item {
    public ImprovisedReactorItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getBurnTime(@NotNull ItemStack itemStack, RecipeType<?> recipeType, @NotNull FuelValues fuelValues) {
        return Config.improReactorBurnTime;
    }
}
