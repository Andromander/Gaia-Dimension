package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.GrowthSapper;
import androsa.gaiadimension.model.GrowthSapperModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrowthSapperRenderer<T extends GrowthSapper, M extends GrowthSapperModel<T>> extends MobRenderer<T, M> {

    public GrowthSapperRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return ModEntitiesRendering.makeTexture(entity, entity.getEntityVariant().getSerializedName());
    }
}
