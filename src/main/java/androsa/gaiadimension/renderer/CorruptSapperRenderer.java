package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.CorruptSapperEntity;
import androsa.gaiadimension.model.GrowthSapperModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CorruptSapperRenderer extends MobRenderer<CorruptSapperEntity, GrowthSapperModel<CorruptSapperEntity>> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "corruptgrowthsapper.png");

    public CorruptSapperRenderer(EntityRendererManager manager, GrowthSapperModel<CorruptSapperEntity> model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(CorruptSapperEntity entity) {
        return textureLoc;
    }
}
