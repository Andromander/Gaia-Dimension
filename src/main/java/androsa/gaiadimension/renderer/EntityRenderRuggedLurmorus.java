package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDRuggedLurmorus;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class EntityRenderRuggedLurmorus extends RenderLiving<GDRuggedLurmorus> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "ruggedlurmorus.png");

    public EntityRenderRuggedLurmorus(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, 1.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(GDRuggedLurmorus par1Entity) {
        return textureLoc;
    }

    @Override
    protected void preRenderCallback(GDRuggedLurmorus entity, float partialTicks) {
        float scale = 2.5F;
        GlStateManager.scale(scale, scale, scale);
    }
}
