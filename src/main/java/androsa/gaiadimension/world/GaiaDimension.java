package androsa.gaiadimension.world;

import androsa.gaiadimension.biomes.BaseGaiaBiome;
import androsa.gaiadimension.registry.GaiaSkyColors;
import androsa.gaiadimension.registry.ModBiomes;
import androsa.gaiadimension.registry.ModDimensions;
import androsa.gaiadimension.registry.ModGaiaConfig;
import androsa.gaiadimension.world.layer.GaiaBiomeProvider;
import androsa.gaiadimension.world.layer.GaiaBiomeProviderSettings;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProviderType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class GaiaDimension extends Dimension {

    public GaiaDimension(World worldIn, DimensionType typeIn) {
        super(worldIn, typeIn, 0.0F);
    }

    @Override
    public ChunkGenerator<?> createChunkGenerator() {
        ChunkGeneratorType<GaiaGenerationSettings, GaiaChunkGenerator> chunkGen = ModDimensions.GAIA_GEN.get();
        GaiaGenerationSettings gaisSettings = chunkGen.createSettings();
        BiomeProviderType<GaiaBiomeProviderSettings, GaiaBiomeProvider> biomeProvider = ModDimensions.GAIA_DIMENSION.get();
        return chunkGen.create(this.world, biomeProvider.create(biomeProvider.getConfig(this.world.getWorldInfo())), gaisSettings);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    @Nullable
    public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
        for(int i = chunkPosIn.getXStart(); i <= chunkPosIn.getXEnd(); ++i) {
            for(int j = chunkPosIn.getZStart(); j <= chunkPosIn.getZEnd(); ++j) {
                BlockPos blockpos = this.findSpawn(i, j, checkValid);
                if (blockpos != null) {
                    return blockpos;
                }
            }
        }

        return null;
    }

    @Override
    @Nullable
    public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
        BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable(posX, 0, posZ);
        Biome biome = this.world.getBiome(blockpos$mutableblockpos);
        BlockState blockstate = biome.getSurfaceBuilderConfig().getTop();
        if (checkValid && !blockstate.getBlock().isIn(BlockTags.VALID_SPAWN)) {
            return null;
        } else {
            Chunk chunk = this.world.getChunk(posX >> 4, posZ >> 4);
            int i = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, posX & 15, posZ & 15);
            if (i < 0) {
                return null;
            } else if (chunk.getTopBlockY(Heightmap.Type.WORLD_SURFACE, posX & 15, posZ & 15) > chunk.getTopBlockY(Heightmap.Type.OCEAN_FLOOR, posX & 15, posZ & 15)) {
                return null;
            } else {
                for(int j = i + 1; j >= 0; --j) {
                    blockpos$mutableblockpos.setPos(posX, j, posZ);
                    BlockState blockstate1 = this.world.getBlockState(blockpos$mutableblockpos);
                    if (!blockstate1.getFluidState().isEmpty()) {
                        break;
                    }

                    if (blockstate1.equals(blockstate)) {
                        return blockpos$mutableblockpos.up().toImmutable();
                    }
                }

                return null;
            }
        }
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    //TODO: Will have to wait until I can render my own sky
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public float getStarBrightness(float par1) {
//        PlayerEntity player = Minecraft.getInstance().player;
//        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));
//
//        if (biome instanceof BaseGaiaBiome) {
//            if (ModGaiaConfig.skyColors.get() == GaiaSkyColors.PURPLE_AGATE || ((BaseGaiaBiome)biome).skyColor == GaiaSkyColors.PURPLE_AGATE) {
//                return 0.5F;
//            }
//        }
//        return world.getStarBrightnessBody(par1);
//    }

    @Override
    public float calculateCelestialAngle(long par1, float par3) {
        return 1.0F;
    }

    @Override
    public int getSeaLevel() {
        return GaiaGenerationSettings.SEALEVEL;
    }

    @Override
    public boolean canRespawnHere() {
        return world.getWorldInfo().isInitialized();
    }

    @Override
    public boolean shouldMapSpin(String entity, double x, double z, double rotation) {
        return false;
    }

    @Override
    public boolean isDaytime() {
        return true;
    }

    //TODO: Keep this for reference later when I can render my own sky
//    @OnlyIn(Dist.CLIENT)
//    private double[] currentSkyColor;
//    @OnlyIn(Dist.CLIENT)
//    private short[] targetSkyColor;
//
//    //Sky colour render
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public Vec3d getSkyColor(BlockPos cameraEntity, float partialTicks) {
//        PlayerEntity player = Minecraft.getInstance().player;
//        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));
//        targetSkyColor = ModGaiaConfig.skyColors.get().getSkyColor();
//
//        if (ModGaiaConfig.enableSkyFog.get())
//            if (biome instanceof BaseGaiaBiome)
//                targetSkyColor = ((BaseGaiaBiome) biome).getSkyRGB();
//        else
//            targetSkyColor = ModGaiaConfig.skyColors.get().getSkyColor();
//
//        if (currentSkyColor == null) {
//            currentSkyColor = new double[3];
//            for (int a = 0; a < 3; a++)
//                currentSkyColor[a] = targetSkyColor[a];
//        }
//
//        for (int a = 0; a < 3; a++)
//            if (currentSkyColor[a] != targetSkyColor[a])
//                if (currentSkyColor[a] < targetSkyColor[a]) {
//                    currentSkyColor[a] += 2D;
//                    if (currentSkyColor[a] > targetSkyColor[a])
//                        currentSkyColor[a] = targetSkyColor[a];
//                } else if (currentSkyColor[a] > targetSkyColor[a]) {
//                    currentSkyColor[a] -= 2D;
//                    if (currentSkyColor[a] < targetSkyColor[a])
//                        currentSkyColor[a] = targetSkyColor[a];
//                }
//
//        return new Vec3d(currentSkyColor[0] / 255D, currentSkyColor[1] / 255D, currentSkyColor[2] / 255D);
//    }

    @OnlyIn(Dist.CLIENT)
    private double[] currentFogColor;
    @OnlyIn(Dist.CLIENT)
    private short[] targetFogColor;

    //Fog colour render
    @Override
    @OnlyIn(Dist.CLIENT)
    public Vec3d getFogColor(float f, float f1) {
        PlayerEntity player = Minecraft.getInstance().player;
        Biome biome = world.getBiome(new BlockPos(player.getX(), player.getY(), player.getZ()));
        targetFogColor = ModGaiaConfig.skyColors.get().getFogColor();

        if (ModGaiaConfig.enableSkyFog.get())
            if (biome instanceof BaseGaiaBiome)
                targetFogColor = ((BaseGaiaBiome) biome).getFogRGB();
        else
            targetFogColor = ModGaiaConfig.skyColors.get().getFogColor();

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

    //TODO: Will need to move into Cloud rendering
//    @OnlyIn(Dist.CLIENT)
//    private double[] currentCloudColor;
//    @OnlyIn(Dist.CLIENT)
//    private short[] targetCloudColor;
//
//    //Cloud colour render
//    @Override
//    @OnlyIn(Dist.CLIENT)
//    public Vec3d getCloudColor(float partialTicks) {
//        PlayerEntity player = Minecraft.getInstance().player;
//        Biome biome = world.getBiome(new BlockPos(player.posX, player.posY, player.posZ));
//        targetCloudColor = new short[] { 234, 178, 224 };
//
//        if (ModGaiaConfig.enableSkyFog.get())
//            if (biome instanceof BaseGaiaBiome)
//                targetCloudColor = ((BaseGaiaBiome) biome).getFogRGB();
//        else
//            targetCloudColor = ModGaiaConfig.skyColors.get().getFogColor();
//
//        if (currentCloudColor == null) {
//            currentCloudColor = new double[3];
//            for (int a = 0; a < 3; a++)
//                currentCloudColor[a] = targetCloudColor[a];
//        }
//
//        for (int a = 0; a < 3; a++)
//            if (currentCloudColor[a] != targetCloudColor[a])
//                if (currentCloudColor[a] < targetCloudColor[a]) {
//                    currentCloudColor[a] += 2D;
//                    if (currentCloudColor[a] > targetCloudColor[a])
//                        currentCloudColor[a] = targetCloudColor[a];
//                } else if (currentCloudColor[a] > targetCloudColor[a]) {
//                    currentCloudColor[a] -= 2D;
//                    if (currentCloudColor[a] < targetCloudColor[a])
//                        currentCloudColor[a] = targetCloudColor[a];
//                }
//
//        return new Vec3d(currentFogColor[0] / 255D, currentFogColor[1] / 255D, currentFogColor[2] / 255D);
//    }

    //TODO: See if we really need this
//    @Override
//    public Biome getBiome(BlockPos pos) {
//        Biome biome = super.getBiome(pos);
//        if (biome == null) {
//            biome = ModBiomes.pink_agate_forest.get();
//        }
//        return biome;
//    }

    //Clouds are *way* up there
    @Override
    @OnlyIn(Dist.CLIENT)
    public float getCloudHeight() {
        return 255.0F;
    }

    //Disable weather in Gaia
    @Override
    public boolean canDoRainSnowIce(@Nullable Chunk chunk) {
        return false;
    }

    //TODO: How might this be handled with custom weather? For now: prevents changing weather
    @Override
    public void updateWeather(Runnable defaultLogic) { }
}
