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
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.block.AbstractBlock.*;
import static net.minecraftforge.common.ToolType.*;

@SuppressWarnings("unused")
public class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GaiaDimensionMod.MODID);

    //Utility Blocks
    public static final RegistryObject<GaiaPortalBlock> gaia_portal = registerNoItem("gaia_portal", () -> new GaiaPortalBlock(Properties.of(Material.PORTAL, MaterialColor.TERRACOTTA_PINK).strength(-1.0F).noCollission().randomTicks().lightLevel((state) -> 15).noDrops()));
    public static final RegistryObject<Block> keystone_block = register("keystone_block", Properties.of(Material.METAL, MaterialColor.GOLD).strength(5.0F, 10.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).harvestLevel(2));
    public static final RegistryObject<Block> gold_fire = registerNoItem("gold_fire", () -> new GoldFireBlock(Properties.of(Material.FIRE, MaterialColor.GOLD).strength(0.0F).noCollission().randomTicks().lightLevel((state) -> 15).noDrops()));
    public static final RegistryObject<Block> pyrite_torch = registerNoItem("pyrite_torch", () -> new PyriteTorchBlock(GaiaBlockProperties.torchProps()));
    public static final RegistryObject<Block> pyrite_wall_torch = registerNoItem("pyrite_wall_torch", () -> new PyriteWallTorchBlock(GaiaBlockProperties.torchProps().lootFrom(pyrite_torch)));
    public static final RegistryObject<Block> agate_crafting_table = register("agate_crafting_table", () -> new AgateCraftingTableBlock(GaiaBlockProperties.stoneProps(Material.WOOD, MaterialColor.TERRACOTTA_PINK, 1.5F, 2.0F, ToolType.AXE, 0)));
    public static final RegistryObject<Block> crude_storage_crate = register("crude_storage_crate", () -> new SmallCrateBlock(GaiaBlockProperties.stoneProps(MaterialColor.TERRACOTTA_PINK, 10.0F, 150.0F, ToolType.AXE, 0)));
    public static final RegistryObject<Block> mega_storage_crate = register("mega_storage_crate", () -> new LargeCrateBlock(GaiaBlockProperties.stoneProps(MaterialColor.TERRACOTTA_PURPLE, 10.0F, 300.0F, ToolType.AXE, 0)));
    public static final RegistryObject<Block> gaia_stone_furnace = register("gaia_stone_furnace", () -> new GaiaStoneFurnaceBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_PINK, 20.0F, 300.0F, ToolType.PICKAXE, 0).lightLevel((state) -> state.getValue(AbstractFurnaceBlock.LIT) ? 13 : 0)));
    public static final RegistryObject<Block> restructurer = register("restructurer", () -> new RestructurerBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_PURPLE, 20.0F, 300.0F, ToolType.PICKAXE, 1).lightLevel((state) -> state.getValue(RestructurerBlock.LIT) ? 14 : 0)));
    public static final RegistryObject<Block> purifier = register("purifier", () -> new PurifierBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.SAND, 20.0F, 300.0F, ToolType.PICKAXE, 2).lightLevel((state) -> state.getValue(PurifierBlock.LIT) ? 14 : 0)));

    //Fluids
    public static final RegistryObject<FlowingFluidBlock> mineral_water = registerNoItem("mineral_water", () ->
            new GaiaFluidBlock(ModFluids.mineral_water_still, Block.Properties.of(Material.WATER, MaterialColor.TERRACOTTA_LIGHT_BLUE)));
    public static final RegistryObject<FlowingFluidBlock> superhot_magma = registerNoItem("superhot_magma", () ->
            new GaiaFluidBlock(ModFluids.superhot_magma_still, Block.Properties.of(Material.LAVA, MaterialColor.COLOR_BLUE).randomTicks().lightLevel((state) -> 15)));
    public static final RegistryObject<FlowingFluidBlock> sweet_muck = registerNoItem("sweet_muck", () ->
            new GaiaFluidBlock(ModFluids.sweet_muck_still, Block.Properties.of(Material.WATER, MaterialColor.COLOR_PURPLE)));
    public static final RegistryObject<FlowingFluidBlock> liquid_bismuth = registerNoItem("liquid_bismuth", () ->
            new GaiaFluidBlock(ModFluids.liquid_bismuth_still, Block.Properties.of(Material.LAVA).randomTicks().lightLevel((state) -> 3)));
    public static final RegistryObject<FlowingFluidBlock> liquid_aura = registerNoItem("liquid_aura", () ->
            new GaiaFluidBlock(ModFluids.liquid_aura_still, Block.Properties.of(Material.WATER)));

    //Natural Blocks
    public static final RegistryObject<Block> heavy_soil = register("heavy_soil", () -> new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<Block> corrupt_soil = register("corrupt_soil", () -> new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<Block> boggy_soil = register("boggy_soil", () -> new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<Block> light_soil = register("light_soil", () -> new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.GOLD)));
    public static final RegistryObject<Block> glitter_grass = register("glitter_grass", () -> new GlitterGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.COLOR_PINK)));
    public static final RegistryObject<Block> corrupt_grass = register("corrupt_grass", () -> new CorruptGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.COLOR_BLACK)));
    public static final RegistryObject<Block> murky_grass = register("murky_grass", () -> new MurkyGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<Block> soft_grass = register("soft_grass", () -> new SoftGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.COLOR_CYAN)));
    public static final RegistryObject<Block> frail_glitter_block = register("frail_glitter_block", () -> new GaiaGlassBlock(GaiaBlockProperties.glassProps(MaterialColor.COLOR_PINK, 1.0F)));
    public static final RegistryObject<Block> thick_glitter_block = register("thick_glitter_block", GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_PURPLE, 1.5F, 7.5F, PICKAXE, 1));
    public static final RegistryObject<Block> gummy_glitter_block = register("gummy_glitter_block", () -> new SlimeBlock(Properties.of(Material.CLAY, MaterialColor.COLOR_PURPLE).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> pink_sludge_block = register("pink_sludge_block", () -> new SlimeBlock(Properties.of(Material.CLAY, MaterialColor.COLOR_PINK).sound(SoundType.SLIME_BLOCK)));

    //Plants
    public static final RegistryObject<Block> crystal_growth = register("crystal_growth", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.SNOW, true)));
    public static final RegistryObject<Block> crystal_growth_red = register("crystal_growth_red", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.COLOR_RED, true)));
    public static final RegistryObject<Block> crystal_growth_black = register("crystal_growth_black", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.COLOR_BLACK, true)));
    public static final RegistryObject<Block> crystal_growth_seared = register("crystal_growth_seared", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.COLOR_BLACK, true)));
    public static final RegistryObject<Block> crystal_growth_mutant = register("crystal_growth_mutant", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.TERRACOTTA_WHITE, true)));
    public static final RegistryObject<Block> crystal_growth_aura = register("crystal_growth_aura", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.TERRACOTTA_LIGHT_BLUE, true)));
    public static final RegistryObject<Block> thiscus = register("thiscus", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> ouzium = register("ouzium", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> agathum = register("agathum", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> varloom = register("varloom", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> corrupted_varloom = register("corrupted_varloom", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> missingno_plant = register("missingno_plant", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> spotted_kersei = register("spotted_kersei", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.COLOR_PINK, false), false));
    public static final RegistryObject<Block> thorny_wiltha = register("thorny_wiltha", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.COLOR_LIGHT_BLUE, false), false));
    public static final RegistryObject<Block> roofed_agaric = register("roofed_agaric", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.COLOR_LIGHT_GREEN, false), false));
    public static final RegistryObject<Block> bulbous_hobina = register("bulbous_hobina", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.TERRACOTTA_PINK, false), false));
    public static final RegistryObject<Block> stickly_cupsir = register("stickly_cupsir", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.TERRACOTTA_YELLOW, false), false));
    public static final RegistryObject<Block> mystical_murgni = register("mystical_murgni", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.GOLD, false), false));
    public static final RegistryObject<Block> corrupted_gaia_eye = register("corrupted_gaia_eye", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.FIRE, false), false));
    //public static final RegistryObject<Block> sacred_gaia_eye = RegistryHelper.registerBlock()("sacred_gaia_eye", new CrystalFungusBlock(false));
    public static final RegistryObject<Block> elder_imklia = register("elder_imklia", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.COLOR_PURPLE, false), true));
    public static final RegistryObject<Block> gold_orb_tucher = register("gold_orb_tucher", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.GOLD, false), true));
    public static final RegistryObject<Block> missingno_fungus = register("missingno_fungus", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.COLOR_MAGENTA, false), false));

    //Tree Blocks
    public static RotatedPillarBlock s_pink_agate_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_MAGENTA));
    public static RotatedPillarBlock s_blue_agate_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_BLUE));
    public static RotatedPillarBlock s_green_agate_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_GREEN));
    public static RotatedPillarBlock s_purple_agate_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.TERRACOTTA_PURPLE));
    public static RotatedPillarBlock s_fossilized_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_YELLOW));
    public static RotatedPillarBlock s_corrupted_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.FIRE));
    public static RotatedPillarBlock s_burnt_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_GRAY));
    public static RotatedPillarBlock s_burning_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_ORANGE).lightLevel((state) -> 3));
    public static RotatedPillarBlock s_aura_log = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.METAL));
    public static RotatedPillarBlock s_pink_agate_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_MAGENTA));
    public static RotatedPillarBlock s_blue_agate_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_BLUE));
    public static RotatedPillarBlock s_green_agate_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_GREEN));
    public static RotatedPillarBlock s_purple_agate_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.TERRACOTTA_PURPLE));
    public static RotatedPillarBlock s_fossilized_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_YELLOW));
    public static RotatedPillarBlock s_corrupted_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.FIRE));
    public static RotatedPillarBlock s_burnt_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_GRAY));
    public static RotatedPillarBlock s_burning_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.COLOR_ORANGE).lightLevel((state) -> 3));
    public static RotatedPillarBlock s_aura_wood = new AgateLogBlock(GaiaBlockProperties.logProps(MaterialColor.METAL));

    public static final RegistryObject<SaplingBlock> pink_agate_sapling = register("pink_agate_sapling", () -> new SaplingBlock(new PinkAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.COLOR_PINK)));
    public static final RegistryObject<SaplingBlock> blue_agate_sapling = register("blue_agate_sapling", () -> new SaplingBlock(new BlueAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<SaplingBlock> green_agate_sapling = register("green_agate_sapling", () -> new SaplingBlock(new GreenAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.COLOR_LIGHT_GREEN)));
    public static final RegistryObject<SaplingBlock> purple_agate_sapling = register("purple_agate_sapling", () -> new SaplingBlock(new PurpleAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<SaplingBlock> fossilized_sapling = register("fossilized_sapling", () -> new SaplingBlock(new FossilizedTree(), GaiaBlockProperties.saplingProps(MaterialColor.TERRACOTTA_YELLOW)));
    public static final RegistryObject<SaplingBlock> corrupted_sapling = register("corrupted_sapling", () -> new SaplingBlock(new GoldstoneCorruptTree(), GaiaBlockProperties.saplingProps(MaterialColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<SaplingBlock> burnt_sapling = register("burnt_sapling", () -> new SaplingBlock(new BurntAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.COLOR_BLACK)));
    public static final RegistryObject<SaplingBlock> burning_sapling = register("burning_sapling", () -> new SaplingBlock(new FieryAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.TERRACOTTA_ORANGE)), 100);
    public static final RegistryObject<SaplingBlock> aura_sapling = register("aura_sapling", () -> new SaplingBlock(new AuraTree(), GaiaBlockProperties.saplingProps(MaterialColor.SNOW)));
    public static final RegistryObject<Block> pink_agate_leaves = register("pink_agate_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.COLOR_MAGENTA)));
    public static final RegistryObject<Block> blue_agate_leaves = register("blue_agate_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.COLOR_BLUE)));
    public static final RegistryObject<Block> green_agate_leaves = register("green_agate_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.COLOR_GREEN)));
    public static final RegistryObject<Block> purple_agate_leaves = register("purple_agate_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<Block> fossilized_leaves = register("fossilized_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.COLOR_YELLOW)));
    public static final RegistryObject<Block> corrupted_leaves = register("corrupted_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.FIRE)));
    public static final RegistryObject<Block> burnt_leaves = register("burnt_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<Block> burning_leaves = register("burning_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 200);
    public static final RegistryObject<Block> aura_leaves = register("aura_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.METAL)));
    public static final RegistryObject<RotatedPillarBlock> pink_agate_log = register("pink_agate_log", () -> new AgateLogBlock(() -> s_pink_agate_log, GaiaBlockProperties.logProps(MaterialColor.COLOR_MAGENTA, MaterialColor.TERRACOTTA_PINK)));
    public static final RegistryObject<RotatedPillarBlock> blue_agate_log = register("blue_agate_log", () -> new AgateLogBlock(() -> s_blue_agate_log, GaiaBlockProperties.logProps(MaterialColor.COLOR_BLUE, MaterialColor.TERRACOTTA_BLUE)));
    public static final RegistryObject<RotatedPillarBlock> green_agate_log = register("green_agate_log", () -> new AgateLogBlock(() -> s_green_agate_log, GaiaBlockProperties.logProps(MaterialColor.COLOR_GREEN, MaterialColor.TERRACOTTA_LIGHT_GREEN)));
    public static final RegistryObject<RotatedPillarBlock> purple_agate_log = register("purple_agate_log", () -> new AgateLogBlock(() -> s_purple_agate_log, GaiaBlockProperties.logProps(MaterialColor.TERRACOTTA_PURPLE, MaterialColor.COLOR_PURPLE)));
    public static final RegistryObject<RotatedPillarBlock> fossilized_log = register("fossilized_log", () -> new AgateLogBlock(() -> s_fossilized_log, GaiaBlockProperties.logProps(MaterialColor.COLOR_YELLOW, MaterialColor.DIRT)));
    public static final RegistryObject<RotatedPillarBlock> corrupted_log = register("corrupted_log", () -> new AgateLogBlock(() -> s_corrupted_log, GaiaBlockProperties.logProps(MaterialColor.FIRE, MaterialColor.TERRACOTTA_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> burnt_log = register("burnt_log", () -> new AgateLogBlock(() -> s_burnt_log, GaiaBlockProperties.logProps(MaterialColor.COLOR_GRAY, MaterialColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<RotatedPillarBlock> burning_log = register("burning_log", () -> new AgateLogBlock(() -> s_burning_log, GaiaBlockProperties.logProps(MaterialColor.COLOR_ORANGE, MaterialColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 1600);
    public static final RegistryObject<RotatedPillarBlock> aura_log = register("aura_log", () -> new AgateLogBlock(() -> s_aura_log, GaiaBlockProperties.logProps(MaterialColor.METAL, MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> stripped_pink_agate_log = register("stripped_pink_agate_log", () -> s_pink_agate_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_blue_agate_log = register("stripped_blue_agate_log", () -> s_blue_agate_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_green_agate_log = register("stripped_green_agate_log", () -> s_green_agate_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_purple_agate_log = register("stripped_purple_agate_log", () -> s_purple_agate_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_fossilized_log = register("stripped_fossilized_log", () -> s_fossilized_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_corrupted_log = register("stripped_corrupted_log", () -> s_corrupted_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_burnt_log = register("stripped_burnt_log", () -> s_burnt_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_burning_log = register("stripped_burning_log", () -> s_burning_log, 1600);
    public static final RegistryObject<RotatedPillarBlock> stripped_aura_log = register("stripped_aura_log", () -> s_aura_log);
    public static final RegistryObject<RotatedPillarBlock> pink_agate_wood = register("pink_agate_wood", () -> new AgateLogBlock(() -> s_pink_agate_wood, GaiaBlockProperties.logProps(MaterialColor.TERRACOTTA_PINK)));
    public static final RegistryObject<RotatedPillarBlock> blue_agate_wood = register("blue_agate_wood", () -> new AgateLogBlock(() -> s_blue_agate_wood, GaiaBlockProperties.logProps(MaterialColor.TERRACOTTA_BLUE)));
    public static final RegistryObject<RotatedPillarBlock> green_agate_wood = register("green_agate_wood", () -> new AgateLogBlock(() -> s_green_agate_wood, GaiaBlockProperties.logProps(MaterialColor.TERRACOTTA_LIGHT_GREEN)));
    public static final RegistryObject<RotatedPillarBlock> purple_agate_wood = register("purple_agate_wood", () -> new AgateLogBlock(() -> s_purple_agate_wood, GaiaBlockProperties.logProps(MaterialColor.COLOR_PURPLE)));
    public static final RegistryObject<RotatedPillarBlock> fossilized_wood = register("fossilized_wood", () -> new AgateLogBlock(() -> s_fossilized_wood, GaiaBlockProperties.logProps(MaterialColor.DIRT)));
    public static final RegistryObject<RotatedPillarBlock> corrupted_wood = register("corrupted_wood", () -> new AgateLogBlock(() -> s_corrupted_wood, GaiaBlockProperties.logProps(MaterialColor.TERRACOTTA_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> burnt_wood = register("burnt_wood", () -> new AgateLogBlock(() -> s_burnt_wood, GaiaBlockProperties.logProps(MaterialColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<RotatedPillarBlock> burning_wood = register("burning_wood", () -> new AgateLogBlock(() -> s_burning_wood, GaiaBlockProperties.logProps(MaterialColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 1600);
    public static final RegistryObject<RotatedPillarBlock> aura_wood = register("aura_wood", () -> new AgateLogBlock(() -> s_aura_wood, GaiaBlockProperties.logProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> stripped_pink_agate_wood = register("stripped_pink_agate_wood", () -> s_pink_agate_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_blue_agate_wood = register("stripped_blue_agate_wood", () -> s_blue_agate_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_green_agate_wood = register("stripped_green_agate_wood", () -> s_green_agate_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_purple_agate_wood = register("stripped_purple_agate_wood", () -> s_purple_agate_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_fossilized_wood = register("stripped_fossilized_wood", () -> s_fossilized_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_corrupted_wood = register("stripped_corrupted_wood", () -> s_corrupted_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_burnt_wood = register("stripped_burnt_wood", () -> s_burnt_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_burning_wood = register("stripped_burning_wood", () -> s_burning_wood, 1600);
    public static final RegistryObject<RotatedPillarBlock> stripped_aura_wood = register("stripped_aura_wood", () -> s_aura_wood);

    public static final RegistryObject<Block> salt = register("salt", () -> new GaiaFallingBlock(GaiaBlockProperties.sandProps(MaterialColor.SNOW, 0.9F, SoundType.SAND), 0xE0E0FF));
    public static final RegistryObject<Block> saltstone = register("saltstone", GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_LIGHT_BLUE, 1.5F, 10.0F, PICKAXE, 0));
    public static final RegistryObject<Block> pebbles = register("pebbles", () -> new GaiaFallingBlock(GaiaBlockProperties.sandProps(MaterialColor.COLOR_GRAY, 1.3F, SoundType.GRAVEL), 0x663366));
    public static final RegistryObject<Block> gaia_stone = register("gaia_stone", GaiaBlockProperties.stoneToolProps(MaterialColor.COLOR_MAGENTA, 2.0F, 15.0F, PICKAXE, 1));
    public static final RegistryObject<Block> gaia_cobblestone = register("gaia_cobblestone", GaiaBlockProperties.stoneToolProps(MaterialColor.COLOR_MAGENTA, 2.0F, 15.0F, PICKAXE, 1));
    public static final RegistryObject<Block> wasteland_stone = register("wasteland_stone", GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_BLUE, 15.0F, 200.0F, PICKAXE, 2));
    public static final RegistryObject<Block> static_stone = register("static_stone", () -> new StaticStoneBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_BLUE, 50.0F, 200.0F, ToolType.PICKAXE, 2)));
    public static final RegistryObject<Block> charged_mineral = register("charged_mineral", () -> new ChargedMineralBlock(Properties.of(Material.METAL, MaterialColor.COLOR_CYAN).strength(4.0F, 15.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> volcanic_rock = register("volcanic_rock", GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_GRAY, 15.0F, 200.0F, PICKAXE, 2));
    public static final RegistryObject<Block> searing_rock = register("searing_rock", () -> new SearingRockBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_GRAY, 20.0F, 600.0F, ToolType.PICKAXE, 2).lightLevel((state) -> 7)));
    public static final RegistryObject<Block> primal_mass = register("primal_mass", GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_PURPLE, 30.0F, 400.0F, PICKAXE, 2));
    public static final RegistryObject<Block> impure_rock = register("impure_rock", GaiaBlockProperties.stoneToolProps(MaterialColor.COLOR_GRAY, 20.0F, 300.0F, PICKAXE, 2));
    public static final RegistryObject<Block> active_rock = register("active_rock", () -> new ActiveRockBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_PURPLE, 15.0F, 250.0F, ToolType.PICKAXE, 2).lightLevel((state) -> 7)));
    public static final RegistryObject<Block> impure_sludge = register("impure_sludge", () -> new ImpureSludgeBlock(GaiaBlockProperties.sludgeProps()));
    public static final RegistryObject<Block> geyser_block = register("geyser_block", () -> new GeyserBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.METAL, 5.0F, 10.0F, ToolType.PICKAXE, 1)));
    public static final RegistryObject<Block> sparkling_rock = register("sparkling_rock", Properties.of(Material.STONE, MaterialColor.METAL).strength(10.0F, 150.0F).sound(SoundType.GLASS).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).harvestLevel(1));
    public static final RegistryObject<Block> aura_shoot = register("aura_shoot", () -> new AuraShootBlock(Properties.of(Material.GLASS, MaterialColor.COLOR_BLUE).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE).harvestLevel(1).randomTicks()));

    //Planks
    public static final RegistryObject<Block> pink_agate_planks = register("pink_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.COLOR_PINK));
    public static final RegistryObject<Block> blue_agate_planks = register("blue_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.COLOR_LIGHT_BLUE));
    public static final RegistryObject<Block> green_agate_planks = register("green_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> purple_agate_planks = register("purple_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.TERRACOTTA_PURPLE));
    public static final RegistryObject<Block> fossilized_planks = register("fossilized_planks", GaiaBlockProperties.tileProps(MaterialColor.TERRACOTTA_YELLOW));
    public static final RegistryObject<Block> corrupted_planks = register("corrupted_planks", GaiaBlockProperties.tileProps(MaterialColor.TERRACOTTA_BLACK));
    public static final RegistryObject<Block> burnt_planks = register("burnt_planks", GaiaBlockProperties.tileProps(MaterialColor.COLOR_BLACK));
    public static final RegistryObject<Block> burning_planks = register("burning_planks", GaiaBlockProperties.tileProps(MaterialColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3), 400);
    public static final RegistryObject<Block> aura_planks = register("aura_planks", GaiaBlockProperties.tileProps(MaterialColor.SNOW));
    public static final RegistryObject<SlabBlock> pink_agate_plank_slab = register("pink_agate_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.COLOR_PINK)));
    public static final RegistryObject<SlabBlock> blue_agate_plank_slab = register("blue_agate_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<SlabBlock> green_agate_plank_slab = register("green_agate_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.COLOR_LIGHT_GREEN)));
    public static final RegistryObject<SlabBlock> purple_agate_plank_slab = register("purple_agate_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<SlabBlock> fossilized_plank_slab = register("fossilized_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.TERRACOTTA_YELLOW)));
    public static final RegistryObject<SlabBlock> corrupted_plank_slab = register("corrupted_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<SlabBlock> burnt_plank_slab = register("burnt_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.COLOR_BLACK)));
    public static final RegistryObject<SlabBlock> burning_plank_slab = register("burning_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 200);
    public static final RegistryObject<SlabBlock> aura_plank_slab = register("aura_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.SNOW)));
    public static final RegistryObject<StairsBlock> pink_agate_plank_stairs = register("pink_agate_plank_stairs", makeStairs(pink_agate_planks), 0);
    public static final RegistryObject<StairsBlock> blue_agate_plank_stairs = register("blue_agate_plank_stairs", makeStairs(blue_agate_planks), 0);
    public static final RegistryObject<StairsBlock> green_agate_plank_stairs = register("green_agate_plank_stairs", makeStairs(green_agate_planks), 0);
    public static final RegistryObject<StairsBlock> purple_agate_plank_stairs = register("purple_agate_plank_stairs", makeStairs(purple_agate_planks), 0);
    public static final RegistryObject<StairsBlock> fossilized_plank_stairs = register("fossilized_plank_stairs", makeStairs(fossilized_planks), 0);
    public static final RegistryObject<StairsBlock> corrupted_plank_stairs = register("corrupted_plank_stairs", makeStairs(corrupted_planks), 0);
    public static final RegistryObject<StairsBlock> burnt_plank_stairs = register("burnt_plank_stairs", makeStairs(burnt_planks), 0);
    public static final RegistryObject<StairsBlock> burning_plank_stairs = register("burning_plank_stairs", makeStairs(burning_planks), 300);
    public static final RegistryObject<StairsBlock> aura_plank_stairs = register("aura_plank_stairs", makeStairs(aura_planks), 0);

    //Manufactured
    public static final RegistryObject<Block> cloudy_glass = register("cloudy_glass", () -> new GaiaGlassBlock(GaiaBlockProperties.glassProps(MaterialColor.COLOR_YELLOW, 0.7F)));
    public static final RegistryObject<Block> foggy_glass = register("foggy_glass", () -> new GaiaGlassBlock(GaiaBlockProperties.glassProps(MaterialColor.COLOR_LIGHT_BLUE, 0.7F)));
    public static final RegistryObject<Block> gaia_stone_bricks = register("gaia_stone_bricks", GaiaBlockProperties.gaiaBrickProps());
    public static final RegistryObject<Block> cracked_gaia_stone_bricks = register("cracked_gaia_stone_bricks", GaiaBlockProperties.gaiaBrickProps());
    public static final RegistryObject<Block> crusted_gaia_stone_bricks = register("crusted_gaia_stone_bricks", GaiaBlockProperties.gaiaBrickProps());

    public static final RegistryObject<Block> raw_jade = register("raw_jade", GaiaBlockProperties.stoneToolProps(MaterialColor.COLOR_GREEN, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> jade_bricks = register("jade_bricks", GaiaBlockProperties.jadeProps());
    public static final RegistryObject<SlabBlock> jade_brick_slab = register("jade_brick_slab", makeSlab(GaiaBlockProperties.jadeProps()));
    public static final RegistryObject<StairsBlock> jade_brick_stairs = register("jade_brick_stairs", makeStairs(jade_bricks));
    public static final RegistryObject<Block> cracked_jade_bricks = register("cracked_jade_bricks", GaiaBlockProperties.jadeProps());
    public static final RegistryObject<SlabBlock> cracked_jade_brick_slab = register("cracked_jade_brick_slab", makeSlab(GaiaBlockProperties.jadeProps()));
    public static final RegistryObject<StairsBlock> cracked_jade_brick_stairs = register("cracked_jade_brick_stairs", makeStairs(cracked_jade_bricks));
    public static final RegistryObject<Block> crusted_jade_bricks = register("crusted_jade_bricks", GaiaBlockProperties.jadeProps());
    public static final RegistryObject<SlabBlock> crusted_jade_brick_slab = register("crusted_jade_brick_slab", makeSlab(GaiaBlockProperties.jadeProps()));
    public static final RegistryObject<StairsBlock> crusted_jade_brick_stairs = register("crusted_jade_brick_stairs", makeStairs(crusted_jade_bricks));
    public static final RegistryObject<Block> raw_copal = register("raw_copal", GaiaBlockProperties.stoneToolProps(MaterialColor.GOLD, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> copal_bricks = register("copal_bricks", GaiaBlockProperties.copalProps());
    public static final RegistryObject<SlabBlock> copal_brick_slab = register("copal_brick_slab", makeSlab(GaiaBlockProperties.copalProps()));
    public static final RegistryObject<StairsBlock> copal_brick_stairs = register("copal_brick_stairs", makeStairs(copal_bricks));
    public static final RegistryObject<Block> cracked_copal_bricks = register("cracked_copal_bricks", GaiaBlockProperties.copalProps());
    public static final RegistryObject<SlabBlock> cracked_copal_brick_slab = register("cracked_copal_brick_slab", makeSlab(GaiaBlockProperties.copalProps()));
    public static final RegistryObject<StairsBlock> cracked_copal_brick_stairs = register("cracked_copal_brick_stairs", makeStairs(cracked_copal_bricks));
    public static final RegistryObject<Block> crusted_copal_bricks = register("crusted_copal_bricks", GaiaBlockProperties.copalProps());
    public static final RegistryObject<SlabBlock> crusted_copal_brick_slab = register("crusted_copal_brick_slab", makeSlab(GaiaBlockProperties.copalProps()));
    public static final RegistryObject<StairsBlock> crusted_copal_brick_stairs = register("crusted_copal_brick_stairs", makeStairs(crusted_copal_bricks));
    public static final RegistryObject<Block> raw_jet = register("raw_jet", GaiaBlockProperties.stoneToolProps(MaterialColor.COLOR_GRAY, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> jet_bricks = register("jet_bricks", GaiaBlockProperties.jetProps());
    public static final RegistryObject<SlabBlock> jet_brick_slab = register("jet_brick_slab", makeSlab(GaiaBlockProperties.jetProps()));
    public static final RegistryObject<StairsBlock> jet_brick_stairs = register("jet_brick_stairs", makeStairs(jet_bricks));
    public static final RegistryObject<Block> cracked_jet_bricks = register("cracked_jet_bricks", GaiaBlockProperties.jetProps());
    public static final RegistryObject<SlabBlock> cracked_jet_brick_slab = register("cracked_jet_brick_slab", makeSlab(GaiaBlockProperties.jetProps()));
    public static final RegistryObject<StairsBlock> cracked_jet_brick_stairs = register("cracked_jet_brick_stairs", makeStairs(cracked_jet_bricks));
    public static final RegistryObject<Block> crusted_jet_bricks = register("crusted_jet_bricks", GaiaBlockProperties.jetProps());
    public static final RegistryObject<SlabBlock> crusted_jet_brick_slab = register("crusted_jet_brick_slab", makeSlab(GaiaBlockProperties.jetProps()));
    public static final RegistryObject<StairsBlock> crusted_jet_brick_stairs = register("crusted_jet_brick_stairs", makeStairs(crusted_jet_bricks));
    public static final RegistryObject<Block> raw_amethyst = register("raw_amethyst", GaiaBlockProperties.stoneToolProps(MaterialColor.TERRACOTTA_PURPLE, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> amethyst_bricks = register("amethyst_bricks", GaiaBlockProperties.amethystProps());
    public static final RegistryObject<SlabBlock> amethyst_brick_slab = register("amethyst_brick_slab", makeSlab(GaiaBlockProperties.amethystProps()));
    public static final RegistryObject<StairsBlock> amethyst_brick_stairs = register("amethyst_brick_stairs", makeStairs(amethyst_bricks));
    public static final RegistryObject<Block> cracked_amethyst_bricks = register("cracked_amethyst_bricks", GaiaBlockProperties.amethystProps());
    public static final RegistryObject<SlabBlock> cracked_amethyst_brick_slab = register("cracked_amethyst_brick_slab", makeSlab(GaiaBlockProperties.amethystProps()));
    public static final RegistryObject<StairsBlock> cracked_amethyst_brick_stairs = register("cracked_amethyst_brick_stairs", makeStairs(cracked_amethyst_bricks));
    public static final RegistryObject<Block> crusted_amethyst_bricks = register("crusted_amethyst_bricks", GaiaBlockProperties.amethystProps());
    public static final RegistryObject<SlabBlock> crusted_amethyst_brick_slab = register("crusted_amethyst_brick_slab", makeSlab(GaiaBlockProperties.amethystProps()));
    public static final RegistryObject<StairsBlock> crusted_amethyst_brick_stairs = register("crusted_amethyst_brick_stairs", makeStairs(crusted_amethyst_bricks));

    public static final RegistryObject<Block> reinforced_bricks = register("reinforced_bricks", GaiaBlockProperties.stoneToolProps(MaterialColor.COLOR_PURPLE, 10.0F, 100.0F, PICKAXE, 1));
    public static final RegistryObject<Block> bolstered_bricks = register("bolstered_bricks", GaiaBlockProperties.stoneToolProps(MaterialColor.SAND, 30.0F, 400.0F, PICKAXE, 2));
    public static final RegistryObject<Block> malachite_bricks = register("malachite_bricks", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_cracked_bricks = register("malachite_cracked_bricks", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_crusted_bricks = register("malachite_crusted_bricks", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_floor_tiles = register("malachite_floor_tiles", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_chisel_bricks = register("malachite_chisel_bricks", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_pulsing_bricks = register("malachite_pulsing_bricks", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_pulsing_tiles = register("malachite_pulsing_tiles", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_pulsing_chisel = register("malachite_pulsing_chisel", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<SlabBlock> malachite_brick_slab = register("malachite_brick_slab", makeSlab(GaiaBlockProperties.malachiteProps()));
    public static final RegistryObject<SlabBlock> malachite_cracked_brick_slab = register("malachite_cracked_brick_slab", makeSlab(GaiaBlockProperties.malachiteProps()));
    public static final RegistryObject<SlabBlock> malachite_crusted_brick_slab = register("malachite_crusted_brick_slab", makeSlab(GaiaBlockProperties.malachiteProps()));
    public static final RegistryObject<SlabBlock> malachite_floor_slab = register("malachite_floor_slab", makeSlab(GaiaBlockProperties.malachiteProps()));
    public static final RegistryObject<RotatedPillarBlock> malachite_pillar = register("malachite_pillar", () -> new RotatedPillarBlock(GaiaBlockProperties.malachiteProps()));
    public static final RegistryObject<StairsBlock> malachite_brick_stairs = register("malachite_brick_stairs", makeStairs(malachite_bricks));
    public static final RegistryObject<StairsBlock> malachite_cracked_brick_stairs = register("malachite_cracked_brick_stairs", makeStairs(malachite_cracked_bricks));
    public static final RegistryObject<StairsBlock> malachite_crusted_brick_stairs = register("malachite_crusted_brick_stairs", makeStairs(malachite_crusted_bricks));
    public static final RegistryObject<StairsBlock> malachite_floor_stairs = register("malachite_floor_stairs", makeStairs(malachite_floor_tiles));
    public static final RegistryObject<StairsBlock> malachite_chisel_stairs = register("malachite_chisel_stairs", makeStairs(malachite_chisel_bricks));
    public static final RegistryObject<StairsBlock> malachite_pulsing_brick_stairs = register("malachite_pulsing_brick_stairs", makeStairs(malachite_bricks));
    public static final RegistryObject<StairsBlock> malachite_pulsing_floor_stairs = register("malachite_pulsing_floor_stairs", makeStairs(malachite_floor_tiles));
    public static final RegistryObject<StairsBlock> malachite_pulsing_chisel_stairs = register("malachite_pulsing_chisel_stairs", makeStairs(malachite_chisel_bricks));
    public static final RegistryObject<StairsBlock> malachite_pillar_stairs = register("malachite_pillar_stairs", makeStairs(malachite_pillar));

    //Storage Blocks
    public static final RegistryObject<Block> sugilite_block = register("sugilite_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_PURPLE));
    public static final RegistryObject<Block> hematite_block = register("hematite_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_GRAY));
    public static final RegistryObject<Block> cinnabar_block = register("cinnabar_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_ORANGE));
    public static final RegistryObject<Block> labradorite_block = register("labradorite_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_GREEN));
    public static final RegistryObject<Block> moonstone_block = register("moonstone_block", GaiaBlockProperties.storageProps(MaterialColor.METAL));
    public static final RegistryObject<Block> opal_block_red = register("opal_block_red", GaiaBlockProperties.storageProps(MaterialColor.COLOR_RED));
    public static final RegistryObject<Block> opal_block_blue = register("opal_block_blue", GaiaBlockProperties.storageProps(MaterialColor.COLOR_LIGHT_BLUE));
    public static final RegistryObject<Block> opal_block_green = register("opal_block_green", GaiaBlockProperties.storageProps(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> opal_block_white = register("opal_block_white", GaiaBlockProperties.storageProps(MaterialColor.SNOW));
    public static final RegistryObject<Block> pyrite_block = register("pyrite_block", GaiaBlockProperties.storageProps(MaterialColor.GOLD).lightLevel((state) -> 15));
    public static final RegistryObject<Block> tektite_block = register("tektite_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_BLACK));
    public static final RegistryObject<Block> goldstone_block = register("goldstone_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_BLACK));
    public static final RegistryObject<Block> aura_block = register("aura_block", GaiaBlockProperties.storageProps(MaterialColor.ICE));
    public static final RegistryObject<Block> bismuth_block = register("bismuth_block", GaiaBlockProperties.storageProps(MaterialColor.PODZOL));
    public static final RegistryObject<Block> ixiolite_block = register("ixiolite_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_GRAY));
    public static final RegistryObject<Block> proustite_block = register("proustite_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_MAGENTA));
    public static final RegistryObject<Block> euclase_block = register("euclase_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> leucite_block = register("leucite_block", GaiaBlockProperties.storageProps(MaterialColor.SAND));
    public static final RegistryObject<Block> carnelian_block = register("carnelian_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_RED));
    public static final RegistryObject<Block> benitoite_block = register("benitoite_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_BLUE));
    public static final RegistryObject<Block> diopside_block = register("diopside_block", GaiaBlockProperties.storageProps(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> chalcedony_block = register("chalcedony_block", GaiaBlockProperties.storageProps(MaterialColor.SNOW));

    //Ores
    public static final RegistryObject<Block> sugilite_ore = register("sugilite_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.COLOR_PURPLE, 1), 1, 3));
    public static final RegistryObject<Block> hematite_ore = register("hematite_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.COLOR_GRAY, 2), 1, 4));
    public static final RegistryObject<Block> cinnabar_ore = register("cinnabar_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.COLOR_ORANGE, 2), 1, 4));
    public static final RegistryObject<Block> labradorite_ore = register("labradorite_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.COLOR_GREEN, 2), 5, 2));
    public static final RegistryObject<Block> moonstone_ore = register("moonstone_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.METAL, 2), 5, 2));
    public static final RegistryObject<Block> opal_ore_red = register("opal_ore_red", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.COLOR_RED, 2), 2, 5));
    public static final RegistryObject<Block> opal_ore_blue = register("opal_ore_blue", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.COLOR_LIGHT_BLUE, 2), 2, 5));
    public static final RegistryObject<Block> opal_ore_green = register("opal_ore_green", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.COLOR_LIGHT_GREEN, 2), 2, 5));
    public static final RegistryObject<Block> opal_ore_white = register("opal_ore_white", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.SNOW, 3), 3, 7));
    public static final RegistryObject<Block> pyrite_ore = register("pyrite_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.GOLD, 2).lightLevel((state) -> 3), 1, 4));
    public static final RegistryObject<Block> speckled_rock = register("speckled_rock", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.COLOR_MAGENTA, 1)));
    public static final RegistryObject<Block> coarse_rock = register("coarse_rock", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.COLOR_MAGENTA, 2)));
    public static final RegistryObject<Block> precious_rock = register("precious_rock", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.COLOR_MAGENTA, 3)));

    //Flower Pots
    public static final RegistryObject<FlowerPotBlock> potted_thiscus = registerFlowerPot(thiscus);
    public static final RegistryObject<FlowerPotBlock> potted_ouzium = registerFlowerPot(ouzium);
    public static final RegistryObject<FlowerPotBlock> potted_agathum = registerFlowerPot(agathum);
    public static final RegistryObject<FlowerPotBlock> potted_varloom = registerFlowerPot(varloom);
    public static final RegistryObject<FlowerPotBlock> potted_corrupted_varloom = registerFlowerPot(corrupted_varloom);
    public static final RegistryObject<FlowerPotBlock> potted_missingno_plant = registerFlowerPot(missingno_plant);
    public static final RegistryObject<FlowerPotBlock> potted_spotted_kersei = registerFlowerPot(spotted_kersei);
    public static final RegistryObject<FlowerPotBlock> potted_thorny_wiltha = registerFlowerPot(thorny_wiltha);
    public static final RegistryObject<FlowerPotBlock> potted_roofed_agaric = registerFlowerPot(roofed_agaric);
    public static final RegistryObject<FlowerPotBlock> potted_bulbous_hobina = registerFlowerPot(bulbous_hobina);
    public static final RegistryObject<FlowerPotBlock> potted_stickly_cupsir = registerFlowerPot(stickly_cupsir);
    public static final RegistryObject<FlowerPotBlock> potted_mystical_murgni = registerFlowerPot(mystical_murgni);
    public static final RegistryObject<FlowerPotBlock> potted_corrupted_gaia_eye = registerFlowerPot(corrupted_gaia_eye);
    public static final RegistryObject<FlowerPotBlock> potted_elder_imklia = registerFlowerPot(elder_imklia);
    public static final RegistryObject<FlowerPotBlock> potted_gold_orb_tucher = registerFlowerPot(gold_orb_tucher);
    public static final RegistryObject<FlowerPotBlock> potted_missingno_fungus = registerFlowerPot(missingno_fungus);
    public static final RegistryObject<FlowerPotBlock> potted_pink_agate_sapling = registerFlowerPot(pink_agate_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_blue_agate_sapling = registerFlowerPot(blue_agate_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_green_agate_sapling = registerFlowerPot(green_agate_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_purple_agate_sapling = registerFlowerPot(purple_agate_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_fossilized_sapling = registerFlowerPot(fossilized_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_corrupted_sapling = registerFlowerPot(corrupted_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_burnt_sapling = registerFlowerPot(burnt_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_burning_sapling = registerFlowerPot(burning_sapling);
    public static final RegistryObject<FlowerPotBlock> potted_aura_sapling = registerFlowerPot(aura_sapling);

    //Spawners
    public static final RegistryObject<BossSpawnerBlock> malachite_guard_spawner = registerNoItem("malachite_guard_spawner", () -> new BossSpawnerBlock(BossSpawnerBlock.BossType.MALACHITE, GaiaBlockProperties.spawnerProps()));

    private static Supplier<GaiaStairsBlock> makeStairs(RegistryObject<? extends Block> state) {
        return () -> new GaiaStairsBlock(state);
    }

    private static Supplier<SlabBlock> makeSlab(Properties props) {
        return () -> new SlabBlock(props);
    }

    private static RegistryObject<Block> register(String name, Properties props) {
        return register(name, props, 0);
    }

    private static RegistryObject<Block> register(String name, Properties props, int burn) {
        return register(name, () -> new Block(props), burn);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> block) {
        return register(name, block, 0);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> block, int burnTime) {
        return registerBlock(name, block, burnTime, item -> registerBlockItemFuel(item, burnTime));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, int burnTime, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemFuel(final RegistryObject<T> block, int burnTime) {
        return () -> new BlockItem(block.get(), new Item.Properties().tab(GaiaItemGroups.GAIA_BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack) {
                return burnTime;
            }
        };
    }

    private static RegistryObject<FlowerPotBlock> registerFlowerPot(RegistryObject<? extends Block> plant) {
        return registerNoItem("potted_" + plant.getId().getPath(), () ->
                new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, plant, Block.Properties.of(Material.DECORATION).strength(0.0F)));
    }

    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<? extends T> block) {
        return BLOCKS.register(name, block);
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