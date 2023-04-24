package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MookaiteConstructEntity;
import androsa.gaiadimension.model.MookaiteConstructModel;
import androsa.gaiadimension.renderer.layer.MookaiteConstructPartLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MookaiteConstructRenderer<T extends MookaiteConstructEntity, M extends MookaiteConstructModel<T>> extends MobRenderer<T, M> {

    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "mookaite_construct/mookaite_construct.png");

    public MookaiteConstructRenderer(EntityRendererProvider.Context manager, M model, float shadow) {
        super(manager, model, shadow);
        this.addLayer(new MookaiteConstructPartLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }
}
