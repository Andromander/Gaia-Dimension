package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.GaiaBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ObjectHolder;

public class ModBiomes {

    public static final RegistryKey<Biome> pink_agate_forest = registerBiome("pink_agate_forest", GaiaBiomes.makePinkAgateForest("pink_agate_forest"));
    public static final RegistryKey<Biome> blue_agate_taiga = registerBiome("blue_agate_taiga", GaiaBiomes.makeBlueAgateTaiga("blue_agate_taiga"));
    public static final RegistryKey<Biome> green_agate_jungle = registerBiome("green_agate_jungle", GaiaBiomes.makeGreenAgateJungle("green_agate_jungle"));
    public static final RegistryKey<Biome> purple_agate_swamp = registerBiome("purple_agate_swamp", GaiaBiomes.makePurpleAgateSwamp("purple_agate_swamp"));
    public static final RegistryKey<Biome> fossil_woodland = registerBiome("fossil_woodland", GaiaBiomes.makeFossilWoodland("fossil_woodland"));
    public static final RegistryKey<Biome> mutant_agate_wildwood = registerBiome("mutant_agate_wildwood", GaiaBiomes.makeMutantAgateWildwood("mutant_agate_wildwood"));
    public static final RegistryKey<Biome> volcanic_lands = registerBiome("volcanic_lands", GaiaBiomes.makeVolcanicLands("volcanic_lands"));
    public static final RegistryKey<Biome> static_wasteland = registerBiome("static_wasteland", GaiaBiomes.makeStaticWasteland("static_wasteland"));
    public static final RegistryKey<Biome> goldstone_lands = registerBiome("goldstone_lands", GaiaBiomes.makeGoldstoneLands("goldstone_lands"));
    public static final RegistryKey<Biome> crystal_plains = registerBiome("crystal_plains", GaiaBiomes.makeCrystalPlains("crystal_plains"));
    public static final RegistryKey<Biome> salt_dunes = registerBiome("salt_dunes", GaiaBiomes.makeSaltDunes("salt_dunes"));
    public static final RegistryKey<Biome> shining_grove = registerBiome("shining_grove", GaiaBiomes.makeShiningGrove("shining_grove"));
    public static final RegistryKey<Biome> smoldering_bog = registerBiome("smoldering_bog", GaiaBiomes.makeSmolderingBog("smoldering_bog"));
    public static final RegistryKey<Biome> mineral_reservoir = registerBiome("mineral_reservoir", GaiaBiomes.makeMineralReservoir("mineral_reservoir"));
    public static final RegistryKey<Biome> mineral_river = registerBiome("mineral_river", GaiaBiomes.makeMineralRiver("mineral_river"));

    private static RegistryKey<Biome> registerBiome(String name, Biome biome) {
        RegistryHelper.registerBiome(biome);
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(GaiaDimensionMod.MODID, name));
    }

//    public static void addBiomeTypes() {
//        addTypes(pink_agate_forest.get(), Type.FOREST);
//        addTypes(blue_agate_taiga.get(), Type.CONIFEROUS);
//        addTypes(green_agate_jungle.get(), Type.JUNGLE);
//        addTypes(purple_agate_swamp.get(), Type.SWAMP, Type.MAGICAL);
//        addTypes(mutant_agate_wildwood.get(), Type.FOREST, Type.LUSH, Type.MAGICAL);
//        addTypes(fossil_woodland.get(), Type.SAVANNA);
//        addTypes(crystal_plains.get(), Type.PLAINS);
//        addTypes(salt_dunes.get(), Type.DRY, Type.HOT, Type.SANDY, Type.SPARSE);
//        addTypes(smoldering_bog.get(), Type.HOT, Type.WASTELAND, Type.DEAD);
//        addTypes(shining_grove.get(), Type.LUSH, Type.FOREST, Type.MAGICAL);
//        addTypes(volcanic_lands.get(), Type.HOT, Type.SPARSE, Type.DRY, Type.MOUNTAIN);
//        addTypes(static_wasteland.get(), Type.WASTELAND, Type.MOUNTAIN);
//        addTypes(goldstone_lands.get(), Type.WASTELAND, Type.PLAINS);
//        addTypes(mineral_reservoir.get(), Type.OCEAN);
//        addTypes(mineral_river.get(), Type.RIVER);
//    }
}
