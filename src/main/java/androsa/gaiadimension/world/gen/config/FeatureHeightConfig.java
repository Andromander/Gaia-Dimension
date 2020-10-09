package androsa.gaiadimension.world.gen.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class FeatureHeightConfig implements IFeatureConfig {

    public static final Codec<FeatureHeightConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Codec.INT.fieldOf("start_height").orElse(0).forGetter((obj) -> obj.startHeight)
            ).apply(instance, FeatureHeightConfig::new));

    public final int startHeight;

    public FeatureHeightConfig(int height) {
        this.startHeight = height;
    }
}
