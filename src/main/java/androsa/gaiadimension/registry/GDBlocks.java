package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.block.*;

import static net.minecraftforge.fml.common.registry.GameRegistry.*;

@ObjectHolder(GaiaDimension.MODID)
public class GDBlocks {

    @ObjectHolder("gaia_portal")
    public static BlockPortal gaiaPortal;
    @ObjectHolder("gold_fire")
    public static Block goldFire;
    @ObjectHolder("pyrite_torch")
    public static Block pyriteTorch;
    @ObjectHolder("agate_crafting_table")
    public static Block agateCraftingTable;
    @ObjectHolder("gaia_stone_furnace_idle")
    public static Block gaiaStoneFurnaceIdle;
    @ObjectHolder("gaia_stone_furnace_lit")
    public static Block gaiaStoneFurnaceActive;
    @ObjectHolder("glitter_furnace_idle")
    public static Block glitterFurnaceIdle;
    @ObjectHolder("glitter_furnace_lit")
    public static Block glitterFurnaceActive;
    @ObjectHolder("purifier_idle")
    public static Block purifierIdle;
    @ObjectHolder("purifier_lit")
    public static Block purifierActive;

    @ObjectHolder("heavy_soil")
    public static Block heavySoil;
    @ObjectHolder("corrupt_soil")
    public static Block corruptSoil;
    @ObjectHolder("glitter_grass")
    public static Block glitterGrass;
    @ObjectHolder("cool_grass")
    public static Block coolGrass;
    @ObjectHolder("verdant_grass")
    public static Block verdantGrass;
    @ObjectHolder("scented_grass")
    public static Block scentedGrass;
    @ObjectHolder("old_grass")
    public static Block oldGrass;
    @ObjectHolder("corrupt_grass")
    public static Block corruptGrass;
    @ObjectHolder("singed_grass")
    public static Block singedGrass;
    @ObjectHolder("mutated_grass")
    public static Block mutantGrass;
    @ObjectHolder("frail_glitter_block")
    public static Block frailGlitterBlock;
    @ObjectHolder("thick_glitter_block")
    public static Block thickGlitterBlock;
    @ObjectHolder("gummy_glitter_block")
    public static Block gummyGlitterBlock;
    @ObjectHolder("crystal_growth")
    public static Block crystalGrowth;
    @ObjectHolder("crystal_bloom")
    public static Block crystalBloom;
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
    @ObjectHolder("pink_agate_plank_slab")
    public static BlockSlab pinkAgatePlankSlab;
    @ObjectHolder("blue_agate_plank_slab")
    public static BlockSlab blueAgatePlankSlab;
    @ObjectHolder("green_agate_plank_slab")
    public static BlockSlab greenAgatePlankSlab;
    @ObjectHolder("purple_agate_plank_slab")
    public static BlockSlab purpleAgatePlankSlab;
    @ObjectHolder("fossilized_plank_slab")
    public static BlockSlab fossilizedPlankSlab;
    @ObjectHolder("corrupted_plank_slab")
    public static BlockSlab corruptedPlankSlab;
    @ObjectHolder("crusty_plank_slab")
    public static BlockSlab crustyPlankSlab;
    @ObjectHolder("heated_plank_slab")
    public static BlockSlab heatedPlankSlab;
    @ObjectHolder("double_pink_agate_plank_slab")
    public static BlockSlab pinkAgatePlankSlabDouble;
    @ObjectHolder("double_blue_agate_plank_slab")
    public static BlockSlab blueAgatePlankSlabDouble;
    @ObjectHolder("double_green_agate_plank_slab")
    public static BlockSlab greenAgatePlankSlabDouble;
    @ObjectHolder("double_purple_agate_plank_slab")
    public static BlockSlab purpleAgatePlankSlabDouble;
    @ObjectHolder("double_fossilized_plank_slab")
    public static BlockSlab fossilizedPlankSlabDouble;
    @ObjectHolder("double_corrupted_plank_slab")
    public static BlockSlab corruptedPlankSlabDouble;
    @ObjectHolder("double_crusty_plank_slab")
    public static BlockSlab crustyPlankSlabDouble;
    @ObjectHolder("double_heated_plank_slab")
    public static BlockSlab heatedPlankSlabDouble;
    @ObjectHolder("pink_agate_plank_stairs")
    public static Block pinkAgatePlankStairs;
    @ObjectHolder("blue_agate_plank_stairs")
    public static Block blueAgatePlankStairs;
    @ObjectHolder("green_agate_plank_stairs")
    public static Block greenAgatePlankStairs;
    @ObjectHolder("purple_agate_plank_stairs")
    public static Block purpleAgatePlankStairs;
    @ObjectHolder("fossilized_plank_stairs")
    public static Block fossilizedPlankStairs;
    @ObjectHolder("corrupted_plank_stairs")
    public static Block corruptedPlankStairs;
    @ObjectHolder("crusty_plank_stairs")
    public static Block crustyPlankStairs;
    @ObjectHolder("heated_plank_stairs")
    public static Block heatedPlankStairs;

    @ObjectHolder("salt")
    public static Block saltBlock;
    @ObjectHolder("saltstone")
    public static Block rockSalt;
    @ObjectHolder("gaia_stone")
    public static Block gaiaStone;
    @ObjectHolder("gaia_cobblestone")
    public static Block gaiaCobblestone;
    @ObjectHolder("wasteland_stone")
    public static Block wastelandStone;
    @ObjectHolder("static_stone")
    public static Block staticStone;
    @ObjectHolder("volcanic_rock")
    public static Block volcanicRock;
    @ObjectHolder("searing_rock")
    public static Block searingRock;
    @ObjectHolder("gaia_stone_bricks")
    public static Block gaiaStoneBricks;
    @ObjectHolder("reinforced_bricks")
    public static Block reinforcedBricks;
    @ObjectHolder("malachite_bricks")
    public static Block malachiteBricks;
    @ObjectHolder("malachite_brick_slab")
    public static BlockSlab malachiteBrickSlab;
    @ObjectHolder("double_malachite_brick_slab")
    public static BlockSlab malachiteBrickSlabDouble;
    @ObjectHolder("malachite_pillar")
    public static Block malachiteBrickPillar;
    @ObjectHolder("malachite_brick_stairs")
    public static Block malachiteBrickStairs;
    @ObjectHolder("malachite_chisel_stairs")
    public static Block malachiteChiselStairs;
    @ObjectHolder("malachite_pulsing_brick_stairs")
    public static Block malachitePulsingBrickStairs;
    @ObjectHolder("malachite_pulsing_floor_stairs")
    public static Block malachitePulsingFloorStairs;
    @ObjectHolder("malachite_pillar_stairs")
    public static Block malachitePillarStairs;
    @ObjectHolder("malachite_floor_stairs")
    public static Block malachiteFloorStairs;
    @ObjectHolder("bolstered_bricks")
    public static Block bolsteredBricks;

    @ObjectHolder("sugilite_block")
    public static Block sugiliteBlock;
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
    @ObjectHolder("goldstone_block")
    public static Block goldstoneBlock;
    @ObjectHolder("ixiolite_block")
    public static Block ixioliteBlock;
    @ObjectHolder("proustite_block")
    public static Block proustiteBlock;
    @ObjectHolder("euclase_block")
    public static Block euclaseBlock;
    @ObjectHolder("leucite_block")
    public static Block leuciteBlock;
    @ObjectHolder("carnelian_block")
    public static Block carnelianBlock;
    @ObjectHolder("benitoite_block")
    public static Block benitoiteBlock;
    @ObjectHolder("diopside_block")
    public static Block diopsideBlock;
    @ObjectHolder("chalcedony_block")
    public static Block chalcedonyBlock;

    @ObjectHolder("sugilite_ore")
    public static Block sugiliteOre;
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
