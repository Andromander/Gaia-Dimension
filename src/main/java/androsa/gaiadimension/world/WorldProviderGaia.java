package androsa.gaiadimension.world;

import androsa.gaiadimension.GDConfig;
import androsa.gaiadimension.GaiaDimension;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderGaia extends WorldProviderSurface {

    public WorldProviderGaia() {
        setDimension(GDConfig.dimension.dimensionID);
    }

    @Override
    public Vec3d getFogColor(float f, float f1) {
        float bright = MathHelper.cos(2.25F * 2.25F * 0.25F) * 2.0F + 0.5F;
        if (bright < 0.0F) {
            bright = 0.0F;
        }
        if (bright > 1.0F) {
            bright = 1.0F;
        }
        float red = 1.0F;
        float green = 0.85F;
        float blue = 0.75F;
        red *= bright * 0.94 + 0.06F;
        green *= bright * 0.91 + 0.09F;
        blue += bright * 0.94F + 0.06F;
        return new Vec3d(red, green, blue);
    }

    @Override
    public DimensionType getDimensionType() {
        return GaiaDimension.dimType;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new GaiaChunkGenerator(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }

    @Override
    public double getHorizon() {
        return GaiaWorld.SEALEVEL;
    }

    //Do we have a seed override?
    @Override
    public long getSeed() {
        if (GDConfig.dimension.gaiaSeed == null || GDConfig.dimension.gaiaSeed.length() == 0) {
            return super.getSeed();
        } else {
            return GDConfig.dimension.gaiaSeed.hashCode();
        }
    }

    //There's no weather in Gaia
    @Override
    @SideOnly(Side.CLIENT)
    public IRenderHandler getWeatherRenderer() {
        return null;
    }
}
