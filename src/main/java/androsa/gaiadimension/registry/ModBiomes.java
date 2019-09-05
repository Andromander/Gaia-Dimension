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
    public static final Biome pink_agate_forest = new PinkAgateForestBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.FOREST, 0.1F, 0.1F, 0.66F);
    public static final Biome blue_agate_taiga = new BlueAgateTaigaBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.TAIGA, 0.1F, 0.2F, 0.4F);
    public static final Biome green_agate_jungle = new GreenAgateJungleBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.JUNGLE, 0.1F, 0.2F, 0.75F);
    public static final Biome purple_agate_swamp = new PurpleAgateSwampBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.SWAMP, 0.0F, 0.05F, 0.66F);
    public static final Biome fossil_woodland = new FossilWoodlandBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.FOREST, 0.1F, 0.05F ,0.66F);
    public static final Biome mutant_agate_wildwood = new MutantAgateWildwoodBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.FOREST ,0.1F, 0.1F, 0.66F);
    public static final Biome volcanic_lands = new VolcaniclandsBiome(ModWorldgen.VOLCANIC, GaiaBiomeFeatures.VOLCANIC_SURFACE_CONFIG, Biome.Category.EXTREME_HILLS, 1.0F, 0.7F, 0.9F);
    public static final Biome static_wasteland = new StaticWastelandBiome(ModWorldgen.STATIC, GaiaBiomeFeatures.WASTELAND_SURFACE_CONFIG, Biome.Category.EXTREME_HILLS, 3.0F, 0.05F, 0.4F);
    public static final Biome goldstone_lands = new GoldstonelandsBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.CORRUPT_SURFACE_CONFIG, Biome.Category.PLAINS, 0.125F, 0.05F, 0.55F);
    public static final Biome crystal_plains = new CrystalPlainsBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.GENERIC_SURFACE_CONFIG, Biome.Category.PLAINS, 0.05F, 0.05F, 0.66F);
    public static final Biome salt_dunes = new SaltDunesBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.SALT_SURFACE_CONFIG, Biome.Category.DESERT, 0.2F, 0.05F, 0.8F);
    public static final Biome shining_grove = new ShiningGroveBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.AURA_SURFACE_CONFIG, Biome.Category.FOREST, 0.4F, 0.05F, 0.5F);
    public static final Biome smoldering_bog = new SmolderingBogBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.BISMUTH_SURFACE_CONFIG, Biome.Category.SWAMP, 0.2F, 0.02F, 0.9F);
    public static final Biome mineral_reservoir = new MineralReservoirBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.SALT_SURFACE_CONFIG, Biome.Category.OCEAN, -1.8F, 0.1F, 0.66F);
    public static final Biome mineral_river = new MineralRiverBiome(ModWorldgen.DEFAULT_GAIA, GaiaBiomeFeatures.SALT_SURFACE_CONFIG, Biome.Category.OCEAN, -0.5F, 0.0F, 0.66F);

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
