package androsa.gaiadimension.biomes;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static net.minecraftforge.common.BiomeDictionary.*;

@Mod.EventBusSubscriber
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
                                .setRainDisabled()
                                .setHeightVariation(0.2F)
                ),
                Type.FOREST
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
