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
    public static final RegistryObject<GaiaPortalBlock> gaia_portal = BLOCKS.register("gaia_portal", () -> new GaiaPortalBlock(Properties.create(Material.PORTAL, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(-1.0F).doesNotBlockMovement().tickRandomly().setLightLevel((state) -> 15).noDrops()));
    public static final RegistryObject<Block> keystone_block = registerPlain("keystone_block", Properties.create(Material.IRON, MaterialColor.GOLD).hardnessAndResistance(5.0F, 10.0F).sound(SoundType.METAL).setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(2));
    public static final RegistryObject<Block> gold_fire = BLOCKS.register("gold_fire", () -> new GoldFireBlock(Properties.create(Material.FIRE, MaterialColor.GOLD).hardnessAndResistance(0.0F).doesNotBlockMovement().tickRandomly().setLightLevel((state) -> 15).noDrops()));
    public static final RegistryObject<Block> pyrite_torch = BLOCKS.register("pyrite_torch", () -> new PyriteTorchBlock(GaiaBlockProperties.torchProps()));
    public static final RegistryObject<Block> pyrite_wall_torch = BLOCKS.register("pyrite_wall_torch", () -> new PyriteWallTorchBlock(GaiaBlockProperties.torchProps().lootFrom(pyrite_torch.get())));
    public static final RegistryObject<Block> agate_crafting_table = registerGeneral("agate_crafting_table", () -> new AgateCraftingTableBlock(GaiaBlockProperties.stoneProps(Material.WOOD, MaterialColor.PINK_TERRACOTTA, 1.5F, 2.0F, ToolType.AXE, 0)));
    public static final RegistryObject<Block> crude_storage_crate = registerGeneral("crude_storage_crate", () -> new SmallCrateBlock(GaiaBlockProperties.stoneProps(MaterialColor.PINK_TERRACOTTA, 10.0F, 150.0F, ToolType.AXE, 0)));
    public static final RegistryObject<Block> mega_storage_crate = registerGeneral("mega_storage_crate", () -> new LargeCrateBlock(GaiaBlockProperties.stoneProps(MaterialColor.PURPLE_TERRACOTTA, 10.0F, 300.0F, ToolType.AXE, 0)));
    public static final RegistryObject<Block> gaia_stone_furnace = registerGeneral("gaia_stone_furnace", () -> new GaiaStoneFurnaceBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.PINK_TERRACOTTA, 20.0F, 300.0F, ToolType.PICKAXE, 0).setLightLevel((state) -> state.get(AbstractFurnaceBlock.LIT) ? 13 : 0)));
    public static final RegistryObject<Block> restructurer = registerGeneral("restructurer", () -> new RestructurerBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE_TERRACOTTA, 20.0F, 300.0F, ToolType.PICKAXE, 1).setLightLevel((state) -> state.get(RestructurerBlock.LIT) ? 14 : 0)));
    public static final RegistryObject<Block> purifier = registerGeneral("purifier", () -> new PurifierBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.SAND, 20.0F, 300.0F, ToolType.PICKAXE, 2).setLightLevel((state) -> state.get(PurifierBlock.LIT) ? 14 : 0)));

    //Fluids
    public static final RegistryObject<FlowingFluidBlock> mineral_water = BLOCKS.register("mineral_water",
            () -> new GaiaFluidBlock(ModFluids.mineral_water_still, Block.Properties.create(Material.WATER, MaterialColor.LIGHT_BLUE_TERRACOTTA)));
    public static final RegistryObject<FlowingFluidBlock> superhot_magma = BLOCKS.register("superhot_magma",
            () -> new GaiaFluidBlock(ModFluids.superhot_magma_still, Block.Properties.create(Material.LAVA, MaterialColor.BLUE).tickRandomly().setLightLevel((state) -> 15)));
    public static final RegistryObject<FlowingFluidBlock> sweet_muck = BLOCKS.register("sweet_muck",
            () -> new GaiaFluidBlock(ModFluids.sweet_muck_still, Block.Properties.create(Material.WATER, MaterialColor.PURPLE)));
    public static final RegistryObject<FlowingFluidBlock> liquid_bismuth = BLOCKS.register("liquid_bismuth",
            () -> new GaiaFluidBlock(ModFluids.liquid_bismuth_still, Block.Properties.create(Material.LAVA).tickRandomly().setLightLevel((state) -> 3)));
    public static final RegistryObject<FlowingFluidBlock> liquid_aura = BLOCKS.register("liquid_aura",
            () -> new GaiaFluidBlock(ModFluids.liquid_aura_still, Block.Properties.create(Material.WATER)));

    //Natural Blocks
    public static final RegistryObject<Block> heavy_soil = registerGeneral("heavy_soil", () -> new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.PURPLE_TERRACOTTA)));
    public static final RegistryObject<Block> corrupt_soil = registerGeneral("corrupt_soil", () -> new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.GRAY)));
    public static final RegistryObject<Block> boggy_soil = registerGeneral("boggy_soil", () -> new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.GRAY)));
    public static final RegistryObject<Block> light_soil = registerGeneral("light_soil", () -> new GaiaSoilBlock(GaiaBlockProperties.soilProps(MaterialColor.GOLD)));
    public static final RegistryObject<Block> glitter_grass = registerGeneral("glitter_grass", () -> new GlitterGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.PINK)));
    public static final RegistryObject<Block> corrupt_grass = registerGeneral("corrupt_grass", () -> new CorruptGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.BLACK)));
    public static final RegistryObject<Block> murky_grass = registerGeneral("murky_grass", () -> new MurkyGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.GRAY)));
    public static final RegistryObject<Block> soft_grass = registerGeneral("soft_grass", () -> new SoftGrassBlock(GaiaBlockProperties.grassProps(MaterialColor.CYAN)));
    public static final RegistryObject<Block> frail_glitter_block = registerGeneral("frail_glitter_block", () -> new GaiaGlassBlock(GaiaBlockProperties.glassProps(MaterialColor.PINK, 1.0F)));
    public static final RegistryObject<Block> thick_glitter_block = registerPlain("thick_glitter_block", GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE_TERRACOTTA, 1.5F, 7.5F, PICKAXE, 1));
    public static final RegistryObject<Block> gummy_glitter_block = registerGeneral("gummy_glitter_block", () -> new SlimeBlock(Properties.create(Material.CLAY, MaterialColor.PURPLE).sound(SoundType.SLIME).notSolid()));
    public static final RegistryObject<Block> pink_sludge_block = registerGeneral("pink_sludge_block", () -> new SlimeBlock(Properties.create(Material.CLAY, MaterialColor.PINK).sound(SoundType.SLIME)));

    //Plants
    public static final RegistryObject<Block> crystal_growth = registerGeneral("crystal_growth", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.SNOW)));
    public static final RegistryObject<Block> crystal_growth_red = registerGeneral("crystal_growth_red", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.RED)));
    public static final RegistryObject<Block> crystal_growth_black = registerGeneral("crystal_growth_black", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.BLACK)));
    public static final RegistryObject<Block> crystal_growth_seared = registerGeneral("crystal_growth_seared", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.BLACK)));
    public static final RegistryObject<Block> crystal_growth_mutant = registerGeneral("crystal_growth_mutant", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.WHITE_TERRACOTTA)));
    public static final RegistryObject<Block> crystal_growth_aura = registerGeneral("crystal_growth_aura", () -> new CrystalGrowthBlock(GaiaBlockProperties.plantProps(MaterialColor.LIGHT_BLUE_TERRACOTTA)));
    public static final RegistryObject<Block> thiscus = registerGeneral("thiscus", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> ouzium = registerGeneral("ouzium", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> agathum = registerGeneral("agathum", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> varloom = registerGeneral("varloom", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> corrupted_varloom = registerGeneral("corrupted_varloom", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> missingno_plant = registerGeneral("missingno_plant", () -> new CrystalBloomBlock(GaiaBlockProperties.bloomProps()));
    public static final RegistryObject<Block> spotted_kersei = registerGeneral("spotted_kersei", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.PINK), false));
    public static final RegistryObject<Block> thorny_wiltha = registerGeneral("thorny_wiltha", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.LIGHT_BLUE), false));
    public static final RegistryObject<Block> roofed_agaric = registerGeneral("roofed_agaric", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.LIME), false));
    public static final RegistryObject<Block> bulbous_hobina = registerGeneral("bulbous_hobina", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.PINK_TERRACOTTA), false));
    public static final RegistryObject<Block> stickly_cupsir = registerGeneral("stickly_cupsir", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.YELLOW_TERRACOTTA), false));
    public static final RegistryObject<Block> mystical_murgni = registerGeneral("mystical_murgni", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.GOLD), false));
    public static final RegistryObject<Block> corrupted_gaia_eye = registerGeneral("corrupted_gaia_eye", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.TNT), false));
    //public static final RegistryObject<Block> sacred_gaia_eye = registerGeneral("sacred_gaia_eye", () -> new CrystalFungusBlock(false));
    public static final RegistryObject<Block> elder_imklia = registerGeneral("elder_imklia", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.PURPLE), true));
    public static final RegistryObject<Block> gold_orb_tucher = registerGeneral("gold_orb_tucher", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.GOLD), true));
    public static final RegistryObject<Block> missingno_fungus = registerGeneral("missingno_fungus", () -> new CrystalFungusBlock(GaiaBlockProperties.plantProps(MaterialColor.MAGENTA), false));

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

    public static final RegistryObject<SaplingBlock> pink_agate_sapling = registerGeneral("pink_agate_sapling", () -> new SaplingBlock(new PinkAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.PINK)));
    public static final RegistryObject<SaplingBlock> blue_agate_sapling = registerGeneral("blue_agate_sapling", () -> new SaplingBlock(new BlueAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.LIGHT_BLUE)));
    public static final RegistryObject<SaplingBlock> green_agate_sapling = registerGeneral("green_agate_sapling", () -> new SaplingBlock(new GreenAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.LIME)));
    public static final RegistryObject<SaplingBlock> purple_agate_sapling = registerGeneral("purple_agate_sapling", () -> new SaplingBlock(new PurpleAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.PURPLE_TERRACOTTA)));
    public static final RegistryObject<SaplingBlock> fossilized_sapling = registerGeneral("fossilized_sapling", () -> new SaplingBlock(new FossilizedTree(), GaiaBlockProperties.saplingProps(MaterialColor.YELLOW_TERRACOTTA)));
    public static final RegistryObject<SaplingBlock> corrupted_sapling = registerGeneral("corrupted_sapling", () -> new SaplingBlock(new GoldstoneCorruptTree(), GaiaBlockProperties.saplingProps(MaterialColor.BLACK_TERRACOTTA)));
    public static final RegistryObject<SaplingBlock> burnt_sapling = registerGeneral("burnt_sapling", () -> new SaplingBlock(new BurntAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.BLACK)));
    public static final RegistryObject<SaplingBlock> burning_sapling = registerFuel("burning_sapling", () -> new SaplingBlock(new FieryAgateTree(), GaiaBlockProperties.saplingProps(MaterialColor.ORANGE_TERRACOTTA)), 100);
    public static final RegistryObject<SaplingBlock> aura_sapling = registerGeneral("aura_sapling", () -> new SaplingBlock(new AuraTree(), GaiaBlockProperties.saplingProps(MaterialColor.SNOW)));
    public static final RegistryObject<Block> pink_agate_leaves = registerGeneral("pink_agate_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.MAGENTA)));
    public static final RegistryObject<Block> blue_agate_leaves = registerGeneral("blue_agate_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.BLUE)));
    public static final RegistryObject<Block> green_agate_leaves = registerGeneral("green_agate_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.GREEN)));
    public static final RegistryObject<Block> purple_agate_leaves = registerGeneral("purple_agate_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.PURPLE_TERRACOTTA)));
    public static final RegistryObject<Block> fossilized_leaves = registerGeneral("fossilized_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.YELLOW)));
    public static final RegistryObject<Block> corrupted_leaves = registerGeneral("corrupted_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.TNT)));
    public static final RegistryObject<Block> burnt_leaves = registerGeneral("burnt_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.GRAY)));
    public static final RegistryObject<Block> burning_leaves = registerFuel("burning_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.ORANGE_TERRACOTTA).setLightLevel((state) -> 3)), 200);
    public static final RegistryObject<Block> aura_leaves = registerGeneral("aura_leaves", () -> new LeavesBlock(GaiaBlockProperties.leavesProps(MaterialColor.IRON)));
    public static final RegistryObject<RotatedPillarBlock> pink_agate_log = registerGeneral("pink_agate_log", () -> new AgateLogBlock(() -> s_pink_agate_log, GaiaBlockProperties.logProps(MaterialColor.MAGENTA, MaterialColor.PINK_TERRACOTTA)));
    public static final RegistryObject<RotatedPillarBlock> blue_agate_log = registerGeneral("blue_agate_log", () -> new AgateLogBlock(() -> s_blue_agate_log, GaiaBlockProperties.logProps(MaterialColor.BLUE, MaterialColor.BLUE_TERRACOTTA)));
    public static final RegistryObject<RotatedPillarBlock> green_agate_log = registerGeneral("green_agate_log", () -> new AgateLogBlock(() -> s_green_agate_log, GaiaBlockProperties.logProps(MaterialColor.GREEN, MaterialColor.LIME_TERRACOTTA)));
    public static final RegistryObject<RotatedPillarBlock> purple_agate_log = registerGeneral("purple_agate_log", () -> new AgateLogBlock(() -> s_purple_agate_log, GaiaBlockProperties.logProps(MaterialColor.PURPLE_TERRACOTTA, MaterialColor.PURPLE)));
    public static final RegistryObject<RotatedPillarBlock> fossilized_log = registerGeneral("fossilized_log", () -> new AgateLogBlock(() -> s_fossilized_log, GaiaBlockProperties.logProps(MaterialColor.YELLOW, MaterialColor.DIRT)));
    public static final RegistryObject<RotatedPillarBlock> corrupted_log = registerGeneral("corrupted_log", () -> new AgateLogBlock(() -> s_corrupted_log, GaiaBlockProperties.logProps(MaterialColor.TNT, MaterialColor.GRAY_TERRACOTTA)));
    public static final RegistryObject<RotatedPillarBlock> burnt_log = registerGeneral("burnt_log", () -> new AgateLogBlock(() -> s_burnt_log, GaiaBlockProperties.logProps(MaterialColor.GRAY, MaterialColor.BLACK_TERRACOTTA)));
    public static final RegistryObject<RotatedPillarBlock> burning_log = registerFuel("burning_log", () -> new AgateLogBlock(() -> s_burning_log, GaiaBlockProperties.logProps(MaterialColor.ADOBE, MaterialColor.ORANGE_TERRACOTTA).setLightLevel((state) -> 3)), 1600);
    public static final RegistryObject<RotatedPillarBlock> aura_log = registerGeneral("aura_log", () -> new AgateLogBlock(() -> s_aura_log, GaiaBlockProperties.logProps(MaterialColor.IRON, MaterialColor.GRAY)));
    public static final RegistryObject<RotatedPillarBlock> stripped_pink_agate_log = registerGeneral("stripped_pink_agate_log", () -> s_pink_agate_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_blue_agate_log = registerGeneral("stripped_blue_agate_log", () -> s_blue_agate_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_green_agate_log = registerGeneral("stripped_green_agate_log", () -> s_green_agate_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_purple_agate_log = registerGeneral("stripped_purple_agate_log", () -> s_purple_agate_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_fossilized_log = registerGeneral("stripped_fossilized_log", () -> s_fossilized_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_corrupted_log = registerGeneral("stripped_corrupted_log", () -> s_corrupted_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_burnt_log = registerGeneral("stripped_burnt_log", () -> s_burnt_log);
    public static final RegistryObject<RotatedPillarBlock> stripped_burning_log = registerFuel("stripped_burning_log", () -> s_burning_log, 1600);
    public static final RegistryObject<RotatedPillarBlock> stripped_aura_log = registerGeneral("stripped_aura_log", () -> s_aura_log);
    public static final RegistryObject<RotatedPillarBlock> pink_agate_wood = registerGeneral("pink_agate_wood", () -> new AgateLogBlock(() -> s_pink_agate_wood, GaiaBlockProperties.logProps(MaterialColor.PINK_TERRACOTTA)));
    public static final RegistryObject<RotatedPillarBlock> blue_agate_wood = registerGeneral("blue_agate_wood", () -> new AgateLogBlock(() -> s_blue_agate_wood, GaiaBlockProperties.logProps(MaterialColor.BLUE_TERRACOTTA)));
    public static final RegistryObject<RotatedPillarBlock> green_agate_wood = registerGeneral("green_agate_wood", () -> new AgateLogBlock(() -> s_green_agate_wood, GaiaBlockProperties.logProps(MaterialColor.LIME_TERRACOTTA)));
    public static final RegistryObject<RotatedPillarBlock> purple_agate_wood = registerGeneral("purple_agate_wood", () -> new AgateLogBlock(() -> s_purple_agate_wood, GaiaBlockProperties.logProps(MaterialColor.PURPLE)));
    public static final RegistryObject<RotatedPillarBlock> fossilized_wood = registerGeneral("fossilized_wood", () -> new AgateLogBlock(() -> s_fossilized_wood, GaiaBlockProperties.logProps(MaterialColor.DIRT)));
    public static final RegistryObject<RotatedPillarBlock> corrupted_wood = registerGeneral("corrupted_wood", () -> new AgateLogBlock(() -> s_corrupted_wood, GaiaBlockProperties.logProps(MaterialColor.GRAY_TERRACOTTA)));
    public static final RegistryObject<RotatedPillarBlock> burnt_wood = registerGeneral("burnt_wood", () -> new AgateLogBlock(() -> s_burnt_wood, GaiaBlockProperties.logProps(MaterialColor.BLACK_TERRACOTTA)));
    public static final RegistryObject<RotatedPillarBlock> burning_wood = registerFuel("burning_wood", () -> new AgateLogBlock(() -> s_burning_wood, GaiaBlockProperties.logProps(MaterialColor.ORANGE_TERRACOTTA).setLightLevel((state) -> 3)), 1600);
    public static final RegistryObject<RotatedPillarBlock> aura_wood = registerGeneral("aura_wood", () -> new AgateLogBlock(() -> s_aura_wood, GaiaBlockProperties.logProps(MaterialColor.GRAY)));
    public static final RegistryObject<RotatedPillarBlock> stripped_pink_agate_wood = registerGeneral("stripped_pink_agate_wood", () -> s_pink_agate_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_blue_agate_wood = registerGeneral("stripped_blue_agate_wood", () -> s_blue_agate_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_green_agate_wood = registerGeneral("stripped_green_agate_wood", () -> s_green_agate_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_purple_agate_wood = registerGeneral("stripped_purple_agate_wood", () -> s_purple_agate_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_fossilized_wood = registerGeneral("stripped_fossilized_wood", () -> s_fossilized_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_corrupted_wood = registerGeneral("stripped_corrupted_wood", () -> s_corrupted_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_burnt_wood = registerGeneral("stripped_burnt_wood", () -> s_burnt_wood);
    public static final RegistryObject<RotatedPillarBlock> stripped_burning_wood = registerFuel("stripped_burning_wood", () -> s_burning_wood, 1600);
    public static final RegistryObject<RotatedPillarBlock> stripped_aura_wood = registerGeneral("stripped_aura_wood", () -> s_aura_wood);

    public static final RegistryObject<Block> salt = registerGeneral("salt", () -> new GaiaFallingBlock(GaiaBlockProperties.sandProps(MaterialColor.SNOW, 0.9F, SoundType.SAND), 0xE0E0FF));
    public static final RegistryObject<Block> saltstone = registerPlain("saltstone", GaiaBlockProperties.stoneToolProps(MaterialColor.LIGHT_BLUE_TERRACOTTA, 1.5F, 10.0F, PICKAXE, 0));
    public static final RegistryObject<Block> pebbles = registerGeneral("pebbles", () -> new GaiaFallingBlock(GaiaBlockProperties.sandProps(MaterialColor.GRAY, 1.3F, SoundType.GROUND), 0x663366));
    public static final RegistryObject<Block> gaia_stone = registerPlain("gaia_stone", GaiaBlockProperties.stoneToolProps(MaterialColor.MAGENTA, 2.0F, 15.0F, PICKAXE, 1));
    public static final RegistryObject<Block> gaia_cobblestone = registerPlain("gaia_cobblestone", GaiaBlockProperties.stoneToolProps(MaterialColor.MAGENTA, 2.0F, 15.0F, PICKAXE, 1));
    public static final RegistryObject<Block> wasteland_stone = registerPlain("wasteland_stone", GaiaBlockProperties.stoneToolProps(MaterialColor.BLUE_TERRACOTTA, 15.0F, 200.0F, PICKAXE, 2));
    public static final RegistryObject<Block> static_stone = registerGeneral("static_stone", () -> new StaticStoneBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.BLUE_TERRACOTTA, 50.0F, 200.0F, ToolType.PICKAXE, 2)));
    public static final RegistryObject<Block> charged_mineral = registerGeneral("charged_mineral", () -> new ChargedMineralBlock(Properties.create(Material.IRON, MaterialColor.CYAN).hardnessAndResistance(4.0F, 15.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1).sound(SoundType.GLASS).notSolid()));
    public static final RegistryObject<Block> volcanic_rock = registerPlain("volcanic_rock", GaiaBlockProperties.stoneToolProps(MaterialColor.GRAY_TERRACOTTA, 15.0F, 200.0F, PICKAXE, 2));
    public static final RegistryObject<Block> searing_rock = registerGeneral("searing_rock", () -> new SearingRockBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.GRAY_TERRACOTTA, 20.0F, 600.0F, ToolType.PICKAXE, 2).setLightLevel((state) -> 7)));
    public static final RegistryObject<Block> primal_mass = registerPlain("primal_mass", GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE_TERRACOTTA, 30.0F, 400.0F, PICKAXE, 2));
    public static final RegistryObject<Block> impure_rock = registerPlain("impure_rock", GaiaBlockProperties.stoneToolProps(MaterialColor.GRAY, 20.0F, 300.0F, PICKAXE, 2));
    public static final RegistryObject<Block> active_rock = registerGeneral("active_rock", () -> new ActiveRockBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE_TERRACOTTA, 15.0F, 250.0F, ToolType.PICKAXE, 2).setLightLevel((state) -> 7)));
    public static final RegistryObject<Block> impure_sludge = registerGeneral("impure_sludge", () -> new ImpureSludgeBlock(GaiaBlockProperties.sludgeProps()));
    public static final RegistryObject<Block> geyser_block = registerGeneral("geyser_block", () -> new GeyserBlock(GaiaBlockProperties.stoneToolProps(MaterialColor.IRON, 5.0F, 10.0F, ToolType.PICKAXE, 1)));
    public static final RegistryObject<Block> sparkling_rock = registerPlain("sparkling_rock", Properties.create(Material.ROCK, MaterialColor.IRON).hardnessAndResistance(10.0F, 150.0F).sound(SoundType.GLASS).setRequiresTool().harvestTool(ToolType.PICKAXE).harvestLevel(1));
    public static final RegistryObject<Block> aura_shoot = registerGeneral("aura_shoot", () -> new AuraShootBlock(Properties.create(Material.GLASS, MaterialColor.BLUE).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE).harvestLevel(1).tickRandomly()));

    //Planks
    public static final RegistryObject<Block> pink_agate_planks = registerPlain("pink_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.PINK));
    public static final RegistryObject<Block> blue_agate_planks = registerPlain("blue_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.LIGHT_BLUE));
    public static final RegistryObject<Block> green_agate_planks = registerPlain("green_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.LIME));
    public static final RegistryObject<Block> purple_agate_planks = registerPlain("purple_agate_planks", GaiaBlockProperties.tileProps(MaterialColor.PURPLE_TERRACOTTA));
    public static final RegistryObject<Block> fossilized_planks = registerPlain("fossilized_planks", GaiaBlockProperties.tileProps(MaterialColor.YELLOW_TERRACOTTA));
    public static final RegistryObject<Block> corrupted_planks = registerPlain("corrupted_planks", GaiaBlockProperties.tileProps(MaterialColor.BLACK_TERRACOTTA));
    public static final RegistryObject<Block> burnt_planks = registerPlain("burnt_planks", GaiaBlockProperties.tileProps(MaterialColor.BLACK));
    public static final RegistryObject<Block> burning_planks = registerPlain("burning_planks", GaiaBlockProperties.tileProps(MaterialColor.ORANGE_TERRACOTTA).setLightLevel((state) -> 3), 400);
    public static final RegistryObject<Block> aura_planks = registerPlain("aura_planks", GaiaBlockProperties.tileProps(MaterialColor.SNOW));
    public static final RegistryObject<SlabBlock> pink_agate_plank_slab = registerGeneral("pink_agate_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.PINK)));
    public static final RegistryObject<SlabBlock> blue_agate_plank_slab = registerGeneral("blue_agate_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.LIGHT_BLUE)));
    public static final RegistryObject<SlabBlock> green_agate_plank_slab = registerGeneral("green_agate_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.LIME)));
    public static final RegistryObject<SlabBlock> purple_agate_plank_slab = registerGeneral("purple_agate_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.PURPLE_TERRACOTTA)));
    public static final RegistryObject<SlabBlock> fossilized_plank_slab = registerGeneral("fossilized_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.YELLOW_TERRACOTTA)));
    public static final RegistryObject<SlabBlock> corrupted_plank_slab = registerGeneral("corrupted_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.BLACK_TERRACOTTA)));
    public static final RegistryObject<SlabBlock> burnt_plank_slab = registerGeneral("burnt_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.BLACK)));
    public static final RegistryObject<SlabBlock> burning_plank_slab = registerFuel("burning_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.ORANGE_TERRACOTTA).setLightLevel((state) -> 3)), 200);
    public static final RegistryObject<SlabBlock> aura_plank_slab = registerGeneral("aura_plank_slab", () -> new SlabBlock(GaiaBlockProperties.tileProps(MaterialColor.SNOW)));
    public static final RegistryObject<StairsBlock> pink_agate_plank_stairs = registerGeneral("pink_agate_plank_stairs", () -> new GaiaStairsBlock(pink_agate_planks));
    public static final RegistryObject<StairsBlock> blue_agate_plank_stairs = registerGeneral("blue_agate_plank_stairs", () -> new GaiaStairsBlock(blue_agate_planks));
    public static final RegistryObject<StairsBlock> green_agate_plank_stairs = registerGeneral("green_agate_plank_stairs", () -> new GaiaStairsBlock(green_agate_planks));
    public static final RegistryObject<StairsBlock> purple_agate_plank_stairs = registerGeneral("purple_agate_plank_stairs", () -> new GaiaStairsBlock(purple_agate_planks));
    public static final RegistryObject<StairsBlock> fossilized_plank_stairs = registerGeneral("fossilized_plank_stairs", () -> new GaiaStairsBlock(fossilized_planks));
    public static final RegistryObject<StairsBlock> corrupted_plank_stairs = registerGeneral("corrupted_plank_stairs", () -> new GaiaStairsBlock(corrupted_planks));
    public static final RegistryObject<StairsBlock> burnt_plank_stairs = registerGeneral("burnt_plank_stairs", () -> new GaiaStairsBlock(burnt_planks));
    public static final RegistryObject<StairsBlock> burning_plank_stairs = registerFuel("burning_plank_stairs", () -> new GaiaStairsBlock(burning_planks), 300);
    public static final RegistryObject<StairsBlock> aura_plank_stairs = registerGeneral("aura_plank_stairs", () -> new GaiaStairsBlock(aura_planks));

    //Manufactured
    public static final RegistryObject<Block> cloudy_glass = registerGeneral("cloudy_glass", () -> new GaiaGlassBlock(GaiaBlockProperties.glassProps(MaterialColor.YELLOW, 0.7F)));
    public static final RegistryObject<Block> foggy_glass = registerGeneral("foggy_glass", () -> new GaiaGlassBlock(GaiaBlockProperties.glassProps(MaterialColor.LIGHT_BLUE, 0.7F)));
    public static final RegistryObject<Block> gaia_stone_bricks = registerPlain("gaia_stone_bricks", GaiaBlockProperties.gaiaBrickProps());
    public static final RegistryObject<Block> cracked_gaia_stone_bricks = registerPlain("cracked_gaia_stone_bricks", GaiaBlockProperties.gaiaBrickProps());
    public static final RegistryObject<Block> crusted_gaia_stone_bricks = registerPlain("crusted_gaia_stone_bricks", GaiaBlockProperties.gaiaBrickProps());

    public static final RegistryObject<Block> raw_jade = registerPlain("raw_jade", GaiaBlockProperties.stoneToolProps(MaterialColor.GREEN, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> jade_bricks = registerPlain("jade_bricks", GaiaBlockProperties.jadeProps());
    public static final RegistryObject<SlabBlock> jade_brick_slab = registerGeneral("jade_brick_slab", () -> new SlabBlock(GaiaBlockProperties.jadeProps()));
    public static final RegistryObject<StairsBlock> jade_brick_stairs = registerGeneral("jade_brick_stairs", () -> new GaiaStairsBlock(jade_bricks));
    public static final RegistryObject<Block> cracked_jade_bricks = registerPlain("cracked_jade_bricks", GaiaBlockProperties.jadeProps());
    public static final RegistryObject<SlabBlock> cracked_jade_brick_slab = registerGeneral("cracked_jade_brick_slab", () -> new SlabBlock(GaiaBlockProperties.jadeProps()));
    public static final RegistryObject<StairsBlock> cracked_jade_brick_stairs = registerGeneral("cracked_jade_brick_stairs", () -> new GaiaStairsBlock(cracked_jade_bricks));
    public static final RegistryObject<Block> crusted_jade_bricks = registerPlain("crusted_jade_bricks", GaiaBlockProperties.jadeProps());
    public static final RegistryObject<SlabBlock> crusted_jade_brick_slab = registerGeneral("crusted_jade_brick_slab", () -> new SlabBlock(GaiaBlockProperties.jadeProps()));
    public static final RegistryObject<StairsBlock> crusted_jade_brick_stairs = registerGeneral("crusted_jade_brick_stairs", () -> new GaiaStairsBlock(crusted_jade_bricks));
    public static final RegistryObject<Block> raw_copal = registerPlain("raw_copal", GaiaBlockProperties.stoneToolProps(MaterialColor.GOLD, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> copal_bricks = registerPlain("copal_bricks", GaiaBlockProperties.copalProps());
    public static final RegistryObject<SlabBlock> copal_brick_slab = registerGeneral("copal_brick_slab", () -> new SlabBlock(GaiaBlockProperties.copalProps()));
    public static final RegistryObject<StairsBlock> copal_brick_stairs = registerGeneral("copal_brick_stairs", () -> new GaiaStairsBlock(copal_bricks));
    public static final RegistryObject<Block> cracked_copal_bricks = registerPlain("cracked_copal_bricks", GaiaBlockProperties.copalProps());
    public static final RegistryObject<SlabBlock> cracked_copal_brick_slab = registerGeneral("cracked_copal_brick_slab", () -> new SlabBlock(GaiaBlockProperties.copalProps()));
    public static final RegistryObject<StairsBlock> cracked_copal_brick_stairs = registerGeneral("cracked_copal_brick_stairs", () -> new GaiaStairsBlock(cracked_copal_bricks));
    public static final RegistryObject<Block> crusted_copal_bricks = registerPlain("crusted_copal_bricks", GaiaBlockProperties.copalProps());
    public static final RegistryObject<SlabBlock> crusted_copal_brick_slab = registerGeneral("crusted_copal_brick_slab", () -> new SlabBlock(GaiaBlockProperties.copalProps()));
    public static final RegistryObject<StairsBlock> crusted_copal_brick_stairs = registerGeneral("crusted_copal_brick_stairs", () -> new GaiaStairsBlock(crusted_copal_bricks));
    public static final RegistryObject<Block> raw_jet = registerPlain("raw_jet", GaiaBlockProperties.stoneToolProps(MaterialColor.GRAY, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> jet_bricks = registerPlain("jet_bricks", GaiaBlockProperties.jetProps());
    public static final RegistryObject<SlabBlock> jet_brick_slab = registerGeneral("jet_brick_slab", () -> new SlabBlock(GaiaBlockProperties.jetProps()));
    public static final RegistryObject<StairsBlock> jet_brick_stairs = registerGeneral("jet_brick_stairs", () -> new GaiaStairsBlock(jet_bricks));
    public static final RegistryObject<Block> cracked_jet_bricks = registerPlain("cracked_jet_bricks", GaiaBlockProperties.jetProps());
    public static final RegistryObject<SlabBlock> cracked_jet_brick_slab = registerGeneral("cracked_jet_brick_slab", () -> new SlabBlock(GaiaBlockProperties.jetProps()));
    public static final RegistryObject<StairsBlock> cracked_jet_brick_stairs = registerGeneral("cracked_jet_brick_stairs", () -> new GaiaStairsBlock(cracked_jet_bricks));
    public static final RegistryObject<Block> crusted_jet_bricks = registerPlain("crusted_jet_bricks", GaiaBlockProperties.jetProps());
    public static final RegistryObject<SlabBlock> crusted_jet_brick_slab = registerGeneral("crusted_jet_brick_slab", () -> new SlabBlock(GaiaBlockProperties.jetProps()));
    public static final RegistryObject<StairsBlock> crusted_jet_brick_stairs = registerGeneral("crusted_jet_brick_stairs", () -> new GaiaStairsBlock(crusted_jet_bricks));
    public static final RegistryObject<Block> raw_amethyst = registerPlain("raw_amethyst", GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE_TERRACOTTA, 2.0F, 20.0F, PICKAXE, 1));
    public static final RegistryObject<Block> amethyst_bricks = registerPlain("amethyst_bricks", GaiaBlockProperties.amethystProps());
    public static final RegistryObject<SlabBlock> amethyst_brick_slab = registerGeneral("amethyst_brick_slab", () -> new SlabBlock(GaiaBlockProperties.amethystProps()));
    public static final RegistryObject<StairsBlock> amethyst_brick_stairs = registerGeneral("amethyst_brick_stairs", () -> new GaiaStairsBlock(amethyst_bricks));
    public static final RegistryObject<Block> cracked_amethyst_bricks = registerPlain("cracked_amethyst_bricks", GaiaBlockProperties.amethystProps());
    public static final RegistryObject<SlabBlock> cracked_amethyst_brick_slab = registerGeneral("cracked_amethyst_brick_slab", () -> new SlabBlock(GaiaBlockProperties.amethystProps()));
    public static final RegistryObject<StairsBlock> cracked_amethyst_brick_stairs = registerGeneral("cracked_amethyst_brick_stairs", () -> new GaiaStairsBlock(cracked_amethyst_bricks));
    public static final RegistryObject<Block> crusted_amethyst_bricks = registerPlain("crusted_amethyst_bricks", GaiaBlockProperties.amethystProps());
    public static final RegistryObject<SlabBlock> crusted_amethyst_brick_slab = registerGeneral("crusted_amethyst_brick_slab", () -> new SlabBlock(GaiaBlockProperties.amethystProps()));
    public static final RegistryObject<StairsBlock> crusted_amethyst_brick_stairs = registerGeneral("crusted_amethyst_brick_stairs", () -> new GaiaStairsBlock(crusted_amethyst_bricks));

    public static final RegistryObject<Block> reinforced_bricks = registerPlain("reinforced_bricks", GaiaBlockProperties.stoneToolProps(MaterialColor.PURPLE, 10.0F, 100.0F, PICKAXE, 1));
    public static final RegistryObject<Block> bolstered_bricks = registerPlain("bolstered_bricks", GaiaBlockProperties.stoneToolProps(MaterialColor.SAND, 30.0F, 400.0F, PICKAXE, 2));
    public static final RegistryObject<Block> malachite_bricks = registerPlain("malachite_bricks", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_cracked_bricks = registerPlain("malachite_cracked_bricks", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_crusted_bricks = registerPlain("malachite_crusted_bricks", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_floor_tiles = registerPlain("malachite_floor_tiles", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_chisel_bricks = registerPlain("malachite_chisel_bricks", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_pulsing_bricks = registerPlain("malachite_pulsing_bricks", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_pulsing_tiles = registerPlain("malachite_pulsing_tiles", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<Block> malachite_pulsing_chisel = registerPlain("malachite_pulsing_chisel", GaiaBlockProperties.malachiteProps());
    public static final RegistryObject<SlabBlock> malachite_brick_slab = registerGeneral("malachite_brick_slab", () -> new SlabBlock(GaiaBlockProperties.malachiteProps()));
    public static final RegistryObject<SlabBlock> malachite_cracked_brick_slab = registerGeneral("malachite_cracked_brick_slab", () -> new SlabBlock(GaiaBlockProperties.malachiteProps()));
    public static final RegistryObject<SlabBlock> malachite_crusted_brick_slab = registerGeneral("malachite_crusted_brick_slab", () -> new SlabBlock(GaiaBlockProperties.malachiteProps()));
    public static final RegistryObject<SlabBlock> malachite_floor_slab = registerGeneral("malachite_floor_slab", () -> new SlabBlock(GaiaBlockProperties.malachiteProps()));
    public static final RegistryObject<RotatedPillarBlock> malachite_pillar = registerGeneral("malachite_pillar", () -> new RotatedPillarBlock(GaiaBlockProperties.malachiteProps()));
    public static final RegistryObject<StairsBlock> malachite_brick_stairs = registerGeneral("malachite_brick_stairs", () -> new GaiaStairsBlock(malachite_bricks));
    public static final RegistryObject<StairsBlock> malachite_cracked_brick_stairs = registerGeneral("malachite_cracked_brick_stairs", () -> new GaiaStairsBlock(malachite_cracked_bricks));
    public static final RegistryObject<StairsBlock> malachite_crusted_brick_stairs = registerGeneral("malachite_crusted_brick_stairs", () -> new GaiaStairsBlock(malachite_crusted_bricks));
    public static final RegistryObject<StairsBlock> malachite_floor_stairs = registerGeneral("malachite_floor_stairs", () -> new GaiaStairsBlock(malachite_floor_tiles));
    public static final RegistryObject<StairsBlock> malachite_chisel_stairs = registerGeneral("malachite_chisel_stairs", () -> new GaiaStairsBlock(malachite_chisel_bricks));
    public static final RegistryObject<StairsBlock> malachite_pulsing_brick_stairs = registerGeneral("malachite_pulsing_brick_stairs", () -> new GaiaStairsBlock(malachite_bricks));
    public static final RegistryObject<StairsBlock> malachite_pulsing_floor_stairs = registerGeneral("malachite_pulsing_floor_stairs", () -> new GaiaStairsBlock(malachite_floor_tiles));
    public static final RegistryObject<StairsBlock> malachite_pulsing_chisel_stairs = registerGeneral("malachite_pulsing_chisel_stairs", () -> new GaiaStairsBlock(malachite_chisel_bricks));
    public static final RegistryObject<StairsBlock> malachite_pillar_stairs = registerGeneral("malachite_pillar_stairs", () -> new GaiaStairsBlock(malachite_pillar));

    //Storage Blocks
    public static final RegistryObject<Block> sugilite_block = registerPlain("sugilite_block", GaiaBlockProperties.storageProps(MaterialColor.PURPLE));
    public static final RegistryObject<Block> hematite_block = registerPlain("hematite_block", GaiaBlockProperties.storageProps(MaterialColor.GRAY));
    public static final RegistryObject<Block> cinnabar_block = registerPlain("cinnabar_block", GaiaBlockProperties.storageProps(MaterialColor.ADOBE));
    public static final RegistryObject<Block> labradorite_block = registerPlain("labradorite_block", GaiaBlockProperties.storageProps(MaterialColor.GREEN));
    public static final RegistryObject<Block> moonstone_block = registerPlain("moonstone_block", GaiaBlockProperties.storageProps(MaterialColor.IRON));
    public static final RegistryObject<Block> opal_block_red = registerPlain("opal_block_red", GaiaBlockProperties.storageProps(MaterialColor.RED));
    public static final RegistryObject<Block> opal_block_blue = registerPlain("opal_block_blue", GaiaBlockProperties.storageProps(MaterialColor.LIGHT_BLUE));
    public static final RegistryObject<Block> opal_block_green = registerPlain("opal_block_green", GaiaBlockProperties.storageProps(MaterialColor.LIME));
    public static final RegistryObject<Block> opal_block_white = registerPlain("opal_block_white", GaiaBlockProperties.storageProps(MaterialColor.SNOW));
    public static final RegistryObject<Block> pyrite_block = registerPlain("pyrite_block", GaiaBlockProperties.storageProps(MaterialColor.GOLD).setLightLevel((state) -> 15));
    public static final RegistryObject<Block> tektite_block = registerPlain("tektite_block", GaiaBlockProperties.storageProps(MaterialColor.BLACK));
    public static final RegistryObject<Block> goldstone_block = registerPlain("goldstone_block", GaiaBlockProperties.storageProps(MaterialColor.BLACK));
    public static final RegistryObject<Block> aura_block = registerPlain("aura_block", GaiaBlockProperties.storageProps(MaterialColor.ICE));
    public static final RegistryObject<Block> bismuth_block = registerPlain("bismuth_block", GaiaBlockProperties.storageProps(MaterialColor.OBSIDIAN));
    public static final RegistryObject<Block> ixiolite_block = registerPlain("ixiolite_block", GaiaBlockProperties.storageProps(MaterialColor.GRAY));
    public static final RegistryObject<Block> proustite_block = registerPlain("proustite_block", GaiaBlockProperties.storageProps(MaterialColor.MAGENTA));
    public static final RegistryObject<Block> euclase_block = registerPlain("euclase_block", GaiaBlockProperties.storageProps(MaterialColor.LIME));
    public static final RegistryObject<Block> leucite_block = registerPlain("leucite_block", GaiaBlockProperties.storageProps(MaterialColor.SAND));
    public static final RegistryObject<Block> carnelian_block = registerPlain("carnelian_block", GaiaBlockProperties.storageProps(MaterialColor.RED));
    public static final RegistryObject<Block> benitoite_block = registerPlain("benitoite_block", GaiaBlockProperties.storageProps(MaterialColor.BLUE));
    public static final RegistryObject<Block> diopside_block = registerPlain("diopside_block", GaiaBlockProperties.storageProps(MaterialColor.LIME));
    public static final RegistryObject<Block> chalcedony_block = registerPlain("chalcedony_block", GaiaBlockProperties.storageProps(MaterialColor.SNOW));

    //Ores
    public static final RegistryObject<Block> sugilite_ore = registerGeneral("sugilite_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.PURPLE, 1), 1, 3));
    public static final RegistryObject<Block> hematite_ore = registerGeneral("hematite_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.GRAY, 2), 1, 4));
    public static final RegistryObject<Block> cinnabar_ore = registerGeneral("cinnabar_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.ADOBE, 2), 1, 4));
    public static final RegistryObject<Block> labradorite_ore = registerGeneral("labradorite_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.GREEN, 2), 5, 2));
    public static final RegistryObject<Block> moonstone_ore = registerGeneral("moonstone_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.IRON, 2), 5, 2));
    public static final RegistryObject<Block> opal_ore_red = registerGeneral("opal_ore_red", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.RED, 2), 2, 5));
    public static final RegistryObject<Block> opal_ore_blue = registerGeneral("opal_ore_blue", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.LIGHT_BLUE, 2), 2, 5));
    public static final RegistryObject<Block> opal_ore_green = registerGeneral("opal_ore_green", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.LIME, 2), 2, 5));
    public static final RegistryObject<Block> opal_ore_white = registerGeneral("opal_ore_white", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.SNOW, 3), 3, 7));
    public static final RegistryObject<Block> pyrite_ore = registerGeneral("pyrite_ore", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.GOLD, 2).setLightLevel((state) -> 3), 1, 4));
    public static final RegistryObject<Block> speckled_rock = registerGeneral("speckled_rock", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.MAGENTA, 1)));
    public static final RegistryObject<Block> coarse_rock = registerGeneral("coarse_rock", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.MAGENTA, 2)));
    public static final RegistryObject<Block> precious_rock = registerGeneral("precious_rock", () -> new GaiaOreBlock(GaiaBlockProperties.oreProps(MaterialColor.MAGENTA, 3)));

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
    public static final RegistryObject<BossSpawnerBlock> malachite_guard_spawner = BLOCKS.register("malachite_guard_spawner", () -> new BossSpawnerBlock(BossSpawnerBlock.BossType.MALACHITE, GaiaBlockProperties.spawnerProps()));

    private static RegistryObject<Block> registerPlain(String name, Properties props) {
        return registerGeneral(name, () -> new Block(props));
    }

    private static RegistryObject<Block> registerPlain(String name, Properties props, int burn) {
        return registerFuel(name, () -> new Block(props), burn);
    }

    private static <T extends Block> RegistryObject<T> registerGeneral(String name, Supplier<? extends T> block) {
        return registerBlock(name, block, 0, ModBlocks::registerBlockItem);
    }

    private static <T extends Block> RegistryObject<T> registerFuel(String name, Supplier<? extends T> block, int burnTime) {
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

    private static RegistryObject<FlowerPotBlock> registerFlowerPot(RegistryObject<? extends Block> plant) {
        return BLOCKS.register("potted_" + plant.getId().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, plant, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F)));
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