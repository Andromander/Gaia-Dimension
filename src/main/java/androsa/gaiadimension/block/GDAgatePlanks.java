package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GDAgatePlanks extends Block implements ModelRegisterCallback {

    public GDAgatePlanks() {
        super(Material.WOOD);

        this.setSoundType(SoundType.STONE);
        this.setHardness(1.5F);
        this.setResistance(2.0F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
