package androsa.gaiadimension.world.chunk;

import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.registration.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraftforge.registries.RegistryObject;

public class GaiaSurfaceRuleData {

    public static final SurfaceRules.RuleSource GLITTER_GRASS = stateRule(ModBlocks.glitter_grass);
    public static final SurfaceRules.RuleSource CORRUPT_GRASS = stateRule(ModBlocks.corrupt_grass);
    public static final SurfaceRules.RuleSource MURKY_GRASS = stateRule(ModBlocks.murky_grass);
    public static final SurfaceRules.RuleSource SOFT_GRASS = stateRule(ModBlocks.soft_grass);
    public static final SurfaceRules.RuleSource GILDED_GRASS = stateRule(ModBlocks.gilded_grass);
    public static final SurfaceRules.RuleSource HEAVY_SOIL = stateRule(ModBlocks.heavy_soil);
    public static final SurfaceRules.RuleSource CORRUPT_SOIL = stateRule(ModBlocks.corrupt_soil);
    public static final SurfaceRules.RuleSource BOGGY_SOIL = stateRule(ModBlocks.boggy_soil);
    public static final SurfaceRules.RuleSource LIGHT_SOIL = stateRule(ModBlocks.light_soil);
    public static final SurfaceRules.RuleSource AURUM_SOIL = stateRule(ModBlocks.aurum_soil);
    public static final SurfaceRules.RuleSource SALT = stateRule(ModBlocks.salt);
    public static final SurfaceRules.RuleSource SALTSTONE = stateRule(ModBlocks.saltstone);
    public static final SurfaceRules.RuleSource PEBBLES = stateRule(ModBlocks.pebbles);
    public static final SurfaceRules.RuleSource VOLCANIC_ROCK = stateRule(ModBlocks.volcanic_rock);
    public static final SurfaceRules.RuleSource WASTELAND_STONE = stateRule(ModBlocks.wasteland_stone);
    public static final SurfaceRules.RuleSource GOLDEN_STONE = stateRule(ModBlocks.golden_stone);
    public static final SurfaceRules.RuleSource SOLID_GOLDEN_STONE = stateRule(ModBlocks.brilliant_stone);
    public static final SurfaceRules.RuleSource GOLDEN_SAND = stateRule(ModBlocks.golden_sand);
    public static final SurfaceRules.RuleSource SCARLET_MOOKAITE = stateRule(ModBlocks.scarlet_mookaite);
    public static final SurfaceRules.RuleSource AUBURN_MOOKAITE = stateRule(ModBlocks.auburn_mookaite);
    public static final SurfaceRules.RuleSource GOLD_MOOKAITE = stateRule(ModBlocks.gold_mookaite);
    public static final SurfaceRules.RuleSource MAUVE_MOOKAITE = stateRule(ModBlocks.mauve_mookaite);
    public static final SurfaceRules.RuleSource GAIA_STONE = stateRule(ModBlocks.gaia_stone);
    public static final SurfaceRules.RuleSource PRIMAL_MASS = stateRule(ModBlocks.primal_mass);
    public static final SurfaceRules.RuleSource NEXUSTONE = stateRule(ModBlocks.nexustone);
    public static final SurfaceRules.RuleSource BEDROCK = SurfaceRules.state(Blocks.BEDROCK.defaultBlockState());

    private static SurfaceRules.RuleSource stateRule(RegistryObject<Block> block) {
        return SurfaceRules.state(block.get().defaultBlockState());
    }

    public static SurfaceRules.RuleSource gaia(boolean hasSeaLevel, boolean bedrockRoof, boolean bedrockFloor) {
        SurfaceRules.ConditionSource seaLevelCondition = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1);
        SurfaceRules.ConditionSource bandsCondition = SurfaceRules.yStartCheck(VerticalAnchor.absolute(74), 1);
        SurfaceRules.ConditionSource aboveSeaCondition = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        SurfaceRules.RuleSource saltstoneRoofRule = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SALTSTONE), SALT);
        SurfaceRules.RuleSource solidGoldRoofRule = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SOLID_GOLDEN_STONE), GOLDEN_SAND);
        SurfaceRules.ConditionSource beachCondition = SurfaceRules.isBiome(GaiaBiomes.mineral_reservoir, GaiaBiomes.mineral_river);
        SurfaceRules.ConditionSource dunesRule = SurfaceRules.isBiome(GaiaBiomes.salt_dunes);
        SurfaceRules.ConditionSource sandsRule = SurfaceRules.isBiome(GaiaBiomes.golden_sands);
        SurfaceRules.ConditionSource surfacerules$conditionsource14 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909D, -0.5454D);
        SurfaceRules.ConditionSource surfacerules$conditionsource15 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818D, 0.1818D);
        SurfaceRules.ConditionSource surfacerules$conditionsource16 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454D, 0.909D);

        SurfaceRules.RuleSource grassRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.goldstone_lands), CORRUPT_GRASS),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.smoldering_bog), MURKY_GRASS),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.shining_grove), SOFT_GRASS),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.golden_hills, GaiaBiomes.golden_forest, GaiaBiomes.golden_plains, GaiaBiomes.golden_marsh), GILDED_GRASS),
                GLITTER_GRASS);
        SurfaceRules.RuleSource dirtRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.goldstone_lands), CORRUPT_SOIL),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.smoldering_bog), BOGGY_SOIL),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.shining_grove), LIGHT_SOIL),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.golden_hills, GaiaBiomes.golden_forest, GaiaBiomes.golden_plains, GaiaBiomes.golden_marsh), AURUM_SOIL),
                HEAVY_SOIL);
        SurfaceRules.RuleSource basicSurfaceRule = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0), grassRule), dirtRule);
        SurfaceRules.RuleSource rockySurfaceRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.golden_hills),
                        SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("brilliant_stone", VerticalAnchor.absolute(100), VerticalAnchor.absolute(105))), SOLID_GOLDEN_STONE)),
                SurfaceRules.ifTrue(sandsRule, solidGoldRoofRule),
                SurfaceRules.ifTrue(beachCondition, saltstoneRoofRule),
                SurfaceRules.ifTrue(dunesRule, saltstoneRoofRule)
                /*SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.DRIPSTONE_CAVES), STONE)*/);

        SurfaceRules.RuleSource mookaite = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.mookaite_mesa),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(bandsCondition,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(surfacerules$conditionsource14, GOLD_MOOKAITE),
                                                                SurfaceRules.ifTrue(surfacerules$conditionsource15, SCARLET_MOOKAITE),
                                                                SurfaceRules.ifTrue(surfacerules$conditionsource16, MAUVE_MOOKAITE),
                                                                SurfaceRules.bandlands())),
                                                /*SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0),
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SALTSTONE), SALT)),*/
                                                SurfaceRules.ifTrue(SurfaceRules.waterStartCheck(-6, -1), GOLD_MOOKAITE)
                                                )),
                                SurfaceRules.ifTrue(seaLevelCondition,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(aboveSeaCondition,
                                                        SurfaceRules.ifTrue(SurfaceRules.not(bandsCondition), AUBURN_MOOKAITE)),
                                                SurfaceRules.bandlands())),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.ifTrue(SurfaceRules.waterStartCheck(-6, -1), SCARLET_MOOKAITE))))
        );

        SurfaceRules.RuleSource wasteland = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.static_wasteland),
                        SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("wasteland_stone", VerticalAnchor.absolute(50), VerticalAnchor.absolute(63))), WASTELAND_STONE))
        );

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(SurfaceRules.waterBlockCheck(-1, 0),
                                SurfaceRules.sequence(
                                        rockySurfaceRule,
                                        basicSurfaceRule)))
        );

        SurfaceRules.RuleSource surfaceRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.waterStartCheck(-6, -1),
                                        SurfaceRules.sequence(
                                                rockySurfaceRule,
                                                dirtRule))
                        )),
                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR,
                        SurfaceRules.ifTrue(beachCondition, SALTSTONE)),
                SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(sandsRule, SOLID_GOLDEN_STONE),
                                SurfaceRules.ifTrue(dunesRule, SALTSTONE)
                        )),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.mineral_reservoir, GaiaBiomes.mineral_river), saltstoneRoofRule),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, GAIA_STONE), PEBBLES))));

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();

        //just in case, generate bedrock roof. Idk why anyone would want this but whatever
        if (bedrockRoof) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK));
        }

        //generate bedrock floor
        if (bedrockFloor) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
        }

        //SurfaceRules.RuleSource aboveSurfaceRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), surfaceRule);
        builder.add(mookaite);
        builder.add(wasteland);
        builder.add(grassSurface);
        builder.add(surfaceRule);
        //generate golden stone under gold biomes
        builder.add(SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.golden_hills, GaiaBiomes.golden_forest, GaiaBiomes.golden_plains, GaiaBiomes.golden_marsh, GaiaBiomes.golden_sands),
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("golden_stone", VerticalAnchor.absolute(50), VerticalAnchor.absolute(63))), GOLDEN_STONE)));
        //generate volcanic rock under Volcanic Lands
        builder.add(SurfaceRules.ifTrue(SurfaceRules.isBiome(GaiaBiomes.volcanic_lands),
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("volcanic_rock", VerticalAnchor.absolute(50), VerticalAnchor.absolute(63))), VOLCANIC_ROCK)));
        //generate nexustone
        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("nexustone", VerticalAnchor.absolute(-48), VerticalAnchor.absolute(-32)), NEXUSTONE));
        //generate primal mass
        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("primal_mass", VerticalAnchor.absolute(0), VerticalAnchor.absolute(16)), PRIMAL_MASS));

        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }
}
