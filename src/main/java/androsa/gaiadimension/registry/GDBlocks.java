package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockPortal;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.minecraftforge.fml.common.registry.GameRegistry.*;

@ObjectHolder(GaiaDimension.MODID)
public class GDBlocks {

    @ObjectHolder("gaia_portal")
    public static BlockPortal gaiaPortal;

    @ObjectHolder("heavy_soil")
    public static final Block heavySoil = Blocks.AIR;
    @ObjectHolder("glitter_grass")
    public static final Block glitterGrass = Blocks.AIR;
    @ObjectHolder("crystal_growth")
    public static final Block crystalGrowth = Blocks.AIR;
    @ObjectHolder("gaia_sapling")
    public static Block gaiaSapling = Blocks.AIR;
    @ObjectHolder("gaia_leaves")
    public static final Block gaiaLeaves = Blocks.AIR;
    @ObjectHolder("special_gaia_leaves")
    public static final Block gaiaLeavesSpecial = Blocks.AIR;
    @ObjectHolder("gaia_log")
    public static final Block gaiaLog = Blocks.AIR;
    @ObjectHolder("special_gaia_log")
    public static final Block gaiaLogSpecial = Blocks.AIR;
    @ObjectHolder("gaia_stone")
    public static final Block gaiaStone = Blocks.AIR;
    @ObjectHolder("gaia_stone_bricks")
    public static final Block gaiaStoneBricks = Blocks.AIR;
    @ObjectHolder("volcanic_rock")
    public static final Block volcanicRock = Blocks.AIR;

    @ObjectHolder("hematite_block")
    public static final Block hematiteBlock = Blocks.AIR;
    @ObjectHolder("labradorite_block")
    public static final Block labradoriteBlock = Blocks.AIR;
    @ObjectHolder("pyrite_block")
    public static final Block pyriteBlock = Blocks.AIR;
    @ObjectHolder("opal_block")
    public static final Block opalBlock = Blocks.AIR;
    @ObjectHolder("moonstone_block")
    public static final Block moonstoneBlock = Blocks.AIR;
    @ObjectHolder("cinnabar_block")
    public static final Block cinnabarBlock = Blocks.AIR;

    @ObjectHolder("hematite_ore")
    public static final Block hematiteOre = Blocks.AIR;
    @ObjectHolder("pyrite_ore")
    public static final Block pyriteOre = Blocks.AIR;
    @ObjectHolder("opal_ore")
    public static final Block opalOre = Blocks.AIR;
    @ObjectHolder("labradorite_ore")
    public static final Block labradoriteOre = Blocks.AIR;
    @ObjectHolder("moonstone_ore")
    public static final Block moonstoneOre = Blocks.AIR;
    @ObjectHolder("cinnabar_ore")
    public static final Block cinnabarOre = Blocks.AIR;

}
