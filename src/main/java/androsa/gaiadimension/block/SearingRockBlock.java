package androsa.gaiadimension.block;

import androsa.gaiadimension.entity.ISpitfireMob;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

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
    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
