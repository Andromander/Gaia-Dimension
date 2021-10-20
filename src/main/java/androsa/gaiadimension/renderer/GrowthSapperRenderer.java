package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.GrowthSapperEntity;
import androsa.gaiadimension.model.GrowthSapperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GrowthSapperRenderer<T extends GrowthSapperEntity, M extends GrowthSapperModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation pinkLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "commongrowthsapper.png");
    private static final ResourceLocation blueLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "chilledgrowthsapper.png");
    private static final ResourceLocation grenLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "nutrientgrowthsapper.png");
    private static final ResourceLocation purpLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "mystifiedgrowthsapper.png");

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
