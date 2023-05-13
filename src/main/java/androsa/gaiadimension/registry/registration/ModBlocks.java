package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.*;
import androsa.gaiadimension.item.ScaynyxBucketItem;
import androsa.gaiadimension.registry.helpers.PropertiesHandler;
import androsa.gaiadimension.world.gen.feature.tree.*;
import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class ModBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GaiaDimensionMod.MODID);

    //Utility Blocks
    public static final RegistryObject<GaiaPortalBlock> gaia_portal = registerNoItem("gaia_portal", () ->
            new GaiaPortalBlock(PropertiesHandler.stoneProps(Material.PORTAL, MaterialColor.TERRACOTTA_PINK, -1.0F, -1.0F, false).noCollission().randomTicks().lightLevel((state) -> 15)));
    public static final RegistryObject<Block> keystone_block = register("keystone_block",
            PropertiesHandler.basicProps(Material.METAL, MaterialColor.GOLD, SoundType.METAL, 5.0F, 10.0F).requiresCorrectToolForDrops());
    public static final RegistryObject<Block> gold_fire = registerNoItem("gold_fire", () ->
            new GoldFireBlock(Properties.of(Material.FIRE, MaterialColor.GOLD).strength(0.0F).noCollission().randomTicks().lightLevel((state) -> 15)));
    public static final RegistryObject<Block> pyrite_torch = registerNoItem("pyrite_torch", () -> new PyriteTorchBlock(PropertiesHandler.torchProps()));
    public static final RegistryObject<Block> pyrite_wall_torch = registerNoItem("pyrite_wall_torch", () -> new PyriteWallTorchBlock(PropertiesHandler.torchProps().lootFrom(pyrite_torch)));
    public static final RegistryObject<Block> agate_crafting_table = register("agate_crafting_table", () ->
            new AgateCraftingTableBlock(PropertiesHandler.stoneProps(Material.WOOD, MaterialColor.TERRACOTTA_PINK, 1.5F, 2.0F, false)));
    public static final RegistryObject<Block> crude_storage_crate = register("crude_storage_crate", () ->
            new SmallCrateBlock(PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_PINK, 10.0F, 150.0F)));
    public static final RegistryObject<Block> mega_storage_crate = register("mega_storage_crate", () ->
            new LargeCrateBlock(PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_PURPLE, 10.0F, 300.0F)));
    public static final RegistryObject<Block> gaia_stone_furnace = register("gaia_stone_furnace", () ->
            new GaiaStoneFurnaceBlock(PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_PINK, 20.0F, 300.0F, true).lightLevel((state) -> state.getValue(AbstractFurnaceBlock.LIT) ? 13 : 0)));
    public static final RegistryObject<Block> restructurer = register("restructurer", () ->
            new RestructurerBlock(PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_PURPLE, 20.0F, 300.0F, true).lightLevel((state) -> state.getValue(RestructurerBlock.LIT) ? 14 : 0)));
    public static final RegistryObject<Block> purifier = register("purifier", () ->
            new PurifierBlock(PropertiesHandler.stoneProps(MaterialColor.SAND, 20.0F, 300.0F, true).lightLevel((state) -> state.getValue(PurifierBlock.LIT) ? 14 : 0)));

    //Fluids
    public static final RegistryObject<LiquidBlock> mineral_water = registerNoItem("mineral_water", () ->
            new GaiaFluidBlock(ModFluids.mineral_water_still, Block.Properties.of(Material.WATER, MaterialColor.TERRACOTTA_LIGHT_BLUE)));
    public static final RegistryObject<LiquidBlock> superhot_magma = registerNoItem("superhot_magma", () ->
            new GaiaFluidBlock(ModFluids.superhot_magma_still, Block.Properties.of(Material.LAVA, MaterialColor.COLOR_BLUE).randomTicks().lightLevel((state) -> 15)));
    public static final RegistryObject<LiquidBlock> sweet_muck = registerNoItem("sweet_muck", () ->
            new GaiaFluidBlock(ModFluids.sweet_muck_still, Block.Properties.of(Material.WATER, MaterialColor.COLOR_PURPLE)));
    public static final RegistryObject<LiquidBlock> liquid_bismuth = registerNoItem("liquid_bismuth", () ->
            new GaiaFluidBlock(ModFluids.liquid_bismuth_still, Block.Properties.of(Material.LAVA).randomTicks().lightLevel((state) -> 3)));
    public static final RegistryObject<LiquidBlock> liquid_aura = registerNoItem("liquid_aura", () ->
            new GaiaFluidBlock(ModFluids.liquid_aura_still, Block.Properties.of(Material.WATER)));

    //Natural Blocks
    public static final RegistryObject<Block> heavy_soil = register("heavy_soil", () -> new GaiaSoilBlock(PropertiesHandler.soilProps(MaterialColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<Block> corrupt_soil = register("corrupt_soil", () -> new GaiaSoilBlock(PropertiesHandler.soilProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<Block> boggy_soil = register("boggy_soil", () -> new GaiaSoilBlock(PropertiesHandler.soilProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<Block> light_soil = register("light_soil", () -> new GaiaSoilBlock(PropertiesHandler.soilProps(MaterialColor.GOLD)));
    public static final RegistryObject<Block> aurum_soil = register("aurum_soil", () -> new GaiaSoilBlock(PropertiesHandler.soilProps(MaterialColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<Block> glitter_grass = register("glitter_grass", () -> new GlitterGrassBlock(PropertiesHandler.grassProps(MaterialColor.COLOR_PINK)));
    public static final RegistryObject<Block> corrupt_grass = register("corrupt_grass", () -> new CorruptGrassBlock(PropertiesHandler.grassProps(MaterialColor.COLOR_BLACK)));
    public static final RegistryObject<Block> murky_grass = register("murky_grass", () -> new MurkyGrassBlock(PropertiesHandler.grassProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<Block> soft_grass = register("soft_grass", () -> new SoftGrassBlock(PropertiesHandler.grassProps(MaterialColor.COLOR_CYAN)));
    public static final RegistryObject<Block> gilded_grass = register("gilded_grass", () -> new GildedGrassBlock(PropertiesHandler.grassProps(MaterialColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<Block> frail_glitter_block = register("frail_glitter_block", () -> new GlassBlock(PropertiesHandler.glassProps(MaterialColor.COLOR_PINK, 1.0F)));
    public static final RegistryObject<Block> thick_glitter_block = register("thick_glitter_block", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_PURPLE, 1.5F, 7.5F, true));
    public static final RegistryObject<Block> gummy_glitter_block = register("gummy_glitter_block", () -> new SlimeBlock(Properties.of(Material.CLAY, MaterialColor.COLOR_PURPLE).sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> pink_sludge_block = register("pink_sludge_block", () -> new SlimeBlock(Properties.of(Material.CLAY, MaterialColor.COLOR_PINK).sound(SoundType.SLIME_BLOCK)));

    //Plants
    public static final RegistryObject<Block> crystal_growth = register("crystal_growth", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MaterialColor.SNOW, true)));
    public static final RegistryObject<Block> crystal_growth_red = register("crystal_growth_red", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MaterialColor.COLOR_RED, true)));
    public static final RegistryObject<Block> crystal_growth_black = register("crystal_growth_black", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MaterialColor.COLOR_BLACK, true)));
    public static final RegistryObject<Block> crystal_growth_seared = register("crystal_growth_seared", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MaterialColor.COLOR_BLACK, true)));
    public static final RegistryObject<Block> crystal_growth_mutant = register("crystal_growth_mutant", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MaterialColor.TERRACOTTA_WHITE, true)));
    public static final RegistryObject<Block> crystal_growth_aura = register("crystal_growth_aura", () -> new CrystalGrowthBlock(PropertiesHandler.plantProps(MaterialColor.TERRACOTTA_LIGHT_BLUE, true)));
    public static final RegistryObject<Block> golden_grass = register("golden_grass", () -> new GoldenGrassBlock(PropertiesHandler.plantProps(MaterialColor.GOLD, false)));
    public static final RegistryObject<Block> tall_golden_grass = register("tall_golden_grass", () -> new DoubleCrystalGrowthBlock(PropertiesHandler.plantProps(MaterialColor.GOLD, false)));
    public static final RegistryObject<Block> thiscus = register("thiscus", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final RegistryObject<Block> ouzium = register("ouzium", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final RegistryObject<Block> agathum = register("agathum", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final RegistryObject<Block> varloom = register("varloom", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final RegistryObject<Block> corrupted_varloom = register("corrupted_varloom", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final RegistryObject<Block> glamelea = register("glamelea", () -> new GlameleaBlock(PropertiesHandler.bloomProps()));
    public static final RegistryObject<Block> missingno_plant = register("missingno_plant", () -> new CrystalBloomBlock(PropertiesHandler.bloomProps()));
    public static final RegistryObject<Block> spotted_kersei = register("spotted_kersei", () -> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.COLOR_PINK, false), false));
    public static final RegistryObject<Block> thorny_wiltha = register("thorny_wiltha", () -> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.COLOR_LIGHT_BLUE, false), false));
    public static final RegistryObject<Block> roofed_agaric = register("roofed_agaric", () -> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.COLOR_LIGHT_GREEN, false), false));
    public static final RegistryObject<Block> bulbous_hobina = register("bulbous_hobina", () -> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.TERRACOTTA_PINK, false), false));
    public static final RegistryObject<Block> stickly_cupsir = register("stickly_cupsir", () -> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.TERRACOTTA_YELLOW, false), false));
    public static final RegistryObject<Block> mystical_murgni = register("mystical_murgni", () -> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.GOLD, false), false));
    public static final RegistryObject<Block> corrupted_gaia_eye = register("corrupted_gaia_eye", () -> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.FIRE, false), false));
    public static final RegistryObject<Block> twinkling_gilsri = register("twinkling_gilsri", ()-> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.GOLD, false), false));
    //public static final RegistryObject<Block> sacred_gaia_eye = RegistryHelper.registerBlock()("sacred_gaia_eye", new CrystalFungusBlock(false));
    public static final RegistryObject<Block> elder_imklia = register("elder_imklia", () -> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.COLOR_PURPLE, false), true));
    public static final RegistryObject<Block> gold_orb_tucher = register("gold_orb_tucher", () -> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.GOLD, false), true));
    public static final RegistryObject<Block> missingno_fungus = register("missingno_fungus", () -> new CrystalFungusBlock(PropertiesHandler.plantProps(MaterialColor.COLOR_MAGENTA, false), false));
    public static final RegistryObject<Block> golden_vine = register("golden_vine", () -> new VineBlock(PropertiesHandler.plantProps(MaterialColor.GOLD, false)));
    public static final RegistryObject<Block> sombre_cacti = register("sombre_cacti", () -> new SombreCactiBlock(PropertiesHandler.plantProps(MaterialColor.TERRACOTTA_BROWN, false))); //TODO DEBUG
    public static final RegistryObject<Block> sombre_shrub = register("sombre_shrub", () -> new SombreShrubBlock(PropertiesHandler.plantProps(MaterialColor.TERRACOTTA_BROWN, false)));

    //Tree Blocks
    public static final RegistryObject<SaplingBlock> pink_agate_sapling = register("pink_agate_sapling", () -> new GaiaSaplingBlock(new PinkAgateTree(), PropertiesHandler.saplingProps(MaterialColor.COLOR_PINK)));
    public static final RegistryObject<SaplingBlock> blue_agate_sapling = register("blue_agate_sapling", () -> new GaiaSaplingBlock(new BlueAgateTree(), PropertiesHandler.saplingProps(MaterialColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<SaplingBlock> green_agate_sapling = register("green_agate_sapling", () -> new GaiaSaplingBlock(new GreenAgateTree(), PropertiesHandler.saplingProps(MaterialColor.COLOR_LIGHT_GREEN)));
    public static final RegistryObject<SaplingBlock> purple_agate_sapling = register("purple_agate_sapling", () -> new GaiaSaplingBlock(new PurpleAgateTree(), PropertiesHandler.saplingProps(MaterialColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<SaplingBlock> fossilized_sapling = register("fossilized_sapling", () -> new GaiaSaplingBlock(new FossilizedTree(), PropertiesHandler.saplingProps(MaterialColor.TERRACOTTA_YELLOW)));
    public static final RegistryObject<SaplingBlock> corrupted_sapling = register("corrupted_sapling", () -> new GaiaSaplingBlock(new GoldstoneCorruptTree(), PropertiesHandler.saplingProps(MaterialColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<SaplingBlock> burnt_sapling = register("burnt_sapling", () -> new GaiaSaplingBlock(new BurntAgateTree(), PropertiesHandler.saplingProps(MaterialColor.COLOR_BLACK)));
    public static final RegistryObject<SaplingBlock> burning_sapling = register("burning_sapling", () -> new GaiaSaplingBlock(new FieryAgateTree(), PropertiesHandler.saplingProps(MaterialColor.TERRACOTTA_ORANGE)), 100);
    public static final RegistryObject<SaplingBlock> aura_sapling = register("aura_sapling", () -> new GaiaSaplingBlock(new AuraTree(), PropertiesHandler.saplingProps(MaterialColor.SNOW)));
    public static final RegistryObject<SaplingBlock> golden_sapling = register("golden_sapling", () -> new GaiaSaplingBlock(new GoldenTree(), PropertiesHandler.saplingProps(MaterialColor.GOLD)));
    public static final RegistryObject<Block> pink_agate_leaves = register("pink_agate_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MaterialColor.COLOR_MAGENTA)));
    public static final RegistryObject<Block> blue_agate_leaves = register("blue_agate_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MaterialColor.COLOR_BLUE)));
    public static final RegistryObject<Block> green_agate_leaves = register("green_agate_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MaterialColor.COLOR_GREEN)));
    public static final RegistryObject<Block> purple_agate_leaves = register("purple_agate_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MaterialColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<Block> fossilized_leaves = register("fossilized_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MaterialColor.COLOR_YELLOW)));
    public static final RegistryObject<Block> corrupted_leaves = register("corrupted_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MaterialColor.FIRE)));
    public static final RegistryObject<Block> burnt_leaves = register("burnt_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<Block> burning_leaves = register("burning_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MaterialColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 200);
    public static final RegistryObject<Block> aura_leaves = register("aura_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MaterialColor.METAL)));
    public static final RegistryObject<Block> golden_leaves = register("golden_leaves", () -> new LeavesBlock(PropertiesHandler.leavesProps(MaterialColor.GOLD)));
    public static final RegistryObject<RotatedPillarBlock> pink_agate_log = register("pink_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_MAGENTA, MaterialColor.TERRACOTTA_PINK)));
    public static final RegistryObject<RotatedPillarBlock> blue_agate_log = register("blue_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_BLUE, MaterialColor.TERRACOTTA_BLUE)));
    public static final RegistryObject<RotatedPillarBlock> green_agate_log = register("green_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_GREEN, MaterialColor.TERRACOTTA_LIGHT_GREEN)));
    public static final RegistryObject<RotatedPillarBlock> purple_agate_log = register("purple_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.TERRACOTTA_PURPLE, MaterialColor.COLOR_PURPLE)));
    public static final RegistryObject<RotatedPillarBlock> fossilized_log = register("fossilized_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_YELLOW, MaterialColor.DIRT)));
    public static final RegistryObject<RotatedPillarBlock> corrupted_log = register("corrupted_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.FIRE, MaterialColor.TERRACOTTA_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> burnt_log = register("burnt_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_GRAY, MaterialColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<RotatedPillarBlock> burning_log = register("burning_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_ORANGE, MaterialColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 1600);
    public static final RegistryObject<RotatedPillarBlock> aura_log = register("aura_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.METAL, MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> golden_log = register("golden_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.GOLD, MaterialColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<RotatedPillarBlock> stripped_pink_agate_log = register("stripped_pink_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_MAGENTA)));
    public static final RegistryObject<RotatedPillarBlock> stripped_blue_agate_log = register("stripped_blue_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_BLUE)));
    public static final RegistryObject<RotatedPillarBlock> stripped_green_agate_log = register("stripped_green_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_GREEN)));
    public static final RegistryObject<RotatedPillarBlock> stripped_purple_agate_log = register("stripped_purple_agate_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<RotatedPillarBlock> stripped_fossilized_log = register("stripped_fossilized_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_YELLOW)));
    public static final RegistryObject<RotatedPillarBlock> stripped_corrupted_log = register("stripped_corrupted_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.FIRE)));
    public static final RegistryObject<RotatedPillarBlock> stripped_burnt_log = register("stripped_burnt_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> stripped_burning_log = register("stripped_burning_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_ORANGE).lightLevel((state) -> 3)), 1600);
    public static final RegistryObject<RotatedPillarBlock> stripped_aura_log = register("stripped_aura_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.METAL)));
    public static final RegistryObject<RotatedPillarBlock> stripped_golden_log = register("stripped_golden_log", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.GOLD)));
    public static final RegistryObject<RotatedPillarBlock> pink_agate_wood = register("pink_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.TERRACOTTA_PINK)));
    public static final RegistryObject<RotatedPillarBlock> blue_agate_wood = register("blue_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.TERRACOTTA_BLUE)));
    public static final RegistryObject<RotatedPillarBlock> green_agate_wood = register("green_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.TERRACOTTA_LIGHT_GREEN)));
    public static final RegistryObject<RotatedPillarBlock> purple_agate_wood = register("purple_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_PURPLE)));
    public static final RegistryObject<RotatedPillarBlock> fossilized_wood = register("fossilized_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.DIRT)));
    public static final RegistryObject<RotatedPillarBlock> corrupted_wood = register("corrupted_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.TERRACOTTA_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> burnt_wood = register("burnt_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<RotatedPillarBlock> burning_wood = register("burning_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 1600);
    public static final RegistryObject<RotatedPillarBlock> aura_wood = register("aura_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> golden_wood = register("golden_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.TERRACOTTA_BROWN)));
    public static final RegistryObject<RotatedPillarBlock> stripped_pink_agate_wood = register("stripped_pink_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_MAGENTA)));
    public static final RegistryObject<RotatedPillarBlock> stripped_blue_agate_wood = register("stripped_blue_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_BLUE)));
    public static final RegistryObject<RotatedPillarBlock> stripped_green_agate_wood = register("stripped_green_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_GREEN)));
    public static final RegistryObject<RotatedPillarBlock> stripped_purple_agate_wood = register("stripped_purple_agate_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<RotatedPillarBlock> stripped_fossilized_wood = register("stripped_fossilized_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_YELLOW)));
    public static final RegistryObject<RotatedPillarBlock> stripped_corrupted_wood = register("stripped_corrupted_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.FIRE)));
    public static final RegistryObject<RotatedPillarBlock> stripped_burnt_wood = register("stripped_burnt_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_GRAY)));
    public static final RegistryObject<RotatedPillarBlock> stripped_burning_wood = register("stripped_burning_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.COLOR_ORANGE).lightLevel((state) -> 3)), 1600);
    public static final RegistryObject<RotatedPillarBlock> stripped_aura_wood = register("stripped_aura_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.METAL)));
    public static final RegistryObject<RotatedPillarBlock> stripped_golden_wood = register("stripped_golden_wood", () -> new RotatedPillarBlock(PropertiesHandler.logProps(MaterialColor.GOLD)));

    public static final RegistryObject<Block> salt = register("salt", () -> new GaiaFallingBlock(PropertiesHandler.sandProps(MaterialColor.SNOW, 0.9F, SoundType.SAND), 0xE0E0FF));
    public static final RegistryObject<Block> saltstone = register("saltstone", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_LIGHT_BLUE, 1.5F, 10.0F, true));
    public static final RegistryObject<Block> pebbles = register("pebbles", () -> new GaiaFallingBlock(PropertiesHandler.sandProps(MaterialColor.COLOR_GRAY, 1.3F, SoundType.GRAVEL), 0x663366));
    public static final RegistryObject<Block> gaia_stone = register("gaia_stone", PropertiesHandler.stoneProps(MaterialColor.COLOR_MAGENTA, 2.0F, 15.0F, true));
    public static final RegistryObject<Block> gaia_cobblestone = register("gaia_cobblestone", PropertiesHandler.stoneProps(MaterialColor.COLOR_MAGENTA, 2.0F, 15.0F, true));
    public static final RegistryObject<Block> wasteland_stone = register("wasteland_stone", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_BLUE, 15.0F, 200.0F, true));
    public static final RegistryObject<Block> static_stone = register("static_stone", () -> new StaticStoneBlock(PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_BLUE, 50.0F, 200.0F, true)));
    public static final RegistryObject<Block> charged_mineral = register("charged_mineral", () -> new ChargedMineralBlock(Properties.of(Material.METAL, MaterialColor.COLOR_CYAN).strength(4.0F, 15.0F).sound(SoundType.GLASS).noOcclusion()));
    public static final RegistryObject<Block> volcanic_rock = register("volcanic_rock", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_GRAY, 15.0F, 200.0F, true));
    public static final RegistryObject<Block> searing_rock = register("searing_rock", () -> new SearingRockBlock(PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_GRAY, 20.0F, 600.0F, true).lightLevel((state) -> 7)));
    public static final RegistryObject<Block> primal_mass = register("primal_mass", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_PURPLE, 5.0F, 45.0F, true));
    public static final RegistryObject<Block> nexustone = register("nexustone", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_BLACK, 10.0F, 100.0F, true));
    public static final RegistryObject<Block> impure_rock = register("impure_rock", PropertiesHandler.stoneProps(MaterialColor.COLOR_GRAY, 20.0F, 300.0F, true));
    public static final RegistryObject<Block> active_rock = register("active_rock", () -> new ActiveRockBlock(PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_PURPLE, 15.0F, 250.0F, true).lightLevel((state) -> 7)));
    public static final RegistryObject<Block> impure_sludge = register("impure_sludge", () -> new SlowingBlock(PropertiesHandler.muckyProps(MaterialColor.TERRACOTTA_YELLOW, 0.4F, 0.8F)));
    public static final RegistryObject<Block> geyser_block = register("geyser_block", () -> new GeyserBlock(PropertiesHandler.stoneProps(MaterialColor.METAL, 5.0F, 10.0F, true)));
    public static final RegistryObject<Block> sparkling_rock = register("sparkling_rock", Properties.of(Material.STONE, MaterialColor.METAL).strength(10.0F, 150.0F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops());
    public static final RegistryObject<Block> aura_shoot = register("aura_shoot", () -> new AuraShootBlock(Properties.of(Material.GLASS, MaterialColor.COLOR_BLUE).sound(SoundType.AMETHYST_CLUSTER).randomTicks()));
    public static final RegistryObject<Block> golden_stone = register("golden_stone", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_PURPLE, 2.0F, 15.0F, true));
    public static final RegistryObject<Block> tough_golden_stone = register("tough_golden_stone", PropertiesHandler.stoneProps(MaterialColor.COLOR_BLACK, 3.0F, 30.0F, true));
    public static final RegistryObject<Block> brilliant_stone = register("brilliant_stone", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_YELLOW, 5.0F, 35.0F, true));
    public static final RegistryObject<Block> gilded_brilliant_stone = register("gilded_brilliant_stone", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_WHITE, 5.0F, 35.0F, true).lightLevel((state) -> 5));
    public static final RegistryObject<Block> aurum_mud = register("aurum_mud", PropertiesHandler.muckyProps(MaterialColor.TERRACOTTA_BLACK, 0.3F, 0.4F));
    public static final RegistryObject<Block> golden_sand = register("golden_sand", () -> new GaiaFallingBlock(PropertiesHandler.sandProps(MaterialColor.GOLD, 1.0F, SoundType.SAND), 0xFFD700));
    public static final RegistryObject<Block> scarlet_mookaite = register("scarlet_mookaite", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_RED, 1.8F, 12.0F));
    public static final RegistryObject<Block> auburn_mookaite = register("auburn_mookaite", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_ORANGE, 1.8F, 12.0F));
    public static final RegistryObject<Block> gold_mookaite = register("gold_mookaite", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_YELLOW, 1.8F, 12.0F));
    public static final RegistryObject<Block> mauve_mookaite = register("mauve_mookaite", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_PURPLE, 1.8F, 12.0F));
    public static final RegistryObject<Block> beige_mookaite = register("beige_mookaite", PropertiesHandler.stoneProps(MaterialColor.SAND, 1.8F, 12.0F));
    public static final RegistryObject<Block> ivory_mookaite = register("ivory_mookaite", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_WHITE, 1.8F, 12.0F));

    //Planks
    public static final RegistryObject<Block> pink_agate_planks = register("pink_agate_planks", PropertiesHandler.tileProps(MaterialColor.COLOR_PINK));
    public static final RegistryObject<Block> blue_agate_planks = register("blue_agate_planks", PropertiesHandler.tileProps(MaterialColor.COLOR_LIGHT_BLUE));
    public static final RegistryObject<Block> green_agate_planks = register("green_agate_planks", PropertiesHandler.tileProps(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> purple_agate_planks = register("purple_agate_planks", PropertiesHandler.tileProps(MaterialColor.TERRACOTTA_PURPLE));
    public static final RegistryObject<Block> fossilized_planks = register("fossilized_planks", PropertiesHandler.tileProps(MaterialColor.TERRACOTTA_YELLOW));
    public static final RegistryObject<Block> corrupted_planks = register("corrupted_planks", PropertiesHandler.tileProps(MaterialColor.TERRACOTTA_BLACK));
    public static final RegistryObject<Block> burnt_planks = register("burnt_planks", PropertiesHandler.tileProps(MaterialColor.COLOR_BLACK));
    public static final RegistryObject<Block> burning_planks = register("burning_planks", PropertiesHandler.tileProps(MaterialColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3), 400);
    public static final RegistryObject<Block> aura_planks = register("aura_planks", PropertiesHandler.tileProps(MaterialColor.SNOW));
    public static final RegistryObject<SlabBlock> pink_agate_plank_slab = register("pink_agate_plank_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MaterialColor.COLOR_PINK)));
    public static final RegistryObject<SlabBlock> blue_agate_plank_slab = register("blue_agate_plank_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MaterialColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<SlabBlock> green_agate_plank_slab = register("green_agate_plank_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MaterialColor.COLOR_LIGHT_GREEN)));
    public static final RegistryObject<SlabBlock> purple_agate_plank_slab = register("purple_agate_plank_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MaterialColor.TERRACOTTA_PURPLE)));
    public static final RegistryObject<SlabBlock> fossilized_plank_slab = register("fossilized_plank_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MaterialColor.TERRACOTTA_YELLOW)));
    public static final RegistryObject<SlabBlock> corrupted_plank_slab = register("corrupted_plank_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MaterialColor.TERRACOTTA_BLACK)));
    public static final RegistryObject<SlabBlock> burnt_plank_slab = register("burnt_plank_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MaterialColor.COLOR_BLACK)));
    public static final RegistryObject<SlabBlock> burning_plank_slab = register("burning_plank_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MaterialColor.TERRACOTTA_ORANGE).lightLevel((state) -> 3)), 200);
    public static final RegistryObject<SlabBlock> aura_plank_slab = register("aura_plank_slab", () -> new SlabBlock(PropertiesHandler.tileProps(MaterialColor.SNOW)));
    public static final RegistryObject<StairBlock> pink_agate_plank_stairs = register("pink_agate_plank_stairs", makeStairs(pink_agate_planks), 0);
    public static final RegistryObject<StairBlock> blue_agate_plank_stairs = register("blue_agate_plank_stairs", makeStairs(blue_agate_planks), 0);
    public static final RegistryObject<StairBlock> green_agate_plank_stairs = register("green_agate_plank_stairs", makeStairs(green_agate_planks), 0);
    public static final RegistryObject<StairBlock> purple_agate_plank_stairs = register("purple_agate_plank_stairs", makeStairs(purple_agate_planks), 0);
    public static final RegistryObject<StairBlock> fossilized_plank_stairs = register("fossilized_plank_stairs", makeStairs(fossilized_planks), 0);
    public static final RegistryObject<StairBlock> corrupted_plank_stairs = register("corrupted_plank_stairs", makeStairs(corrupted_planks), 0);
    public static final RegistryObject<StairBlock> burnt_plank_stairs = register("burnt_plank_stairs", makeStairs(burnt_planks), 0);
    public static final RegistryObject<StairBlock> burning_plank_stairs = register("burning_plank_stairs", makeStairs(burning_planks), 300);
    public static final RegistryObject<StairBlock> aura_plank_stairs = register("aura_plank_stairs", makeStairs(aura_planks), 0);

    //Manufactured
    public static final RegistryObject<Block> cloudy_glass = register("cloudy_glass", () -> new GlassBlock(PropertiesHandler.glassProps(MaterialColor.COLOR_YELLOW, 0.7F)));
    public static final RegistryObject<Block> foggy_glass = register("foggy_glass", () -> new GlassBlock(PropertiesHandler.glassProps(MaterialColor.COLOR_LIGHT_BLUE, 0.7F)));
    public static final RegistryObject<Block> gaia_stone_bricks = register("gaia_stone_bricks", PropertiesHandler.gaiaBrickProps());
    public static final RegistryObject<Block> cracked_gaia_stone_bricks = register("cracked_gaia_stone_bricks", PropertiesHandler.gaiaBrickProps());
    public static final RegistryObject<Block> crusted_gaia_stone_bricks = register("crusted_gaia_stone_bricks", PropertiesHandler.gaiaBrickProps());

    public static final RegistryObject<Block> raw_jade = register("raw_jade", PropertiesHandler.stoneProps(MaterialColor.COLOR_GREEN, 2.0F, 20.0F, true));
    public static final RegistryObject<Block> jade_bricks = register("jade_bricks", PropertiesHandler.jadeProps());
    public static final RegistryObject<SlabBlock> jade_brick_slab = register("jade_brick_slab", makeSlab(PropertiesHandler.jadeProps()));
    public static final RegistryObject<StairBlock> jade_brick_stairs = register("jade_brick_stairs", makeStairs(jade_bricks));
    public static final RegistryObject<Block> cracked_jade_bricks = register("cracked_jade_bricks", PropertiesHandler.jadeProps());
    public static final RegistryObject<SlabBlock> cracked_jade_brick_slab = register("cracked_jade_brick_slab", makeSlab(PropertiesHandler.jadeProps()));
    public static final RegistryObject<StairBlock> cracked_jade_brick_stairs = register("cracked_jade_brick_stairs", makeStairs(cracked_jade_bricks));
    public static final RegistryObject<Block> crusted_jade_bricks = register("crusted_jade_bricks", PropertiesHandler.jadeProps());
    public static final RegistryObject<SlabBlock> crusted_jade_brick_slab = register("crusted_jade_brick_slab", makeSlab(PropertiesHandler.jadeProps()));
    public static final RegistryObject<StairBlock> crusted_jade_brick_stairs = register("crusted_jade_brick_stairs", makeStairs(crusted_jade_bricks));
    public static final RegistryObject<Block> raw_copal = register("raw_copal", PropertiesHandler.stoneProps(MaterialColor.GOLD, 2.0F, 20.0F, true));
    public static final RegistryObject<Block> copal_bricks = register("copal_bricks", PropertiesHandler.copalProps());
    public static final RegistryObject<SlabBlock> copal_brick_slab = register("copal_brick_slab", makeSlab(PropertiesHandler.copalProps()));
    public static final RegistryObject<StairBlock> copal_brick_stairs = register("copal_brick_stairs", makeStairs(copal_bricks));
    public static final RegistryObject<Block> cracked_copal_bricks = register("cracked_copal_bricks", PropertiesHandler.copalProps());
    public static final RegistryObject<SlabBlock> cracked_copal_brick_slab = register("cracked_copal_brick_slab", makeSlab(PropertiesHandler.copalProps()));
    public static final RegistryObject<StairBlock> cracked_copal_brick_stairs = register("cracked_copal_brick_stairs", makeStairs(cracked_copal_bricks));
    public static final RegistryObject<Block> crusted_copal_bricks = register("crusted_copal_bricks", PropertiesHandler.copalProps());
    public static final RegistryObject<SlabBlock> crusted_copal_brick_slab = register("crusted_copal_brick_slab", makeSlab(PropertiesHandler.copalProps()));
    public static final RegistryObject<StairBlock> crusted_copal_brick_stairs = register("crusted_copal_brick_stairs", makeStairs(crusted_copal_bricks));
    public static final RegistryObject<Block> raw_jet = register("raw_jet", PropertiesHandler.stoneProps(MaterialColor.COLOR_GRAY, 2.0F, 20.0F, true));
    public static final RegistryObject<Block> jet_bricks = register("jet_bricks", PropertiesHandler.jetProps());
    public static final RegistryObject<SlabBlock> jet_brick_slab = register("jet_brick_slab", makeSlab(PropertiesHandler.jetProps()));
    public static final RegistryObject<StairBlock> jet_brick_stairs = register("jet_brick_stairs", makeStairs(jet_bricks));
    public static final RegistryObject<Block> cracked_jet_bricks = register("cracked_jet_bricks", PropertiesHandler.jetProps());
    public static final RegistryObject<SlabBlock> cracked_jet_brick_slab = register("cracked_jet_brick_slab", makeSlab(PropertiesHandler.jetProps()));
    public static final RegistryObject<StairBlock> cracked_jet_brick_stairs = register("cracked_jet_brick_stairs", makeStairs(cracked_jet_bricks));
    public static final RegistryObject<Block> crusted_jet_bricks = register("crusted_jet_bricks", PropertiesHandler.jetProps());
    public static final RegistryObject<SlabBlock> crusted_jet_brick_slab = register("crusted_jet_brick_slab", makeSlab(PropertiesHandler.jetProps()));
    public static final RegistryObject<StairBlock> crusted_jet_brick_stairs = register("crusted_jet_brick_stairs", makeStairs(crusted_jet_bricks));
    public static final RegistryObject<Block> raw_amethyst = register("raw_amethyst", PropertiesHandler.stoneProps(MaterialColor.TERRACOTTA_PURPLE, 2.0F, 20.0F, true));
    public static final RegistryObject<Block> amethyst_bricks = register("amethyst_bricks", PropertiesHandler.amethystProps());
    public static final RegistryObject<SlabBlock> amethyst_brick_slab = register("amethyst_brick_slab", makeSlab(PropertiesHandler.amethystProps()));
    public static final RegistryObject<StairBlock> amethyst_brick_stairs = register("amethyst_brick_stairs", makeStairs(amethyst_bricks));
    public static final RegistryObject<Block> cracked_amethyst_bricks = register("cracked_amethyst_bricks", PropertiesHandler.amethystProps());
    public static final RegistryObject<SlabBlock> cracked_amethyst_brick_slab = register("cracked_amethyst_brick_slab", makeSlab(PropertiesHandler.amethystProps()));
    public static final RegistryObject<StairBlock> cracked_amethyst_brick_stairs = register("cracked_amethyst_brick_stairs", makeStairs(cracked_amethyst_bricks));
    public static final RegistryObject<Block> crusted_amethyst_bricks = register("crusted_amethyst_bricks", PropertiesHandler.amethystProps());
    public static final RegistryObject<SlabBlock> crusted_amethyst_brick_slab = register("crusted_amethyst_brick_slab", makeSlab(PropertiesHandler.amethystProps()));
    public static final RegistryObject<StairBlock> crusted_amethyst_brick_stairs = register("crusted_amethyst_brick_stairs", makeStairs(crusted_amethyst_bricks));

    public static final RegistryObject<Block> reinforced_bricks = register("reinforced_bricks", PropertiesHandler.stoneProps(MaterialColor.COLOR_PURPLE, 10.0F, 100.0F, true));
    public static final RegistryObject<Block> bolstered_bricks = register("bolstered_bricks", PropertiesHandler.stoneProps(MaterialColor.SAND, 30.0F, 400.0F, true));
    public static final RegistryObject<Block> malachite_bricks = register("malachite_bricks", PropertiesHandler.malachiteProps());
    public static final RegistryObject<Block> malachite_cracked_bricks = register("malachite_cracked_bricks", PropertiesHandler.malachiteProps());
    public static final RegistryObject<Block> malachite_crusted_bricks = register("malachite_crusted_bricks", PropertiesHandler.malachiteProps());
    public static final RegistryObject<Block> malachite_floor_tiles = register("malachite_floor_tiles", PropertiesHandler.malachiteProps());
    public static final RegistryObject<Block> malachite_chisel_bricks = register("malachite_chisel_bricks", PropertiesHandler.malachiteProps());
    public static final RegistryObject<Block> malachite_pulsing_bricks = register("malachite_pulsing_bricks", PropertiesHandler.malachiteProps());
    public static final RegistryObject<Block> malachite_pulsing_tiles = register("malachite_pulsing_tiles", PropertiesHandler.malachiteProps());
    public static final RegistryObject<Block> malachite_pulsing_chisel = register("malachite_pulsing_chisel", PropertiesHandler.malachiteProps());
    public static final RegistryObject<SlabBlock> malachite_brick_slab = register("malachite_brick_slab", makeSlab(PropertiesHandler.malachiteProps()));
    public static final RegistryObject<SlabBlock> malachite_cracked_brick_slab = register("malachite_cracked_brick_slab", makeSlab(PropertiesHandler.malachiteProps()));
    public static final RegistryObject<SlabBlock> malachite_crusted_brick_slab = register("malachite_crusted_brick_slab", makeSlab(PropertiesHandler.malachiteProps()));
    public static final RegistryObject<SlabBlock> malachite_floor_slab = register("malachite_floor_slab", makeSlab(PropertiesHandler.malachiteProps()));
    public static final RegistryObject<RotatedPillarBlock> malachite_pillar = register("malachite_pillar", () -> new RotatedPillarBlock(PropertiesHandler.malachiteProps()));
    public static final RegistryObject<StairBlock> malachite_brick_stairs = register("malachite_brick_stairs", makeStairs(malachite_bricks));
    public static final RegistryObject<StairBlock> malachite_cracked_brick_stairs = register("malachite_cracked_brick_stairs", makeStairs(malachite_cracked_bricks));
    public static final RegistryObject<StairBlock> malachite_crusted_brick_stairs = register("malachite_crusted_brick_stairs", makeStairs(malachite_crusted_bricks));
    public static final RegistryObject<StairBlock> malachite_floor_stairs = register("malachite_floor_stairs", makeStairs(malachite_floor_tiles));
    public static final RegistryObject<StairBlock> malachite_chisel_stairs = register("malachite_chisel_stairs", makeStairs(malachite_chisel_bricks));
    public static final RegistryObject<StairBlock> malachite_pulsing_brick_stairs = register("malachite_pulsing_brick_stairs", makeStairs(malachite_bricks));
    public static final RegistryObject<StairBlock> malachite_pulsing_floor_stairs = register("malachite_pulsing_floor_stairs", makeStairs(malachite_floor_tiles));
    public static final RegistryObject<StairBlock> malachite_pulsing_chisel_stairs = register("malachite_pulsing_chisel_stairs", makeStairs(malachite_chisel_bricks));
    public static final RegistryObject<StairBlock> malachite_pillar_stairs = register("malachite_pillar_stairs", makeStairs(malachite_pillar));

    //Storage Blocks
    public static final RegistryObject<Block> sugilite_block = register("sugilite_block", PropertiesHandler.storageProps(MaterialColor.COLOR_PURPLE));
    public static final RegistryObject<Block> hematite_block = register("hematite_block", PropertiesHandler.storageProps(MaterialColor.COLOR_GRAY));
    public static final RegistryObject<Block> cinnabar_block = register("cinnabar_block", PropertiesHandler.storageProps(MaterialColor.COLOR_ORANGE));
    public static final RegistryObject<Block> labradorite_block = register("labradorite_block", PropertiesHandler.storageProps(MaterialColor.COLOR_GREEN));
    public static final RegistryObject<Block> moonstone_block = register("moonstone_block", PropertiesHandler.storageProps(MaterialColor.METAL));
    public static final RegistryObject<Block> opal_block_red = register("opal_block_red", PropertiesHandler.storageProps(MaterialColor.COLOR_RED));
    public static final RegistryObject<Block> opal_block_blue = register("opal_block_blue", PropertiesHandler.storageProps(MaterialColor.COLOR_LIGHT_BLUE));
    public static final RegistryObject<Block> opal_block_green = register("opal_block_green", PropertiesHandler.storageProps(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> opal_block_white = register("opal_block_white", PropertiesHandler.storageProps(MaterialColor.SNOW));
    public static final RegistryObject<Block> pyrite_block = register("pyrite_block", PropertiesHandler.storageProps(MaterialColor.GOLD).lightLevel((state) -> 15));
    public static final RegistryObject<Block> tektite_block = register("tektite_block", PropertiesHandler.storageProps(MaterialColor.COLOR_BLACK));
    public static final RegistryObject<Block> goldstone_block = register("goldstone_block", PropertiesHandler.storageProps(MaterialColor.COLOR_BLACK));
    public static final RegistryObject<Block> aura_block = register("aura_block", PropertiesHandler.storageProps(MaterialColor.ICE));
    public static final RegistryObject<Block> bismuth_block = register("bismuth_block", PropertiesHandler.storageProps(MaterialColor.PODZOL));
    public static final RegistryObject<Block> ixiolite_block = register("ixiolite_block", PropertiesHandler.storageProps(MaterialColor.COLOR_GRAY));
    public static final RegistryObject<Block> proustite_block = register("proustite_block", PropertiesHandler.storageProps(MaterialColor.COLOR_MAGENTA));
    public static final RegistryObject<Block> euclase_block = register("euclase_block", PropertiesHandler.storageProps(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> leucite_block = register("leucite_block", PropertiesHandler.storageProps(MaterialColor.SAND));
    public static final RegistryObject<Block> carnelian_block = register("carnelian_block", PropertiesHandler.storageProps(MaterialColor.COLOR_RED));
    public static final RegistryObject<Block> benitoite_block = register("benitoite_block", PropertiesHandler.storageProps(MaterialColor.COLOR_BLUE));
    public static final RegistryObject<Block> diopside_block = register("diopside_block", PropertiesHandler.storageProps(MaterialColor.COLOR_LIGHT_GREEN));
    public static final RegistryObject<Block> chalcedony_block = register("chalcedony_block", PropertiesHandler.storageProps(MaterialColor.SNOW));

    //Ores
    public static final RegistryObject<Block> sugilite_ore = register("sugilite_ore", () -> new DropExperienceBlock(PropertiesHandler.oreProps(MaterialColor.COLOR_PURPLE), UniformInt.of(1, 3)));
    public static final RegistryObject<Block> hematite_ore = register("hematite_ore", () -> new DropExperienceBlock(PropertiesHandler.oreProps(MaterialColor.COLOR_GRAY), UniformInt.of(1, 4)));
    public static final RegistryObject<Block> cinnabar_ore = register("cinnabar_ore", () -> new DropExperienceBlock(PropertiesHandler.oreProps(MaterialColor.COLOR_ORANGE), UniformInt.of(1, 4)));
    public static final RegistryObject<Block> labradorite_ore = register("labradorite_ore", () -> new DropExperienceBlock(PropertiesHandler.oreProps(MaterialColor.COLOR_GREEN), UniformInt.of(5, 2)));
    public static final RegistryObject<Block> moonstone_ore = register("moonstone_ore", () -> new DropExperienceBlock(PropertiesHandler.oreProps(MaterialColor.METAL), UniformInt.of(5, 2)));
    public static final RegistryObject<Block> opal_ore_red = register("opal_ore_red", () -> new DropExperienceBlock(PropertiesHandler.oreProps(MaterialColor.COLOR_RED), UniformInt.of(2, 5)));
    public static final RegistryObject<Block> opal_ore_blue = register("opal_ore_blue", () -> new DropExperienceBlock(PropertiesHandler.oreProps(MaterialColor.COLOR_LIGHT_BLUE), UniformInt.of(2, 5)));
    public static final RegistryObject<Block> opal_ore_green = register("opal_ore_green", () -> new DropExperienceBlock(PropertiesHandler.oreProps(MaterialColor.COLOR_LIGHT_GREEN), UniformInt.of(2, 5)));
    public static final RegistryObject<Block> opal_ore_white = register("opal_ore_white", () -> new DropExperienceBlock(PropertiesHandler.oreProps(MaterialColor.SNOW), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> pyrite_ore = register("pyrite_ore", () -> new DropExperienceBlock(PropertiesHandler.oreProps(MaterialColor.GOLD).lightLevel((state) -> 3), UniformInt.of(1, 4)));
    public static final RegistryObject<Block> speckled_rock = register("speckled_rock", () -> new Block(PropertiesHandler.oreProps(MaterialColor.COLOR_MAGENTA)));
    public static final RegistryObject<Block> coarse_rock = register("coarse_rock", () -> new Block(PropertiesHandler.oreProps(MaterialColor.COLOR_MAGENTA)));
    public static final RegistryObject<Block> precious_rock = register("precious_rock", () -> new Block(PropertiesHandler.oreProps(MaterialColor.COLOR_MAGENTA)));

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
    public static final RegistryObject<FlowerPotBlock> potted_twinkling_gilsri = registerFlowerPot(twinkling_gilsri);
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
    public static final RegistryObject<FlowerPotBlock> potted_golden_sapling = registerFlowerPot(golden_sapling);

    //Spawners
    public static final RegistryObject<BossSpawnerBlock> malachite_guard_spawner = registerNoItem("malachite_guard_spawner", () -> new BossSpawnerBlock(BossSpawnerBlock.BossType.MALACHITE, PropertiesHandler.spawnerProps()));

    private static Supplier<StairBlock> makeStairs(RegistryObject<? extends Block> block) {
        return () -> new StairBlock(() -> block.get().defaultBlockState(), Properties.copy(block.get()));
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
        return registerBlock(name, block, item -> registerBlockItemFuel(item, burnTime));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> reg = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, item.apply(reg));
        return reg;
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItemFuel(final RegistryObject<T> block, int burnTime) {
        return () -> new BlockItem(block.get(), new Item.Properties()) {
            @Override
            public int getBurnTime(ItemStack itemStack, RecipeType<?> type) {
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
        block.addPlant(burning_sapling.getId(), potted_burning_sapling);
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
        addToMap(STRIPABLES, burning_log, stripped_burning_log);
        addToMap(STRIPABLES, aura_log, stripped_aura_log);
        addToMap(STRIPABLES, golden_log, stripped_golden_log);
        addToMap(STRIPABLES, pink_agate_wood, stripped_pink_agate_wood);
        addToMap(STRIPABLES, blue_agate_wood, stripped_blue_agate_wood);
        addToMap(STRIPABLES, green_agate_wood, stripped_green_agate_wood);
        addToMap(STRIPABLES, purple_agate_wood, stripped_purple_agate_wood);
        addToMap(STRIPABLES, fossilized_wood, stripped_fossilized_wood);
        addToMap(STRIPABLES, corrupted_wood, stripped_corrupted_wood);
        addToMap(STRIPABLES, burnt_wood, stripped_burnt_wood);
        addToMap(STRIPABLES, burning_wood, stripped_burning_wood);
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
                BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                Level world = source.getLevel();
                if (bucketitem.emptyContents(null, world, blockpos, null)) {
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
                LevelAccessor iworld = source.getLevel();
                BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                BlockState blockstate = iworld.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (block instanceof BucketPickup) {
                    ItemStack fluid = ((BucketPickup)block).pickupBlock(iworld, blockpos, blockstate);
                    if (fluid.isEmpty()) {
                        return super.execute(source, stack);
                    } else {
                        iworld.gameEvent(null, GameEvent.FLUID_PICKUP, blockpos);
                        Item item = fluid.getItem();
                        stack.shrink(1);
                        if (stack.isEmpty()) {
                            return new ItemStack(item);
                        } else {
                            if (source.<DispenserBlockEntity>getEntity().addItem(new ItemStack(item)) < 0) {
                                this.defaultBehaviour.dispense(source, new ItemStack(item));
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