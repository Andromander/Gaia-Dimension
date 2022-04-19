package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.material.Material;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class GaiaLakesFeature<T extends BlockStateConfiguration> extends Feature<T> {
    private static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();

    public GaiaLakesFeature(Codec<T> config) {
        super(config);
    }

    @Override
    public boolean place(FeaturePlaceContext<T> context) {
        return place(context.level(), context.chunkGenerator(), context.random(), context.origin(), context.config());
    }

    public boolean place(WorldGenLevel worldIn, ChunkGenerator generator, Random rand, BlockPos pos, T config) {
        while(pos.getY() > 5 && worldIn.isEmptyBlock(pos)) {
            pos = pos.below();
        }

        if (pos.getY() <= 4) {
            return false;
        } else {
            pos = pos.below(4);
            boolean[] aboolean = new boolean[2048];
            int i = rand.nextInt(4) + 4;

            for(int j = 0; j < i; ++j) {
                double d0 = rand.nextDouble() * 6.0D + 3.0D;
                double d1 = rand.nextDouble() * 4.0D + 2.0D;
                double d2 = rand.nextDouble() * 6.0D + 3.0D;
                double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for(int l = 1; l < 15; ++l) {
                    for(int i1 = 1; i1 < 15; ++i1) {
                        for(int j1 = 1; j1 < 7; ++j1) {
                            double d6 = ((double)l - d3) / (d0 / 2.0D);
                            double d7 = ((double)j1 - d4) / (d1 / 2.0D);
                            double d8 = ((double)i1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                            if (d9 < 1.0D) {
                                aboolean[(l * 16 + i1) * 8 + j1] = true;
                            }
                        }
                    }
                }
            }

            for(int k1 = 0; k1 < 16; ++k1) {
                for(int l2 = 0; l2 < 16; ++l2) {
                    for(int k = 0; k < 8; ++k) {
                        boolean flag = !aboolean[(k1 * 16 + l2) * 8 + k] && (k1 < 15 && aboolean[((k1 + 1) * 16 + l2) * 8 + k] || k1 > 0 && aboolean[((k1 - 1) * 16 + l2) * 8 + k] || l2 < 15 && aboolean[(k1 * 16 + l2 + 1) * 8 + k] || l2 > 0 && aboolean[(k1 * 16 + (l2 - 1)) * 8 + k] || k < 7 && aboolean[(k1 * 16 + l2) * 8 + k + 1] || k > 0 && aboolean[(k1 * 16 + l2) * 8 + (k - 1)]);
                        if (flag) {
                            Material material = worldIn.getBlockState(pos.offset(k1, k, l2)).getMaterial();
                            if (k >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (k < 4 && !material.isSolid() && worldIn.getBlockState(pos.offset(k1, k, l2)) != config.state) {
                                return false;
                            }
                        }
                    }
                }
            }

            for(int l1 = 0; l1 < 16; ++l1) {
                for(int i3 = 0; i3 < 16; ++i3) {
                    for(int i4 = 0; i4 < 8; ++i4) {
                        if (aboolean[(l1 * 16 + i3) * 8 + i4]) {
                            worldIn.setBlock(pos.offset(l1, i4, i3), i4 >= 4 ? AIR : config.state, 2);
                        }
                    }
                }
            }

            if (config.state.getBlock() != ModBlocks.mineral_water.get()) {
                for(int x = 0; x < 16; ++x) {
                    for(int z = 0; z < 16; ++z) {
                        for(int y = 0; y < 8; ++y) {
                            boolean flag1 = !aboolean[(x * 16 + z) * 8 + y] && (x < 15 && aboolean[((x + 1) * 16 + z) * 8 + y] || x > 0 && aboolean[((x - 1) * 16 + z) * 8 + y] || z < 15 && aboolean[(x * 16 + z + 1) * 8 + y] || z > 0 && aboolean[(x * 16 + (z - 1)) * 8 + y] || y < 7 && aboolean[(x * 16 + z) * 8 + y + 1] || y > 0 && aboolean[(x * 16 + z) * 8 + (y - 1)]);
                            if (flag1 && (y < 4 || rand.nextInt(2) != 0) && worldIn.getBlockState(pos.offset(x, y, z)).getMaterial().isSolid()) {

                                if (config.state.getBlock() == ModBlocks.liquid_bismuth.get()) {
                                    if (rand.nextInt(4) == 0) {
                                        worldIn.setBlock(pos.offset(x, y, z), ModBlocks.active_rock.get().defaultBlockState(), 2);
                                    } else {
                                        worldIn.setBlock(pos.offset(x, y, z), ModBlocks.impure_rock.get().defaultBlockState(), 2);
                                    }
                                } else if (config.state.getBlock() == ModBlocks.liquid_aura.get()) {
                                    worldIn.setBlock(pos.offset(x, y, z), ModBlocks.sparkling_rock.get().defaultBlockState(), 2);
                                } else if (config.state.getBlock() == ModBlocks.sweet_muck.get()) {
                                    worldIn.setBlock(pos.offset(x, y, z), ModBlocks.thick_glitter_block.get().defaultBlockState(), 2);
                                } else {
                                    worldIn.setBlock(pos.offset(x, y, z), ModBlocks.volcanic_rock.get().defaultBlockState(), 2);
                                }
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
