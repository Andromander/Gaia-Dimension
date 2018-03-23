package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GDStaticStone extends Block implements ModelRegisterCallback {

    public GDStaticStone() {
        super(Material.ROCK);

        this.setLightLevel(15);
        this.setHardness(50.0F);
        this.setResistance(200.0F);
        this.setCreativeTab(GDTabs.tabBlock);
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if ( entityIn instanceof EntityLivingBase && !EnchantmentHelper.hasFrostWalkerEnchantment((EntityLivingBase)entityIn)) {
            entityIn.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 1.0F);
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
