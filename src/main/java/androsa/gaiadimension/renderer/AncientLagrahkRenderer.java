package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.AncientLagrahkEntity;
import androsa.gaiadimension.model.AncientLagrahkModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AncientLagrahkRenderer extends MobRenderer<AncientLagrahkEntity, AncientLagrahkModel> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "ancientlagrahk.png");

    public AncientLagrahkRenderer(EntityRendererManager manager, AncientLagrahkModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(AncientLagrahkEntity entity) {
        return textureLoc;
    }
}
