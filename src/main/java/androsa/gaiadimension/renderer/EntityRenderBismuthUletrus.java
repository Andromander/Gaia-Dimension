package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDBismuthUletrus;
import androsa.gaiadimension.entity.GDRuggedLurmorus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class EntityRenderBismuthUletrus extends RenderLiving<GDBismuthUletrus> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "bismuthuletrus.png");

    public EntityRenderBismuthUletrus(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(GDBismuthUletrus par1Entity) {
        return textureLoc;
    }
}
