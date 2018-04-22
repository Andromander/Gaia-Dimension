package androsa.gaiadimension.proxy;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.block.container.ContainerAgateCraftingTable;
import androsa.gaiadimension.block.container.ContainerGlitterFurnace;
import androsa.gaiadimension.block.container.ContainerPurifier;
import androsa.gaiadimension.block.tileentity.TileEntityGlitterFurnace;
import androsa.gaiadimension.block.tileentity.TileEntityPurifier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

    public static enum GuiID {
        AGATE_CRAFT,
        GLITTER_FURNACE,
        PURIFIER;
    }

    private void registerTileEntity(Class<? extends TileEntity> cls, String baseName) {
        GameRegistry.registerTileEntity(cls, GaiaDimension.MODID + "." + baseName);
    }

    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        GuiID guiID = GuiID.values()[ID];
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tile = world.getTileEntity(pos);
        Entity entity = world.getEntityByID(x);

        switch (guiID) {
            case AGATE_CRAFT:
                return new ContainerAgateCraftingTable(player.inventory, world, pos);
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
