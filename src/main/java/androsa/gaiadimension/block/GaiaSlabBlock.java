package androsa.gaiadimension.block;

import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class GaiaSlabBlock extends SlabBlock {

    public GaiaSlabBlock(Material material, MaterialColor color, SoundType sound, float hardness, float resistance, ToolType tool, int level, int light) {
        super(Properties.create(material, color).hardnessAndResistance(hardness, resistance).sound(sound).harvestTool(tool).harvestLevel(level).lightValue(light));
    }
}
