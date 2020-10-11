package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.*;
import androsa.gaiadimension.world.gen.tree.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.ObjectHolder;

import static net.minecraft.block.AbstractBlock.*;
import static net.minecraftforge.common.ToolType.*;

@ObjectHolder(value = GaiaDimensionMod.MODID)
@SuppressWarnings("unused")
public class ModBlocks {

    //Utility Blocks
    public static final GaiaPortalBlock gaia_portal = RegistryHelper.registerBlockOnly("gaia_portal", new GaiaPortalBlock(Properties.create(Material.PORTAL, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(-1.0F).doesNotBlockMovement().tickRandomly().setLightLevel((state) -> 15).noDrops()));
    public static final Block keystone_block = registerPlain("keystone_block", Properties.create(Material.IRON, MaterialColor.GOLD).hardnessAndResistance(5.0F, 10.0F).sound(SoundType.METAL).setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(2));
    public static final Block gold_fire = RegistryHelper.registerBlockOnly("gold_fire", new GoldFireBlock(Properties.create(Material.FIRE, MaterialColor.GOLD).hardnessAndResistance(0.0F).doesNotBlockMovement().tickRandomly().setLightLevel((state) -> 15).noDrops()));
    public static final Block pyrite_torch = RegistryHelper.registerBlockOnly("pyrite_torch", new PyriteTorchBlock(GaiaBlockProperties.torchProps()));
    public static final Block pyrite_wall_torch = RegistryHelper.registerBlockOnly("pyrite_wall_torch", new PyriteWallTorchBlock(GaiaBlockProperties.torchProps().lootFrom(pyrite_torch)));
    public static final Block agate_crafting_table = RegistryHelper.registerBlock("agate_crafting_table", new AgateCraftingTableBlock(GaiaBlockProperties.stoneProps(Material.WOOD, MaterialColor.PINK_TERRACOTTA, 1.5F, 2.0F, ToolType.AXE, 0)));
    public static final Block crude_storage_crate = RegistryHelper.registerBlock("crude_storage_crate", new SmallCrateBlock(GaiaBlockProperties.stoneProps(MaterialColor.PINK_TERRACOTTA, 10.0F, 150.0F, ToolType.AXE, 0)));
    public static final Block mega_storage_crate = RegistryHelper.registerBlock("mega_storage_crate", new LargeCrateBlock(GaiaBlockProperties.stoneProps(MaterialColor.PURPLE_TERRACOTTA, 10.0F, 300.0F, ToolType.AXE, 0)));
    public static final Block gaia_stone_furnace = RegistryHelper.registerBlock("gaia_stone_furnace", new GaiaStoneFurnaceBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.PINK_TERRACOTTA, 20.0F, 300.0F, ToolType.PICKAXE, 0).setLightLevel((state) -> state.get(AbstractFurnaceBlock.LIT) ? 13 : 0)));
    public static final Block restructurer = RegistryHelper.registerBlock("restructurer", new RestructurerBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE_TERRACOTTA, 20.0F, 300.0F, ToolType.PICKAXE, 1).setLightLevel((state) -> state.get(RestructurerBlock.LIT) ? 14 : 0)));
    public static final Block purifier = RegistryHelper.registerBlock("purifier", new PurifierBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.SAND, 20.0F, 300.0F, ToolType.PICKAXE, 2).setLightLevel((state) -> state.get(PurifierBlock.LIT) ? 14 : 0)));

    //Fluids
    public static final FlowingFluidBlock mineral_water = RegistryHelper.registerBlockOnly("mineral_water",
            new GaiaFluidBlock(() -> ModFluids.mineral_water_still, Block.Properties.create(Material.WATER, MaterialColor.LIGHT_BLUE_TERRACOTTA)));
    public static final FlowingFluidBlock superhot_magma = RegistryHelper.registerBlockOnly("superhot_magma",
            new GaiaFluidBlock(() -> ModFluids.superhot_magma_still, Block.Properties.create(Material.LAVA, MaterialColor.BLUE).tickRandomly().setLightLevel((state) -> 15)));
    public static final FlowingFluidBlock sweet_muck = RegistryHelper.registerBlockOnly("sweet_muck",
            new GaiaFluidBlock(() -> ModFluids.sweet_muck_still, Block.Properties.create(Material.WATER, MaterialColor.PURPLE)));
    public static final FlowingFluidBlock liquid_bismuth = RegistryHelper.registerBlockOnly("liquid_bismuth",
            new GaiaFluidBlock(() -> ModFluids.liquid_bismuth_still, Block.Properties.create(Material.LAVA).tickRandomly().setLightLevel((state) -> 3)));
    public static final FlowingFluidBlock liquid_aura = RegistryHelper.registerBlockOnly("liquid_aura",
            new GaiaFluidBlock(() -> ModFluids.liquid_aura_still, Block.Properties.create(Material.WATER)));

    //Natural Blocks
    public static final Block heavy_soil = RegistryHelper.registerBlock("heavy_soil", new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.PURPLE_TERRACOTTA)));
    public static final Block corrupt_soil = RegistryHelper.registerBlock("corrupt_soil", new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.GRAY)));
    public static final Block boggy_soil = RegistryHelper.registerBlock("boggy_soil", new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.GRAY)));
    public static final Block light_soil = RegistryHelper.registerBlock("light_soil", new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.GOLD)));
    public static final Block glitter_grass = RegistryHelper.registerBlock("glitter_grass", new GlitterGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.PINK)));
    public static final Block corrupt_grass = RegistryHelper.registerBlock("corrupt_grass", new CorruptGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.BLACK)));
    public static final Block murky_grass = RegistryHelper.registerBlock("murky_grass", new MurkyGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.GRAY)));
    public static final Block soft_grass = RegistryHelper.registerBlock("soft_grass", new SoftGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.CYAN)));
    public static final Block frail_glitter_block = RegistryHelper.registerBlock("frail_glitter_block", new GaiaGlassBlock(GaiaBlockProperties.glassProps(MaterialColor.PINK, 1.0F)));
    public static final Block thick_glitter_block = registerPlain("thick_glitter_block", GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE_TERRACOTTA, 1.5F, 7.5F, PICKAXE, 1));
    public static final Block gummy_glitter_block = RegistryHelper.registerBlock("gummy_glitter_block", new SlimeBlock(Properties.create(Material.CLAY, MaterialColor.PURPLE).sound(SoundType.SLIME).notSolid()));
    public static final Block pink_sludge_block = RegistryHelper.registerBlock("pink_sludge_block", new SlimeBlock(Properties.create(Material.CLAY, MaterialColor.PINK).sound(SoundType.SLIME)));

    //Plants
    public static final Block crystal_growth = RegistryHelper.registerBlock("crystal_growth", new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.SNOW)));
    public static final Block crystal_growth_red = RegistryHelper.registerBlock("crystal_growth_red", new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.RED)));
    public static final Block crystal_growth_black = RegistryHelper.registerBlock("crystal_growth_black", new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.BLACK)));
    public static final Block crystal_growth_seared = RegistryHelper.registerBlock("crystal_growth_seared", new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.BLACK)));
    public static final Block crystal_growth_mutant = RegistryHelper.registerBlock("crystal_growth_mutant", new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.WHITE_TERRACOTTA)));
    public static final Block crystal_growth_aura = RegistryHelper.registerBlock("crystal_growth_aura", new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.LIGHT_BLUE_TERRACOTTA)));
    public static final Block thiscus = RegistryHelper.registerBlock("thiscus", new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final Block ouzium = RegistryHelper.registerBlock("ouzium", new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final Block agathum = RegistryHelper.registerBlock("agathum", new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final Block varloom = RegistryHelper.registerBlock("varloom", new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final Block corrupted_varloom = RegistryHelper.registerBlock("corrupted_varloom", new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final Block missingno_plant = RegistryHelper.registerBlock("missingno_plant", new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final Block spotted_kersei = RegistryHelper.registerBlock("spotted_kersei", new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.PINK), false));
    public static final Block thorny_wiltha = RegistryHelper.registerBlock("thorny_wiltha", new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.LIGHT_BLUE), false));
    public static final Block roofed_agaric = RegistryHelper.registerBlock("roofed_agaric", new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.LIME), false));
    public static final Block bulbous_hobina = RegistryHelper.registerBlock("bulbous_hobina", new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.PINK_TERRACOTTA), false));
    public static final Block stickly_cupsir = RegistryHelper.registerBlock("stickly_cupsir", new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.YELLOW_TERRACOTTA), false));
    public static final Block mystical_murgni = RegistryHelper.registerBlock("mystical_murgni", new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.GOLD), false));
    public static final Block corrupted_gaia_eye = RegistryHelper.registerBlock("corrupted_gaia_eye", new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.TNT), false));
    //public static final Block sacred_gaia_eye = RegistryHelper.registerBlock()("sacred_gaia_eye", new CrystalFungusBlock(false));
    public static final Block elder_imklia = RegistryHelper.registerBlock("elder_imklia", new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.PURPLE), true));
    public static final Block gold_orb_tucher = RegistryHelper.registerBlock("gold_orb_tucher", new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.GOLD), true));
    public static final Block missingno_fungus = RegistryHelper.registerBlock("missingno_fungus", new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.MAGENTA), false));

    //Tree Blocks
    public static final RotatedPillarBlock s_pink_agate_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.MAGENTA));
    public static final RotatedPillarBlock s_blue_agate_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.BLUE));
    public static final RotatedPillarBlock s_green_agate_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.GREEN));
    public static final RotatedPillarBlock s_purple_agate_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.PURPLE_TERRACOTTA));
    public static final RotatedPillarBlock s_fossilized_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.YELLOW));
    public static final RotatedPillarBlock s_corrupted_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.TNT));
    public static final RotatedPillarBlock s_burnt_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.GRAY));
    public static final RotatedPillarBlock s_burning_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.ADOBE).setLightLevel((state) -> 3));
    public static final RotatedPillarBlock s_aura_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.IRON));
    public static final RotatedPillarBlock s_pink_agate_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.MAGENTA));
    public static final RotatedPillarBlock s_blue_agate_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.BLUE));
    public static final RotatedPillarBlock s_green_agate_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.GREEN));
    public static final RotatedPillarBlock s_purple_agate_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.PURPLE_TERRACOTTA));
    public static final RotatedPillarBlock s_fossilized_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.YELLOW));
    public static final RotatedPillarBlock s_corrupted_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.TNT));
    public static final RotatedPillarBlock s_burnt_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.GRAY));
    public static final RotatedPillarBlock s_burning_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.ADOBE).setLightLevel((state) -> 3));
    public static final RotatedPillarBlock s_aura_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.IRON));

    public static final SaplingBlock pink_agate_sapling = RegistryHelper.registerBlock("pink_agate_sapling", new SaplingBlock(new PinkAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.PINK)));
    public static final SaplingBlock blue_agate_sapling = RegistryHelper.registerBlock("blue_agate_sapling", new SaplingBlock(new BlueAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.LIGHT_BLUE)));
    public static final SaplingBlock green_agate_sapling = RegistryHelper.registerBlock("green_agate_sapling", new SaplingBlock(new GreenAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.LIME)));
    public static final SaplingBlock purple_agate_sapling = RegistryHelper.registerBlock("purple_agate_sapling", new SaplingBlock(new PurpleAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.PURPLE_TERRACOTTA)));
    public static final SaplingBlock fossilized_sapling = RegistryHelper.registerBlock("fossilized_sapling", new SaplingBlock(new FossilizedTree(), GaiaBlockProperties.saplingProps(MaterialColor.YELLOW_TERRACOTTA)));
    public static final SaplingBlock corrupted_sapling = RegistryHelper.registerBlock("corrupted_sapling", new SaplingBlock(new GoldstoneCorruptTree(), GaiaBlockProperties.saplingProps(MaterialColor.BLACK_TERRACOTTA)));
    public static final SaplingBlock burnt_sapling = RegistryHelper.registerBlock("burnt_sapling", new SaplingBlock(new BurntAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.BLACK)));
    public static final SaplingBlock burning_sapling = RegistryHelper.registerBlock("burning_sapling", new SaplingBlock(new FieryAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.ORANGE_TERRACOTTA)), 100);
    public static final SaplingBlock aura_sapling = RegistryHelper.registerBlock("aura_sapling", new SaplingBlock(new AuraTree(), GaiaBlockProperties.saplingProps(MaterialColor.SNOW)));
    public static final Block pink_agate_leaves = RegistryHelper.registerBlock("pink_agate_leaves", new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.MAGENTA)));
    public static final Block blue_agate_leaves = RegistryHelper.registerBlock("blue_agate_leaves", new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.BLUE)));
    public static final Block green_agate_leaves = RegistryHelper.registerBlock("green_agate_leaves", new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.GREEN)));
    public static final Block purple_agate_leaves = RegistryHelper.registerBlock("purple_agate_leaves", new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.PURPLE_TERRACOTTA)));
    public static final Block fossilized_leaves = RegistryHelper.registerBlock("fossilized_leaves", new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.YELLOW)));
    public static final Block corrupted_leaves = RegistryHelper.registerBlock("corrupted_leaves", new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.TNT)));
    public static final Block burnt_leaves = RegistryHelper.registerBlock("burnt_leaves", new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.GRAY)));
    public static final Block burning_leaves = RegistryHelper.registerBlock("burning_leaves", new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.ORANGE_TERRACOTTA).setLightLevel((state) -> 3)), 200);
    public static final Block aura_leaves = RegistryHelper.registerBlock("aura_leaves", new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.IRON)));
    public static final RotatedPillarBlock pink_agate_log = RegistryHelper.registerBlock("pink_agate_log", new AgateLogBlock(() -> s_pink_agate_log, GaiaBlockProperties.logProps(MaterialColor.MAGENTA, MaterialColor.PINK_TERRACOTTA)));
    public static final RotatedPillarBlock blue_agate_log = RegistryHelper.registerBlock("blue_agate_log", new AgateLogBlock(() -> s_blue_agate_log, GaiaBlockProperties.logProps(MaterialColor.BLUE, MaterialColor.BLUE_TERRACOTTA)));
    public static final RotatedPillarBlock green_agate_log = RegistryHelper.registerBlock("green_agate_log", new AgateLogBlock(() -> s_green_agate_log, GaiaBlockProperties.logProps(MaterialColor.GREEN, MaterialColor.LIME_TERRACOTTA)));
    public static final RotatedPillarBlock purple_agate_log = RegistryHelper.registerBlock("purple_agate_log", new AgateLogBlock(() -> s_purple_agate_log, GaiaBlockProperties.logProps(MaterialColor.PURPLE_TERRACOTTA, MaterialColor.PURPLE)));
    public static final RotatedPillarBlock fossilized_log = RegistryHelper.registerBlock("fossilized_log", new AgateLogBlock(() -> s_fossilized_log, GaiaBlockProperties.logProps(MaterialColor.YELLOW, MaterialColor.DIRT)));
    public static final RotatedPillarBlock corrupted_log = RegistryHelper.registerBlock("corrupted_log", new AgateLogBlock(() -> s_corrupted_log, GaiaBlockProperties.logProps(MaterialColor.TNT, MaterialColor.GRAY_TERRACOTTA)));
    public static final RotatedPillarBlock burnt_log = RegistryHelper.registerBlock("burnt_log", new AgateLogBlock(() -> s_burnt_log, GaiaBlockProperties.logProps(MaterialColor.GRAY, MaterialColor.BLACK_TERRACOTTA)));
    public static final RotatedPillarBlock burning_log = RegistryHelper.registerBlock("burning_log", new AgateLogBlock(() -> s_burning_log, GaiaBlockProperties.logProps(MaterialColor.ADOBE, MaterialColor.ORANGE_TERRACOTTA).setLightLevel((state) -> 3)), 1600);
    public static final RotatedPillarBlock aura_log = RegistryHelper.registerBlock("aura_log", new AgateLogBlock(() -> s_aura_log, GaiaBlockProperties.logProps(MaterialColor.IRON, MaterialColor.GRAY)));
    public static final RotatedPillarBlock stripped_pink_agate_log = RegistryHelper.registerBlock("stripped_pink_agate_log", s_pink_agate_log);
    public static final RotatedPillarBlock stripped_blue_agate_log = RegistryHelper.registerBlock("stripped_blue_agate_log", s_blue_agate_log);
    public static final RotatedPillarBlock stripped_green_agate_log = RegistryHelper.registerBlock("stripped_green_agate_log", s_green_agate_log);
    public static final RotatedPillarBlock stripped_purple_agate_log = RegistryHelper.registerBlock("stripped_purple_agate_log", s_purple_agate_log);
    public static final RotatedPillarBlock stripped_fossilized_log = RegistryHelper.registerBlock("stripped_fossilized_log", s_fossilized_log);
    public static final RotatedPillarBlock stripped_corrupted_log = RegistryHelper.registerBlock("stripped_corrupted_log", s_corrupted_log);
    public static final RotatedPillarBlock stripped_burnt_log = RegistryHelper.registerBlock("stripped_burnt_log", s_burnt_log);
    public static final RotatedPillarBlock stripped_burning_log = RegistryHelper.registerBlock("stripped_burning_log", s_burning_log, 1600);
    public static final RotatedPillarBlock stripped_aura_log = RegistryHelper.registerBlock("stripped_aura_log", s_aura_log);
    public static final RotatedPillarBlock pink_agate_wood = RegistryHelper.registerBlock("pink_agate_wood", new AgateLogBlock(() -> s_pink_agate_wood, GaiaBlockProperties.logProps(MaterialColor.PINK_TERRACOTTA)));
    public static final RotatedPillarBlock blue_agate_wood = RegistryHelper.registerBlock("blue_agate_wood", new AgateLogBlock(() -> s_blue_agate_wood, GaiaBlockProperties.logProps(MaterialColor.BLUE_TERRACOTTA)));
    public static final RotatedPillarBlock green_agate_wood = RegistryHelper.registerBlock("green_agate_wood", new AgateLogBlock(() -> s_green_agate_wood, GaiaBlockProperties.logProps(MaterialColor.LIME_TERRACOTTA)));
    public static final RotatedPillarBlock purple_agate_wood = RegistryHelper.registerBlock("purple_agate_wood", new AgateLogBlock(() -> s_purple_agate_wood, GaiaBlockProperties.logProps(MaterialColor.PURPLE)));
    public static final RotatedPillarBlock fossilized_wood = RegistryHelper.registerBlock("fossilized_wood", new AgateLogBlock(() -> s_fossilized_wood, GaiaBlockProperties.logProps(MaterialColor.DIRT)));
    public static final RotatedPillarBlock corrupted_wood = RegistryHelper.registerBlock("corrupted_wood", new AgateLogBlock(() -> s_corrupted_wood, GaiaBlockProperties.logProps(MaterialColor.GRAY_TERRACOTTA)));
    public static final RotatedPillarBlock burnt_wood = RegistryHelper.registerBlock("burnt_wood", new AgateLogBlock(() -> s_burnt_wood, GaiaBlockProperties.logProps(MaterialColor.BLACK_TERRACOTTA)));
    public static final RotatedPillarBlock burning_wood = RegistryHelper.registerBlock("burning_wood", new AgateLogBlock(() -> s_burning_wood, GaiaBlockProperties.logProps(MaterialColor.ORANGE_TERRACOTTA).setLightLevel((state) -> 3)), 1600);
    public static final RotatedPillarBlock aura_wood = RegistryHelper.registerBlock("aura_wood", new AgateLogBlock(() -> s_aura_wood, GaiaBlockProperties.logProps(MaterialColor.GRAY)));
    public static final RotatedPillarBlock stripped_pink_agate_wood = RegistryHelper.registerBlock("stripped_pink_agate_wood", s_pink_agate_wood);
    public static final RotatedPillarBlock stripped_blue_agate_wood = RegistryHelper.registerBlock("stripped_blue_agate_wood", s_blue_agate_wood);
    public static final RotatedPillarBlock stripped_green_agate_wood = RegistryHelper.registerBlock("stripped_green_agate_wood", s_green_agate_wood);
    public static final RotatedPillarBlock stripped_purple_agate_wood = RegistryHelper.registerBlock("stripped_purple_agate_wood", s_purple_agate_wood);
    public static final RotatedPillarBlock stripped_fossilized_wood = RegistryHelper.registerBlock("stripped_fossilized_wood", s_fossilized_wood);
    public static final RotatedPillarBlock stripped_corrupted_wood = RegistryHelper.registerBlock("stripped_corrupted_wood", s_corrupted_wood);
    public static final RotatedPillarBlock stripped_burnt_wood = RegistryHelper.registerBlock("stripped_burnt_wood", s_burnt_wood);
    public static final RotatedPillarBlock stripped_burning_wood = RegistryHelper.registerBlock("stripped_burning_wood", s_burning_wood, 1600);
    public static final RotatedPillarBlock stripped_aura_wood = RegistryHelper.registerBlock("stripped_aura_wood", s_aura_wood);

    public static final Block salt = RegistryHelper.registerBlock("salt", new GaiaFallingBlock(GaiaBlockProperties.sandProps(MaterialColor.SNOW, 0.9F, SoundType.SAND), 0xE0E0FF));
    public static final Block saltstone = registerPlain("saltstone", GaiaBlockProperties.stoneToolProps(MaterialColor.LIGHT_BLUE_TERRACOTTA, 1.5F, 10.0F, PICKAXE, 0));
    public static final Block pebbles = RegistryHelper.registerBlock("pebbles", new GaiaFallingBlock(GaiaBlockProperties.sandProps(MaterialColor.GRAY, 1.3F, SoundType.GROUND), 0x663366));
    public static final Block gaia_stone = registerPlain("gaia_stone", GaiaBlockProperties.stoneToolProps(MaterialColor.MAGENTA, 2.0F, 15.0F, PICKAXE, 1));
    public static final Block gaia_cobblestone = registerPlain("gaia_cobblestone", GaiaBlockProperties.stoneToolProps(MaterialColor.MAGENTA, 2.0F, 15.0F, PICKAXE, 1));
    public static final Block wasteland_stone = registerPlain("wasteland_stone", GaiaBlockProperties.stoneToolProps(MaterialColor.BLUE_TERRACOTTA, 15.0F, 200.0F, PICKAXE, 2));
    public static final Block static_stone = RegistryHelper.registerBlock("static_stone", new StaticStoneBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.BLUE_TERRACOTTA, 50.0F, 200.0F, ToolType.PICKAXE, 2)));
    public static final Block charged_mineral = RegistryHelper.registerBlock("charged_mineral", new ChargedMineralBlock(Properties.create(Material.IRON, MaterialColor.CYAN).hardnessAndResistance(4.0F, 15.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.GLASS).notSolid()));
    public static final Block volcanic_rock = registerPlain("volcanic_rock", GaiaBlockProperties.stoneToolProps(MaterialColor.GRAY_TERRACOTTA, 15.0F, 200.0F, PICKAXE, 2));
    public static final Block searing_rock = RegistryHelper.registerBlock("searing_rock", new SearingRockBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.GRAY_TERRACOTTA, 20.0F, 600.0F, ToolType.PICKAXE, 2).setLightLevel((state) -> 7)));
    public static final Block primal_mass = registerPlain("primal_mass", GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE_TERRACOTTA, 30.0F, 400.0F, PICKAXE, 2));
    public static final Block impure_rock = registerPlain("impure_rock", GaiaBlockProperties.stoneToolProps(MaterialColor.GRAY, 20.0F, 300.0F, PICKAXE, 2));
    public static final Block active_rock = RegistryHelper.registerBlock("active_rock", new ActiveRockBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE_TERRACOTTA, 15.0F, 250.0F, ToolType.PICKAXE, 2).setLightLevel((state) -> 7)));
    public static final Block impure_sludge = RegistryHelper.registerBlock("impure_sludge", new ImpureSludgeBlock(GaiaBlockProperties.sludgeProps()));
    public static final Block geyser_block = RegistryHelper.registerBlock("geyser_block", new GeyserBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.IRON, 5.0F, 10.0F, ToolType.PICKAXE, 1)));
    public static final Block sparkling_rock = registerPlain("sparkling_rock", Properties.create(Material.ROCK, MaterialColor.IRON).hardnessAndResistance(10.0F, 150.0F).sound(SoundType.GLASS).setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(1));
    public static final Block aura_shoot = RegistryHelper.registerBlock("aura_shoot", new AuraShootBlock(Properties.create(Material.GLASS, MaterialColor.BLUE).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE).harvestLevel(1).tickRandomly()));

    //Planks
    public static final Block pink_agate_planks = registerPlain("pink_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.PINK));
    public static final Block blue_agate_planks = registerPlain("blue_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.LIGHT_BLUE));
    public static final Block green_agate_planks = registerPlain("green_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.LIME));
    public static final Block purple_agate_planks = registerPlain("purple_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.PURPLE_TERRACOTTA));
    public static final Block fossilized_planks = registerPlain("fossilized_planks", GaiaBlockProperties.tileProps(MaterialColor.YELLOW_TERRACOTTA));
    public static final Block corrupted_planks = registerPlain("corrupted_planks", GaiaBlockProperties.tileProps(MaterialColor.BLACK_TERRACOTTA));
    public static final Block burnt_planks = registerPlain("burnt_planks", GaiaBlockProperties.tileProps(MaterialColor.BLACK));
    public static final Block burning_planks = registerPlain("burning_planks", GaiaBlockProperties.tileProps(MaterialColor.ORANGE_TERRACOTTA).setLightLevel((state) -> 3), 400);
    public static final Block aura_planks = registerPlain("aura_planks", GaiaBlockProperties.tileProps(MaterialColor.SNOW));
    public static final SlabBlock pink_agate_plank_slab = RegistryHelper.registerBlock("pink_agate_plank_slab", new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.PINK)));
    public static final SlabBlock blue_agate_plank_slab = RegistryHelper.registerBlock("blue_agate_plank_slab", new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.LIGHT_BLUE)));
    public static final SlabBlock green_agate_plank_slab = RegistryHelper.registerBlock("green_agate_plank_slab", new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.LIME)));
    public static final SlabBlock purple_agate_plank_slab = RegistryHelper.registerBlock("purple_agate_plank_slab", new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.PURPLE_TERRACOTTA)));
    public static final SlabBlock fossilized_plank_slab = RegistryHelper.registerBlock("fossilized_plank_slab", new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.YELLOW_TERRACOTTA)));
    public static final SlabBlock corrupted_plank_slab = RegistryHelper.registerBlock("corrupted_plank_slab", new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.BLACK_TERRACOTTA)));
    public static final SlabBlock burnt_plank_slab = RegistryHelper.registerBlock("burnt_plank_slab", new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.BLACK)));
    public static final SlabBlock burning_plank_slab = RegistryHelper.registerBlock("burning_plank_slab", new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.ORANGE_TERRACOTTA).setLightLevel((state) -> 3)), 200);
    public static final SlabBlock aura_plank_slab = RegistryHelper.registerBlock("aura_plank_slab", new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.SNOW)));
    public static final StairsBlock pink_agate_plank_stairs = registerStairs("pink_agate_plank_stairs", pink_agate_planks);
    public static final StairsBlock blue_agate_plank_stairs = registerStairs("blue_agate_plank_stairs", blue_agate_planks);
    public static final StairsBlock green_agate_plank_stairs = registerStairs("green_agate_plank_stairs", green_agate_planks);
    public static final StairsBlock purple_agate_plank_stairs = registerStairs("purple_agate_plank_stairs", purple_agate_planks);
    public static final StairsBlock fossilized_plank_stairs = registerStairs("fossilized_plank_stairs", fossilized_planks);
    public static final StairsBlock corrupted_plank_stairs = registerStairs("corrupted_plank_stairs", corrupted_planks);
    public static final StairsBlock burnt_plank_stairs = registerStairs("burnt_plank_stairs", burnt_planks);
    public static final StairsBlock burning_plank_stairs = RegistryHelper.registerBlock("burning_plank_stairs", new GaiaStairsBlock(() -> burning_planks), 300);
    public static final StairsBlock aura_plank_stairs = registerStairs("aura_plank_stairs", aura_planks);

    //Manufactured
    public static final Block cloudy_glass = RegistryHelper.registerBlock("cloudy_glass", new GaiaGlassBlock(GaiaBlockProperties.glassProps(MaterialColor.YELLOW, 0.7F)));
    public static final Block foggy_glass = RegistryHelper.registerBlock("foggy_glass", new GaiaGlassBlock(GaiaBlockProperties.glassProps(MaterialColor.LIGHT_BLUE, 0.7F)));
    public static final Block gaia_stone_bricks = registerPlain("gaia_stone_bricks", GaiaBlockProperties.gaiaBrickProps());
    public static final Block cracked_gaia_stone_bricks = registerPlain("cracked_gaia_stone_bricks", GaiaBlockProperties.gaiaBrickProps());
    public static final Block crusted_gaia_stone_bricks = registerPlain("crusted_gaia_stone_bricks", GaiaBlockProperties.gaiaBrickProps());

    public static final Block raw_jade = registerPlain("raw_jade", GaiaBlockProperties.stoneToolProps(MaterialColor.GREEN, 2.0F, 20.0F, PICKAXE, 1));
    public static final Block jade_bricks = registerPlain("jade_bricks", GaiaBlockProperties.jadeProps());
    public static final SlabBlock jade_brick_slab = RegistryHelper.registerBlock("jade_brick_slab", new SlabBlock(GaiaBlockProperties.jadeProps()));
    public static final StairsBlock jade_brick_stairs = registerStairs("jade_brick_stairs", jade_bricks);
    public static final Block cracked_jade_bricks = registerPlain("cracked_jade_bricks", GaiaBlockProperties.jadeProps());
    public static final SlabBlock cracked_jade_brick_slab = RegistryHelper.registerBlock("cracked_jade_brick_slab", new SlabBlock(GaiaBlockProperties.jadeProps()));
    public static final StairsBlock cracked_jade_brick_stairs = registerStairs("cracked_jade_brick_stairs", cracked_jade_bricks);
    public static final Block crusted_jade_bricks = registerPlain("crusted_jade_bricks", GaiaBlockProperties.jadeProps());
    public static final SlabBlock crusted_jade_brick_slab = RegistryHelper.registerBlock("crusted_jade_brick_slab", new SlabBlock(GaiaBlockProperties.jadeProps()));
    public static final StairsBlock crusted_jade_brick_stairs = registerStairs("crusted_jade_brick_stairs", crusted_jade_bricks);
    public static final Block raw_copal = registerPlain("raw_copal", GaiaBlockProperties.stoneToolProps(MaterialColor.GOLD, 2.0F, 20.0F, PICKAXE, 1));
    public static final Block copal_bricks = registerPlain("copal_bricks", GaiaBlockProperties.copalProps());
    public static final SlabBlock copal_brick_slab = RegistryHelper.registerBlock("copal_brick_slab", new SlabBlock(GaiaBlockProperties.copalProps()));
    public static final StairsBlock copal_brick_stairs = registerStairs("copal_brick_stairs", copal_bricks);
    public static final Block cracked_copal_bricks = registerPlain("cracked_copal_bricks", GaiaBlockProperties.copalProps());
    public static final SlabBlock cracked_copal_brick_slab = RegistryHelper.registerBlock("cracked_copal_brick_slab", new SlabBlock(GaiaBlockProperties.copalProps()));
    public static final StairsBlock cracked_copal_brick_stairs = registerStairs("cracked_copal_brick_stairs", cracked_copal_bricks);
    public static final Block crusted_copal_bricks = registerPlain("crusted_copal_bricks", GaiaBlockProperties.copalProps());
    public static final SlabBlock crusted_copal_brick_slab = RegistryHelper.registerBlock("crusted_copal_brick_slab", new SlabBlock(GaiaBlockProperties.copalProps()));
    public static final StairsBlock crusted_copal_brick_stairs = registerStairs("crusted_copal_brick_stairs", crusted_copal_bricks);
    public static final Block raw_jet = registerPlain("raw_jet", GaiaBlockProperties.stoneToolProps(MaterialColor.GRAY, 2.0F, 20.0F, PICKAXE, 1));
    public static final Block jet_bricks = registerPlain("jet_bricks", GaiaBlockProperties.jetProps());
    public static final SlabBlock jet_brick_slab = RegistryHelper.registerBlock("jet_brick_slab", new SlabBlock(GaiaBlockProperties.jetProps()));
    public static final StairsBlock jet_brick_stairs = registerStairs("jet_brick_stairs", jet_bricks);
    public static final Block cracked_jet_bricks = registerPlain("cracked_jet_bricks", GaiaBlockProperties.jetProps());
    public static final SlabBlock cracked_jet_brick_slab = RegistryHelper.registerBlock("cracked_jet_brick_slab", new SlabBlock(GaiaBlockProperties.jetProps()));
    public static final StairsBlock cracked_jet_brick_stairs = registerStairs("cracked_jet_brick_stairs", cracked_jet_bricks);
    public static final Block crusted_jet_bricks = registerPlain("crusted_jet_bricks", GaiaBlockProperties.jetProps());
    public static final SlabBlock crusted_jet_brick_slab = RegistryHelper.registerBlock("crusted_jet_brick_slab", new SlabBlock(GaiaBlockProperties.jetProps()));
    public static final StairsBlock crusted_jet_brick_stairs = registerStairs("crusted_jet_brick_stairs", crusted_jet_bricks);
    public static final Block raw_amethyst = registerPlain("raw_amethyst", GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE_TERRACOTTA, 2.0F, 20.0F, PICKAXE, 1));
    public static final Block amethyst_bricks = registerPlain("amethyst_bricks", GaiaBlockProperties.amethystProps());
    public static final SlabBlock amethyst_brick_slab = RegistryHelper.registerBlock("amethyst_brick_slab", new SlabBlock(GaiaBlockProperties.amethystProps()));
    public static final StairsBlock amethyst_brick_stairs = registerStairs("amethyst_brick_stairs", amethyst_bricks);
    public static final Block cracked_amethyst_bricks = registerPlain("cracked_amethyst_bricks", GaiaBlockProperties.amethystProps());
    public static final SlabBlock cracked_amethyst_brick_slab = RegistryHelper.registerBlock("cracked_amethyst_brick_slab", new SlabBlock(GaiaBlockProperties.amethystProps()));
    public static final StairsBlock cracked_amethyst_brick_stairs = registerStairs("cracked_amethyst_brick_stairs", cracked_amethyst_bricks);
    public static final Block crusted_amethyst_bricks = registerPlain("crusted_amethyst_bricks", GaiaBlockProperties.amethystProps());
    public static final SlabBlock crusted_amethyst_brick_slab = RegistryHelper.registerBlock("crusted_amethyst_brick_slab", new SlabBlock(GaiaBlockProperties.amethystProps()));
    public static final StairsBlock crusted_amethyst_brick_stairs = registerStairs("crusted_amethyst_brick_stairs", crusted_amethyst_bricks);

    public static final Block reinforced_bricks = registerPlain("reinforced_bricks", GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE, 10.0F, 100.0F, PICKAXE, 1));
    public static final Block bolstered_bricks = registerPlain("bolstered_bricks", GaiaBlockProperties.stoneToolProps(MaterialColor.SAND, 30.0F, 400.0F, PICKAXE, 2));
    public static final Block malachite_bricks = registerPlain("malachite_bricks", GaiaBlockProperties.malachiteProps());
    public static final Block malachite_cracked_bricks = registerPlain("malachite_cracked_bricks", GaiaBlockProperties.malachiteProps());
    public static final Block malachite_crusted_bricks = registerPlain("malachite_crusted_bricks", GaiaBlockProperties.malachiteProps());
    public static final Block malachite_floor_tiles = registerPlain("malachite_floor_tiles", GaiaBlockProperties.malachiteProps());
    public static final Block malachite_chisel_bricks = registerPlain("malachite_chisel_bricks", GaiaBlockProperties.malachiteProps());
    public static final Block malachite_pulsing_bricks = registerPlain("malachite_pulsing_bricks", GaiaBlockProperties.malachiteProps());
    public static final Block malachite_pulsing_tiles = registerPlain("malachite_pulsing_tiles", GaiaBlockProperties.malachiteProps());
    public static final Block malachite_pulsing_chisel = registerPlain("malachite_pulsing_chisel", GaiaBlockProperties.malachiteProps());
    public static final SlabBlock malachite_brick_slab = RegistryHelper.registerBlock("malachite_brick_slab", new SlabBlock(GaiaBlockProperties.malachiteProps()));
    public static final SlabBlock malachite_cracked_brick_slab = RegistryHelper.registerBlock("malachite_cracked_brick_slab", new SlabBlock(GaiaBlockProperties.malachiteProps()));
    public static final SlabBlock malachite_crusted_brick_slab = RegistryHelper.registerBlock("malachite_crusted_brick_slab", new SlabBlock(GaiaBlockProperties.malachiteProps()));
    public static final SlabBlock malachite_floor_slab = RegistryHelper.registerBlock("malachite_floor_slab", new SlabBlock(GaiaBlockProperties.malachiteProps()));
    public static final RotatedPillarBlock malachite_pillar = RegistryHelper.registerBlock("malachite_pillar", new RotatedPillarBlock(GaiaBlockProperties.malachiteProps()));
    public static final StairsBlock malachite_brick_stairs = registerStairs("malachite_brick_stairs", malachite_bricks);
    public static final StairsBlock malachite_cracked_brick_stairs = registerStairs("malachite_cracked_brick_stairs", malachite_cracked_bricks);
    public static final StairsBlock malachite_crusted_brick_stairs = registerStairs("malachite_crusted_brick_stairs", malachite_crusted_bricks);
    public static final StairsBlock malachite_floor_stairs = registerStairs("malachite_floor_stairs", malachite_floor_tiles);
    public static final StairsBlock malachite_chisel_stairs = registerStairs("malachite_chisel_stairs", malachite_chisel_bricks);
    public static final StairsBlock malachite_pulsing_brick_stairs = registerStairs("malachite_pulsing_brick_stairs", malachite_bricks);
    public static final StairsBlock malachite_pulsing_floor_stairs = registerStairs("malachite_pulsing_floor_stairs", malachite_floor_tiles);
    public static final StairsBlock malachite_pulsing_chisel_stairs = registerStairs("malachite_pulsing_chisel_stairs", malachite_chisel_bricks);
    public static final StairsBlock malachite_pillar_stairs = registerStairs("malachite_pillar_stairs", malachite_pillar);

    //Storage Blocks
    public static final Block sugilite_block = registerPlain("sugilite_block", GaiaBlockProperties.storageProps(MaterialColor.PURPLE));
    public static final Block hematite_block = registerPlain("hematite_block", GaiaBlockProperties.storageProps(MaterialColor.GRAY));
    public static final Block cinnabar_block = registerPlain("cinnabar_block", GaiaBlockProperties.storageProps(MaterialColor.ADOBE));
    public static final Block labradorite_block = registerPlain("labradorite_block", GaiaBlockProperties.storageProps(MaterialColor.GREEN));
    public static final Block moonstone_block = registerPlain("moonstone_block", GaiaBlockProperties.storageProps(MaterialColor.IRON));
    public static final Block opal_block_red = registerPlain("opal_block_red", GaiaBlockProperties.storageProps(MaterialColor.RED));
    public static final Block opal_block_blue = registerPlain("opal_block_blue", GaiaBlockProperties.storageProps(MaterialColor.LIGHT_BLUE));
    public static final Block opal_block_green = registerPlain("opal_block_green", GaiaBlockProperties.storageProps(MaterialColor.LIME));
    public static final Block opal_block_white = registerPlain("opal_block_white", GaiaBlockProperties.storageProps(MaterialColor.SNOW));
    public static final Block pyrite_block = registerPlain("pyrite_block", GaiaBlockProperties.storageProps(MaterialColor.GOLD).setLightLevel((state) -> 15));
    public static final Block tektite_block = registerPlain("tektite_block", GaiaBlockProperties.storageProps(MaterialColor.BLACK));
    public static final Block goldstone_block = registerPlain("goldstone_block", GaiaBlockProperties.storageProps(MaterialColor.BLACK));
    public static final Block aura_block = registerPlain("aura_block", GaiaBlockProperties.storageProps(MaterialColor.ICE));
    public static final Block bismuth_block = registerPlain("bismuth_block", GaiaBlockProperties.storageProps(MaterialColor.OBSIDIAN));
    public static final Block ixiolite_block = registerPlain("ixiolite_block", GaiaBlockProperties.storageProps(MaterialColor.GRAY));
    public static final Block proustite_block = registerPlain("proustite_block", GaiaBlockProperties.storageProps(MaterialColor.MAGENTA));
    public static final Block euclase_block = registerPlain("euclase_block", GaiaBlockProperties.storageProps(MaterialColor.LIME));
    public static final Block leucite_block = registerPlain("leucite_block", GaiaBlockProperties.storageProps(MaterialColor.SAND));
    public static final Block carnelian_block = registerPlain("carnelian_block", GaiaBlockProperties.storageProps(MaterialColor.RED));
    public static final Block benitoite_block = registerPlain("benitoite_block", GaiaBlockProperties.storageProps(MaterialColor.BLUE));
    public static final Block diopside_block = registerPlain("diopside_block", GaiaBlockProperties.storageProps(MaterialColor.LIME));
    public static final Block chalcedony_block = registerPlain("chalcedony_block", GaiaBlockProperties.storageProps(MaterialColor.SNOW));

    //Ores
    public static final Block sugilite_ore = RegistryHelper.registerBlock("sugilite_ore", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.PURPLE, 1), 1, 3));
    public static final Block hematite_ore = RegistryHelper.registerBlock("hematite_ore", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.GRAY, 2), 1, 4));
    public static final Block cinnabar_ore = RegistryHelper.registerBlock("cinnabar_ore", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.ADOBE, 2), 1, 4));
    public static final Block labradorite_ore = RegistryHelper.registerBlock("labradorite_ore", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.GREEN, 2), 5, 2));
    public static final Block moonstone_ore = RegistryHelper.registerBlock("moonstone_ore", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.IRON, 2), 5, 2));
    public static final Block opal_ore_red = RegistryHelper.registerBlock("opal_ore_red", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.RED, 2), 2, 5));
    public static final Block opal_ore_blue = RegistryHelper.registerBlock("opal_ore_blue", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.LIGHT_BLUE, 2), 2, 5));
    public static final Block opal_ore_green = RegistryHelper.registerBlock("opal_ore_green", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.LIME, 2), 2, 5));
    public static final Block opal_ore_white = RegistryHelper.registerBlock("opal_ore_white", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.SNOW, 3), 3, 7));
    public static final Block pyrite_ore = RegistryHelper.registerBlock("pyrite_ore", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.GOLD, 2).setLightLevel((state) -> 3), 1, 4));
    public static final Block speckled_rock = RegistryHelper.registerBlock("speckled_rock", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.MAGENTA, 1)));
    public static final Block coarse_rock = RegistryHelper.registerBlock("coarse_rock", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.MAGENTA, 2)));
    public static final Block precious_rock = RegistryHelper.registerBlock("precious_rock", new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.MAGENTA, 3)));

    //Flower Pots
    public static final FlowerPotBlock potted_thiscus = registerFlowerPot(thiscus);
    public static final FlowerPotBlock potted_ouzium = registerFlowerPot(ouzium);
    public static final FlowerPotBlock potted_agathum = registerFlowerPot(agathum);
    public static final FlowerPotBlock potted_varloom = registerFlowerPot(varloom);
    public static final FlowerPotBlock potted_corrupted_varloom = registerFlowerPot(corrupted_varloom);
    public static final FlowerPotBlock potted_missingno_plant = registerFlowerPot(missingno_plant);
    public static final FlowerPotBlock potted_spotted_kersei = registerFlowerPot(spotted_kersei);
    public static final FlowerPotBlock potted_thorny_wiltha = registerFlowerPot(thorny_wiltha);
    public static final FlowerPotBlock potted_roofed_agaric = registerFlowerPot(roofed_agaric);
    public static final FlowerPotBlock potted_bulbous_hobina = registerFlowerPot(bulbous_hobina);
    public static final FlowerPotBlock potted_stickly_cupsir = registerFlowerPot(stickly_cupsir);
    public static final FlowerPotBlock potted_mystical_murgni = registerFlowerPot(mystical_murgni);
    public static final FlowerPotBlock potted_corrupted_gaia_eye = registerFlowerPot(corrupted_gaia_eye);
    public static final FlowerPotBlock potted_elder_imklia = registerFlowerPot(elder_imklia);
    public static final FlowerPotBlock potted_gold_orb_tucher = registerFlowerPot(gold_orb_tucher);
    public static final FlowerPotBlock potted_missingno_fungus = registerFlowerPot(missingno_fungus);
    public static final FlowerPotBlock potted_pink_agate_sapling = registerFlowerPot(pink_agate_sapling);
    public static final FlowerPotBlock potted_blue_agate_sapling = registerFlowerPot(blue_agate_sapling);
    public static final FlowerPotBlock potted_green_agate_sapling = registerFlowerPot(green_agate_sapling);
    public static final FlowerPotBlock potted_purple_agate_sapling = registerFlowerPot(purple_agate_sapling);
    public static final FlowerPotBlock potted_fossilized_sapling = registerFlowerPot(fossilized_sapling);
    public static final FlowerPotBlock potted_corrupted_sapling = registerFlowerPot(corrupted_sapling);
    public static final FlowerPotBlock potted_burnt_sapling = registerFlowerPot(burnt_sapling);
    public static final FlowerPotBlock potted_burning_sapling = registerFlowerPot(burning_sapling);
    public static final FlowerPotBlock potted_aura_sapling = registerFlowerPot(aura_sapling);

    //Spawners
    public static final BossSpawnerBlock malachite_guard_spawner = RegistryHelper.registerBlockOnly("malachite_guard_spawner", new BossSpawnerBlock(BossSpawnerBlock.BossType.MALACHITE, GaiaBlockProperties.spawnerProps()));

    private static Block registerPlain(String name, Properties props) {
        return registerPlain(name, props, 0);
    }

    private static Block registerPlain(String name, Properties props, int burn) {
        return RegistryHelper.registerBlock(name, new Block(props), burn);
    }



    private static StairsBlock registerStairs(String name, Block base) {
        return RegistryHelper.registerBlock(name, new GaiaStairsBlock(() -> base));
    }

    private static FlowerPotBlock registerFlowerPot(Block plant) {
        return RegistryHelper.registerBlock("potted_" + plant.getRegistryName().getPath(), new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, () -> plant, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));
    }

    public static void addPlants() {
        FlowerPotBlock block = (FlowerPotBlock) Blocks.FLOWER_POT;

        block.addPlant(thiscus.getRegistryName(), () -> potted_thiscus);
        block.addPlant(ouzium.getRegistryName(), () -> potted_ouzium);
        block.addPlant(agathum.getRegistryName(), () -> potted_agathum);
        block.addPlant(varloom.getRegistryName(), () -> potted_varloom);
        block.addPlant(corrupted_varloom.getRegistryName(), () -> potted_corrupted_varloom);
        block.addPlant(missingno_plant.getRegistryName(), () -> potted_missingno_plant);
        block.addPlant(spotted_kersei.getRegistryName(), () -> potted_spotted_kersei);
        block.addPlant(thorny_wiltha.getRegistryName(), () -> potted_thorny_wiltha);
        block.addPlant(roofed_agaric.getRegistryName(), () -> potted_roofed_agaric);
        block.addPlant(bulbous_hobina.getRegistryName(), () -> potted_bulbous_hobina);
        block.addPlant(stickly_cupsir.getRegistryName(), () -> potted_stickly_cupsir);
        block.addPlant(mystical_murgni.getRegistryName(), () -> potted_mystical_murgni);
        block.addPlant(corrupted_gaia_eye.getRegistryName(), () -> potted_corrupted_gaia_eye);
        block.addPlant(elder_imklia.getRegistryName(), () -> potted_elder_imklia);
        block.addPlant(gold_orb_tucher.getRegistryName(), () -> potted_gold_orb_tucher);
        block.addPlant(missingno_fungus.getRegistryName(), () -> potted_missingno_fungus);
        block.addPlant(pink_agate_sapling.getRegistryName(), () -> potted_pink_agate_sapling);
        block.addPlant(blue_agate_sapling.getRegistryName(), () -> potted_blue_agate_sapling);
        block.addPlant(green_agate_sapling.getRegistryName(), () -> potted_green_agate_sapling);
        block.addPlant(purple_agate_sapling.getRegistryName(), () -> potted_purple_agate_sapling);
        block.addPlant(fossilized_sapling.getRegistryName(), () -> potted_fossilized_sapling);
        block.addPlant(corrupted_sapling.getRegistryName(), () -> potted_corrupted_sapling);
        block.addPlant(burnt_sapling.getRegistryName(), () -> potted_burnt_sapling);
        block.addPlant(burning_sapling.getRegistryName(), () -> potted_burning_sapling);
        block.addPlant(aura_sapling.getRegistryName(), () -> potted_aura_sapling);
    }
}