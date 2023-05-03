package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.MalachiteDroneEntity;
import androsa.gaiadimension.model.MalachiteDroneModel;
import androsa.gaiadimension.registry.ModEntitiesRendering;
import androsa.gaiadimension.renderer.layer.MalachiteDroneGlowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

//TODO: Texture locations
public class MalachiteDroneRenderer<T extends MalachiteDroneEntity, M extends MalachiteDroneModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation normalLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "malachitedrone_normal.png");
    private static final ResourceLocation followLoc = new ResourceLocation(ModEntitiesRendering.TEXTURE_DIRECTORY + "malachitedrone_follow.png");

    public MalachiteDroneRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
        addLayer(new MalachiteDroneGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return entity.getOwnerUniqueId() != null ? followLoc : normalLoc;
    }
}
