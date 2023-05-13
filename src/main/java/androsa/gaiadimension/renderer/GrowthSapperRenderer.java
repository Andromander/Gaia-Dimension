package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.GrowthSapperEntity;
import androsa.gaiadimension.model.GrowthSapperModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrowthSapperRenderer<T extends GrowthSapperEntity, M extends GrowthSapperModel<T>> extends MobRenderer<T, M> {

    public GrowthSapperRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return switch (entity.getEntityVariant()) {
            case 1 -> ModEntitiesRendering.makeTexture(entity, "blue");
            case 2 -> ModEntitiesRendering.makeTexture(entity, "green");
            case 3 -> ModEntitiesRendering.makeTexture(entity, "purple");
            default -> ModEntitiesRendering.makeTexture(entity, "pink");
        };
    }
}
