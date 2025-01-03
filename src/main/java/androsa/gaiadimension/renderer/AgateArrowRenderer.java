package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.projectile.AgateArrow;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;

public class AgateArrowRenderer<T extends AgateArrow> extends ArrowRenderer<T, ArrowRenderState> {
    public static final ResourceLocation textureLoc = ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, ModEntitiesRendering.TEXTURE_DIRECTORY + "projectiles/agate_arrow.png");

    public AgateArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    public ResourceLocation getTextureLocation(ArrowRenderState entity) {
        return textureLoc;
    }
}
