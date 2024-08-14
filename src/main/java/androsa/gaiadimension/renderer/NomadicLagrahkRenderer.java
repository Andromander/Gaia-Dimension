package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.NomadicLagrahk;
import androsa.gaiadimension.model.NomadicLagrahkModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NomadicLagrahkRenderer<T extends NomadicLagrahk, M extends NomadicLagrahkModel<T>> extends MobRenderer<T, M> {

    public NomadicLagrahkRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return ModEntitiesRendering.makeTexture(entity, entity.getEntityVariant().getSerializedName());
    }
}
