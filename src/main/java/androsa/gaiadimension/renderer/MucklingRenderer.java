package androsa.gaiadimension.renderer;

import androsa.gaiadimension.registry.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Slime;

public class MucklingRenderer extends SlimeRenderer {

    public MucklingRenderer(EntityRendererProvider.Context manager, float shadowSize) {
        super(manager);
        this.shadowRadius = shadowSize;
    }

    @Override
    public ResourceLocation getTextureLocation(Slime entity) {
        return ModEntitiesRendering.makeTexture(entity);
    }
}
