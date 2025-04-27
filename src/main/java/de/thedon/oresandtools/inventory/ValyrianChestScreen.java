package de.thedon.oresandtools.inventory;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class ValyrianChestScreen extends AbstractContainerScreen<ValyrianChestMenu> {
    private static final ResourceLocation CONTAINER_BACKGROUND_6x9 = ResourceLocation.withDefaultNamespace("textures/gui/container/generic_54.png");
    private static final ResourceLocation CONTAINER_BACKGROUND_9x12 = ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, "textures/gui/container/generic_108.png");

    private final ResourceLocation background;
    private final int containerRows;

    public static ValyrianChestScreen screen6x9(ValyrianChestMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        return new ValyrianChestScreen(pMenu, pPlayerInventory, pTitle, CONTAINER_BACKGROUND_6x9);
    }

    public static ValyrianChestScreen screen9x12(ValyrianChestMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        return new ValyrianChestScreen(pMenu, pPlayerInventory, pTitle, CONTAINER_BACKGROUND_9x12);
    }

    public ValyrianChestScreen(ValyrianChestMenu pMenu, Inventory pPlayerInventory, Component pTitle, ResourceLocation pBackground) {
        super(pMenu, pPlayerInventory, pTitle);
        int i = 222;
        int j = 114;
        this.background = pBackground;
        this.containerRows = pMenu.getRowCount();
        if (this.background == CONTAINER_BACKGROUND_6x9) {
            this.imageHeight = 114 + this.containerRows * 18;
        } else {
            this.imageWidth = 230;
            this.imageHeight = 94 + this.containerRows * 18;
        }
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        if (this.background == CONTAINER_BACKGROUND_6x9) {
            super.renderLabels(pGuiGraphics, pMouseX, pMouseY);
        }
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        if (this.background == CONTAINER_BACKGROUND_6x9) {
            pGuiGraphics.blit(this.background, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
            pGuiGraphics.blit(this.background, i, j + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
        } else {
            pGuiGraphics.blit(this.background, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 7);
            pGuiGraphics.blit(this.background, i, j + this.containerRows * 18 + 7, 0, 170, this.imageWidth, 86);
        }
    }
}
