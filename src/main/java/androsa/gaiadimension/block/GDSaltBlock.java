package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GDSaltBlock extends BlockFalling implements ModelRegisterCallback {

    public GDSaltBlock() {
        super(Material.SAND);

        this.setHardness(0.9F);
        setHarvestLevel("shovel", -1);
        this.setSoundType(SoundType.SAND);
        this.setCreativeTab(GDTabs.tabBlock);
    }
}
