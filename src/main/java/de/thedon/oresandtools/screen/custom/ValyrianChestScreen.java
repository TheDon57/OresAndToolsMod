package de.thedon.oresandtools.screen.custom;

import de.thedon.oresandtools.OresAndToolsMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class ValyrianChestScreen extends AbstractContainerScreen<ValyrianChestMenu> {
    private static final ResourceLocation CONTAINER_BACKGROUND_6x9 = ResourceLocation.withDefaultNamespace("textures/gui/container/generic_54.png");
    private static final ResourceLocation CONTAINER_BACKGROUND_9x12 = ResourceLocation.fromNamespaceAndPath(OresAndToolsMod.MOD_ID, "textures/gui/container/generic_108.png");

    private final ResourceLocation background;
    private final int containerRows;

    public static ValyrianChestScreen screen6x9(ValyrianChestMenu menu, Inventory playerInventory, Component title) {
        return new ValyrianChestScreen(menu, playerInventory, title, CONTAINER_BACKGROUND_6x9);
    }

    public static ValyrianChestScreen screen9x12(ValyrianChestMenu menu, Inventory playerInventory, Component title) {
        return new ValyrianChestScreen(menu, playerInventory, title, CONTAINER_BACKGROUND_9x12);
    }

    public ValyrianChestScreen(ValyrianChestMenu menu, Inventory playerInventory, Component title, ResourceLocation background) {
        super(menu, playerInventory, title);
        int i = 222;
        int j = 114;
        this.background = background;
        this.containerRows = menu.getRowCount();
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
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (this.background == CONTAINER_BACKGROUND_6x9) {
            super.renderLabels(guiGraphics, mouseX, mouseY);
        }
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        if (this.background == CONTAINER_BACKGROUND_6x9) {
            guiGraphics.blit(RenderType::guiTextured, this.background, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 17, 256, 256);
            guiGraphics.blit(RenderType::guiTextured, this.background, i, j + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96, 256, 256);
        } else {
            guiGraphics.blit(RenderType::guiTextured, this.background, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 7, 256, 256);
            guiGraphics.blit(RenderType::guiTextured, this.background, i, j + this.containerRows * 18 + 7, 0, 170, this.imageWidth, 86, 256, 256);
        }
    }
}
