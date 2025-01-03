package androsa.gaiadimension.renderer;

import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.ResourceLocation;

public class MucklingRenderer extends SlimeRenderer {

    public MucklingRenderer(EntityRendererProvider.Context manager, float shadowSize) {
        super(manager);
        this.shadowRadius = shadowSize;
    }

    @Override
    public ResourceLocation getTextureLocation(SlimeRenderState entity) {
        return ModEntitiesRendering.makeTexture("muckling");
    }
}
