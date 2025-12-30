package androsa.gaiadimension.renderer;

import androsa.gaiadimension.entity.GrowthSapper;
import androsa.gaiadimension.model.GrowthSapperModel;
import androsa.gaiadimension.model.renderstate.GrowthSapperRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class GrowthSapperRenderer<T extends GrowthSapper, M extends GrowthSapperModel> extends MobRenderer<T, GrowthSapperRenderState, M> {

    public GrowthSapperRenderer(EntityRendererProvider.Context manager, M model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    public GrowthSapperRenderState createRenderState() {
        return new GrowthSapperRenderState();
    }

    @Override
    public void extractRenderState(T entity, GrowthSapperRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.variant = entity.getEntityVariant();
    }

    @Override
    public Identifier getTextureLocation(GrowthSapperRenderState entity) {
        return ModEntitiesRendering.makeTexture("growth_sapper", entity.variant.getSerializedName());
    }
}
