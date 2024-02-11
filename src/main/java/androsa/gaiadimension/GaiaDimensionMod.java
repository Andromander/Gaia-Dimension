package androsa.gaiadimension;

import androsa.gaiadimension.client.ClientEvents;
import androsa.gaiadimension.data.*;
import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.bootstrap.GaiaDimensions;
import androsa.gaiadimension.registry.helpers.GaiaConfig;
import androsa.gaiadimension.registry.registration.*;
import androsa.gaiadimension.registry.values.GaiaFluidAttributes;
import androsa.gaiadimension.world.chunk.GaiaBiomeSource;
import androsa.gaiadimension.world.chunk.GaiaChunkGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Mod(GaiaDimensionMod.MODID)
public class GaiaDimensionMod {
    public static final String MODID = "gaiadimension";

    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static GaiaConfig.ClientConfig clientConfig;
    public static GaiaConfig.CommonConfig commonConfig;

    public static final MobType GAIAN = new MobType();
    public static final MobType CORRUPT = new MobType();

    public GaiaDimensionMod(IEventBus bus) {
        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherData);
        bus.addListener(this::extraRegistries);

        GaiaBiomes.BIOMES.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModMenus.CONTAINERS.register(bus);
        ModTabs.CREATIVE_TABS.register(bus);
        ModPOIs.POI_TYPES.register(bus);
        ModEffects.MOB_EFFECTS.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        ModFluids.FLUID_TYPES.register(bus);
        ModFluids.FLUIDS.register(bus);
        ModItems.ITEMS.register(bus);
        ModParticles.PARTICLE_TYPES.register(bus);
        ModRecipes.RECIPE_TYPES.register(bus);
        ModRecipes.RECIPE_SERIALIZERS.register(bus);
        ModBlockEntities.TILE_ENTITIES.register(bus);
        ModStructures.STRUCTURE_PIECES.register(bus);
        ModStructures.STRUCTURE_PROCESSORS.register(bus);
        ModStructures.STRUCTURE_TYPES.register(bus);
        ModSounds.SOUND_EVENTS.register(bus);
        ModWorldgen.DECORATORS.register(bus);
        ModWorldgen.FEATURES.register(bus);
        ModWorldgen.FOLIAGE_PLACERS.register(bus);
        ModWorldgen.TRUNK_PLACERS.register(bus);
        ModWorldgen.WORLD_CARVERS.register(bus);

        final Pair<GaiaConfig.ClientConfig, ModConfigSpec> specPairC = new ModConfigSpec.Builder().configure(GaiaConfig.ClientConfig::new);
        final Pair<GaiaConfig.CommonConfig, ModConfigSpec> specPairB = new ModConfigSpec.Builder().configure(GaiaConfig.CommonConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPairC.getRight());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, specPairB.getRight());
        clientConfig = specPairC.getLeft();
        commonConfig = specPairB.getLeft();
    }

    //TODO: Verify this event's necessity
    public void extraRegistries(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.BIOME_SOURCE) {
            Registry.register(BuiltInRegistries.BIOME_SOURCE, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"), GaiaBiomeSource.CODEC);
        }
        if (event.getRegistryKey() == Registries.CHUNK_GENERATOR) {
            Registry.register(BuiltInRegistries.CHUNK_GENERATOR, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_gen"), GaiaChunkGenerator.CODEC);
        }
    }

    public void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModBlocks.addStripping();
            ModBlocks.registerDispenserBehaviour();
            GaiaFluidAttributes.registerFluidInteractions();
        });
        ModBlocks.addPlants();
    }

    public void clientSetup(FMLClientSetupEvent event) {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::registerBlockRenderers);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ModItems::addItemProperties);
    }

    public void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        GaiaBlockTags blocktags = new GaiaBlockTags(output, provider, event.getExistingFileHelper());

        generator.addProvider(event.includeClient(), new GaiaBlockStates(output, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new GaiaItemModels(output, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new GaiaLootTables(output));
        generator.addProvider(event.includeServer(), new GaiaRecipes(output, provider));
        generator.addProvider(event.includeServer(), new GaiaAdvancements(output, provider, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), blocktags);
        generator.addProvider(event.includeServer(), new GaiaItemTags(output, provider, blocktags.contentsGetter(), event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new GaiaFluidTags(output, provider, event.getExistingFileHelper()));

        DatapackBuiltinEntriesProvider datapackEntries = new GaiaDatapackRegistries(output, provider);
        CompletableFuture<HolderLookup.Provider> datapackProvider = datapackEntries.getRegistryProvider();
        generator.addProvider(event.includeServer(), datapackEntries);
        generator.addProvider(event.includeServer(), new GaiaBiomeTags(output, datapackProvider, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new GaiaDamageTags(output, datapackProvider, event.getExistingFileHelper()));
    }
}