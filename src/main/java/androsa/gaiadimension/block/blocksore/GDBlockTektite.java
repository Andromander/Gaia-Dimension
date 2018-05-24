package androsa.gaiadimension.block.blocksore;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GDBlockTektite extends Block implements ModelRegisterCallback {

    public GDBlockTektite() {
        super(Material.ROCK);

        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
