package androsa.gaiadimension.world.surface;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.Random;
import java.util.function.Function;

public class VolcanicSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public VolcanicSurfaceBuilder(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> config) {
        super(config);
    }

    @Override
    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int sealevel, long seed, SurfaceBuilderConfig config) {
        this.genVolcanicTerrain(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTop(), config.getUnder(), config.getUnderWaterMaterial(), sealevel);
    }

    public final void genVolcanicTerrain(Random rand, IChunk chunk, Biome biome, int x, int z, int startHeight, double noiseVal, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel) {
        BlockState topBlock = top;
        BlockState middleBlock = middle;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int j = -1;
        int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int posX = x & 15;
        int posZ = z & 15;

        for (int posY = startHeight; posY >= 0; --posY) {
            blockpos$mutableblockpos.setPos(posX, posY, posZ);
            BlockState blockstate2 = chunk.getBlockState(blockpos$mutableblockpos);
            if (blockstate2.isAir()) {
                j = -1;
            } else if (blockstate2.getBlock() == defaultBlock.getBlock()) {
                if (posY > sealevel + 10) {
                    chunk.setBlockState(blockpos$mutableblockpos, ModBlocks.volcanic_rock.get().getDefaultState(), false);
                }
                if (j == -1) {
                    if (k <= 0) {
                        topBlock = AIR;
                        middleBlock = defaultBlock;
                    } else if (posY >= sealevel - 4 && posY <= sealevel + 1) {
                        topBlock = top;
                        middleBlock = middle;
                    }

                    if (posY < sealevel && (topBlock == null || topBlock.isAir())) {
                        if (biome.getTemperature(blockpos$mutableblockpos.setPos(x, posY, z)) < 0.15F) {
                            topBlock = Blocks.ICE.getDefaultState();
                        } else {
                            topBlock = defaultFluid;
                        }
                        blockpos$mutableblockpos.setPos(posX, posY, posZ);
                    }

                    j = k;

                    if (posY >= sealevel - 1) {
                        chunk.setBlockState(blockpos$mutableblockpos, topBlock, false);
                    } else if (posY < sealevel - 7 - k) {
                        topBlock = AIR;
                        middleBlock = defaultBlock;
                        chunk.setBlockState(blockpos$mutableblockpos, bottom, false);
                    } else if (posY > sealevel + 10) {
                        chunk.setBlockState(blockpos$mutableblockpos, ModBlocks.volcanic_rock.get().getDefaultState(), false);
                    } else {
                        chunk.setBlockState(blockpos$mutableblockpos, middleBlock, false);
                    }
                } else if (j > 0) {
                    --j;
                    chunk.setBlockState(blockpos$mutableblockpos, middleBlock, false);

                    if (j == 0 && middleBlock.getBlock() == ModBlocks.salt.get() && k > 1) {
                        j = rand.nextInt(4) + Math.max(0, posY - 63);
                        middleBlock = ModBlocks.saltstone.get().getDefaultState();
                    }
                }
            }
        }
    }
}
