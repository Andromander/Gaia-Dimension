package androsa.gaiadimension;

import androsa.gaiadimension.client.ClientEvents;
import androsa.gaiadimension.client.GaiaDimensionRenderInfo;
import androsa.gaiadimension.data.*;
import androsa.gaiadimension.registry.*;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.MobType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Mod(GaiaDimensionMod.MODID)
public class GaiaDimensionMod {
    public static final String MODID = "gaiadimension";

    public static final String MODEL_DIR = "gaiadimension:textures/entity/";

    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static ModGaiaConfig.ClientConfig clientConfig;
    public static ModGaiaConfig.CommonConfig commonConfig;

    public static final MobType GAIAN = new MobType();
    public static final MobType CORRUPT = new MobType();

    public GaiaDimensionMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);
        modEventBus.addListener(this::hackyEvent);

        ModBiomes.BIOMES.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModMenus.CONTAINERS.register(modEventBus);
        ModDimensions.POI_TYPES.register(modEventBus);
//      ModEffects.POTIONS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
		ModFluids.FLUIDS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
//      ModParticles.PARTICLE_TYPES.register(modEventBus);
        ModRecipes.RECIPE_TYPES.register(modEventBus);
        ModRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        ModBlockEntities.TILE_ENTITIES.register(modEventBus);
        ModWorldgen.DECORATORS.register(modEventBus);
        ModWorldgen.FEATURES.register(modEventBus);
        ModWorldgen.FOLIAGE_PLACERS.register(modEventBus);
//      ModWorldgen.STRUCTURES.register(modEventBus);
        ModWorldgen.TRUNK_PLACERS.register(modEventBus);
        ModWorldgen.WORLD_CARVERS.register(modEventBus);

        final Pair<ModGaiaConfig.ClientConfig, ForgeConfigSpec> specPairC = new ForgeConfigSpec.Builder().configure(ModGaiaConfig.ClientConfig::new);
        final Pair<ModGaiaConfig.CommonConfig, ForgeConfigSpec> specPairB = new ForgeConfigSpec.Builder().configure(ModGaiaConfig.CommonConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPairC.getRight());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, specPairB.getRight());
        clientConfig = specPairC.getLeft();
        commonConfig = specPairB.getLeft();
    }

    public void hackyEvent(RegisterEvent event) {
        if (Objects.equals(event.getForgeRegistry(), ForgeRegistries.RECIPE_SERIALIZERS)) {
            ModDimensions.initDimension();
        }
    }

    public void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // needs to be in enqueue as vanilla WorldGen registry maps arent threadsafe.
            ModWorldgen.StructureTypes.init();
            ModBlocks.addStripping();
            ModBlocks.registerDispenserBehaviour();
        });
        ModBlocks.addPlants();
        ModEntities.registerSpawnPlacement();
        ModBiomes.addBiomeTypes();
    }

    public void clientSetup(FMLClientSetupEvent event) {
        DimensionSpecialEffects gaia = new GaiaDimensionRenderInfo();
        DimensionSpecialEffects.EFFECTS.put(new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), gaia);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ModMenus::registerScreens);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ModParticles::forgeClassLoadingIsFuckedThisShouldntBeHereButHereItIs);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::registerBlockColors);
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::registerItemColors);
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
        generator.addProvider(event.includeServer(), new GaiaBiomeTags(output, provider, event.getExistingFileHelper()));
        GaiaDatapackRegistries.generate(event.includeServer(), generator, output, provider);
    }
}