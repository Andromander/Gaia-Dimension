package androsa.gaiadimension.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ActiveRockBlock extends BasicGaiaBlock {

    public ActiveRockBlock() {
        super(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA, 15.0F, 250.0F, SoundType.STONE, ToolType.PICKAXE, 2, 7);
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity) entityIn)) {
            entityIn.attackEntityFrom(DamageSource.MAGIC, 2.0F);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    //TODO: RenderTypeLookup
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public BlockRenderLayer getRenderLayer() {
//        return BlockRenderLayer.CUTOUT;
//    }
}
