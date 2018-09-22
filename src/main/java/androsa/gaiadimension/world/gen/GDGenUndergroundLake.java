package androsa.gaiadimension.world.gen;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class GDGenUndergroundLake extends WorldGenerator {

    private final Block block;

    public GDGenUndergroundLake(Block blockIn) {
        this.block = blockIn;
    }

        @ParametersAreNonnullByDefault
        public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (position = position.add(-8, 0, -8); position.getY() > 5 && worldIn.isAirBlock(position); position = position.down()) {
            ;
        }

        if (position.getY() <= 4) {
            return false;
        } else {
            position = position.down(4);
            boolean[] aboolean = new boolean[2048];
            int i = rand.nextInt(4) + 4;

            for (int j = 0; j < i; ++j) {
                double d0 = rand.nextDouble() * 8.0D + 5.0D;
                double d1 = rand.nextDouble() * 6.0D + 4.0D;
                double d2 = rand.nextDouble() * 8.0D + 5.0D;
                double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int pointX = 1; pointX < 15; ++pointX) {
                    for (int pointZ = 1; pointZ < 15; ++pointZ) {
                        for (int pointY = 1; pointY < 7; ++pointY) {
                            double dx = ((double)pointX - d3) / (d0 / 2.0D);
                            double dy = ((double)pointY - d4) / (d1 / 2.0D);
                            double dz = ((double)pointZ - d5) / (d2 / 2.0D);
                            double dSize = dx * dx + dy * dy + dz * dz;

                            if (dSize < 1.0D) {
                                aboolean[(pointX * 16 + pointZ) * 8 + pointY] = true;
                            }
                        }
                    }
                }
            }

            for (int posX = 0; posX < 16; ++posX) {
                for (int posZ = 0; posZ < 16; ++posZ) {
                    for (int posY = 0; posY < 8; ++posY) {
                        boolean flag = !aboolean[(posX * 16 + posZ) * 8 + posY] && (posX < 15 && aboolean[((posX + 1) * 16 + posZ) * 8 + posY] ||
                                posX > 0 && aboolean[((posX - 1) * 16 + posZ) * 8 + posY] ||
                                posZ < 15 && aboolean[(posX * 16 + posZ + 1) * 8 + posY] ||
                                posZ > 0 && aboolean[(posX * 16 + (posZ - 1)) * 8 + posY] ||
                                posY < 7 && aboolean[(posX * 16 + posZ) * 8 + posY + 1] ||
                                posY > 0 && aboolean[(posX * 16 + posZ) * 8 + (posY - 1)]);

                        if (flag) {
                            Material material = worldIn.getBlockState(position.add(posX, posY, posZ)).getMaterial();

                            if (posY >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (posY < 4 && !material.isSolid() && worldIn.getBlockState(position.add(posX, posY, posZ)).getBlock() != this.block) {
                                return false;
                            }
                        }
                    }
                }
            }

            for (int pondX = 0; pondX < 16; ++pondX) {
                for (int pondZ = 0; pondZ < 16; ++pondZ) {
                    for (int pondY = 0; pondY < 8; ++pondY) {
                        if (aboolean[(pondX * 16 + pondZ) * 8 + pondY]) {
                            worldIn.setBlockState(position.add(pondX, pondY, pondZ), pondY >= 4 ? Blocks.AIR.getDefaultState() : this.block.getDefaultState(), 2);
                        }
                    }
                }
            }

            for (int grassX = 0; grassX < 16; ++grassX) {
                for (int grassZ = 0; grassZ < 16; ++grassZ) {
                    for (int grassY = 4; grassY < 8; ++grassY) {
                        if (aboolean[(grassX * 16 + grassZ) * 8 + grassY]) {
                            BlockPos blockpos = position.add(grassX, grassY - 1, grassZ);

                            if (worldIn.getBlockState(blockpos).getBlock() == GDBlocks.heavy_soil && worldIn.getLightFor(EnumSkyBlock.SKY, position.add(grassX, grassY, grassZ)) > 0) {
                                Biome biome = worldIn.getBiome(blockpos);

                                if (biome.topBlock.getBlock() == Blocks.MYCELIUM) {
                                    worldIn.setBlockState(blockpos, Blocks.MYCELIUM.getDefaultState(), 2);
                                } else {
                                    worldIn.setBlockState(blockpos, GDBlocks.glitter_grass.getDefaultState(), 2);
                                }
                            }
                        }
                    }
                }
            }

            if (this.block.getDefaultState().getMaterial() == Material.LAVA) {
                for (int volX = 0; volX < 16; ++volX) {
                    for (int volZ = 0; volZ < 16; ++volZ) {
                        for (int volY = 0; volY < 8; ++volY) {
                            boolean flag1 = !aboolean[(volX * 16 + volZ) * 8 + volY] && (volX < 15 && aboolean[((volX + 1) * 16 + volZ) * 8 + volY] || volX > 0 && aboolean[((volX - 1) * 16 + volZ) * 8 + volY] || volZ < 15 && aboolean[(volX * 16 + volZ + 1) * 8 + volY] || volZ > 0 && aboolean[(volX * 16 + (volZ - 1)) * 8 + volY] || volY < 7 && aboolean[(volX * 16 + volZ) * 8 + volY + 1] || volY > 0 && aboolean[(volX * 16 + volZ) * 8 + (volY - 1)]);

                            if (flag1 && (volY < 4 || rand.nextInt(2) != 0) && worldIn.getBlockState(position.add(volX, volY, volZ)).getMaterial().isSolid()) {
                                worldIn.setBlockState(position.add(volX, volY, volZ), GDBlocks.volcanic_rock.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
