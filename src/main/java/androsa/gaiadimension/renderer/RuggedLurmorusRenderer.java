package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.RuggedLurmorus;
import androsa.gaiadimension.model.RuggedLurmorusModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class RuggedLurmorusRenderer<T extends RuggedLurmorus, M extends RuggedLurmorusModel<T>> extends BasicEntityRenderer<T, M> {

    public RuggedLurmorusRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected void scale(T entity, PoseStack stack, float p_225620_3_) {
        float scale = 2.5F;
        stack.scale(scale, scale, scale);
    }
}
