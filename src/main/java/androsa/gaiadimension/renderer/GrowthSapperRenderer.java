package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.GrowthSapperEntity;
import androsa.gaiadimension.model.GrowthSapperModel;
import androsa.gaiadimension.registry.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

//TODO: Texture names
public class GrowthSapperRenderer<T extends GrowthSapperEntity, M extends GrowthSapperModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation pinkLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "commongrowthsapper.png");
    private static final ResourceLocation blueLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "chilledgrowthsapper.png");
    private static final ResourceLocation grenLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "nutrientgrowthsapper.png");
    private static final ResourceLocation purpLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "mystifiedgrowthsapper.png");

    public GrowthSapperRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return switch (entity.getEntityVariant()) {
            case 1 -> blueLoc;
            case 2 -> grenLoc;
            case 3 -> purpLoc;
            default -> pinkLoc;
        };
    }
}
