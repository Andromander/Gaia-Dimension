package androsa.gaiadimension.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class BogPatchFeature<T extends DiskConfiguration> extends Feature<T> {

    public BogPatchFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.random(), context.origin(), context.config());
    }

    public boolean place(WorldGenLevel world, RandomSource random, BlockPos pos, DiskConfiguration config) {
        int range = config.radius().sample(random) + 2;
        int y = pos.getY();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        boolean flag = false;

        for (BlockPos position : BlockPos.betweenClosed(pos.offset(-range, 0, -range), pos.offset(range, 0, range))) {
            int l1 = position.getX() - pos.getX();
            int i2 = position.getZ() - pos.getZ();

            if (l1 * l1 + i2 * i2 <= range * range) {
                flag |= this.place(config, world, random, y + config.halfHeight(), y - config.halfHeight() - 1, mutable.set(position));
            }
        }

        return flag;
    }

    private boolean place(DiskConfiguration config, WorldGenLevel level, RandomSource random, int maxY, int minY, BlockPos.MutableBlockPos mutable) {
        boolean flag = false;

        for (int dy = maxY; dy > minY; --dy) {
           mutable.setY(dy);
            if (config.target().test(level, mutable)) {
                if (random.nextInt(3) != 0) {
                    BlockState state = config.stateProvider().getState(level, random, mutable);
                    level.setBlock(mutable, state, 2);
                    this.markAboveForPostProcessing(level, mutable);
                    flag = true;
                }
            }
        }
        return flag;
    }
}
