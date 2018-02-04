package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.block.*;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber
public final class GDBlocksRegister {
    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        BlockRegistryHelper blocks = new BlockRegistryHelper(event.getRegistry());

        blocks.register("gaia_portal", (new GDGaiaPortal()).setUnlocalizedName("gaia_portal"));

        blocks.register("heavy_soil", (new GDHeavySoil()).setUnlocalizedName("heavy_soil"));
        blocks.register("glitter_grass", (new GDGlitterGrass()).setUnlocalizedName("glitter_grass"));
        blocks.register("crystal_growth", (new GDCrystalGrowth()).setUnlocalizedName("crystal_growth"));
        blocks.register("gaia_sapling", (new GDGaiaSapling()).setUnlocalizedName("gaia_sapling"));
        blocks.register("gaia_leaves", (new GDGaiaLeaves()).setUnlocalizedName("gaia_leaves"));
        blocks.register("special_gaia_leaves", (new GDSpecialLeaves()).setUnlocalizedName("special_gaia_leaves"));
        blocks.register("gaia_log", (new GDGaiaLog()).setUnlocalizedName("gaia_log"));
        blocks.register("special_gaia_log", (new GDSpecialLog()).setUnlocalizedName("special_gaia_log"));

        blocks.register("pink_agate_planks", (new GDAgatePlanks()).setUnlocalizedName("pink_agate_planks"));
        blocks.register("blue_agate_planks", (new GDAgatePlanks()).setUnlocalizedName("blue_agate_planks"));
        blocks.register("green_agate_planks", (new GDAgatePlanks()).setUnlocalizedName("green_agate_planks"));
        blocks.register("purple_agate_planks", (new GDAgatePlanks()).setUnlocalizedName("purple_agate_planks"));
        blocks.register("fossilized_planks", (new GDAgatePlanks()).setUnlocalizedName("fossilized_planks"));
        blocks.register("corrupted_planks", (new GDAgatePlanks()).setUnlocalizedName("corrupted_planks"));
        blocks.register("crusty_planks", (new GDAgatePlanks()).setUnlocalizedName("crusty_planks"));
        blocks.register("heated_planks", (new GDAgatePlanks()).setLightLevel(5).setUnlocalizedName("heated_planks"));
        blocks.register("pink_agate_plank_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("pink_agate_plank_slab"));
        blocks.register("blue_agate_plank_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("blue_agate_plank_slab"));
        blocks.register("green_agate_plank_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("green_agate_plank_slab"));
        blocks.register("purple_agate_plank_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("purple_agate_plank_slab"));
        blocks.register("fossilized_plank_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("fossilized_plank_slab"));
        blocks.register("corrupted_plank_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("corrupted_plank_slab"));
        blocks.register("crusty_plank_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("crusty_plank_slab"));
        blocks.register("heated_plank_slab", (new GDAgatePlankSlab(false)).setLightLevel(5).setUnlocalizedName("heated_plank_slab"));
        blocks.register("double_pink_agate_plank_slab", (new GDAgatePlankSlab(true)).setUnlocalizedName("pink_agate_plank_slab"));
        blocks.register("double_blue_agate_planks_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("blue_agate_plank_slab"));
        blocks.register("double_green_agate_planks_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("green_agate_plank_slab"));
        blocks.register("double_purple_agate_planks_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("purple_agate_plank_slab"));
        blocks.register("double_fossilized_planks_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("fossilized_plank_slab"));
        blocks.register("double_corrupted_planks_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("corrupted_plank_slab"));
        blocks.register("double_crusty_planks_slab", (new GDAgatePlankSlab(false)).setUnlocalizedName("crusty_plank_slab"));
        blocks.register("double_heated_planks_slab", (new GDAgatePlankSlab(false)).setLightLevel(5).setUnlocalizedName("heated_plank_slab"));

        blocks.register("gaia_stone", (new GDGaiaStone()).setUnlocalizedName("gaia_stone"));
        blocks.register("gaia_stone_bricks", (new GDGaiaStoneBricks()).setUnlocalizedName("gaia_stone_bricks"));
        blocks.register("malachite_bricks", (new GDMalachiteBricks()).setUnlocalizedName("malachite_bricks"));
        blocks.register("malachite_brick_slab", (new GDMalachiteBrickSlab(false)).setUnlocalizedName("malachite_brick_slab"));
        blocks.register("double_malachite_brick_slab", (new GDMalachiteBrickSlab(true)).setUnlocalizedName("double_malachite_brick_slab"));
        blocks.register("malachite_pillar", new GDMalachiteBrickPillar().setUnlocalizedName("malachite_pillar"));
        Block malachitePillar = new GDMalachiteBrickPillar();
        blocks.register("malachite_stairs", (new GDMalachitePillarStairs(malachitePillar.getDefaultState())).setUnlocalizedName("malachite_stairs"));
        blocks.register("volcanic_rock", (new GDVolcanicRock()).setUnlocalizedName("volcanic_rock"));

        blocks.register("hematite_block", (new GDHematiteBlock()).setUnlocalizedName("hematite_block"));
        blocks.register("labradorite_block", (new GDLabradoriteBlock()).setUnlocalizedName("laboradorite_block"));
        blocks.register("opal_block", (new GDOpalBlock()).setUnlocalizedName("opal_block"));
        blocks.register("pyrite_block", (new GDPyriteBlock()).setUnlocalizedName("pyrite_block"));
        blocks.register("moonstone_block", (new GDMoonstoneBlock()).setUnlocalizedName("moonstone_block"));
        blocks.register("cinnabar_block", (new GDCinnabarBlock()).setUnlocalizedName("cinnabar_block"));
        blocks.register("tektite_block", (new GDTektiteBlock()).setUnlocalizedName("tektite_block"));

        blocks.register("hematite_ore", (new GDHematiteOre()).setUnlocalizedName("hematite_ore"));
        blocks.register("pyrite_ore", (new GDPyriteOre()).setUnlocalizedName("pyrite_ore"));
        blocks.register("opal_ore", (new GDOpalOre()).setUnlocalizedName("opal_ore"));
        blocks.register("labradorite_ore", (new GDLabradoriteOre()).setUnlocalizedName("labradorite_ore"));
        blocks.register("moonstone_ore", (new GDMoonstoneOre()).setUnlocalizedName("moonstone_ore"));
        blocks.register("cinnabar_ore", (new GDCinnabarOre()).setUnlocalizedName("cinnabar_ore"));
    }

    private static class BlockRegistryHelper {
        private final IForgeRegistry<Block> registry;

        BlockRegistryHelper(IForgeRegistry<Block> registry) {
            this.registry = registry;
        }

        private void register(String registryName, Block block) {
            block.setRegistryName(GaiaDimension.MODID, registryName);
            registry.register(block);
        }
    }
}
