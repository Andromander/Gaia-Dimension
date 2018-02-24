package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GDThickGlitterBlock extends Block implements ModelRegisterCallback {

    public GDThickGlitterBlock() {
        super(Material.ROCK);

        this.setHardness(1.5F);
        this.setResistance(7.5F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
