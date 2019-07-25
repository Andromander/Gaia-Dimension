package androsa.gaiadimension.block;

import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class MalachiteBrickPillarBlock extends RotatedPillarBlock {

    public MalachiteBrickPillarBlock() {
        super(Properties.create(Material.ROCK, MaterialColor.GREEN).hardnessAndResistance(20.0F, 100.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2));
    }
}
