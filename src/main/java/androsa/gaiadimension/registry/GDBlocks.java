package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(GaiaDimension.MODID)

public class GDBlocks {

    @GameRegistry.ObjectHolder("heavy_soil")
    public static final Block heavySoil = Blocks.AIR;
    @GameRegistry.ObjectHolder("glitter_grass")
    public static final Block glitterGrass = Blocks.AIR;
    @GameRegistry.ObjectHolder("gaia_leaves")
    public static final Block gaiaLeaves = Blocks.AIR;
    @GameRegistry.ObjectHolder("special_gaia_leaves")
    public static final Block gaiaLeavesSpecial = Blocks.AIR;
    @GameRegistry.ObjectHolder("gaia_log")
    public static final Block gaiaLog = Blocks.AIR;
    @GameRegistry.ObjectHolder("special_gaia_log")
    public static final Block gaiaLogSpecial = Blocks.AIR;
    @GameRegistry.ObjectHolder("gaia_stone")
    public static final Block gaiaStone = Blocks.AIR;
    @GameRegistry.ObjectHolder("gaia_stone_bricks")
    public static final Block gaiaStoneBricks = Blocks.AIR;

    @GameRegistry.ObjectHolder("hematite_block")
    public static final Block hematiteBlock = Blocks.AIR;
    @GameRegistry.ObjectHolder("labradorite_block")
    public static final Block labradoriteBlock = Blocks.AIR;
    @GameRegistry.ObjectHolder("pyrite_block")
    public static final Block pyriteBlock = Blocks.AIR;
    @GameRegistry.ObjectHolder("opal_block")
    public static final Block opalBlock = Blocks.AIR;
    @GameRegistry.ObjectHolder("moonstone_block")
    public static final Block moonstoneBlock = Blocks.AIR;
    @GameRegistry.ObjectHolder("cinnabar_block")
    public static final Block cinnabarBlock = Blocks.AIR;

    @GameRegistry.ObjectHolder("hematite_ore")
    public static final Block hematiteOre = Blocks.AIR;
    @GameRegistry.ObjectHolder("pyrite_ore")
    public static final Block pyriteOre = Blocks.AIR;
    @GameRegistry.ObjectHolder("opal_ore")
    public static final Block opalOre = Blocks.AIR;
    @GameRegistry.ObjectHolder("labradorite_ore")
    public static final Block labradoriteOre = Blocks.AIR;
    @GameRegistry.ObjectHolder("moonstone_ore")
    public static final Block moonstoneOre = Blocks.AIR;
    @GameRegistry.ObjectHolder("cinnabar_ore")
    public static final Block cinnabarOre = Blocks.AIR;

}
