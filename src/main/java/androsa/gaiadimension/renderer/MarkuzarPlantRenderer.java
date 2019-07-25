package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MarkuzarPlantEntity;
import androsa.gaiadimension.model.MarkuzarPlantModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MarkuzarPlantRenderer extends MobRenderer<MarkuzarPlantEntity, MarkuzarPlantModel> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "markuzarplant.png");

    public MarkuzarPlantRenderer(EntityRendererManager manager, MarkuzarPlantModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(MarkuzarPlantEntity entity) {
        return textureLoc;
    }
}
