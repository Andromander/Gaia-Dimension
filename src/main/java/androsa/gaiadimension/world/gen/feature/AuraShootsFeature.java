package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.block.AuraShootBlock;
import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class AuraShootsFeature<T extends NoneFeatureConfiguration> extends Feature<T> {

    private static final BlockState AURA_SHOOT = ModBlocks.aura_shoot.get().defaultBlockState();

    public AuraShootsFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.random(), context.origin());
    }

    public boolean place(WorldGenLevel worldIn, RandomSource rand, BlockPos position) {
        for (int i = 0; i < 20; ++i) {
            BlockPos blockpos = position.offset(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));

            if (worldIn.isEmptyBlock(blockpos)) {
                    int j = 7 + rand.nextInt(5);

                    for (int k = 0; k < j; ++k) {
                        if (AURA_SHOOT.canSurvive(worldIn, blockpos)) {
                            worldIn.setBlock(blockpos.above(k), AURA_SHOOT.setValue(AuraShootBlock.IS_TOP, k + 1 == j), 2);
                        }
                    }
                }
            }

        return true;
    }
}
