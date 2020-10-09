package androsa.gaiadimension.block;

import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChargedMineralBlock extends AbstractGlassBlock {

    //TODO: Contact with this block on any side deals damage
    public ChargedMineralBlock(Properties props) {
        super(props);
    }

    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 4.0F);
    }
}
