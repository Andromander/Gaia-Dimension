package androsa.gaiadimension.client;

import com.google.common.collect.Maps;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.pipeline.LightUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Random;

@MethodsReturnNonnullByDefault
public class EmissiveModel implements BakedModel {

    private Map<Direction, List<BakedQuad>> quadsMap = Maps.newHashMap();
    private final BakedModel model;

    public EmissiveModel(BakedModel model) {
        this.model = model;
    }

    @Override
    @Deprecated
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
    @Deprecated
    public TextureAtlasSprite getParticleIcon() {
        return model.getParticleIcon();
    }

    @Override
    public ItemOverrides getOverrides() {
        return model.getOverrides();
    }

    @Override
    @Deprecated
    public ItemTransforms getTransforms() {
        return model.getTransforms();
    }
}
