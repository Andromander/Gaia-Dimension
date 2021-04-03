package androsa.gaiadimension.client;

import com.google.common.collect.Maps;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.pipeline.LightUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;

@MethodsReturnNonnullByDefault
public class EmissiveModel implements IBakedModel {

    private Map<Direction, List<BakedQuad>> quadsMap = Maps.newHashMap();
    private final IBakedModel model;

    public EmissiveModel(IBakedModel model) {
        this.model = model;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, Random rand) {
        return quadsMap.computeIfAbsent(direction, (side) -> {
            List<BakedQuad> quads = model.getQuads(state, direction, rand);
            for (BakedQuad quad : quads) {
                if (quad.isTinted()) {
                    LightUtil.setLightData(quad, 0xF000F0);
                }
            }
            return quads;
        });
    }

    @Override
    public boolean useAmbientOcclusion() {
        return model.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return model.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return model.usesBlockLight();
    }

    @Override
    public boolean isCustomRenderer() {
        return model.isCustomRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return model.getParticleIcon();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return model.getOverrides();
    }

    @Override
    @Deprecated
    public ItemCameraTransforms getTransforms() {
        return model.getTransforms();
    }
}
