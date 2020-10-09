package androsa.gaiadimension.world.gen.carver;

import androsa.gaiadimension.block.AbstractGaiaGrassBlock;
import androsa.gaiadimension.block.GaiaSoilBlock;
import androsa.gaiadimension.registry.ModBlocks;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class ChasmsWorldCarver<T extends ProbabilityConfig> extends WorldCarver<T> {

    public ChasmsWorldCarver(Codec<T> config, int height) {
        super(config, height);
        carvableBlocks = ImmutableSet.of(ModBlocks.glitter_grass.get(), ModBlocks.corrupt_grass.get(), ModBlocks.murky_grass.get(), ModBlocks.soft_grass.get(), ModBlocks.heavy_soil.get(), ModBlocks.corrupt_soil.get(), ModBlocks.boggy_soil.get(), ModBlocks.light_soil.get(), ModBlocks.saltstone.get(), ModBlocks.gaia_stone.get(), ModBlocks.wasteland_stone.get(), ModBlocks.volcanic_rock.get(), ModBlocks.primal_mass.get());
    }

    @Override
    public boolean shouldCarve(Random rand, int chunkX, int chunkZ, T config) {
        return rand.nextFloat() <= config.probability;
    }

    @Override
    public boolean carveRegion(IChunk chunkIn, Function<BlockPos, Biome> biomePos, Random rand, int seaLevel, int posX, int posZ, int chunkX, int chunkZ, BitSet carvingMask, T config) {
        int i = (this.func_222704_c() * 2 - 1) * 16;
        int j = rand.nextInt(rand.nextInt(rand.nextInt(this.getChunkSize()) + 1) + 1);

        for(int k = 0; k < j; ++k) {
            double d0 = (double)(posX * 16 + rand.nextInt(16));
            double d1 = (double)this.generateCaveStartY(rand);
            double d2 = (double)(posZ * 16 + rand.nextInt(16));
            int l = 1;
            if (rand.nextInt(4) == 0) {
                float f1 = 1.0F + rand.nextFloat() * 6.0F;
                this.carveCave(chunkIn, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, d0, d1, d2, f1, 0.5D, carvingMask);
                l += rand.nextInt(4);
            }

            for(int k1 = 0; k1 < l; ++k1) {
                float f = rand.nextFloat() * ((float)Math.PI * 2F);
                float f3 = (rand.nextFloat() - 0.5F) / 4.0F;
                float f2 = this.generateCaveRadius(rand);
                int i1 = i - rand.nextInt(i / 4);
                this.carveTunnels(chunkIn, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, d0, d1, d2, f2, f, f3, 0, i1, this.getDiameter(), carvingMask);
            }
        }

        return true;
    }

    protected int getChunkSize() {
        return 15;
    }

    protected float generateCaveRadius(Random rand) {
        float f = rand.nextFloat() * 2.0F + rand.nextFloat();
        if (rand.nextInt(10) == 0) {
            f *= rand.nextFloat() * rand.nextFloat() * 3.0F + 1.0F;
        }

        return f;
    }

    protected double getDiameter() {
        return 1.0D;
    }

    protected int generateCaveStartY(Random rand) {
        return rand.nextInt(rand.nextInt(26) + 8);
    }

    protected void carveCave(IChunk chunkIn, Function<BlockPos, Biome> biomePos, long seed, int seaLevel, int chunkX, int chunkZ, double x, double y, double z, float radius, double half, BitSet mask) {
        double d0 = 1.5D + (double)(MathHelper.sin(((float)Math.PI / 2F)) * radius);
        double d1 = d0 * half;
        this.func_227208_a_(chunkIn, biomePos, seed, seaLevel, chunkX, chunkZ, x + 1.0D, y, z, d0 * 4, d1 * 2, mask);
    }

    protected void carveTunnels(IChunk chunkIn, Function<BlockPos, Biome> biomePos, long seed, int seaLevel, int centerX, int centerZ, double x, double y, double z, float radius, float yaw, float pitch, int baseSize, int maxSize, double diameter, BitSet mask) {
        Random random = new Random(seed);
        int i = random.nextInt(maxSize / 2) + maxSize / 4;
        boolean flag = random.nextInt(6) == 0;
        float f = 0.0F;
        float f1 = 0.0F;

        for (int j = baseSize; j < maxSize; ++j) {
            double d0 = 1.5D + (double)(MathHelper.sin((float)Math.PI * (float)j / (float)maxSize) * radius);
            double d1 = d0 * diameter;
            float f2 = MathHelper.cos(pitch);
            x += (double)(MathHelper.cos(yaw) * f2);
            y += (double)MathHelper.sin(pitch);
            z += (double)(MathHelper.sin(yaw) * f2);
            pitch = pitch * (flag ? 0.92F : 0.7F);
            pitch = pitch + f1 * 0.1F;
            yaw += f * 0.1F;
            f1 = f1 * 0.9F;
            f = f * 0.75F;
            f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (j == i && radius > 1.0F) {
                this.carveTunnels(chunkIn, biomePos, random.nextLong(), seaLevel, centerX, centerZ, x, y, z, random.nextFloat() * 0.5F + 0.5F, yaw - ((float)Math.PI / 2F), pitch / 3.0F, j, maxSize, 1.0D, mask);
                this.carveTunnels(chunkIn, biomePos, random.nextLong(), seaLevel, centerX, centerZ, x, y, z, random.nextFloat() * 0.5F + 0.5F, yaw + ((float)Math.PI / 2F), pitch / 3.0F, j, maxSize, 1.0D, mask);
                return;
            }

            if (random.nextInt(4) != 0) {
                if (!this.func_222702_a(centerX, centerZ, x, z, j, maxSize, radius)) {
                    return;
                }

                this.func_227208_a_(chunkIn, biomePos, seed, seaLevel, centerX, centerZ, x, y, z, d0 * 4, d1 * 2, mask);
            }
        }
    }

    @Override
    protected boolean func_230358_a_(IChunk chunkIn, Function<BlockPos, Biome> biomePos, BitSet carvingMask, Random rand, BlockPos.Mutable mutablePos, BlockPos.Mutable mutablePosAbove, BlockPos.Mutable mutablePosBelow, int seaLevel, int chunkX, int chunkZ, int posX, int posZ, int xVal, int yVal, int zVal, MutableBoolean flag) {
        int i = xVal | zVal << 4 | yVal << 8;
        if (carvingMask.get(i)) {
            return false;
        } else {
            carvingMask.set(i);
            mutablePos.setPos(posX, yVal, posZ);
            BlockState blockstate = chunkIn.getBlockState(mutablePos);
            BlockState blockstate1 = chunkIn.getBlockState(mutablePosAbove.setPos(mutablePos).move(Direction.UP));
            if (blockstate.getBlock() instanceof AbstractGaiaGrassBlock) {
                flag.setTrue();
            }

            if (!this.canCarveBlock(blockstate, blockstate1)) {
                return false;
            } else {
                if (yVal < 11) {
                    chunkIn.setBlockState(mutablePos, ModBlocks.superhot_magma.get().getDefaultState(), false);
                } else {
                    chunkIn.setBlockState(mutablePos, CAVE_AIR, false);
                    if (flag.isTrue()) {
                        mutablePosBelow.setPos(mutablePos).move(Direction.DOWN);
                        if (chunkIn.getBlockState(mutablePosBelow).getBlock() instanceof GaiaSoilBlock) {
                            chunkIn.setBlockState(mutablePosBelow, biomePos.apply(mutablePos).getGenerationSettings().getSurfaceBuilderConfig().getTop(), false);
                        }
                    }
                }

                return true;
            }
        }
    }

    @Override
    protected boolean func_222708_a(double xPos, double yPos, double zPos, int yVal) {
        return yPos <= -0.7D || xPos * xPos + yPos * yPos + zPos * zPos >= 1.0D;
    }
}
