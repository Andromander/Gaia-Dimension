package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.biomes.*;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static net.minecraftforge.common.BiomeDictionary.*;

@ObjectHolder(GaiaDimensionMod.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes {
    public static final Biome pink_agate_forest = new PinkAgateForestBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.FOREST)
            .depth(0.1F)
            .scale(0.1F)
            .temperature(0.66F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome blue_agate_taiga = new BlueAgateTaigaBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.TAIGA)
            .depth(0.1F)
            .scale(0.2F)
            .temperature(0.4F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome green_agate_jungle = new GreenAgateJungleBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.JUNGLE)
            .depth(0.1F)
            .scale(0.2F)
            .temperature(0.75F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome purple_agate_swamp = new PurpleAgateSwampBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.SWAMP)
            .depth(0.0F)
            .scale(0.05F)
            .temperature(0.66F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome fossil_woodland = new FossilWoodlandBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.FOREST)
            .depth(0.1F)
            .scale(0.05F)
            .temperature(0.66F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome mutant_agate_wildwood = new MutantAgateWildwoodBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.FOREST)
            .depth(0.1F)
            .scale(0.1F)
            .temperature(0.66F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome volcanic_lands = new VolcaniclandsBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.VOLCANIC, GaiaBiomeFeatures.VOLCANIC_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.EXTREME_HILLS)
            .depth(1.0F)
            .scale(0.7F)
            .temperature(0.9F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome static_wasteland = new StaticWastelandBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.STATIC, GaiaBiomeFeatures.WASTELAND_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.EXTREME_HILLS)
            .depth(3.0F)
            .scale(0.05F)
            .temperature(0.4F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome goldstone_lands = new GoldstonelandsBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.CORRUPT_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.PLAINS)
            .depth(0.125F)
            .scale(0.05F)
            .temperature(0.55F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome crystal_plains = new CrystalPlainsBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.PLAINS)
            .depth(0.05F)
            .scale(0.05F)
            .temperature(0.66F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome salt_dunes = new SaltDunesBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.SALT_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.DESERT)
            .depth(0.2F)
            .scale(0.05F)
            .temperature(0.8F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome shining_grove = new ShiningGroveBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.AURA_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.FOREST)
            .depth(0.4F)
            .scale(0.05F)
            .temperature(0.5F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome smoldering_bog = new SmolderingBogBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.BISMUTH_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.SWAMP)
            .depth(0.2F)
            .scale(0.02F)
            .temperature(0.9F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome mineral_reservoir = new MineralReservoirBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.SALT_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.OCEAN)
            .depth(-1.8F)
            .scale(0.1F)
            .temperature(0.66F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));
    public static final Biome mineral_river = new MineralRiverBiome((new Biome.Builder())
            .surfaceBuilder(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.SALT_SURFACE_CONFIG)
            .precipitation(Biome.RainType.NONE)
            .category(Biome.Category.OCEAN)
            .depth(-0.5F)
            .scale(0.0F)
            .temperature(0.66F)
            .downfall(0.0F)
            .waterColor(0x6C99B1)
            .waterFogColor(0x92BED4)
            .parent(null));

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> e) {
        final IForgeRegistry<Biome> registry = e.getRegistry();

        registry.register(pink_agate_forest.setRegistryName("pink_agate_forest"));
        registry.register(blue_agate_taiga.setRegistryName("blue_agate_taiga"));
        registry.register(green_agate_jungle.setRegistryName("green_agate_jungle"));
        registry.register(purple_agate_swamp.setRegistryName("purple_agate_swamp"));
        registry.register(mutant_agate_wildwood.setRegistryName("mutant_agate_wildwood"));
        registry.register(fossil_woodland.setRegistryName("fossil_woodland"));
        registry.register(crystal_plains.setRegistryName("crystal_plains"));
        registry.register(salt_dunes.setRegistryName("salt_dunes"));
        registry.register(smoldering_bog.setRegistryName("smoldering_bog"));
        registry.register(shining_grove.setRegistryName("shining_grove"));
        registry.register(volcanic_lands.setRegistryName("volcanic_lands"));
        registry.register(static_wasteland.setRegistryName("static_wasteland"));
        registry.register(goldstone_lands.setRegistryName("goldstone_lands"));
        registry.register(mineral_reservoir.setRegistryName("mineral_reservoir"));
        registry.register(mineral_river.setRegistryName("mineral_river"));
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
