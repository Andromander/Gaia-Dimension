package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.AncientLagrahkEntity;
import androsa.gaiadimension.model.AncientLagrahkModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AncientLagrahkRenderer extends MobRenderer<AncientLagrahkEntity, AncientLagrahkModel> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "ancientlagrahk.png");

    public AncientLagrahkRenderer(EntityRendererManager manager, AncientLagrahkModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(AncientLagrahkEntity entity) {
        return textureLoc;
    }
}
