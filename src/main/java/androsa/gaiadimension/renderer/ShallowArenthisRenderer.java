package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.ShallowArenthis;
import androsa.gaiadimension.model.ShallowArenthisModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SquidRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class ShallowArenthisRenderer<T extends ShallowArenthis, M extends ShallowArenthisModel> extends MobRenderer<T, SquidRenderState, M> {

    public ShallowArenthisRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
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
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0F - rotationYaw));
        matrixStack.mulPose(Axis.XP.rotationDegrees(entity.xBodyRot));
        matrixStack.mulPose(Axis.YP.rotationDegrees(entity.zBodyRot));
        matrixStack.translate(0.0F, -1.2F, 0.0F);
    }

    @Override
    public Identifier getTextureLocation(SquidRenderState entity) {
        return ModEntitiesRendering.makeTexture("shallow_arenthis");
    }
}
