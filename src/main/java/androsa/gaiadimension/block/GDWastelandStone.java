package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GDWastelandStone extends Block implements ModelRegisterCallback {

    public GDWastelandStone() {
        super(Material.ROCK);

        this.setHardness(15.0F);
        this.setResistance(200.0F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
