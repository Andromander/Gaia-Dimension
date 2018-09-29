package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GDPrimalMass extends Block implements ModelRegisterCallback {

    public GDPrimalMass() {
        super(Material.ROCK);

        this.setHardness(10F);
        this.setResistance(300.0F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
