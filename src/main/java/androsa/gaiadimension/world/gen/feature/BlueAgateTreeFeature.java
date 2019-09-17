package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.IPlantable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public class BlueAgateTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {

    public BlueAgateTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean flag) {
        super(configIn, flag);
        this.setSapling((IPlantable) ModBlocks.blue_agate_sapling);
    }

    @Override
    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader world, Random rand, BlockPos pos, MutableBoundingBox boundingBox) {
        int height = rand.nextInt(4) + 6;
        int j = 1 + rand.nextInt(2);
        int k = height - j;
        int l = 2 + rand.nextInt(2);
        boolean allClear = true;

        if (pos.getY() >= 1 && pos.getY() + height + 1 <= world.getMaxHeight()) {
            for (int i1 = pos.getY(); i1 <= pos.getY() + 1 + height && allClear; ++i1) {
                int j1;

                if (i1 - pos.getY() < j) {
                    j1 = 0;
                } else {
                    j1 = l;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int k1 = pos.getX() - j1; k1 <= pos.getX() + j1 && allClear; ++k1) {
                    for (int l1 = pos.getZ() - j1; l1 <= pos.getZ() + j1 && allClear; ++l1) {
                        if (i1 >= 0 && i1 < world.getMaxHeight()) {
                            if (!func_214587_a(world, blockpos$mutableblockpos.setPos(k1, i1, l1))) {
                                allClear = false;
                            }
                        } else {
                            allClear = false;
                        }
                    }
                }
            }

            if (!allClear) {
                return false;
            } else if (isSoil(world, pos.down(), getSapling()) && pos.getY() < world.getMaxHeight() - height - 1) {
                this.setDirtAt(world, pos.down(), pos); //TODO: evaluate if this will set the right block
                int i3 = rand.nextInt(2);
                int j3 = 1;
                int k3 = 0;

                for (int l3 = 0; l3 <= k; ++l3) {
                    int j4 = pos.getY() + height - l3;
                    for (int i2 = pos.getX() - i3; i2 <= pos.getX() + i3; ++i2) {
                        int j2 = i2 - pos.getX();
                        for (int k2 = pos.getZ() - i3; k2 <= pos.getZ() + i3; ++k2) {
                            int l2 = k2 - pos.getZ();
                            if (Math.abs(j2) != i3 || Math.abs(l2) != i3 || i3 <= 0) {
                                BlockPos blockpos = new BlockPos(i2, j4, k2);
                                if (isAirOrLeaves(world, blockpos) || isTallPlants(world, blockpos)) {
                                    this.setLogState(changedBlocks, world, blockpos, ModBlocks.blue_agate_leaves.getDefaultState(), boundingBox);
                                }
                            }
                        }
                    }

                    if (i3 >= j3) {
                        i3 = k3;
                        k3 = 1;
                        ++j3;

                        if (j3 > l) {
                            j3 = l;
                        }
                    } else {
                        ++i3;
                    }
                }

                int i4 = rand.nextInt(3);

                for (int k4 = 0; k4 < height - i4; ++k4) {
                    BlockPos upN = pos.up(k4);
                    if (isAirOrLeaves(world, upN)) {
                        this.setLogState(changedBlocks, world, pos.up(k4), ModBlocks.blue_agate_log.getDefaultState(), boundingBox);
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}