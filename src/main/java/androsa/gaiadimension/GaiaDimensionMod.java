package androsa.gaiadimension;

import androsa.gaiadimension.registry.*;
import androsa.gaiadimension.world.GaiaTeleporter;
import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static androsa.gaiadimension.registry.ModDimensions.GAIA;

@Mod(GaiaDimensionMod.MODID)
public class GaiaDimensionMod {
    public static final String MODID = "gaiadimension";

    public static final String ARMOR_DIR = "gaiadimension:textures/armor/";
    public static final String MODEL_DIR = "gaiadimension:textures/model/";

    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static ModGaiaConfig config;
    public static DimensionType gaia_dimension;
    public static GaiaTeleporter gaiaTeleporter;

    public static final CreatureAttribute GAIAN = new CreatureAttribute();
    public static final CreatureAttribute CORRUPT = new CreatureAttribute();

    public static final DamageSource CORRUPTION = new DamageSource("corruption").setDamageBypassesArmor();

    public static final Tag<Block> AGATE_LOGS = new BlockTags.Wrapper(new ResourceLocation(MODID, "agate_logs"));

    public GaiaDimensionMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        ModBiomes.BIOMES.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModContainers.CONTAINERS.register(modEventBus);
        ModDimensions.BIOME_PROVIDER_TYPES.register(modEventBus);
        ModDimensions.CHUNK_GENERATOR_TYPES.register(modEventBus);
        ModDimensions.MOD_DIMENSIONS.register(modEventBus);
        ModEffects.POTIONS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModFluids.FLUIDS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModParticles.PARTICLE_TYPES.register(modEventBus);
        ModRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        ModTileEntities.TILE_ENTITIES.register(modEventBus);
        ModWorldgen.FEATURES.register(modEventBus);
        ModWorldgen.SURFACE_BUILDERS.register(modEventBus);
        ModWorldgen.WORLD_CARVERS.register(modEventBus);

        final Pair<ModGaiaConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ModGaiaConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPair.getRight());
        config = specPair.getLeft();
    }

    public void setup(FMLCommonSetupEvent event) {
        ModEntities.registerSpawnPlacement();
        ModBiomes.addBiomeTypes();
        ModBiomes.addBiomeFeatures();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ModContainers::registerScreens);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ModEntities::registerEntityRender);
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public static class ForgeEventBus {
        @SubscribeEvent
        public static void registerModDimension(final RegisterDimensionsEvent e) {
            ResourceLocation gaia = new ResourceLocation(GaiaDimensionMod.MODID, "gaia");

            if (DimensionType.byName(gaia) == null) {
                gaia_dimension = DimensionManager.registerDimension(gaia, GAIA.get(), new PacketBuffer(Unpooled.buffer()), true);
                DimensionManager.keepLoaded(gaia_dimension, false);
            } else {
                gaia_dimension = DimensionType.byName(gaia);
            }

            GaiaDimensionMod.LOGGER.info("We are set for the world of Gaia.");
        }

        @SubscribeEvent
        public static void onWorldLoad(WorldEvent.Load e) {
            if (!(e.getWorld() instanceof ServerWorld)) return;

            ServerWorld world = (ServerWorld)e.getWorld();
            if (world.dimension.getType() == DimensionType.OVERWORLD || world.dimension.getType() == GaiaDimensionMod.gaia_dimension) {
                world.customTeleporters.add(gaiaTeleporter = new GaiaTeleporter(world));
            }
        }
    }
}