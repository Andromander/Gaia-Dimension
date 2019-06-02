package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static net.minecraftforge.common.BiomeDictionary.Type;

@EventBusSubscriber
public final class GDBiomesRegister {

    @SubscribeEvent
    public static void onRegisterBiomes(Register<Biome> event) {
        final BiomeRegistry biomes = new BiomeRegistry(event.getRegistry());

        biomes.register(
                "pink_agate_forest",
                new GDPinkAgateForest(
                        new BiomeProperties("Pink Agate Forest")
                                .setTemperature(0.66F)
                                .setRainDisabled()
                                .setRainfall(0.0F)
                                .setBaseHeight(0.1F)
                                .setHeightVariation(0.1F)
                ),
                Type.FOREST
        );

        biomes.register(
                "blue_agate_taiga",
                new GDBlueAgateTaiga(
                        new BiomeProperties("Blue Agate Taiga")
                                .setTemperature(0.4F)
                                .setRainDisabled()
                                .setRainfall(0.0F)
                                .setBaseHeight(0.1F)
                                .setHeightVariation(0.2F)
                ),
                Type.CONIFEROUS
        );

        biomes.register(
                "green_agate_jungle",
                new GDGreenAgateJungle(
                        new BiomeProperties("Green Agate Jungle")
                                .setTemperature(0.75F)
                                .setRainDisabled()
                                .setRainfall(0.0F)
                                .setBaseHeight(0.1F)
                                .setHeightVariation(0.2F)
                ),
                Type.JUNGLE
        );

        biomes.register("purple_agate_swamp",
                new GDPurpleAgateSwamp(
                        new BiomeProperties("Purple Agate Swamp")
                                .setTemperature(0.7F)
                                .setRainDisabled()
                                .setRainfall(0.0F)
                                .setHeightVariation(0.05F)
                ),
                Type.SWAMP, Type.MAGICAL
        );

        biomes.register(
                "fossil_woodland",
                new GDFossilWoodland(
                        new BiomeProperties("Fossil Woodland")
                                .setTemperature(0.66F)
                                .setRainDisabled()
                                .setRainfall(0.0F)
                                .setBaseHeight(0.1F)
                                .setHeightVariation(0.05F)
                ),
                Type.SAVANNA
        );

        biomes.register(
                "mutant_agate_wildwood",
                new GDMutantAgateWildwood(
                        new BiomeProperties("Mutant Agate Wildwood")
                                .setTemperature(0.66F)
                                .setRainDisabled()
                                .setRainfall(0.0F)
                                .setBaseHeight(0.1F)
                                .setHeightVariation(0.1F)
                ),
                Type.FOREST, Type.LUSH, Type.MAGICAL
        );

        biomes.register(
                "crystal_plains",
                new GDCrystalPlains(
                        new BiomeProperties("Crystal Plains")
                                .setTemperature(0.66F)
                                .setRainDisabled()
                                .setRainfall(0.0F)
                                .setBaseHeight(0.05F)
                                .setHeightVariation(0.05F)
                ),
                Type.PLAINS
        );

        biomes.register(
                "salt_dunes",
                new GDSaltDunes(
                        new BiomeProperties("Salt Dunes")
                                .setTemperature(0.8F)
                                .setRainDisabled()
                                .setRainfall(0.0F)
                                .setBaseHeight(0.2F)
                                .setHeightVariation(0.05F)
                ),
                Type.DRY, Type.HOT, Type.SANDY, Type.SPARSE
        );

        biomes.register(
                "smoldering_bog",
                new GDSmolderingBog(
                        new BiomeProperties("Smoldering Bog")
                                .setTemperature(0.9F)
                                .setRainDisabled()
                                .setRainfall(0.0F)
                                .setBaseHeight(0.2F)
                                .setHeightVariation(0.02F)
                ),
                Type.HOT, Type.WASTELAND, Type.DEAD
        );

        biomes.register(
                "volcaniclands",
                new GDVolcanicLands(
                        new BiomeProperties("Volcaniclands")
                                .setTemperature(0.9F)
                                .setRainfall(0.0F)
                                .setRainDisabled()
                                .setBaseHeight(1F)
                                .setHeightVariation(0.7F)
                ),
                Type.HOT, Type.SPARSE, Type.DRY, Type.MOUNTAIN
        );

        biomes.register(
                "static_wasteland",
                new GDStaticWasteland(
                        new BiomeProperties("Static Wasteland")
                                .setTemperature(0.4F)
                                .setRainfall(0.0F)
                                .setRainDisabled()
                                .setBaseHeight(3F)
                                .setHeightVariation(0.05F)
                ),
                Type.WASTELAND, Type.MOUNTAIN
        );

        biomes.register(
                "goldstonelands",
                new GDGoldstoneLands(
                        new BiomeProperties("Goldstonelands")
                                .setTemperature(0.55F)
                                .setRainfall(0.0F)
                                .setRainDisabled()
                                .setBaseHeight(0.125F)
                                .setHeightVariation(0.05F)
                ),
                Type.WASTELAND, Type.PLAINS
        );

        biomes.register(
                "mineral_reservoir",
                new GDMineralReservoir(
                        new BiomeProperties("Mineral Reservoir")
                                .setTemperature(0.66F)
                                .setRainfall(0.0F)
                                .setRainDisabled()
                                .setBaseHeight(-1.8F)
                                .setHeightVariation(0.1F)
                ),
                Type.OCEAN
        );

        biomes.register(
                "mineral_river",
                new GDMineralRiver(
                        new BiomeProperties("Mineral River")
                                .setTemperature(0.5F)
                                .setRainfall(0.0F)
                                .setRainDisabled()
                                .setBaseHeight(-0.5F)
                                .setHeightVariation(0)
                ),
                Type.RIVER
        );
    }

    private static class BiomeRegistry {

        private final IForgeRegistry<Biome> registry;

        BiomeRegistry(IForgeRegistry<Biome> registry) {
            this.registry = registry;
        }

        public void register(String registryName, Biome biome, Type... biomeTypes) {
            biome.setRegistryName(GaiaDimension.MODID, registryName);
            registry.register(biome);
            BiomeDictionary.addTypes(biome, biomeTypes);
        }
    }
}
