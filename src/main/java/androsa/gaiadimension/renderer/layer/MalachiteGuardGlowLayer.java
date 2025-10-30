package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.model.MalachiteGuardModel;
import androsa.gaiadimension.model.renderstate.MalachiteGuardRenderState;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;

public class MalachiteGuardGlowLayer<M extends MalachiteGuardModel> extends EyesLayer<MalachiteGuardRenderState, M> {
    private static RenderType GLOW;

    public MalachiteGuardGlowLayer(RenderLayerParent<MalachiteGuardRenderState, M> render) {
        super(render);
    }

    public RenderType renderType() {
        if (GLOW == null) {
            GLOW = RenderType.eyes(ModEntitiesRendering.makeTexture("malachite_guard", "glow"));
        }
        return GLOW;
    }
}
