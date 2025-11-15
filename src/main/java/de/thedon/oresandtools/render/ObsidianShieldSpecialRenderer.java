package de.thedon.oresandtools.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.special.ShieldSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

public class ObsidianShieldSpecialRenderer extends ShieldSpecialRenderer {
    public static final Material OBSIDIAN_SHIELD_BASE = new Material(Sheets.SHIELD_SHEET, ResourceLocation.withDefaultNamespace("entity/obsidian_shield_base"));
    public static final Material NO_PATTERN_OBSIDIAN_SHIELD = new Material(Sheets.SHIELD_SHEET, ResourceLocation.withDefaultNamespace("entity/obsidian_shield_base_nopattern"));

    private final ShieldModel model;
    private final MaterialSet materials;

    public ObsidianShieldSpecialRenderer(MaterialSet materials, ShieldModel model) {
        super(materials, model);
        this.model = model;
        this.materials = materials;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void submit(@Nullable DataComponentMap dataComponents, ItemDisplayContext displayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, int packedOverlay, boolean glint, int i) {
        BannerPatternLayers bannerpatternlayers = dataComponents != null ? dataComponents.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY) : BannerPatternLayers.EMPTY;
        DyeColor dyecolor = dataComponents != null ? dataComponents.get(DataComponents.BASE_COLOR) : null;
        boolean flag = !bannerpatternlayers.layers().isEmpty() || dyecolor != null;
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = flag ? OBSIDIAN_SHIELD_BASE : NO_PATTERN_OBSIDIAN_SHIELD;
        submitNodeCollector.submitModelPart(this.model.handle(), poseStack, this.model.renderType(material.atlasLocation()), packedLight, packedOverlay, this.materials.get(material), false, false, -1, null, i);
        if (flag) {
            BannerRenderer.submitPatterns(this.materials, poseStack, submitNodeCollector, packedLight, packedOverlay, this.model, Unit.INSTANCE, material, false, (DyeColor)Objects.requireNonNullElse(dyecolor, DyeColor.WHITE), bannerpatternlayers, glint, null, i);
        } else {
            submitNodeCollector.submitModelPart(this.model.plate(), poseStack, this.model.renderType(material.atlasLocation()), packedLight, packedOverlay, this.materials.get(material), false, glint, -1, null, i);
        }

        poseStack.popPose();
    }

    public static record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final ObsidianShieldSpecialRenderer.Unbaked INSTANCE = new ObsidianShieldSpecialRenderer.Unbaked();
        public static final MapCodec<ObsidianShieldSpecialRenderer.Unbaked> MAP_CODEC;

        public @NotNull MapCodec<ObsidianShieldSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            return new ObsidianShieldSpecialRenderer(context.materials(), new ShieldModel(context.entityModelSet().bakeLayer(ModelLayers.SHIELD)));
        }

        static {
            MAP_CODEC = MapCodec.unit(INSTANCE);
        }
    }
}
