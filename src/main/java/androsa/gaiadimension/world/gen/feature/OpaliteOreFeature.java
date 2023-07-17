package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.world.gen.feature.config.OpaliteOreConfiguration;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.BitSet;
import java.util.Map;
import java.util.function.Function;

public class OpaliteOreFeature extends Feature<OpaliteOreConfiguration> {

    private static final Map<BlockState, BlockState> BLOCK_TO_ORE = ImmutableMap.of(
            ModBlocks.scarlet_mookaite.get().defaultBlockState(), ModBlocks.scarlet_opalite_ore.get().defaultBlockState(),
            ModBlocks.auburn_mookaite.get().defaultBlockState(), ModBlocks.auburn_opalite_ore.get().defaultBlockState(),
            ModBlocks.gold_mookaite.get().defaultBlockState(), ModBlocks.gold_opalite_ore.get().defaultBlockState(),
            ModBlocks.mauve_mookaite.get().defaultBlockState(), ModBlocks.mauve_opalite_ore.get().defaultBlockState(),
            ModBlocks.beige_mookaite.get().defaultBlockState(), ModBlocks.beige_opalite_ore.get().defaultBlockState(),
            ModBlocks.ivory_mookaite.get().defaultBlockState(), ModBlocks.ivory_opalite_ore.get().defaultBlockState());

    public OpaliteOreFeature(Codec<OpaliteOreConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<OpaliteOreConfiguration> context) {
        return place(context.level(), context.random(), context.origin(), context.config());
    }

    public boolean place(WorldGenLevel level, RandomSource random, BlockPos pos, OpaliteOreConfiguration config) {
        float area = random.nextFloat() * (float)Math.PI;
        float size = (float)config.size / 8.0F;
        int min = Mth.ceil(((float)config.size / 16.0F * 2.0F + 1.0F) / 2.0F);
        double maxX = (double)pos.getX() + Math.sin((double)area) * (double)size;
        double minX = (double)pos.getX() - Math.sin((double)area) * (double)size;
        double maxZ = (double)pos.getZ() + Math.cos((double)area) * (double)size;
        double minZ = (double)pos.getZ() - Math.cos((double)area) * (double)size;
        double y1 = (double)(pos.getY() + random.nextInt(3) - 2);
        double y2 = (double)(pos.getY() + random.nextInt(3) - 2);
        int startX = pos.getX() - Mth.ceil(size) - min;
        int startY = pos.getY() - 2 - min;
        int startZ = pos.getZ() - Mth.ceil(size) - min;
        int j1 = 2 * (Mth.ceil(size) + min);
        int k1 = 2 * (2 + min);

        for(int x = startX; x <= startX + j1; ++x) {
            for(int z = startZ; z <= startZ + j1; ++z) {
                if (startY <= level.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, x, z)) {
                    return this.doPlace(level, random, config, maxX, minX, maxZ, minZ, y1, y2, startX, startY, startZ, j1, k1);
                }
            }
        }

        return false;
    }

    protected boolean doPlace(WorldGenLevel level, RandomSource random, OpaliteOreConfiguration config, double maxX, double minX, double maxZ, double minZ, double y1, double y2, int p_225181_, int p_225182_, int p_225183_, int p_225184_, int p_225185_) {
        int i = 0;
        BitSet bitset = new BitSet(p_225184_ * p_225185_ * p_225184_);
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        int size = config.size;
        double[] adouble = new double[size * 4];

        for(int k = 0; k < size; ++k) {
            float f = (float)k / (float)size;
            double d0 = Mth.lerp((double)f, maxX, minX);
            double d1 = Mth.lerp((double)f, y1, y2);
            double d2 = Mth.lerp((double)f, maxZ, minZ);
            double d3 = random.nextDouble() * (double)size / 16.0D;
            double d4 = ((double)(Mth.sin((float)Math.PI * f) + 1.0F) * d3 + 1.0D) / 2.0D;
            adouble[k * 4 + 0] = d0;
            adouble[k * 4 + 1] = d1;
            adouble[k * 4 + 2] = d2;
            adouble[k * 4 + 3] = d4;
        }

        for(int l3 = 0; l3 < size - 1; ++l3) {
            if (!(adouble[l3 * 4 + 3] <= 0.0D)) {
                for(int i4 = l3 + 1; i4 < size; ++i4) {
                    if (!(adouble[i4 * 4 + 3] <= 0.0D)) {
                        double d8 = adouble[l3 * 4 + 0] - adouble[i4 * 4 + 0];
                        double d10 = adouble[l3 * 4 + 1] - adouble[i4 * 4 + 1];
                        double d12 = adouble[l3 * 4 + 2] - adouble[i4 * 4 + 2];
                        double d14 = adouble[l3 * 4 + 3] - adouble[i4 * 4 + 3];
                        if (d14 * d14 > d8 * d8 + d10 * d10 + d12 * d12) {
                            if (d14 > 0.0D) {
                                adouble[i4 * 4 + 3] = -1.0D;
                            } else {
                                adouble[l3 * 4 + 3] = -1.0D;
                            }
                        }
                    }
                }
            }
        }

        try (BulkSectionAccess bulk = new BulkSectionAccess(level)) {
            for(int j4 = 0; j4 < size; ++j4) {
                double d9 = adouble[j4 * 4 + 3];
                if (!(d9 < 0.0D)) {
                    double d11 = adouble[j4 * 4 + 0];
                    double d13 = adouble[j4 * 4 + 1];
                    double d15 = adouble[j4 * 4 + 2];
                    int k4 = Math.max(Mth.floor(d11 - d9), p_225181_);
                    int l = Math.max(Mth.floor(d13 - d9), p_225182_);
                    int i1 = Math.max(Mth.floor(d15 - d9), p_225183_);
                    int j1 = Math.max(Mth.floor(d11 + d9), k4);
                    int k1 = Math.max(Mth.floor(d13 + d9), l);
                    int l1 = Math.max(Mth.floor(d15 + d9), i1);

                    for(int secX = k4; secX <= j1; ++secX) {
                        double d5 = ((double)secX + 0.5D - d11) / d9;
                        if (d5 * d5 < 1.0D) {
                            for(int secY = l; secY <= k1; ++secY) {
                                double d6 = ((double)secY + 0.5D - d13) / d9;
                                if (d5 * d5 + d6 * d6 < 1.0D) {
                                    for(int secZ = i1; secZ <= l1; ++secZ) {
                                        double d7 = ((double)secZ + 0.5D - d15) / d9;
                                        if (d5 * d5 + d6 * d6 + d7 * d7 < 1.0D && !level.isOutsideBuildHeight(secY)) {
                                            int l2 = secX - p_225181_ + (secY - p_225182_) * p_225184_ + (secZ - p_225183_) * p_225184_ * p_225185_;
                                            if (!bitset.get(l2)) {
                                                bitset.set(l2);
                                                mutable.set(secX, secY, secZ);
                                                if (level.ensureCanWrite(mutable)) {
                                                    LevelChunkSection section = bulk.getSection(mutable);
                                                    if (section != null) {
                                                        int relX = SectionPos.sectionRelative(secX);
                                                        int relY = SectionPos.sectionRelative(secY);
                                                        int relZ = SectionPos.sectionRelative(secZ);
                                                        BlockState state = section.getBlockState(relX, relY, relZ);

                                                        if (canPlaceOre(state, bulk::getBlockState, random, config, mutable)) {
                                                            section.setBlockState(relX, relY, relZ, BLOCK_TO_ORE.get(state), false);
                                                            ++i;
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return i > 0;
    }

    public static boolean canPlaceOre(BlockState state, Function<BlockPos, BlockState> test, RandomSource random, OpaliteOreConfiguration config, BlockPos.MutableBlockPos mutable) {
        if (!BLOCK_TO_ORE.containsKey(state)) {
            return false;
        } else if (shouldSkipAirCheck(random, config.discardChanceOnAirExposure)) {
            return true;
        } else {
            return !isAdjacentToAir(test, mutable);
        }
    }

    protected static boolean shouldSkipAirCheck(RandomSource random, float chance) {
        if (chance <= 0.0F) {
            return true;
        } else if (chance >= 1.0F) {
            return false;
        } else {
            return random.nextFloat() >= chance;
        }
    }
}
