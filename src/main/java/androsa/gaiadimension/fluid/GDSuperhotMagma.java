package androsa.gaiadimension.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class GDSuperhotMagma extends GDFluidBlock {

    public GDSuperhotMagma(Fluid fluid, Material material) {
        super(fluid, material);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        super.onEntityCollidedWithBlock(world, pos, state, entity);
        entity.attackEntityFrom(DamageSource.IN_FIRE, 5.0F);
    }
}
