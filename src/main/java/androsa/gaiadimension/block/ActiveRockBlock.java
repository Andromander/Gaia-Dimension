package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.bootstrap.GaiaDamage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ActiveRockBlock extends Block {

    public ActiveRockBlock(Properties props) {
        super(props);
    }

    @Override
    public void stepOn(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
        if (entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn)) {
            entityIn.hurt(GaiaDamage.getDamage(worldIn, GaiaDamage.ENERGETIC), 2.0F);
        }

        super.stepOn(worldIn, pos, state, entityIn);
    }
}
