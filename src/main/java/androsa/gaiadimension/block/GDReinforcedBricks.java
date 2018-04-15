package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GDReinforcedBricks extends Block implements ModelRegisterCallback {

    public GDReinforcedBricks() {
        super(Material.ROCK);

        this.setHardness(20.0F);
        this.setResistance(300.0F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
