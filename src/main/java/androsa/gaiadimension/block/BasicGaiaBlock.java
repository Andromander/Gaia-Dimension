package androsa.gaiadimension.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

/**
 * The generic Block class in the event of a block that does not have special properties.
 */
public class BasicGaiaBlock extends Block {

    public BasicGaiaBlock(Material material) {
        this(material, material.getColor());
    }

    public BasicGaiaBlock(Material material, MaterialColor color) {
        this(material, color, SoundType.STONE);
    }

    public BasicGaiaBlock(Material material, SoundType sound) {
        this(material, material.getColor(), sound);
    }

    public BasicGaiaBlock(Material material, MaterialColor color, SoundType sound) {
        this(material, color, sound, null, 0);
    }

    public BasicGaiaBlock(Material material, MaterialColor color, SoundType sound, float hardness, float resistance) {
        this(material, color, hardness, resistance, sound, null, -1);
    }

    public BasicGaiaBlock(Material material, MaterialColor color, ToolType toolClass, int harvestLevel) {
        this(material, color, 0.0F, SoundType.STONE, toolClass, harvestLevel);
    }

    public BasicGaiaBlock(Material material, SoundType sound, ToolType toolClass, int harvestLevel) {
        this(material, material.getColor(), 0.0F, sound, toolClass, harvestLevel);
    }

    public BasicGaiaBlock(Material material, MaterialColor color, SoundType sound, ToolType toolClass, int harvestLevel) {
        this(material, color, 0.0F, sound, toolClass, harvestLevel);
    }

    public BasicGaiaBlock(Material material, MaterialColor color, float hardresist, SoundType sound, ToolType toolClass, int harvestLevel) {
        this(material, color, hardresist, hardresist, sound, toolClass, harvestLevel);
    }

    public BasicGaiaBlock(Material material, MaterialColor color, float hardness, float resistance, ToolType toolClass, int harvestLevel) {
        this(material, color, hardness, resistance, SoundType.STONE, toolClass, harvestLevel);
    }

    public BasicGaiaBlock(Material material, MaterialColor color, float hardness, float resistance, SoundType sound, ToolType toolClass, int harvestLevel) {
        this(material, color, hardness, resistance, sound, toolClass, harvestLevel, 0);
    }

    public BasicGaiaBlock(Material material, MaterialColor color, float hardness, float resistance, SoundType sound, ToolType toolClass, int harvestLevel, int light) {
        super(Properties.create(material, color).hardnessAndResistance(hardness, resistance).sound(sound).harvestTool(toolClass).harvestLevel(harvestLevel).lightValue(light));
    }
}
