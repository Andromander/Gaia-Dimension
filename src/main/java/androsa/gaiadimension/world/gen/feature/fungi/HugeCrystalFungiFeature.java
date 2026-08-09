package androsa.gaiadimension.world.gen.feature.fungi;

import androsa.gaiadimension.registry.values.GaiaTags;
import androsa.gaiadimension.world.gen.feature.config.CrystalFungiConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public abstract class HugeCrystalFungiFeature extends Feature<CrystalFungiConfig> {


    public HugeCrystalFungiFeature(Codec<CrystalFungiConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CrystalFungiConfig> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        CrystalFungiConfig config = context.config();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        if (this.isValidPosition(level, origin, config.stemHeight() + getCapHeight(), mutable, config)) {
            this.generateCap(level, random, this.generateStalk(level, random, origin, config), config);
            return true;
        }
        return false;
    }

    protected boolean isValidPosition(WorldGenLevel level, BlockPos origin, int treeHeight, BlockPos.MutableBlockPos blockPos, CrystalFungiConfig config) {
        int y = origin.getY();
        if (y >= level.getMinY() + 1 && y + treeHeight + 1 <= level.getMaxY()) {
            if (!config.canPlace().test(level, origin.below())) {
                return false;
            } else {
                for (int dy = 0; dy <= treeHeight; dy++) {
                    int radius = this.getMaxCapRadius();

                    for (int dx = -radius; dx <= radius; dx++) {
                        for (int dz = -radius; dz <= radius; dz++) {
                            BlockState state = level.getBlockState(blockPos.setWithOffset(origin, dx, dy, dz));
                            if (!state.isAir() && !state.is(BlockTags.LEAVES)) {
                                return false;
                            }
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    protected abstract BlockPos generateStalk(WorldGenLevel level, RandomSource random, BlockPos origin, CrystalFungiConfig config);

    protected abstract void generateCap(WorldGenLevel level, RandomSource rand, BlockPos origin, CrystalFungiConfig config);

    public abstract int getCapHeight();

    protected abstract int getMaxCapRadius();

    protected void placeBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        BlockState replace = level.getBlockState(pos);
        if (replace.isAir() || replace.is(GaiaTags.Blocks.HUGE_CRYSTAL_FUNGI_REPLACEABLES) || replace.canBeReplaced()) {
            this.setBlock(level, pos, state);
        }
    }
}
