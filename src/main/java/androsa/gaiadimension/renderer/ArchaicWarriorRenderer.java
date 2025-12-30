package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.ArchaicWarrior;
import androsa.gaiadimension.model.ArchaicWarriorModel;
import androsa.gaiadimension.model.renderstate.SimpleHumanoidRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NullMarked;

//TODO: Display weapon
@NullMarked
public class ArchaicWarriorRenderer<T extends ArchaicWarrior, M extends ArchaicWarriorModel> extends MobRenderer<T, SimpleHumanoidRenderState, M> {

    public ArchaicWarriorRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
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
    public Identifier getTextureLocation(SimpleHumanoidRenderState entity) {
        return ModEntitiesRendering.makeTexture("archaic_warrior");
    }
}
