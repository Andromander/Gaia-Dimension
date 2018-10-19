package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDCorruptSapper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class EntityRenderCorruptSapper extends RenderLiving<GDCorruptSapper> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "corruptgrowthsapper.png");

    public EntityRenderCorruptSapper(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(GDCorruptSapper par1Entity) {
        return textureLoc;
    }
}
