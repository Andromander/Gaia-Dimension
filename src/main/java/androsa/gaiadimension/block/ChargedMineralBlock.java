package androsa.gaiadimension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ChargedMineralBlock extends AbstractGlassBlock {

    //TODO: Contact with this block on any side deals damage
    public ChargedMineralBlock(Properties props) {
        super(props);
    }

    @Override
    @Deprecated
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        entityIn.hurt(DamageSource.LIGHTNING_BOLT, 4.0F);
    }
}
