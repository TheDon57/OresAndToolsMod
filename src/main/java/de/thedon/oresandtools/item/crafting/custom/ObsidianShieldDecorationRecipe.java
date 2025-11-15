package de.thedon.oresandtools.item.crafting.custom;

import de.thedon.oresandtools.item.ModItems;
import de.thedon.oresandtools.item.crafting.ModRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.jetbrains.annotations.NotNull;

public class ObsidianShieldDecorationRecipe extends CustomRecipe {
    public ObsidianShieldDecorationRecipe(CraftingBookCategory category) {
        super(category);
    }

    public boolean matches(CraftingInput input, @NotNull Level level) {
        if (input.ingredientCount() != 2) {
            return false;
        } else {
            boolean flag = false;
            boolean flag1 = false;

            for(int i = 0; i < input.size(); ++i) {
                ItemStack itemstack = input.getItem(i);
                if (!itemstack.isEmpty()) {
                    if (itemstack.getItem() instanceof BannerItem) {
                        if (flag1) {
                            return false;
                        }

                        flag1 = true;
                    } else {
                        if (!itemstack.is(ModItems.OBSIDIAN_SHIELD.get())) {
                            return false;
                        }

                        if (flag) {
                            return false;
                        }

                        BannerPatternLayers bannerpatternlayers = itemstack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
                        if (!bannerpatternlayers.layers().isEmpty()) {
                            return false;
                        }

                        flag = true;
                    }
                }
            }

            return flag && flag1;
        }
    }

    public @NotNull ItemStack assemble(CraftingInput input, HolderLookup.@NotNull Provider provider) {
        ItemStack itemstack = ItemStack.EMPTY;
        ItemStack itemstack1 = ItemStack.EMPTY;

        for(int i = 0; i < input.size(); ++i) {
            ItemStack itemstack2 = input.getItem(i);
            if (!itemstack2.isEmpty()) {
                if (itemstack2.getItem() instanceof BannerItem) {
                    itemstack = itemstack2;
                } else if (itemstack2.is(ModItems.OBSIDIAN_SHIELD.get())) {
                    itemstack1 = itemstack2.copy();
                }
            }
        }

        if (itemstack1.isEmpty()) {
            return itemstack1;
        } else {
            itemstack1.set(DataComponents.BANNER_PATTERNS, itemstack.get(DataComponents.BANNER_PATTERNS));
            itemstack1.set(DataComponents.BASE_COLOR, ((BannerItem)itemstack.getItem()).getColor());
            return itemstack1;
        }
    }

    public @NotNull RecipeSerializer<ObsidianShieldDecorationRecipe> getSerializer() {
        return ModRecipeSerializers.OBSIDIAN_SHIELD_DECORATION.get();
    }
}
