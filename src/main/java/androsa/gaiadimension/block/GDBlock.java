package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * The generic Block class in the event of a block that does not have special properties.
 */
public class GDBlock extends Block implements ModelRegisterCallback {

    public GDBlock(Material material, MapColor color, SoundType sound, String toolClass, int harvestLevel) {
        super(material, color);

        setSoundType(sound);
        setCreativeTab(GDTabs.tabBlock);
        setHarvestLevel(toolClass, harvestLevel);
    }

    public GDBlock(Material material, MapColor color, SoundType sound) {
        super(material, color);

        setSoundType(sound);
        setCreativeTab(GDTabs.tabBlock);
    }

    public GDBlock(Material material, MapColor color, String toolClass, int harvestLevel) {
        super(material, color);

        setSoundType(SoundType.STONE);
        setCreativeTab(GDTabs.tabBlock);
        setHarvestLevel(toolClass, harvestLevel);
    }

    public GDBlock(Material material, MapColor color) {
        super(material, color);

        setSoundType(SoundType.STONE);
        setCreativeTab(GDTabs.tabBlock);
    }
}
