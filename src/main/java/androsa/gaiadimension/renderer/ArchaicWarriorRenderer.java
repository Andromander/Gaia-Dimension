package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.ArchaicWarriorEntity;
import androsa.gaiadimension.model.ArchaicWarriorModel;
import androsa.gaiadimension.registry.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

//TODO: Display weapon
public class ArchaicWarriorRenderer<T extends ArchaicWarriorEntity, M extends ArchaicWarriorModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation textureLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "archaic_warrior.png");

    public ArchaicWarriorRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }
}
