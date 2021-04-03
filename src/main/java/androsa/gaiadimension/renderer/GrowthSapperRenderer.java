package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.GrowthSapperEntity;
import androsa.gaiadimension.model.GrowthSapperModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrowthSapperRenderer<T extends GrowthSapperEntity, M extends GrowthSapperModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation pinkLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "commongrowthsapper.png");
    private static final ResourceLocation blueLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "chilledgrowthsapper.png");
    private static final ResourceLocation grenLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "nutrientgrowthsapper.png");
    private static final ResourceLocation purpLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "mystifiedgrowthsapper.png");

    public GrowthSapperRenderer(EntityRendererManager manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        switch(entity.getEntityVariant()) {
            case 0:
                return pinkLoc;
            case 1:
                return blueLoc;
            case 2:
                return grenLoc;
            case 3:
                return purpLoc;
            default:
                return pinkLoc;
        }
    }
}
