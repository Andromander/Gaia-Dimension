package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDAncientLagrahk;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class EntityRenderAncientLagrahk extends RenderLiving<GDAncientLagrahk> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "ancientlagrahk.png");

    public EntityRenderAncientLagrahk(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(GDAncientLagrahk par1Entity) {
        return textureLoc;
    }
}
