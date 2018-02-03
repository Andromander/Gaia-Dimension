package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.block.*;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static net.minecraftforge.fml.common.registry.GameRegistry.*;

@ObjectHolder(GaiaDimension.MODID)
public class GDBlocks {

    @ObjectHolder("gaia_portal")
    public static BlockPortal gaiaPortal;

    @ObjectHolder("heavy_soil")
    public static Block heavySoil;
    @ObjectHolder("glitter_grass")
    public static Block glitterGrass;
    @ObjectHolder("crystal_growth")
    public static Block crystalGrowth;
    @ObjectHolder("gaia_sapling")
    public static Block gaiaSapling;
    @ObjectHolder("gaia_leaves")
    public static Block gaiaLeaves;
    @ObjectHolder("special_gaia_leaves")
    public static Block gaiaLeavesSpecial;
    @ObjectHolder("gaia_log")
    public static Block gaiaLog;
    @ObjectHolder("special_gaia_log")
    public static Block gaiaLogSpecial;

    @ObjectHolder("pink_agate_planks")
    public static Block pinkAgatePlanks;
    @ObjectHolder("blue_agate_planks")
    public static Block blueAgatePlanks;
    @ObjectHolder("green_agate_planks")
    public static Block greenAgatePlanks;
    @ObjectHolder("purple_agate_planks")
    public static Block purpleAgatePlanks;
    @ObjectHolder("fossilized_planks")
    public static Block fossilizedPlanks;
    @ObjectHolder("corrupted_planks")
    public static Block corruptedPlanks;
    @ObjectHolder("crusty_planks")
    public static Block crustyPlanks;
    @ObjectHolder("heated_planks")
    public static Block heatedPlanks;

    @ObjectHolder("gaia_stone")
    public static Block gaiaStone;
    @ObjectHolder("gaia_stone_bricks")
    public static Block gaiaStoneBricks;
    @ObjectHolder("malachite_bricks")
    public static Block malachiteBricks;
    @ObjectHolder("malachite_brick_slab")
    public static BlockSlab malachiteBrickSlab;
    @ObjectHolder("double_malachite_brick_slab")
    public static BlockSlab malachiteBrickSlabDouble;
    @ObjectHolder("malachite_pillar")
    public static Block malachiteBrickPillar;
    @ObjectHolder("malachite_stairs")
    public static Block malachiteStairs;
    @ObjectHolder("volcanic_rock")
    public static Block volcanicRock;

    @ObjectHolder("hematite_block")
    public static Block hematiteBlock;
    @ObjectHolder("labradorite_block")
    public static Block labradoriteBlock;
    @ObjectHolder("pyrite_block")
    public static Block pyriteBlock;
    @ObjectHolder("opal_block")
    public static Block opalBlock;
    @ObjectHolder("moonstone_block")
    public static Block moonstoneBlock;
    @ObjectHolder("cinnabar_block")
    public static Block cinnabarBlock;
    @ObjectHolder("tektite_block")
    public static Block tektiteBlock;

    @ObjectHolder("hematite_ore")
    public static Block hematiteOre;
    @ObjectHolder("pyrite_ore")
    public static Block pyriteOre;
    @ObjectHolder("opal_ore")
    public static Block opalOre;
    @ObjectHolder("labradorite_ore")
    public static Block labradoriteOre;
    @ObjectHolder("moonstone_ore")
    public static Block moonstoneOre;
    @ObjectHolder("cinnabar_ore")
    public static Block cinnabarOre;

}
