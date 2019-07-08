package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.AgateGolemEntity;
import androsa.gaiadimension.model.AgateGolemModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AgateGolemRenderer extends MobRenderer<AgateGolemEntity, AgateGolemModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "agategolem.png");

    public AgateGolemRenderer(EntityRendererManager manager, AgateGolemModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(AgateGolemEntity entity) {
        return textureLoc;
    }
}
