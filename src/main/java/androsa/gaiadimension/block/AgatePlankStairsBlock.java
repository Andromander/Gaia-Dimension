package androsa.gaiadimension.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class AgatePlankStairsBlock extends StairsBlock {

    public AgatePlankStairsBlock(BlockState state, MaterialColor color) {
        this(state, color, 0);
    }

    public AgatePlankStairsBlock(BlockState state, MaterialColor color, int light) {
        super(() -> state, Properties.create(Material.WOOD, color).hardnessAndResistance(1.5F, 2.0F).sound(SoundType.STONE).harvestTool(ToolType.AXE).harvestLevel(0).lightValue(light));
    }
}