package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.*;
import androsa.gaiadimension.world.gen.tree.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraftforge.common.ToolType.*;

@SuppressWarnings("unused")
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, GaiaDimensionMod.MODID);

    //Utility Blocks
    public static final RegistryObject<GaiaPortalBlock> gaia_portal = BLOCKS.register("gaia_portal", GaiaPortalBlock::new);
    public static final RegistryObject<Block> keystone_block = registerBlockGeneral("keystone_block", () -> new BasicGaiaBlock(Material.IRON, MaterialColor.GOLD, 5.0F, 10.0F,SoundType.METAL, PICKAXE, 2));
    public static final RegistryObject<Block> gold_fire = BLOCKS.register("gold_fire", GoldFireBlock::new);
    public static final RegistryObject<Block> pyrite_torch = BLOCKS.register("pyrite_torch", PyriteTorchBlock::new);
    public static final RegistryObject<Block> pyrite_wall_torch = BLOCKS.register("pyrite_wall_torch", PyriteWallTorchBlock::new);
    public static final RegistryObject<Block> agate_crafting_table = registerBlockGeneral("agate_crafting_table", AgateCraftingTableBlock::new);
    public static final RegistryObject<Block> crude_storage_crate = registerBlockGeneral("crude_storage_crate", SmallCrateBlock::new);
    public static final RegistryObject<Block> mega_storage_crate = registerBlockGeneral("mega_storage_crate", LargeCrateBlock::new);
    public static final RegistryObject<Block> gaia_stone_furnace = registerBlockGeneral("gaia_stone_furnace", GaiaStoneFurnaceBlock::new);
    public static final RegistryObject<Block> restructurer = registerBlockGeneral("restructurer", RestructurerBlock::new);
    public static final RegistryObject<Block> purifier = registerBlockGeneral("purifier", PurifierBlock::new);

    //Fluids
    public static final RegistryObject<FlowingFluidBlock> mineral_water = BLOCKS.register("mineral_water",
            () -> new GaiaFluidBlock(ModFluids.mineral_water_still, Block.Properties.create(Material.WATER, MaterialColor.LIGHT_BLUE_TERRACOTTA)));
    public static final RegistryObject<FlowingFluidBlock> superhot_magma = BLOCKS.register("superhot_magma",
            () -> new GaiaFluidBlock(ModFluids.superhot_magma_still, Block.Properties.create(Material.LAVA, MaterialColor.BLUE).tickRandomly().lightValue(15)));
    public static final RegistryObject<FlowingFluidBlock> sweet_muck = BLOCKS.register("sweet_muck",
            () -> new GaiaFluidBlock(ModFluids.sweet_muck_still, Block.Properties.create(Material.WATER, MaterialColor.PURPLE)));
    public static final RegistryObject<FlowingFluidBlock> liquid_bismuth = BLOCKS.register("liquid_bismuth",
            () -> new GaiaFluidBlock(ModFluids.liquid_bismuth_still, Block.Properties.create(Material.LAVA).tickRandomly().lightValue(3)));
    public static final RegistryObject<FlowingFluidBlock> liquid_aura = BLOCKS.register("liquid_aura",
            () -> new GaiaFluidBlock(ModFluids.liquid_aura_still, Block.Properties.create(Material.WATER)));

    //Natural Blocks
    public static final RegistryObject<Block> heavy_soil = registerBlockGeneral("heavy_soil", () -> new GaiaSoilBlock(MaterialColor.PURPLE_TERRACOTTA));
    public static final RegistryObject<Block> corrupt_soil = registerBlockGeneral("corrupt_soil", () -> new GaiaSoilBlock(MaterialColor.GRAY));
    public static final RegistryObject<Block> boggy_soil = registerBlockGeneral("boggy_soil", () -> new GaiaSoilBlock(MaterialColor.GRAY));
    public static final RegistryObject<Block> light_soil = registerBlockGeneral("light_soil", () -> new GaiaSoilBlock(MaterialColor.GOLD));
    public static final RegistryObject<Block> glitter_grass = registerBlockGeneral("glitter_grass", GlitterGrassBlock::new);
    public static final RegistryObject<Block> corrupt_grass = registerBlockGeneral("corrupt_grass", CorruptGrassBlock::new);
    public static final RegistryObject<Block> murky_grass = registerBlockGeneral("murky_grass", MurkyGrassBlock::new);
    public static final RegistryObject<Block> soft_grass = registerBlockGeneral("soft_grass", SoftGrassBlock::new);
    public static final RegistryObject<Block> frail_glitter_block = registerBlockGeneral("frail_glitter_block", () -> new GaiaGlassBlock(MaterialColor.PINK, 1.0F));
    public static final RegistryObject<Block> thick_glitter_block = registerBlockGeneral("thick_glitter_block", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA, 1.5F, 7.5F, PICKAXE, 1));
    public static final RegistryObject<Block> gummy_glitter_block = registerBlockGeneral("gummy_glitter_block", GummyGlitterBlock::new);
    public static final RegistryObject<Block> pink_sludge_block = registerBlockGeneral("pink_sludge_block", PinkSludgeBlock::new);

    //Plants
    public static final RegistryObject<Block> crystal_growth = registerBlockGeneral("crystal_growth", () -> new CrystalGrowthBlock(MaterialColor.SNOW));
    public static final RegistryObject<Block> crystal_growth_red = registerBlockGeneral("crystal_growth_red", () -> new CrystalGrowthBlock(MaterialColor.RED));
    public static final RegistryObject<Block> crystal_growth_black = registerBlockGeneral("crystal_growth_black", () -> new CrystalGrowthBlock(MaterialColor.BLACK));
    public static final RegistryObject<Block> crystal_growth_seared = registerBlockGeneral("crystal_growth_seared", () -> new CrystalGrowthBlock(MaterialColor.BLACK));
    public static final RegistryObject<Block> crystal_growth_mutant = registerBlockGeneral("crystal_growth_mutant", () -> new CrystalGrowthBlock(MaterialColor.WHITE_TERRACOTTA));
    public static final RegistryObject<Block> crystal_growth_aura = registerBlockGeneral("crystal_growth_aura", () -> new CrystalGrowthBlock(MaterialColor.LIGHT_BLUE_TERRACOTTA));
    public static final RegistryObject<Block> thiscus = registerBlockGeneral("thiscus", CrystalBloomBlock::new);
    public static final RegistryObject<Block> ouzium = registerBlockGeneral("ouzium", CrystalBloomBlock::new);
    public static final RegistryObject<Block> agathum = registerBlockGeneral("agathum", CrystalBloomBlock::new);
    public static final RegistryObject<Block> varloom = registerBlockGeneral("varloom", CrystalBloomBlock::new);
    public static final RegistryObject<Block> corrupted_varloom = registerBlockGeneral("corrupted_varloom", CrystalBloomBlock::new);
    public static final RegistryObject<Block> missingno_plant = registerBlockGeneral("missingno_plant", CrystalBloomBlock::new);
    public static final RegistryObject<Block> spotted_kersei = registerBlockGeneral("spotted_kersei", () -> new CrystalFungusBlock(MaterialColor.PINK, false));
    public static final RegistryObject<Block> thorny_wiltha = registerBlockGeneral("thorny_wiltha", () -> new CrystalFungusBlock(MaterialColor.LIGHT_BLUE, false));
    public static final RegistryObject<Block> roofed_agaric = registerBlockGeneral("roofed_agaric", () -> new CrystalFungusBlock(MaterialColor.LIME, false));
    public static final RegistryObject<Block> bulbous_hobina = registerBlockGeneral("bulbous_hobina", () -> new CrystalFungusBlock(MaterialColor.PINK_TERRACOTTA, false));
    public static final RegistryObject<Block> stickly_cupsir = registerBlockGeneral("stickly_cupsir", () -> new CrystalFungusBlock(MaterialColor.YELLOW_TERRACOTTA, false));
    public static final RegistryObject<Block> mystical_murgni = registerBlockGeneral("mystical_murgni", () -> new CrystalFungusBlock(MaterialColor.GOLD, false));
    public static final RegistryObject<Block> corrupted_gaia_eye = registerBlockGeneral("corrupted_gaia_eye", () -> new CrystalFungusBlock(MaterialColor.TNT, false));
    //public static final RegistryObject<Block> sacred_gaia_eye = registerBlockGeneral("sacred_gaia_eye", () -> new CrystalFungusBlock(false));
    public static final RegistryObject<Block> elder_imklia = registerBlockGeneral("elder_imklia", () -> new CrystalFungusBlock(MaterialColor.PURPLE, true));
    public static final RegistryObject<Block> gold_orb_tucher = registerBlockGeneral("gold_orb_tucher", () -> new CrystalFungusBlock(MaterialColor.GOLD, true));
    public static final RegistryObject<Block> missingno_fungus = registerBlockGeneral("missingno_fungus", () -> new CrystalFungusBlock(MaterialColor.MAGENTA, false));

    //Tree Blocks
    public static final LogBlock s_pink_agate_log = new AgateLogBlock(MaterialColor.MAGENTA, MaterialColor.MAGENTA);
    public static final LogBlock s_blue_agate_log = new AgateLogBlock(MaterialColor.BLUE, MaterialColor.BLUE);
    public static final LogBlock s_green_agate_log = new AgateLogBlock(MaterialColor.GREEN, MaterialColor.GREEN);
    public static final LogBlock s_purple_agate_log = new AgateLogBlock(MaterialColor.PURPLE_TERRACOTTA, MaterialColor.PURPLE_TERRACOTTA);
    public static final LogBlock s_fossilized_log = new AgateLogBlock(MaterialColor.YELLOW, MaterialColor.YELLOW);
    public static final LogBlock s_corrupted_log = new AgateLogBlock(MaterialColor.TNT, MaterialColor.TNT);
    public static final LogBlock s_burnt_log = new AgateLogBlock(MaterialColor.GRAY, MaterialColor.GRAY);
    public static final LogBlock s_burning_log = new AgateLogBlock(MaterialColor.ADOBE, MaterialColor.ADOBE, 3);
    public static final LogBlock s_aura_log = new AgateLogBlock(MaterialColor.IRON, MaterialColor.IRON);
    public static final LogBlock s_pink_agate_wood = new AgateLogBlock(MaterialColor.MAGENTA, MaterialColor.MAGENTA);
    public static final LogBlock s_blue_agate_wood = new AgateLogBlock(MaterialColor.BLUE, MaterialColor.BLUE);
    public static final LogBlock s_green_agate_wood = new AgateLogBlock(MaterialColor.GREEN, MaterialColor.GREEN);
    public static final LogBlock s_purple_agate_wood = new AgateLogBlock(MaterialColor.PURPLE_TERRACOTTA, MaterialColor.PURPLE_TERRACOTTA);
    public static final LogBlock s_fossilized_wood = new AgateLogBlock(MaterialColor.YELLOW, MaterialColor.YELLOW);
    public static final LogBlock s_corrupted_wood = new AgateLogBlock(MaterialColor.TNT, MaterialColor.TNT);
    public static final LogBlock s_burnt_wood = new AgateLogBlock(MaterialColor.GRAY, MaterialColor.GRAY);
    public static final LogBlock s_burning_wood = new AgateLogBlock(MaterialColor.ADOBE, MaterialColor.ADOBE, 3);
    public static final LogBlock s_aura_wood = new AgateLogBlock(MaterialColor.IRON, MaterialColor.IRON);

    public static final RegistryObject<SaplingBlock> pink_agate_sapling = registerBlockGeneral("pink_agate_sapling", () -> new AgateSaplingBlock(MaterialColor.PINK, new PinkAgateTree()));
    public static final RegistryObject<SaplingBlock> blue_agate_sapling = registerBlockGeneral("blue_agate_sapling", () -> new AgateSaplingBlock(MaterialColor.LIGHT_BLUE, new BlueAgateTree()));
    public static final RegistryObject<SaplingBlock> green_agate_sapling = registerBlockGeneral("green_agate_sapling", () -> new AgateSaplingBlock(MaterialColor.LIME, new GreenAgateTree()));
    public static final RegistryObject<SaplingBlock> purple_agate_sapling = registerBlockGeneral("purple_agate_sapling", () -> new AgateSaplingBlock(MaterialColor.PURPLE_TERRACOTTA, new PurpleAgateTree()));
    public static final RegistryObject<SaplingBlock> fossilized_sapling = registerBlockGeneral("fossilized_sapling", () -> new AgateSaplingBlock(MaterialColor.YELLOW_TERRACOTTA, new FossilizedTree()));
    public static final RegistryObject<SaplingBlock> corrupted_sapling = registerBlockGeneral("corrupted_sapling", () -> new AgateSaplingBlock(MaterialColor.BLACK_TERRACOTTA, new GoldstoneCorruptTree()));
    public static final RegistryObject<SaplingBlock> burnt_sapling = registerBlockGeneral("burnt_sapling", () -> new AgateSaplingBlock(MaterialColor.BLACK, new BurntAgateTree()));
    public static final RegistryObject<SaplingBlock> burning_sapling = registerBlockWithFuel("burning_sapling", () -> new AgateSaplingBlock(MaterialColor.ORANGE_TERRACOTTA, new FieryAgateTree()), 100);
    public static final RegistryObject<SaplingBlock> aura_sapling = registerBlockGeneral("aura_sapling", () -> new AgateSaplingBlock(MaterialColor.SNOW, new AuraTree()));
    public static final RegistryObject<Block> pink_agate_leaves = registerBlockGeneral("pink_agate_leaves", () -> new AgateLeavesBlock(MaterialColor.MAGENTA));
    public static final RegistryObject<Block> blue_agate_leaves = registerBlockGeneral("blue_agate_leaves", () -> new AgateLeavesBlock(MaterialColor.BLUE));
    public static final RegistryObject<Block> green_agate_leaves = registerBlockGeneral("green_agate_leaves", () -> new AgateLeavesBlock(MaterialColor.GREEN));
    public static final RegistryObject<Block> purple_agate_leaves = registerBlockGeneral("purple_agate_leaves", () -> new AgateLeavesBlock(MaterialColor.PURPLE_TERRACOTTA));
    public static final RegistryObject<Block> fossilized_leaves = registerBlockGeneral("fossilized_leaves", () -> new AgateLeavesBlock(MaterialColor.YELLOW));
    public static final RegistryObject<Block> corrupted_leaves = registerBlockGeneral("corrupted_leaves", () -> new AgateLeavesBlock(MaterialColor.TNT));
    public static final RegistryObject<Block> burnt_leaves = registerBlockGeneral("burnt_leaves", () -> new AgateLeavesBlock(MaterialColor.GRAY));
    public static final RegistryObject<Block> burning_leaves = registerBlockWithFuel("burning_leaves", () -> new AgateLeavesBlock(MaterialColor.ORANGE_TERRACOTTA, 3), 200);
    public static final RegistryObject<Block> aura_leaves = registerBlockGeneral("aura_leaves", () -> new AgateLeavesBlock(MaterialColor.IRON));
    public static final RegistryObject<LogBlock> pink_agate_log = registerBlockGeneral("pink_agate_log", () -> new AgateLogBlock(() -> s_pink_agate_log, MaterialColor.MAGENTA, MaterialColor.PINK_TERRACOTTA));
    public static final RegistryObject<LogBlock> blue_agate_log = registerBlockGeneral("blue_agate_log", () -> new AgateLogBlock(() -> s_blue_agate_log, MaterialColor.BLUE, MaterialColor.BLUE_TERRACOTTA));
    public static final RegistryObject<LogBlock> green_agate_log = registerBlockGeneral("green_agate_log", () -> new AgateLogBlock(() -> s_green_agate_log, MaterialColor.GREEN, MaterialColor.LIME_TERRACOTTA));
    public static final RegistryObject<LogBlock> purple_agate_log = registerBlockGeneral("purple_agate_log", () -> new AgateLogBlock(() -> s_purple_agate_log, MaterialColor.PURPLE_TERRACOTTA, MaterialColor.PURPLE));
    public static final RegistryObject<LogBlock> fossilized_log = registerBlockGeneral("fossilized_log", () -> new AgateLogBlock(() -> s_fossilized_log, MaterialColor.YELLOW, MaterialColor.DIRT));
    public static final RegistryObject<LogBlock> corrupted_log = registerBlockGeneral("corrupted_log", () -> new AgateLogBlock(() -> s_corrupted_log, MaterialColor.TNT, MaterialColor.GRAY_TERRACOTTA));
    public static final RegistryObject<LogBlock> burnt_log = registerBlockGeneral("burnt_log", () -> new AgateLogBlock(() -> s_burnt_log, MaterialColor.GRAY, MaterialColor.BLACK_TERRACOTTA));
    public static final RegistryObject<LogBlock> burning_log = registerBlockWithFuel("burning_log", () -> new AgateLogBlock(() -> s_burning_log, MaterialColor.ADOBE, MaterialColor.ORANGE_TERRACOTTA, 3), 1600);
    public static final RegistryObject<LogBlock> aura_log = registerBlockGeneral("aura_log", () -> new AgateLogBlock(() -> s_aura_log, MaterialColor.IRON, MaterialColor.GRAY));
    public static final RegistryObject<LogBlock> stripped_pink_agate_log = registerBlockGeneral("stripped_pink_agate_log", () -> s_pink_agate_log);
    public static final RegistryObject<LogBlock> stripped_blue_agate_log = registerBlockGeneral("stripped_blue_agate_log", () -> s_blue_agate_log);
    public static final RegistryObject<LogBlock> stripped_green_agate_log = registerBlockGeneral("stripped_green_agate_log", () -> s_green_agate_log);
    public static final RegistryObject<LogBlock> stripped_purple_agate_log = registerBlockGeneral("stripped_purple_agate_log", () -> s_purple_agate_log);
    public static final RegistryObject<LogBlock> stripped_fossilized_log = registerBlockGeneral("stripped_fossilized_log", () -> s_fossilized_log);
    public static final RegistryObject<LogBlock> stripped_corrupted_log = registerBlockGeneral("stripped_corrupted_log", () -> s_corrupted_log);
    public static final RegistryObject<LogBlock> stripped_burnt_log = registerBlockGeneral("stripped_burnt_log", () -> s_burnt_log);
    public static final RegistryObject<LogBlock> stripped_burning_log = registerBlockWithFuel("stripped_burning_log", () -> s_burning_log, 1600);
    public static final RegistryObject<LogBlock> stripped_aura_log = registerBlockGeneral("stripped_aura_log", () -> s_aura_log);
    public static final RegistryObject<LogBlock> pink_agate_wood = registerBlockGeneral("pink_agate_wood", () -> new AgateLogBlock(() -> s_pink_agate_wood, MaterialColor.PINK_TERRACOTTA, MaterialColor.PINK_TERRACOTTA));
    public static final RegistryObject<LogBlock> blue_agate_wood = registerBlockGeneral("blue_agate_wood", () -> new AgateLogBlock(() -> s_blue_agate_wood, MaterialColor.BLUE_TERRACOTTA, MaterialColor.BLUE_TERRACOTTA));
    public static final RegistryObject<LogBlock> green_agate_wood = registerBlockGeneral("green_agate_wood", () -> new AgateLogBlock(() -> s_green_agate_wood, MaterialColor.LIME_TERRACOTTA, MaterialColor.LIME_TERRACOTTA));
    public static final RegistryObject<LogBlock> purple_agate_wood = registerBlockGeneral("purple_agate_wood", () -> new AgateLogBlock(() -> s_purple_agate_wood, MaterialColor.PURPLE, MaterialColor.PURPLE));
    public static final RegistryObject<LogBlock> fossilized_wood = registerBlockGeneral("fossilized_wood", () -> new AgateLogBlock(() -> s_fossilized_wood, MaterialColor.DIRT, MaterialColor.DIRT));
    public static final RegistryObject<LogBlock> corrupted_wood = registerBlockGeneral("corrupted_wood", () -> new AgateLogBlock(() -> s_corrupted_wood, MaterialColor.GRAY_TERRACOTTA, MaterialColor.GRAY_TERRACOTTA));
    public static final RegistryObject<LogBlock> burnt_wood = registerBlockGeneral("burnt_wood", () -> new AgateLogBlock(() -> s_burnt_wood, MaterialColor.BLACK_TERRACOTTA, MaterialColor.BLACK_TERRACOTTA));
    public static final RegistryObject<LogBlock> burning_wood = registerBlockWithFuel("burning_wood", () -> new AgateLogBlock(() -> s_burning_wood, MaterialColor.ORANGE_TERRACOTTA, MaterialColor.ORANGE_TERRACOTTA, 3), 1600);
    public static final RegistryObject<LogBlock> aura_wood = registerBlockGeneral("aura_wood", () -> new AgateLogBlock(() -> s_aura_wood, MaterialColor.GRAY, MaterialColor.GRAY));
    public static final RegistryObject<LogBlock> stripped_pink_agate_wood = registerBlockGeneral("stripped_pink_agate_wood", () -> s_pink_agate_wood);
    public static final RegistryObject<LogBlock> stripped_blue_agate_wood = registerBlockGeneral("stripped_blue_agate_wood", () -> s_blue_agate_wood);
    public static final RegistryObject<LogBlock> stripped_green_agate_wood = registerBlockGeneral("stripped_green_agate_wood", () -> s_green_agate_wood);
    public static final RegistryObject<LogBlock> stripped_purple_agate_wood = registerBlockGeneral("stripped_purple_agate_wood", () -> s_purple_agate_wood);
    public static final RegistryObject<LogBlock> stripped_fossilized_wood = registerBlockGeneral("stripped_fossilized_wood", () -> s_fossilized_wood);
    public static final RegistryObject<LogBlock> stripped_corrupted_wood = registerBlockGeneral("stripped_corrupted_wood", () -> s_corrupted_wood);
    public static final RegistryObject<LogBlock> stripped_burnt_wood = registerBlockGeneral("stripped_burnt_wood", () -> s_burnt_wood);
    public static final RegistryObject<LogBlock> stripped_burning_wood = registerBlockWithFuel("stripped_burning_wood", () -> s_burning_wood, 1600);
    public static final RegistryObject<LogBlock> stripped_aura_wood = registerBlockGeneral("stripped_aura_wood", () -> s_aura_wood);

    public static final RegistryObject<Block> salt = registerBlockGeneral("salt", () -> new GaiaFallingBlock(MaterialColor.SNOW, 0.9F, SoundType.SAND, 0xE0E0FF));
    public static final RegistryObject<Block> saltstone = registerBlockGeneral("saltstone", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.LIGHT_BLUE_TERRACOTTA, 1.5F, 10.0F, PICKAXE, 0));
    public static final RegistryObject<Block> pebbles = registerBlockGeneral("pebbles", () -> new GaiaFallingBlock(MaterialColor.GRAY, 1.3F, SoundType.GROUND, 0x663366));
    public static final RegistryObject<Block> gaia_stone = registerBlockGeneral("gaia_stone", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.MAGENTA, 2.0F, 15.0F, PICKAXE, 1));
    public static final RegistryObject<Block> gaia_cobblestone = registerBlockGeneral("gaia_cobblestone", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.MAGENTA, 2.0F, 15.0F, PICKAXE, 1));
    public static final RegistryObject<Block> wasteland_stone = registerBlockGeneral("wasteland_stone", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.BLUE_TERRACOTTA, 15.0F, 200.0F, PICKAXE, 2));
    public static final RegistryObject<Block> static_stone = registerBlockGeneral("static_stone", StaticStoneBlock::new);
    public static final RegistryObject<Block> charged_mineral = registerBlockGeneral("charged_mineral", ChargedMineralBlock::new);
    public static final RegistryObject<Block> volcanic_rock = registerBlockGeneral("volcanic_rock", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.GRAY_TERRACOTTA, 15.0F, 200.0F, PICKAXE, 2));
    public static final RegistryObject<Block> searing_rock = registerBlockGeneral("searing_rock", SearingRockBlock::new);
    public static final RegistryObject<Block> primal_mass = registerBlockGeneral("primal_mass", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.PURPLE_TERRACOTTA, 30.0F, 400.0F, PICKAXE, 2));
    public static final RegistryObject<Block> impure_rock = registerBlockGeneral("impure_rock", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.GRAY, 20.0F, 300.0F, PICKAXE, 2));
    public static final RegistryObject<Block> active_rock = registerBlockGeneral("active_rock", ActiveRockBlock::new);
    public static final RegistryObject<Block> impure_sludge = registerBlockGeneral("impure_sludge", ImpureSludgeBlock::new);
    public static final RegistryObject<Block> geyser_block = registerBlockGeneral("geyser_block", GeyserBlock::new);
    public static final RegistryObject<Block> sparkling_rock = registerBlockGeneral("sparkling_rock", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.IRON, 10.0F, 150.0F, SoundType.GLASS, PICKAXE, 1));
    public static final RegistryObject<Block> aura_shoot = registerBlockGeneral("aura_shoot", AuraShootBlock::new);

    //Planks
    public static final RegistryObject<Block> pink_agate_planks = registerBlockGeneral("pink_agate_planks", () -> new AgatePlanksBlock(MaterialColor.PINK));
    public static final RegistryObject<Block> blue_agate_planks = registerBlockGeneral("blue_agate_planks", () -> new AgatePlanksBlock(MaterialColor.LIGHT_BLUE));
    public static final RegistryObject<Block> green_agate_planks = registerBlockGeneral("green_agate_planks", () -> new AgatePlanksBlock(MaterialColor.LIME));
    public static final RegistryObject<Block> purple_agate_planks = registerBlockGeneral("purple_agate_planks", () -> new AgatePlanksBlock(MaterialColor.PURPLE_TERRACOTTA));
    public static final RegistryObject<Block> fossilized_planks = registerBlockGeneral("fossilized_planks", () -> new AgatePlanksBlock(MaterialColor.YELLOW_TERRACOTTA));
    public static final RegistryObject<Block> corrupted_planks = registerBlockGeneral("corrupted_planks", () -> new AgatePlanksBlock(MaterialColor.BLACK_TERRACOTTA));
    public static final RegistryObject<Block> burnt_planks = registerBlockGeneral("burnt_planks", () -> new AgatePlanksBlock(MaterialColor.BLACK));
    public static final RegistryObject<Block> burning_planks = registerBlockWithFuel("burning_planks", () -> new AgatePlanksBlock(MaterialColor.ORANGE_TERRACOTTA, 3), 400);
    public static final RegistryObject<Block> aura_planks = registerBlockGeneral("aura_planks", () -> new AgatePlanksBlock(MaterialColor.SNOW));
    public static final RegistryObject<SlabBlock> pink_agate_plank_slab = registerBlockGeneral("pink_agate_plank_slab", () -> new AgatePlanksSlabBlock(MaterialColor.PINK));
    public static final RegistryObject<SlabBlock> blue_agate_plank_slab = registerBlockGeneral("blue_agate_plank_slab", () -> new AgatePlanksSlabBlock(MaterialColor.LIGHT_BLUE));
    public static final RegistryObject<SlabBlock> green_agate_plank_slab = registerBlockGeneral("green_agate_plank_slab", () -> new AgatePlanksSlabBlock(MaterialColor.LIME));
    public static final RegistryObject<SlabBlock> purple_agate_plank_slab = registerBlockGeneral("purple_agate_plank_slab", () -> new AgatePlanksSlabBlock(MaterialColor.PURPLE_TERRACOTTA));
    public static final RegistryObject<SlabBlock> fossilized_plank_slab = registerBlockGeneral("fossilized_plank_slab", () -> new AgatePlanksSlabBlock(MaterialColor.YELLOW_TERRACOTTA));
    public static final RegistryObject<SlabBlock> corrupted_plank_slab = registerBlockGeneral("corrupted_plank_slab", () -> new AgatePlanksSlabBlock(MaterialColor.BLACK_TERRACOTTA));
    public static final RegistryObject<SlabBlock> burnt_plank_slab = registerBlockGeneral("burnt_plank_slab", () -> new AgatePlanksSlabBlock(MaterialColor.BLACK));
    public static final RegistryObject<SlabBlock> burning_plank_slab = registerBlockWithFuel("burning_plank_slab", () -> new AgatePlanksSlabBlock(MaterialColor.ORANGE_TERRACOTTA, 7), 200);
    public static final RegistryObject<SlabBlock> aura_plank_slab = registerBlockGeneral("aura_plank_slab", () -> new AgatePlanksSlabBlock(MaterialColor.SNOW));
    public static final RegistryObject<StairsBlock> pink_agate_plank_stairs = registerBlockGeneral("pink_agate_plank_stairs",
            () -> new AgatePlankStairsBlock(pink_agate_planks.get().getDefaultState(), MaterialColor.PINK));
    public static final RegistryObject<StairsBlock> blue_agate_plank_stairs = registerBlockGeneral("blue_agate_plank_stairs",
            () -> new AgatePlankStairsBlock(blue_agate_planks.get().getDefaultState(), MaterialColor.LIGHT_BLUE));
    public static final RegistryObject<StairsBlock> green_agate_plank_stairs = registerBlockGeneral("green_agate_plank_stairs",
            () -> new AgatePlankStairsBlock(green_agate_planks.get().getDefaultState(), MaterialColor.LIME));
    public static final RegistryObject<StairsBlock> purple_agate_plank_stairs = registerBlockGeneral("purple_agate_plank_stairs",
            () -> new AgatePlankStairsBlock(purple_agate_planks.get().getDefaultState(), MaterialColor.PURPLE_TERRACOTTA));
    public static final RegistryObject<StairsBlock> fossilized_plank_stairs = registerBlockGeneral("fossilized_plank_stairs",
            () -> new AgatePlankStairsBlock(fossilized_planks.get().getDefaultState(), MaterialColor.YELLOW_TERRACOTTA));
    public static final RegistryObject<StairsBlock> corrupted_plank_stairs = registerBlockGeneral("corrupted_plank_stairs",
            () -> new AgatePlankStairsBlock(corrupted_planks.get().getDefaultState(), MaterialColor.BLACK_TERRACOTTA));
    public static final RegistryObject<StairsBlock> burnt_plank_stairs = registerBlockGeneral("burnt_plank_stairs",
            () -> new AgatePlankStairsBlock(burnt_planks.get().getDefaultState(), MaterialColor.BLACK));
    public static final RegistryObject<StairsBlock> burning_plank_stairs = registerBlockWithFuel("burning_plank_stairs",
            () -> new AgatePlankStairsBlock(burning_planks.get().getDefaultState(), MaterialColor.ORANGE_TERRACOTTA, 7), 300);
    public static final RegistryObject<StairsBlock> aura_plank_stairs = registerBlockGeneral("aura_plank_stairs",
            () -> new AgatePlankStairsBlock(aura_planks.get().getDefaultState(), MaterialColor.SNOW));

    //Manufactured
    public static final RegistryObject<Block> cloudy_glass = registerBlockGeneral("cloudy_glass", () -> new GaiaGlassBlock(MaterialColor.YELLOW, 0.7F));
    public static final RegistryObject<Block> foggy_glass = registerBlockGeneral("foggy_glass", () -> new GaiaGlassBlock(MaterialColor.LIGHT_BLUE, 0.7F));
    public static final RegistryObject<Block> gaia_stone_bricks = registerBlockGeneral("gaia_stone_bricks", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.MAGENTA, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> cracked_gaia_stone_bricks = registerBlockGeneral("cracked_gaia_stone_bricks", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.MAGENTA, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> crusted_gaia_stone_bricks = registerBlockGeneral("crusted_gaia_stone_bricks", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.MAGENTA, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> reinforced_bricks = registerBlockGeneral("reinforced_bricks", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.PURPLE, 10.0F, 100.0F, PICKAXE, 1));
    public static final RegistryObject<Block> bolstered_bricks = registerBlockGeneral("bolstered_bricks", () -> new BasicGaiaBlock(Material.ROCK, MaterialColor.SAND, 30.0F, 400.0F, PICKAXE, 2));
    public static final RegistryObject<Block> malachite_bricks = registerBlockGeneral("malachite_bricks", MalachiteBricksBlock::new);
    public static final RegistryObject<Block> malachite_cracked_bricks = registerBlockGeneral("malachite_cracked_bricks", MalachiteBricksBlock::new);
    public static final RegistryObject<Block> malachite_crusted_bricks = registerBlockGeneral("malachite_crusted_bricks", MalachiteBricksBlock::new);
    public static final RegistryObject<Block> malachite_floor_tiles = registerBlockGeneral("malachite_floor_tiles", MalachiteBricksBlock::new);
    public static final RegistryObject<Block> malachite_chisel_bricks = registerBlockGeneral("malachite_chisel_bricks", MalachiteBricksBlock::new);
    public static final RegistryObject<Block> malachite_pulsing_bricks = registerBlockGeneral("malachite_pulsing_bricks", MalachiteBricksBlock::new);
    public static final RegistryObject<Block> malachite_pulsing_tiles = registerBlockGeneral("malachite_pulsing_tiles", MalachiteBricksBlock::new);
    public static final RegistryObject<Block> malachite_pulsing_chisel = registerBlockGeneral("malachite_pulsing_chisel", MalachiteBricksBlock::new);
    public static final RegistryObject<SlabBlock> malachite_brick_slab = registerBlockGeneral("malachite_brick_slab", MalachiteBrickSlabBlock::new);
    public static final RegistryObject<SlabBlock> malachite_floor_slab = registerBlockGeneral("malachite_floor_slab", MalachiteBrickSlabBlock::new);
    public static final RegistryObject<RotatedPillarBlock> malachite_pillar = registerBlockGeneral("malachite_pillar", MalachiteBrickPillarBlock::new);
    public static final RegistryObject<StairsBlock> malachite_brick_stairs = registerBlockGeneral("malachite_brick_stairs", () -> new MalachiteStairsBlock(malachite_bricks));
    public static final RegistryObject<StairsBlock> malachite_floor_stairs = registerBlockGeneral("malachite_floor_stairs", () -> new MalachiteStairsBlock(malachite_floor_tiles));
    public static final RegistryObject<StairsBlock> malachite_chisel_stairs = registerBlockGeneral("malachite_chisel_stairs", () -> new MalachiteStairsBlock(malachite_chisel_bricks));
    public static final RegistryObject<StairsBlock> malachite_pulsing_brick_stairs = registerBlockGeneral("malachite_pulsing_brick_stairs", () -> new MalachiteStairsBlock(malachite_bricks));
    public static final RegistryObject<StairsBlock> malachite_pulsing_floor_stairs = registerBlockGeneral("malachite_pulsing_floor_stairs", () -> new MalachiteStairsBlock(malachite_floor_tiles));
    public static final RegistryObject<StairsBlock> malachite_pulsing_chisel_stairs = registerBlockGeneral("malachite_pulsing_chisel_stairs", () -> new MalachiteStairsBlock(malachite_chisel_bricks));
    public static final RegistryObject<StairsBlock> malachite_pillar_stairs = registerBlockGeneral("malachite_pillar_stairs", () -> new MalachiteStairsBlock(malachite_pillar));

    //Storage Blocks
    public static final RegistryObject<Block> sugilite_block = registerBlockGeneral("sugilite_block", () -> new StorageBlock(MaterialColor.PURPLE));
    public static final RegistryObject<Block> hematite_block = registerBlockGeneral("hematite_block", () -> new StorageBlock(MaterialColor.GRAY));
    public static final RegistryObject<Block> cinnabar_block = registerBlockGeneral("cinnabar_block", () -> new StorageBlock(MaterialColor.ADOBE));
    public static final RegistryObject<Block> labradorite_block = registerBlockGeneral("labradorite_block", () -> new StorageBlock(MaterialColor.GREEN));
    public static final RegistryObject<Block> moonstone_block = registerBlockGeneral("moonstone_block", () -> new StorageBlock(MaterialColor.IRON));
    public static final RegistryObject<Block> opal_block_red = registerBlockGeneral("opal_block_red", () -> new StorageBlock(MaterialColor.RED));
    public static final RegistryObject<Block> opal_block_blue = registerBlockGeneral("opal_block_blue", () -> new StorageBlock(MaterialColor.LIGHT_BLUE));
    public static final RegistryObject<Block> opal_block_green = registerBlockGeneral("opal_block_green", () -> new StorageBlock(MaterialColor.LIME));
    public static final RegistryObject<Block> opal_block_white = registerBlockGeneral("opal_block_white", () -> new StorageBlock(MaterialColor.SNOW));
    public static final RegistryObject<Block> pyrite_block = registerBlockGeneral("pyrite_block", () -> new StorageBlock(MaterialColor.GOLD, 15));
    public static final RegistryObject<Block> tektite_block = registerBlockGeneral("tektite_block", () -> new StorageBlock(MaterialColor.BLACK));
    public static final RegistryObject<Block> goldstone_block = registerBlockGeneral("goldstone_block", () -> new StorageBlock(MaterialColor.BLACK));
    public static final RegistryObject<Block> aura_block = registerBlockGeneral("aura_block", () -> new StorageBlock(MaterialColor.ICE));
    public static final RegistryObject<Block> bismuth_block = registerBlockGeneral("bismuth_block", () -> new StorageBlock(MaterialColor.OBSIDIAN));
    public static final RegistryObject<Block> ixiolite_block = registerBlockGeneral("ixiolite_block", () -> new StorageBlock(MaterialColor.GRAY));
    public static final RegistryObject<Block> proustite_block = registerBlockGeneral("proustite_block", () -> new StorageBlock(MaterialColor.MAGENTA));
    public static final RegistryObject<Block> euclase_block = registerBlockGeneral("euclase_block", () -> new StorageBlock(MaterialColor.LIME));
    public static final RegistryObject<Block> leucite_block = registerBlockGeneral("leucite_block", () -> new StorageBlock(MaterialColor.SAND));
    public static final RegistryObject<Block> carnelian_block = registerBlockGeneral("carnelian_block", () -> new StorageBlock(MaterialColor.RED));
    public static final RegistryObject<Block> benitoite_block = registerBlockGeneral("benitoite_block", () -> new StorageBlock(MaterialColor.BLUE));
    public static final RegistryObject<Block> diopside_block = registerBlockGeneral("diopside_block", () -> new StorageBlock(MaterialColor.LIME));
    public static final RegistryObject<Block> chalcedony_block = registerBlockGeneral("chalcedony_block", () -> new StorageBlock(MaterialColor.SNOW));

    //Ores
    public static final RegistryObject<Block> sugilite_ore = registerBlockGeneral("sugilite_ore", () -> new GaiaOreBlock(MaterialColor.PURPLE, 1, 1, 3));
    public static final RegistryObject<Block> hematite_ore = registerBlockGeneral("hematite_ore", () -> new GaiaOreBlock(MaterialColor.GRAY, 2, 1, 4));
    public static final RegistryObject<Block> cinnabar_ore = registerBlockGeneral("cinnabar_ore", () -> new GaiaOreBlock(MaterialColor.ADOBE, 2, 1, 4));
    public static final RegistryObject<Block> labradorite_ore = registerBlockGeneral("labradorite_ore", () -> new GaiaOreBlock(MaterialColor.GREEN, 2, 5, 2));
    public static final RegistryObject<Block> moonstone_ore = registerBlockGeneral("moonstone_ore", () -> new GaiaOreBlock(MaterialColor.IRON, 2, 5, 2));
    public static final RegistryObject<Block> opal_ore_red = registerBlockGeneral("opal_ore_red", () -> new GaiaOreBlock(MaterialColor.RED, 2, 2, 5));
    public static final RegistryObject<Block> opal_ore_blue = registerBlockGeneral("opal_ore_blue", () -> new GaiaOreBlock(MaterialColor.LIGHT_BLUE, 2, 2, 5));
    public static final RegistryObject<Block> opal_ore_green = registerBlockGeneral("opal_ore_green", () -> new GaiaOreBlock(MaterialColor.LIME, 2, 2, 5));
    public static final RegistryObject<Block> opal_ore_white = registerBlockGeneral("opal_ore_white", () -> new GaiaOreBlock(MaterialColor.SNOW, 3, 3, 7));
    public static final RegistryObject<Block> pyrite_ore = registerBlockGeneral("pyrite_ore", () -> new GaiaOreBlock(MaterialColor.GOLD, 2, 1, 4));
    public static final RegistryObject<Block> speckled_rock = registerBlockGeneral("speckled_rock", () -> new GaiaOreBlock(MaterialColor.MAGENTA, 1));
    public static final RegistryObject<Block> coarse_rock = registerBlockGeneral("coarse_rock", () -> new GaiaOreBlock(MaterialColor.MAGENTA, 2));
    public static final RegistryObject<Block> precious_rock = registerBlockGeneral("precious_rock", () -> new GaiaOreBlock(MaterialColor.MAGENTA, 3));

    //Flower Pots
    public static final RegistryObject<FlowerPotBlock> potted_thiscus = registerFlowerPot("potted_thiscus", thiscus);
    public static final RegistryObject<FlowerPotBlock> potted_ouzium = registerFlowerPot("potted_ouzium", ouzium);
    public static final RegistryObject<FlowerPotBlock> potted_agathum = registerFlowerPot("potted_agathum", agathum);
    public static final RegistryObject<FlowerPotBlock> potted_varloom = registerFlowerPot("potted_varloom", varloom);
    public static final RegistryObject<FlowerPotBlock> potted_corrupted_varloom = registerFlowerPot("potted_corrupted_varloom", corrupted_varloom);
    public static final RegistryObject<FlowerPotBlock> potted_missingno_plant = registerFlowerPot("potted_missingno_plant", missingno_plant);
    public static final RegistryObject<FlowerPotBlock> potted_spotted_kersei = registerFlowerPot("potted_spotted_kersei", spotted_kersei);
    public static final RegistryObject<FlowerPotBlock> potted_thorny_wiltha = registerFlowerPot("potted_thorny_wiltha", thorny_wiltha);
    public static final RegistryObject<FlowerPotBlock> potted_roofed_agaric = registerFlowerPot("potted_roofed_agaric", roofed_agaric);
    public static final RegistryObject<FlowerPotBlock> potted_bulbous_hobina = registerFlowerPot("potted_bulbous_hobina", bulbous_hobina);
    public static final RegistryObject<FlowerPotBlock> potted_stickly_cupsir = registerFlowerPot("potted_stickly_cupsir", stickly_cupsir);
    public static final RegistryObject<FlowerPotBlock> potted_mystical_murgni = registerFlowerPot("potted_mystical_murgni", mystical_murgni);
    public static final RegistryObject<FlowerPotBlock> potted_corrupted_gaia_eye = registerFlowerPot("potted_corrupted_gaia_eye", corrupted_gaia_eye);
    public static final RegistryObject<FlowerPotBlock> potted_elder_imklia = registerFlowerPot("potted_elder_imklia", elder_imklia);
    public static final RegistryObject<FlowerPotBlock> potted_gold_orb_tucher = registerFlowerPot("potted_gold_orb_tucher", gold_orb_tucher);
    public static final RegistryObject<FlowerPotBlock> potted_missingno_fungus = registerFlowerPot("potted_missingno_fungus", missingno_fungus);
    public static final RegistryObject<FlowerPotBlock> potted_pink_agate_sapling = registerFlowerPot("potted_pink_agate_sapling", pink_agate_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_blue_agate_sapling = registerFlowerPot("potted_blue_agate_sapling", blue_agate_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_green_agate_sapling = registerFlowerPot("potted_green_agate_sapling", green_agate_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_purple_agate_sapling = registerFlowerPot("potted_purple_agate_sapling", purple_agate_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_fossilized_sapling = registerFlowerPot("potted_fossilized_sapling", fossilized_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_corrupted_sapling = registerFlowerPot("potted_corrupted_sapling", corrupted_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_burnt_sapling = registerFlowerPot("potted_burnt_sapling", burnt_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_burning_sapling = registerFlowerPot("potted_burning_sapling", burning_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_aura_sapling = registerFlowerPot("potted_aura_sapling", aura_sapling);

    private static <T extends Block> RegistryObject<T> registerBlockGeneral(String name, Supplier<? extends T> block) {
        return registerBlock(name, block, 0, ModBlocks::registerBlockItem);
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithFuel(String name, Supplier<? extends T> block, int burnTime) {
        return registerBlock(name, block, burnTime, item -> registerBlockItemFuel(item, burnTime));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, int burnTime, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final RegistryObject<T> block) {
        return () -> new BlockItem(block.get(), new Item.Properties().group(GaiaItemGroups.GAIA_BLOCKS));
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemFuel(final RegistryObject<T> block, int burnTime) {
        return () -> new BlockItem(block.get(), new Item.Properties().group(GaiaItemGroups.GAIA_BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack) {
                return burnTime;
            }
        };
    }

    private static RegistryObject<FlowerPotBlock> registerFlowerPot(String name, Supplier<? extends Block> plant) {
        return BLOCKS.register(name, () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, plant, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));
    }

    public static void addPlants() {
        FlowerPotBlock block = (FlowerPotBlock) Blocks.FLOWER_POT;

        block.addPlant(thiscus.getId(), potted_thiscus);
        block.addPlant(ouzium.getId(), potted_ouzium);
        block.addPlant(agathum.getId(), potted_agathum);
        block.addPlant(varloom.getId(), potted_varloom);
        block.addPlant(corrupted_varloom.getId(), potted_corrupted_varloom);
        block.addPlant(missingno_plant.getId(), potted_missingno_plant);
        block.addPlant(spotted_kersei.getId(), potted_spotted_kersei);
        block.addPlant(thorny_wiltha.getId(), potted_thorny_wiltha);
        block.addPlant(roofed_agaric.getId(), potted_roofed_agaric);
        block.addPlant(bulbous_hobina.getId(), potted_bulbous_hobina);
        block.addPlant(stickly_cupsir.getId(), potted_stickly_cupsir);
        block.addPlant(mystical_murgni.getId(), potted_mystical_murgni);
        block.addPlant(corrupted_gaia_eye.getId(), potted_corrupted_gaia_eye);
        block.addPlant(elder_imklia.getId(), potted_elder_imklia);
        block.addPlant(gold_orb_tucher.getId(), potted_gold_orb_tucher);
        block.addPlant(missingno_fungus.getId(), potted_missingno_fungus);
        block.addPlant(pink_agate_sapling.getId(), potted_pink_agate_sapling);
        block.addPlant(blue_agate_sapling.getId(), potted_blue_agate_sapling);
        block.addPlant(green_agate_sapling.getId(), potted_green_agate_sapling);
        block.addPlant(purple_agate_sapling.getId(), potted_purple_agate_sapling);
        block.addPlant(fossilized_sapling.getId(), potted_fossilized_sapling);
        block.addPlant(corrupted_sapling.getId(), potted_corrupted_sapling);
        block.addPlant(burnt_sapling.getId(), potted_burnt_sapling);
        block.addPlant(burning_sapling.getId(), potted_burning_sapling);
        block.addPlant(aura_sapling.getId(), potted_aura_sapling);
    }
}