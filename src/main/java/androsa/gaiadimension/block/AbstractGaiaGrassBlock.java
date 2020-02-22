package androsa.gaiadimension.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
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
import net.minecraftforge.common.ToolType;

import java.util.Random;

public abstract class AbstractGaiaGrassBlock extends Block implements IGrowable {

    private final Block dirt;

    public AbstractGaiaGrassBlock(MaterialColor color, Block dirtblock) {
        super(Properties.create(Material.ORGANIC, color).hardnessAndResistance(0.9F, 0.0F).sound(SoundType.PLANT).harvestTool(ToolType.SHOVEL).harvestLevel(0).tickRandomly());

        dirt = dirtblock;
    }

    @Override
    @Deprecated
    public void scheduledTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!worldIn.isRemote) {
            if (!worldIn.isAreaLoaded(pos, 3)) return;
            if (!isLightEnough(state, worldIn, pos)) {
                worldIn.setBlockState(pos, dirt.getDefaultState());
            } else if (worldIn.getLight(pos.up()) >= 4) {
                if (worldIn.getLight(pos.up()) >= 9) {
                    BlockState blockstate = this.getDefaultState();

                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                        if (worldIn.getBlockState(blockpos).getBlock() == dirt && canGrowGrass(blockstate, worldIn, blockpos)) {
                            worldIn.setBlockState(blockpos, blockstate);
                        }
                    }
                }

            }
        }
    }

    private static boolean isLightEnough(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        BlockState blockstate = reader.getBlockState(blockpos);

        int i = LightEngine.func_215613_a(reader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getOpacity(reader, blockpos));
        return i < reader.getMaxLightLevel();
    }

    private static boolean canGrowGrass(BlockState state, IWorldReader reader, BlockPos pos) {
        BlockPos blockpos = pos.up();
        return isLightEnough(state, reader, pos) && !reader.getFluidState(blockpos).isTagged(FluidTags.WATER);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        boolean hasWater = world.getBlockState(pos.east()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.west()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.north()).getMaterial() == Material.WATER ||
                world.getBlockState(pos.south()).getMaterial() == Material.WATER;
        return plantable.getPlantType(world, pos.offset(facing)) == PlantType.Plains ||
                plantable.getPlantType(world, pos.offset(facing)) == PlantType.Beach && hasWater;
    }

    @Override
    public boolean canGrow(IBlockReader reader, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }
}
