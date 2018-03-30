package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GDSaltRock extends Block implements ModelRegisterCallback {

    public GDSaltRock() {
        super(Material.ROCK);

        this.setHardness(5F);
        this.setResistance(10F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
