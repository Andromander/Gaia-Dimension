package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class ModBiomes {

    public static final RegistryKey<Biome> pink_agate_forest = registerKey("pink_agate_forest");
    public static final RegistryKey<Biome> blue_agate_taiga = registerKey("blue_agate_taiga");
    public static final RegistryKey<Biome> green_agate_jungle = registerKey("green_agate_jungle");
    public static final RegistryKey<Biome> purple_agate_swamp = registerKey("purple_agate_swamp");
    public static final RegistryKey<Biome> fossil_woodland = registerKey("fossil_woodland");
    public static final RegistryKey<Biome> mutant_agate_wildwood = registerKey("mutant_agate_wildwood");
    public static final RegistryKey<Biome> volcanic_lands = registerKey("volcanic_lands");
    public static final RegistryKey<Biome> static_wasteland = registerKey("static_wasteland");
    public static final RegistryKey<Biome> goldstone_lands = registerKey("goldstone_lands");
    public static final RegistryKey<Biome> crystal_plains = registerKey("crystal_plains");
    public static final RegistryKey<Biome> salt_dunes = registerKey("salt_dunes");
    public static final RegistryKey<Biome> shining_grove = registerKey("shining_grove");
    public static final RegistryKey<Biome> smoldering_bog = registerKey("smoldering_bog");
    public static final RegistryKey<Biome> mineral_reservoir = registerKey("mineral_reservoir");
    public static final RegistryKey<Biome> mineral_river = registerKey("mineral_river");

    private static RegistryKey<Biome> registerKey(String name) {
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
