package de.thedon.oresandtools.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import de.thedon.oresandtools.block.ModBlocks;
import de.thedon.oresandtools.block.ValyrianChestBlock;
import de.thedon.oresandtools.entity.ValyrianChestBlockEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
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
import org.jetbrains.annotations.NotNull;

public class ValyrianChestRenderer extends ChestRenderer<ValyrianChestBlockEntity> {
    public static final Material CHEST_LOCATION = chestMaterial("valyrian");
    public static final Material CHEST_LOCATION_LEFT = chestMaterial("valyrian_left");
    public static final Material CHEST_LOCATION_RIGHT = chestMaterial("valyrian_right");
    private static final String BOTTOM = "bottom";
    private static final String LID = "lid";
    private static final String LOCK = "lock";
    private final ModelPart lid;
    private final ModelPart bottom;
    private final ModelPart lock;
    private final ModelPart doubleLeftLid;
    private final ModelPart doubleLeftBottom;
    private final ModelPart doubleLeftLock;
    private final ModelPart doubleRightLid;
    private final ModelPart doubleRightBottom;
    private final ModelPart doubleRightLock;

    public ValyrianChestRenderer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);

        ModelPart modelpart = pContext.bakeLayer(ModelLayers.CHEST);
        this.bottom = modelpart.getChild("bottom");
        this.lid = modelpart.getChild("lid");
        this.lock = modelpart.getChild("lock");
        ModelPart modelpart1 = pContext.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT);
        this.doubleLeftBottom = modelpart1.getChild("bottom");
        this.doubleLeftLid = modelpart1.getChild("lid");
        this.doubleLeftLock = modelpart1.getChild("lock");
        ModelPart modelpart2 = pContext.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT);
        this.doubleRightBottom = modelpart2.getChild("bottom");
        this.doubleRightLid = modelpart2.getChild("lid");
        this.doubleRightLock = modelpart2.getChild("lock");
    }

    @Override
    public void render(ValyrianChestBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        Level level = pBlockEntity.getLevel();
        boolean flag = level != null;
        BlockState blockstate = flag ? pBlockEntity.getBlockState() : ModBlocks.VALYRIAN_CHEST.get().defaultBlockState().setValue(ValyrianChestBlock.FACING, Direction.SOUTH);
        ChestType chesttype = blockstate.hasProperty(ValyrianChestBlock.TYPE) ? blockstate.getValue(ValyrianChestBlock.TYPE) : ChestType.SINGLE;
        Block block = blockstate.getBlock();
        if (block instanceof ValyrianChestBlock valyrianChestBlock) {
            boolean flag1 = chesttype != ChestType.SINGLE;
            pPoseStack.pushPose();
            float f = blockstate.getValue(ValyrianChestBlock.FACING).toYRot();
            pPoseStack.translate(0.5F, 0.5F, 0.5F);
            pPoseStack.mulPose(Axis.YP.rotationDegrees(-f));
            pPoseStack.translate(-0.5F, -0.5F, -0.5F);
            DoubleBlockCombiner.NeighborCombineResult<? extends ValyrianChestBlockEntity> neighborcombineresult;
            if (flag) {
                neighborcombineresult = valyrianChestBlock.combine(blockstate, level, pBlockEntity.getBlockPos(), true);
            } else {
                neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float f1 = neighborcombineresult.apply(ValyrianChestBlock.opennessCombiner(pBlockEntity)).get(pPartialTick);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = neighborcombineresult.apply(new BrightnessCombiner<>()).applyAsInt(pPackedLight);
            Material material = this.getMaterial(pBlockEntity, chesttype);
            VertexConsumer vertexconsumer = material.buffer(pBuffer, RenderType::entityCutout);
            if (flag1) {
                if (chesttype == ChestType.LEFT) {
                    this.render(pPoseStack, vertexconsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, pPackedOverlay);
                } else {
                    this.render(pPoseStack, vertexconsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, pPackedOverlay);
                }
            } else {
                this.render(pPoseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, i, pPackedOverlay);
            }

            pPoseStack.popPose();
        }
    }

    private void render(PoseStack pPoseStack, VertexConsumer pConsumer, ModelPart pLidPart, ModelPart pLockPart, ModelPart pBottomPart, float pLidAngle, int pPackedLight, int pPackedOverlay) {
        pLidPart.xRot = -(pLidAngle * ((float)Math.PI / 2F));
        pLockPart.xRot = pLidPart.xRot;
        pLidPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
        pLockPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
        pBottomPart.render(pPoseStack, pConsumer, pPackedLight, pPackedOverlay);
    }

    @Override
    protected @NotNull Material getMaterial(@NotNull ValyrianChestBlockEntity blockEntity, @NotNull ChestType chestType) {
        return switch (chestType) {
            case LEFT -> ValyrianChestRenderer.CHEST_LOCATION_LEFT;
            case RIGHT -> ValyrianChestRenderer.CHEST_LOCATION_RIGHT;
            default -> ValyrianChestRenderer.CHEST_LOCATION;
        };
    }

    private static Material chestMaterial(String pChestName) {
        return new Material(Sheets.CHEST_SHEET, ResourceLocation.withDefaultNamespace("entity/chest/" + pChestName));
    }
}
