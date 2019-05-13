package androsa.gaiadimension.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GDImpureSludge extends GDBlock {

    protected static final AxisAlignedBB SLUDGE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

    public GDImpureSludge() {
        super(Material.GROUND, MapColor.SILVER, SoundType.GROUND);
        setHardness(0.6F);
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return SLUDGE_AABB;
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX *= 0.4D;
        entityIn.motionZ *= 0.4D;
        entityIn.motionY *= 0.1D;
    }
}
