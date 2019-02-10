package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDGrowthSapper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class EntityRenderGrowthSapper extends RenderLiving<GDGrowthSapper> {
    private static final ResourceLocation pinkLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "commongrowthsapper.png");
    private static final ResourceLocation blueLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "chilledgrowthsapper.png");
    private static final ResourceLocation grenLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "nutrientgrowthsapper.png");
    private static final ResourceLocation purpLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "mystifiedgrowthsapper.png");

    public EntityRenderGrowthSapper(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(GDGrowthSapper entity) {
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
