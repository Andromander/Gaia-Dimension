package androsa.gaiadimension.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class FeatureHeightConfig implements FeatureConfiguration {

    public static final Codec<FeatureHeightConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Codec.INT.fieldOf("start_height").orElse(0).forGetter((obj) -> obj.startHeight)
            ).apply(instance, FeatureHeightConfig::new));

    public final int startHeight;

    public FeatureHeightConfig(int height) {
        this.startHeight = height;
    }
}
