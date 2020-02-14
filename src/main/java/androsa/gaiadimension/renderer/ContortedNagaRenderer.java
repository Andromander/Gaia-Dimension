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
public class ContortedNagaRenderer<T extends ContortedNagaEntity, M extends ContortedNagaModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "contortednaga.png");

    public ContortedNagaRenderer(EntityRendererManager manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return textureLoc;
    }
}
