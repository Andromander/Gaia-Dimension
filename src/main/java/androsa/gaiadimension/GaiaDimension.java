package androsa.gaiadimension;

import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.world.GaiaChunkGenerator;
import androsa.gaiadimension.world.GaiaWorld;
import androsa.gaiadimension.world.WorldProviderGaia;
import net.minecraft.init.Blocks;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = GaiaDimension.MODID,
        name = "GaiaDimension",
        version = GaiaDimension.VERSION)

public class GaiaDimension
{
    public static final String MODID = "gaiadimension";
    public static final String VERSION = "1.0";

    public static final GDItems items = new GDItems();
    public static final GDBlocks blocks = new GDBlocks();
    public static final String ARMOR_DIR = "gaiadimension:textures/armor/";

    @SidedProxy(clientSide = "androsa.gaiadimension.proxy.ClientProxy", serverSide = "androsa.gaiadimension.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static DimensionType dimType;
    public static int backupdimensionID = -258;

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new GaiaTeleporter());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // got to change this to something else...
        System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());

        DimensionManager.registerDimension(GDConfig.dimension.dimensionID, DimensionType.register("Gaia", "_gaia", GDConfig.dimension.dimensionID, WorldProviderGaia.class, false));

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
