package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaBiomeProvider;
import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModEntities;
import com.google.common.collect.ImmutableMap;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;

import java.util.Map;

public class GaiaBiomes extends GaiaBiomeProvider {

    public GaiaBiomes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected Map<RegistryKey<Biome>, Biome> registerBiomes() {
        final ImmutableMap.Builder<RegistryKey<Biome>, Biome> biomes = ImmutableMap.builder();

        biomes.put(ModBiomes.pink_agate_forest, buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        pinkAgateForest(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 1))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.GROWTH_SAPPER.get(), 20, 3, 5))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.AGATE_GOLEM.get(), 15, 1, 3)))
                ).depth(0.1F).scale(0.1F).temperature(0.66F))
        );
        biomes.put(ModBiomes.blue_agate_taiga, buildBiome(
                createBuilder(
                        createAmbience(6851272, 9815527, 15381216),
                        blueAgateTaiga(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.HOWLITE_WOLF.get(), 15, 2, 4))
                                        .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.BLUE_HOWLITE_WOLF.get(), 1, 1, 1))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 1))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.GROWTH_SAPPER.get(), 20, 3, 5)))
                ).depth(0.1F).scale(0.2F).temperature(0.4F))
        );
        biomes.put(ModBiomes.green_agate_jungle, buildBiome(
                createBuilder(
                        createAmbience(4961870, 8437662, 15381216),
                        greenAgateJungle(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 1))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.GROWTH_SAPPER.get(), 20, 3, 5))
                                        .withSpawner(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(ModEntities.MARKUZAR_PLANT.get(), 15, 2, 4)))
                ).depth(0.1F).scale(0.2F).temperature(0.75F))
        );
        biomes.put(ModBiomes.purple_agate_swamp, buildBiome(
                createBuilder(
                        createAmbience(8417209, 11234801, 15381216),
                        purpleAgateSwamp(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 1))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.GROWTH_SAPPER.get(), 20, 3, 5))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.SPELLBOUND_ELEMENTAL.get(), 10, 2, 4)))
                ).depth(0.0F).scale(0.5F).temperature(0.66F))
        );
        biomes.put(ModBiomes.fossil_woodland, buildBiome(
                createBuilder(
                        createAmbience(12298105, 13016408, 15381216),
                        fossilWoodland(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.ANCIENT_LAGRAHK.get(), 10, 1, 2))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 1))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.ROCKY_LUGGEROTH.get(), 10, 4, 5))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.RUGGED_LURMORUS.get(), 10, 1, 3)))
                ).depth(0.1F).scale(0.05F).temperature(0.66F))
        );
        biomes.put(ModBiomes.mutant_agate_wildwood, buildBiome(
                createBuilder(
                        createAmbience(13948848, 15833793, 15381216),
                        mutantAgateWildwood(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 1))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.GROWTH_SAPPER.get(), 40, 3, 5))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.MUTANT_GROWTH_EXTRACTOR.get(), 5, 2, 4)))
                ).depth(0.1F).scale(0.1F).temperature(0.66F))
        );
        biomes.put(ModBiomes.volcanic_lands, buildBiome(
                createBuilder(
                        createAmbience(2302755, 4922905, 16086896),
                        volcanicLands(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.LESSER_SPITFIRE.get(), 10, 2, 4))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 3)))
                ).depth(1.0F).scale(0.7F).temperature(0.9F))
        );
        biomes.put(ModBiomes.static_wasteland, buildBiome(
                createBuilder(
                        createAmbience(2837910, 2633554, 5690794),
                        staticWasteland(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.LESSER_SHOCKSHOOTER.get(), 10, 2, 4))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 3)))
                ).depth(3.0F).scale(0.05F).temperature(0.4F))
        );
        biomes.put(ModBiomes.goldstone_lands, buildBiome(
                createBuilder(
                        createAmbience(2302755, 2236962, 12352044),
                        goldstoneLands(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.CORRUPT_SAPPER.get(), 20, 2, 4))
                                        .withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(ModEntities.CONTORTED_NAGA.get(), 10, 2, 3)))
                ).depth(0.125F).scale(0.05F).temperature(0.55F))
        );
        biomes.put(ModBiomes.crystal_plains, buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        crystalPlains(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 1))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.GROWTH_SAPPER.get(), 20, 4, 6))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.CRYSTAL_GOLEM.get(), 15, 1, 3)))
                ).depth(0.05F).scale(0.05F).temperature(0.66F))
        );
        biomes.put(ModBiomes.salt_dunes, buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        saltDunes(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 3))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.SALTION.get(), 15, 1, 3)))
                ).depth(0.2F).scale(0.05F).temperature(0.8F))
        );
        biomes.put(ModBiomes.smoldering_bog, buildBiome(
                createBuilder(
                        createAmbience(2500135, 1118482, 3287859, 8284598),
                        smolderingBog(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 1))
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.BISMUTH_ULETRUS.get(), 20, 2, 3)))
                ).depth(0.2F).scale(0.02F).temperature(0.9F))
        );
        biomes.put(ModBiomes.shining_grove, buildBiome(
                createBuilder(
                        createAmbience(7982765, 14546943, 15004627, 16764489),
                        shiningGrove(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 1)))
                ).depth(0.4F).scale(0.05F).temperature(0.5F))
        );
        biomes.put(ModBiomes.mineral_reservoir, buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        mineralBiome(),
                        buildSpawns(
                                createSpawns()
                                        .withSpawner(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(ModEntities.MINERAL_ARENTHIS.get(), 10, 1, 4)))
                ).depth(-1.8F).scale(0.1F).temperature(0.66F))
        );
        biomes.put(ModBiomes.mineral_river, buildBiome(
                createBuilder(
                        createAmbience(15901620, 13016408, 15381216),
                        mineralBiome(),
                        buildSpawns(createSpawns())
                ).depth(-0.5F).scale(0.0F).temperature(0.66F))
        );

        return biomes.build();
    }
}
