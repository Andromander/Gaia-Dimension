package androsa.gaiadimension.client;

import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.client.renderer.state.SkyRenderState;
import net.neoforged.neoforge.client.CustomSkyboxRenderer;
import org.joml.Matrix4f;

public class GaiaDimensionRenderInfo implements CustomSkyboxRenderer {

    private GaiaSkyRender renderer;

    @Override
    public boolean renderSky(LevelRenderState levelState, SkyRenderState skyState, Matrix4f modelMatrix, Runnable setupFog) {
        if (renderer == null) {
            renderer = new GaiaSkyRender();
        }
        return renderer.render(levelState, skyState, modelMatrix, setupFog);
    }

//    @Override
//    public Vec3 getBrightnessDependentFogColor(Vec3 vector3d, float v) {
//        return vector3d;
//    }
//
//    @Override
//    public boolean isFoggyAt(int i, int i1) {
//        return false;
//    }
}
