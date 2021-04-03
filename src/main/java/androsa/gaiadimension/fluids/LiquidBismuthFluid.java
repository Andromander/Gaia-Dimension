package androsa.gaiadimension.fluids;

import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class LiquidBismuthFluid extends ForgeFlowingFluid {

    public LiquidBismuthFluid(Properties props) {
        super(props);
    }

    @Override
    protected void beforeDestroyingBlock(IWorld iWorld, BlockPos pos, BlockState state) {

    }

    @Override
    protected boolean canBeReplacedWith(FluidState fluidstate, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction) {
        return fluidstate.getHeight(reader, pos) >= 0.44444445F && fluid.is(FluidTags.WATER);
    }

    @Override
    protected void spreadTo(IWorld worldIn, BlockPos pos, BlockState blockStateIn, Direction direction, FluidState fluidStateIn) {
        if (direction == Direction.DOWN) {
            FluidState fluidstate = worldIn.getFluidState(pos);
            if (this.is(FluidTags.LAVA) && fluidstate.is(FluidTags.WATER)) {
                if (blockStateIn.getBlock() instanceof FlowingFluidBlock) {
                    if (fluidstate.is(GaiaTags.Fluids.LIQUID_AURA)) {
                        worldIn.setBlock(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, ModBlocks.bismuth_block.get().defaultBlockState()), 3);
                    } else {
                        worldIn.setBlock(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, ModBlocks.active_rock.get().defaultBlockState()), 3);
                    }
                    this.triggerEffects(worldIn, pos);
                }
                return;
            }
        }

        super.spreadTo(worldIn, pos, blockStateIn, direction, fluidStateIn);
    }

    private void triggerEffects(IWorld worldIn, BlockPos pos) {
        worldIn.levelEvent(Constants.WorldEvents.LAVA_EXTINGUISH, pos, 0);
    }
}
