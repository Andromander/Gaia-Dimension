package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.block.GDCorruptGrass;
import androsa.gaiadimension.block.*;
import androsa.gaiadimension.block.tileentity.*;
import androsa.gaiadimension.fluid.GDFluidBlock;
import androsa.gaiadimension.fluid.GDLiquidAura;
import androsa.gaiadimension.fluid.GDLiquidBismuth;
import androsa.gaiadimension.fluid.GDSuperhotMagma;
import androsa.gaiadimension.world.gen.*;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
        blocks.register("keystone_block", new GDBlock(Material.IRON, MapColor.GOLD, SoundType.METAL, "pickaxe", 2) {
            @Override
            @SideOnly(Side.CLIENT)
            public BlockRenderLayer getRenderLayer() {
                return BlockRenderLayer.CUTOUT;
            }
        }.setHardness(3.0F).setResistance(5.0F));
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
        blocks.register("mineral_water_block", new GDFluidBlock(GDFluids.mineralWater, Material.WATER, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY));
        blocks.register("superhot_magma_block", new GDSuperhotMagma(GDFluids.superhotMagma, Material.LAVA).setLightLevel(1.0F));
        blocks.register("sweet_muck_block", new GDFluidBlock(GDFluids.sweetMuck, Material.WATER, MapColor.PURPLE));
        blocks.register("liquid_bismuth_block", new GDLiquidBismuth(GDFluids.liquidBismuth, Material.LAVA));
        blocks.register("liquid_aura_block", new GDLiquidAura(GDFluids.liquidAura, Material.WATER));

        //Natural Blocks
        blocks.register("heavy_soil", new GDGaiaSoil(MapColor.PURPLE_STAINED_HARDENED_CLAY));
        blocks.register("corrupt_soil", new GDGaiaSoil(MapColor.GRAY));
        blocks.register("boggy_soil", new GDGaiaSoil(MapColor.GRAY));
        blocks.register("light_soil", new GDGaiaSoil(MapColor.GOLD));
        blocks.register("glitter_grass", new GDGlitterGrass());
        blocks.register("corrupt_grass", new GDCorruptGrass());
        blocks.register("murky_grass", new GDMurkyGrass());
        blocks.register("soft_grass", new GDSoftGrass());
        blocks.register("frail_glitter_block", new GDFrailGlitterBlock());
        blocks.register("thick_glitter_block", new GDBlock(Material.ROCK, MapColor.PURPLE_STAINED_HARDENED_CLAY, "pickaxe", 1).setHardness(1.5F).setResistance(7.5F));
        blocks.register("gummy_glitter_block", new GDGummyGlitterBlock());
        blocks.register("pink_sludge_block", new GDPinkSludgeBlock());

        //Plants
        blocks.register("crystal_growth", new GDCrystalGrowth());
        blocks.register("crystal_growth_red", new GDCrystalGrowth());
        blocks.register("crystal_growth_black", new GDCrystalGrowth());
        blocks.register("crystal_growth_seared", new GDCrystalGrowth());
        blocks.register("crystal_growth_mutant", new GDCrystalGrowth());
        blocks.register("crystal_growth_aura", new GDCrystalGrowth());
        blocks.register("thiscus", new GDCrystalBloom());
        blocks.register("ouzium", new GDCrystalBloom());
        blocks.register("agathum", new GDCrystalBloom());
        blocks.register("varloom", new GDCrystalBloom());
        blocks.register("corrupt_varloom", new GDCrystalBloom());
        blocks.register("missingno_plant", new GDCrystalBloom());
        blocks.register("spotted_kersei", new GDCrystalFungus(MapColor.PINK, false));
        blocks.register("thorny_wiltha", new GDCrystalFungus(MapColor.LIGHT_BLUE, false));
        blocks.register("roofed_agaric", new GDCrystalFungus(MapColor.LIME, false));
        blocks.register("bulbous_hobina", new GDCrystalFungus(MapColor.PINK_STAINED_HARDENED_CLAY, false));
        blocks.register("stickly_cupsir", new GDCrystalFungus(MapColor.YELLOW_STAINED_HARDENED_CLAY, false));
        blocks.register("mystical_murgni", new GDCrystalFungus(MapColor.GOLD, false));
        blocks.register("corrupted_gaia_eye", new GDCrystalFungus(MapColor.TNT, false));
        //blocks.register("sacred_gaia_eye", new GDCrystalFungus(false));
        blocks.register("elder_imklia", new GDCrystalFungus(MapColor.PURPLE, true));
        blocks.register("gold_orb_tucher", new GDCrystalFungus(MapColor.GOLD, true));
        blocks.register("missingno_fungus", new GDCrystalFungus(MapColor.MAGENTA, false));

        blocks.register("pink_agate_sapling", new GDAgateSapling(() -> new GDGenPinkAgateTree(true)));
        blocks.register("blue_agate_sapling", new GDAgateSapling(() -> new GDGenBlueAgateTree(true)));
        blocks.register("green_agate_sapling", new GDAgateSapling(() -> new GDGenGreenAgateTree(true)));
        blocks.register("purple_agate_sapling", new GDAgateSapling(() -> new GDGenPurpleAgateTree(true)));
        blocks.register("fossilized_sapling", new GDAgateSapling(() -> new GDGenFossilizedTree(true)));
        blocks.register("corrupted_sapling", new GDAgateSapling(() -> new GDGenGoldstoneCorruptTree(true)));
        blocks.register("burnt_sapling", new GDAgateSapling(() -> new GDGenBurntAgateTree(true)));
        blocks.register("burning_sapling", new GDAgateSapling(() -> new GDGenFieryAgateTree(true)));
        blocks.register("aura_sapling", new GDAgateSapling(() -> new GDGenAuraTree(true)));
        blocks.register("pink_agate_leaves", new GDAgateLeaves(() -> Item.getItemFromBlock(GDBlocks.pink_agate_sapling), MapColor.MAGENTA));
        blocks.register("blue_agate_leaves", new GDAgateLeaves(() -> Item.getItemFromBlock(GDBlocks.blue_agate_sapling), MapColor.BLUE));
        blocks.register("green_agate_leaves", new GDAgateLeaves(() -> Item.getItemFromBlock(GDBlocks.green_agate_sapling), MapColor.GREEN));
        blocks.register("purple_agate_leaves", new GDAgateLeaves(() -> Item.getItemFromBlock(GDBlocks.purple_agate_sapling), MapColor.PURPLE_STAINED_HARDENED_CLAY));
        blocks.register("fossilized_leaves", new GDAgateLeaves(() -> GDItems.fine_dust, MapColor.YELLOW));
        blocks.register("corrupted_leaves", new GDAgateLeaves(() -> GDItems.goldstone_dust, MapColor.TNT));
        blocks.register("burnt_leaves", new GDAgateLeaves(() -> Items.GUNPOWDER, MapColor.GRAY));
        blocks.register("burning_leaves", new GDAgateLeaves(() -> GDItems.hot_dust, MapColor.ORANGE_STAINED_HARDENED_CLAY).setLightLevel(0.3F));
        blocks.register("aura_leaves", new GDAgateLeaves(() -> Item.getItemFromBlock(GDBlocks.aura_sapling), MapColor.SILVER));
        blocks.register("pink_agate_log", new GDAgateLog(MapColor.PINK_STAINED_HARDENED_CLAY));
        blocks.register("blue_agate_log", new GDAgateLog(MapColor.BLUE_STAINED_HARDENED_CLAY));
        blocks.register("green_agate_log", new GDAgateLog(MapColor.LIME_STAINED_HARDENED_CLAY));
        blocks.register("purple_agate_log", new GDAgateLog(MapColor.PURPLE));
        blocks.register("fossilized_log", new GDAgateLog(MapColor.DIRT));
        blocks.register("corrupted_log", new GDAgateLog(MapColor.GRAY_STAINED_HARDENED_CLAY));
        blocks.register("burnt_log", new GDAgateLog(MapColor.BLACK_STAINED_HARDENED_CLAY));
        blocks.register("burning_log", new GDAgateLog(MapColor.ORANGE_STAINED_HARDENED_CLAY).setLightLevel(0.3F));
        blocks.register("aura_log", new GDAgateLog(MapColor.SILVER));
        blocks.register("salt", new GDSaltBlock());
        blocks.register("saltstone", new GDBlock(Material.ROCK, MapColor.LIGHT_BLUE_STAINED_HARDENED_CLAY, "pickaxe", 0).setHardness(1.5F).setResistance(10.0F));
        blocks.register("pebbles", new GDPebblesBlock());
        blocks.register("gaia_stone", new GDBlock(Material.ROCK, MapColor.MAGENTA, "pickaxe", 1).setHardness(2.0F).setResistance(15.0F));
        blocks.register("gaia_cobblestone", new GDBlock(Material.ROCK, MapColor.MAGENTA, "pickaxe", 1).setHardness(2.0F).setResistance(15.0F));
        blocks.register("wasteland_stone", new GDBlock(Material.ROCK, MapColor.BLUE_STAINED_HARDENED_CLAY, "pickaxe", 2).setHardness(15.0F).setResistance(200.0F));
        blocks.register("static_stone", new GDStaticStone());
        blocks.register("charged_mineral", new GDChargedMineral());
        blocks.register("volcanic_rock", new GDBlock(Material.ROCK, MapColor.GRAY_STAINED_HARDENED_CLAY, "pickaxe", 2).setHardness(15.0F).setResistance(200.0F));
        blocks.register("searing_rock", new GDSearingRock());
        blocks.register("primal_mass", new GDBlock(Material.ROCK, MapColor.PURPLE_STAINED_HARDENED_CLAY, "pickaxe", 2).setHardness(30.0F).setResistance(400.0F));
        blocks.register("impure_rock", new GDBlock(Material.ROCK, MapColor.GRAY, "pickaxe", 2).setHardness(20.0F).setResistance(300.0F));
        blocks.register("active_rock", new GDActiveRock());
        blocks.register("impure_sludge", new GDImpureSludge());
        blocks.register("geyser_block", new GDGeyserBlock());
        blocks.register("sparkling_rock", new GDBlock(Material.ROCK, MapColor.SILVER, SoundType.GLASS, "pickaxe", 1).setHardness(10.0F).setResistance(150.0F));
        blocks.register("aura_shoot", new GDAuraShoot());

        //Planks
        blocks.register("pink_agate_planks", new GDBlock(Material.WOOD, MapColor.PINK, SoundType.STONE, "axe", 0).setHardness(1.5F).setResistance(2.0F));
        blocks.register("blue_agate_planks", new GDBlock(Material.WOOD, MapColor.LIGHT_BLUE, SoundType.STONE, "axe", 0).setHardness(1.5F).setResistance(2.0F));
        blocks.register("green_agate_planks", new GDBlock(Material.WOOD, MapColor.LIME, SoundType.STONE, "axe", 0).setHardness(1.5F).setResistance(2.0F));
        blocks.register("purple_agate_planks", new GDBlock(Material.WOOD, MapColor.PURPLE_STAINED_HARDENED_CLAY, SoundType.STONE, "axe", 0).setHardness(1.5F).setResistance(2.0F));
        blocks.register("fossilized_planks", new GDBlock(Material.WOOD, MapColor.YELLOW_STAINED_HARDENED_CLAY, SoundType.STONE, "axe", 0).setHardness(1.5F).setResistance(2.0F));
        blocks.register("corrupted_planks", new GDBlock(Material.WOOD, MapColor.BLACK_STAINED_HARDENED_CLAY, SoundType.STONE, "axe", 0).setHardness(1.5F).setResistance(2.0F));
        blocks.register("burnt_planks", new GDBlock(Material.WOOD, MapColor.BLACK, SoundType.STONE, "axe", 0).setHardness(1.5F).setResistance(2.0F));
        blocks.register("burning_planks", new GDBlock(Material.WOOD, MapColor.ORANGE_STAINED_HARDENED_CLAY, SoundType.STONE, "axe", 0).setHardness(1.5F).setResistance(2.0F).setLightLevel(0.5F));
        blocks.register("aura_planks", new GDBlock(Material.WOOD, MapColor.SILVER, SoundType.STONE, "axe", 0).setHardness(1.5F).setResistance(2.0F));
        blocks.register("pink_agate_plank_slab",   new GDAgatePlankSlab(false, MapColor.PINK, null).setHardness(1.5F).setResistance(2.0F));
        blocks.register("blue_agate_plank_slab",   new GDAgatePlankSlab(false, MapColor.LIGHT_BLUE, null).setHardness(1.5F).setResistance(2.0F));
        blocks.register("green_agate_plank_slab",  new GDAgatePlankSlab(false, MapColor.LIME, null).setHardness(1.5F).setResistance(2.0F));
        blocks.register("purple_agate_plank_slab", new GDAgatePlankSlab(false, MapColor.PURPLE_STAINED_HARDENED_CLAY, null).setHardness(1.5F).setResistance(2.0F));
        blocks.register("fossilized_plank_slab",   new GDAgatePlankSlab(false, MapColor.YELLOW_STAINED_HARDENED_CLAY, null).setHardness(1.5F).setResistance(2.0F));
        blocks.register("corrupted_plank_slab",    new GDAgatePlankSlab(false, MapColor.BLACK_STAINED_HARDENED_CLAY, null).setHardness(1.5F).setResistance(2.0F));
        blocks.register("burnt_plank_slab",        new GDAgatePlankSlab(false, MapColor.BLACK, null).setHardness(1.5F).setResistance(2.0F));
        blocks.register("burning_plank_slab",      new GDAgatePlankSlab(false, MapColor.ORANGE_STAINED_HARDENED_CLAY, null).setLightLevel(0.5F).setHardness(1.5F).setResistance(2.0F));
        blocks.register("aura_plank_slab",         new GDAgatePlankSlab(false, MapColor.SILVER, null).setHardness(1.5F).setResistance(2.0F));
        blocks.register("double_pink_agate_plank_slab",   new GDAgatePlankSlab(true, MapColor.PINK, () -> Item.getItemFromBlock(GDBlocks.pink_agate_plank_slab)));
        blocks.register("double_blue_agate_plank_slab",   new GDAgatePlankSlab(true, MapColor.LIGHT_BLUE, () -> Item.getItemFromBlock(GDBlocks.blue_agate_plank_slab)));
        blocks.register("double_green_agate_plank_slab",  new GDAgatePlankSlab(true, MapColor.LIME, () -> Item.getItemFromBlock(GDBlocks.green_agate_plank_slab)));
        blocks.register("double_purple_agate_plank_slab", new GDAgatePlankSlab(true, MapColor.PURPLE_STAINED_HARDENED_CLAY, () -> Item.getItemFromBlock(GDBlocks.purple_agate_plank_slab)));
        blocks.register("double_fossilized_plank_slab",   new GDAgatePlankSlab(true, MapColor.YELLOW_STAINED_HARDENED_CLAY, () -> Item.getItemFromBlock(GDBlocks.fossilized_plank_slab)));
        blocks.register("double_corrupted_plank_slab",    new GDAgatePlankSlab(true, MapColor.BLACK_STAINED_HARDENED_CLAY, () -> Item.getItemFromBlock(GDBlocks.corrupted_plank_slab)));
        blocks.register("double_burnt_plank_slab",        new GDAgatePlankSlab(true, MapColor.BLACK, () -> Item.getItemFromBlock(GDBlocks.burnt_plank_slab)));
        blocks.register("double_burning_plank_slab",      new GDAgatePlankSlab(true, MapColor.ORANGE_STAINED_HARDENED_CLAY, () -> Item.getItemFromBlock(GDBlocks.burning_plank_slab)).setLightLevel(0.5F));
        blocks.register("double_aura_plank_slab",         new GDAgatePlankSlab(true, MapColor.SILVER, () -> Item.getItemFromBlock(GDBlocks.aura_plank_slab)));
        Block pinkPlanks    = new GDBlock(Material.WOOD, MapColor.PINK);
        Block bluePlanks    = new GDBlock(Material.WOOD, MapColor.LIGHT_BLUE);
        Block greenPlanks   = new GDBlock(Material.WOOD, MapColor.LIME);
        Block purplePlanks  = new GDBlock(Material.WOOD, MapColor.PURPLE_STAINED_HARDENED_CLAY);
        Block fossilPlanks  = new GDBlock(Material.WOOD, MapColor.YELLOW_STAINED_HARDENED_CLAY);
        Block corruptPlanks = new GDBlock(Material.WOOD, MapColor.BLACK_STAINED_HARDENED_CLAY);
        Block burntPlanks   = new GDBlock(Material.WOOD, MapColor.BLACK);
        Block firePlanks    = new GDBlock(Material.WOOD, MapColor.ORANGE_STAINED_HARDENED_CLAY).setLightLevel(0.5F);
        Block auraPlanks    = new GDBlock(Material.WOOD, MapColor.SILVER);
        blocks.register("pink_agate_plank_stairs", new GDAgatePlankStairs(pinkPlanks.getDefaultState(), MapColor.PINK_STAINED_HARDENED_CLAY));
        blocks.register("blue_agate_plank_stairs", new GDAgatePlankStairs(bluePlanks.getDefaultState(), MapColor.BLUE_STAINED_HARDENED_CLAY));
        blocks.register("green_agate_plank_stairs", new GDAgatePlankStairs(greenPlanks.getDefaultState(), MapColor.LIME_STAINED_HARDENED_CLAY));
        blocks.register("purple_agate_plank_stairs", new GDAgatePlankStairs(purplePlanks.getDefaultState(), MapColor.PURPLE));
        blocks.register("fossilized_plank_stairs", new GDAgatePlankStairs(fossilPlanks.getDefaultState(), MapColor.DIRT));
        blocks.register("corrupted_plank_stairs", new GDAgatePlankStairs(corruptPlanks.getDefaultState(), MapColor.GRAY_STAINED_HARDENED_CLAY));
        blocks.register("burnt_plank_stairs", new GDAgatePlankStairs(burntPlanks.getDefaultState(), MapColor.BLACK_STAINED_HARDENED_CLAY));
        blocks.register("burning_plank_stairs", new GDAgatePlankStairs(firePlanks.getDefaultState(), MapColor.ORANGE_STAINED_HARDENED_CLAY).setLightLevel(0.5F));
        blocks.register("aura_plank_stairs", new GDAgatePlankStairs(auraPlanks.getDefaultState(), MapColor.SILVER));

        //Manufactured Blocks
        blocks.register("cloudy_glass", new GDGlassBlock());
        blocks.register("foggy_glass", new GDGlassBlock());
        blocks.register("gaia_stone_bricks", new GDBlock(Material.ROCK, MapColor.MAGENTA, "pickaxe", 1).setHardness(2.0F).setResistance(20.0F));
        blocks.register("cracked_gaia_stone_bricks", new GDBlock(Material.ROCK, MapColor.MAGENTA, "pickaxe", 1).setHardness(2.0F).setResistance(20.0F));
        blocks.register("crusted_gaia_stone_bricks", new GDBlock(Material.ROCK, MapColor.MAGENTA, "pickaxe", 1).setHardness(2.0F).setResistance(20.0F));
        blocks.register("reinforced_bricks", new GDBlock(Material.ROCK, MapColor.PURPLE, "pickaxe", 1).setHardness(10.0F).setResistance(100.0F));
        blocks.register("malachite_bricks", new GDBlock(Material.ROCK, MapColor.GREEN, "pickaxe", 2).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_cracked_bricks", new GDBlock(Material.ROCK, MapColor.GREEN, "pickaxe", 2).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_crusted_bricks", new GDBlock(Material.ROCK, MapColor.GREEN, "pickaxe", 2).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_floor_tiles", new GDBlock(Material.ROCK, MapColor.GREEN, "pickaxe", 2).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_chisel_bricks", new GDBlock(Material.ROCK, MapColor.GREEN, "pickaxe", 2).setHardness(20.0F).setResistance(200.0F));
        blocks.register("malachite_pulsing_bricks", new GDMalachitePulseBricks());
        blocks.register("malachite_pulsing_tiles", new GDMalachitePulseBricks());
        blocks.register("malachite_pulsing_chisel", new GDMalachitePulseBricks());
        blocks.register("malachite_brick_slab", new GDMalachiteBrickSlab(false, () -> null));
        blocks.register("double_malachite_brick_slab", new GDMalachiteBrickSlab(true, () -> Item.getItemFromBlock(GDBlocks.malachite_brick_slab)));
        blocks.register("malachite_floor_slab", new GDMalachiteBrickSlab(false, () -> null));
        blocks.register("double_malachite_floor_slab", new GDMalachiteBrickSlab(true, () -> Item.getItemFromBlock(GDBlocks.malachite_floor_slab)));
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
        blocks.register("bolstered_bricks", new GDBlock(Material.ROCK, MapColor.SAND, "pickaxe", 2).setHardness(30.0F).setResistance(400.0F));

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
        blocks.register("sugilite_ore",    new GDOre(MapColor.PURPLE, "pickaxe", 1, () -> GDItems.sugilite).setHardness(4.0F).setResistance(25.0F));
        blocks.register("hematite_ore",    new GDOre(MapColor.GRAY, "pickaxe", 2, () -> GDItems.hematite).setHardness(4.0F).setResistance(25.0F));
        blocks.register("pyrite_ore",      new GDOre(MapColor.GOLD, "pickaxe", 2, () -> GDItems.pyrite).setHardness(4.0F).setResistance(25.0F));
        blocks.register("opal_ore_red",    new GDOre(MapColor.RED, "pickaxe", 2, () -> GDItems.red_opal).setHardness(4.0F).setResistance(25.0F));
        blocks.register("opal_ore_blue",   new GDOre(MapColor.LIGHT_BLUE, "pickaxe", 2, () -> GDItems.blue_opal).setHardness(4.0F).setResistance(25.0F));
        blocks.register("opal_ore_green",  new GDOre(MapColor.LIME, "pickaxe", 2, () -> GDItems.green_opal).setHardness(4.0F).setResistance(25.0F));
        blocks.register("opal_ore_white",  new GDOre(MapColor.SNOW, "pickaxe", 3, () -> GDItems.white_opal).setHardness(4.0F).setResistance(25.0F));
        blocks.register("labradorite_ore", new GDOre(MapColor.GREEN, "pickaxe", 2, () -> GDItems.labradorite).setHardness(4.0F).setResistance(25.0F));
        blocks.register("moonstone_ore",   new GDOre(MapColor.SILVER, "pickaxe", 2, () -> GDItems.moonstone).setHardness(4.0F).setResistance(25.0F));
        blocks.register("cinnabar_ore",    new GDOre(MapColor.ADOBE, "pickaxe", 2, () -> GDItems.cinnabar).setHardness(4.0F).setResistance(25.0F));
        blocks.register("speckled_rock",   new GDOre(MapColor.MAGENTA, "pickaxe", 1, null).setHardness(4.0F).setResistance(25.0F));
        blocks.register("coarse_rock",     new GDOre(MapColor.MAGENTA, "pickaxe", 2, null).setHardness(4.0F).setResistance(25.0F));
        blocks.register("precious_rock",   new GDOre(MapColor.MAGENTA, "pickaxe", 3, null).setHardness(4.0F).setResistance(25.0F));

        GameRegistry.registerTileEntity(TileEntitySmallCrate.class, new ResourceLocation("gaiadimension:small_crate"));
        GameRegistry.registerTileEntity(TileEntityLargeCrate.class, new ResourceLocation("gaiadimension:large_crate"));
        GameRegistry.registerTileEntity(TileEntityGaiaStoneFurnace.class, new ResourceLocation("gaiadimension:gaia_stone_furnace"));
        GameRegistry.registerTileEntity(TileEntityRestructurer.class, new ResourceLocation("gaiadimension:restructurer"));
        GameRegistry.registerTileEntity(TileEntityPurifier.class, new ResourceLocation("gaiadimension:purifier"));
        GameRegistry.registerTileEntity(TileEntityGeyser.class, new ResourceLocation("gaiadimension:geyser"));
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
