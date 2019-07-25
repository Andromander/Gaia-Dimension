package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.ShalurkerEntity;
import androsa.gaiadimension.model.ShalurkerModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShalurkerRenderer extends MobRenderer<ShalurkerEntity, ShalurkerModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "shalurker.png");

    public ShalurkerRenderer(EntityRendererManager manager, ShalurkerModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(ShalurkerEntity entity) {
        return textureLoc;
    }
}
