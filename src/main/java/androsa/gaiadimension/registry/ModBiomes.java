package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraftforge.common.BiomeDictionary.*;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, GaiaDimensionMod.MODID);

    public static final RegistryKey<Biome> pink_agate_forest = registerBiome("pink_agate_forest");
    public static final RegistryKey<Biome> blue_agate_taiga = registerBiome("blue_agate_taiga");
    public static final RegistryKey<Biome> green_agate_jungle = registerBiome("green_agate_jungle");
    public static final RegistryKey<Biome> purple_agate_swamp = registerBiome("purple_agate_swamp");
    public static final RegistryKey<Biome> fossil_woodland = registerBiome("fossil_woodland");
    public static final RegistryKey<Biome> mutant_agate_wildwood = registerBiome("mutant_agate_wildwood");
    public static final RegistryKey<Biome> volcanic_lands = registerBiome("volcanic_lands");
    public static final RegistryKey<Biome> static_wasteland = registerBiome("static_wasteland");
    public static final RegistryKey<Biome> goldstone_lands = registerBiome("goldstone_lands");
    public static final RegistryKey<Biome> crystal_plains = registerBiome("crystal_plains");
    public static final RegistryKey<Biome> salt_dunes = registerBiome("salt_dunes");
    public static final RegistryKey<Biome> shining_grove = registerBiome("shining_grove");
    public static final RegistryKey<Biome> smoldering_bog = registerBiome("smoldering_bog");
    public static final RegistryKey<Biome> mineral_reservoir = registerBiome("mineral_reservoir");
    public static final RegistryKey<Biome> mineral_river = registerBiome("mineral_river");

    private static RegistryKey<Biome> registerBiome(String name) {
    	//Supply a dummy biome, as Biome are only required for IDs. They do not save generations
        BIOMES.register(name, () -> new Biome.Builder()
				.precipitation(Biome.RainType.NONE)
				.biomeCategory(Biome.Category.NONE)
				.depth(0)
				.downfall(0)
				.scale(0)
				.temperature(0)
				.specialEffects(new BiomeAmbience.Builder().fogColor(0).waterColor(0).waterFogColor(0).skyColor(0).build())
				.generationSettings(new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS).build())
				.mobSpawnSettings(new MobSpawnInfo.Builder().build())
				.temperatureAdjustment(Biome.TemperatureModifier.NONE)
				.build());
        return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, name));
    }

    public static void addBiomeTypes() {
        addTypes(pink_agate_forest, Type.FOREST);
        addTypes(blue_agate_taiga, Type.CONIFEROUS);
        addTypes(green_agate_jungle, Type.JUNGLE);
        addTypes(purple_agate_swamp, Type.SWAMP, Type.MAGICAL);
        addTypes(mutant_agate_wildwood, Type.FOREST, Type.LUSH, Type.MAGICAL);
        addTypes(fossil_woodland, Type.SAVANNA);
        addTypes(crystal_plains, Type.PLAINS);
        addTypes(salt_dunes, Type.DRY, Type.HOT, Type.SANDY, Type.SPARSE);
        addTypes(smoldering_bog, Type.HOT, Type.WASTELAND, Type.DEAD);
        addTypes(shining_grove, Type.LUSH, Type.FOREST, Type.MAGICAL);
        addTypes(volcanic_lands, Type.HOT, Type.SPARSE, Type.DRY, Type.MOUNTAIN);
        addTypes(static_wasteland, Type.WASTELAND, Type.MOUNTAIN);
        addTypes(goldstone_lands, Type.WASTELAND, Type.PLAINS);
        addTypes(mineral_reservoir, Type.OCEAN);
        addTypes(mineral_river, Type.RIVER);
    }
}
