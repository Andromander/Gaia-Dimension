package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.RockyLuggerothEntity;
import androsa.gaiadimension.model.RockyLuggerothModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RockyLuggerothRenderer extends MobRenderer<RockyLuggerothEntity, RockyLuggerothModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "rockyluggeroth.png");

    public RockyLuggerothRenderer(EntityRendererManager manager, RockyLuggerothModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(RockyLuggerothEntity entity) {
        return textureLoc;
    }
}
