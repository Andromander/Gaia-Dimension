package androsa.gaiadimension;

import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDConfig;
import androsa.gaiadimension.registry.GDFluids;
import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.world.WorldProviderGaia;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = GaiaDimension.MODID,
        name = "GaiaDimension",
        version = GaiaDimension.VERSION,
        dependencies = "required-after:forge@[14.23.3.2655,)"
)

public class GaiaDimension {
    public static final String MODID = "gaiadimension";
    public static final String VERSION = "1.0";

    public static final GDItems items = new GDItems();
    public static final GDBlocks blocks = new GDBlocks();
    public static final GDFluids fluids = new GDFluids();
    public static final String ARMOR_DIR = "gaiadimension:textures/armor/";
    public static final String MODEL_DIR = "gaiadimension:textures/model/";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static DimensionType dimType;
    public static int backupdimensionID = -258;

    public static final EnumCreatureAttribute GAIAN = EnumHelper.addCreatureAttribute("GAIAN");
    public static final EnumCreatureAttribute CORRUPT = EnumHelper.addCreatureAttribute("CORRUPT");

    @Instance(MODID)
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

        FluidRegistry.addBucketForFluid(GDFluids.mineralWater);
        FluidRegistry.addBucketForFluid(GDFluids.superhotMagma);
        FluidRegistry.addBucketForFluid(GDFluids.sweetMuck);

        proxy.doPreLoadRegistration();
    }

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getTranslationKey());

        GaiaDimension.LOGGER.info("Registering GUI Handler...");
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (!DimensionManager.isDimensionRegistered(GDConfig.dimension.dimensionID)) {
            DimensionManager.registerDimension(GDConfig.dimension.dimensionID, GaiaDimension.dimType);
        } else {
            GaiaDimension.LOGGER.warn("The ID '{}' chosen in the config log is already in use. Falling back onto backup dimension ID");
            DimensionManager.registerDimension(GaiaDimension.backupdimensionID, GaiaDimension.dimType);
            GDConfig.dimension.dimensionID = GaiaDimension.backupdimensionID;
        }
    }
}