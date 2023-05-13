package androsa.gaiadimension.registry.bootstrap;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GaiaBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, GaiaDimensionMod.MODID);

    public static final ResourceKey<Biome> pink_agate_forest = registerBiome("pink_agate_forest");
    public static final ResourceKey<Biome> blue_agate_taiga = registerBiome("blue_agate_taiga");
    public static final ResourceKey<Biome> green_agate_jungle = registerBiome("green_agate_jungle");
    public static final ResourceKey<Biome> purple_agate_swamp = registerBiome("purple_agate_swamp");
    public static final ResourceKey<Biome> fossil_woodland = registerBiome("fossil_woodland");
    public static final ResourceKey<Biome> mutant_agate_wildwood = registerBiome("mutant_agate_wildwood");
    public static final ResourceKey<Biome> volcanic_lands = registerBiome("volcanic_lands");
    public static final ResourceKey<Biome> static_wasteland = registerBiome("static_wasteland");
    public static final ResourceKey<Biome> goldstone_lands = registerBiome("goldstone_lands");
    public static final ResourceKey<Biome> crystal_plains = registerBiome("crystal_plains");
    public static final ResourceKey<Biome> salt_dunes = registerBiome("salt_dunes");
    public static final ResourceKey<Biome> mookaite_mesa = registerBiome("mookaite_mesa");
    public static final ResourceKey<Biome> shining_grove = registerBiome("shining_grove");
    public static final ResourceKey<Biome> smoldering_bog = registerBiome("smoldering_bog");
    public static final ResourceKey<Biome> mineral_reservoir = registerBiome("mineral_reservoir");
    public static final ResourceKey<Biome> mineral_river = registerBiome("mineral_river");

    public static final ResourceKey<Biome> golden_forest = registerBiome("golden_forest");
    public static final ResourceKey<Biome> golden_plains = registerBiome("golden_plains");
    public static final ResourceKey<Biome> golden_hills = registerBiome("golden_hills");
    public static final ResourceKey<Biome> golden_sands = registerBiome("golden_sands");
    public static final ResourceKey<Biome> golden_marsh = registerBiome("golden_marsh");

    private static ResourceKey<Biome> registerBiome(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(GaiaDimensionMod.MODID, name));
    }
}
