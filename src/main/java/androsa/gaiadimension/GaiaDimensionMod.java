package androsa.gaiadimension;

import androsa.gaiadimension.proxy.ClientProxy;
import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModContainers;
import androsa.gaiadimension.registry.ModGaiaConfig;
import androsa.gaiadimension.registry.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static androsa.gaiadimension.registry.ModDimensions.GAIA_DIM;

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

        System.out.println(ModBlocks.heavy_soil);
    }

    @SubscribeEvent
    public void preInit(FMLCommonSetupEvent event) {
        DimensionManager.registerDimension(new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), GAIA_DIM, null, true);
        GaiaDimensionMod.LOGGER.info("We are set for the world of Gaia.");

        /* TODO: Re-enable once Fludis are back
        FluidRegistry.registerFluid(GDFluids.mineralWater);
        FluidRegistry.registerFluid(GDFluids.superhotMagma);
        FluidRegistry.registerFluid(GDFluids.sweetMuck);
        FluidRegistry.registerFluid(GDFluids.liquidBismuth);
        FluidRegistry.registerFluid(GDFluids.liquidAura);

        FluidRegistry.addBucketForFluid(GDFluids.mineralWater);
        FluidRegistry.addBucketForFluid(GDFluids.superhotMagma);
        FluidRegistry.addBucketForFluid(GDFluids.sweetMuck);
        FluidRegistry.addBucketForFluid(GDFluids.liquidBismuth);
        FluidRegistry.addBucketForFluid(GDFluids.liquidAura);
        */

        proxy.doPreLoadRegistration();
        particles.registerFactories();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> ModContainers::registerScreens);
    }

    static {
        FluidRegistry.enableUniversalBucket();
    }
}