package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.projectile.AgateArrowEntity;
import androsa.gaiadimension.registry.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class AgateArrowRenderer<T extends AgateArrowEntity> extends ArrowRenderer<T> {
    public static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODID, ModEntitiesRendering.TEXTURE_DIRECTORY + "projectiles/agate_arrow.png");

    public AgateArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }
}
