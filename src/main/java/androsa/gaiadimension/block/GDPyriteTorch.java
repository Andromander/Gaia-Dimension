package androsa.gaiadimension.block;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.EnumParticlesGD;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDPyriteTorch extends BlockTorch implements ModelRegisterCallback {

    public GDPyriteTorch() {
        super();

        this.setLightLevel(0.5F);
        this.setCreativeTab(GDTabs.tabBlock);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        EnumFacing enumfacing = stateIn.getValue(FACING);
        double d0 = (double)pos.getX() + rand.nextDouble() * 0.5D + 0.2D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 0.7D + 0.2D;
        double d2 = (double)pos.getZ() + rand.nextDouble() * 0.5D + 0.2D;
        double d3 = 0.22D;
        double d4 = 0.27D;

        if (enumfacing.getAxis().isHorizontal()) {
            EnumFacing enumfacing1 = enumfacing.getOpposite();
            GaiaDimension.proxy.spawnParticle(EnumParticlesGD.PYRITE, d0 + d4 * (double)enumfacing1.getXOffset(), d1 + d3, d2 + d4 * (double)enumfacing1.getZOffset(), 0.0D, 0.0D, 0.0D);
        } else {
            GaiaDimension.proxy.spawnParticle(EnumParticlesGD.PYRITE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation((Item.getItemFromBlock(this)).getRegistryName(), "inventory"));
    }
}
