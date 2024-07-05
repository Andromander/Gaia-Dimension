package androsa.gaiadimension.registry.values;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class GaiaTags {

    private static final String ID = GaiaDimensionMod.MODID;

    private static TagKey<Item> tagItem(String name) {
        return ItemTags.create(new ResourceLocation(ID, name));
    }

    private static TagKey<Item> tagItemForge(String name) {
        return ItemTags.create(new ResourceLocation("forge", name));
    }

    private static TagKey<Block> tagBlock(String name) {
        return BlockTags.create(new ResourceLocation(ID, name));
    }

    private static TagKey<Block> tagBlockForge(String name) {
        return BlockTags.create(new ResourceLocation("forge", name));
    }

    private static TagKey<Fluid> tagFluid(String name) {
        return FluidTags.create(new ResourceLocation(GaiaDimensionMod.MODID, name));
    }

    private static TagKey<Biome> tagStructure(String name) {
        return tagBiome("has_structure/" + name);
    }

    private static TagKey<Biome> tagBiome(String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(ID, name));
    }

    private static TagKey<EntityType<?>> tagEntity(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, name));
    }

    public static class Items {
        public static final TagKey<Item> ORES_SUGILITE = tagItemForge("ore/sugilite");
        public static final TagKey<Item> ORES_HEMATITE = tagItemForge("ore/hematite");
        public static final TagKey<Item> ORES_CINNABAR = tagItemForge("ore/cinnabar");
        public static final TagKey<Item> ORES_LABRADORITE = tagItemForge("ore/labradorite");
        public static final TagKey<Item> ORES_MOONSTONE = tagItemForge("ore/moonstone");
        public static final TagKey<Item> ORES_RED_OPAL = tagItemForge("ore/red_opal");
        public static final TagKey<Item> ORES_BLUE_OPAL = tagItemForge("ore/blue_opal");
        public static final TagKey<Item> ORES_GREEN_OPAL = tagItemForge("ore/green_opal");
        public static final TagKey<Item> ORES_WHITE_OPAL = tagItemForge("ore/white_opal");
        public static final TagKey<Item> ORES_PYRITE = tagItemForge("ore/pyrite");
        public static final TagKey<Item> ORES_OPALITE = tagItemForge("ore/opalite");
        public static final TagKey<Item> STORAGE_BLOCKS_SUGILITE = tagItemForge("storage_blocks/sugilite");
        public static final TagKey<Item> STORAGE_BLOCKS_HEMATITE = tagItemForge("storage_blocks/hematite");
        public static final TagKey<Item> STORAGE_BLOCKS_CINNABAR = tagItemForge("storage_blocks/cinnabar");
        public static final TagKey<Item> STORAGE_BLOCKS_LABRADORITE = tagItemForge("storage_blocks/labradorite");
        public static final TagKey<Item> STORAGE_BLOCKS_MOONSTONE = tagItemForge("storage_blocks/moonstone");
        public static final TagKey<Item> STORAGE_BLOCKS_RED_OPAL = tagItemForge("storage_blocks/red_opal");
        public static final TagKey<Item> STORAGE_BLOCKS_BLUE_OPAL = tagItemForge("storage_blocks/blue_opal");
        public static final TagKey<Item> STORAGE_BLOCKS_GREEN_OPAL = tagItemForge("storage_blocks/green_opal");
        public static final TagKey<Item> STORAGE_BLOCKS_WHITE_OPAL = tagItemForge("storage_blocks/white_opal");
        public static final TagKey<Item> STORAGE_BLOCKS_PYRITE = tagItemForge("storage_blocks/pyrite");
        public static final TagKey<Item> STORAGE_BLOCKS_TEKTITE = tagItemForge("storage_blocks/tektite");
        public static final TagKey<Item> STORAGE_BLOCKS_GOLDSTONE = tagItemForge("storage_blocks/goldstone");
        public static final TagKey<Item> STORAGE_BLOCKS_AURA_CRYSTAL = tagItemForge("storage_blocks/aura");
        public static final TagKey<Item> STORAGE_BLOCKS_BISMUTH = tagItemForge("storage_blocks/bismuth");
        public static final TagKey<Item> STORAGE_BLOCKS_STIBNITE = tagItemForge("storage_blocks/stibnite");
        public static final TagKey<Item> STORAGE_BLOCKS_PROUSTITE = tagItemForge("storage_blocks/proustite");
        public static final TagKey<Item> STORAGE_BLOCKS_EUCLASE = tagItemForge("storage_blocks/euclase");
        public static final TagKey<Item> STORAGE_BLOCKS_ALBITE = tagItemForge("storage_blocks/albite");
        public static final TagKey<Item> STORAGE_BLOCKS_CARNELIAN = tagItemForge("storage_blocks/carnelian");
        public static final TagKey<Item> STORAGE_BLOCKS_BENITOITE = tagItemForge("storage_blocks/benitoite");
        public static final TagKey<Item> STORAGE_BLOCKS_DIOPSIDE = tagItemForge("storage_blocks/diopside");
        public static final TagKey<Item> STORAGE_BLOCKS_GOSHENITE = tagItemForge("storage_blocks/goshenite");

        public static final TagKey<Item> DUSTS_FINE = tagItemForge("dusts/fine");
        public static final TagKey<Item> DUSTS_GOLDSTONE = tagItemForge("dusts/goldstone");
        public static final TagKey<Item> DUSTS_HOT = tagItemForge("dusts/hot");
        public static final TagKey<Item> INGOTS_SCAYNYX = tagItemForge("ingots/scaynyx");
        public static final TagKey<Item> RODS_AGATE = tagItemForge("rods/agate");
        public static final TagKey<Item> GEMS_SUGILITE = tagItemForge("gems/sugilite");
        public static final TagKey<Item> GEMS_HEMATITE = tagItemForge("gems/hematite");
        public static final TagKey<Item> GEMS_CINNABAR = tagItemForge("gems/cinnabar");
        public static final TagKey<Item> GEMS_LABRADORITE = tagItemForge("gems/labradorite");
        public static final TagKey<Item> GEMS_MOONSTONE = tagItemForge("gems/moonstone");
        public static final TagKey<Item> GEMS_RED_OPAL = tagItemForge("gems/red_opal");
        public static final TagKey<Item> GEMS_BLUE_OPAL = tagItemForge("gems/blue_opal");
        public static final TagKey<Item> GEMS_GREEN_OPAL = tagItemForge("gems/green_opal");
        public static final TagKey<Item> GEMS_WHITE_OPAL = tagItemForge("gems/white_opal");
        public static final TagKey<Item> GEMS_STIBNITE = tagItemForge("gems/stibnite");
        public static final TagKey<Item> GEMS_PROUSTITE = tagItemForge("gems/proustite");
        public static final TagKey<Item> GEMS_EUCLASE = tagItemForge("gems/euclase");
        public static final TagKey<Item> GEMS_ALBITE = tagItemForge("gems/albite");
        public static final TagKey<Item> GEMS_CARNELIAN = tagItemForge("gems/carnelian");
        public static final TagKey<Item> GEMS_BENITOITE = tagItemForge("gems/benitoite");
        public static final TagKey<Item> GEMS_DIOPSIDE = tagItemForge("gems/diopside");
        public static final TagKey<Item> GEMS_GOSHENITE = tagItemForge("gems/goshenite");
        public static final TagKey<Item> GEMS_PYRITE = tagItemForge("gems/pyrite");
        public static final TagKey<Item> GEMS_TEKTITE = tagItemForge("gems/tektite");
        public static final TagKey<Item> GEMS_GOLDSTONE = tagItemForge("gems/goldstone");
        public static final TagKey<Item> GEMS_AURA = tagItemForge("gems/aura");
        public static final TagKey<Item> GEMS_BISMUTH = tagItemForge("gems/bismuth");
        public static final TagKey<Item> GEMS_OPALITE = tagItemForge("gems/opalite");
        public static final TagKey<Item> GEMS_CELESTINE = tagItemForge("gems/celestine");

        public static final TagKey<Item> MOOKAITE = tagItem("mookaite");
        public static final TagKey<Item> TILES = tagItem("agate_tiles");
        public static final TagKey<Item> PINK_AGATE_LOGS = tagItem("pink_agate_logs");
        public static final TagKey<Item> BLUE_AGATE_LOGS = tagItem("blue_agate_logs");
        public static final TagKey<Item> GREEN_AGATE_LOGS = tagItem("green_agate_logs");
        public static final TagKey<Item> PURPLE_AGATE_LOGS = tagItem("purple_agate_logs");
        public static final TagKey<Item> FOSSILIZED_LOGS = tagItem("fossilized_logs");
        public static final TagKey<Item> CORRUPTED_LOGS = tagItem("corrupted_logs");
        public static final TagKey<Item> BURNT_LOGS = tagItem("burnt_logs");
        public static final TagKey<Item> BURNING_LOGS = tagItem("burning_logs");
        public static final TagKey<Item> AURA_LOGS = tagItem("aura_logs");
        public static final TagKey<Item> GOLDEN_LOGS = tagItem("golden_logs");
        public static final TagKey<Item> GAIA_BRICKS = tagItem("gaia_bricks");
        public static final TagKey<Item> AMETHYST_BRICKS = tagItem("amethyst_bricks");
        public static final TagKey<Item> COPAL_BRICKS = tagItem("copal_bricks");
        public static final TagKey<Item> JADE_BRICKS = tagItem("jade_bricks");
        public static final TagKey<Item> JET_BRICKS = tagItem("jet_bricks");
        public static final TagKey<Item> GEM_POUCH_ITEMS = tagItem("gem_pouch_items");
        public static final TagKey<Item> CRUDE_STORAGE_BLACKLIST = tagItem("crude_storage_blacklist");
        public static final TagKey<Item> MEGA_STORAGE_BLACKLIST = tagItem("mega_storage_blacklist");

        public static final TagKey<Item> SHULKER_BOXES = ItemTags.create(new ResourceLocation("shulker_boxes"));
    }

    public static class Blocks {
        public static final TagKey<Block> GAIA_STONE = tagBlock("base_stone_gaia");
        public static final TagKey<Block> VOLCANIC = tagBlock("base_stone_volcanic");
        public static final TagKey<Block> STATIC = tagBlock("base_stone_static");
        public static final TagKey<Block> PRIMAL = tagBlock("base_stone_primal");
        public static final TagKey<Block> MOOKAITE = tagBlock("mookaite");
        public static final TagKey<Block> GAIA_CARVER_REPLACEABLES = tagBlock("gaia_carver_replaceables");
        public static final TagKey<Block> GAIA_GRASS = tagBlock("gaia_grass");
        public static final TagKey<Block> GAIA_SOIL = tagBlock("gaia_soil");
        public static final TagKey<Block> TILES = tagBlock("agate_tiles");
        public static final TagKey<Block> PINK_AGATE_LOGS = tagBlock("pink_agate_logs");
        public static final TagKey<Block> BLUE_AGATE_LOGS = tagBlock("blue_agate_logs");
        public static final TagKey<Block> GREEN_AGATE_LOGS = tagBlock("green_agate_logs");
        public static final TagKey<Block> PURPLE_AGATE_LOGS = tagBlock("purple_agate_logs");
        public static final TagKey<Block> FOSSILIZED_LOGS = tagBlock("fossilized_logs");
        public static final TagKey<Block> CORRUPTED_LOGS = tagBlock("corrupted_logs");
        public static final TagKey<Block> BURNT_LOGS = tagBlock("burnt_logs");
        public static final TagKey<Block> BURNING_LOGS = tagBlock("burning_logs");
        public static final TagKey<Block> AURA_LOGS = tagBlock("aura_logs");
        public static final TagKey<Block> GOLDEN_LOGS = tagBlock("golden_logs");
        public static final TagKey<Block> GAIA_BRICKS = tagBlock("gaia_bricks");
        public static final TagKey<Block> AMETHYST_BRICKS = tagBlock("amethyst_bricks");
        public static final TagKey<Block> COPAL_BRICKS = tagBlock("copal_bricks");
        public static final TagKey<Block> JADE_BRICKS = tagBlock("jade_bricks");
        public static final TagKey<Block> JET_BRICKS = tagBlock("jet_bricks");

        public static final TagKey<Block> ORES_SUGILITE = tagBlockForge("ore/sugilite");
        public static final TagKey<Block> ORES_HEMATITE = tagBlockForge("ore/hematite");
        public static final TagKey<Block> ORES_CINNABAR = tagBlockForge("ore/cinnabar");
        public static final TagKey<Block> ORES_LABRADORITE = tagBlockForge("ore/labradorite");
        public static final TagKey<Block> ORES_MOONSTONE = tagBlockForge("ore/moonstone");
        public static final TagKey<Block> ORES_RED_OPAL = tagBlockForge("ore/red_opal");
        public static final TagKey<Block> ORES_BLUE_OPAL = tagBlockForge("ore/blue_opal");
        public static final TagKey<Block> ORES_GREEN_OPAL = tagBlockForge("ore/green_opal");
        public static final TagKey<Block> ORES_WHITE_OPAL = tagBlockForge("ore/white_opal");
        public static final TagKey<Block> ORES_PYRITE = tagBlockForge("ore/pyrite");
        public static final TagKey<Block> ORES_OPALITE = tagBlockForge("ore/opalite");
        public static final TagKey<Block> ORES_CELESTINE = tagBlockForge("ore/celestine");
        public static final TagKey<Block> STORAGE_BLOCKS_SUGILITE = tagBlockForge("storage_blocks/sugilite");
        public static final TagKey<Block> STORAGE_BLOCKS_HEMATITE = tagBlockForge("storage_blocks/hematite");
        public static final TagKey<Block> STORAGE_BLOCKS_CINNABAR = tagBlockForge("storage_blocks/cinnabar");
        public static final TagKey<Block> STORAGE_BLOCKS_LABRADORITE = tagBlockForge("storage_blocks/labradorite");
        public static final TagKey<Block> STORAGE_BLOCKS_MOONSTONE = tagBlockForge("storage_blocks/moonstone");
        public static final TagKey<Block> STORAGE_BLOCKS_RED_OPAL = tagBlockForge("storage_blocks/red_opal");
        public static final TagKey<Block> STORAGE_BLOCKS_BLUE_OPAL = tagBlockForge("storage_blocks/blue_opal");
        public static final TagKey<Block> STORAGE_BLOCKS_GREEN_OPAL = tagBlockForge("storage_blocks/green_opal");
        public static final TagKey<Block> STORAGE_BLOCKS_WHITE_OPAL = tagBlockForge("storage_blocks/white_opal");
        public static final TagKey<Block> STORAGE_BLOCKS_PYRITE = tagBlockForge("storage_blocks/pyrite");
        public static final TagKey<Block> STORAGE_BLOCKS_TEKTITE = tagBlockForge("storage_blocks/tektite");
        public static final TagKey<Block> STORAGE_BLOCKS_GOLDSTONE = tagBlockForge("storage_blocks/goldstone");
        public static final TagKey<Block> STORAGE_BLOCKS_AURA_CRYSTAL = tagBlockForge("storage_blocks/aura");
        public static final TagKey<Block> STORAGE_BLOCKS_BISMUTH = tagBlockForge("storage_blocks/bismuth");
        public static final TagKey<Block> STORAGE_BLOCKS_OPALITE = tagBlockForge("storage_blocks/opalite");
        public static final TagKey<Block> STORAGE_BLOCKS_STIBNITE = tagBlockForge("storage_blocks/stibnite");
        public static final TagKey<Block> STORAGE_BLOCKS_PROUSTITE = tagBlockForge("storage_blocks/proustite");
        public static final TagKey<Block> STORAGE_BLOCKS_EUCLASE = tagBlockForge("storage_blocks/euclase");
        public static final TagKey<Block> STORAGE_BLOCKS_ALBITE = tagBlockForge("storage_blocks/albite");
        public static final TagKey<Block> STORAGE_BLOCKS_CARNELIAN = tagBlockForge("storage_blocks/carnelian");
        public static final TagKey<Block> STORAGE_BLOCKS_BENITOITE = tagBlockForge("storage_blocks/benitoite");
        public static final TagKey<Block> STORAGE_BLOCKS_DIOPSIDE = tagBlockForge("storage_blocks/diopside");
        public static final TagKey<Block> STORAGE_BLOCKS_GOSHENITE = tagBlockForge("storage_blocks/goshenite");
        public static final TagKey<Block> STORAGE_BLOCKS_CELESTINE = tagBlockForge("storage_blocks/celestine");

        public static final TagKey<Block> INCORRECT_FOR_AGATE = tagBlock("incorrect_for_agate_tool");
        public static final TagKey<Block> INCORRECT_FOR_SUGILITE = tagBlock("incorrect_for_sugilite_tool");
        public static final TagKey<Block> INCORRECT_FOR_STIBNITE = tagBlock("incorrect_for_stibnite_tool");
        public static final TagKey<Block> INCORRECT_FOR_EUCLASE = tagBlock("incorrect_for_euclase_tool");
        public static final TagKey<Block> INCORRECT_FOR_CARNELIAN = tagBlock("incorrect_for_carnelian_tool");
        public static final TagKey<Block> INCORRECT_FOR_BENITOITE = tagBlock("incorrect_for_benitoite_tool");
        public static final TagKey<Block> INCORRECT_FOR_GOSHENITE = tagBlock("incorrect_for_goshenite_tool");
        public static final TagKey<Block> INCORRECT_FOR_MALACHITE = tagBlock("incorrect_for_malachite_tool");
        public static final TagKey<Block> INCORRECT_FOR_TIGER_EYE = tagBlock("incorrect_for_tiger_eye_tool");
        public static final TagKey<Block> INCORRECT_FOR_SPINEL = tagBlock("incorrect_for_spinel_tool");
        public static final TagKey<Block> INCORRECT_FOR_ZIRCON = tagBlock("incorrect_for_zircon_tool");
        public static final TagKey<Block> INCORRECT_FOR_CORRUPT = tagBlock("incorrect_for_corrupt_tool");
        public static final TagKey<Block> INCORRECT_FOR_BIXBITE = tagBlock("incorrect_for_bixbite_tool");
        public static final TagKey<Block> INCORRECT_FOR_TSAVORITE = tagBlock("incorrect_for_tsavorite_tool");
        public static final TagKey<Block> INCORRECT_FOR_LARVIKITE = tagBlock("incorrect_for_larvikite_tool");
        public static final TagKey<Block> INCORRECT_FOR_CHAMPION = tagBlock("incorrect_for_champion_tool");
    }

    public static class Fluids {
        public static final TagKey<Fluid> MINERAL_WATER = tagFluid("mineral_water");
        public static final TagKey<Fluid> SUPERHOT_MAGMA = tagFluid("superhot_magma");
        public static final TagKey<Fluid> SWEET_MUCK = tagFluid("sweet_muck");
        public static final TagKey<Fluid> LIQUID_BISMUTH = tagFluid("liquid_bismuth");
        public static final TagKey<Fluid> LIQUID_AURA = tagFluid("liquid_aura");
    }

    public static class Biomes {
        public static final TagKey<Biome> HAS_MINI_TOWER = tagStructure("mini_tower");
        public static final TagKey<Biome> HAS_MALACHITE_WATCHTOWER = tagStructure("malachite_watchtower");
        public static final TagKey<Biome> PORTAL_BIOMES = tagBiome("portal_biomes");
        public static final TagKey<Biome> GAIA_BIOMES = tagBiome("is_gaia");
        public static final TagKey<Biome> AGATE_BIOMES = tagBiome("is_agate");
        public static final TagKey<Biome> GOLDEN_BIOMES = tagBiome("is_golden");
    }

    public static class Entities {
        TagKey<EntityType<?>> GAIAN = tagEntity("gaian");
        TagKey<EntityType<?>> CORRUPT = tagEntity("corrupt");
        TagKey<EntityType<?>> CORRUPTION_IMMUNE = tagEntity("corruption_immune");
    }
}
