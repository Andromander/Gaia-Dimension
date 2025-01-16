package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.Muckling;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import androsa.gaiadimension.renderer.layer.MucklingOuterLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class MucklingRenderer extends MobRenderer<Muckling, SlimeRenderState, SlimeModel> {

    public static final ResourceLocation LOCATION = ModEntitiesRendering.makeTexture("muckling");

    public MucklingRenderer(EntityRendererProvider.Context manager, float shadowSize) {
        super(manager, new SlimeModel(manager.bakeLayer(ModelLayers.SLIME)), shadowSize);
        this.addLayer(new MucklingOuterLayer(this, manager.getModelSet()));
    }

    @Override
    public SlimeRenderState createRenderState() {
        return new SlimeRenderState();
    }

    @Override
    public void extractRenderState(Muckling entity, SlimeRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.squish = Mth.lerp(partialTicks, entity.oSquish, entity.squish);
        state.size = entity.getSize();
    }

    @Override
    public ResourceLocation getTextureLocation(SlimeRenderState entity) {
        return LOCATION;
    }

    @Override
    protected float getShadowRadius(SlimeRenderState state) {
        return state.size * 0.25F;
    }

    @Override
    protected void scale(SlimeRenderState state, PoseStack stack) {
        //stack.scale(0.999F, 0.999F, 0.999F);
        //stack.translate(0.0F, 0.001F, 0.0F);
        float size = state.size;
        float squishSize = state.squish / (size * 0.5F + 1.0F);
        float squishScale = 1.0F / (squishSize + 1.0F);
        stack.scale(squishScale * size, 1.0F / squishScale * size, squishScale * size);
    }
}
