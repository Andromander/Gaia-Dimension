package androsa.gaiadimension.world;

import androsa.gaiadimension.GDConfig;
import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.biomes.GDBiomes;
import androsa.gaiadimension.biomes.GDBlueAgateTaiga;
import androsa.gaiadimension.world.layer.GDBiomeProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.swing.text.html.parser.Entity;

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
        float blue = 0.25F;
        red *= bright * 0.94F + 0.06F;
        green *= bright * 0.91F + 0.09F;
        blue += bright * 0.7F + 0.06F;
        return new Vec3d(red, green, blue);
    }

    @Override
    public float calculateCelestialAngle(long par1, float par3) {
        return 1.0f;
    }

    @Override
    public void init() {
        super.init();
        this.biomeProvider = new GDBiomeProvider(world);
    }

    @Override
    public DimensionType getDimensionType() {
        return GaiaDimension.dimType;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new GaiaChunkGenerator(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }

    //Let's see what can be done here...
    @Override
    public boolean isSkyColored() {
        return true;
    }

    @Override
    public double getHorizon() {
        return GaiaWorld.SEALEVEL;
    }

    @Override
    public int getAverageGroundLevel() {
        return 63;
    }

    @Override
    public boolean canRespawnHere() {
        return world.getWorldInfo().isInitialized();
    }

    @Override
    public boolean isDaytime() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    private double[] currentSkyColor;
    @SideOnly(Side.CLIENT)
    private short[] targetSkyColor;

    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks) {
        return new Vec3d(198 / 256.0, 157 / 256.0, 88 / 256.0);
    }
/* //If I can get this to work, I'll implement it
        EntityPlayer player = Minecraft.getMinecraft().player;
        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));

        if (biome instanceof GDBlueAgateTaiga) {
            targetSkyColor = ((GDBlueAgateTaiga) biome).getSkyRGB();
        } else {
            return new Vec3d(198 / 256.0, 157 / 256.0, 88 / 256.0);
        }

        if (currentSkyColor == null) {
            currentSkyColor = new double[3];
            for (int time = 0; time < 3; time++) {
                currentSkyColor[time] = targetSkyColor[time];
            }
        }

        for (int time = 0; time < 3; time++) {
            if (currentSkyColor[time] != targetSkyColor[time]) {
                if (currentSkyColor[time] < targetSkyColor[time]) {
                    currentSkyColor[time] += 2D;
                    if (currentSkyColor[time] > targetSkyColor[time]) {
                        currentSkyColor[time] = targetSkyColor[time];
                    }
                }
            } else if (currentSkyColor[time] > targetSkyColor[time]) {
                currentSkyColor[time] -= 2D;
                if (currentSkyColor[time] < targetSkyColor[time]) {
                    targetSkyColor[time] = targetSkyColor[time];
                }
            }
        }

        return new Vec3d(currentSkyColor[0] / 255D, currentSkyColor[1] / 255D, currentSkyColor[2] / 255D);
    }
*/
    //I mean, it will never be null, but if it should we have this
    @Override
    public Biome getBiomeForCoords(BlockPos pos) {
        Biome biome = super.getBiomeForCoords(pos);
        if (biome == null) {
            biome = GDBiomes.pinkAgateForest;
        }
        return biome;
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

    //Can't really null this, so we have clouds, but *way* up there
    @Override
    public float getCloudHeight() {
        return 255.0F;
    }
}
