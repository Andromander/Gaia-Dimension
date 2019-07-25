package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.boss.BlueHowliteWolfEntity;
import androsa.gaiadimension.model.BlueHowliteWolfModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlueHowliteWolfRenderer extends MobRenderer<BlueHowliteWolfEntity, BlueHowliteWolfModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "bluehowlitewolf.png");

    public BlueHowliteWolfRenderer(EntityRendererManager manager, BlueHowliteWolfModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(BlueHowliteWolfEntity entity) {
        return textureLoc;
    }
}
