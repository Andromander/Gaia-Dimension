package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GDMalachitePulseBricks extends Block implements ModelRegisterCallback {

    public GDMalachitePulseBricks() {
        super(Material.ROCK);

        this.setHardness(20);
        this.setResistance(100);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
