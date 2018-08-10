package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.block.*;
import androsa.gaiadimension.block.blocksgrass.*;
import androsa.gaiadimension.block.blocksore.*;
import androsa.gaiadimension.block.tileentity.TileEntityGaiaStoneFurnace;
import androsa.gaiadimension.block.tileentity.TileEntityGlitterFurnace;
import androsa.gaiadimension.block.tileentity.TileEntityPurifier;
import androsa.gaiadimension.fluid.GDFluidBlock;
import androsa.gaiadimension.fluid.GDSuperhotMagma;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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
        blocks.register("gold_fire", new GDGoldFire());
        blocks.register("pyrite_torch", new GDPyriteTorch());
        blocks.register("agate_crafting_table", new GDAgateCraftingTable());
        blocks.register("gaia_stone_furnace_idle", new GDGaiaStoneFurnace(false));
        blocks.register("gaia_stone_furnace_lit", new GDGaiaStoneFurnace(true));
        blocks.register("glitter_furnace_idle", new GDGlitterFurnace(false));
        blocks.register("glitter_furnace_lit", new GDGlitterFurnace(true));
        blocks.register("purifier_idle", new GDPurifier(false));
        blocks.register("purifier_lit", new GDPurifier(true));
        blocks.register("mineral_water_block", new GDFluidBlock(GDFluids.mineralWater, Material.WATER));
        blocks.register("superhot_magma_block", new GDSuperhotMagma(GDFluids.superhotMagma, Material.LAVA));
        blocks.register("sweet_muck_block", new GDFluidBlock(GDFluids.sweetMuck, Material.WATER));

        //Natural Blocks
        blocks.register("heavy_soil", new GDHeavySoil());
        blocks.register("corrupt_soil", new GDCorruptSoil());
        blocks.register("glitter_grass", new GDGrassGlitter());
        blocks.register("cool_grass", new GDGrassCool());
        blocks.register("verdant_grass", new GDGrassVerdant());
        blocks.register("scented_grass", new GDGrassScented());
        blocks.register("old_grass", new GDGrassOld());
        blocks.register("corrupt_grass", new GDCorruptGrass());
        blocks.register("singed_grass", new GDGrassSinged());
        blocks.register("mutated_grass", new GDGrassMutated());
        blocks.register("frail_glitter_block", new GDFrailGlitterBlock());
        blocks.register("thick_glitter_block", new GDThickGlitterBlock());
        blocks.register("gummy_glitter_block", new GDGummyGlitterBlock());

        //Crystal Growth
        blocks.register("crystal_growth_pink", new GDCrystalGrowth());
        blocks.register("crystal_growth_blue", new GDCrystalGrowth());
        blocks.register("crystal_growth_green", new GDCrystalGrowth());
        blocks.register("crystal_growth_purple", new GDCrystalGrowth());
        blocks.register("crystal_growth_old", new GDCrystalGrowth());
        blocks.register("crystal_growth_red", new GDCrystalGrowth());
        blocks.register("crystal_growth_black", new GDCrystalGrowth());
        blocks.register("crystal_growth_seared", new GDCrystalGrowth());
        blocks.register("crystal_growth_mutant", new GDCrystalGrowth());

        //Crystal Bloom
        blocks.register("thiscus", new GDCrystalBloom());
        blocks.register("ouzium", new GDCrystalBloom());
        blocks.register("agathum", new GDCrystalBloom());
        blocks.register("varloom", new GDCrystalBloom());
        blocks.register("corrupt_varloom", new GDCrystalBloom());
        blocks.register("missingno_plant", new GDCrystalBloom());

        blocks.register("gaia_sapling", new GDAgateSapling());
        blocks.register("gaia_leaves", new GDAgateLeaves());
        blocks.register("special_gaia_leaves", new GDSpecialLeaves());
        blocks.register("gaia_log", new GDAgateLog());
        blocks.register("special_gaia_log", new GDSpecialLog());
        blocks.register("salt", new GDSaltBlock());
        blocks.register("saltstone", new GDSaltRock());
        blocks.register("gaia_stone", new GDGaiaStone());
        blocks.register("gaia_cobblestone", new GDGaiaStone());
        blocks.register("wasteland_stone", new GDWastelandStone());
        blocks.register("static_stone", new GDStaticStone());
        blocks.register("charged_mineral", new GDChargedMineral());
        blocks.register("volcanic_rock", new GDVolcanicRock());
        blocks.register("searing_rock", new GDSearingRock());

        //Planks
        blocks.register("pink_agate_planks", new GDAgatePlanks());
        blocks.register("blue_agate_planks", new GDAgatePlanks());
        blocks.register("green_agate_planks", new GDAgatePlanks());
        blocks.register("purple_agate_planks", new GDAgatePlanks());
        blocks.register("fossilized_planks", new GDAgatePlanks());
        blocks.register("corrupted_planks", new GDAgatePlanks());
        blocks.register("crusty_planks", new GDAgatePlanks());
        blocks.register("heated_planks", (new GDAgatePlanks()).setLightLevel(0.5F));
        blocks.register("pink_agate_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("blue_agate_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("green_agate_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("purple_agate_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("fossilized_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("corrupted_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("crusty_plank_slab", new GDAgatePlankSlab(false));
        blocks.register("heated_plank_slab", (new GDAgatePlankSlab(false)).setLightLevel(0.5F));
        blocks.register("double_pink_agate_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_blue_agate_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_green_agate_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_purple_agate_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_fossilized_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_corrupted_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_crusty_plank_slab", new GDAgatePlankSlab(true));
        blocks.register("double_heated_plank_slab", (new GDAgatePlankSlab(true)).setLightLevel(0.5F));
        Block agatePlanks = new GDAgatePlanks();
        blocks.register("pink_agate_plank_stairs", new GDAgatePlankStairs(agatePlanks.getDefaultState()));
        blocks.register("blue_agate_plank_stairs", new GDAgatePlankStairs(agatePlanks.getDefaultState()));
        blocks.register("green_agate_plank_stairs", new GDAgatePlankStairs(agatePlanks.getDefaultState()));
        blocks.register("purple_agate_plank_stairs", new GDAgatePlankStairs(agatePlanks.getDefaultState()));
        blocks.register("fossilized_plank_stairs", new GDAgatePlankStairs(agatePlanks.getDefaultState()));
        blocks.register("corrupted_plank_stairs", new GDAgatePlankStairs(agatePlanks.getDefaultState()));
        blocks.register("crusty_plank_stairs", new GDAgatePlankStairs(agatePlanks.getDefaultState()));
        blocks.register("heated_plank_stairs", (new GDAgatePlankStairs(agatePlanks.getDefaultState())).setLightLevel(0.5F));

        //Manufactured Blocks
        blocks.register("gaia_stone_bricks", new GDGaiaStoneBricks());
        blocks.register("cracked_gaia_stone_bricks", new GDGaiaStoneBricks());
        blocks.register("crusted_gaia_stone_bricks", new GDGaiaStoneBricks());
        blocks.register("reinforced_bricks", new GDReinforcedBricks());
        blocks.register("malachite_bricks", new GDMalachiteBricks());
        blocks.register("malachite_pulsing_bricks", new GDMalachitePulseBricks());
        blocks.register("malachite_pulsing_tiles", new GDMalachitePulseBricks());
        blocks.register("malachite_pulsing_chisel", new GDMalachitePulseBricks());
        blocks.register("malachite_brick_slab", new GDMalachiteBrickSlab(false));
        blocks.register("double_malachite_brick_slab", new GDMalachiteBrickSlab(true));
        blocks.register("malachite_floor_slab", new GDMalachiteBrickSlab(false));
        blocks.register("double_malachite_floor_slab", new GDMalachiteBrickSlab(true));
        blocks.register("malachite_pillar", new GDMalachiteBrickPillar());
        Block malachiteBricks = new GDMalachiteBricks();
        blocks.register("malachite_brick_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        blocks.register("malachite_chisel_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        blocks.register("malachite_pulsing_brick_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        blocks.register("malachite_pulsing_chisel_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        Block malachitePillar = new GDMalachiteBrickPillar();
        blocks.register("malachite_pillar_stairs", new GDMalachiteStairs(malachitePillar.getDefaultState()));
        blocks.register("malachite_floor_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        blocks.register("malachite_pulsing_floor_stairs", new GDMalachiteStairs(malachiteBricks.getDefaultState()));
        blocks.register("bolstered_bricks", new GDBolsteredBricks());

        //Storage Blocks
        blocks.register("sugilite_block", new GDBlockStorage());
        blocks.register("hematite_block", new GDBlockStorage());
        blocks.register("labradorite_block", new GDBlockStorage());
        blocks.register("opal_block_red", new GDBlockStorage());
        blocks.register("opal_block_blue", new GDBlockStorage());
        blocks.register("opal_block_green", new GDBlockStorage());
        blocks.register("opal_block_white", new GDBlockStorage());
        blocks.register("pyrite_block", (new GDBlockStorage()).setLightLevel(1.0F));
        blocks.register("moonstone_block", new GDBlockStorage());
        blocks.register("cinnabar_block", new GDBlockStorage());
        blocks.register("tektite_block", new GDBlockStorage());
        blocks.register("goldstone_block", new GDBlockStorage());
        blocks.register("ixiolite_block", new GDBlockStorage());
        blocks.register("proustite_block", new GDBlockStorage());
        blocks.register("euclase_block", new GDBlockStorage());
        blocks.register("leucite_block", new GDBlockStorage());
        blocks.register("carnelian_block", new GDBlockStorage());
        blocks.register("benitoite_block", new GDBlockStorage());
        blocks.register("diopside_block", new GDBlockStorage());
        blocks.register("chalcedony_block", new GDBlockStorage());

        //Ores
        blocks.register("sugilite_ore", new GDOreSugilite());
        blocks.register("hematite_ore", new GDOreHematite());
        blocks.register("pyrite_ore", new GDOrePyrite());
        blocks.register("opal_ore", new GDOreOpal());
        blocks.register("labradorite_ore", new GDOreLabradorite());
        blocks.register("moonstone_ore", new GDOreMoonstone());
        blocks.register("cinnabar_ore", new GDOreCinnabar());
        blocks.register("speckled_rock", new GDScaynyxOre());
        blocks.register("coarse_rock", new GDScaynyxOre());
        blocks.register("precious_rock", new GDScaynyxOre());

        GameRegistry.registerTileEntity(TileEntityGaiaStoneFurnace.class, new ResourceLocation("gaiadimension:gaia_stone_furnace"));
        GameRegistry.registerTileEntity(TileEntityGlitterFurnace.class, new ResourceLocation("gaiadimension:glitter_furnace"));
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
            block.setUnlocalizedName(GaiaDimension.MODID + "." + registryName);

            if (block instanceof ModelRegisterCallback) {
                blockModels.add((ModelRegisterCallback) block);
            }
            registry.register(block);
        }
    }
}
