package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraftforge.common.BiomeDictionary.Type;
import static net.minecraftforge.common.BiomeDictionary.addTypes;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, GaiaDimensionMod.MODID);

    public static final ResourceKey<Biome> pink_agate_forest = registerBiome("pink_agate_forest");
    public static final ResourceKey<Biome> blue_agate_taiga = registerBiome("blue_agate_taiga");
    public static final ResourceKey<Biome> green_agate_jungle = registerBiome("green_agate_jungle");
    public static final ResourceKey<Biome> purple_agate_swamp = registerBiome("purple_agate_swamp");
    public static final ResourceKey<Biome> fossil_woodland = registerBiome("fossil_woodland");
    public static final ResourceKey<Biome> mutant_agate_wildwood = registerBiome("mutant_agate_wildwood");
    public static final ResourceKey<Biome> igneous_plains = registerBiome("igneous_plains");
    public static final ResourceKey<Biome> volcanic_lands = registerBiome("volcanic_lands");
    public static final ResourceKey<Biome> wasteland_hills = registerBiome("wasteland_hills");
    public static final ResourceKey<Biome> static_wasteland = registerBiome("static_wasteland");
    public static final ResourceKey<Biome> weirded_goldstone_lands = registerBiome("weirded_goldstone_lands");
    public static final ResourceKey<Biome> goldstone_lands = registerBiome("goldstone_lands");
    public static final ResourceKey<Biome> crystal_plains = registerBiome("crystal_plains");
    public static final ResourceKey<Biome> salt_dunes = registerBiome("salt_dunes");
    public static final ResourceKey<Biome> crystal_salt_dunes = registerBiome("crystal_salt_dunes");
    public static final ResourceKey<Biome> mookaite_mesa = registerBiome("mookaite_mesa");
    public static final ResourceKey<Biome> shining_grove = registerBiome("shining_grove");
    public static final ResourceKey<Biome> smoldering_bog = registerBiome("smoldering_bog");
    public static final ResourceKey<Biome> hotspot = registerBiome("hotspot");
    public static final ResourceKey<Biome> prismatic_steppe = registerBiome("prismatic_steppe");
    public static final ResourceKey<Biome> mineral_reservoir = registerBiome("mineral_reservoir");
    public static final ResourceKey<Biome> aquamarine_trench = registerBiome("aquarmarine_trench");
    public static final ResourceKey<Biome> salty_coast = registerBiome("salty_coast");
    public static final ResourceKey<Biome> tourmaline_coast = registerBiome("tourmaline_coast");
    public static final ResourceKey<Biome> mineral_river = registerBiome("mineral_river");

    public static final ResourceKey<Biome> golden_forest = registerBiome("golden_forest");
    public static final ResourceKey<Biome> golden_plains = registerBiome("golden_plains");
    public static final ResourceKey<Biome> golden_hills = registerBiome("golden_hills");
    public static final ResourceKey<Biome> golden_sands = registerBiome("golden_sands");
    public static final ResourceKey<Biome> golden_marsh = registerBiome("golden_marsh");

    public static final ResourceKey<Biome> glitter_caves = registerBiome("glitter_caves");
    public static final ResourceKey<Biome> energy_caves = registerBiome("energy_caves");
    public static final ResourceKey<Biome> sludge_caves = registerBiome("sludge_caves");
    public static final ResourceKey<Biome> corrupt_caves = registerBiome("corrupt_caves");
    public static final ResourceKey<Biome> static_caves = registerBiome("static_caves");
    public static final ResourceKey<Biome> magma_caves = registerBiome("magma_caves");
    public static final ResourceKey<Biome> golden_caves = registerBiome("golden_caves");

    private static ResourceKey<Biome> registerBiome(String name) {
    	//Supply a dummy biome, as Biome are only required for IDs. They do not save generations
        BIOMES.register(name, () -> new Biome.BiomeBuilder()
				.precipitation(Biome.Precipitation.NONE)
				.biomeCategory(Biome.BiomeCategory.NONE)
				.downfall(0)
				.temperature(0)
				.specialEffects(new BiomeSpecialEffects.Builder().fogColor(0).waterColor(0).waterFogColor(0).skyColor(0).build())
				.generationSettings(new BiomeGenerationSettings.Builder().build())
				.mobSpawnSettings(new MobSpawnSettings.Builder().build())
				.temperatureAdjustment(Biome.TemperatureModifier.NONE)
				.build());
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(GaiaDimensionMod.MODID, name));
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
        addTypes(mookaite_mesa, Type.DRY, Type.SPARSE, Type.HOT, Type.MESA, Type.PLATEAU);
        addTypes(smoldering_bog, Type.HOT, Type.WASTELAND, Type.DEAD);
        addTypes(shining_grove, Type.LUSH, Type.FOREST, Type.MAGICAL);
        addTypes(volcanic_lands, Type.HOT, Type.SPARSE, Type.DRY, Type.MOUNTAIN);
        addTypes(static_wasteland, Type.WASTELAND, Type.MOUNTAIN);
        addTypes(goldstone_lands, Type.WASTELAND, Type.PLAINS);
        addTypes(mineral_reservoir, Type.OCEAN);
        addTypes(mineral_river, Type.RIVER);
    }
}
