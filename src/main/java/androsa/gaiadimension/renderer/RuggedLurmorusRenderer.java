package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.RuggedLurmorusEntity;
import androsa.gaiadimension.model.RuggedLurmorusModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RuggedLurmorusRenderer<T extends RuggedLurmorusEntity, M extends RuggedLurmorusModel<T>> extends MobRenderer<T, M> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimensionMod.MODEL_DIR + "ruggedlurmorus.png");

    public RuggedLurmorusRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return textureLoc;
    }

    @Override
    protected void scale(T entity, PoseStack stack, float p_225620_3_) {
        float scale = 2.5F;
        stack.scale(scale, scale, scale);
    }
}
