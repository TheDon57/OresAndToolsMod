package de.thedon.oresandtools.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.block.custom.ValyrianChestBlock;
import de.thedon.oresandtools.block.entity.ValyrianChestBlockEntity;
import net.minecraft.client.model.ChestModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class ValyrianChestRenderer implements BlockEntityRenderer<ValyrianChestBlockEntity, ValyrianChestRenderState> {
    public static final Material CHEST_LOCATION = Sheets.CHEST_MAPPER.defaultNamespaceApply("valyrian");
    public static final Material CHEST_LOCATION_LEFT = Sheets.CHEST_MAPPER.defaultNamespaceApply("valyrian_left");
    public static final Material CHEST_LOCATION_RIGHT = Sheets.CHEST_MAPPER.defaultNamespaceApply("valyrian_right");

    private final MaterialSet materials;
    private final ChestModel singleModel;
    private final ChestModel doubleLeftModel;
    private final ChestModel doubleRightModel;

    public ValyrianChestRenderer(BlockEntityRendererProvider.Context context) {
        this.materials = context.materials();
        this.singleModel = new ChestModel(context.bakeLayer(ModelLayers.CHEST));
        this.doubleLeftModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT));
        this.doubleRightModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT));
    }

    @Override
    public @NotNull ValyrianChestRenderState createRenderState() {
        return new ValyrianChestRenderState();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void extractRenderState(ValyrianChestBlockEntity blockEntity, ValyrianChestRenderState renderState, float partialTick, Vec3 vec3, @Nullable ModelFeatureRenderer.CrumblingOverlay overlay) {
        DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborcombineresult;
        label30: {
            BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, vec3, overlay);
            boolean flag = blockEntity.getLevel() != null;
            BlockState blockstate = flag ? blockEntity.getBlockState() : ModBlocks.VALYRIAN_CHEST.get().defaultBlockState().setValue(ValyrianChestBlock.FACING, Direction.SOUTH);
            renderState.type = blockstate.hasProperty(ValyrianChestBlock.TYPE) ? blockstate.getValue(ValyrianChestBlock.TYPE) : ChestType.SINGLE;
            renderState.angle = (blockstate.getValue(ValyrianChestBlock.FACING)).toYRot();
            if (flag) {
                Block block = blockstate.getBlock();
                if (block instanceof ValyrianChestBlock chestBlock) {
                    neighborcombineresult = chestBlock.combine(blockstate, blockEntity.getLevel(), blockEntity.getBlockPos(), true);
                    break label30;
                }
            }

            neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;
        }

        renderState.open = (neighborcombineresult.apply(ValyrianChestBlock.opennessCombiner(blockEntity))).get(partialTick);
        if (renderState.type != ChestType.SINGLE) {
            renderState.lightCoords = (neighborcombineresult.apply(new BrightnessCombiner<>())).applyAsInt(renderState.lightCoords);
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void submit(ValyrianChestRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(-renderState.angle));
        poseStack.translate(-0.5F, -0.5F, -0.5F);
        float f = renderState.open;
        f = 1.0F - f;
        f = 1.0F - f * f * f;
        Material material;
        switch (renderState.type) {
            case SINGLE -> material = CHEST_LOCATION;
            case LEFT -> material = CHEST_LOCATION_LEFT;
            case RIGHT -> material = CHEST_LOCATION_RIGHT;
            default -> throw new MatchException(null, null);
        }
        RenderType rendertype = material.renderType(RenderType::entityCutout);
        TextureAtlasSprite textureatlassprite = this.materials.get(material);
        if (renderState.type != ChestType.SINGLE) {
            if (renderState.type == ChestType.LEFT) {
                nodeCollector.submitModel(this.doubleLeftModel, f, poseStack, rendertype, renderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, textureatlassprite, 0, renderState.breakProgress);
            } else {
                nodeCollector.submitModel(this.doubleRightModel, f, poseStack, rendertype, renderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, textureatlassprite, 0, renderState.breakProgress);
            }
        } else {
            nodeCollector.submitModel(this.singleModel, f, poseStack, rendertype, renderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, textureatlassprite, 0, renderState.breakProgress);
        }

        poseStack.popPose();
    }

    @Override
    public @NotNull AABB getRenderBoundingBox(ValyrianChestBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        return AABB.encapsulatingFullBlocks(pos.offset(-1, 0, -1), pos.offset(1, 1, 1));
    }
}
