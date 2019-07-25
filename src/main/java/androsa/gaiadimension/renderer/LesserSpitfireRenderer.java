package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.LesserSpitfireEntity;
import androsa.gaiadimension.model.LesserSpitfireModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LesserSpitfireRenderer extends MobRenderer<LesserSpitfireEntity, LesserSpitfireModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "lesserspitfire.png");

    public LesserSpitfireRenderer(EntityRendererManager manager, LesserSpitfireModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(LesserSpitfireEntity entity) {
        return textureLoc;
    }
}
