package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.BlockTorch;

public class GDPyriteTorch extends BlockTorch implements ModelRegisterCallback {

    public GDPyriteTorch() {
        super();

        this.setLightLevel(2F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
