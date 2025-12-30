package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.model.renderstate.MalachiteGuardRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class MalachiteGuardGlowLayer<M extends MalachiteGuardModel> extends EyesLayer<MalachiteGuardRenderState, M> {
    private static final RenderType GLOW = RenderTypes.eyes(ModEntitiesRendering.makeTexture("malachite_guard", "glow"));

    public MalachiteGuardGlowLayer(RenderLayerParent<MalachiteGuardRenderState, M> render) {
        super(render);
    }

    public RenderType renderType() {
        return GLOW;
    }
}
