package androsa.gaiadimension.block.blocksore;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GDScaynyxOre extends Block implements ModelRegisterCallback {

    public GDScaynyxOre() {
        super(Material.ROCK);

        this.setCreativeTab(GDTabs.tabBlock);
    }
}
