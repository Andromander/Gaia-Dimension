package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.entity.data.GuardPhase;
import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.model.renderstate.MalachiteGuardRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;

public class MalachiteDefenceLayer extends EnergySwirlLayer<MalachiteGuardRenderState, MalachiteGuardModel> {

    private final MalachiteGuardModel model;

    public MalachiteDefenceLayer(RenderLayerParent<MalachiteGuardRenderState, MalachiteGuardModel> renderer, EntityModelSet set) {
        super(renderer);
        this.model = new MalachiteGuardModel(set.bakeLayer(ModEntitiesRendering.MALACHITE_GUARD_DEFENCE));
    }

    @Override
    protected boolean isPowered(MalachiteGuardRenderState state) {
        return state.phase == GuardPhase.DEFENCE;
    }

    @Override
    protected float xOffset(float ticks) {
        return ticks * 0.01F;
    }

    @Override
    protected ResourceLocation getTextureLocation() {
        return ModEntitiesRendering.makeTexture("malachite_guard", "defence");
    }

    @Override
    protected MalachiteGuardModel model() {
        return model;
    }
}
