package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.PrimalBeastEntity;
import androsa.gaiadimension.model.PrimalBeastModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;

public class PrimalBeastRenderer<T extends PrimalBeastEntity, M extends PrimalBeastModel<T>> extends BasicEntityRenderer<T, M> {

    public PrimalBeastRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected int getBlockLightLevel(T entity, BlockPos position) {
        return 15;
    }
}
