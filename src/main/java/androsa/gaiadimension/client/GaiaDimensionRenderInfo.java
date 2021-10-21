package androsa.gaiadimension.client;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ISkyRenderHandler;

import javax.annotation.Nonnull;

public class GaiaDimensionRenderInfo extends DimensionSpecialEffects {

    private ISkyRenderHandler skyrender;

    public GaiaDimensionRenderInfo() {
        super(255.0F, true, SkyType.NORMAL, false, false);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float v) {
        return vector3d;
    }

    @Override
    public boolean isFoggyAt(int i, int i1) {
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
