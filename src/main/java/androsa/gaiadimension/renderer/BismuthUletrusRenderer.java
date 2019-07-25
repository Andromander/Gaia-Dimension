package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.BismuthUletrusEntity;
import androsa.gaiadimension.model.BismuthUletrusModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BismuthUletrusRenderer extends MobRenderer<BismuthUletrusEntity, BismuthUletrusModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "bismuthuletrus.png");

    public BismuthUletrusRenderer(EntityRendererManager manager, BismuthUletrusModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(BismuthUletrusEntity entity) {
        return textureLoc;
    }
}
