package androsa.gaiadimension;

import androsa.gaiadimension.proxy.ClientProxy;
import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.*;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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
    public static ModParticles particles;

    public static DimensionType dimType;

    public static final CreatureAttribute GAIAN = new CreatureAttribute();
    public static final CreatureAttribute CORRUPT = new CreatureAttribute();

    public static final DamageSource CORRUPTION = new DamageSource("corruption").setDamageBypassesArmor();

    public static CommonProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public GaiaDimensionMod() {
        final Pair<ModGaiaConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ModGaiaConfig::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPair.getRight());
        config = specPair.getLeft();
    }

    @SubscribeEvent
    public void preInit(FMLCommonSetupEvent event) {
        DimensionManager.registerDimension(new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), GAIA, null, true);
        GaiaDimensionMod.LOGGER.info("We are set for the world of Gaia.");

        ModBiomes.addBiomeTypes();
        proxy.doPreLoadRegistration();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> ModContainers::registerScreens);
    }

    public void clientSetup(FMLClientSetupEvent event) {
        particles.registerFactories();
    }
}