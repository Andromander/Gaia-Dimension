package androsa.gaiadimension.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDActiveRock extends GDBlock {

    public GDActiveRock() {
        super(Material.ROCK, MapColor.PURPLE_STAINED_HARDENED_CLAY);

        this.setLightLevel(0.5F);
        this.setHardness(15.0F);
        this.setResistance(250.0F);
        this.setHarvestLevel("pickaxe", 2);
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn)) {
            entityIn.attackEntityFrom(DamageSource.MAGIC, 2.0F);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
