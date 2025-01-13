package androsa.gaiadimension;

import androsa.gaiadimension.client.ClientEvents;
import androsa.gaiadimension.data.*;
import androsa.gaiadimension.data.provider.GaiaModelProvider;
import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.registration.ModSlotDisplay;
import androsa.gaiadimension.registry.helpers.GaiaConfig;
import androsa.gaiadimension.registry.helpers.RemapHelper;
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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
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

import java.util.concurrent.CompletableFuture;

@Mod(GaiaDimensionMod.MODID)
public class GaiaDimensionMod {
    public static final String MODID = "gaiadimension";

    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static GaiaConfig.ClientConfig clientConfig;
    public static GaiaConfig.CommonConfig commonConfig;

    public GaiaDimensionMod(IEventBus bus, ModContainer container) {
        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
        bus.addListener(this::gatherClientData);
        bus.addListener(this::gatherServerData);
        bus.addListener(this::extraRegistries);
        bus.addListener(ModDataMaps::registerDataMaps);

        GaiaBiomes.BIOMES.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModMenus.CONTAINERS.register(bus);
        ModTabs.CREATIVE_TABS.register(bus);
        ModPOIs.POI_TYPES.register(bus);
        ModEffects.MOB_EFFECTS.register(bus);
        ModEntities.ENTITY_DATA_SERIALIZERS.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        ModDataComponents.DATA_COMPONENTS.register(bus);
        ModFluids.FLUID_TYPES.register(bus);
        ModFluids.FLUIDS.register(bus);
        ModItems.ITEMS.register(bus);
        ModParticles.PARTICLE_TYPES.register(bus);
        ModPredicates.ENTITY_SUB_PREDICATES.register(bus);
        ModRecipes.RECIPE_TYPES.register(bus);
        ModRecipes.RECIPE_SERIALIZERS.register(bus);
        ModBlockEntities.TILE_ENTITIES.register(bus);
        ModSlotDisplay.SLOT_DISPLAYS.register(bus);
        ModStructures.STRUCTURE_PIECES.register(bus);
        ModStructures.STRUCTURE_PROCESSORS.register(bus);
        ModStructures.STRUCTURE_TYPES.register(bus);
        ModSounds.SOUND_EVENTS.register(bus);
        ModWorldgen.DECORATORS.register(bus);
        ModWorldgen.FEATURES.register(bus);
        ModWorldgen.FOLIAGE_PLACERS.register(bus);
        ModWorldgen.TRUNK_PLACERS.register(bus);
        ModWorldgen.WORLD_CARVERS.register(bus);

        RemapHelper.remapEntries();

        final Pair<GaiaConfig.ClientConfig, ModConfigSpec> specPairC = new ModConfigSpec.Builder().configure(GaiaConfig.ClientConfig::new);
        final Pair<GaiaConfig.CommonConfig, ModConfigSpec> specPairB = new ModConfigSpec.Builder().configure(GaiaConfig.CommonConfig::new);
        container.registerConfig(ModConfig.Type.CLIENT, specPairC.getRight());
        container.registerConfig(ModConfig.Type.COMMON, specPairB.getRight());
        clientConfig = specPairC.getLeft();
        commonConfig = specPairB.getLeft();
    }

    //TODO: Verify this event's necessity
    public void extraRegistries(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.BIOME_SOURCE) {
            Registry.register(BuiltInRegistries.BIOME_SOURCE, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "gaia_dimension"), GaiaBiomeSource.CODEC);
        }
        if (event.getRegistryKey() == Registries.CHUNK_GENERATOR) {
            Registry.register(BuiltInRegistries.CHUNK_GENERATOR, ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "gaia_gen"), GaiaChunkGenerator.CODEC);
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
        ClientEvents.registerBlockRenderers();
        ModItems.addItemProperties();
    }

    public void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        // FIXME: NeoForge removed their custom DataGen stuff due to Lex's old code breaking, check out BlockModelGenerators and ItemModelGenerators
        generator.addProvider(true, new GaiaModelProvider(output));
    }

    public void gatherServerData(GatherDataEvent.Server event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        GaiaBlockTags blocktags = new GaiaBlockTags(output, provider);

        generator.addProvider(true, new GaiaLootTables(output, provider));
        generator.addProvider(true, new GaiaRecipes.Runner(output, provider));
        generator.addProvider(true, blocktags);
        generator.addProvider(true, new GaiaItemTags(output, provider, blocktags.contentsGetter()));
        generator.addProvider(true, new GaiaFluidTags(output, provider));
        generator.addProvider(true, new GaiaDataMaps(output, provider));

        DatapackBuiltinEntriesProvider datapackEntries = new GaiaDatapackRegistries(output, provider);
        CompletableFuture<HolderLookup.Provider> datapackProvider = datapackEntries.getRegistryProvider();
        generator.addProvider(true, datapackEntries);
        generator.addProvider(true, new GaiaBiomeTags(output, datapackProvider));
        generator.addProvider(true, new GaiaDamageTags(output, datapackProvider));
        generator.addProvider(true, new GaiaEntityTags(output, datapackProvider));
        generator.addProvider(true, new GaiaAdvancements(output, datapackProvider));
    }
}