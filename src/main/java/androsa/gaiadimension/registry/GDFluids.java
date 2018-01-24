package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.fluid.GDFluidBlock;
import androsa.gaiadimension.fluid.GDSuperhotMagma;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class GDFluids {

    public static Fluid mineralWater;
    public static Fluid superhotMagma;

    public static MaterialLiquid matMineralWater;
    public static MaterialLiquid matSuperhotMagma;

    public static GDFluidBlock mineralWaterBlock;
    public static GDFluidBlock superhotMagmaBlock;

    private static List<SuperRegistry> modelList;

    static {
        modelList = new ArrayList<>();

        mineralWater = GDFluidsRegister.createFluid(GaiaDimension.MODID, "mineral_water", "fluids/mineralwater/mineral_water", true, true);
        superhotMagma = GDFluidsRegister.createFluid(GaiaDimension.MODID, "superhot_magma", "fluids/superhotmagma/superhot_magma", true, true);

        mineralWater.setLuminosity(0).setDensity(1000).setViscosity(750).setGaseous(false).setColor(0xCEB0C0FF);
        superhotMagma.setLuminosity(15).setDensity(4000).setViscosity(4000).setGaseous(false).setColor(0xFF00FFFF).setTemperature(1000);

        matMineralWater = new MaterialLiquid(MapColor.CYAN_STAINED_HARDENED_CLAY);
        matSuperhotMagma = new MaterialLiquid(MapColor.BLUE);

        modelList.add(mineralWaterBlock = new GDFluidBlock(GDTabs.tabBlock, mineralWater, Material.WATER, "mineral_water"));
        modelList.add(superhotMagmaBlock = new GDSuperhotMagma(GDTabs.tabBlock, superhotMagma, Material.LAVA, "superhot_magma"));
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for (SuperRegistry b : modelList)
            b.registerBlock(event);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (SuperRegistry b : modelList)
            b.registerItem(event);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (SuperRegistry b : modelList)
            b.registerModel(event);
    }
}
