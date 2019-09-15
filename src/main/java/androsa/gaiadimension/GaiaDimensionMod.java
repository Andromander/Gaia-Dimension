package androsa.gaiadimension;

import androsa.gaiadimension.proxy.ClientProxy;
import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.*;
import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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
    public static ModParticles particles = new ModParticles();
    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public static DimensionType gaia_dimension;

    public static final CreatureAttribute GAIAN = new CreatureAttribute();
    public static final CreatureAttribute CORRUPT = new CreatureAttribute();

    public static final DamageSource CORRUPTION = new DamageSource("corruption").setDamageBypassesArmor();

    public static final Tag<Block> AGATE_LOGS = new BlockTags.Wrapper(new ResourceLocation(MODID, "agate_logs"));

    public GaiaDimensionMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        final Pair<ModGaiaConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ModGaiaConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPair.getRight());
        config = specPair.getLeft();
    }

    public void setup(FMLCommonSetupEvent event) {
        GaiaSpawnPlacements.registerSpawnPlacement();
        ModBiomes.addBiomeTypes();
        proxy.doPreLoadRegistration();
        DistExecutor.runWhenOn(Dist.CLIENT, () -> ModContainers::registerScreens);
    }

    public void clientSetup(FMLClientSetupEvent event) {
        particles.registerFactories(); //TODO: Remove on PR
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public static class ForgeEventBus {
        @SubscribeEvent
        public static void registerModDimension(final RegisterDimensionsEvent e) {
            ResourceLocation gaia = new ResourceLocation(GaiaDimensionMod.MODID, "gaia");

            if (DimensionType.byName(gaia) == null) {
                gaia_dimension = DimensionManager.registerDimension(gaia, GAIA, new PacketBuffer(Unpooled.buffer()), true);
                DimensionManager.keepLoaded(gaia_dimension, false);
            } else {
                gaia_dimension = DimensionType.byName(gaia);
            }

            GaiaDimensionMod.LOGGER.info("We are set for the world of Gaia.");
        }
    }
}