package androsa.gaiadimension.biomes;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static net.minecraftforge.common.BiomeDictionary.*;

@EventBusSubscriber
public final class GDBiomesRegister {
    @SuppressWarnings("OverlyCoupledMethod")
    @SubscribeEvent
    public static void onRegisterBiomes(Register<Biome> event) {
        final BiomeRegistry biomes = new BiomeRegistry(event.getRegistry());

        biomes.register(
                "pink_agate_forest",
                new GDPinkAgateForest(
                        new BiomeProperties("Pink Agate Forest")
                                .setTemperature(0.66F)
                                .setRainfall(0)
                                .setHeightVariation(0.1F)
                ),
                Type.FOREST
        );

        biomes.register(
                "green_agate_jungle",
                new GDGreenAgateJungle(
                        new BiomeProperties("Green Agate Jungle")
                        .setTemperature(0.75F)
                        .setRainfall(0)
                        .setHeightVariation(0.2F)
                ),
                Type.JUNGLE
        );
/*
        biomes.register(
                "crystal plains",
                new GDCrystalPlains(
                        new BiomeProperties("Crystal Plains")
                ),
                Type.PLAINS
        );

*/
        biomes.register(
                "mineral_reservoir",
                new GDMineralReservoir(
                        new BiomeProperties("Mineral Reservoir")
                        .setTemperature(0.66F)
                        .setRainfall(0)
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
                        .setRainfall(0)
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

        public void register(String registryName, Biome biome, Type biomeTypes) {
            biome.setRegistryName(GaiaDimension.MODID, registryName);
            registry.register(biome);
            BiomeDictionary.addTypes(biome, biomeTypes);
        }
    }
}
