package androsa.gaiadimension.world.gen.config;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class GaiaTreeFeatureConfig implements IFeatureConfig {

    public static final Codec<GaiaTreeFeatureConfig> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((obj) -> obj.trunkProvider),
                    BlockStateProvider.CODEC.fieldOf("leaves_provider").forGetter((obj) -> obj.leavesProvider),
                    Codec.INT.fieldOf("height").forGetter((obj) -> obj.minHeight),
                    BlockStateProvider.CODEC.fieldOf("sapling").orElse(new SimpleBlockStateProvider(ModBlocks.pink_agate_sapling.get().defaultBlockState())).forGetter((obj) -> obj.sapling)
            ).apply(instance, GaiaTreeFeatureConfig::new));

    public final BlockStateProvider trunkProvider;
    public final BlockStateProvider leavesProvider;
    public final int minHeight;
    public final BlockStateProvider sapling;
    public transient boolean forcePlacement;

    public GaiaTreeFeatureConfig(BlockStateProvider trunk, BlockStateProvider leaves, int height, BlockStateProvider sapling) {
        this.trunkProvider = trunk;
        this.leavesProvider = leaves;
        this.minHeight = height;
        this.sapling = sapling;
    }

    public IPlantable getSapling(Random rand, BlockPos pos) {
        return (IPlantable) sapling.getState(rand, pos).getBlock();
    }

    public void forcePlacement() {
        this.forcePlacement = true;
    }

    public static class Builder {
        private BlockStateProvider trunkProvider;
        private BlockStateProvider leavesProvider;
        private int baseHeight;
        private BlockStateProvider sapling;

        public Builder(BlockStateProvider trunk, BlockStateProvider leaves, int height) {
            this.trunkProvider = trunk;
            this.leavesProvider = leaves;
            this.baseHeight = height;
        }

        public GaiaTreeFeatureConfig.Builder setSapling(SaplingBlock sapling) {
            this.sapling = new SimpleBlockStateProvider(sapling.defaultBlockState());
            return this;
        }

        public GaiaTreeFeatureConfig build() {
            return new GaiaTreeFeatureConfig(trunkProvider, leavesProvider, baseHeight, sapling);
        }
    }
}
