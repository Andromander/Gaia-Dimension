package androsa.gaiadimension.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class LiquidBismuthFluid extends ForgeFlowingFluid {

    public LiquidBismuthFluid(Properties props) {
        super(props);
    }

    @Override
    protected boolean canBeReplacedWith(FluidState fluidstate, BlockGetter reader, BlockPos pos, Fluid fluid, Direction direction) {
        return fluidstate.getHeight(reader, pos) >= 0.44444445F && fluid.is(FluidTags.WATER);
    }
}
