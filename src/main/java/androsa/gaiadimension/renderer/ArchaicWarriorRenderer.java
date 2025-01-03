package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.ArchaicWarrior;
import androsa.gaiadimension.model.ArchaicWarriorModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

//TODO: Display weapon
public class ArchaicWarriorRenderer<T extends ArchaicWarrior, M extends ArchaicWarriorModel> extends BasicEntityRenderer<T, HumanoidRenderState, M> {

    public ArchaicWarriorRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, new HumanoidRenderState(), "archaic_warrior", shadowSize);
    }
}
