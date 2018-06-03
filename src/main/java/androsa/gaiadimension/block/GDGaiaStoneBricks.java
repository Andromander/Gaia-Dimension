package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GDGaiaStoneBricks extends Block implements ModelRegisterCallback {

    public GDGaiaStoneBricks() {
        super(Material.ROCK);

        this.setHardness(2.0F);
        this.setResistance(20.0F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
