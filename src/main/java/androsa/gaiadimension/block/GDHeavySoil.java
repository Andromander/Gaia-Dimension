package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GDHeavySoil extends Block implements ModelRegisterCallback {

    public GDHeavySoil() {
        super(Material.GROUND);

        this.setSoundType(SoundType.GROUND);
        this.setHardness(0.9F);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
