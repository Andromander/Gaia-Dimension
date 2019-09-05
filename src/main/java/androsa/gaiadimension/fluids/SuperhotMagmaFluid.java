package androsa.gaiadimension.fluids;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModFluids;
import androsa.gaiadimension.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fluids.FluidAttributes;

import java.util.Random;

public abstract class SuperhotMagmaFluid extends FlowingFluid {

    @Override
    public Fluid getFlowingFluid() {
        return ModFluids.superhot_magma_flow;
    }

    @Override
    public Fluid getStillFluid() {
        return ModFluids.superhot_magma_still;
    }

    @Override
    protected boolean canSourcesMultiply() {
        return false;
    }

    @Override
    protected void beforeReplacingBlock(IWorld iWorld, BlockPos pos, BlockState state) {
        this.triggerEffects(iWorld, pos);
    }

    @Override
    protected int getSlopeFindDistance(IWorldReader iWorldReader) {
        return 2;
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
        return ModItems.superhot_magma_bucket;
    }

    @Override
    protected boolean func_215665_a(IFluidState fluidstate, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction) {
        return fluidstate.func_215679_a(reader, pos) >= 0.44444445F && fluid.isIn(FluidTags.WATER);
    }

    @Override
    public int getTickRate(IWorldReader p_205569_1_) {
        return 30;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    protected BlockState getBlockState(IFluidState state) {
        return ModBlocks.superhot_magma.getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(World worldIn, BlockPos pos, IFluidState state, Random random) {
        BlockPos blockpos = pos.up();
        if (worldIn.getBlockState(blockpos).isAir() && !worldIn.getBlockState(blockpos).isOpaqueCube(worldIn, blockpos)) {
            if (random.nextInt(100) == 0) {
                double d0 = (double)((float)pos.getX() + random.nextFloat());
                double d1 = (double)(pos.getY() + 1);
                double d2 = (double)((float)pos.getZ() + random.nextFloat());
                worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D); //TODO: Make this Superhot Magma particle
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }

            if (random.nextInt(200) == 0) {
                worldIn.playSound((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }
    }

    @Override
    public void randomTick(World world, BlockPos pos, IFluidState state, Random random) {
        if (world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
            int i = random.nextInt(3);
            if (i > 0) {
                BlockPos blockpos = pos;

                for(int j = 0; j < i; ++j) {
                    blockpos = blockpos.add(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
                    if (!world.isBlockPresent(blockpos)) {
                        return;
                    }

                    BlockState blockstate = world.getBlockState(blockpos);
                    if (blockstate.isAir()) {
                        if (this.isSurroundingBlockFlammable(world, blockpos)) {
                            world.setBlockState(blockpos, Blocks.FIRE.getDefaultState());
                            return;
                        }
                    } else if (blockstate.getMaterial().blocksMovement()) {
                        return;
                    }
                }
            } else {
                for(int k = 0; k < 3; ++k) {
                    BlockPos blockpos1 = pos.add(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                    if (!world.isBlockPresent(blockpos1)) {
                        return;
                    }

                    if (world.isAirBlock(blockpos1.up()) && this.getCanBlockBurn(world, blockpos1)) {
                        world.setBlockState(blockpos1.up(), Blocks.FIRE.getDefaultState());
                    }
                }
            }

        }
    }

    private boolean isSurroundingBlockFlammable(IWorldReader worldIn, BlockPos pos) {
        for(Direction direction : Direction.values()) {
            if (this.getCanBlockBurn(worldIn, pos.offset(direction))) {
                return true;
            }
        }

        return false;
    }

    private boolean getCanBlockBurn(IWorldReader worldIn, BlockPos pos) {
        return (pos.getY() < 0 || pos.getY() >= 256 || worldIn.isBlockLoaded(pos)) && worldIn.getBlockState(pos).getMaterial().isFlammable();
    }

    private void triggerEffects(IWorld world, BlockPos pos) {
        world.playEvent(Constants.WorldEvents.LAVA_EXTINGUISH, pos, 0);
    }

    @Override
    public boolean isEquivalentTo(Fluid fluidIn) {
        return fluidIn == ModFluids.superhot_magma_still || fluidIn == ModFluids.superhot_magma_flow;
    }

    @Override
    protected FluidAttributes createAttributes(Fluid fluid) {
        String fluidDir = "fluids/superhotmagma/superhot_magma_";
        return FluidAttributes.builder("superhot_magma", new ResourceLocation(GaiaDimensionMod.MODID, fluidDir + "still"), new ResourceLocation(GaiaDimensionMod.MODID, fluidDir + "flow"))
                .color(0xFF00FFFF)
                .density(4000)
                .luminosity(15)
                .temperature(2000)
                .viscosity(4000)
                .build();
    }

    public static class Flowing extends SuperhotMagmaFluid {

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

    public static class Source extends SuperhotMagmaFluid {

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
