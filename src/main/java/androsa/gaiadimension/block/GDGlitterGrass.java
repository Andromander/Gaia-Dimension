package androsa.gaiadimension.block;

import androsa.gaiadimension.biomes.*;
import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.Random;

public class GDGlitterGrass extends GDGaiaGrass {

    public GDGlitterGrass() {
        super(() -> GDBlocks.glitter_grass, () -> GDBlocks.heavy_soil);
    }

    @Override
    @Deprecated
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        Biome biome = worldIn.getBiome(pos);

        if (biome instanceof GDBlueAgateTaiga)
            return MapColor.LIGHT_BLUE;
        if (biome instanceof GDGreenAgateJungle)
            return MapColor.LIME;
        if (biome instanceof GDPurpleAgateSwamp)
            return MapColor.PURPLE;
        if (biome instanceof GDFossilWoodland)
            return MapColor.YELLOW_STAINED_HARDENED_CLAY;
        if (biome instanceof GDMutantAgateWildwood)
            return MapColor.WHITE_STAINED_HARDENED_CLAY;
        if (biome instanceof GDStaticWasteland)
            return MapColor.CYAN;
        if (biome instanceof GDVolcanicLands || biome instanceof GDGoldstoneLands)
            return MapColor.BLACK;
        return MapColor.PINK;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        BlockPos blockpos = pos.up();

        for (int i = 0; i < 128; ++i) {
            BlockPos blockpos1 = blockpos;
            int j = 0;

            while (true) {
                if (j >= i / 16) {
                    if (worldIn.isAirBlock(blockpos1)) {
                        if (rand.nextInt(8) == 0) {
                            worldIn.getBiome(blockpos1).plantFlower(worldIn, rand, blockpos1);
                        } else {
                            IBlockState normalGrowth = GDBlocks.crystal_growth.getDefaultState();
                            IBlockState mutantGrowth = GDBlocks.crystal_growth_mutant.getDefaultState();

                            if (((BlockBush)GDBlocks.crystal_growth).canBlockStay(worldIn, blockpos1, normalGrowth)) {
                                if (worldIn.getBiome(pos) instanceof GDMutantAgateWildwood) {
                                    worldIn.setBlockState(blockpos1, mutantGrowth, 3);
                                } else {
                                    worldIn.setBlockState(blockpos1, normalGrowth, 3);
                                }
                            }
                        }
                    }

                    break;
                }

                blockpos1 = blockpos1.add(rand.nextInt(3) - 1, (rand.nextInt(3) - 1) * rand.nextInt(3) / 2, rand.nextInt(3) - 1);

                if (worldIn.getBlockState(blockpos1.down()).getBlock() != GDBlocks.glitter_grass || worldIn.getBlockState(blockpos1).isNormalCube()) {
                    break;
                }

                ++j;
            }
        }
    }
}
