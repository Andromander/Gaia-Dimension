package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDGoldFire extends Block implements ModelRegisterCallback {

    public GDGoldFire() {
        super(Material.FIRE);
        this.setTickRandomly(true);
        this.setLightLevel(1.0F);
    }

    @Override
    public boolean isBurning(IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    @Deprecated
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return null;
    }

    @Override
    @Deprecated
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos pos2) {
        if(!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP))
            worldIn.setBlockToAir(pos);
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public int tickRate(World worldIn) {
        return 30;
    }

    @Override
    public boolean requiresUpdates() {
        return false;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public void onEntityCollision(World par1World, BlockPos pos, IBlockState state, Entity par5Entity) {
        super.onEntityCollision(par1World, pos, state, par5Entity);

        if(par5Entity instanceof EntityLivingBase)
            ((EntityLivingBase)par5Entity).addPotionEffect(new PotionEffect(MobEffects.GLOWING, 100));
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        if (!((GDGaiaPortal) GDBlocks.gaia_portal).tryToCreatePortal(world, pos)) {
            if (!world.isSideSolid(pos.down(), EnumFacing.UP)) {
                world.setBlockToAir(pos);
            } else {
                world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
            }
        } else {
            world.scheduleUpdate(pos, this, this.tickRate(world) + world.rand.nextInt(10));
        }
    }
}
