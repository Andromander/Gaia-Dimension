package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GrowthSapperEntity;
import androsa.gaiadimension.model.GrowthSapperModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrowthSapperRenderer extends MobRenderer<GrowthSapperEntity, GrowthSapperModel<GrowthSapperEntity>> {
    private static final ResourceLocation pinkLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "commongrowthsapper.png");
    private static final ResourceLocation blueLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "chilledgrowthsapper.png");
    private static final ResourceLocation grenLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "nutrientgrowthsapper.png");
    private static final ResourceLocation purpLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "mystifiedgrowthsapper.png");

    public GrowthSapperRenderer(EntityRendererManager manager, GrowthSapperModel<GrowthSapperEntity> model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(GrowthSapperEntity entity) {
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
