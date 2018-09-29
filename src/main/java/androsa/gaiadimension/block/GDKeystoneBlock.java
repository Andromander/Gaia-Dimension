package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GDKeystoneBlock extends Block implements ModelRegisterCallback {

    public GDKeystoneBlock() {
        super(Material.IRON);

        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setSoundType(SoundType.METAL);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
