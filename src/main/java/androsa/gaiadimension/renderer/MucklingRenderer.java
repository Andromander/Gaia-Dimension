package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SlimeRenderer;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MucklingRenderer extends SlimeRenderer {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "muckling.png");

    public MucklingRenderer(EntityRendererManager manager, float shadowSize) {
        super(manager);
        this.shadowRadius = shadowSize;
    }

    @Override
    public ResourceLocation getTextureLocation(SlimeEntity entity) {
        return textureLoc;
    }
}
