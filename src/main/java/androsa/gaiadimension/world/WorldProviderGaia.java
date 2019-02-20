package androsa.gaiadimension.world;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.biomes.*;
import androsa.gaiadimension.registry.EnumSkyColors;
import androsa.gaiadimension.registry.GDBiomes;
import androsa.gaiadimension.registry.GDConfig;
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

    @Override
    @SideOnly(Side.CLIENT)
    public float getStarBrightness(float par1) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));

        if ((biome instanceof GDPurpleAgateSwamp) || (!GDConfig.dimension.enableSkyFog && GDConfig.dimension.skyColors == EnumSkyColors.PURPLE_AGATE)) {
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
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return true;
    }

    @Override
    public double getHorizon() {
        return GaiaWorld.SEALEVEL;
    }

    @Override
    public int getAverageGroundLevel() {
        return 64;
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

    //Sky colour render
    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getSkyColor(net.minecraft.entity.Entity cameraEntity, float partialTicks) {

        EntityPlayer player = Minecraft.getMinecraft().player;
        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));
        targetSkyColor = new short[]{ 198, 157, 88 };

        if (GDConfig.dimension.enableSkyFog) {
            if (biome instanceof GDBiomeBase) {
                targetSkyColor = ((GDBiomeBase) biome).getSkyRGB();
            }
        } else {
            switch (GDConfig.dimension.skyColors) {
                case BLUE_AGATE:
                    targetSkyColor = new short[] { 149, 197, 231 };
                    break;
                case GREEN_AGATE:
                    targetSkyColor = new short[] { 128, 191, 158 };
                    break;
                case PURPLE_AGATE:
                    targetSkyColor = new short[] { 171, 109, 241 };
                    break;
                case MUTANT_AGATE:
                    targetSkyColor = new short[] { 241, 154, 193 };
                    break;
                case SALT_DUNES:
                    targetSkyColor = new short[] { 230, 193, 110 };
                    break;
                case STATIC_WASTELAND:
                    targetSkyColor = new short[]{ 40, 47, 82 };
                    break;
                case VOLCANICLAND:
                    targetSkyColor = new short[] { 75, 30, 25 };
                    break;
                case GOLDSTONE:
                    targetSkyColor = new short[] { 34, 34, 34 };
                    break;
                case GENERAL:
                default:
                    targetSkyColor = new short[]{ 198, 157, 88 };
                    break;
            }
        }

        if (currentSkyColor == null) {
            currentSkyColor = new double[3];
            for (int a = 0; a < 3; a++)
                currentSkyColor[a] = targetSkyColor[a];
        }

        for (int a = 0; a < 3; a++)
            if (currentSkyColor[a] != targetSkyColor[a])
                if (currentSkyColor[a] < targetSkyColor[a]) {
                    currentSkyColor[a] += 2D;
                    if (currentSkyColor[a] > targetSkyColor[a])
                        currentSkyColor[a] = targetSkyColor[a];
                } else if (currentSkyColor[a] > targetSkyColor[a]) {
                    currentSkyColor[a] -= 2D;
                    if (currentSkyColor[a] < targetSkyColor[a])
                        currentSkyColor[a] = targetSkyColor[a];
                }

        return new Vec3d(currentSkyColor[0] / 255D, currentSkyColor[1] / 255D, currentSkyColor[2] / 255D);
    }

    @SideOnly(Side.CLIENT)
    private double[] currentFogColor;
    @SideOnly(Side.CLIENT)
    private short[] targetFogColor;

    //Fog colour render
    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getFogColor(float f, float f1) {

        EntityPlayer player = Minecraft.getMinecraft().player;
        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));
        targetFogColor = new short[] { 234, 178, 224 };

        if (GDConfig.dimension.enableSkyFog) {
            if (biome instanceof GDVolcanicLands) {
                targetFogColor = new short[]{ 245, 119, 112 };
            } else if (biome instanceof GDGoldstoneLands) {
                targetFogColor = new short[]{ 188, 122, 44 };
            } else if (biome instanceof GDStaticWasteland) {
                targetFogColor = new short[]{ 86, 213, 170 };
            } else if (biome instanceof GDSaltDunes) {
                targetFogColor = new short[]{ 187, 211, 255 };
            }
        } else {
            switch (GDConfig.dimension.skyColors) {
                case SALT_DUNES:
                    targetFogColor = new short[]{ 187, 211, 255 };
                    break;
                case STATIC_WASTELAND:
                    targetFogColor = new short[]{ 86, 213, 170 };
                    break;
                case VOLCANICLAND:
                    targetFogColor = new short[]{ 245, 119, 112 };
                    break;
                case GOLDSTONE:
                    targetFogColor = new short[]{ 188, 122, 44 };
                    break;
                case GENERAL:
                case BLUE_AGATE:
                case GREEN_AGATE:
                case PURPLE_AGATE:
                case MUTANT_AGATE:
                default:
                    targetFogColor = new short[] { 234, 178, 224 };
                    break;
            }
        }

        if (currentFogColor == null) {
            currentFogColor = new double[3];
            for (int a = 0; a < 3; a++)
                currentFogColor[a] = targetFogColor[a];
        }

        for (int a = 0; a < 3; a++)
            if (currentFogColor[a] != targetFogColor[a])
                if (currentFogColor[a] < targetFogColor[a]) {
                    currentFogColor[a] += 2D;
                    if (currentFogColor[a] > targetFogColor[a])
                        currentFogColor[a] = targetFogColor[a];
                } else if (currentFogColor[a] > targetFogColor[a]) {
                    currentFogColor[a] -= 2D;
                    if (currentFogColor[a] < targetFogColor[a])
                        currentFogColor[a] = targetFogColor[a];
                }

        return new Vec3d(currentFogColor[0] / 255D, currentFogColor[1] / 255D, currentFogColor[2] / 255D);
    }

    @SideOnly(Side.CLIENT)
    private double[] currentCloudColor;
    @SideOnly(Side.CLIENT)
    private short[] targetCloudColor;

    //Cloud colour render
    @Override
    @SideOnly(Side.CLIENT)
    public Vec3d getCloudColor(float partialTicks) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));
        targetCloudColor = new short[] { 234, 178, 224 };

        if (GDConfig.dimension.enableSkyFog) {
            if (biome instanceof GDVolcanicLands) {
                targetCloudColor = new short[]{ 245, 119, 112 };
            } else if (biome instanceof GDGoldstoneLands) {
                targetCloudColor = new short[]{ 188, 122, 44 };
            } else if (biome instanceof GDStaticWasteland) {
                targetCloudColor = new short[]{ 86, 213, 170 };
            } else if (biome instanceof GDSaltDunes) {
                targetCloudColor = new short[]{ 187, 211, 255 };
            }
        } else {
            switch (GDConfig.dimension.skyColors) {
                case SALT_DUNES:
                    targetFogColor = new short[]{ 187, 211, 255 };
                    break;
                case STATIC_WASTELAND:
                    targetFogColor = new short[]{ 86, 213, 170 };
                    break;
                case VOLCANICLAND:
                    targetFogColor = new short[]{ 245, 119, 112 };
                    break;
                case GOLDSTONE:
                    targetFogColor = new short[]{ 188, 122, 44 };
                    break;
                case GENERAL:
                case BLUE_AGATE:
                case GREEN_AGATE:
                case PURPLE_AGATE:
                case MUTANT_AGATE:
                default:
                    targetFogColor = new short[] { 234, 178, 224 };
                    break;
            }
        }

        if (currentCloudColor == null) {
            currentCloudColor = new double[3];
            for (int a = 0; a < 3; a++)
                currentCloudColor[a] = targetCloudColor[a];
        }

        for (int a = 0; a < 3; a++)
            if (currentCloudColor[a] != targetCloudColor[a])
                if (currentCloudColor[a] < targetCloudColor[a]) {
                    currentCloudColor[a] += 2D;
                    if (currentCloudColor[a] > targetCloudColor[a])
                        currentCloudColor[a] = targetCloudColor[a];
                } else if (currentCloudColor[a] > targetCloudColor[a]) {
                    currentCloudColor[a] -= 2D;
                    if (currentCloudColor[a] < targetCloudColor[a])
                        currentCloudColor[a] = targetCloudColor[a];
                }

        return new Vec3d(currentFogColor[0] / 255D, currentFogColor[1] / 255D, currentFogColor[2] / 255D);
    }

    //I mean, it will never be null, but if it should we have this
    @Override
    public Biome getBiomeForCoords(BlockPos pos) {
        Biome biome = super.getBiomeForCoords(pos);
        if (biome == null) {
            biome = GDBiomes.pink_agate_forest;
        }
        return biome;
    }

    //Can't really null this, so we have clouds, but *way* up there
    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 255.0F;
    }

    //Disable weather in Gaia
    @Override
    public boolean canDoRainSnowIce(@Nullable Chunk chunk) {
        return false;
    }
}
