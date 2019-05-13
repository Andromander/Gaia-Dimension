package androsa.gaiadimension.fluid;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class GDLiquidBismuth extends GDFluidBlock {

    public GDLiquidBismuth(Fluid fluid, Material material) {
        super(fluid, material, MapColor.GRAY);
        setLightOpacity(5);
    }

    @Override
    public void mixFluids(World world, BlockPos pos) {
        for (EnumFacing side : EnumFacing.VALUES) {
            if (side != EnumFacing.DOWN) {
                IBlockState offset = world.getBlockState(pos.offset(side));
                if (offset.getMaterial().isLiquid()) {
                    if (offset.getBlock() == GDBlocks.sweet_muck_block || offset.getBlock() == GDBlocks.superhot_magma_block) {
                        world.setBlockState(pos, GDBlocks.active_rock.getDefaultState());
                        this.playSound(world, pos);
                        break;
                    } else if (offset.getBlock() instanceof BlockFluidBase || offset.getBlock() instanceof BlockLiquid) {
                        if (offset.getMaterial() == Material.WATER) {
                            world.setBlockState(pos, GDBlocks.impure_rock.getDefaultState());
                            this.playSound(world, pos);
                            break;
                        }
                    }
                }
            }
        }
    }
}
