package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.boss.MalachiteGuard;
import androsa.gaiadimension.entity.data.ThreeStagePhase;
import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.model.renderstate.MalachiteGuardRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import androsa.gaiadimension.renderer.layer.MalachiteDefenceLayer;
import androsa.gaiadimension.renderer.layer.MalachiteGuardGlowLayer;
import androsa.gaiadimension.renderer.layer.MalachiteResistLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class MalachiteGuardRenderer<T extends MalachiteGuard> extends MobRenderer<T, MalachiteGuardRenderState, MalachiteGuardModel> {

    public MalachiteGuardRenderer(EntityRendererProvider.Context manager, MalachiteGuardModel model, float shadowSize) {
        super(manager, model, shadowSize);
        addLayer(new MalachiteGuardGlowLayer<>(this));
        addLayer(new MalachiteDefenceLayer(this, manager.getModelSet()));
        addLayer(new MalachiteResistLayer(this, manager.getModelSet()));
    }

    @Override
    public MalachiteGuardRenderState createRenderState() {
        return new MalachiteGuardRenderState();
    }

    @Override
    public void extractRenderState(T entity, MalachiteGuardRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.phase = entity.getPhase();
        state.chargePhase = entity.getChargePhase();
        state.stompPhase = entity.getStompPhase();
    }

    @Override
    public Identifier getTextureLocation(MalachiteGuardRenderState entity) {
        return ModEntitiesRendering.makeTexture("malachite_guard", "");
    }

    @Override
    protected boolean isShaking(MalachiteGuardRenderState entity) {
        return entity.chargePhase == ThreeStagePhase.CHARGE;
    }
}