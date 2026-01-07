package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.MineralArenthis;
import androsa.gaiadimension.model.MineralArenthisModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import androsa.gaiadimension.renderer.layer.MineralArenthisPartsLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SquidRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class MineralArenthisRenderer<T extends MineralArenthis, M extends MineralArenthisModel> extends MobRenderer<T, SquidRenderState, M> {

    public MineralArenthisRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
        this.addLayer(new MineralArenthisPartsLayer<>(this));
    }

    @Override
    public Identifier getTextureLocation(SquidRenderState entity) {
        return ModEntitiesRendering.makeTexture("mineral_arenthis", "");
    }

    @Override
    public SquidRenderState createRenderState() {
        return new SquidRenderState();
    }

    @Override
    public void extractRenderState(T entity, SquidRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.tentacleAngle = Mth.lerp(partialTicks, entity.lastTentacleAngle, entity.tentacleAngle);
        state.xBodyRot = Mth.lerp(partialTicks, entity.prevArenthisPitch, entity.arenthisPitch);
        state.zBodyRot = Mth.lerp(partialTicks, entity.prevArenthisYaw, entity.arenthisYaw);
    }

    @Override
    protected void setupRotations(SquidRenderState entity, PoseStack matrixStack, float rotationPitch, float rotationYaw) {
        matrixStack.translate(0.0F, 0.5F, 0.0F);
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F - rotationPitch));
        matrixStack.mulPose(Axis.XP.rotationDegrees(entity.xBodyRot));
        matrixStack.mulPose(Axis.YP.rotationDegrees(entity.zBodyRot));
        matrixStack.translate(0.0F, -1.2F, 0.0F);
    }
}
