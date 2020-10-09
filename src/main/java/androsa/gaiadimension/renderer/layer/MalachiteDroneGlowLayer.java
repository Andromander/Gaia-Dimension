package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MalachiteDroneEntity;
import androsa.gaiadimension.model.MalachiteDroneModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MalachiteDroneGlowLayer<T extends MalachiteDroneEntity, M extends MalachiteDroneModel<T>> extends LayerRenderer<T, M> {
    private static final RenderType normalLoc = RenderType.getEyes(new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachitedrone_normal_glow.png"));
    private static final RenderType followLoc = RenderType.getEyes(new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachitedrone_follow_glow.png"));

    public MalachiteDroneGlowLayer(IEntityRenderer<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int i, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
        IVertexBuilder builder = entity.getOwnerUniqueId() != null ? buffer.getBuffer(followLoc) : buffer.getBuffer(normalLoc);
        this.getEntityModel().render(matrixStack, builder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}