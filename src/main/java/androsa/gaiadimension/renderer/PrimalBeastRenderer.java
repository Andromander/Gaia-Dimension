package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.PrimalBeast;
import androsa.gaiadimension.model.PrimalBeastModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.core.BlockPos;

public class PrimalBeastRenderer<T extends PrimalBeast, M extends PrimalBeastModel> extends BasicEntityRenderer<T, LivingEntityRenderState, M> {

    public PrimalBeastRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, new LivingEntityRenderState(), "primal_beast", shadowSize);
    }

    @Override
    protected int getBlockLightLevel(T entity, BlockPos position) {
        return 15;
    }
}
