package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.OpaliteContructEntity;
import androsa.gaiadimension.model.OpaliteConstructModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class OpaliteConstructRenderer<T extends OpaliteContructEntity, M extends OpaliteConstructModel<T>> extends MobRenderer<T, M> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "opalite_construct.png");

    public OpaliteConstructRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, shadow);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }
}
