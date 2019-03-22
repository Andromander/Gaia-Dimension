package androsa.gaiadimension.block;

import androsa.gaiadimension.entity.IShockshooterMob;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
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

public class GDStaticStone extends GDBlock {

    public GDStaticStone() {
        super(Material.ROCK, MapColor.BLUE_STAINED_HARDENED_CLAY);

        this.setHardness(50.0F);
        this.setResistance(200.0F);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setHarvestLevel("pickaxe", 2);
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn) || !(entityIn instanceof IShockshooterMob)) {
            entityIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 2.0F);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
