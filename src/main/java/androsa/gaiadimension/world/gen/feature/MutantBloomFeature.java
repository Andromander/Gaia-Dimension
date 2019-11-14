package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class MutantBloomFeature extends FlowersFeature {

    public MutantBloomFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
        super(config);
    }

    @Override
    public BlockState getRandomFlower(Random random, BlockPos blockPos) {
        if (random.nextInt(4) == 0)
            return ModBlocks.agathum.get().getDefaultState();
        else
            return ModBlocks.ouzium.get().getDefaultState();
    }
}