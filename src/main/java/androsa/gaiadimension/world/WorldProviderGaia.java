package androsa.gaiadimension.world;

import androsa.gaiadimension.GDConfig;
import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.biomes.*;
import androsa.gaiadimension.world.layer.GDBiomeProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class WorldProviderGaia extends WorldProviderSurface {

    public WorldProviderGaia() {
        setDimension(GDConfig.dimension.dimensionID);
    }

    @SideOnly(Side.CLIENT)
    private double[] currentFogColor;
    @SideOnly(Side.CLIENT)
    private short[] targetFogColor;

    @Override
    public Vec3d getFogColor(float f, float f1) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));
        targetFogColor = new short[] { 234, 178, 224 };

        if (biome instanceof GDVolcanicLands) {
            targetFogColor = new short[]{ 245, 119, 112 };
        } else if (biome instanceof GDGoldstoneLands) {
            targetFogColor = new short[]{ 188, 122, 44 };
        }

        return new Vec3d(targetFogColor[0] / 255D, targetFogColor[1] / 255D, targetFogColor[2] / 255D);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));

        if (biome instanceof GDPurpleAgateSwamp) {
            return 0.5F;
        }
        return world.getStarBrightnessBody(par1);
    }

    @Override
    public float calculateCelestialAngle(long par1, float par3) {
        return 1.0F;
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
        //return new Vec3d(198/255, 157/255, 88/255);

        EntityPlayer player = Minecraft.getMinecraft().player;
        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));
        targetSkyColor = new short[]{ 198, 157, 88 };

        if (biome instanceof GDBlueAgateTaiga) {
            targetSkyColor = ((GDBlueAgateTaiga) biome).getSkyRGB();
        } else if (biome instanceof GDGreenAgateJungle) {
            targetSkyColor = ((GDGreenAgateJungle) biome).getSkyRGB();
        } else if (biome instanceof GDPurpleAgateSwamp) {
            targetSkyColor = ((GDPurpleAgateSwamp) biome).getSkyRGB();
        } else if (biome instanceof GDVolcanicLands) {
            targetSkyColor = ((GDVolcanicLands) biome).getSkyRGB();
        } else if (biome instanceof GDGoldstoneLands) {
            targetSkyColor = ((GDGoldstoneLands) biome).getSkyRGB();
        }

//Will find a way to get this to work properly
/*
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
*/
        return new Vec3d(targetSkyColor[0] / 255D, targetSkyColor[1] / 255D, targetSkyColor[2] / 255D);
    }

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

    //Disable weather in Gaia
    @Override
    public boolean canDoRainSnowIce(@Nullable Chunk chunk) {
        return false;
    }
}
