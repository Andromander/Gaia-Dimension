package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.CorruptSapperEntity;
import androsa.gaiadimension.model.GrowthSapperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CorruptSapperRenderer<T extends CorruptSapperEntity, M extends GrowthSapperModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "corruptgrowthsapper.png");

    public CorruptSapperRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }
}
