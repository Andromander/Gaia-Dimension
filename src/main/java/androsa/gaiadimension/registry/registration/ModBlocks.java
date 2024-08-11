package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.*;
import androsa.gaiadimension.item.ScaynyxBucketItem;
import androsa.gaiadimension.registry.bootstrap.GaiaFeatures;
import androsa.gaiadimension.registry.helpers.PropertiesHandler;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.util.ColorRGBA;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ModBlocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GaiaDimensionMod.MODID);

    //Utility Blocks
    public static final DeferredBlock<GaiaPortalBlock> gaia_portal = registerNoItem("gaia_portal", () ->
            new GaiaPortalBlock(PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PINK, -1.0F, -1.0F, false).noCollission().randomTicks().lightLevel((state) -> 15).noLootTable()));
    public static final DeferredBlock<Block> keystone_block = register("keystone_block",
            PropertiesHandler.basicProps(MapColor.GOLD, SoundType.METAL, 5.0F, 10.0F).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> gold_fire = registerNoItem("gold_fire", () ->
            new GoldFireBlock(Properties.of().mapColor(MapColor.GOLD).strength(0.0F).noCollission().randomTicks().lightLevel((state) -> 15).noLootTable()));
    public static final DeferredBlock<Block> pyrite_torch = registerNoItem("pyrite_torch", () -> new PyriteTorchBlock(PropertiesHandler.torchProps()));
    public static final DeferredBlock<Block> pyrite_wall_torch = registerNoItem("pyrite_wall_torch", () -> new PyriteWallTorchBlock(PropertiesHandler.torchProps().lootFrom(pyrite_torch)));
    public static final DeferredBlock<Block> agate_crafting_table = register("agate_crafting_table", () ->
            new AgateCraftingTableBlock(PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PINK, 1.5F, 2.0F, false)));
    public static final DeferredBlock<Block> crude_storage_crate = registerNoItem("crude_storage_crate", () ->
            new SmallCrateBlock(PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PINK, 10.0F, 150.0F).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> mega_storage_crate = registerNoItem("mega_storage_crate", () ->
            new LargeCrateBlock(PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PURPLE, 10.0F, 300.0F).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> gaia_stone_furnace = register("gaia_stone_furnace", () ->
            new GaiaStoneFurnaceBlock(PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PINK, 20.0F, 300.0F, true).lightLevel((state) -> state.getValue(AbstractFurnaceBlock.LIT) ? 13 : 0)));
    public static final DeferredBlock<Block> restructurer = register("restructurer", () ->
            new RestructurerBlock(PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PURPLE, 20.0F, 300.0F, true).lightLevel((state) -> state.getValue(RestructurerBlock.LIT) ? 14 : 0)));
    public static final DeferredBlock<Block> purifier = register("purifier", () ->
            new PurifierBlock(PropertiesHandler.stoneProps(MapColor.SAND, 20.0F, 300.0F, true).lightLevel((state) -> state.getValue(PurifierBlock.LIT) ? 14 : 0)));

    //Fluids
    public static final DeferredBlock<LiquidBlock> mineral_water = registerNoItem("mineral_water", () ->
            new GaiaFluidBlock(ModFluids.mineral_water_still, PropertiesHandler.liquidProps(MapColor.TERRACOTTA_LIGHT_BLUE)));
    public static final DeferredBlock<LiquidBlock> superhot_magma = registerNoItem("superhot_magma", () ->
            new GaiaFluidBlock(ModFluids.superhot_magma_still, PropertiesHandler.liquidProps(MapColor.COLOR_BLUE).randomTicks().lightLevel((state) -> 15)));
    public static final DeferredBlock<LiquidBlock> sweet_muck = registerNoItem("sweet_muck", () ->
            new GaiaFluidBlock(ModFluids.sweet_muck_still, PropertiesHandler.liquidProps(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<LiquidBlock> liquid_bismuth = registerNoItem("liquid_bismuth", () ->
            new GaiaFluidBlock(ModFluids.liquid_bismuth_still, PropertiesHandler.liquidProps(MapColor.TERRACOTTA_PURPLE).randomTicks().lightLevel((state) -> 3)));
    public static final DeferredBlock<LiquidBlock> liquid_aura = registerNoItem("liquid_aura", () ->
            new GaiaFluidBlock(ModFluids.liquid_aura_still, PropertiesHandler.liquidProps(MapColor.COLOR_LIGHT_BLUE)));

    //Natural Blocks
    public static final DeferredBlock<Block> heavy_soil = register("heavy_soil", () -> new GaiaSoilBlock(PropertiesHandler.soilProps(MapColor.TERRACOTTA_PURPLE)));
    public static final DeferredBlock<Block> corrupted_soil = register("corrupted_soil", () -> new GaiaSoilBlock(PropertiesHandler.soilProps(MapColor.COLOR_GRAY)));
    public static final DeferredBlock<Block> boggy_soil = register("boggy_soil", () -> new GaiaSoilBlock(PropertiesHandler.soilProps(MapColor.COLOR_GRAY)));
    public static final DeferredBlock<Block> light_soil = register("light_soil", () -> new GaiaSoilBlock(PropertiesHandler.soilProps(MapColor.GOLD)));
    public static final DeferredBlock<Block> aurum_soil = register("aurum_soil", () -> new GaiaSoilBlock(PropertiesHandler.soilProps(MapColor.TERRACOTTA_BLACK)));
    public static final DeferredBlock<Block> glitter_grass = register("glitter_grass", () -> new GlitterGrassBlock(PropertiesHandler.grassProps(MapColor.COLOR_PINK)));
    public static final DeferredBlock<Block> corrupted_grass = register("corrupted_grass", () -> new CorruptGrassBlock(PropertiesHandler.grassProps(MapColor.COLOR_BLACK)));
    public static final DeferredBlock<Block> murky_grass = register("murky_grass", () -> new MurkyGrassBlock(PropertiesHandler.grassProps(MapColor.COLOR_GRAY)));
    public static final DeferredBlock<Block> soft_grass = register("soft_grass", () -> new SoftGrassBlock(PropertiesHandler.grassProps(MapColor.COLOR_CYAN)));
    public static final DeferredBlock<Block> gilded_grass = register("gilded_grass", () -> new GildedGrassBlock(PropertiesHandler.grassProps(MapColor.TERRACOTTA_BROWN)));
    public static final DeferredBlock<Block> frail_glitter_block = register("frail_glitter_block", () -> new GlassBlock(PropertiesHandler.glassProps(MapColor.COLOR_PINK, 1.0F)));
    public static final DeferredBlock<Block> thick_glitter_block = register("thick_glitter_block", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PURPLE, 1.5F, 7.5F, true));
    public static final DeferredBlock<Block> gummy_glitter_block = register("gummy_glitter_block", () -> new SlimeBlock(Properties.of().mapColor(MapColor.COLOR_PURPLE).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final DeferredBlock<Block> pink_sludge_block = register("pink_sludge_block", () -> new SlimeBlock(Properties.of().mapColor(MapColor.COLOR_PINK).sound(SoundType.SLIME_BLOCK)));

    //Plants
    public static final DeferredBlock<Block> crystal_growth = register("crystal_growth", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MapColor.SNOW, true)));
    public static final DeferredBlock<Block> crystal_growth_red = register("crystal_growth_red", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MapColor.COLOR_RED, true)));
    public static final DeferredBlock<Block> crystal_growth_black = register("crystal_growth_black", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MapColor.COLOR_BLACK, true)));
    public static final DeferredBlock<Block> crystal_growth_seared = register("crystal_growth_seared", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MapColor.COLOR_BLACK, true)));
    public static final DeferredBlock<Block> crystal_growth_mutant = register("crystal_growth_mutant", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MapColor.TERRACOTTA_WHITE, true)));
    public static final DeferredBlock<Block> crystal_growth_aura = register("crystal_growth_aura", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MapColor.TERRACOTTA_LIGHT_BLUE, true)));
    public static final DeferredBlock<Block> golden_grass = register("golden_grass", () -> new GoldenGrassBlock(PropertiesHandler.plantProps(MapColor.GOLD, false)));
    public static final DeferredBlock<Block> tall_golden_grass = register("tall_golden_grass", () -> new DoubleCrystalGrowthBlock(PropertiesHandler.plantProps(MapColor.GOLD, false)));
    public static final DeferredBlock<Block> thiscus = register("thiscus", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final DeferredBlock<Block> ouzium = register("ouzium", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final DeferredBlock<Block> agathum = register("agathum", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final DeferredBlock<Block> varloom = register("varloom", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final DeferredBlock<Block> corrupted_varloom = register("corrupted_varloom", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final DeferredBlock<Block> glamelea = register("glamelea", () -> new GlameleaBlock(PropertiesHandler.bloomProps()));
    public static final DeferredBlock<Block> missingno_plant = register("missingno_plant", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final DeferredBlock<Block> spotted_kersei = register("spotted_kersei", () -> new CrystalFungusBlock(false, PropertiesHandler.plantProps(MapColor.COLOR_PINK, false)));
    public static final DeferredBlock<Block> thorny_wiltha = register("thorny_wiltha", () -> new CrystalFungusBlock(false, PropertiesHandler.plantProps(MapColor.COLOR_LIGHT_BLUE, false)));
    public static final DeferredBlock<Block> roofed_agaric = register("roofed_agaric", () -> new CrystalFungusBlock(false, PropertiesHandler.plantProps(MapColor.COLOR_LIGHT_GREEN, false)));
    public static final DeferredBlock<Block> bulbous_hobina = register("bulbous_hobina", () -> new CrystalFungusBlock(false, PropertiesHandler.plantProps(MapColor.TERRACOTTA_PINK, false)));
    public static final DeferredBlock<Block> stickly_cupsir = register("stickly_cupsir", () -> new CrystalFungusBlock(false, PropertiesHandler.plantProps(MapColor.TERRACOTTA_YELLOW, false)));
    public static final DeferredBlock<Block> mystical_murgni = register("mystical_murgni", () -> new CrystalFungusBlock(false, PropertiesHandler.plantProps(MapColor.GOLD, false)));
    public static final DeferredBlock<Block> corrupted_gaia_eye = register("corrupted_gaia_eye", () -> new CrystalFungusBlock(false, PropertiesHandler.plantProps(MapColor.FIRE, false)));
    public static final DeferredBlock<Block> twinkling_gilsri = register("twinkling_gilsri", ()-> new CrystalFungusBlock(false, PropertiesHandler.plantProps(MapColor.GOLD, false)));
    //public static final DeferredBlock<Block> sacred_gaia_eye = RegistryHelper.registerBlock()("sacred_gaia_eye", new CrystalFungusBlock(false));
    public static final DeferredBlock<Block> elder_imklia = register("elder_imklia", () -> new CrystalFungusBlock(true, PropertiesHandler.plantProps(MapColor.COLOR_PURPLE, false)));
    public static final DeferredBlock<Block> gold_orb_tucher = register("gold_orb_tucher", () -> new CrystalFungusBlock(true, PropertiesHandler.plantProps(MapColor.GOLD, false)));
    public static final DeferredBlock<Block> missingno_fungus = register("missingno_fungus", () -> new CrystalFungusBlock(false, PropertiesHandler.plantProps(MapColor.COLOR_MAGENTA, false)));
    public static final DeferredBlock<Block> golden_vine = register("golden_vine", () -> new VineBlock(PropertiesHandler.plantProps(MapColor.GOLD, false)));
    public static final DeferredBlock<Block> sombre_cacti = register("sombre_cacti", () -> new SombreCactiBlock(PropertiesHandler.plantProps(MapColor.TERRACOTTA_BROWN, false)));
    public static final DeferredBlock<Block> sombre_shrub = register("sombre_shrub", () -> new SombreShrubBlock(PropertiesHandler.plantProps(MapColor.TERRACOTTA_BROWN, false)));

    //Tree Blocks
    public static final DeferredBlock<Block> pink_agate_leaves = register("pink_agate_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MapColor.COLOR_MAGENTA)));
    public static final DeferredBlock<Block> blue_agate_leaves = register("blue_agate_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MapColor.COLOR_BLUE)));
    public static final DeferredBlock<Block> green_agate_leaves = register("green_agate_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MapColor.COLOR_GREEN)));
    public static final DeferredBlock<Block> purple_agate_leaves = register("purple_agate_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MapColor.TERRACOTTA_PURPLE)));
    public static final DeferredBlock<Block> fossilized_leaves = register("fossilized_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<Block> corrupted_leaves = register("corrupted_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MapColor.FIRE)));
    public static final DeferredBlock<Block> burnt_leaves = register("burnt_agate_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MapColor.COLOR_GRAY)));
    public static final DeferredBlock<Block> fire_agate_leaves = register("fire_agate_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MapColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 200);
    public static final DeferredBlock<Block> aura_leaves = register("aura_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MapColor.METAL)));
    public static final DeferredBlock<Block> golden_leaves = register("golden_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MapColor.GOLD)));
    public static final DeferredBlock<RotatedPillarBlock> pink_agate_log = register("pink_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_MAGENTA, MapColor.TERRACOTTA_PINK)));
    public static final DeferredBlock<RotatedPillarBlock> blue_agate_log = register("blue_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_BLUE, MapColor.TERRACOTTA_BLUE)));
    public static final DeferredBlock<RotatedPillarBlock> green_agate_log = register("green_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_GREEN, MapColor.TERRACOTTA_LIGHT_GREEN)));
    public static final DeferredBlock<RotatedPillarBlock> purple_agate_log = register("purple_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.TERRACOTTA_PURPLE, MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<RotatedPillarBlock> fossilized_log = register("fossilized_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_YELLOW, MapColor.DIRT)));
    public static final DeferredBlock<RotatedPillarBlock> corrupted_log = register("corrupted_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.FIRE, MapColor.TERRACOTTA_GRAY)));
    public static final DeferredBlock<RotatedPillarBlock> burnt_log = register("burnt_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_GRAY, MapColor.TERRACOTTA_BLACK)));
    public static final DeferredBlock<RotatedPillarBlock> fire_agate_log = register("fire_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_ORANGE, MapColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 1600);
    public static final DeferredBlock<RotatedPillarBlock> aura_log = register("aura_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.METAL, MapColor.COLOR_GRAY)));
    public static final DeferredBlock<RotatedPillarBlock> golden_log = register("golden_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.GOLD, MapColor.TERRACOTTA_BROWN)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_pink_agate_log = register("stripped_pink_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_MAGENTA)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_blue_agate_log = register("stripped_blue_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_BLUE)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_green_agate_log = register("stripped_green_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_GREEN)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_purple_agate_log = register("stripped_purple_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.TERRACOTTA_PURPLE)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_fossilized_log = register("stripped_fossilized_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_corrupted_log = register("stripped_corrupted_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.FIRE)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_burnt_log = register("stripped_burnt_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_GRAY)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_fire_agate_log = register("stripped_fire_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_ORANGE).lightLevel((state) -> 3)), 1600);
    public static final DeferredBlock<RotatedPillarBlock> stripped_aura_log = register("stripped_aura_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.METAL)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_golden_log = register("stripped_golden_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.GOLD)));
    public static final DeferredBlock<RotatedPillarBlock> pink_agate_wood = register("pink_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.TERRACOTTA_PINK)));
    public static final DeferredBlock<RotatedPillarBlock> blue_agate_wood = register("blue_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.TERRACOTTA_BLUE)));
    public static final DeferredBlock<RotatedPillarBlock> green_agate_wood = register("green_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.TERRACOTTA_LIGHT_GREEN)));
    public static final DeferredBlock<RotatedPillarBlock> purple_agate_wood = register("purple_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<RotatedPillarBlock> fossilized_wood = register("fossilized_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.DIRT)));
    public static final DeferredBlock<RotatedPillarBlock> corrupted_wood = register("corrupted_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.TERRACOTTA_GRAY)));
    public static final DeferredBlock<RotatedPillarBlock> burnt_wood = register("burnt_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.TERRACOTTA_BLACK)));
    public static final DeferredBlock<RotatedPillarBlock> fire_agate_wood = register("fire_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 1600);
    public static final DeferredBlock<RotatedPillarBlock> aura_wood = register("aura_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_GRAY)));
    public static final DeferredBlock<RotatedPillarBlock> golden_wood = register("golden_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.TERRACOTTA_BROWN)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_pink_agate_wood = register("stripped_pink_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_MAGENTA)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_blue_agate_wood = register("stripped_blue_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_BLUE)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_green_agate_wood = register("stripped_green_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_GREEN)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_purple_agate_wood = register("stripped_purple_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.TERRACOTTA_PURPLE)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_fossilized_wood = register("stripped_fossilized_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_YELLOW)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_corrupted_wood = register("stripped_corrupted_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.FIRE)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_burnt_wood = register("stripped_burnt_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_GRAY)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_fire_agate_wood = register("stripped_fire_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.COLOR_ORANGE).lightLevel((state) -> 3)), 1600);
    public static final DeferredBlock<RotatedPillarBlock> stripped_aura_wood = register("stripped_aura_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.METAL)));
    public static final DeferredBlock<RotatedPillarBlock> stripped_golden_wood = register("stripped_golden_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MapColor.GOLD)));

    public static final DeferredBlock<Block> salt = register("salt", () -> new ColoredFallingBlock(new ColorRGBA(0xE0E0FFFF), PropertiesHandler.sandProps(MapColor.SNOW, 0.9F, SoundType.SAND)));
    public static final DeferredBlock<Block> saltstone = register("saltstone", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_LIGHT_BLUE, 1.5F, 10.0F, true));
    public static final DeferredBlock<Block> pebbles = register("pebbles", () -> new ColoredFallingBlock(new ColorRGBA(0x663366FF), PropertiesHandler.sandProps(MapColor.COLOR_GRAY, 1.3F, SoundType.GRAVEL)));
    public static final DeferredBlock<Block> gaia_stone = register("gaia_stone", PropertiesHandler.stoneProps(MapColor.COLOR_MAGENTA, 2.0F, 15.0F, true));
    public static final DeferredBlock<Block> gaia_cobblestone = register("gaia_cobblestone", PropertiesHandler.stoneProps(MapColor.COLOR_MAGENTA, 2.0F, 15.0F, true));
    public static final DeferredBlock<Block> wasteland_stone = register("wasteland_stone", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_BLUE, 15.0F, 200.0F, true));
    public static final DeferredBlock<Block> static_stone = register("static_stone", () -> new StaticStoneBlock(PropertiesHandler.stoneProps(MapColor.TERRACOTTA_BLUE, 50.0F, 200.0F, true)));
    public static final DeferredBlock<Block> charged_mineral = register("charged_mineral", () -> new ChargedMineralBlock(Properties.of().mapColor(MapColor.COLOR_CYAN).strength(4.0F, 15.0F).sound(SoundType.GLASS).noOcclusion()));
    public static final DeferredBlock<Block> volcanic_rock = register("volcanic_rock", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_GRAY, 15.0F, 200.0F, true));
    public static final DeferredBlock<Block> searing_rock = register("searing_rock", () -> new SearingRockBlock(PropertiesHandler.stoneProps(MapColor.TERRACOTTA_GRAY, 20.0F, 600.0F, true).lightLevel((state) -> 7)));
    public static final DeferredBlock<Block> primal_mass = register("primal_mass", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PURPLE, 5.0F, 45.0F, true));
    public static final DeferredBlock<Block> nexustone = register("nexustone", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_BLACK, 10.0F, 100.0F, true));
    public static final DeferredBlock<Block> impure_rock = register("impure_rock", PropertiesHandler.stoneProps(MapColor.COLOR_GRAY, 20.0F, 300.0F, true));
    public static final DeferredBlock<Block> active_rock = register("active_rock", () -> new ActiveRockBlock(PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PURPLE, 15.0F, 250.0F, true).lightLevel((state) -> 7)));
    public static final DeferredBlock<Block> impure_sludge = register("impure_sludge", () -> new SlowingBlock(PropertiesHandler.muckyProps(MapColor.TERRACOTTA_YELLOW, 0.4F, 0.8F)));
    public static final DeferredBlock<Block> geyser_block = register("geyser_block", () -> new GeyserBlock(PropertiesHandler.stoneProps(MapColor.METAL, 5.0F, 10.0F, true)));
    public static final DeferredBlock<Block> sparkling_rock = register("sparkling_rock", Properties.of().mapColor(MapColor.METAL).strength(10.0F, 150.0F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops());
    public static final DeferredBlock<Block> aura_shoot = register("aura_shoot", () -> new AuraShootBlock(Properties.of().mapColor(MapColor.COLOR_BLUE).sound(SoundType.AMETHYST_CLUSTER).randomTicks()));
    public static final DeferredBlock<Block> golden_stone = register("golden_stone", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PURPLE, 2.0F, 15.0F, true));
    public static final DeferredBlock<Block> tough_golden_stone = register("tough_golden_stone", PropertiesHandler.stoneProps(MapColor.COLOR_BLACK, 3.0F, 30.0F, true));
    public static final DeferredBlock<Block> brilliant_stone = register("brilliant_stone", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_YELLOW, 5.0F, 35.0F, true));
    public static final DeferredBlock<Block> gilded_brilliant_stone = register("gilded_brilliant_stone", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_WHITE, 5.0F, 35.0F, true).lightLevel((state) -> 5));
    public static final DeferredBlock<Block> aurum_mud = register("aurum_mud", PropertiesHandler.muckyProps(MapColor.TERRACOTTA_BLACK, 0.3F, 0.4F));
    public static final DeferredBlock<Block> golden_sand = register("golden_sand", () -> new ColoredFallingBlock(new ColorRGBA(0xFFD700FF), PropertiesHandler.sandProps(MapColor.GOLD, 1.0F, SoundType.SAND)));
    public static final DeferredBlock<Block> scarlet_mookaite = register("scarlet_mookaite", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_RED, 1.8F, 12.0F));
    public static final DeferredBlock<Block> auburn_mookaite = register("auburn_mookaite", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_ORANGE, 1.8F, 12.0F));
    public static final DeferredBlock<Block> gold_mookaite = register("gold_mookaite", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_YELLOW, 1.8F, 12.0F));
    public static final DeferredBlock<Block> mauve_mookaite = register("mauve_mookaite", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PURPLE, 1.8F, 12.0F));
    public static final DeferredBlock<Block> beige_mookaite = register("beige_mookaite", PropertiesHandler.stoneProps(MapColor.SAND, 1.8F, 12.0F));
    public static final DeferredBlock<Block> ivory_mookaite = register("ivory_mookaite", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_WHITE, 1.8F, 12.0F));

    //Planks
    public static final DeferredBlock<Block> pink_agate_tiles = register("pink_agate_tiles", PropertiesHandler.tileProps(MapColor.COLOR_PINK));
    public static final DeferredBlock<Block> blue_agate_tiles = register("blue_agate_tiles", PropertiesHandler.tileProps(MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredBlock<Block> green_agate_tiles = register("green_agate_tiles", PropertiesHandler.tileProps(MapColor.COLOR_LIGHT_GREEN));
    public static final DeferredBlock<Block> purple_agate_tiles = register("purple_agate_tiles", PropertiesHandler.tileProps(MapColor.TERRACOTTA_PURPLE));
    public static final DeferredBlock<Block> fossilized_tiles = register("fossilized_tiles", PropertiesHandler.tileProps(MapColor.TERRACOTTA_YELLOW));
    public static final DeferredBlock<Block> corrupted_tiles = register("corrupted_tiles", PropertiesHandler.tileProps(MapColor.TERRACOTTA_BLACK));
    public static final DeferredBlock<Block> burnt_tiles = register("burnt_agate_tiles", PropertiesHandler.tileProps(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> fire_agate_tiles = register("fire_agate_tiles", PropertiesHandler.tileProps(MapColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3), 400);
    public static final DeferredBlock<Block> aura_tiles = register("aura_tiles", PropertiesHandler.tileProps(MapColor.SNOW));
    public static final DeferredBlock<Block> golden_tiles = register("golden_tiles", PropertiesHandler.tileProps(MapColor.GOLD));
    public static final DeferredBlock<SlabBlock> pink_agate_tile_slab = register("pink_agate_tile_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MapColor.COLOR_PINK)));
    public static final DeferredBlock<SlabBlock> blue_agate_tile_slab = register("blue_agate_tile_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MapColor.COLOR_LIGHT_BLUE)));
    public static final DeferredBlock<SlabBlock> green_agate_tile_slab = register("green_agate_tile_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MapColor.COLOR_LIGHT_GREEN)));
    public static final DeferredBlock<SlabBlock> purple_agate_tile_slab = register("purple_agate_tile_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MapColor.TERRACOTTA_PURPLE)));
    public static final DeferredBlock<SlabBlock> fossilized_tile_slab = register("fossilized_tile_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MapColor.TERRACOTTA_YELLOW)));
    public static final DeferredBlock<SlabBlock> corrupted_tile_slab = register("corrupted_tile_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MapColor.TERRACOTTA_BLACK)));
    public static final DeferredBlock<SlabBlock> burnt_tile_slab = register("burnt_agate_tile_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MapColor.COLOR_BLACK)));
    public static final DeferredBlock<SlabBlock> fire_agate_tile_slab = register("fire_agate_tile_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MapColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 200);
    public static final DeferredBlock<SlabBlock> aura_tile_slab = register("aura_tile_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MapColor.SNOW)));
    public static final DeferredBlock<SlabBlock> golden_tile_slab = register("golden_tile_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MapColor.GOLD)));
    public static final DeferredBlock<StairBlock> pink_agate_tile_stairs = register("pink_agate_tile_stairs", makeStairs(pink_agate_tiles), 0);
    public static final DeferredBlock<StairBlock> blue_agate_tile_stairs = register("blue_agate_tile_stairs", makeStairs(blue_agate_tiles), 0);
    public static final DeferredBlock<StairBlock> green_agate_tile_stairs = register("green_agate_tile_stairs", makeStairs(green_agate_tiles), 0);
    public static final DeferredBlock<StairBlock> purple_agate_tile_stairs = register("purple_agate_tile_stairs", makeStairs(purple_agate_tiles), 0);
    public static final DeferredBlock<StairBlock> fossilized_tile_stairs = register("fossilized_tile_stairs", makeStairs(fossilized_tiles), 0);
    public static final DeferredBlock<StairBlock> corrupted_tile_stairs = register("corrupted_tile_stairs", makeStairs(corrupted_tiles), 0);
    public static final DeferredBlock<StairBlock> burnt_tile_stairs = register("burnt_agate_tile_stairs", makeStairs(burnt_tiles), 0);
    public static final DeferredBlock<StairBlock> fire_agate_tile_stairs = register("fire_agate_tile_stairs", makeStairs(fire_agate_tiles), 300);
    public static final DeferredBlock<StairBlock> aura_tile_stairs = register("aura_tile_stairs", makeStairs(aura_tiles), 0);
    public static final DeferredBlock<StairBlock> golden_tile_stairs = register("golden_tile_stairs", makeStairs(golden_tiles), 0);

    //Decor
    public static final DeferredBlock<CurtainBlock> pink_agate_curtain = register("pink_agate_curtain", () -> new CurtainBlock(PropertiesHandler.curtainProps(MapColor.COLOR_PINK)));
    public static final DeferredBlock<CurtainBlock> blue_agate_curtain = register("blue_agate_curtain", () -> new CurtainBlock(PropertiesHandler.curtainProps(MapColor.COLOR_LIGHT_BLUE)));
    public static final DeferredBlock<CurtainBlock> green_agate_curtain = register("green_agate_curtain", () -> new CurtainBlock(PropertiesHandler.curtainProps(MapColor.COLOR_LIGHT_GREEN)));
    public static final DeferredBlock<CurtainBlock> purple_agate_curtain = register("purple_agate_curtain", () -> new CurtainBlock(PropertiesHandler.curtainProps(MapColor.TERRACOTTA_PURPLE)));
    public static final DeferredBlock<CurtainBlock> fossilized_curtain = register("fossilized_curtain", () -> new CurtainBlock(PropertiesHandler.curtainProps(MapColor.TERRACOTTA_YELLOW)));
    public static final DeferredBlock<CurtainBlock> corrupted_curtain = register("corrupted_curtain", () -> new CurtainBlock(PropertiesHandler.curtainProps(MapColor.TERRACOTTA_BLACK)));
    public static final DeferredBlock<CurtainBlock> burnt_agate_curtain = register("burnt_agate_curtain", () -> new CurtainBlock(PropertiesHandler.curtainProps(MapColor.TERRACOTTA_BLACK)));
    public static final DeferredBlock<CurtainBlock> fire_agate_curtain = register("fire_agate_curtain", () -> new CurtainBlock(PropertiesHandler.curtainProps(MapColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)));
    public static final DeferredBlock<CurtainBlock> aura_curtain = register("aura_curtain", () -> new CurtainBlock(PropertiesHandler.curtainProps(MapColor.SNOW)));
    public static final DeferredBlock<CurtainBlock> golden_curtain = register("golden_curtain", () -> new CurtainBlock(PropertiesHandler.curtainProps(MapColor.GOLD)));

    //Manufactured
    public static final DeferredBlock<Block> cloudy_glass = register("cloudy_glass", () -> new GlassBlock(PropertiesHandler.glassProps(MapColor.COLOR_YELLOW, 0.7F)));
    public static final DeferredBlock<Block> foggy_glass = register("foggy_glass", () -> new GlassBlock(PropertiesHandler.glassProps(MapColor.COLOR_LIGHT_BLUE, 0.7F)));
    public static final DeferredBlock<Block> gaia_stone_bricks = register("gaia_stone_bricks", PropertiesHandler.gaiaBrickProps());
    public static final DeferredBlock<Block> cracked_gaia_stone_bricks = register("cracked_gaia_stone_bricks", PropertiesHandler.gaiaBrickProps());
    public static final DeferredBlock<Block> crusted_gaia_stone_bricks = register("crusted_gaia_stone_bricks", PropertiesHandler.gaiaBrickProps());

    public static final DeferredBlock<Block> raw_jade = register("raw_jade", PropertiesHandler.stoneProps(MapColor.COLOR_GREEN, 2.0F, 20.0F, true));
    public static final DeferredBlock<Block> jade_bricks = register("jade_bricks", PropertiesHandler.jadeProps());
    public static final DeferredBlock<SlabBlock> jade_brick_slab = register("jade_brick_slab", makeSlab(PropertiesHandler.jadeProps()));
    public static final DeferredBlock<StairBlock> jade_brick_stairs = register("jade_brick_stairs", makeStairs(jade_bricks));
    public static final DeferredBlock<Block> cracked_jade_bricks = register("cracked_jade_bricks", PropertiesHandler.jadeProps());
    public static final DeferredBlock<SlabBlock> cracked_jade_brick_slab = register("cracked_jade_brick_slab", makeSlab(PropertiesHandler.jadeProps()));
    public static final DeferredBlock<StairBlock> cracked_jade_brick_stairs = register("cracked_jade_brick_stairs", makeStairs(cracked_jade_bricks));
    public static final DeferredBlock<Block> crusted_jade_bricks = register("crusted_jade_bricks", PropertiesHandler.jadeProps());
    public static final DeferredBlock<SlabBlock> crusted_jade_brick_slab = register("crusted_jade_brick_slab", makeSlab(PropertiesHandler.jadeProps()));
    public static final DeferredBlock<StairBlock> crusted_jade_brick_stairs = register("crusted_jade_brick_stairs", makeStairs(crusted_jade_bricks));
    public static final DeferredBlock<Block> raw_copal = register("raw_copal", PropertiesHandler.stoneProps(MapColor.GOLD, 2.0F, 20.0F, true));
    public static final DeferredBlock<Block> copal_bricks = register("copal_bricks", PropertiesHandler.copalProps());
    public static final DeferredBlock<SlabBlock> copal_brick_slab = register("copal_brick_slab", makeSlab(PropertiesHandler.copalProps()));
    public static final DeferredBlock<StairBlock> copal_brick_stairs = register("copal_brick_stairs", makeStairs(copal_bricks));
    public static final DeferredBlock<Block> cracked_copal_bricks = register("cracked_copal_bricks", PropertiesHandler.copalProps());
    public static final DeferredBlock<SlabBlock> cracked_copal_brick_slab = register("cracked_copal_brick_slab", makeSlab(PropertiesHandler.copalProps()));
    public static final DeferredBlock<StairBlock> cracked_copal_brick_stairs = register("cracked_copal_brick_stairs", makeStairs(cracked_copal_bricks));
    public static final DeferredBlock<Block> crusted_copal_bricks = register("crusted_copal_bricks", PropertiesHandler.copalProps());
    public static final DeferredBlock<SlabBlock> crusted_copal_brick_slab = register("crusted_copal_brick_slab", makeSlab(PropertiesHandler.copalProps()));
    public static final DeferredBlock<StairBlock> crusted_copal_brick_stairs = register("crusted_copal_brick_stairs", makeStairs(crusted_copal_bricks));
    public static final DeferredBlock<Block> raw_jet = register("raw_jet", PropertiesHandler.stoneProps(MapColor.COLOR_GRAY, 2.0F, 20.0F, true));
    public static final DeferredBlock<Block> jet_bricks = register("jet_bricks", PropertiesHandler.jetProps());
    public static final DeferredBlock<SlabBlock> jet_brick_slab = register("jet_brick_slab", makeSlab(PropertiesHandler.jetProps()));
    public static final DeferredBlock<StairBlock> jet_brick_stairs = register("jet_brick_stairs", makeStairs(jet_bricks));
    public static final DeferredBlock<Block> cracked_jet_bricks = register("cracked_jet_bricks", PropertiesHandler.jetProps());
    public static final DeferredBlock<SlabBlock> cracked_jet_brick_slab = register("cracked_jet_brick_slab", makeSlab(PropertiesHandler.jetProps()));
    public static final DeferredBlock<StairBlock> cracked_jet_brick_stairs = register("cracked_jet_brick_stairs", makeStairs(cracked_jet_bricks));
    public static final DeferredBlock<Block> crusted_jet_bricks = register("crusted_jet_bricks", PropertiesHandler.jetProps());
    public static final DeferredBlock<SlabBlock> crusted_jet_brick_slab = register("crusted_jet_brick_slab", makeSlab(PropertiesHandler.jetProps()));
    public static final DeferredBlock<StairBlock> crusted_jet_brick_stairs = register("crusted_jet_brick_stairs", makeStairs(crusted_jet_bricks));
    public static final DeferredBlock<Block> raw_amethyst = register("raw_amethyst", PropertiesHandler.stoneProps(MapColor.TERRACOTTA_PURPLE, 2.0F, 20.0F, true));
    public static final DeferredBlock<Block> amethyst_bricks = register("amethyst_bricks", PropertiesHandler.amethystProps());
    public static final DeferredBlock<SlabBlock> amethyst_brick_slab = register("amethyst_brick_slab", makeSlab(PropertiesHandler.amethystProps()));
    public static final DeferredBlock<StairBlock> amethyst_brick_stairs = register("amethyst_brick_stairs", makeStairs(amethyst_bricks));
    public static final DeferredBlock<Block> cracked_amethyst_bricks = register("cracked_amethyst_bricks", PropertiesHandler.amethystProps());
    public static final DeferredBlock<SlabBlock> cracked_amethyst_brick_slab = register("cracked_amethyst_brick_slab", makeSlab(PropertiesHandler.amethystProps()));
    public static final DeferredBlock<StairBlock> cracked_amethyst_brick_stairs = register("cracked_amethyst_brick_stairs", makeStairs(cracked_amethyst_bricks));
    public static final DeferredBlock<Block> crusted_amethyst_bricks = register("crusted_amethyst_bricks", PropertiesHandler.amethystProps());
    public static final DeferredBlock<SlabBlock> crusted_amethyst_brick_slab = register("crusted_amethyst_brick_slab", makeSlab(PropertiesHandler.amethystProps()));
    public static final DeferredBlock<StairBlock> crusted_amethyst_brick_stairs = register("crusted_amethyst_brick_stairs", makeStairs(crusted_amethyst_bricks));

    public static final DeferredBlock<Block> reinforced_bricks = register("reinforced_bricks", PropertiesHandler.stoneProps(MapColor.COLOR_PURPLE, 10.0F, 100.0F, true));
    public static final DeferredBlock<Block> bolstered_bricks = register("bolstered_bricks", PropertiesHandler.stoneProps(MapColor.SAND, 30.0F, 400.0F, true));
    public static final DeferredBlock<Block> malachite_bricks = register("malachite_bricks", PropertiesHandler.malachiteProps());
    public static final DeferredBlock<Block> malachite_cracked_bricks = register("malachite_cracked_bricks", PropertiesHandler.malachiteProps());
    public static final DeferredBlock<Block> malachite_crusted_bricks = register("malachite_crusted_bricks", PropertiesHandler.malachiteProps());
    public static final DeferredBlock<Block> malachite_tiles = register("malachite_tiles", PropertiesHandler.malachiteProps());
    public static final DeferredBlock<Block> malachite_chisel_bricks = register("malachite_chisel_bricks", PropertiesHandler.malachiteProps());
    public static final DeferredBlock<Block> malachite_pulsing_bricks = register("malachite_pulsing_bricks", PropertiesHandler.malachiteProps());
    public static final DeferredBlock<Block> malachite_pulsing_tiles = register("malachite_pulsing_tiles", PropertiesHandler.malachiteProps());
    public static final DeferredBlock<Block> malachite_pulsing_chisel = register("malachite_pulsing_chisel", PropertiesHandler.malachiteProps());
    public static final DeferredBlock<SlabBlock> malachite_brick_slab = register("malachite_brick_slab", makeSlab(PropertiesHandler.malachiteProps()));
    public static final DeferredBlock<SlabBlock> malachite_cracked_brick_slab = register("malachite_cracked_brick_slab", makeSlab(PropertiesHandler.malachiteProps()));
    public static final DeferredBlock<SlabBlock> malachite_crusted_brick_slab = register("malachite_crusted_brick_slab", makeSlab(PropertiesHandler.malachiteProps()));
    public static final DeferredBlock<SlabBlock> malachite_tile_slab = register("malachite_tile_slab", makeSlab(PropertiesHandler.malachiteProps()));
    public static final DeferredBlock<RotatedPillarBlock> malachite_pillar = register("malachite_pillar", () -> new RotatedPillarBlock(PropertiesHandler.malachiteProps()));
    public static final DeferredBlock<StairBlock> malachite_brick_stairs = register("malachite_brick_stairs", makeStairs(malachite_bricks));
    public static final DeferredBlock<StairBlock> malachite_cracked_brick_stairs = register("malachite_cracked_brick_stairs", makeStairs(malachite_cracked_bricks));
    public static final DeferredBlock<StairBlock> malachite_crusted_brick_stairs = register("malachite_crusted_brick_stairs", makeStairs(malachite_crusted_bricks));
    public static final DeferredBlock<StairBlock> malachite_tile_stairs = register("malachite_tile_stairs", makeStairs(malachite_tiles));
    public static final DeferredBlock<StairBlock> malachite_chisel_stairs = register("malachite_chisel_stairs", makeStairs(malachite_chisel_bricks));
    public static final DeferredBlock<StairBlock> malachite_pulsing_brick_stairs = register("malachite_pulsing_brick_stairs", makeStairs(malachite_pulsing_bricks));
    public static final DeferredBlock<StairBlock> malachite_pulsing_floor_stairs = register("malachite_pulsing_tile_stairs", makeStairs(malachite_pulsing_tiles));
    public static final DeferredBlock<StairBlock> malachite_pulsing_chisel_stairs = register("malachite_pulsing_chisel_stairs", makeStairs(malachite_pulsing_chisel));
    public static final DeferredBlock<StairBlock> malachite_pillar_stairs = register("malachite_pillar_stairs", makeStairs(malachite_pillar));

    //Storage Blocks
    public static final DeferredBlock<Block> sugilite_block = register("sugilite_block", PropertiesHandler.storageProps(MapColor.COLOR_PURPLE));
    public static final DeferredBlock<Block> hematite_block = register("hematite_block", PropertiesHandler.storageProps(MapColor.COLOR_GRAY));
    public static final DeferredBlock<Block> cinnabar_block = register("cinnabar_block", PropertiesHandler.storageProps(MapColor.COLOR_ORANGE));
    public static final DeferredBlock<Block> labradorite_block = register("labradorite_block", PropertiesHandler.storageProps(MapColor.COLOR_GREEN));
    public static final DeferredBlock<Block> moonstone_block = register("moonstone_block", PropertiesHandler.storageProps(MapColor.METAL));
    public static final DeferredBlock<Block> red_opal_block = register("red_opal_block", PropertiesHandler.storageProps(MapColor.COLOR_RED));
    public static final DeferredBlock<Block> blue_opal_block = register("blue_opal_block", PropertiesHandler.storageProps(MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredBlock<Block> green_opal_block = register("green_opal_block", PropertiesHandler.storageProps(MapColor.COLOR_LIGHT_GREEN));
    public static final DeferredBlock<Block> white_opal_block = register("white_opal_block", PropertiesHandler.storageProps(MapColor.SNOW));
    public static final DeferredBlock<Block> pyrite_block = register("pyrite_block", PropertiesHandler.storageProps(MapColor.GOLD).lightLevel((state) -> 15));
    public static final DeferredBlock<Block> tektite_block = register("tektite_block", PropertiesHandler.storageProps(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> goldstone_block = register("goldstone_block", PropertiesHandler.storageProps(MapColor.COLOR_BLACK));
    public static final DeferredBlock<Block> aura_block = register("aura_block", PropertiesHandler.storageProps(MapColor.ICE));
    public static final DeferredBlock<Block> bismuth_block = register("bismuth_block", PropertiesHandler.storageProps(MapColor.PODZOL));
    public static final DeferredBlock<Block> opalite_block = register("opalite_block", PropertiesHandler.storageProps(MapColor.COLOR_LIGHT_BLUE));
    public static final DeferredBlock<Block> stibnite_block = register("stibnite_block", PropertiesHandler.storageProps(MapColor.COLOR_GRAY));
    public static final DeferredBlock<Block> proustite_block = register("proustite_block", PropertiesHandler.storageProps(MapColor.COLOR_MAGENTA));
    public static final DeferredBlock<Block> euclase_block = register("euclase_block", PropertiesHandler.storageProps(MapColor.COLOR_LIGHT_GREEN));
    public static final DeferredBlock<Block> albite_block = register("albite_block", PropertiesHandler.storageProps(MapColor.SAND));
    public static final DeferredBlock<Block> carnelian_block = register("carnelian_block", PropertiesHandler.storageProps(MapColor.COLOR_RED));
    public static final DeferredBlock<Block> benitoite_block = register("benitoite_block", PropertiesHandler.storageProps(MapColor.COLOR_BLUE));
    public static final DeferredBlock<Block> diopside_block = register("diopside_block", PropertiesHandler.storageProps(MapColor.COLOR_LIGHT_GREEN));
    public static final DeferredBlock<Block> goshenite_block = register("goshenite_block", PropertiesHandler.storageProps(MapColor.SNOW));
    public static final DeferredBlock<Block> celestine_block = register("celestine_block", PropertiesHandler.storageProps(MapColor.COLOR_LIGHT_BLUE));

    //Ores
    public static final DeferredBlock<Block> sugilite_ore = register("sugilite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 3), PropertiesHandler.oreProps(MapColor.COLOR_PURPLE)));
    public static final DeferredBlock<Block> hematite_ore = register("hematite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 4), PropertiesHandler.oreProps(MapColor.COLOR_GRAY)));
    public static final DeferredBlock<Block> cinnabar_ore = register("cinnabar_ore", () -> new DropExperienceBlock(UniformInt.of(1, 4), PropertiesHandler.oreProps(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> labradorite_ore = register("labradorite_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), PropertiesHandler.oreProps(MapColor.COLOR_GREEN)));
    public static final DeferredBlock<Block> moonstone_ore = register("moonstone_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), PropertiesHandler.oreProps(MapColor.METAL)));
    public static final DeferredBlock<Block> red_opal_ore = register("red_opal_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), PropertiesHandler.oreProps(MapColor.COLOR_RED)));
    public static final DeferredBlock<Block> blue_opal_ore = register("blue_opal_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), PropertiesHandler.oreProps(MapColor.COLOR_LIGHT_BLUE)));
    public static final DeferredBlock<Block> green_opal_ore = register("green_opal_ore", () -> new DropExperienceBlock(UniformInt.of(2, 5), PropertiesHandler.oreProps(MapColor.COLOR_LIGHT_GREEN)));
    public static final DeferredBlock<Block> white_opal_ore = register("white_opal_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), PropertiesHandler.oreProps(MapColor.SNOW)));
    public static final DeferredBlock<Block> pyrite_ore = register("pyrite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 4), PropertiesHandler.oreProps(MapColor.GOLD).lightLevel((state) -> 3)));
    public static final DeferredBlock<Block> speckled_rock = register("speckled_rock", () -> new Block(PropertiesHandler.oreProps(MapColor.COLOR_MAGENTA)));
    public static final DeferredBlock<Block> coarse_rock = register("coarse_rock", () -> new Block(PropertiesHandler.oreProps(MapColor.COLOR_MAGENTA)));
    public static final DeferredBlock<Block> precious_rock = register("precious_rock", () -> new Block(PropertiesHandler.oreProps(MapColor.COLOR_MAGENTA)));
    public static final DeferredBlock<Block> scarlet_opalite_ore = register("scarlet_opalite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 2), PropertiesHandler.oreProps(MapColor.TERRACOTTA_RED)));
    public static final DeferredBlock<Block> auburn_opalite_ore = register("auburn_opalite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 2), PropertiesHandler.oreProps(MapColor.TERRACOTTA_ORANGE)));
    public static final DeferredBlock<Block> gold_opalite_ore = register("gold_opalite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 2), PropertiesHandler.oreProps(MapColor.TERRACOTTA_YELLOW)));
    public static final DeferredBlock<Block> mauve_opalite_ore = register("mauve_opalite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 2), PropertiesHandler.oreProps(MapColor.TERRACOTTA_PURPLE)));
    public static final DeferredBlock<Block> beige_opalite_ore = register("beige_opalite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 2), PropertiesHandler.oreProps(MapColor.SAND)));
    public static final DeferredBlock<Block> ivory_opalite_ore = register("ivory_opalite_ore", () -> new DropExperienceBlock(UniformInt.of(1, 2), PropertiesHandler.oreProps(MapColor.TERRACOTTA_WHITE)));
    public static final DeferredBlock<Block> celestine_ore = register("celestine_ore", () -> new DropExperienceBlock(UniformInt.of(2, 4), PropertiesHandler.oreProps(MapColor.COLOR_LIGHT_BLUE)));

    //Saplings, to force my hand
    public static final DeferredBlock<SaplingBlock> pink_agate_sapling = register("pink_agate_sapling", () -> new GaiaSaplingBlock(GaiaFeatures.Trees.PINK_AGATE, PropertiesHandler.saplingProps(MapColor.COLOR_PINK)));
    public static final DeferredBlock<SaplingBlock> blue_agate_sapling = register("blue_agate_sapling", () -> new GaiaSaplingBlock(GaiaFeatures.Trees.BLUE_AGATE, PropertiesHandler.saplingProps(MapColor.COLOR_LIGHT_BLUE)));
    public static final DeferredBlock<SaplingBlock> green_agate_sapling = register("green_agate_sapling", () -> new GaiaSaplingBlock(GaiaFeatures.Trees.GREEN_AGATE, PropertiesHandler.saplingProps(MapColor.COLOR_LIGHT_GREEN)));
    public static final DeferredBlock<SaplingBlock> purple_agate_sapling = register("purple_agate_sapling", () -> new GaiaSaplingBlock(GaiaFeatures.Trees.PURPLE_AGATE, PropertiesHandler.saplingProps(MapColor.TERRACOTTA_PURPLE)));
    public static final DeferredBlock<SaplingBlock> fossilized_sapling = register("fossilized_sapling", () -> new GaiaSaplingBlock(GaiaFeatures.Trees.FOSSILIZED, PropertiesHandler.saplingProps(MapColor.TERRACOTTA_YELLOW)));
    public static final DeferredBlock<SaplingBlock> corrupted_sapling = register("corrupted_sapling", () -> new GaiaSaplingBlock(GaiaFeatures.Trees.GOLDSTONE, PropertiesHandler.saplingProps(MapColor.TERRACOTTA_BLACK)));
    public static final DeferredBlock<SaplingBlock> burnt_sapling = register("burnt_agate_sapling", () -> new GaiaSaplingBlock(GaiaFeatures.Trees.BURNT_AGATE, PropertiesHandler.saplingProps(MapColor.COLOR_BLACK)));
    public static final DeferredBlock<SaplingBlock> fire_agate_sapling = register("fire_agate_sapling", () -> new GaiaSaplingBlock(GaiaFeatures.Trees.FIERY_AGATE, PropertiesHandler.saplingProps(MapColor.TERRACOTTA_ORANGE)), 100);
    public static final DeferredBlock<SaplingBlock> aura_sapling = register("aura_sapling", () -> new GaiaSaplingBlock(GaiaFeatures.Trees.AURA, PropertiesHandler.saplingProps(MapColor.SNOW)));
    public static final DeferredBlock<SaplingBlock> golden_sapling = register("golden_sapling", () -> new GaiaSaplingBlock(GaiaFeatures.Trees.GOLDEN, PropertiesHandler.saplingProps(MapColor.GOLD)));

    //Flower Pots
    public static final DeferredBlock<FlowerPotBlock> potted_thiscus = registerFlowerPot(thiscus);
    public static final DeferredBlock<FlowerPotBlock> potted_ouzium = registerFlowerPot(ouzium);
    public static final DeferredBlock<FlowerPotBlock> potted_agathum = registerFlowerPot(agathum);
    public static final DeferredBlock<FlowerPotBlock> potted_varloom = registerFlowerPot(varloom);
    public static final DeferredBlock<FlowerPotBlock> potted_corrupted_varloom = registerFlowerPot(corrupted_varloom);
    public static final DeferredBlock<FlowerPotBlock> potted_missingno_plant = registerFlowerPot(missingno_plant);
    public static final DeferredBlock<FlowerPotBlock> potted_spotted_kersei = registerFlowerPot(spotted_kersei);
    public static final DeferredBlock<FlowerPotBlock> potted_thorny_wiltha = registerFlowerPot(thorny_wiltha);
    public static final DeferredBlock<FlowerPotBlock> potted_roofed_agaric = registerFlowerPot(roofed_agaric);
    public static final DeferredBlock<FlowerPotBlock> potted_bulbous_hobina = registerFlowerPot(bulbous_hobina);
    public static final DeferredBlock<FlowerPotBlock> potted_stickly_cupsir = registerFlowerPot(stickly_cupsir);
    public static final DeferredBlock<FlowerPotBlock> potted_mystical_murgni = registerFlowerPot(mystical_murgni);
    public static final DeferredBlock<FlowerPotBlock> potted_corrupted_gaia_eye = registerFlowerPot(corrupted_gaia_eye);
    public static final DeferredBlock<FlowerPotBlock> potted_twinkling_gilsri = registerFlowerPot(twinkling_gilsri);
    public static final DeferredBlock<FlowerPotBlock> potted_elder_imklia = registerFlowerPot(elder_imklia);
    public static final DeferredBlock<FlowerPotBlock> potted_gold_orb_tucher = registerFlowerPot(gold_orb_tucher);
    public static final DeferredBlock<FlowerPotBlock> potted_missingno_fungus = registerFlowerPot(missingno_fungus);
    public static final DeferredBlock<FlowerPotBlock> potted_pink_agate_sapling = registerFlowerPot(pink_agate_sapling);
    public static final DeferredBlock<FlowerPotBlock> potted_blue_agate_sapling = registerFlowerPot(blue_agate_sapling);
    public static final DeferredBlock<FlowerPotBlock> potted_green_agate_sapling = registerFlowerPot(green_agate_sapling);
    public static final DeferredBlock<FlowerPotBlock> potted_purple_agate_sapling = registerFlowerPot(purple_agate_sapling);
    public static final DeferredBlock<FlowerPotBlock> potted_fossilized_sapling = registerFlowerPot(fossilized_sapling);
    public static final DeferredBlock<FlowerPotBlock> potted_corrupted_sapling = registerFlowerPot(corrupted_sapling);
    public static final DeferredBlock<FlowerPotBlock> potted_burnt_sapling = registerFlowerPot(burnt_sapling);
    public static final DeferredBlock<FlowerPotBlock> potted_fire_agate_sapling = registerFlowerPot(fire_agate_sapling);
    public static final DeferredBlock<FlowerPotBlock> potted_aura_sapling = registerFlowerPot(aura_sapling);
    public static final DeferredBlock<FlowerPotBlock> potted_golden_sapling = registerFlowerPot(golden_sapling);

    //Spawners
    public static final DeferredBlock<BossSpawnerBlock> malachite_guard_spawner = registerNoItem("malachite_guard_spawner", () -> new BossSpawnerBlock(BossSpawnerBlock.BossType.MALACHITE, PropertiesHandler.spawnerProps()));

    private static Supplier<StairBlock> makeStairs(Supplier<? extends Block> block) {
        return () -> new StairBlock(block.get().defaultBlockState(), Properties.ofLegacyCopy(block.get()));
    }

    private static Supplier<SlabBlock> makeSlab(Properties props) {
        return () -> new SlabBlock(props);
    }

    private static DeferredBlock<Block> register(String name, Properties props) {
        return register(name, props, 0);
    }

    private static DeferredBlock<Block> register(String name, Properties props, int burn) {
        return register(name, () -> new Block(props), burn);
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Supplier<? extends T> block) {
        return register(name, block, 0);
    }

    private static <T extends Block> DeferredBlock<T> register(String name, Supplier<? extends T> block, int burnTime) {
        return registerBlock(name, block, item -> registerBlockItemFuel(item, burnTime));
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<? extends T> block, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
        DeferredBlock<T> reg = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemFuel(final Supplier<T> block, int burnTime) {
        return () -> new BlockItem(block.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(ItemStack itemStack, RecipeType<?> type) {
                return burnTime;
            }
        };
    }

    private static DeferredBlock<FlowerPotBlock> registerFlowerPot(DeferredBlock<? extends Block> plant) {
        return registerNoItem("potted_" + plant.getId().getPath(), () ->
                new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, plant, Block.Properties.of().strength(0.0F)));
    }

    private static <T extends Block> DeferredBlock<T> registerNoItem(String name, Supplier<? extends T> block) {
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
        block.addPlant(twinkling_gilsri.getId(), potted_twinkling_gilsri);
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
        block.addPlant(fire_agate_sapling.getId(), potted_fire_agate_sapling);
        block.addPlant(aura_sapling.getId(), potted_aura_sapling);
        block.addPlant(golden_sapling.getId(), potted_golden_sapling);
    }

    public static void addStripping() {
        Map<Block, Block> STRIPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);

        addToMap(STRIPABLES, pink_agate_log, stripped_pink_agate_log);
        addToMap(STRIPABLES, blue_agate_log, stripped_blue_agate_log);
        addToMap(STRIPABLES, green_agate_log, stripped_green_agate_log);
        addToMap(STRIPABLES, purple_agate_log, stripped_purple_agate_log);
        addToMap(STRIPABLES, fossilized_log, stripped_fossilized_log);
        addToMap(STRIPABLES, corrupted_log, stripped_corrupted_log);
        addToMap(STRIPABLES, burnt_log, stripped_burnt_log);
        addToMap(STRIPABLES, fire_agate_log, stripped_fire_agate_log);
        addToMap(STRIPABLES, aura_log, stripped_aura_log);
        addToMap(STRIPABLES, golden_log, stripped_golden_log);
        addToMap(STRIPABLES, pink_agate_wood, stripped_pink_agate_wood);
        addToMap(STRIPABLES, blue_agate_wood, stripped_blue_agate_wood);
        addToMap(STRIPABLES, green_agate_wood, stripped_green_agate_wood);
        addToMap(STRIPABLES, purple_agate_wood, stripped_purple_agate_wood);
        addToMap(STRIPABLES, fossilized_wood, stripped_fossilized_wood);
        addToMap(STRIPABLES, corrupted_wood, stripped_corrupted_wood);
        addToMap(STRIPABLES, burnt_wood, stripped_burnt_wood);
        addToMap(STRIPABLES, fire_agate_wood, stripped_fire_agate_wood);
        addToMap(STRIPABLES, aura_wood, stripped_aura_wood);
        addToMap(STRIPABLES, golden_wood, stripped_golden_wood);

        AxeItem.STRIPPABLES = STRIPABLES;
    }

    private static void addToMap(Map<Block, Block> map, Supplier<? extends Block> original, Supplier<? extends Block> newstate) {
        map.put(original.get(), newstate.get());
    }

    public static void registerDispenserBehaviour() {
        DispenseItemBehavior dispenseFluid = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultBehaviour = new DefaultDispenseItemBehavior();

            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                ScaynyxBucketItem bucketitem = (ScaynyxBucketItem)stack.getItem();
                BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                Level world = source.level();
                if (bucketitem.emptyContents(null, world, blockpos, null, stack)) {
                    bucketitem.checkExtraContent(null, world, stack, blockpos);
                    return new ItemStack(ModItems.scaynyx_bucket.get());
                } else {
                    return this.defaultBehaviour.dispense(source, stack);
                }
            }
        };

        DispenserBlock.registerBehavior(ModItems.mineral_water_bucket.get(), dispenseFluid);
        DispenserBlock.registerBehavior(ModItems.superhot_magma_bucket.get(), dispenseFluid);
        DispenserBlock.registerBehavior(ModItems.sweet_muck_bucket.get(), dispenseFluid);
        DispenserBlock.registerBehavior(ModItems.liquid_bismuth_bucket.get(), dispenseFluid);
        DispenserBlock.registerBehavior(ModItems.liquid_aura_bucket.get(), dispenseFluid);
        DispenserBlock.registerBehavior(ModItems.scaynyx_bucket.get(), new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultBehaviour = new DefaultDispenseItemBehavior();

            @Override
            public ItemStack execute(BlockSource source, ItemStack stack) {
                LevelAccessor iworld = source.level();
                BlockPos blockpos = source.pos().relative(source.state().getValue(DispenserBlock.FACING));
                BlockState blockstate = iworld.getBlockState(blockpos);
                if (blockstate.getBlock() instanceof BucketPickup block) {
                    ItemStack fluid = block.pickupBlock(null, iworld, blockpos, blockstate);
                    if (fluid.isEmpty()) {
                        return super.execute(source, stack);
                    } else {
                        iworld.gameEvent(null, GameEvent.FLUID_PICKUP, blockpos);
                        Item item = fluid.getItem();
                        stack.shrink(1);
                        if (stack.isEmpty()) {
                            return new ItemStack(item);
                        } else {
                            if (!source.blockEntity().insertItem(new ItemStack(item)).isEmpty()) {
                                this.defaultBehaviour.dispense(source, new ItemStack(item)); //TODO
                            }

                            return stack;
                        }
                    }
                } else {
                    return super.execute(source, stack);
                }
            }
        });
    }
}