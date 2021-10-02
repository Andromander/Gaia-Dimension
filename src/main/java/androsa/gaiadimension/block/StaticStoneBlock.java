package androsa.gaiadimension.block;

import androsa.gaiadimension.entity.IShockshooterMob;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class StaticStoneBlock extends Block {

    public StaticStoneBlock(Properties props) {
        super(props);
    }

    //TODO: Shocked damage
    @Override
    public void stepOn(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
        if (entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn) || !(entityIn instanceof IShockshooterMob)) {
            entityIn.hurt(DamageSource.LIGHTNING_BOLT, 2.0F);
        }

        super.stepOn(worldIn, pos, state, entityIn);
    }
}
