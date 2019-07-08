package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.RuggedLurmorusEntity;
import androsa.gaiadimension.model.RuggedLurmorusModel;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RuggedLurmorusRenderer extends MobRenderer<RuggedLurmorusEntity, RuggedLurmorusModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "ruggedlurmorus.png");

    public RuggedLurmorusRenderer(EntityRendererManager manager, RuggedLurmorusModel model, float shadowSize) {
        super(manager, model, 1.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(RuggedLurmorusEntity entity) {
        return textureLoc;
    }

    @Override
    protected void preRenderCallback(RuggedLurmorusEntity entity, float partialTicks) {
        float scale = 2.5F;
        GlStateManager.scalef(scale, scale, scale);
    }
}
