package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.NomadicLagrahkEntity;
import androsa.gaiadimension.model.NomadicLagrahkModel;
import androsa.gaiadimension.registry.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

//TODO: texture locations
public class NomadicLagrahkRenderer<T extends NomadicLagrahkEntity, M extends NomadicLagrahkModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation defaultLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "nomadiclagrahk_none.png");
    private static final ResourceLocation saltyLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "nomadiclagrahk_salty.png");
    private static final ResourceLocation staticLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "nomadiclagrahk_static.png");
    private static final ResourceLocation volcanicLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "nomadiclagrahk_volcanic.png");

    public NomadicLagrahkRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return switch (entity.getEntityVariant()) {
            case 1 -> saltyLoc;
            case 2 -> staticLoc;
            case 3 -> volcanicLoc;
            default -> defaultLoc;
        };
    }
}
