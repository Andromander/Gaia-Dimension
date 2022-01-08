package androsa.gaiadimension.fluids;

import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class LiquidBismuthFluid extends ForgeFlowingFluid {

    public LiquidBismuthFluid(Properties props) {
        super(props);
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor iWorld, BlockPos pos, BlockState state) {

    }

    @Override
    protected boolean canBeReplacedWith(FluidState fluidstate, BlockGetter reader, BlockPos pos, Fluid fluid, Direction direction) {
        return fluidstate.getHeight(reader, pos) >= 0.44444445F && fluid.is(FluidTags.WATER);
    }

    @Override
    protected void spreadTo(LevelAccessor worldIn, BlockPos pos, BlockState blockStateIn, Direction direction, FluidState fluidStateIn) {
        if (direction == Direction.DOWN) {
            FluidState fluidstate = worldIn.getFluidState(pos);
            if (this.is(FluidTags.LAVA) && fluidstate.is(FluidTags.WATER)) {
                if (blockStateIn.getBlock() instanceof LiquidBlock) {
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

    private void triggerEffects(LevelAccessor worldIn, BlockPos pos) {
        worldIn.levelEvent(LevelEvent.SOUND_EXTINGUISH_FIRE, pos, 0);
    }
}
