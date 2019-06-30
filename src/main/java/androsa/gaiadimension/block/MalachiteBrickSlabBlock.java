package androsa.gaiadimension.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class MalachiteBrickSlabBlock extends BasicGaiaSlabBlock {

    public MalachiteBrickSlabBlock() {
        super(Material.ROCK, MaterialColor.GREEN, SoundType.STONE, 20.0F, 100.0F, ToolType.PICKAXE, 2, 0);
    }
}
