package androsa.gaiadimension.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class TwoBlockStateConfig implements FeatureConfiguration {
    public static final Codec<TwoBlockStateConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BlockState.CODEC.fieldOf("first_state").forGetter((obj) -> obj.first),
                    BlockState.CODEC.fieldOf("second_state").forGetter((obj) -> obj.second)
            ).apply(instance, TwoBlockStateConfig::new));

    public final BlockState first;
    public final BlockState second;

    public TwoBlockStateConfig(BlockState first, BlockState second) {
        this.first = first;
        this.second = second;
    }
}
