package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.GaiaBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraftforge.common.BiomeDictionary.*;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, GaiaDimensionMod.MODID);

    public static final RegistryKey<Biome> pink_agate_forest = registerBiome("pink_agate_forest", GaiaBiomes.makePinkAgateForest());
    public static final RegistryKey<Biome> blue_agate_taiga = registerBiome("blue_agate_taiga", GaiaBiomes.makeBlueAgateTaiga());
    public static final RegistryKey<Biome> green_agate_jungle = registerBiome("green_agate_jungle", GaiaBiomes.makeGreenAgateJungle());
    public static final RegistryKey<Biome> purple_agate_swamp = registerBiome("purple_agate_swamp", GaiaBiomes.makePurpleAgateSwamp());
    public static final RegistryKey<Biome> fossil_woodland = registerBiome("fossil_woodland", GaiaBiomes.makeFossilWoodland());
    public static final RegistryKey<Biome> mutant_agate_wildwood = registerBiome("mutant_agate_wildwood", GaiaBiomes.makeMutantAgateWildwood());
    public static final RegistryKey<Biome> volcanic_lands = registerBiome("volcanic_lands", GaiaBiomes.makeVolcanicLands());
    public static final RegistryKey<Biome> static_wasteland = registerBiome("static_wasteland", GaiaBiomes.makeStaticWasteland());
    public static final RegistryKey<Biome> goldstone_lands = registerBiome("goldstone_lands", GaiaBiomes.makeGoldstoneLands());
    public static final RegistryKey<Biome> crystal_plains = registerBiome("crystal_plains", GaiaBiomes.makeCrystalPlains());
    public static final RegistryKey<Biome> salt_dunes = registerBiome("salt_dunes", GaiaBiomes.makeSaltDunes());
    public static final RegistryKey<Biome> shining_grove = registerBiome("shining_grove", GaiaBiomes.makeShiningGrove());
    public static final RegistryKey<Biome> smoldering_bog = registerBiome("smoldering_bog", GaiaBiomes.makeSmolderingBog());
    public static final RegistryKey<Biome> mineral_reservoir = registerBiome("mineral_reservoir", GaiaBiomes.makeMineralReservoir());
    public static final RegistryKey<Biome> mineral_river = registerBiome("mineral_river", GaiaBiomes.makeMineralRiver());

    private static RegistryKey<Biome> registerBiome(String name, Biome biome) {
        BIOMES.register(name, () -> biome);
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(GaiaDimensionMod.MODID, name));
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
