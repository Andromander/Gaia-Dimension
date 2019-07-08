package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.NomadicLagrahkEntity;
import androsa.gaiadimension.model.NomadicLagrahkModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NomadicLagrahkRenderer extends MobRenderer<NomadicLagrahkEntity, NomadicLagrahkModel> {
    private static final ResourceLocation defaultLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "nomadiclagrahk_none.png");
    private static final ResourceLocation saltyLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "nomadiclagrahk_salty.png");
    private static final ResourceLocation staticLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "nomadiclagrahk_static.png");
    private static final ResourceLocation volcanicLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "nomadiclagrahk_volcanic.png");

    public NomadicLagrahkRenderer(EntityRendererManager manager, NomadicLagrahkModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(NomadicLagrahkEntity entity) {
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
