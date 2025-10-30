package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.model.MineralArenthisModel;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.SquidRenderState;

public class MineralArenthisPartsLayer<M extends MineralArenthisModel> extends EyesLayer<SquidRenderState, M> {
    private static final RenderType GLOW = RenderType.eyes(ModEntitiesRendering.makeTexture("mineral_arenthis", "glow"));

    public MineralArenthisPartsLayer(RenderLayerParent<SquidRenderState, M> arentisRenderIn) {
        super(arentisRenderIn);
    }

    public RenderType renderType() {
        return GLOW;
    }
}
