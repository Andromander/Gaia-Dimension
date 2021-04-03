package androsa.gaiadimension.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

import java.util.Random;

public abstract class AbstractGaiaGrassBlock extends Block implements IGrowable {

    private final Block dirt;

    public AbstractGaiaGrassBlock(Properties props, Block dirtblock) {
        super(props);

        dirt = dirtblock;
    }

    @Override
    @Deprecated
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!worldIn.isClientSide()) {
            if (!worldIn.isAreaLoaded(pos, 3)) return;
            if (!isLightEnough(state, worldIn, pos)) {
                worldIn.setBlockAndUpdate(pos, dirt.defaultBlockState());
            } else if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9) {
                BlockState blockstate = this.defaultBlockState();

                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                    if (worldIn.getBlockState(blockpos).getBlock() == dirt && isValidBonemealTargetGrass(blockstate, worldIn, blockpos)) {
                        worldIn.setBlockAndUpdate(blockpos, blockstate);
                    }
                }
            }
        }
    }

    private static boolean isLightEnough(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = reader.getBlockState(blockpos);

        int i = LightEngine.getLightBlockInto(reader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(reader, blockpos));
        return i < reader.getMaxLightLevel();
    }

    private static boolean isValidBonemealTargetGrass(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return isLightEnough(state, reader, pos) && !reader.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        boolean hasWater = world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.south()).getMaterial() == Material.WATER;
        return plantable.getPlantType(world, pos.relative(facing)) == PlantType.PLAINS ||
                plantable.getPlantType(world, pos.relative(facing)) == PlantType.BEACH && hasWater;
    }

    @Override
    public boolean isValidBonemealTarget(IBlockReader reader, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }
}
