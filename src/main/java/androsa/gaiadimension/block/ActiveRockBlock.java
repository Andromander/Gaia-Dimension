package androsa.gaiadimension.block;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ActiveRockBlock extends Block {

    public ActiveRockBlock(Properties props) {
        super(props);
    }

    @Override
    public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn)) {
            entityIn.hurt(DamageSource.MAGIC, 2.0F);
        }

        super.stepOn(worldIn, pos, entityIn);
    }
}
