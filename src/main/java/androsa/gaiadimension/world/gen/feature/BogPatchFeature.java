package androsa.gaiadimension.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class BogPatchFeature<T extends DiskConfiguration> extends Feature<T> {

    public BogPatchFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.random(), context.origin(), context.config());
    }

    public boolean place(WorldGenLevel world, Random random, BlockPos pos, DiskConfiguration config) {
        int range = config.radius().sample(random) + 2;
        int yRange = config.halfHeight();

        for (int dx = pos.getX() - range; dx <= pos.getX() + range; dx++) {
            for (int dz = pos.getZ() - range; dz <= pos.getZ() + range; dz++) {
                int l1 = dx - pos.getX();
                int i2 = dz - pos.getZ();

                if (l1 * l1 + i2 * i2 > range * range) {
                    continue;
                }

                for (int dy = pos.getY() - yRange; dy <= pos.getY() + yRange; dy++) {
                    BlockPos dPos = new BlockPos(dx, dy, dz);
                    Block blockThere = world.getBlockState(dPos).getBlock();
                    for (BlockState state : config.targets()) {
                        if (state.is(blockThere)) {
                            if (random.nextInt(5) != 0) {
                                world.setBlock(dPos, config.state(), 2);
                                this.markAboveForPostProcessing(world, dPos);
                            }
                        }
                    }

                }
            }
        }

        return true;
    }
}
