package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SphereReplaceConfig;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class BogPatchFeature<T extends SphereReplaceConfig> extends Feature<T> {

    public BogPatchFeature(Codec<T> configIn) {
        super(configIn);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, T config) {
        int range = random.nextInt(4 - 2) + 2;
        int yRange = 1;

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
                    if (blockThere == ModBlocks.murky_grass.get()) {
                        if (random.nextInt(6) != 0) {
                            world.setBlock(dPos, ModBlocks.impure_sludge.get().defaultBlockState(), 2);
                        }
                    }
                }
            }
        }

        return true;
    }
}
