package androsa.gaiadimension;

import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.GDConfig;
import androsa.gaiadimension.world.WorldProviderGaia;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// updateJSON = "https://raw.githubusercontent.com/Andromander/Gaia-Dimension/master/update.json"
@Mod(GaiaDimension.MODID)
public class GaiaDimension {
    public static final String MODID = "gaiadimension";
    public static final String VERSION = "1.0.5";
    public static final String NAME = "Gaia Dimension";

    public static final String ARMOR_DIR = "gaiadimension:textures/armor/";
    public static final String MODEL_DIR = "gaiadimension:textures/model/";
    public static final ResourceLocation POTION_TEXTURES = new ResourceLocation(MODID, "textures/gui/potions.png");

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static DimensionType dimType;

    public static final CreatureAttribute GAIAN = new CreatureAttribute();
    public static final CreatureAttribute CORRUPT = new CreatureAttribute();

    public static final DamageSource CORRUPTION = new DamageSource("corruption").setDamageBypassesArmor();

    public static GaiaDimension instance;

    @SidedProxy(clientSide = "androsa.gaiadimension.proxy.ClientProxy", serverSide = "androsa.gaiadimension.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        dimType = DimensionType.register("Gaia", "_gaia", GDConfig.dimension.dimensionID, WorldProviderGaia.class, false);
        GaiaDimension.LOGGER.info("We are set for the world of Gaia.");

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

        proxy.doPreLoadRegistration();
    }

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        GaiaDimension.LOGGER.info("Registering GUI Handler...");
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        DimensionManager.registerDimension(GDConfig.dimension.dimensionID, GaiaDimension.dimType);
    }
}