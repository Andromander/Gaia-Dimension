package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.NomadicLagrahkEntity;
import androsa.gaiadimension.model.NomadicLagrahkModel;
import androsa.gaiadimension.registry.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NomadicLagrahkRenderer<T extends NomadicLagrahkEntity, M extends NomadicLagrahkModel<T>> extends MobRenderer<T, M> {

    public NomadicLagrahkRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return switch (entity.getEntityVariant()) {
            case 1 -> ModEntitiesRendering.makeTexture(entity, "salty");
            case 2 -> ModEntitiesRendering.makeTexture(entity, "static");
            case 3 -> ModEntitiesRendering.makeTexture(entity, "volcanic");
            default -> ModEntitiesRendering.makeTexture(entity, "base");
        };
    }
}
