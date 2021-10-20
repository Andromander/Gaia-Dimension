package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MalachiteDroneEntity;
import androsa.gaiadimension.model.MalachiteDroneModel;
import androsa.gaiadimension.renderer.layer.MalachiteDroneGlowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MalachiteDroneRenderer<T extends MalachiteDroneEntity, M extends MalachiteDroneModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation normalLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachitedrone_normal.png");
    private static final ResourceLocation followLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachitedrone_follow.png");

    public MalachiteDroneRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
        addLayer(new MalachiteDroneGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return entity.getOwnerUniqueId() != null ? followLoc : normalLoc;
    }
}
