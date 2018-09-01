package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDGummyGlitterBlock extends BlockBreakable implements ModelRegisterCallback {

    public GDGummyGlitterBlock() {
        super(Material.CLAY, true);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setSoundType(SoundType.SLIME);
    }

    @Override
    public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        if (entityIn.isSneaking()) {
            super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
        } else {
            entityIn.fall(fallDistance, 0.0F);
        }
    }

    @Override
    public void onLanded(World worldIn, Entity entityIn) {
        if (entityIn.isSneaking()) {
            super.onLanded(worldIn, entityIn);
        } else if (entityIn.motionY < 0.0D) {
            entityIn.motionY = -entityIn.motionY;

            if (!(entityIn instanceof EntityLivingBase)) {
                entityIn.motionY *= 0.5D;
            }
        }
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (Math.abs(entityIn.motionY) < 0.1D && !entityIn.isSneaking()) {
            double d0 = 0.4D + Math.abs(entityIn.motionY) * 0.2D;
            entityIn.motionX *= d0;
            entityIn.motionZ *= d0;
        }

        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Deprecated
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side) && blockState != blockAccess.getBlockState(pos.offset(side));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }
}
