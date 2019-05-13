package androsa.gaiadimension.proxy;

import androsa.gaiadimension.block.container.*;
import androsa.gaiadimension.block.tileentity.*;
import androsa.gaiadimension.item.GDGemstonePouch;
import androsa.gaiadimension.item.inventory.ContainerGemPouch;
import androsa.gaiadimension.item.inventory.InventoryGemPouch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

    public enum GuiID {
        AGATE_CRAFT,
        SMALL_CRATE,
        LARGE_CRATE,
        GAIA_STONE_FURNACE,
        GLITTER_FURNACE,
        PURIFIER,
        GEM_POUCH
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
            case LARGE_CRATE:
                return new ContainerLargeCrate(player.inventory, (TileEntityLargeCrate) tile);
            case GAIA_STONE_FURNACE:
                return new ContainerGaiaStoneFurnace(player.inventory, (TileEntityGaiaStoneFurnace) tile);
            case GLITTER_FURNACE:
                return new ContainerRestructurer(player.inventory, (TileEntityRestructurer) tile);
            case PURIFIER:
                return new ContainerPurifier(player.inventory, (TileEntityPurifier) tile);
            case GEM_POUCH:
                ItemStack item = player.getHeldItemMainhand();
                if(item.isEmpty() || !(item.getItem() instanceof GDGemstonePouch)) {
                    item = player.getHeldItemOffhand();
                }
                if(!item.isEmpty() && item.getItem() instanceof GDGemstonePouch) {
                    String name = item.hasDisplayName() ? item.getDisplayName() : "container.gaiadimension.gem_pouch";
                    return new ContainerGemPouch(player.inventory, new InventoryGemPouch(item, name));
                }
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public void doPreLoadRegistration(){ }

}
