package androsa.gaiadimension.client;

import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.ISkyRenderHandler;

import javax.annotation.Nonnull;

public class GaiaDimensionRenderInfo extends DimensionRenderInfo {

    private ISkyRenderHandler skyrender;

    public GaiaDimensionRenderInfo() {
        super(255.0F, true, FogType.NORMAL, false, false);
    }

    @Override
    public Vector3d func_230494_a_(Vector3d vector3d, float v) {
        return vector3d;
    }

    @Override
    public boolean func_230493_a_(int i, int i1) {
        return false;
    }

    @Nonnull
    @Override
    public ISkyRenderHandler getSkyRenderHandler() {
        if (skyrender == null)
            skyrender = new GaiaSkyRender();
        return skyrender;
    }
}
