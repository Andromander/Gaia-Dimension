package androsa.gaiadimension.world.gen.carver;

import androsa.gaiadimension.block.AbstractGaiaGrassBlock;
import androsa.gaiadimension.block.GaiaSoilBlock;
import androsa.gaiadimension.registry.ModBlocks;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

import java.util.BitSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class ChasmsWorldCarver extends WorldCarver<ProbabilityConfig> {

    protected Set<Block> carvableBlocks = ImmutableSet.of(ModBlocks.glitter_grass, ModBlocks.corrupt_grass, ModBlocks.murky_grass, ModBlocks.soft_grass, ModBlocks.heavy_soil, ModBlocks.corrupt_soil, ModBlocks.boggy_soil, ModBlocks.light_soil, ModBlocks.saltstone, ModBlocks.gaia_stone, ModBlocks.wasteland_stone, ModBlocks.volcanic_rock, ModBlocks.primal_mass, ModBlocks.frail_glitter_block);

    public ChasmsWorldCarver(Function<Dynamic<?>, ? extends ProbabilityConfig> config, int height) {
        super(config, height);
    }

    @Override
    public boolean shouldCarve(Random rand, int chunkX, int chunkZ, ProbabilityConfig config) {
        return rand.nextFloat() <= config.probability;
    }

    public boolean carve(IChunk chunkIn, Random rand, int seaLevel, int chunkX, int chunkZ, int p_212867_6_, int p_212867_7_, BitSet carvingMask, ProbabilityConfig config) {
        int i = (this.func_222704_c() * 2 - 1) * 16;
        int j = rand.nextInt(rand.nextInt(rand.nextInt(this.func_222724_a()) + 1) + 1);

        for(int k = 0; k < j; ++k) {
            double d0 = (double)(chunkX * 16 + rand.nextInt(16));
            double d1 = (double)this.generateCaveStartY(rand);
            double d2 = (double)(chunkZ * 16 + rand.nextInt(16));
            int l = 1;
            if (rand.nextInt(4) == 0) {
                float f1 = 1.0F + rand.nextFloat() * 6.0F;
                this.func_222723_a(chunkIn, rand.nextLong(), seaLevel, p_212867_6_, p_212867_7_, d0, d1, d2, f1, 0.5D, carvingMask);
                l += rand.nextInt(4);
            }

            for(int k1 = 0; k1 < l; ++k1) {
                float f = rand.nextFloat() * ((float)Math.PI * 2F);
                float f3 = (rand.nextFloat() - 0.5F) / 4.0F;
                float f2 = this.generateCaveRadius(rand);
                int i1 = i - rand.nextInt(i / 4);
                this.carveTunnel(chunkIn, rand.nextLong(), seaLevel, p_212867_6_, p_212867_7_, d0, d1, d2, f2, f, f3, 0, i1, this.func_222725_b(), carvingMask);
            }
        }

        return true;
    }

    protected int func_222724_a() {
        return 15;
    }

    protected float generateCaveRadius(Random rand) {
        float f = rand.nextFloat() * 2.0F + rand.nextFloat();
        if (rand.nextInt(10) == 0) {
            f *= rand.nextFloat() * rand.nextFloat() * 3.0F + 1.0F;
        }

        return f;
    }

    protected double func_222725_b() {
        return 1.0D;
    }

    protected int generateCaveStartY(Random rand) {
        return rand.nextInt(rand.nextInt(26) + 8);
    }

    protected void func_222723_a(IChunk chunkIn, long seed, int seaLevel, int p_222723_5_, int p_222723_6_, double chunkX, double chunkY, double chunkZ, float radius, double half, BitSet mask) {
        double d0 = 1.5D + (double)(MathHelper.sin(((float)Math.PI / 2F)) * radius);
        double d1 = d0 * half;
        this.func_222705_a(chunkIn, seed, seaLevel, p_222723_5_, p_222723_6_, chunkX + 1.0D, chunkY, chunkZ, d0 * 4, d1 * 2, mask);
    }

    protected void carveTunnel(IChunk chunkIn, long seed, int seaLevel, int centerX, int centerZ, double chunkX, double chunkY, double chunkZ, float radius, float p_222727_14_, float p_222727_15_, int baseSize, int maxSize, double diameter, BitSet mask) {
        Random random = new Random(seed);
        int i = random.nextInt(maxSize / 2) + maxSize / 4;
        boolean flag = random.nextInt(6) == 0;
        float f = 0.0F;
        float f1 = 0.0F;

        for (int j = baseSize; j < maxSize; ++j) {
            double d0 = 1.5D + (double)(MathHelper.sin((float)Math.PI * (float)j / (float)maxSize) * radius);
            double d1 = d0 * diameter;
            float f2 = MathHelper.cos(p_222727_15_);
            chunkX += (double)(MathHelper.cos(p_222727_14_) * f2);
            chunkY += (double)MathHelper.sin(p_222727_15_);
            chunkZ += (double)(MathHelper.sin(p_222727_14_) * f2);
            p_222727_15_ = p_222727_15_ * (flag ? 0.92F : 0.7F);
            p_222727_15_ = p_222727_15_ + f1 * 0.1F;
            p_222727_14_ += f * 0.1F;
            f1 = f1 * 0.9F;
            f = f * 0.75F;
            f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (j == i && radius > 1.0F) {
                this.carveTunnel(chunkIn, random.nextLong(), seaLevel, centerX, centerZ, chunkX, chunkY, chunkZ, random.nextFloat() * 0.5F + 0.5F, p_222727_14_ - ((float)Math.PI / 2F), p_222727_15_ / 3.0F, j, maxSize, 1.0D, mask);
                this.carveTunnel(chunkIn, random.nextLong(), seaLevel, centerX, centerZ, chunkX, chunkY, chunkZ, random.nextFloat() * 0.5F + 0.5F, p_222727_14_ + ((float)Math.PI / 2F), p_222727_15_ / 3.0F, j, maxSize, 1.0D, mask);
                return;
            }

            if (random.nextInt(4) != 0) {
                if (!this.func_222702_a(centerX, centerZ, chunkX, chunkZ, j, maxSize, radius)) {
                    return;
                }

                this.func_222705_a(chunkIn, seed, seaLevel, centerX, centerZ, chunkX, chunkY, chunkZ, d0 * 4, d1 * 2, mask);
            }
        }
    }

    @Override
    protected boolean carveBlock(IChunk chunkIn, BitSet carvingMask, Random rand, BlockPos.MutableBlockPos mutablePos, BlockPos.MutableBlockPos mutablePosAbove, BlockPos.MutableBlockPos mutablePosBelow, int p_222703_7_, int p_222703_8_, int p_222703_9_, int posX, int posZ, int p_222703_12_, int posY, int p_222703_14_, AtomicBoolean flag) {
        int i = p_222703_12_ | p_222703_14_ << 4 | posY << 8;
        if (carvingMask.get(i)) {
            return false;
        } else {
            carvingMask.set(i);
            mutablePos.setPos(posX, posY, posZ);
            BlockState blockstate = chunkIn.getBlockState(mutablePos);
            BlockState blockstate1 = chunkIn.getBlockState(mutablePosAbove.setPos(mutablePos).move(Direction.UP));
            if (blockstate.getBlock() instanceof AbstractGaiaGrassBlock) {
                flag.set(true);
            }

            if (!this.canCarveBlock(blockstate, blockstate1)) {
                return false;
            } else {
                if (posY < 11) {
                    chunkIn.setBlockState(mutablePos, ModBlocks.superhot_magma.getDefaultState(), false);
                } else {
                    chunkIn.setBlockState(mutablePos, CAVE_AIR, false);
                    if (flag.get()) {
                        mutablePosBelow.setPos(mutablePos).move(Direction.DOWN);
                        if (chunkIn.getBlockState(mutablePosBelow).getBlock() instanceof GaiaSoilBlock) {
                            chunkIn.setBlockState(mutablePosBelow, chunkIn.getBiome(mutablePos).getSurfaceBuilderConfig().getTop(), false);
                        }
                    }
                }

                return true;
            }
        }
    }

    @Override
    protected boolean func_222706_a(BlockState state) {
        return this.carvableBlocks.contains(state.getBlock());
    }

    protected boolean func_222708_a(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
        return p_222708_3_ <= -0.7D || p_222708_1_ * p_222708_1_ + p_222708_3_ * p_222708_3_ + p_222708_5_ * p_222708_5_ >= 1.0D;
    }
}
