package androsa.gaiadimension.world.surface;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;

import java.util.Random;

public class VolcanicSurfaceBuilder<T extends SurfaceBuilderBaseConfiguration> extends SurfaceBuilder<T> {

    public VolcanicSurfaceBuilder(Codec<T> config) {
        super(config);
    }

    @Override
    public void apply(Random random, ChunkAccess chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int sealevel, int minlevel, long seed, SurfaceBuilderBaseConfiguration config) {
        this.genVolcanicTerrain(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, config.getTopMaterial(), config.getUnderMaterial(), config.getUnderwaterMaterial(), sealevel, minlevel);
    }

    public final void genVolcanicTerrain(Random rand, ChunkAccess chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, BlockState top, BlockState middle, BlockState bottom, int sealevel, int minlevel) {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int i = (int)(noise / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        if (i == 0) {
            boolean flag = false;

            for(int y = startHeight; y >= minlevel; --y) {
                mutable.set(x, y, z);
                BlockState state = chunk.getBlockState(mutable);
                if (state.isAir()) {
                    flag = false;
                } else if (state.is(defaultBlock.getBlock())) {
                    if (!flag) {
                        BlockState newstate;
                         if (y >= sealevel) {
                            newstate = Blocks.AIR.defaultBlockState();
                        } else if (y > sealevel + 10) {
                            newstate = ModBlocks.volcanic_rock.get().defaultBlockState();
                        } else if (y == sealevel - 1) {
                            newstate = biome.getTemperature(mutable) < 0.15F ? Blocks.ICE.defaultBlockState() : defaultFluid;
                        } else if (y >= sealevel - (7 + i)) {
                            newstate = defaultBlock;
                        } else {
                            newstate = bottom;
                        }

                        chunk.setBlockState(mutable, newstate, false);
                    }

                    flag = true;
                }
            }
        } else {
            BlockState blockstate3 = middle;
            int k = -1;

            for(int l = startHeight; l >= minlevel; --l) {
                mutable.set(x, l, z);
                BlockState blockstate4 = chunk.getBlockState(mutable);
                if (blockstate4.isAir()) {
                    k = -1;
                } else if (blockstate4.is(defaultBlock.getBlock())) {
                    if (k == -1) {
                        k = i;
                        BlockState blockstate2;
                        if (l >= sealevel + 2) {
                            blockstate2 = top;
                        } else if (l >= sealevel - 1) {
                            blockstate3 = middle;
                            blockstate2 = top;
                        } else if (l >= sealevel - 4) {
                            blockstate3 = middle;
                            blockstate2 = middle;
                        } else if (l >= sealevel - (7 + i)) {
                            blockstate2 = blockstate3;
                        } else {
                            blockstate3 = defaultBlock;
                            blockstate2 = bottom;
                        }

                        chunk.setBlockState(mutable, blockstate2, false);
                    } else if (k > 0) {
                        --k;
                        chunk.setBlockState(mutable, blockstate3, false);
                        if (k == 0 && blockstate3.is(ModBlocks.salt.get()) && i > 1) {
                            k = rand.nextInt(4) + Math.max(0, l - sealevel);
                            blockstate3 = ModBlocks.saltstone.get().defaultBlockState();
                        }
                    }
                }
            }
        }
    }
}
