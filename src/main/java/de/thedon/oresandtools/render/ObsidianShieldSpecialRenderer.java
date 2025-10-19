package de.thedon.oresandtools.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

public class ObsidianShieldSpecialRenderer extends ShieldSpecialRenderer {
    public static final Material OBSIDIAN_SHIELD_BASE = new Material(Sheets.SHIELD_SHEET, ResourceLocation.withDefaultNamespace("entity/obsidian_shield_base"));
    public static final Material NO_PATTERN_OBSIDIAN_SHIELD = new Material(Sheets.SHIELD_SHEET, ResourceLocation.withDefaultNamespace("entity/obsidian_shield_base_nopattern"));

    private final ShieldModel model;

    public ObsidianShieldSpecialRenderer(ShieldModel model) {
        super(model);
        this.model = model;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(@Nullable DataComponentMap dataComponents, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, boolean glint) {
        BannerPatternLayers bannerpatternlayers = dataComponents != null ? dataComponents.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY) : BannerPatternLayers.EMPTY;
        DyeColor dyecolor = dataComponents != null ? dataComponents.get(DataComponents.BASE_COLOR) : null;
        boolean flag = !bannerpatternlayers.layers().isEmpty() || dyecolor != null;
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = flag ? OBSIDIAN_SHIELD_BASE : NO_PATTERN_OBSIDIAN_SHIELD;
        VertexConsumer vertexconsumer = material.sprite().wrap(ItemRenderer.getFoilBuffer(buffer, this.model.renderType(material.atlasLocation()), displayContext == ItemDisplayContext.GUI, glint));
        this.model.handle().render(poseStack, vertexconsumer, packedLight, packedOverlay);
        if (flag) {
            BannerRenderer.renderPatterns(poseStack, buffer, packedLight, packedOverlay, this.model.plate(), material, false, Objects.requireNonNullElse(dyecolor, DyeColor.WHITE), bannerpatternlayers, glint, false);
        } else {
            this.model.plate().render(poseStack, vertexconsumer, packedLight, packedOverlay);
        }

        poseStack.popPose();
    }

    @OnlyIn(Dist.CLIENT)
    public static record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final ObsidianShieldSpecialRenderer.Unbaked INSTANCE = new ObsidianShieldSpecialRenderer.Unbaked();
        public static final MapCodec<ObsidianShieldSpecialRenderer.Unbaked> MAP_CODEC;

        public MapCodec<ObsidianShieldSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public SpecialModelRenderer<?> bake(EntityModelSet entityModelSet) {
            return new ObsidianShieldSpecialRenderer(new ShieldModel(entityModelSet.bakeLayer(ModelLayers.SHIELD)));
        }

        static {
            MAP_CODEC = MapCodec.unit(INSTANCE);
        }
    }
}
