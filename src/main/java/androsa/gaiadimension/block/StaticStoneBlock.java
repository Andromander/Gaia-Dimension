package androsa.gaiadimension.block;

import androsa.gaiadimension.entity.IShockshooterMob;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StaticStoneBlock extends Block {

    public StaticStoneBlock(Properties props) {
        super(props);
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn) || !(entityIn instanceof IShockshooterMob)) {
            entityIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 2.0F);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
