package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.MutantGrowthExtractorEntity;
import androsa.gaiadimension.model.GrowthExtractorModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrowthExtractorRenderer extends MobRenderer<MutantGrowthExtractorEntity, GrowthExtractorModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "mutantgrowthextractor.png");

    public GrowthExtractorRenderer(EntityRendererManager manager, GrowthExtractorModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(MutantGrowthExtractorEntity entity) {
        return textureLoc;
    }
}
