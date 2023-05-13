package androsa.gaiadimension.renderer;

import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class BasicEntityRenderer<T extends Mob, M extends EntityModel<T>> extends MobRenderer<T, M> {

    public BasicEntityRenderer(EntityRendererProvider.Context context, M model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return ModEntitiesRendering.makeTexture(entity);
    }
}
