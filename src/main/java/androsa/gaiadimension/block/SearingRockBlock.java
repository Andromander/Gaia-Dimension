package androsa.gaiadimension.block;

import androsa.gaiadimension.entity.ISpitfireMob;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SearingRockBlock extends Block {

    public SearingRockBlock(Properties props) {
        super(props);
    }

    public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn) || !(entityIn instanceof ISpitfireMob)) {
            entityIn.hurt(DamageSource.HOT_FLOOR, 2.0F);
        }

        super.stepOn(worldIn, pos, entityIn);
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        BlockPos blockpos = pos.above();
        if (worldIn.getFluidState(pos).is(FluidTags.WATER)) {
            worldIn.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);
            if (worldIn instanceof ServerWorld) {
                worldIn.sendParticles(ParticleTypes.LARGE_SMOKE, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.25D, (double)blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
        }
    }

    @Override
    @Deprecated
    public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getBlockTicks().scheduleTick(pos, this, 20);
    }
}
