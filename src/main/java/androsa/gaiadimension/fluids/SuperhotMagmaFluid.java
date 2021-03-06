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
    protected void beforeDestroyingBlock(IWorld iWorld, BlockPos pos, BlockState state) {
        this.triggerEffects(iWorld, pos);
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
                    worldIn.setBlock(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, ModBlocks.primal_mass.get().defaultBlockState()), 3);
                    this.triggerEffects(worldIn, pos);
                }
                return;
            }
        }

        super.spreadTo(worldIn, pos, blockStateIn, direction, fluidStateIn);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(World worldIn, BlockPos pos, FluidState state, Random random) {
        BlockPos blockpos = pos.above();
        if (worldIn.getBlockState(blockpos).isAir() && !worldIn.getBlockState(blockpos).isSolidRender(worldIn, blockpos)) {
            if (random.nextInt(100) == 0) {
                double d0 = (float)pos.getX() + random.nextFloat();
                double d1 = pos.getY() + 1;
                double d2 = (float)pos.getZ() + random.nextFloat();
                worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D); //TODO: Make this Superhot Magma particle
                worldIn.playLocalSound(d0, d1, d2, SoundEvents.LAVA_POP, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }

            if (random.nextInt(200) == 0) {
                worldIn.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.LAVA_AMBIENT, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }
    }

    @Override
    public void randomTick(World world, BlockPos pos, FluidState state, Random random) {
        if (world.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
            int i = random.nextInt(3);
            if (i > 0) {
                BlockPos blockpos = pos;

                for(int j = 0; j < i; ++j) {
                    blockpos = blockpos.offset(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
                    if (!world.isLoaded(blockpos)) {
                        return;
                    }

                    BlockState blockstate = world.getBlockState(blockpos);
                    if (blockstate.isAir()) {
                        if (this.isSurroundingBlockFlammable(world, blockpos)) {
                            world.setBlockAndUpdate(blockpos, Blocks.FIRE.defaultBlockState());
                            return;
                        }
                    } else if (blockstate.getMaterial().blocksMotion()) {
                        return;
                    }
                }
            } else {
                for(int k = 0; k < 3; ++k) {
                    BlockPos blockpos1 = pos.offset(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                    if (!world.isLoaded(blockpos1)) {
                        return;
                    }

                    if (world.isEmptyBlock(blockpos1.above()) && this.getCanBlockBurn(world, blockpos1)) {
                        world.setBlockAndUpdate(blockpos1.above(), Blocks.FIRE.defaultBlockState());
                    }
                }
            }

        }
    }

    private boolean isSurroundingBlockFlammable(IWorldReader worldIn, BlockPos pos) {
        for(Direction direction : Direction.values()) {
            if (this.getCanBlockBurn(worldIn, pos.relative(direction))) {
                return true;
            }
        }

        return false;
    }

    private boolean getCanBlockBurn(IWorldReader worldIn, BlockPos pos) {
        return (pos.getY() < 0 || pos.getY() >= 256 || worldIn.hasChunkAt(pos)) && worldIn.getBlockState(pos).getMaterial().isFlammable();
    }

    private void triggerEffects(IWorld world, BlockPos pos) {
        world.levelEvent(Constants.WorldEvents.LAVA_EXTINGUISH, pos, 0);
    }
}
