package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MineralArenthisEntity;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.model.MineralArenthisModel;
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
public class MalachiteGuardGlowLayer<T extends MalachiteGuardEntity, M extends MalachiteGuardModel<T>> extends LayerRenderer<T, M> {
    private static final RenderType textureLoc = RenderType.getEyes(new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachiteguard_glow.png"));

    public MalachiteGuardGlowLayer(IEntityRenderer<T, M> arentisRenderIn) {
        super(arentisRenderIn);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int i, T entity, float v, float v1, float v2, float v3, float v4, float v5) {
        IVertexBuilder builder = buffer.getBuffer(textureLoc);
        this.getEntityModel().render(matrixStack, builder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
}
