package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.block.*;
import androsa.gaiadimension.block.tileentity.*;
import androsa.gaiadimension.fluid.GDFluidBlock;
import androsa.gaiadimension.fluid.GDSuperhotMagma;
import androsa.gaiadimension.world.gen.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public final class GDBlocksRegister {
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        BlockRegistryHelper blocks = new BlockRegistryHelper(event.getRegistry());

        //Utility Blocks
        blocks.register("gaia_portal", new GDGaiaPortal());
        blocks.register("keystone_block", new GDBlock(Material.IRON, MapColor.GOLD, SoundType.METAL).setHardness(3.0F).setResistance(5.0F));
        blocks.register("gold_fire", new GDGoldFire());
        blocks.register("pyrite_torch", new GDPyriteTorch());
        blocks.register("agate_crafting_table", new GDAgateCraftingTable());
        blocks.register("crude_storage_crate", new GDCrateSmall());
        blocks.register("mega_storage_crate", new GDCrateLarge());
        blocks.register("gaia_stone_furnace_idle", new GDGaiaStoneFurnace(false));
        blocks.register("gaia_stone_furnace_lit", new GDGaiaStoneFurnace(true));
        blocks.register("restructurer_idle", new GDRestructurer(false));
        blocks.register("restructurer_lit", new GDRestructurer(true));
        blocks.register("purifier_idle", new GDPurifier(false));
        blocks.register("purifier_lit", new GDPurifier(true));
        blocks.register("mineral_water_block", new GDFluidBlock(GDFluids.mineralWater, Material.WATER));
        blocks.register("superhot_magma_block", new GDSuperhotMagma(GDFluids.superhotMagma, Material.LAVA).setLightLevel(1.0F));
        blocks.register("sweet_muck_block", new GDFluidBlock(GDFluids.sweetMuck, Material.WATER));

        //Natural Blocks
        blocks.register("heavy_soil", new GDHeavySoil());
        blocks.register("corrupt_soil", new GDCorruptSoil());
        blocks.register("glitter_grass", new GDCrystalGrass(() -> GDBlocks.glitter_grass, () -> GDBlocks.heavy_soil));
        blocks.register("corrupt_grass", new GDCrystalGrass(() -> GDBlocks.corrupt_grass, () -> GDBlocks.corrupt_soil));
        blocks.register("frail_glitter_block", new GDFrailGlitterBlock());
        blocks.register("thick_glitter_block", new GDBlock(Material.ROCK, MapColor.PURPLE_STAINED_HARDENED_CLAY).setHardness(1.5F).setResistance(7.5F));
        blocks.register("gummy_glitter_block", new GDGummyGlitterBlock());
        blocks.register("pink_sludge_block", new GDPinkSludgeBlock());

        //Plants
        blocks.register("crystal_growth", new GDCrystalGrowth());
        blocks.register("crystal_growth_red", new GDCrystalGrowth());
        blocks.register("crystal_growth_black", new GDCrystalGrowth());
        blocks.register("crystal_growth_seared", new GDCrystalGrowth());
        blocks.register("crystal_growth_mutant", new GDCrystalGrowth());
        blocks.register("thiscus", new GDCrystalBloom());
        blocks.register("ouzium", new GDCrystalBloom());
        blocks.register("agathum", new GDCrystalBloom());
        blocks.register("varloom", new GDCrystalBloom());
        blocks.register("corrupt_varloom", new GDCrystalBloom());
        blocks.register("missingno_plant", new GDCrystalBloom());
        blocks.register("spotted_kersei", new GDCrystalFungus(false));
        blocks.register("thorny_wiltha", new GDCrystalFungus(false));
        blocks.register("roofed_agaric", new GDCrystalFungus(false));
        blocks.register("bulbous_hobina", new GDCrystalFungus(false));
        blocks.register("stickly_cupsir", new GDCrystalFungus(false));
        blocks.register("mystical_murgni", new GDCrystalFungus(false));
        blocks.register("corrupted_gaia_eye", new GDCrystalFungus(false));
        //blocks.register("sacred_gaia_eye", new GDCrystalFungus(false));
        blocks.register("elder_imklia", new GDCrystalFungus(true));
        blocks.register("gold_orb_tucher", new GDCrystalFungus(true));
        blocks.register("missingno_fungus", new GDCrystalFungus(false));

        blocks.register("pink_agate_sapling", new GDAgateSapling(() -> new GDGenPinkAgateTree(true)));
        blocks.register("blue_agate_sapling", new GDAgateSapling(() -> new GDGenBlueAgateTree(true)));
        blocks.register("green_agate_sapling", new GDAgateSapling(() -> new GDGenGreenAgateTree(true)));
        blocks.register("purple_agate_sapling", new GDAgateSapling(() -> new GDGenPurpleAgateTree(true)));
        blocks.register("fossilized_sapling", new GDAgateSapling(() -> new GDGenFossilizedTree(true)));
        blocks.register("corrupted_sapling", new GDAgateSapling(() -> new GDGenGoldstoneCorruptTree(true)));
        blocks.register("burnt_sapling", new GDAgateSapling(() -> new GDGenBurntAgateTree(true)));
        blocks.register("burning_sapling", new GDAgateSapling(() -> new GDGenFieryAgateTree(true)));
        blocks.register("pink_agate_leaves", new GDAgateLeaves(() -> Item.getItemFromBlock(GDBlocks.pink_agate_sapling)));
        blocks.register("blue_agate_leaves", new GDAgateLeaves(() -> Item.getItemFromBlock(GDBlocks.blue_agate_sapling)));
        blocks.register("green_agate_leaves", new GDAgateLeaves(() -> Item.getItemFromBlock(GDBlocks.green_agate_sapling)));
        blocks.register("purple_agate_leaves", new GDAgateLeaves(() -> Item.getItemFromBlock(GDBlocks.purple_agate_sapling)));
        blocks.register("fossilized_leaves", new GDAgateLeaves(() -> GDItems.fine_dust));
        blocks.register("corrupted_leaves", new GDAgateLeaves(() -> GDItems.goldstone_dust));
        blocks.register("burnt_leaves", new GDAgateLeaves(() -> Items.GUNPOWDER));
        blocks.register("burning_leaves", (new GDAgateLeaves(() -> GDItems.hot_dust)).setLightLevel(0.3F));
        blocks.register("pink_agate_log", new GDAgateLog());
        blocks.register("blue_agate_log", new GDAgateLog());
        blocks.register("green_agate_log", new GDAgateLog());
        blocks.register("purple_agate_log", new GDAgateLog());
        blocks.register("fossilized_log", new GDAgateLog());
        blocks.register("corrupted_log", new GDAgateLog());
        blocks.register("burnt_log", new GDAgateLog());
        blocks.register("burning_log", (new GDAgateLog()).setLightLevel(0.3F));
        blocks.register("salt", new GDSaltBlock());
        blocks.register("saltstone", new GDBlock(Material.ROCK, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY).setHardness(1.5F).setResistance(10.0F));
        blocks.register("gaia_stone", new GDBlock(Material.ROCK, MapColor.MAGENTA).setHardness(2.0F).setResistance(15.0F));
        blocks.register("gaia_cobblestone", new GDBlock(Material.ROCK, MapColor.MAGENTA).setHardness(2.0F).setResistance(15.0F));
        blocks.register("wasteland_stone", new GDBlock(Material.ROCK, MapColor.BLUE_STAINED_HARDENED_CLAY).setHardness(15.0F).setResistance(200.0F));
        blocks.register("static_stone", new GDStaticStone());
        blocks.register("charged_mineral", new GDChargedMineral());
        blocks.register("volcanic_rock", new GDBlock(Material.ROCK, MapColor.GRAY_STAINED_HARDENED_CLAY).setHardness(15.0F).setResistance(200.0F));
        blocks.register("searing_rock", new GDSearingRock());
        blocks.register("primal_mass", new GDBlock(Material.ROCK, MapColor.PURPLE_STAINED_HARDENED_CLAY).setHardness(30.0F).setResistance(400.0F));

        //Planks
        blocks.register("pink_agate_planks", new GDBlock(Material.WOOD, MapColor.PINK, SoundType.STONE).setHardness(1.5F).setResistance(2.0F));
        blocks.register("blue_agate_planks", new GDBlock(Material.WOOD, MapColor.LIGHT_BLUE, SoundType.STONE).setHardness(1.5F).setResistance(2.0F));
        blocks.register("green_agate_planks", new GDBlock(Material.WOOD, MapColor.LIME, SoundType.STONE).setHardness(1.5F).setResistance(2.0F));
        blocks.register("purple_agate_planks", new GDBlock(Material.WOOD, MapColor.PURPLE_STAINED_HARDENED_CLAY, SoundType.STONE).setHardness(1.5F).setResistance(2.0F));
        blocks.register("fossilized_planks", new GDBlock(Material.WOOD, MapColor.YELLOW_STAINED_HARDENED_CLAY, SoundType.STONE).setHardness(1.5F).setResistance(2.0F));
        blocks.register("corrupted_planks", new GDBlock(Material.WOOD, MapColor.BLACK_STAINED_HARDENED_CLAY, SoundType.STONE).setHardness(1.5F).setResistance(2.0F));
        blocks.register("burnt_planks", new GDBlock(Material.WOOD, MapColor.BLACK, SoundType.STONE).setHardness(1.5F).setResistance(2.0F));
        blocks.register("burning_planks", new GDBlock(Material.WOOD, MapColor.ORANGE_STAINED_HARDENED_CLAY, SoundType.STONE).setHardness(1.5F).setResistance(2.0F).setLightLevel(0.5F));
        blocks.register("pink_agate_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("blue_agate_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("green_agate_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("purple_agate_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("fossilized_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("corrupted_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("burnt_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("burning_plank_slab", (new GDAgatePlankSlab(false)).setLightLevel(0.5F));
        blocks.register("double_pink_agate_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_blue_agate_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_green_agate_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_purple_agate_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_fossilized_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_corrupted_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_burnt_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_burning_plank_slab", (new GDAgatePlankSlab(true)).setLightLevel(0.5F));
        Block pinkPlanks    = new GDBlock(Material.WOOD, MapColor.PINK);
        Block bluePlanks    = new GDBlock(Material.WOOD, MapColor.LIGHT_BLUE);
        Block greenPlanks   = new GDBlock(Material.WOOD, MapColor.LIME);
        Block purplePlanks  = new GDBlock(Material.WOOD, MapColor.PURPLE_STAINED_HARDENED_CLAY);
        Block fossilPlanks  = new GDBlock(Material.WOOD, MapColor.YELLOW_STAINED_HARDENED_CLAY);
        Block corruptPlanks = new GDBlock(Material.WOOD, MapColor.BLACK_STAINED_HARDENED_CLAY);
        Block burntPlanks   = new GDBlock(Material.WOOD, MapColor.BLACK);
        Block firePlanks    = new GDBlock(Material.WOOD, MapColor.ORANGE_STAINED_HARDENED_CLAY).setLightLevel(0.5F);
        blocks.register("pink_agate_plank_stairs", new GDAgatePlankStairs(pinkPlanks.getDefaultState()));
        blocks.register("blue_agate_plank_stairs", new GDAgatePlankStairs(bluePlanks.getDefaultState()));
        blocks.register("green_agate_plank_stairs", new GDAgatePlankStairs(greenPlanks.getDefaultState()));
        blocks.register("purple_agate_plank_stairs", new GDAgatePlankStairs(purplePlanks.getDefaultState()));
        blocks.register("fossilized_plank_stairs", new GDAgatePlankStairs(fossilPlanks.getDefaultState()));
        blocks.register("corrupted_plank_stairs", new GDAgatePlankStairs(corruptPlanks.getDefaultState()));
        blocks.register("burnt_plank_stairs", new GDAgatePlankStairs(burntPlanks.getDefaultState()));
        blocks.register("burning_plank_stairs", new GDAgatePlankStairs(firePlanks.getDefaultState()).setLightLevel(0.5F));

        //Manufactured Blocks
        blocks.register("gaia_stone_bricks", new GDBlock(Material.ROCK, MapColor.MAGENTA).setHardness(2.0F).setResistance(20.0F));
        blocks.register("cracked_gaia_stone_bricks", new GDBlock(Material.ROCK, MapColor.MAGENTA).setHardness(2.0F).setResistance(20.0F));
        blocks.register("crusted_gaia_stone_bricks", new GDBlock(Material.ROCK, MapColor.MAGENTA).setHardness(2.0F).setResistance(20.0F));
        blocks.register("reinforced_bricks", new GDBlock(Material.ROCK, MapColor.PURPLE).setHardness(10.0F).setResistance(100.0F));
        blocks.register("malachite_bricks", new GDBlock(Material.ROCK, MapColor.GREEN).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_cracked_bricks", new GDBlock(Material.ROCK, MapColor.GREEN).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_crusted_bricks", new GDBlock(Material.ROCK, MapColor.GREEN).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_floor_tiles", new GDBlock(Material.ROCK, MapColor.GREEN).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_chisel_bricks", new GDBlock(Material.ROCK, MapColor.GREEN).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_pulsing_bricks", new GDBlock(Material.ROCK, MapColor.GREEN).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_pulsing_tiles", new GDBlock(Material.ROCK, MapColor.GREEN).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_pulsing_chisel", new GDBlock(Material.ROCK, MapColor.GREEN).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_brick_slab", new GDMalachiteBrickSlab(false));
        blocks.register("double_malachite_brick_slab", new GDMalachiteBrickSlab(true));
        blocks.register("malachite_floor_slab", new GDMalachiteBrickSlab(false));
        blocks.register("double_malachite_floor_slab", new GDMalachiteBrickSlab(true));
        blocks.register("malachite_pillar", new GDMalachiteBrickPillar());
        Block malachiteBricks = new GDBlock(Material.ROCK, MapColor.GREEN);
        blocks.register("malachite_brick_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        blocks.register("malachite_chisel_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        blocks.register("malachite_pulsing_brick_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        blocks.register("malachite_pulsing_chisel_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        Block malachitePillar = new GDMalachiteBrickPillar();
        blocks.register("malachite_pillar_stairs", new GDMalachiteStairs(malachitePillar.getDefaultState()));
        blocks.register("malachite_floor_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        blocks.register("malachite_pulsing_floor_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        blocks.register("bolstered_bricks", new GDBlock(Material.ROCK, MapColor.SAND).setHardness(30.0F).setResistance(400.0F));

        //Storage Blocks
        blocks.register("sugilite_block",    new GDStorageBlock(MapColor.PURPLE).setHardness(5.0F).setResistance(10.0F));
        blocks.register("hematite_block",    new GDStorageBlock(MapColor.GRAY).setHardness(5.0F).setResistance(10.0F));
        blocks.register("labradorite_block", new GDStorageBlock(MapColor.GREEN).setHardness(5.0F).setResistance(10.0F));
        blocks.register("opal_block_red",    new GDStorageBlock(MapColor.RED).setHardness(5.0F).setResistance(10.0F));
        blocks.register("opal_block_blue",   new GDStorageBlock(MapColor.LIGHT_BLUE).setHardness(5.0F).setResistance(10.0F));
        blocks.register("opal_block_green",  new GDStorageBlock(MapColor.LIME).setHardness(5.0F).setResistance(10.0F));
        blocks.register("opal_block_white",  new GDStorageBlock(MapColor.SNOW).setHardness(5.0F).setResistance(10.0F));
        blocks.register("pyrite_block",      new GDStorageBlock(MapColor.GOLD).setHardness(5.0F).setResistance(10.0F).setLightLevel(1.0F));
        blocks.register("moonstone_block",   new GDStorageBlock(MapColor.SILVER).setHardness(5.0F).setResistance(10.0F));
        blocks.register("cinnabar_block",    new GDStorageBlock(MapColor.ADOBE).setHardness(5.0F).setResistance(10.0F));
        blocks.register("tektite_block",     new GDStorageBlock(MapColor.BLACK).setHardness(5.0F).setResistance(10.0F));
        blocks.register("goldstone_block",   new GDStorageBlock(MapColor.BLACK).setHardness(5.0F).setResistance(10.0F));
        blocks.register("aura_block",        new GDStorageBlock(MapColor.ICE).setHardness(5.0F).setResistance(10.0F));
        blocks.register("bismuth_block",     new GDStorageBlock(MapColor.OBSIDIAN).setHardness(5.0F).setResistance(10.0F));
        blocks.register("ixiolite_block",    new GDStorageBlock(MapColor.GRAY).setHardness(5.0F).setResistance(10.0F));
        blocks.register("proustite_block",   new GDStorageBlock(MapColor.MAGENTA).setHardness(5.0F).setResistance(10.0F));
        blocks.register("euclase_block",     new GDStorageBlock(MapColor.LIME).setHardness(5.0F).setResistance(10.0F));
        blocks.register("leucite_block",     new GDStorageBlock(MapColor.SAND).setHardness(5.0F).setResistance(10.0F));
        blocks.register("carnelian_block",   new GDStorageBlock(MapColor.RED).setHardness(5.0F).setResistance(10.0F));
        blocks.register("benitoite_block",   new GDStorageBlock(MapColor.BLUE).setHardness(5.0F).setResistance(10.0F));
        blocks.register("diopside_block",    new GDStorageBlock(MapColor.LIME).setHardness(5.0F).setResistance(10.0F));
        blocks.register("chalcedony_block",  new GDStorageBlock(MapColor.SNOW).setHardness(5.0F).setResistance(10.0F));

        //Ores
        blocks.register("sugilite_ore",    new GDOre(MapColor.PURPLE, () -> GDItems.sugilite).setHardness(4.0F).setResistance(25.0F));
        blocks.register("hematite_ore",    new GDOre(MapColor.GRAY, () -> GDItems.hematite).setHardness(4.0F).setResistance(25.0F));
        blocks.register("pyrite_ore",      new GDOre(MapColor.GOLD, () -> GDItems.pyrite).setHardness(4.0F).setResistance(25.0F));
        blocks.register("opal_ore_red",    new GDOre(MapColor.RED, () -> GDItems.red_opal).setHardness(4.0F).setResistance(25.0F));
        blocks.register("opal_ore_blue",   new GDOre(MapColor.LIGHT_BLUE, () -> GDItems.blue_opal).setHardness(4.0F).setResistance(25.0F));
        blocks.register("opal_ore_green",  new GDOre(MapColor.LIME, () -> GDItems.green_opal).setHardness(4.0F).setResistance(25.0F));
        blocks.register("opal_ore_white",  new GDOre(MapColor.SNOW, () -> GDItems.white_opal).setHardness(4.0F).setResistance(25.0F));
        blocks.register("labradorite_ore", new GDOre(MapColor.GREEN, () -> GDItems.labradorite).setHardness(4.0F).setResistance(25.0F));
        blocks.register("moonstone_ore",   new GDOre(MapColor.SILVER, () -> GDItems.moonstone).setHardness(4.0F).setResistance(25.0F));
        blocks.register("cinnabar_ore",    new GDOre(MapColor.ADOBE, () -> GDItems.cinnabar).setHardness(4.0F).setResistance(25.0F));
        blocks.register("speckled_rock",   new GDOre(MapColor.MAGENTA, null).setHardness(4.0F).setResistance(25.0F));
        blocks.register("coarse_rock",     new GDOre(MapColor.MAGENTA, null).setHardness(4.0F).setResistance(25.0F));
        blocks.register("precious_rock",   new GDOre(MapColor.MAGENTA, null).setHardness(4.0F).setResistance(25.0F));

        GameRegistry.registerTileEntity(TileEntitySmallCrate.class, new ResourceLocation("gaiadimension:small_crate"));
        GameRegistry.registerTileEntity(TileEntityLargeCrate.class, new ResourceLocation("gaiadimension:large_crate"));
        GameRegistry.registerTileEntity(TileEntityGaiaStoneFurnace.class, new ResourceLocation("gaiadimension:gaia_stone_furnace"));
        GameRegistry.registerTileEntity(TileEntityRestructurer.class, new ResourceLocation("gaiadimension:restructurer"));
        GameRegistry.registerTileEntity(TileEntityPurifier.class, new ResourceLocation("gaiadimension:purifier"));
    }

    public static List<ModelRegisterCallback> getBlockModels() {
        return ImmutableList.copyOf(BlockRegistryHelper.blockModels);
    }

    private static class BlockRegistryHelper {
        private final IForgeRegistry<Block> registry;

        private static List<ModelRegisterCallback> blockModels = new ArrayList<>();

        BlockRegistryHelper(IForgeRegistry<Block> registry) {
            this.registry = registry;
        }

        private void register(String registryName, Block block) {
            block.setRegistryName(GaiaDimension.MODID, registryName);
            block.setTranslationKey(GaiaDimension.MODID + "." + registryName);

            if (block instanceof ModelRegisterCallback) {
                blockModels.add((ModelRegisterCallback) block);
            }
            registry.register(block);
        }
    }
}
