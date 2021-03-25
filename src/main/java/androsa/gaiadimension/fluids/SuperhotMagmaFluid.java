package androsa.gaiadimension.fluids;

import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.Random;

public abstract class SuperhotMagmaFluid extends ForgeFlowingFluid {

    public SuperhotMagmaFluid(Properties props) {
        super(props);
    }

    @Override
    protected void beforeReplacingBlock(IWorld iWorld, BlockPos pos, BlockState state) {
        this.triggerEffects(iWorld, pos);
    }

    @Override
    protected boolean canDisplace(FluidState fluidstate, IBlockReader reader, BlockPos pos, Fluid fluid, Direction direction) {
        return fluidstate.getActualHeight(reader, pos) >= 0.44444445F && fluid.isIn(FluidTags.WATER);
    }

    @Override
    protected void flowInto(IWorld worldIn, BlockPos pos, BlockState blockStateIn, Direction direction, FluidState fluidStateIn) {
        if (direction == Direction.DOWN) {
            FluidState fluidstate = worldIn.getFluidState(pos);
            if (this.isIn(FluidTags.LAVA) && fluidstate.isTagged(FluidTags.WATER)) {
                if (blockStateIn.getBlock() instanceof FlowingFluidBlock) {
                    worldIn.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, ModBlocks.primal_mass.get().getDefaultState()), 3);
                    this.triggerEffects(worldIn, pos);
                }
                return;
            }
        }

        super.flowInto(worldIn, pos, blockStateIn, direction, fluidStateIn);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(World worldIn, BlockPos pos, FluidState state, Random random) {
        BlockPos blockpos = pos.up();
        if (worldIn.getBlockState(blockpos).isAir() && !worldIn.getBlockState(blockpos).isOpaqueCube(worldIn, blockpos)) {
            if (random.nextInt(100) == 0) {
                double d0 = (float)pos.getX() + random.nextFloat();
                double d1 = pos.getY() + 1;
                double d2 = (float)pos.getZ() + random.nextFloat();
                worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D); //TODO: Make this Superhot Magma particle
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }

            if (random.nextInt(200) == 0) {
                worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }
    }

    @Override
    public void randomTick(World world, BlockPos pos, FluidState state, Random random) {
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
}
