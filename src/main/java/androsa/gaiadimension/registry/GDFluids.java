package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class GDFluids {

    public static final Fluid mineralWater =  registerFluid("mineral_water", "mineralwater")
            .setViscosity(750).setColor(0xCEB0C0FF);
    public static final Fluid superhotMagma = registerFluid("superhot_magma", "superhotmagma")
            .setLuminosity(15).setDensity(4000).setViscosity(4000).setTemperature(1000).setColor(0xFF00FFFF);
    public static final Fluid sweetMuck =     registerFluid("sweet_muck", "sweetmuck")
            .setDensity(1000).setViscosity(750).setColor(0xFF800080);
    public static final Fluid liquidBismuth = registerFluid("liquid_bismuth", "liquidbismuth")
            .setDensity(2500).setViscosity(3500).setTemperature(300);

    private static Fluid registerFluid(String name, String dir) {
        return new Fluid(name, fluidPath(name, dir, "_still"), fluidPath(name, dir, "_flow"));
    }

    private static ResourceLocation fluidPath(String name, String dir, String type) {
        return new ResourceLocation(GaiaDimension.MODID, String.format("fluids/%s/%s%s", dir, name, type));
    }
}