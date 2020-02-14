package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.boss.MalachiteGuardEntity;
import androsa.gaiadimension.model.MalachiteGuardModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MalachiteGuardRenderer<T extends MalachiteGuardEntity, M extends MalachiteGuardModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "malachiteguard.png");

    public MalachiteGuardRenderer(EntityRendererManager manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getEntityTexture(T entity) {
        return textureLoc;
    }
}
