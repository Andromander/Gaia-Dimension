package androsa.gaiadimension.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

import java.util.List;
import java.util.Optional;

public record CrystalFungiConfig(int stemHeight, BlockState primaryStem, Optional<List<BlockState>> secondaryStem, BlockState primaryCap, Optional<List<BlockState>> secondaryCap, BlockPredicate canPlace) implements FeatureConfiguration {

    public static final Codec<CrystalFungiConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.intRange(0, 32).fieldOf("stem_height").forGetter(c -> c.stemHeight),
                    BlockState.CODEC.fieldOf("primary_stem_state").forGetter(c -> c.primaryStem),
                    Codec.list(BlockState.CODEC).optionalFieldOf("secondary_stem_state").forGetter(c -> c.secondaryStem),
                    BlockState.CODEC.fieldOf("primary_cap_state").forGetter(c -> c.primaryCap),
                    Codec.list(BlockState.CODEC).optionalFieldOf("secondary_cap_state").forGetter(c -> c.secondaryCap),
                    BlockPredicate.CODEC.fieldOf("can_place_on").forGetter(c -> c.canPlace)
            ).apply(instance, CrystalFungiConfig::new)
    );
}
