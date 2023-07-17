package androsa.gaiadimension.registry.values;

import androsa.gaiadimension.registry.registration.ModBlocks;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class GaiaBiomeFeatures {

    public static final RuleTest GAIA_STONE = new TagMatchTest(GaiaTags.Blocks.GAIA_STONE);
    public static final RuleTest VOLCANIC = new TagMatchTest(GaiaTags.Blocks.VOLCANIC);
    public static final RuleTest STATIC = new TagMatchTest(GaiaTags.Blocks.STATIC);

    public static final BlockState GLITTER_GRASS = state(ModBlocks.glitter_grass);
    public static final BlockState CORRUPT_GRASS = state(ModBlocks.corrupted_grass);
    public static final BlockState MURKY_GRASS = state(ModBlocks.murky_grass);
    public static final BlockState SOFT_GRASS = state(ModBlocks.soft_grass);
    public static final BlockState GILDED_GRASS = state(ModBlocks.gilded_grass);
    public static final BlockState HEAVY_SOIL = state(ModBlocks.heavy_soil);
    public static final BlockState CORRUPT_SOIL = state(ModBlocks.corrupted_soil);
    public static final BlockState BOGGY_SOIL = state(ModBlocks.boggy_soil);
    public static final BlockState LIGHT_SOIL = state(ModBlocks.light_soil);
    public static final BlockState AURUM_SOIL = state(ModBlocks.aurum_soil);
    public static final BlockState SALT = state(ModBlocks.salt);
    public static final BlockState WASTELAND_STONE = state(ModBlocks.wasteland_stone);
    public static final BlockState SUPERHOT_MAGMA = state(ModBlocks.superhot_magma);
    public static final BlockState MINERAL_WATER = state(ModBlocks.mineral_water);
    public static final BlockState SWEET_MUCK = state(ModBlocks.sweet_muck);
    public static final BlockState LIQUID_AURA = state(ModBlocks.liquid_aura);
    public static final BlockState LIQUID_BISMUTH = state(ModBlocks.liquid_bismuth);
    public static final BlockState GUMMY_GLITTER = state(ModBlocks.gummy_glitter_block);
    public static final BlockState PRIMAL_MASS = state(ModBlocks.primal_mass);
    public static final BlockState THICK_GLITTER = state(ModBlocks.thick_glitter_block);
    public static final BlockState SEARING_ROCK = state(ModBlocks.searing_rock);
    public static final BlockState STATIC_STONE = state(ModBlocks.static_stone);
    public static final BlockState IMPURE_SLUDGE = state(ModBlocks.impure_sludge);
    public static final BlockState BRILLIANT_STONE = state(ModBlocks.brilliant_stone);
    public static final BlockState GILDED_BRILLIANT_STONE = state(ModBlocks.gilded_brilliant_stone);
    public static final BlockState AURUM_MUD = state(ModBlocks.aurum_mud);
    public static final BlockState PEBBLES = state(ModBlocks.pebbles);
    public static final BlockState SPECKLED_ROCK = state(ModBlocks.speckled_rock);
    public static final BlockState COARSE_ROCK = state(ModBlocks.coarse_rock);
    public static final BlockState PRECIOUS_ROCK = state(ModBlocks.precious_rock);
    public static final BlockState RAW_AMETHYST = state(ModBlocks.raw_amethyst);
    public static final BlockState RAW_COPAL = state(ModBlocks.raw_copal);
    public static final BlockState RAW_JADE = state(ModBlocks.raw_jade);
    public static final BlockState RAW_JET = state(ModBlocks.raw_jet);
    public static final BlockState SUGILITE_ORE = state(ModBlocks.sugilite_ore);
    public static final BlockState HEMATITE_ORE = state(ModBlocks.hematite_ore);
    public static final BlockState PYRITE_ORE = state(ModBlocks.pyrite_ore);
    public static final BlockState CINNABAR_ORE = state(ModBlocks.cinnabar_ore);
    public static final BlockState LABRADORITE_ORE = state(ModBlocks.labradorite_ore);
    public static final BlockState MOONSTONE_ORE = state(ModBlocks.moonstone_ore);
    public static final BlockState RED_OPAL_ORE = state(ModBlocks.red_opal_ore);
    public static final BlockState BLUE_OPAL_ORE = state(ModBlocks.blue_opal_ore);
    public static final BlockState GREEN_OPAL_ORE = state(ModBlocks.green_opal_ore);
    public static final BlockState WHITE_OPAL_ORE = state(ModBlocks.white_opal_ore);
    public static final BlockState SCARLET_OPALITE_ORE = state(ModBlocks.scarlet_opalite_ore);
    public static final BlockState AUBURN_OPALITE_ORE = state(ModBlocks.auburn_opalite_ore);
    public static final BlockState GOLD_OPALITE_ORE = state(ModBlocks.gold_opalite_ore);
    public static final BlockState MAUVE_OPALITE_ORE = state(ModBlocks.mauve_opalite_ore);
    public static final BlockState BEIGE_OPALITE_ORE = state(ModBlocks.beige_opalite_ore);
    public static final BlockState IVORY_OPALITE_ORE = state(ModBlocks.ivory_opalite_ore);
    public static final BlockState PINK_AGATE_LOG = state(ModBlocks.pink_agate_log);
    public static final BlockState PINK_AGATE_LEAVES = state(ModBlocks.pink_agate_leaves);
    public static final BlockState BLUE_AGATE_LOG = state(ModBlocks.blue_agate_log);
    public static final BlockState BLUE_AGATE_LEAVES = state(ModBlocks.blue_agate_leaves);
    public static final BlockState GREEN_AGATE_LOG = state(ModBlocks.green_agate_log);
    public static final BlockState GREEN_AGATE_LEAVES = state(ModBlocks.green_agate_leaves);
    public static final BlockState PURPLE_AGATE_LOG = state(ModBlocks.purple_agate_log);
    public static final BlockState PURPLE_AGATE_LEAVES = state(ModBlocks.purple_agate_leaves);
    public static final BlockState FOSSIL_LOG = state(ModBlocks.fossilized_log);
    public static final BlockState FOSSIL_LEAVES = state(ModBlocks.fossilized_leaves);
    public static final BlockState CORRUPTED_LOG = state(ModBlocks.corrupted_log);
    public static final BlockState CORRUPTED_LEAVES = state(ModBlocks.corrupted_leaves);
    public static final BlockState BURNT_LOG = state(ModBlocks.burnt_log);
    public static final BlockState BURNT_LEAVES = state(ModBlocks.burnt_leaves);
    public static final BlockState BURNING_LOG = state(ModBlocks.fire_agate_log);
    public static final BlockState BURNING_LEAVES = state(ModBlocks.fire_agate_leaves);
    public static final BlockState AURA_LOG = state(ModBlocks.aura_log);
    public static final BlockState AURA_LEAVES = state(ModBlocks.aura_leaves);
    public static final BlockState GOLDEN_LOG = state(ModBlocks.golden_log);
    public static final BlockState GOLDEN_LEAVES = state(ModBlocks.golden_leaves);
    public static final BlockState CRYSTAL_GROWTH = state(ModBlocks.crystal_growth);
    public static final BlockState CRYSTAL_GROWTH_MUTANT = state(ModBlocks.crystal_growth_mutant);
    public static final BlockState CRYSTAL_GROWTH_SEARED = state(ModBlocks.crystal_growth_seared);
    public static final BlockState CRYSTAL_GROWTH_RED = state(ModBlocks.crystal_growth_red);
    public static final BlockState CRYSTAL_GROWTH_BLACK = state(ModBlocks.crystal_growth_black);
    public static final BlockState CRYSTAL_GROWTH_AURA = state(ModBlocks.crystal_growth_aura);
    public static final BlockState GOLDEN_GRASS = state(ModBlocks.golden_grass);
    public static final BlockState TALL_GOLDEN_GRASS = state(ModBlocks.tall_golden_grass);
    public static final BlockState GOLDEN_STONE = state(ModBlocks.golden_stone);
    public static final BlockState TOUGH_GOLDEN_STONE = state(ModBlocks.tough_golden_stone);
    public static final BlockState THISCUS = state(ModBlocks.thiscus);
    public static final BlockState OUZIUM = state(ModBlocks.ouzium);
    public static final BlockState AGATHUM = state(ModBlocks.agathum);
    public static final BlockState CORRUPTED_VARLOOM = state(ModBlocks.corrupted_varloom);
    public static final BlockState GLAMELEA = state(ModBlocks.glamelea);
    public static final BlockState SPOTTED_KERSEI = state(ModBlocks.spotted_kersei);
    public static final BlockState THORNY_WILTHA = state(ModBlocks.thorny_wiltha);
    public static final BlockState ROOFED_AGARIC = state(ModBlocks.roofed_agaric);
    public static final BlockState BULBOUS_HOBINA = state(ModBlocks.bulbous_hobina);
    public static final BlockState STICKLY_CUPSIR = state(ModBlocks.stickly_cupsir);
    public static final BlockState MYSTICAL_MURGNI = state(ModBlocks.mystical_murgni);
    public static final BlockState CORRUPTED_GAIA_EYE = state(ModBlocks.corrupted_gaia_eye);
    public static final BlockState TWINKLING_GILSRI = state(ModBlocks.twinkling_gilsri);
    public static final BlockState ELDER_IMKLIA = state(ModBlocks.elder_imklia);
    public static final BlockState GOLD_ORB_TUCHER = state(ModBlocks.gold_orb_tucher);
    public static final BlockState SOMBRE_SHRUB = state(ModBlocks.sombre_shrub);

    public static final List<Block> cave_blacklist = ImmutableList.of(GLITTER_GRASS.getBlock(), HEAVY_SOIL.getBlock(), CORRUPT_GRASS.getBlock(), CORRUPT_SOIL.getBlock(), MURKY_GRASS.getBlock(), BOGGY_SOIL.getBlock(), SOFT_GRASS.getBlock(), LIGHT_SOIL.getBlock(), SALT.getBlock());

    private static BlockState state(RegistryObject<? extends Block> block) {
        return block.get().defaultBlockState();
    }

    @SafeVarargs
    protected static BlockPredicate match(RegistryObject<Block>... blocks) {
        List<Block> list = Lists.newArrayList();
        for (RegistryObject<Block> block : blocks) {
            list.add(block.get());
        }
        return BlockPredicate.matchesBlocks(list);
    }
}
