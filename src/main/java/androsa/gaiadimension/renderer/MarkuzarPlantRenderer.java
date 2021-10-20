package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MarkuzarPlantEntity;
import androsa.gaiadimension.model.MarkuzarPlantModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MarkuzarPlantRenderer<T extends MarkuzarPlantEntity, M extends MarkuzarPlantModel<T>> extends MobRenderer<T, M> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "markuzarplant.png");

    public MarkuzarPlantRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }
}
