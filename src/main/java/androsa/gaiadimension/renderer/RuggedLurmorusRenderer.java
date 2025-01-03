package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.RuggedLurmorus;
import androsa.gaiadimension.model.RuggedLurmorusModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class RuggedLurmorusRenderer<T extends RuggedLurmorus, M extends RuggedLurmorusModel> extends BasicEntityRenderer<T, LivingEntityRenderState, M> {

    public RuggedLurmorusRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, new LivingEntityRenderState(), "rugged_lurmorus", shadowSize);
    }

    @Override
    protected void scale(LivingEntityRenderState entity, PoseStack stack) {
        float scale = 2.5F;
        stack.scale(scale, scale, scale);
    }
}
