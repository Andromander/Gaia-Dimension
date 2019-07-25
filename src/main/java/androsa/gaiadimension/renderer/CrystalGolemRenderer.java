package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.CrystalGolemEntity;
import androsa.gaiadimension.model.CrystalGolemModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrystalGolemRenderer extends MobRenderer<CrystalGolemEntity, CrystalGolemModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "crystalgolem.png");

    public CrystalGolemRenderer(EntityRendererManager manager, CrystalGolemModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(CrystalGolemEntity entity) {
        return textureLoc;
    }
}
