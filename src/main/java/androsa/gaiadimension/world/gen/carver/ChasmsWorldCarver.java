package androsa.gaiadimension.world.gen.carver;

import androsa.gaiadimension.block.AbstractGaiaGrassBlock;
import androsa.gaiadimension.block.GaiaSoilBlock;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModFluids;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.material.FluidState;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.Random;
import java.util.function.Function;

public class ChasmsWorldCarver<T extends CaveCarverConfiguration> extends WorldCarver<T> {

    public ChasmsWorldCarver(Codec<T> config) {
        super(config);
    }

    @Override
    public boolean isStartChunk(T config, RandomSource rand) {
        return rand.nextFloat() <= config.probability;
    }

    @Override
    public boolean carve(CarvingContext context, T config, ChunkAccess chunkIn, Function<BlockPos, Holder<Biome>> biomePos, RandomSource rand, Aquifer aquifer, ChunkPos chunkpos, CarvingMask carvingMask) {
        int i = SectionPos.sectionToBlockCoord(this.getRange() * 2 - 1);
        int j = rand.nextInt(rand.nextInt(rand.nextInt(this.getCaveBound()) + 1) + 1);

        for(int k = 0; k < j; ++k) {
            double bX = chunkpos.getBlockX(rand.nextInt(16));
            double bY = config.y.sample(rand, context);
            double bZ = chunkpos.getBlockZ(rand.nextInt(16));
            double hR = config.horizontalRadiusMultiplier.sample(rand);
            double vR = config.verticalRadiusMultiplier.sample(rand);
            double floor = config.floorLevel.sample(rand);
            WorldCarver.CarveSkipChecker skipchecker = (cxt, x, y, z, noise) -> skip(x, y, z, floor);
            int l = 1;
            if (rand.nextInt(4) == 0) {
                double sY = config.yScale.sample(rand);
                float f1 = 1.0F + rand.nextFloat() * 6.0F;
                //TODO: Diameter: 0.5D
                this.genRoom(context, config, chunkIn, biomePos, aquifer, bX, bY, bZ, f1, sY, carvingMask, skipchecker);
                l += rand.nextInt(4);
            }

            for(int k1 = 0; k1 < l; ++k1) {
                float f = rand.nextFloat() * ((float)Math.PI * 2F);
                float f3 = (rand.nextFloat() - 0.5F) / 4.0F;
                float width = this.generateCaveRadius(rand);
                int i1 = i - rand.nextInt(i / 4);
                this.genTunnels(context, config, chunkIn, biomePos, rand.nextLong(), aquifer, bX, bY, bZ, hR, vR, width, f, f3, 0, i1, this.getYScale(), carvingMask, skipchecker);
            }
        }

        return true;
    }

    protected int getCaveBound() {
        return 15;
    }

    protected float generateCaveRadius(RandomSource rand) {
        float f = rand.nextFloat() * 2.0F + rand.nextFloat();
        if (rand.nextInt(10) == 0) {
            f *= rand.nextFloat() * rand.nextFloat() * 3.0F + 1.0F;
        }

        return f;
    }

    protected double getYScale() {
        return 1.0D;
    }

    protected void genRoom(CarvingContext context, T config, ChunkAccess chunkIn, Function<BlockPos, Holder<Biome>> biomePos, Aquifer aquifer, double x, double y, double z, float radius, double diameter, CarvingMask mask, CarveSkipChecker checker) {
        double d0 = 1.5D + (double)(Mth.sin(((float)Math.PI / 2F)) * radius);
        double d1 = d0 * diameter;
        this.carveEllipsoid(context, config, chunkIn, biomePos, aquifer, x + 1.0D, y, z, d0 * 4, d1 * 2, mask, checker);
    }

    protected void genTunnels(CarvingContext context, T config, ChunkAccess chunk, Function<BlockPos, Holder<Biome>> biomepos, long seed, Aquifer aquifer, double x, double y, double z, double hRad, double vRad, float width, float yaw, float pitch, int baseSize, int maxSize, double diameter, CarvingMask mask, CarveSkipChecker checker) {
        Random random = new Random(seed);
        int i = random.nextInt(maxSize / 2) + maxSize / 4;
        boolean flag = random.nextInt(6) == 0;
        float f = 0.0F;
        float f1 = 0.0F;

        for (int j = baseSize; j < maxSize; ++j) {
            double d0 = 1.5D + (double)(Mth.sin((float)Math.PI * (float)j / (float)maxSize) * width);
            double d1 = d0 * diameter;
            float f2 = Mth.cos(pitch);
            x += Mth.cos(yaw) * f2;
            y += Mth.sin(pitch);
            z += Mth.sin(yaw) * f2;
            pitch = pitch * (flag ? 0.92F : 0.7F);
            pitch = pitch + f1 * 0.1F;
            yaw += f * 0.1F;
            f1 = f1 * 0.9F;
            f = f * 0.75F;
            f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (j == i && width > 1.0F) {
                this.genTunnels(context, config, chunk, biomepos, random.nextLong(), aquifer, x, y, z, hRad, vRad, random.nextFloat() * 0.5F + 0.5F, yaw - ((float)Math.PI / 2F), pitch / 3.0F, j, maxSize, 1.0D, mask, checker);
                this.genTunnels(context, config, chunk, biomepos, random.nextLong(), aquifer, x, y, z, hRad, vRad, random.nextFloat() * 0.5F + 0.5F, yaw + ((float)Math.PI / 2F), pitch / 3.0F, j, maxSize, 1.0D, mask, checker);
                return;
            }

            if (random.nextInt(4) != 0) {
                if (!canReach(chunk.getPos(), x, z, j, maxSize, width)) {
                    return;
                }

                this.carveEllipsoid(context, config, chunk, biomepos, aquifer, x, y, z, d0 * hRad * 4, d1 * vRad * 2, mask, checker);
            }
        }
    }

    @Override
    protected boolean carveBlock(CarvingContext context, T config, ChunkAccess chunkIn, Function<BlockPos, Holder<Biome>> biomePos, CarvingMask carvingMask, BlockPos.MutableBlockPos mutable, BlockPos.MutableBlockPos newmutable, Aquifer aquifer, MutableBoolean flag) {
        BlockState blockstate = chunkIn.getBlockState(mutable);
        if (blockstate.getBlock() instanceof AbstractGaiaGrassBlock) {
            flag.setTrue();
        }

        if (!this.canReplaceBlock(config, blockstate)) {
            return false;
        } else {
            BlockState state = this.getCarveState(context, config, mutable, aquifer);
            if (state == null) {
                return false;
            } else {
                for (Direction dir : Direction.values()) {
                    FluidState around = chunkIn.getFluidState(newmutable.relative(dir));
                    FluidState above = chunkIn.getFluidState(newmutable.offset(mutable.offset(0, 1, 0)));
                    FluidState aroundabove = chunkIn.getFluidState(newmutable.offset(mutable.offset(0, 1, 1).relative(dir)));
                    if (around.is(ModFluids.mineral_water_still.get()) || above.is(ModFluids.mineral_water_still.get()) || aroundabove.is(ModFluids.mineral_water_still.get())) {
                        return false;
                    } else {
                        chunkIn.setBlockState(mutable, state, false);

                        if (flag.isTrue()) {
                            newmutable.setWithOffset(mutable, Direction.DOWN);
                            if (chunkIn.getBlockState(newmutable).getBlock() instanceof GaiaSoilBlock) {
                                context.topMaterial(biomePos, chunkIn, newmutable, !state.getFluidState().isEmpty()).ifPresent((newstate) -> {
                                    chunkIn.setBlockState(newmutable, newstate, false);
                                    if (!newstate.getFluidState().isEmpty()) {
                                        chunkIn.markPosForPostprocessing(newmutable);
                                    }
                                });

                            }
                        }
                    }
                }
            }

            return true;
        }
    }

    private BlockState getCarveState(CarvingContext context, T config, BlockPos pos, Aquifer aquifer) {
        if (pos.getY() <= config.lavaLevel.resolveY(context)) {
            return ModFluids.superhot_magma_still.get().defaultFluidState().createLegacyBlock();
        } else {
            BlockState blockstate = aquifer.computeSubstance(new DensityFunction.SinglePointContext(pos.getX(), pos.getY(), pos.getZ()), 0.0D);
            return blockstate == ModBlocks.gaia_stone.get().defaultBlockState() ? null : CAVE_AIR;
        }
    }

    protected static boolean skip(double xPos, double yPos, double zPos, double floor) {
        if (yPos <= floor) {
            return true;
        } else {
            return xPos * xPos + yPos * yPos + zPos * zPos >= 1.0D;
        }
    }
}
