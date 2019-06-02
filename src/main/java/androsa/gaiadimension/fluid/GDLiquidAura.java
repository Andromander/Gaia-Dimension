package androsa.gaiadimension.fluid;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class GDLiquidAura extends GDFluidBlock {

    public GDLiquidAura(Fluid fluid, Material material) {
        super(fluid, material, MapColor.SNOW);
    }

    @Override
    public void mixFluids(World world, BlockPos pos) {
        for (EnumFacing side : EnumFacing.VALUES) {
            if (side != EnumFacing.DOWN) {
                IBlockState offset = world.getBlockState(pos.offset(side));
                if (offset.getMaterial().isLiquid()) {
                    if (offset.getBlock() == GDBlocks.superhot_magma_block) {
                        world.setBlockState(pos, GDBlocks.aura_block.getDefaultState());
                        this.playSound(world, pos);
                        break;
                    } else if (offset.getBlock() == GDBlocks.liquid_bismuth_block) {
                        world.setBlockState(pos, GDBlocks.tektite_block.getDefaultState());
                        this.playSound(world, pos);
                        break;
                    } else if (offset.getMaterial() == Material.LAVA) {
                        world.setBlockState(pos, GDBlocks.sparkling_rock.getDefaultState());
                        this.playSound(world, pos);
                        break;
                    }
                }
            }
        }
    }
}
