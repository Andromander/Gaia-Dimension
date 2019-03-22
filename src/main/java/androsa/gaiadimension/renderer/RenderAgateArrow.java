package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDAgateArrowEntity;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAgateArrow extends RenderArrow<GDAgateArrowEntity> {
    public static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "projectiles/agate_arrow.png");

    public RenderAgateArrow(RenderManager manager) {
        super(manager);
    }

    protected ResourceLocation getEntityTexture(GDAgateArrowEntity entity) {
        return textureLoc;
    }
}
