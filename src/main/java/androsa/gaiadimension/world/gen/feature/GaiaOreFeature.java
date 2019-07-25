package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.world.gen.config.GaiaOreFeatureConfig;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public class GaiaOreFeature extends Feature<GaiaOreFeatureConfig> {

    public GaiaOreFeature(Function<Dynamic<?>, ? extends GaiaOreFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, GaiaOreFeatureConfig config) {
        float f = rand.nextFloat() * (float)Math.PI;
        float f1 = (float)config.size / 8.0F;
        int i = MathHelper.ceil(((float)config.size / 16.0F * 2.0F + 1.0F) / 2.0F);
        double xS = (double)((float)pos.getX() + MathHelper.sin(f) * f1);
        double xE = (double)((float)pos.getX() - MathHelper.sin(f) * f1);
        double zS = (double)((float)pos.getZ() + MathHelper.cos(f) * f1);
        double zE = (double)((float)pos.getZ() - MathHelper.cos(f) * f1);
        double yS = (double)(pos.getY() + rand.nextInt(3) - 2);
        double yE = (double)(pos.getY() + rand.nextInt(3) - 2);
        int xSize = pos.getX() - MathHelper.ceil(f1) - i;
        int ySize = pos.getY() - 2 - i;
        int zSize = pos.getZ() - MathHelper.ceil(f1) - i;
        int bit = 2 * (MathHelper.ceil(f1) + i);
        int size = 2 * (2 + i);

        for(int x = xSize; x <= xSize + bit; ++x) {
            for(int z = zSize; z <= zSize + bit; ++z) {
                if (ySize <= worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, x, z)) {
                    return this.genOreShape(worldIn, rand, config, xS, xE, zS, zE, yS, yE, xSize, ySize, zSize, bit, size);
                }
            }
        }

        return false;
    }

    protected boolean genOreShape(IWorld worldIn, Random random, GaiaOreFeatureConfig config, double xMin, double xMax, double zMin, double zMax, double yMin, double yMax, int xSize, int ySize, int zSize, int bit, int bitsize) {
        int i = 0;
        BitSet bitset = new BitSet(bit * bitsize * bit);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        double[] adouble = new double[config.size * 4];

        for(int size = 0; size < config.size; ++size) {
            float f = (float)size / (float)config.size;
            double xLin = MathHelper.lerp((double)f, xMin, xMax);
            double yLin = MathHelper.lerp((double)f, yMin, yMax);
            double zLin = MathHelper.lerp((double)f, zMin, zMax);
            double d6 = random.nextDouble() * (double)config.size / 16.0D;
            double d7 = ((double)(MathHelper.sin((float)Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
            adouble[size * 4 + 0] = xLin;
            adouble[size * 4 + 1] = yLin;
            adouble[size * 4 + 2] = zLin;
            adouble[size * 4 + 3] = d7;
        }

        for(int l2 = 0; l2 < config.size - 1; ++l2) {
            if (!(adouble[l2 * 4 + 3] <= 0.0D)) {
                for(int j3 = l2 + 1; j3 < config.size; ++j3) {
                    if (!(adouble[j3 * 4 + 3] <= 0.0D)) {
                        double d12 = adouble[l2 * 4 + 0] - adouble[j3 * 4 + 0];
                        double d13 = adouble[l2 * 4 + 1] - adouble[j3 * 4 + 1];
                        double d14 = adouble[l2 * 4 + 2] - adouble[j3 * 4 + 2];
                        double d15 = adouble[l2 * 4 + 3] - adouble[j3 * 4 + 3];
                        if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
                            if (d15 > 0.0D) {
                                adouble[j3 * 4 + 3] = -1.0D;
                            } else {
                                adouble[l2 * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        for(int i3 = 0; i3 < config.size; ++i3) {
            double d11 = adouble[i3 * 4 + 3];
            if (!(d11 < 0.0D)) {
                double adX = adouble[i3 * 4 + 0];
                double adY = adouble[i3 * 4 + 1];
                double adZ = adouble[i3 * 4 + 2];
                int xMinSize = Math.max(MathHelper.floor(adX - d11), xSize);
                int yMinSize = Math.max(MathHelper.floor(adY - d11), ySize);
                int zMinSize = Math.max(MathHelper.floor(adZ - d11), zSize);
                int xMaxSize = Math.max(MathHelper.floor(adX + d11), xMinSize);
                int yMaxSize = Math.max(MathHelper.floor(adY + d11), yMinSize);
                int zMaxSize = Math.max(MathHelper.floor(adZ + d11), zMinSize);

                for(int xPos = xMinSize; xPos <= xMaxSize; ++xPos) {
                    double d8 = ((double)xPos + 0.5D - adX) / d11;
                    if (d8 * d8 < 1.0D) {
                        for(int yPos = yMinSize; yPos <= yMaxSize; ++yPos) {
                            double d9 = ((double)yPos + 0.5D - adY) / d11;
                            if (d8 * d8 + d9 * d9 < 1.0D) {
                                for(int zPos = zMinSize; zPos <= zMaxSize; ++zPos) {
                                    double d10 = ((double)zPos + 0.5D - adZ) / d11;
                                    if (d8 * d8 + d9 * d9 + d10 * d10 < 1.0D) {
                                        int k2 = xPos - xSize + (yPos - ySize) * bit + (zPos - zSize) * bit * bitsize;
                                        if (!bitset.get(k2)) {
                                            bitset.set(k2);
                                            blockpos$mutableblockpos.setPos(xPos, yPos, zPos);
                                            if (config.target.getStonePredicate().test(worldIn.getBlockState(blockpos$mutableblockpos))) {
                                                worldIn.setBlockState(blockpos$mutableblockpos, config.state, 2);
                                                ++i;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}
