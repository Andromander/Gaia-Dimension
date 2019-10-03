package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.IPlantable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

@ParametersAreNonnullByDefault
public class BurntAgateTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    private static final BlockState TRUNK = ModBlocks.burnt_log.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.burnt_leaves.get().getDefaultState();

    public BurntAgateTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean flag) {
        super(configIn, flag);
        this.setSapling((IPlantable)ModBlocks.burnt_sapling.get());
    }

    @Override
    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox boundingBox) {
        int height = rand.nextInt(3) + rand.nextInt(3) + 5;
        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + height + 1 <= 256) {
            for (int cy = position.getY(); cy <= position.getY() + 1 + height; ++cy) {
                int k = 1;

                if (cy == position.getY()) {
                    k = 0;
                }

                if (cy >= position.getY() + 1 + height - 2) {
                    k = 2;
                }

                BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                for (int cx = position.getX() - k; cx <= position.getX() + k && flag; ++cx) {
                    for (int cz = position.getZ() - k; cz <= position.getZ() + k && flag; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(cx, cy, cz))) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else if (isSoil(worldIn, position.down(), getSapling()) && position.getY() < worldIn.getMaxHeight() - height - 1) {
                this.setDirtAt(worldIn, position.down(), position); //TODO: evaluate if this will set the right block
                int posX = position.getX();
                int posZ = position.getZ();
                int k1 = 0;

                for (int base = 0; base < height; ++base) {
                    int i2 = position.getY() + base;

                    BlockPos blockpos = new BlockPos(posX, i2, posZ);
                    if (isAirOrLeaves(worldIn, blockpos)) {
                        this.placeLogAt(changedBlocks, worldIn, blockpos, boundingBox);
                        k1 = i2;
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, k1, posZ);
                for (int j3 = -2; j3 <= 2; ++j3) {
                    for (int i4 = -2; i4 <= 2; ++i4) {
                        if (Math.abs(j3) != 2 || Math.abs(i4) != 2){
                            this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(j3, 0, i4), boundingBox);
                        }
                    }
                }

                blockpos2 = blockpos2.up();
                for (int k3 = -1; k3 <= 1; ++k3) {
                    for (int j4 = -1; j4 <= 1; ++j4) {
                        this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(k3, 0, j4), boundingBox);
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

    private void placeLogAt(Set<BlockPos> setPos, IWorldWriter writer, BlockPos pos, MutableBoundingBox boundingBox) {
        this.setLogState(setPos, writer, pos, TRUNK, boundingBox);
    }

    private void placeLeafAt(Set<BlockPos> worldIn, IWorldGenerationReader writer, BlockPos pos, MutableBoundingBox boundingBox) {
        if (isAirOrLeaves(writer, pos)) {
            this.setLogState(worldIn, writer, pos, LEAF, boundingBox);
        }
    }
}
