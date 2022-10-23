package androsa.gaiadimension.world.chunk;

import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
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
    public static final SurfaceRules.RuleSource WASTELAND_STONE = stateRule(ModBlocks.wasteland_stone);
    public static final SurfaceRules.RuleSource GOLDEN_STONE = stateRule(ModBlocks.golden_stone);
    public static final SurfaceRules.RuleSource SOLID_GOLDEN_STONE = stateRule(ModBlocks.brilliant_stone);
    public static final SurfaceRules.RuleSource GAIA_STONE = stateRule(ModBlocks.gaia_stone);
    public static final SurfaceRules.RuleSource PRIMAL_MASS = stateRule(ModBlocks.primal_mass);
    public static final SurfaceRules.RuleSource NEXUSTONE = stateRule(ModBlocks.nexustone);
    public static final SurfaceRules.RuleSource BEDROCK = SurfaceRules.state(Blocks.BEDROCK.defaultBlockState());

    private static SurfaceRules.RuleSource stateRule(RegistryObject<Block> block) {
        return SurfaceRules.state(block.get().defaultBlockState());
    }

    public static SurfaceRules.RuleSource gaia(boolean hasSeaLevel, boolean bedrockRoof, boolean bedrockFloor) {
        SurfaceRules.ConditionSource aboveWaterCondition = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource belowWaterCondition = SurfaceRules.waterStartCheck(-6, -1);
        SurfaceRules.RuleSource dirtRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.goldstone_lands), CORRUPT_SOIL),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.smoldering_bog), BOGGY_SOIL),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.shining_grove), LIGHT_SOIL),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.golden_hills, ModBiomes.golden_forest, ModBiomes.golden_plains), AURUM_SOIL),
                HEAVY_SOIL);
        SurfaceRules.RuleSource grassRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.goldstone_lands), CORRUPT_GRASS),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.smoldering_bog), MURKY_GRASS),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.shining_grove), SOFT_GRASS),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.golden_hills, ModBiomes.golden_forest, ModBiomes.golden_plains), GILDED_GRASS),
                GLITTER_GRASS);
        SurfaceRules.RuleSource defaultSurfaceRule = SurfaceRules.sequence(SurfaceRules.ifTrue(aboveWaterCondition, grassRule), dirtRule);
        SurfaceRules.RuleSource saltstoneRoofRule = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SALTSTONE), SALT); //TOD: Saltstone and salt
        SurfaceRules.RuleSource stoneroofrule = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, GAIA_STONE), PEBBLES);
        SurfaceRules.ConditionSource saltyBiomeCondition = SurfaceRules.isBiome(ModBiomes.salt_dunes, ModBiomes.crystal_salt_dunes, ModBiomes.salty_coast, ModBiomes.mineral_river, ModBiomes.mineral_reservoir);
        SurfaceRules.RuleSource stoneyRule =
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.static_wasteland),
                                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("wasteland_stone", VerticalAnchor.absolute(50), VerticalAnchor.absolute(63)), WASTELAND_STONE)),
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.golden_hills),
                                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("brilliant_stone", VerticalAnchor.absolute(100), VerticalAnchor.absolute(105))), SOLID_GOLDEN_STONE)),
                        SurfaceRules.ifTrue(saltyBiomeCondition, saltstoneRoofRule)/*,
                        SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.DRIPSTONE_CAVES), STONE)*/); //TODO: watch this space for new biomes
        SurfaceRules.RuleSource belowTopRule =
                SurfaceRules.sequence(
                        stoneyRule,
                        dirtRule);
        SurfaceRules.RuleSource topMaterialRule =
                SurfaceRules.sequence(
                        stoneyRule,
                        defaultSurfaceRule);
        SurfaceRules.RuleSource worldSurfaceRule =
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.ifTrue(aboveWaterCondition, topMaterialRule)),
                        SurfaceRules.ifTrue(belowWaterCondition,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, belowTopRule),
                                        SurfaceRules.ifTrue(saltyBiomeCondition,
                                                SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(0, true, 30, CaveSurface.FLOOR), SALTSTONE)))),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.mineral_reservoir, ModBiomes.mineral_river), saltstoneRoofRule),
                                        stoneroofrule)));

        ImmutableList.Builder<SurfaceRules.RuleSource> builder = ImmutableList.builder();
        if (bedrockRoof) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK));
        }

        if (bedrockFloor) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
        }

        SurfaceRules.RuleSource aboveSeaLevelRule = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), worldSurfaceRule);
        builder.add(hasSeaLevel ? aboveSeaLevelRule : worldSurfaceRule);
        builder.add(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.golden_hills, ModBiomes.golden_forest, ModBiomes.golden_plains),
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("golden_stone", VerticalAnchor.absolute(50), VerticalAnchor.absolute(63))), GOLDEN_STONE)));
        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("nexustone", VerticalAnchor.absolute(-48), VerticalAnchor.absolute(-32)), NEXUSTONE));
        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("primal_mass", VerticalAnchor.absolute(0), VerticalAnchor.absolute(16)), PRIMAL_MASS));
        return SurfaceRules.sequence(builder.build().toArray(SurfaceRules.RuleSource[]::new));
    }
}
