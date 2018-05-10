package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDMarkuzarPlant;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class EntityRenderMarkuzarPlant extends RenderLiving<GDMarkuzarPlant> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "markuzarplant.png");

    public EntityRenderMarkuzarPlant(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(GDMarkuzarPlant par1Entity) {
        return textureLoc;
    }
}
