package androsa.gaiadimension.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class SinglePlantFeature extends FlowersFeature {

    private final Supplier<Block> plant;

    public SinglePlantFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> config, Supplier<Block> block) {
        super(config);

        plant = block;
    }

    @Override
    public BlockState getRandomFlower(Random random, BlockPos blockPos) {
        return plant.get().getDefaultState();
    }
}
