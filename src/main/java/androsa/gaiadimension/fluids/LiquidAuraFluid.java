package androsa.gaiadimension.fluids;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModFluids;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;

public abstract class LiquidAuraFluid extends FlowingFluid {

    @Override
    public Fluid getFlowingFluid() {
        return ModFluids.liquid_aura_flow;
    }

    @Override
    public Fluid getStillFluid() {
        return ModFluids.liquid_aura_still;
    }

    @Override
    protected boolean canSourcesMultiply() {
        return false;
    }

    @Override
    protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
        TileEntity tileentity = state.getBlock().hasTileEntity(state) ? worldIn.getTileEntity(pos) : null;
        Block.spawnDrops(state, worldIn.getWorld(), pos, tileentity);
    }

    @Override
    protected int getSlopeFindDistance(IWorldReader iWorldReader) {
        return 4;
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
        return ModItems.liquid_aura_bucket;
    }

    @Override
    protected boolean func_215665_a(IFluidState state, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.isIn(FluidTags.WATER);
    }

    @Override
    public int getTickRate(IWorldReader reader) {
        return 10;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        return ModBlocks.liquid_aura.getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    @Override
    public boolean isEquivalentTo(Fluid fluidIn) {
        return fluidIn == ModFluids.liquid_aura_still || fluidIn == ModFluids.liquid_aura_flow;
    }

    @Override
    protected FluidAttributes createAttributes(Fluid fluid) {
        String fluidDir = "fluids/liquidaura/liquid_aura_";
        return FluidAttributes.builder("liquid_aura", new ResourceLocation(GaiaDimensionMod.MODID, fluidDir + "still"), new ResourceLocation(GaiaDimensionMod.MODID, fluidDir + "flow"))
                .color(0xCEB0C0FF)
                .overlay(new ResourceLocation(GaiaDimensionMod.MODID, fluidDir + "overlay"))
                .viscosity(1500)
                .build();
    }

    public static class Flowing extends LiquidAuraFluid {

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

    public static class Source extends LiquidAuraFluid {

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
