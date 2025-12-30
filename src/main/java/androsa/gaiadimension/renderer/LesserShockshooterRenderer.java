package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.LesserShockshooter;
import androsa.gaiadimension.model.LesserShockshooterModel;
import androsa.gaiadimension.model.renderstate.SimpleHumanoidRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class LesserShockshooterRenderer<T extends LesserShockshooter, M extends LesserShockshooterModel> extends MobRenderer<T, SimpleHumanoidRenderState, M> {
    public LesserShockshooterRenderer(EntityRendererProvider.Context context, M model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public SimpleHumanoidRenderState createRenderState() {
        return new SimpleHumanoidRenderState();
    }

    @Override
    public void extractRenderState(T entity, SimpleHumanoidRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        ArmedEntityRenderState.extractArmedEntityRenderState(entity, state, this.itemModelResolver, partialTicks);
        state.attackTime = entity.getAttackAnim(partialTicks);
    }

    @Override
    public Identifier getTextureLocation(SimpleHumanoidRenderState state) {
        return ModEntitiesRendering.makeTexture("lesser_shockshooter");
    }
}
