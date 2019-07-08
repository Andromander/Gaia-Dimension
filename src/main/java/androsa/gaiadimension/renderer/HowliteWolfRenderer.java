package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.HowliteWolfEntity;
import androsa.gaiadimension.model.HowliteWolfModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HowliteWolfRenderer extends MobRenderer<HowliteWolfEntity, HowliteWolfModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "howlitewolf.png");

    public HowliteWolfRenderer(EntityRendererManager manager, HowliteWolfModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(HowliteWolfEntity entity) {
        return textureLoc;
    }
}
