package androsa.gaiadimension.block;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GDAgateCraftingTable extends Block implements ModelRegisterCallback {

    public GDAgateCraftingTable() {
        super(Material.WOOD);

        this.setHardness(1.5F);
        this.setResistance(2.0F);
        this.setSoundType(SoundType.STONE);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setHarvestLevel("axe", 0);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {
            //System.out.println("Block Activated");
            player.openGui(GaiaDimension.instance, CommonProxy.GuiID.AGATE_CRAFT.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
    }
}
