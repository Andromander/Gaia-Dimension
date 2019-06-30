package androsa.gaiadimension.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class BasicGaiaSlabBlock extends SlabBlock {

    private final ToolType toolType;
    private final int toolLevel;

    public BasicGaiaSlabBlock(Material material, MaterialColor color, SoundType sound, float hardness, float resistance, ToolType tool, int level, int light) {
        super(Properties.create(material, color).hardnessAndResistance(hardness, resistance).sound(sound).lightValue(light));

        toolType = tool;
        toolLevel = level;
    }

    @Override
    public ToolType getHarvestTool(BlockState state) {
        return toolType;
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return toolLevel;
    }
}
