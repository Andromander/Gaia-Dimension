package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;

public class EntityRenderMuckling extends RenderSlime {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "muckling.png");

    public EntityRenderMuckling(RenderManager manager, float shadowSize) {
        super(manager);
        this.shadowSize = shadowSize;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntitySlime entity) {
        return textureLoc;
    }
}
