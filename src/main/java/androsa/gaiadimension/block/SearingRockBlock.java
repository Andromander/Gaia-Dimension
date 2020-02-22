package androsa.gaiadimension.block;

import androsa.gaiadimension.entity.ISpitfireMob;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class SearingRockBlock extends BasicGaiaBlock {

    public SearingRockBlock() {
        super(Material.ROCK, MaterialColor.GRAY_TERRACOTTA, 20.0F, 600.0F, SoundType.STONE, ToolType.PICKAXE, 2, 7);
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn) || !(entityIn instanceof ISpitfireMob)) {
            entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 2.0F);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        BlockPos blockpos = pos.up();
        if (worldIn.getFluidState(pos).isTagged(FluidTags.WATER)) {
            worldIn.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
            if (worldIn instanceof ServerWorld) {
                worldIn.spawnParticle(ParticleTypes.LARGE_SMOKE, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.25D, (double)blockpos.getZ() + 0.5D, 8, 0.5D, 0.25D, 0.5D, 0.0D);
            }
        }
    }

    @Override
    public int tickRate(IWorldReader world) {
        return 20;
    }
}
