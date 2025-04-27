package de.thedon.oresandtools.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.entity.ModBlockEntities;
import de.thedon.oresandtools.entity.ValyrianChestBlockEntity;
import de.thedon.oresandtools.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class ModBEWLRenderer extends BlockEntityWithoutLevelRenderer {
    public static final Material OBSIDIAN_SHIELD_BASE = new Material(Sheets.SHIELD_SHEET, ResourceLocation.withDefaultNamespace("entity/obsidian_shield_base"));
    public static final Material NO_PATTERN_OBSIDIAN_SHIELD = new Material(Sheets.SHIELD_SHEET, ResourceLocation.withDefaultNamespace("entity/obsidian_shield_base_nopattern"));

    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private final EntityModelSet entityModelSet;
    private ShieldModel shieldModel;

    public ModBEWLRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.blockEntityRenderDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
        this.entityModelSet = Minecraft.getInstance().getEntityModels();
        this.shieldModel = new ShieldModel(this.entityModelSet.bakeLayer(ModelLayers.SHIELD));
    }

    @Override
    public void onResourceManagerReload(@NotNull ResourceManager pResourceManager) {
        this.shieldModel = new ShieldModel(this.entityModelSet.bakeLayer(ModelLayers.SHIELD));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void renderByItem(ItemStack pStack, ItemDisplayContext pDisplayContext, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        Item item = pStack.getItem();
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            BlockState blockstate = block.defaultBlockState();
            BlockEntity blockentity;
            if (blockstate.is(ModBlocks.VALYRIAN_CHEST.get())) {
                blockentity = new ValyrianChestBlockEntity(ModBlockEntities.VALYRIAN_CHEST.get(), BlockPos.ZERO, ModBlocks.VALYRIAN_CHEST.get().defaultBlockState());
            } else {
                return;
            }
            this.blockEntityRenderDispatcher.renderItem(blockentity, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        } else {
            if (pStack.is(ModItems.OBSIDIAN_SHIELD.get())) {
                BannerPatternLayers bannerpatternlayers = pStack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
                DyeColor dyecolor = pStack.get(DataComponents.BASE_COLOR);
                boolean flag = !bannerpatternlayers.layers().isEmpty() || dyecolor != null;
                pPoseStack.pushPose();
                pPoseStack.scale(1.0F, -1.0F, -1.0F);
                Material material = flag ? OBSIDIAN_SHIELD_BASE : NO_PATTERN_OBSIDIAN_SHIELD;
                VertexConsumer vertexconsumer = material.sprite()
                        .wrap(ItemRenderer.getFoilBufferDirect(pBuffer, this.shieldModel.renderType(material.atlasLocation()), true, pStack.hasFoil()));
                this.shieldModel.handle().render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
                if (flag) {
                    BannerRenderer.renderPatterns(
                            pPoseStack,
                            pBuffer,
                            pPackedLight,
                            pPackedOverlay,
                            this.shieldModel.plate(),
                            material,
                            false,
                            Objects.requireNonNullElse(dyecolor, DyeColor.WHITE),
                            bannerpatternlayers,
                            pStack.hasFoil()
                    );
                } else {
                    this.shieldModel.plate().render(pPoseStack, vertexconsumer, pPackedLight, pPackedOverlay);
                }

                pPoseStack.popPose();
            }
        }
    }

    public static void registerItem(RegisterClientExtensionsEvent event, Item pItem) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public @NotNull BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return new ModBEWLRenderer();
            }
        }, pItem);
    }
}
