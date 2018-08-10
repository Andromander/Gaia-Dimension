package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GDFluids {

    public static final Fluid mineralWater = new Fluid("mineral_water", new ResourceLocation(GaiaDimension.MODID, "fluids/mineralwater/mineral_water_still"),
            new ResourceLocation(GaiaDimension.MODID, "fluids/mineralwater/mineral_water_flow"))
            .setDensity(1000).setViscosity(750).setColor(0xCEB0C0FF);
    public static final Fluid superhotMagma = new Fluid("superhot_magma", new ResourceLocation(GaiaDimension.MODID, "fluids/superhotmagma/superhot_magma_still"),
            new ResourceLocation(GaiaDimension.MODID, "fluids/superhotmagma/superhot_magma_flow"))
            .setLuminosity(15).setDensity(4000).setViscosity(4000).setTemperature(1000).setColor(0xFF00FFFF);
    public static final Fluid sweetMuck = new Fluid("sweet_muck", new ResourceLocation(GaiaDimension.MODID, "fluids/sweetmuck/sweet_muck_still"),
            new ResourceLocation(GaiaDimension.MODID, "fluids/sweetmuck/sweet_muck_flow"))
            .setDensity(1000).setViscosity(750).setColor(0xFF800080);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        GDItems.mineral_water_bucket_item = FluidUtil.getFilledBucket(new FluidStack(GDFluids.mineralWater, Fluid.BUCKET_VOLUME));
        GDItems.superhot_magma_bucket_item = FluidUtil.getFilledBucket(new FluidStack(GDFluids.superhotMagma, Fluid.BUCKET_VOLUME));
        GDItems.sweet_muck_bucket_item = FluidUtil.getFilledBucket(new FluidStack(GDFluids.sweetMuck, Fluid.BUCKET_VOLUME));
    }
}
