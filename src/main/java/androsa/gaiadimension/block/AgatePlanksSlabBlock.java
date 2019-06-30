package androsa.gaiadimension.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class AgatePlanksSlabBlock extends BasicGaiaSlabBlock {

    public AgatePlanksSlabBlock(MaterialColor color) {
        this(color, 0);
    }

    public AgatePlanksSlabBlock(MaterialColor color, int light) {
        super(Material.WOOD, color, SoundType.STONE, 1.5F, 2.0F, ToolType.AXE, 0, light);
    }
}

