package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDNomadicLagrahk;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class EntityRenderNomadicLagrahk extends RenderLiving<GDNomadicLagrahk> {
    private static final ResourceLocation defaultLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "nomadiclagrahk_none.png");
    private static final ResourceLocation saltyLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "nomadiclagrahk_salty.png");
    private static final ResourceLocation staticLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "nomadiclagrahk_static.png");
    private static final ResourceLocation volcanicLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "nomadiclagrahk_volcanic.png");

    public EntityRenderNomadicLagrahk(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(GDNomadicLagrahk entity) {
        switch(entity.getEntityVariant()) {
            case 1:
                return saltyLoc;
            case 2:
                return staticLoc;
            case 3:
                return volcanicLoc;
            default:
                return defaultLoc;
        }
    }
}
