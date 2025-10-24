package de.thedon.oresandtools.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.block.custom.ValyrianChestBlock;
import de.thedon.oresandtools.block.entity.ValyrianChestBlockEntity;
import net.minecraft.client.model.ChestModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ValyrianChestRenderer extends ChestRenderer<ValyrianChestBlockEntity> {
    public static final Material CHEST_LOCATION = chestMaterial("valyrian");
    public static final Material CHEST_LOCATION_LEFT = chestMaterial("valyrian_left");
    public static final Material CHEST_LOCATION_RIGHT = chestMaterial("valyrian_right");

    private final ChestModel singleModel;
    private final ChestModel doubleLeftModel;
    private final ChestModel doubleRightModel;

    public ValyrianChestRenderer(BlockEntityRendererProvider.Context context) {
        super(context);

        this.singleModel = new ChestModel(context.bakeLayer(ModelLayers.CHEST));
        this.doubleLeftModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT));
        this.doubleRightModel = new ChestModel(context.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT));
    }

    @Override
    public void render(ValyrianChestBlockEntity blockEntity, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay, Vec3 vec) {
        Level level = blockEntity.getLevel();
        boolean flag = level != null;
        BlockState blockstate = flag ? blockEntity.getBlockState() : ModBlocks.VALYRIAN_CHEST.get().defaultBlockState().setValue(ValyrianChestBlock.FACING, Direction.SOUTH);
        ChestType chesttype = blockstate.hasProperty(ValyrianChestBlock.TYPE) ? blockstate.getValue(ValyrianChestBlock.TYPE) : ChestType.SINGLE;
        Block block = blockstate.getBlock();
        if (block instanceof ValyrianChestBlock valyrianChestBlock) {
            boolean flag1 = chesttype != ChestType.SINGLE;
            poseStack.pushPose();
            float f = blockstate.getValue(ValyrianChestBlock.FACING).toYRot();
            poseStack.translate(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(Axis.YP.rotationDegrees(-f));
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            DoubleBlockCombiner.NeighborCombineResult<? extends ValyrianChestBlockEntity> neighborcombineresult;
            if (flag) {
                neighborcombineresult = valyrianChestBlock.combine(blockstate, level, blockEntity.getBlockPos(), true);
            } else {
                neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float f1 = neighborcombineresult.apply(ValyrianChestBlock.opennessCombiner(blockEntity)).get(partialTick);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = neighborcombineresult.apply(new BrightnessCombiner<>()).applyAsInt(packedLight);
            Material material = this.getMaterial(blockEntity, chesttype);
            VertexConsumer vertexconsumer = material.buffer(buffer, RenderType::entityCutout);
            if (flag1) {
                if (chesttype == ChestType.LEFT) {
                    this.render(poseStack, vertexconsumer, this.doubleLeftModel, f1, i, packedOverlay);
                } else {
                    this.render(poseStack, vertexconsumer, this.doubleRightModel, f1, i, packedOverlay);
                }
            } else {
                this.render(poseStack, vertexconsumer, this.singleModel, f1, i, packedOverlay);
            }

            poseStack.popPose();
        }
    }

    private void render(PoseStack poseStack, VertexConsumer buffer, ChestModel model, float openness, int packedLight, int packedOverlay) {
        model.setupAnim(openness);
        model.renderToBuffer(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    protected @NotNull Material getMaterial(@NotNull ValyrianChestBlockEntity blockEntity, @NotNull ChestType chestType) {
        return switch (chestType) {
            case LEFT -> ValyrianChestRenderer.CHEST_LOCATION_LEFT;
            case RIGHT -> ValyrianChestRenderer.CHEST_LOCATION_RIGHT;
            default -> ValyrianChestRenderer.CHEST_LOCATION;
        };
    }

    private static Material chestMaterial(String chestName) {
        return new Material(Sheets.CHEST_SHEET, ResourceLocation.withDefaultNamespace("entity/chest/" + chestName));
    }
}
