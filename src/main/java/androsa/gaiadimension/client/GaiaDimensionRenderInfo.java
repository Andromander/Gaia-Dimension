package androsa.gaiadimension.client;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

public class GaiaDimensionRenderInfo extends DimensionSpecialEffects {

    public GaiaDimensionRenderInfo() {
        super(255.0F, true, SkyType.NORMAL, false, false);
    }

    @Override
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelMatrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        return GaiaSkyRender.render(partialTick, modelMatrix, level, camera, projectionMatrix, setupFog);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float v) {
        return vector3d;
    }

    @Override
    public boolean isFoggyAt(int i, int i1) {
        return false;
    }
}
