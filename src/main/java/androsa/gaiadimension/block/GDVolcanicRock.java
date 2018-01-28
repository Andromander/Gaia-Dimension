package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GDVolcanicRock extends Block implements ModelRegisterCallback {

    public GDVolcanicRock() {
        super(Material.ROCK);

        this.setHardness(50F);
        this.setResistance(600.0F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
