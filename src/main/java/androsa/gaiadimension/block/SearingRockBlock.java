package androsa.gaiadimension.block;

import androsa.gaiadimension.entity.ISpitfireMob;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SearingRockBlock extends Block {

    public static final MapCodec<? extends SearingRockBlock> CODEC = simpleCodec(SearingRockBlock::new);

    public SearingRockBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    public void stepOn(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
        if (entityIn instanceof LivingEntity || !(entityIn instanceof ISpitfireMob)) {
            entityIn.hurt(worldIn.damageSources().hotFloor(), 2.0F);
        }

        super.stepOn(worldIn, pos, state, entityIn);
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        BlockPos blockpos = pos.above();
        if (worldIn.getFluidState(pos).is(FluidTags.WATER)) {
            worldIn.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);
            worldIn.sendParticles(ParticleTypes.LARGE_SMOKE, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.25D, (double)blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
        }
    }

    @Override
    @Deprecated
    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.scheduleTick(pos, this, 20);
    }
}
