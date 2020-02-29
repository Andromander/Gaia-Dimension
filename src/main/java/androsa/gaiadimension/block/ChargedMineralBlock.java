package androsa.gaiadimension.block;

import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ChargedMineralBlock extends AbstractGlassBlock {

    //TODO: Contact with this block on any side deals damage
    public ChargedMineralBlock() {
        super(Properties.create(Material.IRON, MaterialColor.CYAN).hardnessAndResistance(4.0F, 15.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.GLASS).nonOpaque());
    }

    @Override
    @Deprecated
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 4.0F);
    }
}
