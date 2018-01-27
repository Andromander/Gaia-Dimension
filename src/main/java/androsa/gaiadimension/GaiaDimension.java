package androsa.gaiadimension;

import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDFluids;
import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.world.WorldProviderGaia;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = GaiaDimension.MODID,
        name = "GaiaDimension",
        version = GaiaDimension.VERSION)

public class GaiaDimension
{
    public static final String MODID = "gaiadimension";
    public static final String VERSION = "1.0";

    public static final GDItems items = new GDItems();
    public static final GDBlocks blocks = new GDBlocks();
    public static final GDFluids fluids = new GDFluids();
    public static final String ARMOR_DIR = "gaiadimension:textures/armor/";

    public static final Material matMineralWater = new MaterialLiquid(MapColor.CYAN_STAINED_HARDENED_CLAY);
    public static final Material matSuperhotMagma = new MaterialLiquid(MapColor.BLUE);

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static DimensionType dimType;
    public static int backupdimensionID = -258;

    @SidedProxy(clientSide = "androsa.gaiadimension.proxy.ClientProxy", serverSide = "androsa.gaiadimension.proxy.CommonProxy")
    public static CommonProxy proxy;



    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        dimType = DimensionType.register("gaia", "_gaia", GDConfig.dimension.dimensionID, WorldProviderGaia.class, false);

        proxy.doPreLoadRegistration();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // got to change this to something else...
        System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());
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
