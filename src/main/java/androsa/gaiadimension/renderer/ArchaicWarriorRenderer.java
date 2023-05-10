package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.ArchaicWarriorEntity;
import androsa.gaiadimension.model.ArchaicWarriorModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

//TODO: Display weapon
public class ArchaicWarriorRenderer<T extends ArchaicWarriorEntity, M extends ArchaicWarriorModel<T>> extends BasicEntityRenderer<T, M> {

    public ArchaicWarriorRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }
}
