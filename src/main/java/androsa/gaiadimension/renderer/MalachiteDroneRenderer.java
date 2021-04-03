package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MalachiteDroneEntity;
import androsa.gaiadimension.model.MalachiteDroneModel;
import androsa.gaiadimension.renderer.layer.MalachiteDroneGlowLayer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MalachiteDroneRenderer<T extends MalachiteDroneEntity, M extends MalachiteDroneModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation normalLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachitedrone_normal.png");
    private static final ResourceLocation followLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachitedrone_follow.png");

    public MalachiteDroneRenderer(EntityRendererManager manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
        addLayer(new MalachiteDroneGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        if (entity.getOwnerUniqueId() != null)
            return followLoc;
        else
            return normalLoc;
    }
}
