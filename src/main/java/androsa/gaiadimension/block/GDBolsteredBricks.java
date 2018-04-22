package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GDBolsteredBricks extends Block implements ModelRegisterCallback {

    public GDBolsteredBricks() {
        super(Material.ROCK);

        this.setHardness(30.0F);
        this.setResistance(400.0F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
