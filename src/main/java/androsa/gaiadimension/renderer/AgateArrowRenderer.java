package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AgateArrowRenderer<T extends AgateArrowEntity> extends ArrowRenderer<T> {
    public static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "projectiles/agate_arrow.png");

    public AgateArrowRenderer(EntityRendererManager manager) {
        super(manager);
    }

    public ResourceLocation getEntityTexture(T entity) {
        return textureLoc;
    }
}
