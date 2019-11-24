package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraftforge.common.BiomeDictionary.*;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, GaiaDimensionMod.MODID);

    public static final RegistryObject<Biome> pink_agate_forest = BIOMES.register("pink_agate_forest", () ->
            new PinkAgateForestBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.NONE, 0.1F, 0.1F, 0.66F));
    public static final RegistryObject<Biome> blue_agate_taiga = BIOMES.register("blue_agate_taiga", () ->
            new BlueAgateTaigaBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.NONE, 0.1F, 0.2F, 0.4F));
    public static final RegistryObject<Biome> green_agate_jungle = BIOMES.register("green_agate_jungle", () ->
            new GreenAgateJungleBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.NONE, 0.1F, 0.2F, 0.75F));
    public static final RegistryObject<Biome> purple_agate_swamp = BIOMES.register("purple_agate_swamp", () ->
            new PurpleAgateSwampBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.NONE, 0.0F, 0.05F, 0.66F));
    public static final RegistryObject<Biome> fossil_woodland = BIOMES.register("fossil_woodland", () ->
            new FossilWoodlandBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.NONE, 0.1F, 0.05F ,0.66F));
    public static final RegistryObject<Biome> mutant_agate_wildwood = BIOMES.register("mutant_agate_wildwood", () ->
            new MutantAgateWildwoodBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.NONE ,0.1F, 0.1F, 0.66F));
    public static final RegistryObject<Biome> volcanic_lands = BIOMES.register("volcanic_lands", () ->
            new VolcaniclandsBiome(ModWorldgen.s_volcanic, GaiaBiomeFeatures.VOLCANIC_SURFACE_CONFIG, Biome.Category.NONE, 1.0F, 0.7F, 0.9F));
    public static final RegistryObject<Biome> static_wasteland = BIOMES.register("static_wasteland", () ->
            new StaticWastelandBiome(ModWorldgen.s_static, GaiaBiomeFeatures.WASTELAND_SURFACE_CONFIG, Biome.Category.NONE, 3.0F, 0.05F, 0.4F));
    public static final RegistryObject<Biome> goldstone_lands = BIOMES.register("goldstone_lands", () ->
            new GoldstonelandsBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.CORRUPT_SURFACE_CONFIG, Biome.Category.NONE, 0.125F, 0.05F, 0.55F));
    public static final RegistryObject<Biome> crystal_plains = BIOMES.register("crystal_plains", () ->
            new CrystalPlainsBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.NONE, 0.05F, 0.05F, 0.66F));
    public static final RegistryObject<Biome> salt_dunes = BIOMES.register("salt_dunes", () ->
            new SaltDunesBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.SALT_SURFACE_CONFIG, Biome.Category.NONE, 0.2F, 0.05F, 0.8F));
    public static final RegistryObject<Biome> shining_grove = BIOMES.register("shining_grove", () ->
            new ShiningGroveBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.AURA_SURFACE_CONFIG, Biome.Category.NONE, 0.4F, 0.05F, 0.5F));
    public static final RegistryObject<Biome> smoldering_bog = BIOMES.register("smoldering_bog", () ->
            new SmolderingBogBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.BISMUTH_SURFACE_CONFIG, Biome.Category.NONE, 0.2F, 0.02F, 0.9F));
    public static final RegistryObject<Biome> mineral_reservoir = BIOMES.register("mineral_reservoir", () ->
            new MineralReservoirBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.SALT_SURFACE_CONFIG, Biome.Category.NONE, -1.8F, 0.1F, 0.66F));
    public static final RegistryObject<Biome> mineral_river = BIOMES.register("mineral_river", () ->
            new MineralRiverBiome(ModWorldgen.s_gaia, GaiaBiomeFeatures.SALT_SURFACE_CONFIG, Biome.Category.NONE, -0.5F, 0.0F, 0.66F));

    public static void addBiomeTypes() {
        addTypes(pink_agate_forest.get(), Type.FOREST);
        addTypes(blue_agate_taiga.get(), Type.CONIFEROUS);
        addTypes(green_agate_jungle.get(), Type.JUNGLE);
        addTypes(purple_agate_swamp.get(), Type.SWAMP, Type.MAGICAL);
        addTypes(mutant_agate_wildwood.get(), Type.FOREST, Type.LUSH, Type.MAGICAL);
        addTypes(fossil_woodland.get(), Type.SAVANNA);
        addTypes(crystal_plains.get(), Type.PLAINS);
        addTypes(salt_dunes.get(), Type.DRY, Type.HOT, Type.SANDY, Type.SPARSE);
        addTypes(smoldering_bog.get(), Type.HOT, Type.WASTELAND, Type.DEAD);
        addTypes(shining_grove.get(), Type.LUSH, Type.FOREST, Type.MAGICAL);
        addTypes(volcanic_lands.get(), Type.HOT, Type.SPARSE, Type.DRY, Type.MOUNTAIN);
        addTypes(static_wasteland.get(), Type.WASTELAND, Type.MOUNTAIN);
        addTypes(goldstone_lands.get(), Type.WASTELAND, Type.PLAINS);
        addTypes(mineral_reservoir.get(), Type.OCEAN);
        addTypes(mineral_river.get(), Type.RIVER);
    }

    public static void addBiomeFeatures() {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            if (biome instanceof BaseGaiaBiome) {
                ((BaseGaiaBiome)biome).addFeatures();
            }
        }
    }
}
