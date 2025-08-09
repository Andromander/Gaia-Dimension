package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.projectile.StaffProjectile;
import androsa.gaiadimension.model.renderstate.MagicProjectileRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class StaffProjectileRenderer extends EntityRenderer<StaffProjectile, MagicProjectileRenderState> {
    private static final ResourceLocation TEXTURE_LOCATION = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "textures/entity/staff_projectile.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(TEXTURE_LOCATION);

    public StaffProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public MagicProjectileRenderState createRenderState() {
        return new MagicProjectileRenderState();
    }

    @Override
    public void extractRenderState(StaffProjectile entity, MagicProjectileRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.element = entity.getElement();
        state.behavior = entity.getBehavior();
        state.stat = entity.getStat();
    }

    protected int getBlockLightLevel(StaffProjectile projectile, BlockPos pos) {
        return 15;
    }

    public void render(MagicProjectileRenderState state, PoseStack stack, MultiBufferSource buffer, int light) {
        stack.pushPose();
        stack.scale(0.65F, 0.65F, 0.65F);
        stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        PoseStack.Pose last = stack.last();
        VertexConsumer vertexconsumer = buffer.getBuffer(RENDER_TYPE);
        vertex(vertexconsumer, last, state, light, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, last, state, light, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, last, state, light, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, last, state, light, 0.0F, 1, 0, 0);
        stack.popPose();
        super.render(state, stack, buffer, light);
    }

    private static void vertex(VertexConsumer consumer, PoseStack.Pose pose, MagicProjectileRenderState state, int light, float x, int y, int u, int v) {
        consumer.addVertex(pose, x - 0.5F, y - 0.25F, 0.0F)
                .setColor(state.element.getColor())
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(pose, 0.0F, 1.0F, 0.0F);
    }
}
