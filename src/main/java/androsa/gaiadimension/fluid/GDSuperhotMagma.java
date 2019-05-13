package androsa.gaiadimension.fluid;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class GDSuperhotMagma extends GDFluidBlock {

    public GDSuperhotMagma(Fluid fluid, Material material) {
        super(fluid, material, MapColor.LIGHT_BLUE);
        setLightLevel(1.0F);
        setLightOpacity(0);
    }

    @Override
    public void mixFluids(World world, BlockPos pos) {
        for (EnumFacing side : EnumFacing.VALUES) {
            if (side != EnumFacing.DOWN) {
                IBlockState offset = world.getBlockState(pos.offset(side));
                if (offset.getMaterial().isLiquid()) {
                    if (offset.getBlock() instanceof GDFluidBlock) {
                        if (offset.getBlock() == GDBlocks.mineral_water_block) {
                            world.setBlockState(pos, GDBlocks.gaia_stone.getDefaultState());
                            this.playSound(world, pos);
                            break;
                        }
                        if (offset.getBlock() == GDBlocks.sweet_muck_block) {
                            world.setBlockState(pos, GDBlocks.primal_mass.getDefaultState());
                            this.playSound(world, pos);
                            break;
                        }
                    } else if (offset.getBlock() instanceof BlockFluidBase || offset.getBlock() instanceof BlockLiquid) {
                        if (offset.getMaterial() == Material.WATER) {
                            world.setBlockState(pos, GDBlocks.gaia_cobblestone.getDefaultState());
                            this.playSound(world, pos);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
        super.onEntityCollision(world, pos, state, entity);
        if(!entity.isImmuneToFire()) {
            entity.attackEntityFrom(DamageSource.IN_FIRE, 5.0F);
        }
    }
}
