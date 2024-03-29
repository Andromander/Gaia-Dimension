package androsa.gaiadimension.fluids;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;

public abstract class SuperhotMagmaFluid extends BaseFlowingFluid {

    public SuperhotMagmaFluid(Properties props) {
        super(props);
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor iWorld, BlockPos pos, BlockState state) {
        this.triggerEffects(iWorld, pos);
        super.beforeDestroyingBlock(iWorld, pos, state);
    }

    @Override
    protected boolean canBeReplacedWith(FluidState fluidstate, BlockGetter reader, BlockPos pos, Fluid fluid, Direction direction) {
        return fluidstate.getHeight(reader, pos) >= 0.44444445F && fluid.is(FluidTags.WATER);
    }

    @Override
    public void animateTick(Level worldIn, BlockPos pos, FluidState state, RandomSource random) {
        BlockPos blockpos = pos.above();
        if (worldIn.getBlockState(blockpos).isAir() && !worldIn.getBlockState(blockpos).isSolidRender(worldIn, blockpos)) {
            if (random.nextInt(100) == 0) {
                double d0 = (float)pos.getX() + random.nextFloat();
                double d1 = pos.getY() + 1;
                double d2 = (float)pos.getZ() + random.nextFloat();
                worldIn.addParticle(ParticleTypes.LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D); //TODO: Make this Superhot Magma particle
                worldIn.playLocalSound(d0, d1, d2, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }

            if (random.nextInt(200) == 0) {
                worldIn.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.LAVA_AMBIENT, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }
    }

    @Override
    public void randomTick(Level world, BlockPos pos, FluidState state, RandomSource random) {
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
                    } else if (blockstate.blocksMotion()) {
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

    private boolean isSurroundingBlockFlammable(LevelAccessor worldIn, BlockPos pos) {
        for(Direction direction : Direction.values()) {
            if (this.getCanBlockBurn(worldIn, pos.relative(direction))) {
                return true;
            }
        }

        return false;
    }

    private boolean getCanBlockBurn(LevelAccessor worldIn, BlockPos pos) {
        return (pos.getY() < 0 || pos.getY() >= 256 || worldIn.hasChunkAt(pos)) && worldIn.getBlockState(pos).ignitedByLava();
    }

    private void triggerEffects(LevelAccessor world, BlockPos pos) {
        world.levelEvent(LevelEvent.LAVA_FIZZ, pos, 0);
    }
}
