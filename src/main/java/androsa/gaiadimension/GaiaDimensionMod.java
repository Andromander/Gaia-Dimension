package androsa.gaiadimension;

import androsa.gaiadimension.client.ClientEvents;
import androsa.gaiadimension.data.*;
import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.bootstrap.GaiaDimensions;
import androsa.gaiadimension.registry.helpers.GaiaConfig;
import androsa.gaiadimension.registry.registration.*;
import androsa.gaiadimension.registry.values.GaiaFluidAttributes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.MobType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.DistExecutor;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
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
        modEventBus.addListener(this::gatherData);
        modEventBus.addListener(this::hackyEvent);

        GaiaBiomes.BIOMES.register(modEventBus);
        ModBlocks.BLOCKS.register(bus);
        ModMenus.CONTAINERS.register(bus);
        ModTabs.CREATIVE_TABS.register(bus);
        ModPOIs.POI_TYPES.register(bus);
        ModEffects.MOB_EFFECTS.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        ModFluids.FLUID_TYPES.register(modEventBus);
        ModFluids.FLUIDS.register(modEventBus);
        ModItems.ITEMS.register(bus);
        ModParticles.PARTICLE_TYPES.register(modEventBus);
        ModRecipes.RECIPE_TYPES.register(modEventBus);
        ModRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        ModBlockEntities.TILE_ENTITIES.register(modEventBus);
        ModStructures.STRUCTURE_PIECES.register(modEventBus);
        ModStructures.STRUCTURE_PROCESSORS.register(modEventBus);
        ModStructures.STRUCTURE_TYPES.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModWorldgen.DECORATORS.register(modEventBus);
        ModWorldgen.FEATURES.register(modEventBus);
        ModWorldgen.FOLIAGE_PLACERS.register(modEventBus);
        ModWorldgen.TRUNK_PLACERS.register(modEventBus);
        ModWorldgen.WORLD_CARVERS.register(modEventBus);

        final Pair<GaiaConfig.ClientConfig, ForgeConfigSpec> specPairC = new ForgeConfigSpec.Builder().configure(GaiaConfig.ClientConfig::new);
        final Pair<GaiaConfig.CommonConfig, ForgeConfigSpec> specPairB = new ForgeConfigSpec.Builder().configure(GaiaConfig.CommonConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPairC.getRight());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, specPairB.getRight());
        clientConfig = specPairC.getLeft();
        commonConfig = specPairB.getLeft();
    }

    public void hackyEvent(RegisterEvent event) {
        if (Objects.equals(event.getForgeRegistry(), ForgeRegistries.RECIPE_SERIALIZERS)) {
            GaiaDimensions.initDimension();
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
        generator.addProvider(event.includeServer(), new GaiaRecipes(output));
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