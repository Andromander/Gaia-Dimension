package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.bootstrap.GaiaDamage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ChargedMineralBlock extends GlassBlock {

    //TODO: Contact with this block on any side deals damage
    public ChargedMineralBlock(Properties props) {
        super(props);
    }

    @Override
    @Deprecated
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        entityIn.hurt(GaiaDamage.getDamage(worldIn, GaiaDamage.STATIC), 4.0F);
    }
}
