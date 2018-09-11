package androsa.gaiadimension.proxy;

import androsa.gaiadimension.block.container.*;
import androsa.gaiadimension.block.tileentity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

    public static enum GuiID {
        AGATE_CRAFT,
        SMALL_CRATE,
        GAIA_STONE_FURNACE,
        GLITTER_FURNACE,
        PURIFIER
    }

    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        GuiID guiID = GuiID.values()[ID];
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);

        switch (guiID) {
            case AGATE_CRAFT:
                return new ContainerAgateCraftingTable(player.inventory, world, pos);
            case SMALL_CRATE:
                return new ContainerSmallCrate(player.inventory, (TileEntitySmallCrate) tile);
            case GAIA_STONE_FURNACE:
                return new ContainerGaiaStoneFurnace(player.inventory, (TileEntityGaiaStoneFurnace) tile);
            case GLITTER_FURNACE:
                return new ContainerGlitterFurnace(player.inventory, (TileEntityGlitterFurnace) tile);
            case PURIFIER:
                return new ContainerPurifier(player.inventory, (TileEntityPurifier) tile);
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
    public void doPreLoadRegistration(){;}

    public void doOnLoadRegistration(){;}
    public World getClientWorld() {
        return null;
    }
}
