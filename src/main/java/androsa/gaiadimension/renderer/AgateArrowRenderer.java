package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AgateArrowRenderer extends ArrowRenderer<AgateArrowEntity> {
    public static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "projectiles/agate_arrow.png");

    public AgateArrowRenderer(EntityRendererManager manager) {
        super(manager);
    }

    protected ResourceLocation getEntityTexture(AgateArrowEntity entity) {
        return textureLoc;
    }
}
