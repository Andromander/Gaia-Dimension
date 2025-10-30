package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.data.GuardPhase;
import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.model.renderstate.MalachiteGuardRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;

public class MalachiteResistLayer extends EnergySwirlLayer<MalachiteGuardRenderState,MalachiteGuardModel> {

    private final MalachiteGuardModel model;

    public MalachiteResistLayer(RenderLayerParent<MalachiteGuardRenderState, MalachiteGuardModel> renderer, EntityModelSet set) {
        super(renderer);
        this.model = new MalachiteGuardModel(set.bakeLayer(ModEntitiesRendering.MALACHITE_GUARD_RESIST));
    }

    @Override
    protected boolean isPowered(MalachiteGuardRenderState state) {
        return state.phase == GuardPhase.RESIST;
    }

    @Override
    protected float xOffset(float ticks) {
        return ticks * 0.005F;
    }

    @Override
    protected ResourceLocation getTextureLocation() {
        return ModEntitiesRendering.makeTexture("malachite_guard", "resist");
    }

    @Override
    protected MalachiteGuardModel model() {
        return model;
    }
}
