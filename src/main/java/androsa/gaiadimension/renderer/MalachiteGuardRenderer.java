package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.renderer.layer.MalachiteDefenceLayer;
import androsa.gaiadimension.renderer.layer.MalachiteGuardGlowLayer;
import androsa.gaiadimension.renderer.layer.MalachiteResistLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MalachiteGuardRenderer<T extends MalachiteGuardEntity, M extends MalachiteGuardModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachiteguard.png");

    public MalachiteGuardRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
        addLayer(new MalachiteGuardGlowLayer<>(this));
        addLayer(new MalachiteDefenceLayer<>(this, manager.getModelSet()));
        addLayer(new MalachiteResistLayer<>(this, manager.getModelSet()));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }

    @Override
    protected boolean isShaking(T entity) {
        return entity.getChargePhase() == 1;
    }
}