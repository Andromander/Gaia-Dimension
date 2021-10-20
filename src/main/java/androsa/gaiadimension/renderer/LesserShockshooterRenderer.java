package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.LesserShockshooterEntity;
import androsa.gaiadimension.model.LesserShockshooterModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LesserShockshooterRenderer<T extends LesserShockshooterEntity, M extends LesserShockshooterModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "lessershockshooter.png");

    public LesserShockshooterRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }
}
