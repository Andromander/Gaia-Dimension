package androsa.gaiadimension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class GaiaSoilBlock extends Block {

    public GaiaSoilBlock(Properties props) {
        super(props);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        boolean hasWater = false;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockState facingState = world.getBlockState(pos.relative(dir));
            FluidState facingFluid = world.getFluidState(pos.relative(dir));
            hasWater = hasWater || facingState.is(Blocks.FROSTED_ICE) || facingFluid.is(FluidTags.WATER);
        }
        return plantable.getPlantType(world, pos.relative(facing)) == PlantType.PLAINS ||
                plantable.getPlantType(world, pos.relative(facing)) == PlantType.BEACH && hasWater;
    }
}
