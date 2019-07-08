package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.model.MalachiteGuardModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MalachiteGuardRenderer extends MobRenderer<MalachiteGuardEntity, MalachiteGuardModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "malachiteguard.png");

    public MalachiteGuardRenderer(EntityRendererManager manager, MalachiteGuardModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(MalachiteGuardEntity entity) {
        return textureLoc;
    }
}
