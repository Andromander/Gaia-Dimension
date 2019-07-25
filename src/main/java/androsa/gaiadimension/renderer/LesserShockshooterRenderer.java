package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.LesserShockshooterEntity;
import androsa.gaiadimension.model.LesserShockshooterModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LesserShockshooterRenderer extends MobRenderer<LesserShockshooterEntity, LesserShockshooterModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "lessershockshooter.png");

    public LesserShockshooterRenderer(EntityRendererManager manager, LesserShockshooterModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(LesserShockshooterEntity entity) {
        return textureLoc;
    }
}
