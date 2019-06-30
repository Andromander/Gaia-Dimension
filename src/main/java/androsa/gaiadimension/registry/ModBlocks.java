package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.registries.ObjectHolder;

import static net.minecraftforge.common.ToolType.*;

@ObjectHolder(GaiaDimension.MODID)
public class ModBlocks {

    //Utility Blocks
    public static final GaiaPortalBlock gaia_portal = new GaiaPortalBlock();
    public static final Block keystone_block = new BasicGaiaBlock(Material.IRON, MaterialColor.GOLD, 5.0F, 10.0F, SoundType.METAL, PICKAXE, 2);
    public static final Block gold_fire = new GoldFireBlock();
    public static final Block pyrite_torch = new PyriteTorchBlock();
    public static final Block pyrite_wall_torch = new PyriteWallTorchBlock();
    public static final Block agate_crafting_table = new AgateCraftingTableBlock();
    public static final Block crude_storage_crate = new SmallCrateBlock();
    public static final Block mega_storage_crate = new LargeCrateBlock();
    public static final Block gaia_stone_furnace = new GaiaStoneFurnaceBlock();
    public static final Block restructurer = new RestructurerBlock();
    public static final Block purifier = new PurifierBlock();
    public static final Block mineral_water_block = null;
    public static final Block superhot_magma_block = null;
    public static final Block sweet_muck_block = null;
    public static final Block liquid_bismuth_block = null;
    public static final Block liquid_aura_block = null;

    //Natural Blocks
    public static final Block heavy_soil = new GaiaSoilBlock(MaterialColor.PURPLE_TERRACOTTA);
    public static final Block corrupt_soil = new GaiaSoilBlock(MaterialColor.GRAY);
    public static final Block boggy_soil = new GaiaSoilBlock(MaterialColor.GRAY);
    public static final Block light_soil = new GaiaSoilBlock(MaterialColor.GOLD);
    public static final Block glitter_grass = new GlitterGrassBlock();
    public static final Block corrupt_grass = new CorruptGrassBlock();
    public static final Block murky_grass = new MurkyGrassBlock();
    public static final Block soft_grass = new SoftGrassBlock();
    public static final Block frail_glitter_block = new GaiaGlassBlock(MaterialColor.PINK, 1.0F);
    public static final Block thick_glitter_block = new BasicGaiaBlock(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA, 1.5F, 7.5F, PICKAXE, 1);
    public static final Block gummy_glitter_block = new GummyGlitterBlock();
    public static final Block pink_sludge_block = new PinkSludgeBlock();
    public static final Block pink_agate_leaves = new AgateLeavesBlock(MaterialColor.MAGENTA);
    public static final Block blue_agate_leaves = new AgateLeavesBlock(MaterialColor.BLUE);
    public static final Block green_agate_leaves = new AgateLeavesBlock(MaterialColor.GREEN);
    public static final Block purple_agate_leaves = new AgateLeavesBlock(MaterialColor.PURPLE_TERRACOTTA);
    public static final Block fossilized_leaves = new AgateLeavesBlock(MaterialColor.YELLOW);
    public static final Block corrupted_leaves = new AgateLeavesBlock(MaterialColor.TNT);
    public static final Block burnt_leaves = new AgateLeavesBlock(MaterialColor.GRAY);
    public static final Block burning_leaves = new AgateLeavesBlock(MaterialColor.ORANGE_TERRACOTTA, 3);
    public static final Block aura_leaves = new AgateLeavesBlock(MaterialColor.IRON);

    //Logs
    public static final Block pink_agate_log = new AgateLogBlock(MaterialColor.MAGENTA, MaterialColor.PINK_TERRACOTTA);
    public static final Block blue_agate_log = new AgateLogBlock(MaterialColor.BLUE, MaterialColor.BLUE_TERRACOTTA);
    public static final Block green_agate_log = new AgateLogBlock(MaterialColor.GREEN, MaterialColor.LIME_TERRACOTTA);
    public static final Block purple_agate_log = new AgateLogBlock(MaterialColor.PURPLE_TERRACOTTA, MaterialColor.PURPLE);
    public static final Block fossilized_log = new AgateLogBlock(MaterialColor.YELLOW, MaterialColor.DIRT);
    public static final Block corrupted_log = new AgateLogBlock(MaterialColor.TNT, MaterialColor.GRAY_TERRACOTTA);
    public static final Block burnt_log = new AgateLogBlock(MaterialColor.GRAY, MaterialColor.BLACK_TERRACOTTA);
    public static final Block burning_log = new AgateLogBlock(MaterialColor.ADOBE, MaterialColor.ORANGE_TERRACOTTA, 3);
    public static final Block aura_log = new AgateLogBlock(MaterialColor.IRON, MaterialColor.GRAY);
    public static final Block stripped_pink_agate_log = new AgateLogBlock(MaterialColor.MAGENTA, MaterialColor.MAGENTA);
    public static final Block stripped_blue_agate_log = new AgateLogBlock(MaterialColor.BLUE, MaterialColor.BLUE);
    public static final Block stripped_green_agate_log = new AgateLogBlock(MaterialColor.GREEN, MaterialColor.GREEN);
    public static final Block stripped_purple_agate_log = new AgateLogBlock(MaterialColor.PURPLE_TERRACOTTA, MaterialColor.PURPLE_TERRACOTTA);
    public static final Block stripped_fossilized_log = new AgateLogBlock(MaterialColor.YELLOW, MaterialColor.YELLOW);
    public static final Block stripped_corrupted_log = new AgateLogBlock(MaterialColor.TNT, MaterialColor.TNT);
    public static final Block stripped_burnt_log = new AgateLogBlock(MaterialColor.GRAY, MaterialColor.GRAY);
    public static final Block stripped_burning_log = new AgateLogBlock(MaterialColor.ADOBE, MaterialColor.ADOBE, 3);
    public static final Block stripped_aura_log = new AgateLogBlock(MaterialColor.IRON, MaterialColor.IRON);
    public static final Block pink_agate_wood = new AgateLogBlock(MaterialColor.PINK_TERRACOTTA, MaterialColor.PINK_TERRACOTTA);
    public static final Block blue_agate_wood = new AgateLogBlock(MaterialColor.BLUE_TERRACOTTA, MaterialColor.BLUE_TERRACOTTA);
    public static final Block green_agate_wood = new AgateLogBlock(MaterialColor.LIME_TERRACOTTA, MaterialColor.LIME_TERRACOTTA);
    public static final Block purple_agate_wood = new AgateLogBlock(MaterialColor.PURPLE, MaterialColor.PURPLE);
    public static final Block fossilized_wood = new AgateLogBlock(MaterialColor.DIRT, MaterialColor.DIRT);
    public static final Block corrupted_wood = new AgateLogBlock(MaterialColor.GRAY_TERRACOTTA, MaterialColor.GRAY_TERRACOTTA);
    public static final Block burnt_wood = new AgateLogBlock(MaterialColor.BLACK_TERRACOTTA, MaterialColor.BLACK_TERRACOTTA);
    public static final Block burning_wood = new AgateLogBlock(MaterialColor.ORANGE_TERRACOTTA, MaterialColor.ORANGE_TERRACOTTA, 3);
    public static final Block aura_wood = new AgateLogBlock(MaterialColor.GRAY, MaterialColor.GRAY);

    public static final Block salt = new GaiaFallingBlock(MaterialColor.SNOW, 0.9F, SoundType.SAND, 0xE0E0FF);
    public static final Block saltstone = new BasicGaiaBlock(Material.ROCK, MaterialColor.LIGHT_BLUE_TERRACOTTA, 1.5F, 10.0F, PICKAXE, 0);
    public static final Block pebbles = new GaiaFallingBlock(MaterialColor.GRAY, 1.3F, SoundType.GROUND, 0x663366);
    public static final Block gaia_stone = new BasicGaiaBlock(Material.ROCK, MaterialColor.MAGENTA, 2.0F, 15.0F, PICKAXE, 1);
    public static final Block gaia_cobblestone = new BasicGaiaBlock(Material.ROCK, MaterialColor.MAGENTA, 2.0F, 15.0F, PICKAXE, 1);
    public static final Block wasteland_stone = new BasicGaiaBlock(Material.ROCK, MaterialColor.BLUE_TERRACOTTA, 15.0F, 200.0F, PICKAXE, 2);
    public static final Block static_stone = new StaticStoneBlock();
    public static final Block charged_mineral = new ChargedMineralBlock();
    public static final Block volcanic_rock = new BasicGaiaBlock(Material.ROCK, MaterialColor.GRAY_TERRACOTTA, 15.0F, 200.0F, PICKAXE, 2);
    public static final Block searing_rock = new SearingRockBlock();
    public static final Block primal_mass = new BasicGaiaBlock(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA, 30.0F, 400.0F, PICKAXE, 2);
    public static final Block impure_rock = new BasicGaiaBlock(Material.ROCK, MaterialColor.GRAY, 20.0F, 300.0F, PICKAXE, 2);
    public static final Block active_rock = new ActiveRockBlock();
    public static final Block impure_sludge = new ImpureSludgeBlock();
    public static final Block geyser_block = new GeyserBlock();
    public static final Block sparkling_rock = new BasicGaiaBlock(Material.ROCK, MaterialColor.IRON, 10.0F, 150.0F, SoundType.GLASS, PICKAXE, 1);
    public static final Block aura_shoot = new AuraShootBlock();

    //Plants
    public static final Block crystal_growth = new CrystalGrowthBlock(MaterialColor.SNOW);
    public static final Block crystal_growth_red = new CrystalGrowthBlock(MaterialColor.RED);
    public static final Block crystal_growth_black = new CrystalGrowthBlock(MaterialColor.BLACK);
    public static final Block crystal_growth_seared = new CrystalGrowthBlock(MaterialColor.BLACK);
    public static final Block crystal_growth_mutant = new CrystalGrowthBlock(MaterialColor.WHITE_TERRACOTTA);
    public static final Block crystal_growth_aura = new CrystalGrowthBlock(MaterialColor.LIGHT_BLUE_TERRACOTTA);
    public static final Block thiscus = new CrystalBloomBlock();
    public static final Block ouzium = new CrystalBloomBlock();
    public static final Block agathum = new CrystalBloomBlock();
    public static final Block varloom = new CrystalBloomBlock();
    public static final Block corrupt_varloom = new CrystalBloomBlock();
    public static final Block missingno_plant = new CrystalBloomBlock();
    public static final Block spotted_kersei = new CrystalFungusBlock(MaterialColor.PINK, false);
    public static final Block thorny_wiltha = new CrystalFungusBlock(MaterialColor.LIGHT_BLUE, false);
    public static final Block roofed_agaric = new CrystalFungusBlock(MaterialColor.LIME, false);
    public static final Block bulbous_hobina = new CrystalFungusBlock(MaterialColor.PINK_TERRACOTTA, false);
    public static final Block stickly_cupsir = new CrystalFungusBlock(MaterialColor.YELLOW_TERRACOTTA, false);
    public static final Block mystical_murgni = new CrystalFungusBlock(MaterialColor.GOLD, false);
    public static final Block corrupted_gaia_eye = new CrystalFungusBlock(MaterialColor.TNT, false);
    //public static final Block sacred_gaia_eye = new CrystalFungusBlock(false);
    public static final Block elder_imklia = new CrystalFungusBlock(MaterialColor.PURPLE, true);
    public static final Block gold_orb_tucher = new CrystalFungusBlock(MaterialColor.GOLD, true);
    public static final Block missingno_fungus = new CrystalFungusBlock(MaterialColor.MAGENTA, false);
    public static final Block pink_agate_sapling = new AgateSaplingBlock(MaterialColor.PINK, new GDGenPinkAgateTree(true));
    public static final Block blue_agate_sapling = new AgateSaplingBlock(MaterialColor.LIGHT_BLUE, new GDGenBlueAgateTree(true));
    public static final Block green_agate_sapling = new AgateSaplingBlock(MaterialColor.LIME, new GDGenGreenAgateTree(true));
    public static final Block purple_agate_sapling = new AgateSaplingBlock(MaterialColor.PURPLE_TERRACOTTA, new GDGenPurpleAgateTree(true));
    public static final Block fossilized_sapling = new AgateSaplingBlock(MaterialColor.YELLOW_TERRACOTTA, new GDGenFossilizedTree(true));
    public static final Block corrupted_sapling = new AgateSaplingBlock(MaterialColor.BLACK_TERRACOTTA, new GDGenGoldstoneCorruptTree(true));
    public static final Block burnt_sapling = new AgateSaplingBlock(MaterialColor.BLACK, new GDGenBurntAgateTree(true));
    public static final Block burning_sapling = new AgateSaplingBlock(MaterialColor.ORANGE_TERRACOTTA, new GDGenFieryAgateTree(true));
    public static final Block aura_sapling = new AgateSaplingBlock(MaterialColor.SNOW, new GDGenAuraTree(true));

    //Planks
    public static final Block pink_agate_planks = new AgatePlanksBlock(MaterialColor.PINK);
    public static final Block blue_agate_planks = new AgatePlanksBlock(MaterialColor.LIGHT_BLUE);
    public static final Block green_agate_planks = new AgatePlanksBlock(MaterialColor.LIME);
    public static final Block purple_agate_planks = new AgatePlanksBlock(MaterialColor.PURPLE_TERRACOTTA);
    public static final Block fossilized_planks = new AgatePlanksBlock(MaterialColor.YELLOW_TERRACOTTA);
    public static final Block corrupted_planks = new AgatePlanksBlock(MaterialColor.BLACK_TERRACOTTA);
    public static final Block burnt_planks = new AgatePlanksBlock(MaterialColor.BLACK);
    public static final Block burning_planks = new AgatePlanksBlock(MaterialColor.ORANGE_TERRACOTTA, 3);
    public static final Block aura_planks = new AgatePlanksBlock(MaterialColor.SNOW);
    public static final Block pink_agate_plank_slab = new AgatePlanksSlabBlock(MaterialColor.PINK);
    public static final Block blue_agate_plank_slab = new AgatePlanksSlabBlock(MaterialColor.LIGHT_BLUE);
    public static final Block green_agate_plank_slab = new AgatePlanksSlabBlock(MaterialColor.LIME);
    public static final Block purple_agate_plank_slab = new AgatePlanksSlabBlock(MaterialColor.PURPLE_TERRACOTTA);
    public static final Block fossilized_plank_slab = new AgatePlanksSlabBlock(MaterialColor.YELLOW_TERRACOTTA);
    public static final Block corrupted_plank_slab = new AgatePlanksSlabBlock(MaterialColor.BLACK_TERRACOTTA);
    public static final Block burnt_plank_slab = new AgatePlanksSlabBlock(MaterialColor.BLACK);
    public static final Block burning_plank_slab = new AgatePlanksSlabBlock(MaterialColor.ORANGE_TERRACOTTA, 7);
    public static final Block aura_plank_slab = new AgatePlanksSlabBlock(MaterialColor.SNOW);
    public static final Block pink_agate_plank_stairs = new AgatePlankStairsBlock(pink_agate_planks.getDefaultState(), MaterialColor.PINK);
    public static final Block blue_agate_plank_stairs = new AgatePlankStairsBlock(blue_agate_planks.getDefaultState(), MaterialColor.LIGHT_BLUE);
    public static final Block green_agate_plank_stairs = new AgatePlankStairsBlock(green_agate_planks.getDefaultState(), MaterialColor.LIME);
    public static final Block purple_agate_plank_stairs = new AgatePlankStairsBlock(purple_agate_planks.getDefaultState(), MaterialColor.PURPLE_TERRACOTTA);
    public static final Block fossilized_plank_stairs = new AgatePlankStairsBlock(fossilized_planks.getDefaultState(), MaterialColor.YELLOW_TERRACOTTA);
    public static final Block corrupted_plank_stairs = new AgatePlankStairsBlock(corrupted_planks.getDefaultState(), MaterialColor.BLACK_TERRACOTTA);
    public static final Block burnt_plank_stairs = new AgatePlankStairsBlock(burnt_planks.getDefaultState(), MaterialColor.BLACK);
    public static final Block burning_plank_stairs = new AgatePlankStairsBlock(burning_planks.getDefaultState(), MaterialColor.ORANGE_TERRACOTTA, 7);
    public static final Block aura_plank_stairs = new AgatePlankStairsBlock(aura_planks.getDefaultState(), MaterialColor.SNOW);

    //Manufactured
    public static final Block cloudy_glass = new GaiaGlassBlock(MaterialColor.YELLOW, 0.7F);
    public static final Block foggy_glass = new GaiaGlassBlock(MaterialColor.LIGHT_BLUE, 0.7F);
    public static final Block gaia_stone_bricks = new BasicGaiaBlock(Material.ROCK, MaterialColor.MAGENTA, 2.0F, 20.0F, PICKAXE, 1);
    public static final Block cracked_gaia_stone_bricks = new BasicGaiaBlock(Material.ROCK, MaterialColor.MAGENTA, 2.0F, 20.0F, PICKAXE, 1);
    public static final Block crusted_gaia_stone_bricks = new BasicGaiaBlock(Material.ROCK, MaterialColor.MAGENTA, 2.0F, 20.0F, PICKAXE, 1);
    public static final Block reinforced_bricks = new BasicGaiaBlock(Material.ROCK, MaterialColor.PURPLE, 10.0F, 100.0F, PICKAXE, 1);
    public static final Block malachite_bricks = new MalachiteBricksBlock(false);
    public static final Block malachite_cracked_bricks = new MalachiteBricksBlock(false);
    public static final Block malachite_crusted_bricks = new MalachiteBricksBlock(false);
    public static final Block malachite_floor_tiles = new MalachiteBricksBlock(false);
    public static final Block malachite_chisel_bricks = new MalachiteBricksBlock(false);
    public static final Block malachite_pulsing_bricks = new MalachiteBricksBlock(true);
    public static final Block malachite_pulsing_tiles = new MalachiteBricksBlock(true);
    public static final Block malachite_pulsing_chisel = new MalachiteBricksBlock(true);
    public static final Block malachite_brick_slab = new MalachiteBrickSlabBlock();
    public static final Block malachite_floor_slab = new MalachiteBrickSlabBlock();
    public static final Block malachite_pillar = new MalachiteBrickPillarBlock();
    public static final Block malachite_brick_stairs = new MalachiteStairsBlock(malachite_bricks.getDefaultState(), false);
    public static final Block malachite_floor_stairs = new MalachiteStairsBlock(malachite_floor_tiles.getDefaultState(), false);
    public static final Block malachite_chisel_stairs = new MalachiteStairsBlock(malachite_chisel_bricks.getDefaultState(), false);
    public static final Block malachite_pulsing_brick_stairs = new MalachiteStairsBlock(malachite_bricks.getDefaultState(), true);
    public static final Block malachite_pulsing_floor_stairs = new MalachiteStairsBlock(malachite_floor_tiles.getDefaultState(), true);
    public static final Block malachite_pulsing_chisel_stairs = new MalachiteStairsBlock(malachite_chisel_bricks.getDefaultState(), true);
    public static final Block malachite_pillar_stairs = new MalachiteStairsBlock(malachite_pillar.getDefaultState(), false);
    public static final Block bolstered_bricks =  new BasicGaiaBlock(Material.ROCK, MaterialColor.SAND, 30.0F, 400.0F, PICKAXE, 2);

    //Storage Blocks
    public static final Block sugilite_block = new StorageBlock(MaterialColor.PURPLE);
    public static final Block hematite_block = new StorageBlock(MaterialColor.GRAY);
    public static final Block labradorite_block = new StorageBlock(MaterialColor.GREEN);
    public static final Block opal_block_red = new StorageBlock(MaterialColor.RED);
    public static final Block opal_block_blue = new StorageBlock(MaterialColor.LIGHT_BLUE);
    public static final Block opal_block_green = new StorageBlock(MaterialColor.LIME);
    public static final Block opal_block_white = new StorageBlock(MaterialColor.SNOW);
    public static final Block pyrite_block = new StorageBlock(MaterialColor.GOLD, 15);
    public static final Block moonstone_block = new StorageBlock(MaterialColor.IRON);
    public static final Block cinnabar_block = new StorageBlock(MaterialColor.ADOBE);
    public static final Block tektite_block = new StorageBlock(MaterialColor.BLACK);
    public static final Block goldstone_block = new StorageBlock(MaterialColor.BLACK);
    public static final Block aura_block = new StorageBlock(MaterialColor.ICE);
    public static final Block bismuth_block = new StorageBlock(MaterialColor.OBSIDIAN);
    public static final Block ixiolite_block = new StorageBlock(MaterialColor.GRAY);
    public static final Block proustite_block = new StorageBlock(MaterialColor.MAGENTA);
    public static final Block euclase_block = new StorageBlock(MaterialColor.LIME);
    public static final Block leucite_block = new StorageBlock(MaterialColor.SAND);
    public static final Block carnelian_block = new StorageBlock(MaterialColor.RED);
    public static final Block benitoite_block = new StorageBlock(MaterialColor.BLUE);
    public static final Block diopside_block = new StorageBlock(MaterialColor.LIME);
    public static final Block chalcedony_block = new StorageBlock(MaterialColor.SNOW);

    //Ores
    public static final Block sugilite_ore = new GaiaOreBlock(MaterialColor.PURPLE, 1, 0, 3);
    public static final Block hematite_ore = new GaiaOreBlock(MaterialColor.GRAY, 2, 1, 4);
    public static final Block pyrite_ore = new GaiaOreBlock(MaterialColor.GOLD, 2, 1, 4);
    public static final Block opal_ore_red = new GaiaOreBlock(MaterialColor.RED, 2, 2, 5);
    public static final Block opal_ore_blue = new GaiaOreBlock(MaterialColor.LIGHT_BLUE, 2, 2, 5);
    public static final Block opal_ore_green = new GaiaOreBlock(MaterialColor.LIME, 2, 2, 5);
    public static final Block opal_ore_white = new GaiaOreBlock(MaterialColor.SNOW, 3, 3, 7);
    public static final Block labradorite_ore = new GaiaOreBlock(MaterialColor.GREEN, 2, 5, 2);
    public static final Block moonstone_ore = new GaiaOreBlock(MaterialColor.IRON, 2, 5, 2);
    public static final Block cinnabar_ore = new GaiaOreBlock(MaterialColor.ADOBE, 2, 1, 4);
    public static final Block speckled_rock = new GaiaOreBlock(MaterialColor.MAGENTA, 1);
    public static final Block coarse_rock = new GaiaOreBlock(MaterialColor.MAGENTA, 2);
    public static final Block precious_rock = new GaiaOreBlock(MaterialColor.MAGENTA, 3);
}
