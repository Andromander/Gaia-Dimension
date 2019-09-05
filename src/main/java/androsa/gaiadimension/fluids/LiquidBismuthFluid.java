package androsa.gaiadimension.fluids;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModFluids;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;

public abstract class LiquidBismuthFluid extends FlowingFluid {

    @Override
    public Fluid getFlowingFluid() {
        return ModFluids.liquid_bismuth_flow;
    }

    @Override
    public Fluid getStillFluid() {
        return ModFluids.liquid_bismuth_still;
    }

    @Override
    protected boolean canSourcesMultiply() {
        return false;
    }

    @Override
    protected void beforeReplacingBlock(IWorld iWorld, BlockPos pos, BlockState state) {

    }

    @Override
    protected int getSlopeFindDistance(IWorldReader iWorldReader) {
        return 3;
    }

    @Override
    protected int getLevelDecreasePerBlock(IWorldReader iWorldReader) {
        return 1;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public Item getFilledBucket() {
        return ModItems.liquid_bismuth_bucket;
    }

    @Override
    protected boolean func_215665_a(IFluidState fluidstate, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction) {
        return fluidstate.func_215679_a(reader, pos) >= 0.44444445F && fluid.isIn(FluidTags.WATER);
    }

    @Override
    public int getTickRate(IWorldReader reader) {
        return 20;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        return ModBlocks.liquid_bismuth.getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    @Override
    public boolean isEquivalentTo(Fluid fluidIn) {
        return fluidIn == ModFluids.liquid_bismuth_still || fluidIn == ModFluids.liquid_bismuth_flow;
    }

    @Override
    protected FluidAttributes createAttributes(Fluid fluid) {
        String fluidDir = "fluids/liquidbismuth/liquid_bismuth_";
        return FluidAttributes.builder("liquid_bismuth", new ResourceLocation(GaiaDimensionMod.MODID, fluidDir + "still"), new ResourceLocation(GaiaDimensionMod.MODID, fluidDir + "flow"))
                .density(2500)
                .luminosity(3)
                .overlay(new ResourceLocation(GaiaDimensionMod.MODID, fluidDir + "overlay"))
                .temperature(300)
                .viscosity(3500)
                .build();
    }

    public static class Flowing extends LiquidBismuthFluid {

        @Override
        protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
            super.fillStateContainer(builder);
            builder.add(LEVEL_1_8);
        }

        @Override
        public int getLevel(IFluidState state) {
            return state.get(LEVEL_1_8);
        }

        @Override
        public boolean isSource(IFluidState state) {
            return false;
        }
    }

    public static class Source extends LiquidBismuthFluid {

        @Override
        public int getLevel(IFluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(IFluidState state) {
            return true;
        }
    }
}
