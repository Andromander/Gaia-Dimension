package androsa.gaiadimension.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class StorageBlock extends BasicGaiaBlock {

    public StorageBlock(MaterialColor color) {
        this(color, 0);
    }

    public StorageBlock(MaterialColor color, int light) {
        super(Material.IRON, color, 5.0F, 10.0F, SoundType.METAL, ToolType.PICKAXE, 2, light);
    }
}
