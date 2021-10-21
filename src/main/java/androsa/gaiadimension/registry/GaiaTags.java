package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.versions.forge.ForgeVersion;

//TODO: Mining tags
public class GaiaTags {

    private static final String ID = GaiaDimensionMod.MODID;

    private static Tag.Named<Item> tagItem(String name) {
        return ItemTags.bind(new ResourceLocation(ID, name).toString());
    }

    private static Tag.Named<Item> tagItemForge(String name) {
        return ItemTags.bind(new ResourceLocation(ForgeVersion.MOD_ID, name).toString());
    }

    private static Tag.Named<Block> tagBlock(String name) {
        return BlockTags.bind(new ResourceLocation(ID, name).toString());
    }

    private static Tag.Named<Block> tagBlockForge(String name) {
        return BlockTags.bind(new ResourceLocation(ForgeVersion.MOD_ID, name).toString());
    }

    private static Tag.Named<Fluid> tagFluidForge(String name) {
        return FluidTags.bind(new ResourceLocation(ForgeVersion.MOD_ID, name).toString());
    }

    public static class Items {
        public static final Tag.Named<Item> ORES_SUGILITE = tagItemForge("ore/sugilite");
        public static final Tag.Named<Item> ORES_HEMATITE = tagItemForge("ore/hematite");
        public static final Tag.Named<Item> ORES_CINNABAR = tagItemForge("ore/cinnabar");
        public static final Tag.Named<Item> ORES_LABRADORITE = tagItemForge("ore/labradorite");
        public static final Tag.Named<Item> ORES_MOONSTONE = tagItemForge("ore/moonstone");
        public static final Tag.Named<Item> ORES_RED_OPAL = tagItemForge("ore/red_opal");
        public static final Tag.Named<Item> ORES_BLUE_OPAL = tagItemForge("ore/blue_opal");
        public static final Tag.Named<Item> ORES_GREEN_OPAL = tagItemForge("ore/green_opal");
        public static final Tag.Named<Item> ORES_WHITE_OPAL = tagItemForge("ore/white_opal");
        public static final Tag.Named<Item> ORES_PYRITE = tagItemForge("ore/pyrite");
        public static final Tag.Named<Item> STORAGE_BLOCKS_SUGILITE = tagItemForge("storage_blocks/sugilite");
        public static final Tag.Named<Item> STORAGE_BLOCKS_HEMATITE = tagItemForge("storage_blocks/hematite");
        public static final Tag.Named<Item> STORAGE_BLOCKS_CINNABAR = tagItemForge("storage_blocks/cinnabar");
        public static final Tag.Named<Item> STORAGE_BLOCKS_LABRADORITE = tagItemForge("storage_blocks/labradorite");
        public static final Tag.Named<Item> STORAGE_BLOCKS_MOONSTONE = tagItemForge("storage_blocks/moonstone");
        public static final Tag.Named<Item> STORAGE_BLOCKS_RED_OPAL = tagItemForge("storage_blocks/red_opal");
        public static final Tag.Named<Item> STORAGE_BLOCKS_BLUE_OPAL = tagItemForge("storage_blocks/blue_opal");
        public static final Tag.Named<Item> STORAGE_BLOCKS_GREEN_OPAL = tagItemForge("storage_blocks/green_opal");
        public static final Tag.Named<Item> STORAGE_BLOCKS_WHITE_OPAL = tagItemForge("storage_blocks/white_opal");
        public static final Tag.Named<Item> STORAGE_BLOCKS_PYRITE = tagItemForge("storage_blocks/pyrite");
        public static final Tag.Named<Item> STORAGE_BLOCKS_TEKTITE = tagItemForge("storage_blocks/tektite");
        public static final Tag.Named<Item> STORAGE_BLOCKS_GOLDSTONE = tagItemForge("storage_blocks/goldstone");
        public static final Tag.Named<Item> STORAGE_BLOCKS_AURA_CRYSTAL = tagItemForge("storage_blocks/aura");
        public static final Tag.Named<Item> STORAGE_BLOCKS_BISMUTH = tagItemForge("storage_blocks/bismuth");
        public static final Tag.Named<Item> STORAGE_BLOCKS_IXIOLITE = tagItemForge("storage_blocks/ixiolite");
        public static final Tag.Named<Item> STORAGE_BLOCKS_PROUSTITE = tagItemForge("storage_blocks/proustite");
        public static final Tag.Named<Item> STORAGE_BLOCKS_EUCLASE = tagItemForge("storage_blocks/euclase");
        public static final Tag.Named<Item> STORAGE_BLOCKS_LEUCITE = tagItemForge("storage_blocks/leucite");
        public static final Tag.Named<Item> STORAGE_BLOCKS_CARNELIAN = tagItemForge("storage_blocks/carnelian");
        public static final Tag.Named<Item> STORAGE_BLOCKS_BENITOITE = tagItemForge("storage_blocks/benitoite");
        public static final Tag.Named<Item> STORAGE_BLOCKS_DIOPSIDE = tagItemForge("storage_blocks/diopside");
        public static final Tag.Named<Item> STORAGE_BLOCKS_CHALCEDONY = tagItemForge("storage_blocks/chalcedony");

        public static final Tag.Named<Item> DUSTS_FINE = tagItemForge("dusts/fine");
        public static final Tag.Named<Item> DUSTS_GOLDSTONE = tagItemForge("dusts/goldstone");
        public static final Tag.Named<Item> DUSTS_HOT = tagItemForge("dusts/hot");
        public static final Tag.Named<Item> INGOTS_SCAYNYX = tagItemForge("ingots/scaynyx");
        public static final Tag.Named<Item> RODS_AGATE = tagItemForge("rods/agate");
        public static final Tag.Named<Item> GEMS_SUGILITE = tagItemForge("gems/sugilite");
        public static final Tag.Named<Item> GEMS_HEMATITE = tagItemForge("gems/hematite");
        public static final Tag.Named<Item> GEMS_CINNABAR = tagItemForge("gems/cinnabar");
        public static final Tag.Named<Item> GEMS_LABRADORITE = tagItemForge("gems/labradorite");
        public static final Tag.Named<Item> GEMS_MOONSTONE = tagItemForge("gems/moonstone");
        public static final Tag.Named<Item> GEMS_RED_OPAL = tagItemForge("gems/red_opal");
        public static final Tag.Named<Item> GEMS_BLUE_OPAL = tagItemForge("gems/blue_opal");
        public static final Tag.Named<Item> GEMS_GREEN_OPAL = tagItemForge("gems/green_opal");
        public static final Tag.Named<Item> GEMS_WHITE_OPAL = tagItemForge("gems/white_opal");
        public static final Tag.Named<Item> GEMS_IXIOLITE = tagItemForge("gems/ixiolite");
        public static final Tag.Named<Item> GEMS_PROUSTITE = tagItemForge("gems/proustite");
        public static final Tag.Named<Item> GEMS_EUCLASE = tagItemForge("gems/euclase");
        public static final Tag.Named<Item> GEMS_LEUCITE = tagItemForge("gems/leucite");
        public static final Tag.Named<Item> GEMS_CARNELIAN = tagItemForge("gems/carnelian");
        public static final Tag.Named<Item> GEMS_BENITOITE = tagItemForge("gems/benitoite");
        public static final Tag.Named<Item> GEMS_DIOPSIDE = tagItemForge("gems/diopside");
        public static final Tag.Named<Item> GEMS_CHALCEDONY = tagItemForge("gems/chalcedony");
        public static final Tag.Named<Item> GEMS_PYRITE = tagItemForge("gems/pyrite");
        public static final Tag.Named<Item> GEMS_TEKTITE = tagItemForge("gems/tektite");
        public static final Tag.Named<Item> GEMS_GOLDSTONE = tagItemForge("gems/goldstone");
        public static final Tag.Named<Item> GEMS_AURA = tagItemForge("gems/aura");
        public static final Tag.Named<Item> GEMS_BISMUTH = tagItemForge("gems/bismuth");

        public static final Tag.Named<Item> TILES = tagItem("agate_tiles");
        public static final Tag.Named<Item> PINK_AGATE_LOGS = tagItem("pink_agate_logs");
        public static final Tag.Named<Item> BLUE_AGATE_LOGS = tagItem("blue_agate_logs");
        public static final Tag.Named<Item> GREEN_AGATE_LOGS = tagItem("green_agate_logs");
        public static final Tag.Named<Item> PURPLE_AGATE_LOGS = tagItem("purple_agate_logs");
        public static final Tag.Named<Item> FOSSILIZED_LOGS = tagItem("fossilized_logs");
        public static final Tag.Named<Item> CORRUPTED_LOGS = tagItem("corrupted_logs");
        public static final Tag.Named<Item> BURNT_LOGS = tagItem("burnt_logs");
        public static final Tag.Named<Item> BURNING_LOGS = tagItem("burning_logs");
        public static final Tag.Named<Item> AURA_LOGS = tagItem("aura_logs");
        public static final Tag.Named<Item> GAIA_BRICKS = tagItem("gaia_bricks");
        public static final Tag.Named<Item> AMETHYST_BRICKS = tagItem("amethyst_bricks");
        public static final Tag.Named<Item> COPAL_BRICKS = tagItem("copal_bricks");
        public static final Tag.Named<Item> JADE_BRICKS = tagItem("jade_bricks");
        public static final Tag.Named<Item> JET_BRICKS = tagItem("jet_bricks");
        public static final Tag.Named<Item> GEM_POUCH_ITEMS = tagItem("gem_pouch_items");
        public static final Tag.Named<Item> CRUDE_STORAGE_BLACKLIST = tagItem("crude_storage_blacklist");
        public static final Tag.Named<Item> MEGA_STORAGE_BLACKLIST = tagItem("mega_storage_blacklist");

        public static final Tag.Named<Item> SHULKER_BOXES = ItemTags.bind("minecraft:shulker_boxes");
    }

    public static class Blocks {
        public static final Tag.Named<Block> VOLCANIC = tagBlock("base_stone_volcanic");
        public static final Tag.Named<Block> STATIC = tagBlock("base_stone_static");
        public static final Tag.Named<Block> TILES = tagBlock("agate_tiles");
        public static final Tag.Named<Block> PINK_AGATE_LOGS = tagBlock("pink_agate_logs");
        public static final Tag.Named<Block> BLUE_AGATE_LOGS = tagBlock("blue_agate_logs");
        public static final Tag.Named<Block> GREEN_AGATE_LOGS = tagBlock("green_agate_logs");
        public static final Tag.Named<Block> PURPLE_AGATE_LOGS = tagBlock("purple_agate_logs");
        public static final Tag.Named<Block> FOSSILIZED_LOGS = tagBlock("fossilized_logs");
        public static final Tag.Named<Block> CORRUPTED_LOGS = tagBlock("corrupted_logs");
        public static final Tag.Named<Block> BURNT_LOGS = tagBlock("burnt_logs");
        public static final Tag.Named<Block> BURNING_LOGS = tagBlock("burning_logs");
        public static final Tag.Named<Block> AURA_LOGS = tagBlock("aura_logs");
        public static final Tag.Named<Block> GAIA_BRICKS = tagBlock("gaia_bricks");
        public static final Tag.Named<Block> AMETHYST_BRICKS = tagBlock("amethyst_bricks");
        public static final Tag.Named<Block> COPAL_BRICKS = tagBlock("copal_bricks");
        public static final Tag.Named<Block> JADE_BRICKS = tagBlock("jade_bricks");
        public static final Tag.Named<Block> JET_BRICKS = tagBlock("jet_bricks");

        public static final Tag.Named<Block> ORES_SUGILITE = tagBlockForge("ore/sugilite");
        public static final Tag.Named<Block> ORES_HEMATITE = tagBlockForge("ore/hematite");
        public static final Tag.Named<Block> ORES_CINNABAR = tagBlockForge("ore/cinnabar");
        public static final Tag.Named<Block> ORES_LABRADORITE = tagBlockForge("ore/labradorite");
        public static final Tag.Named<Block> ORES_MOONSTONE = tagBlockForge("ore/moonstone");
        public static final Tag.Named<Block> ORES_RED_OPAL = tagBlockForge("ore/red_opal");
        public static final Tag.Named<Block> ORES_BLUE_OPAL = tagBlockForge("ore/blue_opal");
        public static final Tag.Named<Block> ORES_GREEN_OPAL = tagBlockForge("ore/green_opal");
        public static final Tag.Named<Block> ORES_WHITE_OPAL = tagBlockForge("ore/white_opal");
        public static final Tag.Named<Block> ORES_PYRITE = tagBlockForge("ore/pyrite");
        public static final Tag.Named<Block> STORAGE_BLOCKS_SUGILITE = tagBlockForge("storage_blocks/sugilite");
        public static final Tag.Named<Block> STORAGE_BLOCKS_HEMATITE = tagBlockForge("storage_blocks/hematite");
        public static final Tag.Named<Block> STORAGE_BLOCKS_CINNABAR = tagBlockForge("storage_blocks/cinnabar");
        public static final Tag.Named<Block> STORAGE_BLOCKS_LABRADORITE = tagBlockForge("storage_blocks/labradorite");
        public static final Tag.Named<Block> STORAGE_BLOCKS_MOONSTONE = tagBlockForge("storage_blocks/moonstone");
        public static final Tag.Named<Block> STORAGE_BLOCKS_RED_OPAL = tagBlockForge("storage_blocks/red_opal");
        public static final Tag.Named<Block> STORAGE_BLOCKS_BLUE_OPAL = tagBlockForge("storage_blocks/blue_opal");
        public static final Tag.Named<Block> STORAGE_BLOCKS_GREEN_OPAL = tagBlockForge("storage_blocks/green_opal");
        public static final Tag.Named<Block> STORAGE_BLOCKS_WHITE_OPAL = tagBlockForge("storage_blocks/white_opal");
        public static final Tag.Named<Block> STORAGE_BLOCKS_PYRITE = tagBlockForge("storage_blocks/pyrite");
        public static final Tag.Named<Block> STORAGE_BLOCKS_TEKTITE = tagBlockForge("storage_blocks/tektite");
        public static final Tag.Named<Block> STORAGE_BLOCKS_GOLDSTONE = tagBlockForge("storage_blocks/goldstone");
        public static final Tag.Named<Block> STORAGE_BLOCKS_AURA_CRYSTAL = tagBlockForge("storage_blocks/aura");
        public static final Tag.Named<Block> STORAGE_BLOCKS_BISMUTH = tagBlockForge("storage_blocks/bismuth");
        public static final Tag.Named<Block> STORAGE_BLOCKS_IXIOLITE = tagBlockForge("storage_blocks/ixiolite");
        public static final Tag.Named<Block> STORAGE_BLOCKS_PROUSTITE = tagBlockForge("storage_blocks/proustite");
        public static final Tag.Named<Block> STORAGE_BLOCKS_EUCLASE = tagBlockForge("storage_blocks/euclase");
        public static final Tag.Named<Block> STORAGE_BLOCKS_LEUCITE = tagBlockForge("storage_blocks/leucite");
        public static final Tag.Named<Block> STORAGE_BLOCKS_CARNELIAN = tagBlockForge("storage_blocks/carnelian");
        public static final Tag.Named<Block> STORAGE_BLOCKS_BENITOITE = tagBlockForge("storage_blocks/benitoite");
        public static final Tag.Named<Block> STORAGE_BLOCKS_DIOPSIDE = tagBlockForge("storage_blocks/diopside");
        public static final Tag.Named<Block> STORAGE_BLOCKS_CHALCEDONY = tagBlockForge("storage_blocks/chalcedony");
    }

    public static class Fluids {
        public static final Tag.Named<Fluid> MINERAL_WATER = tagFluidForge("mineral_water");
        public static final Tag.Named<Fluid> SUPERHOT_MAGMA = tagFluidForge("superhot_magma");
        public static final Tag.Named<Fluid> SWEET_MUCK = tagFluidForge("sweet_muck");
        public static final Tag.Named<Fluid> LIQUID_BISMUTH = tagFluidForge("liquid_bismuth");
        public static final Tag.Named<Fluid> LIQUID_AURA = tagFluidForge("liquid_aura");
    }
}
