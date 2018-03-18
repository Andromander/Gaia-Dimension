package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDGenLavaLake extends WorldGenerator {

    private final Block block;

    public GDGenLavaLake(Block blockIn) {
        this.block = blockIn;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos) {

        for (pos = pos.add(-8, 0, -8); pos.getY() > 5 && world.isAirBlock(pos); pos = pos.down()) {
            ;
        }

        if (pos.getY() <= 4) {
            return false;
        } else {
            pos = pos.down(4);
            boolean[] check = new boolean[2048];
            int i = random.nextInt(4) + 4;

            for (int j = 0; j < i; ++j) {
                double d0 = random.nextDouble() * 6.0D + 3.0D;
                double d1 = random.nextDouble() * 4.0D + 2.0D;
                double d2 = random.nextDouble() * 6.0D + 3.0D;
                double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int l = 1; l < 15; ++l) {
                    for (int i1 = 1; i1 < 15; ++i1) {
                        for (int j1 = 1; j1 < 7; ++j1) {
                            double d6 = ((double) l - d3) / (d0 / 2.0D);
                            double d7 = ((double) j1 - d4) / (d1 / 2.0D);
                            double d8 = ((double) i1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D) {
                                check[(l * 16 + i1) * 8 + j1] = true;
                            }
                        }
                    }
                }
            }

            for (int k1 = 0; k1 < 16; ++k1) {
                for (int l2 = 0; l2 < 16; ++l2) {
                    for (int k = 0; k < 8; ++k) {
                        boolean flag = !check[(k1 * 16 + l2) * 8 + k] && (k1 < 15 &&
                                check[((k1 + 1) * 16 + l2) * 8 + k] || k1 > 0 &&
                                check[((k1 - 1) * 16 + l2) * 8 + k] || l2 < 15 &&
                                check[(k1 * 16 + l2 + 1) * 8 + k] || l2 > 0 &&
                                check[(k1 * 16 + (l2 - 1)) * 8 + k] || k < 7 &&
                                check[(k1 * 16 + l2) * 8 + k + 1] || k > 0 &&
                                check[(k1 * 16 + l2) * 8 + (k - 1)]);

                        if (flag) {
                            Material material = world.getBlockState(pos.add(k1, k, l2)).getMaterial();

                            if (k >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (k < 4 && !material.isSolid() && world.getBlockState(pos.add(k1, k, l2)).getBlock() != this.block) {
                                return false;
                            }
                        }
                    }
                }
            }

            for (int l1 = 0; l1 < 16; ++l1) {
                for (int i3 = 0; i3 < 16; ++i3) {
                    for (int i4 = 0; i4 < 8; ++i4) {
                        if (check[(l1 * 16 + i3) * 8 + i4]) {
                            world.setBlockState(pos.add(l1, i4, i3), i4 >= 4 ? Blocks.AIR.getDefaultState() : this.block.getDefaultState(), 2);
                        }
                    }
                }
            }

            for (int i2 = 0; i2 < 16; ++i2) {
                for (int j3 = 0; j3 < 16; ++j3) {
                    for (int j4 = 4; j4 < 8; ++j4) {
                        if (check[(i2 * 16 + j3) * 8 + j4]) {
                            BlockPos blockpos = pos.add(i2, j4 - 1, j3);

                            if (world.getBlockState(blockpos).getBlock() == Blocks.DIRT && world.getLightFor(EnumSkyBlock.SKY, pos.add(i2, j4, j3)) > 0) {
                                Biome biome = world.getBiome(blockpos);

                                if (biome.topBlock.getBlock() == Blocks.MYCELIUM) {
                                    world.setBlockState(blockpos, Blocks.MYCELIUM.getDefaultState(), 2);
                                } else {
                                    world.setBlockState(blockpos, Blocks.GRASS.getDefaultState(), 2);
                                }
                            }
                        }
                    }
                }
            }

            if (this.block.getDefaultState().getMaterial() == Material.LAVA) {
                for (int j2 = 0; j2 < 16; ++j2) {
                    for (int k3 = 0; k3 < 16; ++k3) {
                        for (int k4 = 0; k4 < 8; ++k4) {
                            boolean flag1 = !check[(j2 * 16 + k3) * 8 + k4] && (j2 < 15 &&
                                    check[((j2 + 1) * 16 + k3) * 8 + k4] || j2 > 0 &&
                                    check[((j2 - 1) * 16 + k3) * 8 + k4] || k3 < 15 &&
                                    check[(j2 * 16 + k3 + 1) * 8 + k4] || k3 > 0 &&
                                    check[(j2 * 16 + (k3 - 1)) * 8 + k4] || k4 < 7 &&
                                    check[(j2 * 16 + k3) * 8 + k4 + 1] || k4 > 0 &&
                                    check[(j2 * 16 + k3) * 8 + (k4 - 1)]);

                            if (flag1 && (k4 < 4 || random.nextInt(2) != 0) && world.getBlockState(pos.add(j2, k4, k3)).getMaterial().isSolid()) {
                                world.setBlockState(pos.add(j2, k4, k3), GDBlocks.volcanicRock.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
