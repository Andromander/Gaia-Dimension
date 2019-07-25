package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.PrimalBeastEntity;
import androsa.gaiadimension.model.PrimalBeastModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PrimalBeastRenderer extends MobRenderer<PrimalBeastEntity, PrimalBeastModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "primalbeast.png");

    public PrimalBeastRenderer(EntityRendererManager manager, PrimalBeastModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(PrimalBeastEntity entity) {
        return textureLoc;
    }
}
