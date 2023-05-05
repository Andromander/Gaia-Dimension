package androsa.gaiadimension.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

/**
 * You daft bastards.
 * What kind of excuse is there to give TreeFeature the ability to generate *inside* each other?
 * I certainly didn't ask for it.
 * Do datapackers love to have trees merge inside each other?
 * Nay, I say, I think you all just assumed that trees would never do such a thing.
 * I made them do that. Not out of intent, but just because I wanted a pool of 4 trees.
 * Must feel proud about skimping on space checks.
 * All it took was this tiny bit of line down there, you see it?
 * "Is this BlockPos air? Go ahead."
 * What kind of checks do you even have?
 * "Yeah, if the block plants here go ahead, oh, and we can definitely generate on logs"
 * What kind a situation do you need to have trees in trees?
 */
public class StrictTreeFeature extends TreeFeature {

    public StrictTreeFeature(Codec<TreeConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(TreeConfiguration config, WorldGenLevel level, ChunkGenerator generator, RandomSource random, BlockPos pos) {
        if (level.ensureCanWrite(pos)) {
            if (level.getBlockState(pos).isAir()) {
                return super.place(config, level, generator, random, pos);
            }
        }

        return false;
    }
}
