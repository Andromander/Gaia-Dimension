package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.CavernTickEntity;
import androsa.gaiadimension.model.CavernTickModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CavernTickRenderer extends MobRenderer<CavernTickEntity, CavernTickModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "caverntick.png");

    public CavernTickRenderer(EntityRendererManager manager, CavernTickModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(CavernTickEntity entity) {
        return textureLoc;
    }
}
