package androsa.gaiadimension.fluids;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class LiquidBismuthFluid extends ForgeFlowingFluid {

    public LiquidBismuthFluid(Properties props) {
        super(props);
    }

    @Override
    protected void beforeReplacingBlock(IWorld iWorld, BlockPos pos, BlockState state) {

    }

    @Override
    protected boolean canDisplace(FluidState fluidstate, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction) {
        return fluidstate.getActualHeight(reader, pos) >= 0.44444445F && fluid.isIn(FluidTags.WATER);
    }

    @Override
    public int getTickRate(IWorldReader reader) {
        return 20;
    }
}
