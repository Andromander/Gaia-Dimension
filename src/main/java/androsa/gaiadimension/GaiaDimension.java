package androsa.gaiadimension;

import androsa.gaiadimension.entity.GaiaEntities;
import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.recipe.GlitterFuelHandler;
import androsa.gaiadimension.recipe.PurifierFuelHandler;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDFluids;
import androsa.gaiadimension.registry.GDItems;
import androsa.gaiadimension.world.WorldProviderGaia;
import com.google.common.collect.Lists;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.IFuelHandler;
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

import java.util.List;

@Mod(modid = GaiaDimension.MODID,
        name = "GaiaDimension",
        version = GaiaDimension.VERSION,
        dependencies = "required-after:forge@[14.23.2.2611,)"
)

@SuppressWarnings("deprecated")
public class GaiaDimension {
    public static final String MODID = "gaiadimension";
    public static final String VERSION = "1.0";

    public static final GDItems items = new GDItems();
    public static final GDBlocks blocks = new GDBlocks();
    public static final GDFluids fluids = new GDFluids();
    public static final String ARMOR_DIR = "gaiadimension:textures/armor/";
    public static final String MODEL_DIR = "gaiadimension:textures/model/";

    private static final List<IFuelHandler> glitterFuelHandlers = Lists.newArrayList();
    private static final List<IFuelHandler> purifierFuelHandlers = Lists.newArrayList();

    public static final Material matMineralWater = new MaterialLiquid(MapColor.CYAN_STAINED_HARDENED_CLAY);
    public static final Material matSuperhotMagma = new MaterialLiquid(MapColor.BLUE);

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

        GaiaDimension.LOGGER.info("Registering creatures of Gaia...");
        registerCreatures();
        dimType = DimensionType.register("Gaia", "_gaia", GDConfig.dimension.dimensionID, WorldProviderGaia.class, false);
        GaiaDimension.LOGGER.info("We are set for the world of Gaia.");

        proxy.doPreLoadRegistration();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        //System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());

        GaiaDimension.LOGGER.info("Registering GUI Handler...");
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        GaiaDimension.LOGGER.info("Prepping Fuel for the tile entities...");
        registerFuelHandler(new GlitterFuelHandler(), FuelType.GLITTER_FURNACE);
        registerFuelHandler(new PurifierFuelHandler(), FuelType.PURIFIER);

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

    public enum FuelType {
        GLITTER_FURNACE,
        PURIFIER
    }

    public static void registerFuelHandler(IFuelHandler handler, FuelType type) {
        switch(type) {
            case GLITTER_FURNACE:
                glitterFuelHandlers.add(handler);
            case PURIFIER:
                purifierFuelHandlers.add(handler);
        }
    }

    public static int getFuelValue(ItemStack stack, FuelType type) {
        int fuelValue = 0;
        switch(type) {
            case GLITTER_FURNACE:
                for (IFuelHandler handler : glitterFuelHandlers)
                    fuelValue = Math.max(fuelValue, handler.getBurnTime(stack));
            case PURIFIER:
                for (IFuelHandler handler : purifierFuelHandlers)
                    fuelValue = Math.max(fuelValue, handler.getBurnTime(stack));
        }

        return fuelValue;
    }

    private void registerCreatures() {
        int id = 0;
        //GaiaEntities.registerEntity(GaiaEntityNames.EARTH_SHOT, GDShotGaianEnergy.class, id++, 150, 5, true);
        GaiaEntities.registerEntity(GaiaEntityNames.COMMON_SAPPER, androsa.gaiadimension.entity.GDCommonGrowthSapper.class, id++, 0x5A4514, 0xFF00FF);
        GaiaEntities.registerEntity(GaiaEntityNames.CHILLED_SAPPER, androsa.gaiadimension.entity.GDChilledGrowthSapper.class, id++, 0x5A4514, 0x0080A0);
        GaiaEntities.registerEntity(GaiaEntityNames.NUTRIENT_SAPPER, androsa.gaiadimension.entity.GDNutrientGrowthSapper.class, id++, 0x5A4514, 0x00FF10);
        GaiaEntities.registerEntity(GaiaEntityNames.MYSTIFIED_SAPPER, androsa.gaiadimension.entity.GDMystifiedGrowthSapper.class, id++, 0x5A4514, 0x800080);
        GaiaEntities.registerEntity(GaiaEntityNames.HOWLITE_WOLF, androsa.gaiadimension.entity.GDHowliteWolf.class, id++, 0xFF0000, 0x0000FF);
        GaiaEntities.registerEntity(GaiaEntityNames.SPELLBOUND_ELEMENTAL, androsa.gaiadimension.entity.GDSpellElement.class, id++, 0xFFFF00, 0x0000FF);
        GaiaEntities.registerEntity(GaiaEntityNames.ROCKY_LUGGEROTH, androsa.gaiadimension.entity.GDRockyLuggeroth.class, id++, 0x00FF00, 0xFF00FF);
        GaiaEntities.registerEntity(GaiaEntityNames.SHALURKER, androsa.gaiadimension.entity.GDShalurker.class, id++, 0xF0F0F0, 0x0F0F0F);
        GaiaEntities.registerEntity(GaiaEntityNames.MUCKLING, androsa.gaiadimension.entity.GDMuckling.class, id++, 0xF00000, 0x00000F);
        GaiaEntities.registerEntity(GaiaEntityNames.MARKUZAR_PLANT, androsa.gaiadimension.entity.GDMarkuzarPlant.class, id++, 0x00FF00, 0x800080);
        GaiaEntities.registerEntity(GaiaEntityNames.RUGGED_LURMORUS, androsa.gaiadimension.entity.GDRuggedLurmorus.class, id++, 0x294934, 0x204750);
        GaiaEntities.registerEntity(GaiaEntityNames.AGATE_GOLEM, androsa.gaiadimension.entity.GDAgateGolem.class, id++, 0x946353, 0x122534);

        GaiaEntities.registerEntity(GaiaEntityNames.BLUE_HOWLITE_WOLF, androsa.gaiadimension.entity.boss.GDBlueHowliteWolf.class, id++, 0x00FF00, 0xFF00FF);
        GaiaEntities.registerEntity(GaiaEntityNames.MALACHITE_GUARD, androsa.gaiadimension.entity.boss.GDMalachiteGuard.class, id++, 0x0000FF, 0x00FF00);
    }
}
