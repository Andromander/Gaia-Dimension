package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.block.AuraShootBlock;
import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public class AuraShootsFeature<T extends NoFeatureConfig> extends Feature<T> {

    private static final BlockState AURA_SHOOT = ModBlocks.aura_shoot.get().getDefaultState();

    public AuraShootsFeature(Function<Dynamic<?>, T> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos position, T config) {

        for (int i = 0; i < 20; ++i) {
            BlockPos blockpos = position.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));

            if (worldIn.isAirBlock(blockpos)) {
                    int j = 7 + rand.nextInt(5);

                    for (int k = 0; k < j; ++k) {
                        if (AURA_SHOOT.isValidPosition(worldIn, blockpos)) {
                            worldIn.setBlockState(blockpos.up(k), AURA_SHOOT.with(AuraShootBlock.IS_TOP, k + 1 == j), 2);
                        }
                    }
                }
            }

        return true;
    }
}
