package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.ContortedNagaEntity;
import androsa.gaiadimension.model.ContortedNagaModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContortedNagaRenderer extends MobRenderer<ContortedNagaEntity, ContortedNagaModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "contortednaga.png");

    public ContortedNagaRenderer(EntityRendererManager manager, ContortedNagaModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(ContortedNagaEntity entity) {
        return textureLoc;
    }
}
