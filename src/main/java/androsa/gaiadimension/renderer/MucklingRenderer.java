package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Slime;

public class MucklingRenderer extends SlimeRenderer {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "muckling.png");

    public MucklingRenderer(EntityRendererProvider.Context manager, float shadowSize) {
        super(manager);
        this.shadowRadius = shadowSize;
    }

    @Override
    public ResourceLocation getTextureLocation(Slime entity) {
        return textureLoc;
    }
}
