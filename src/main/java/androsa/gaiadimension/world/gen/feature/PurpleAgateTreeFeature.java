package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.IPlantable;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class PurpleAgateTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    private static final BlockState TRUNK = ModBlocks.purple_agate_log.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.purple_agate_leaves.get().getDefaultState();

    public PurpleAgateTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean flag) {
        super(configIn, flag);
        this.setSapling((IPlantable)ModBlocks.purple_agate_sapling.get());
    }

    @Override
    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox boundingBox) {
        int height = rand.nextInt(3) + rand.nextInt(3) + 7;
        boolean canGrow = true;

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

                for (int cx = position.getX() - k; cx <= position.getX() + k && canGrow; ++cx) {
                    for (int cz = position.getZ() - k; cz <= position.getZ() + k && canGrow; ++cz) {
                        if (cy >= 0 && cy < 256) {
                            if (!func_214587_a(worldIn, blockpos$mutableblockpos.setPos(cx, cy, cz))) {
                                canGrow = false;
                            }
                        } else {
                            canGrow = false;
                        }
                    }
                }
            }

            if (!canGrow) {
                return false;
            } else if (isSoil(worldIn, position.down(), getSapling()) && position.getY() < worldIn.getMaxHeight() - height - 1) {
                this.setDirtAt(worldIn, position.down(), position);
                int posX = position.getX();
                int posZ = position.getZ();
                int posY = 0;

                for (int base = 0; base < height; ++base) {
                    int currentY = position.getY() + base;

                    BlockPos blockpos = new BlockPos(posX, currentY, posZ);
                    if (isAirOrLeaves(worldIn, blockpos)) {
                        if (base == height - 2) {
                            for (int length = 1; length <= 2; ++length) {
                                this.placeLogAt(changedBlocks, worldIn, blockpos.north(length), Direction.Axis.Z, boundingBox);
                                this.placeLogAt(changedBlocks, worldIn, blockpos.south(length), Direction.Axis.Z, boundingBox);
                                this.placeLogAt(changedBlocks, worldIn, blockpos.east(length), Direction.Axis.X, boundingBox);
                                this.placeLogAt(changedBlocks, worldIn, blockpos.west(length), Direction.Axis.X, boundingBox);
                            }
                        } else if (base == height - 1) {
                            for (int length = 3; length <= 4; ++length) {
                                this.placeLogAt(changedBlocks, worldIn, blockpos.north(length), Direction.Axis.Z, boundingBox);
                                this.placeLogAt(changedBlocks, worldIn, blockpos.south(length), Direction.Axis.Z, boundingBox);
                                this.placeLogAt(changedBlocks, worldIn, blockpos.east(length), Direction.Axis.X, boundingBox);
                                this.placeLogAt(changedBlocks, worldIn, blockpos.west(length), Direction.Axis.X, boundingBox);
                            }
                        } else {
                            this.placeLogAt(changedBlocks, worldIn, blockpos, Direction.Axis.Y, boundingBox);
                        }
                        posY = currentY;
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, posY, posZ);
                for (int k3 = -1; k3 <= 1; ++k3) {
                    for (int j4 = -1; j4 <= 1; ++j4) {
                        for (int l5 = -1; l5 <= 1; ++l5) {
                            if (Math.abs(k3) != 1 || Math.abs(j4) != 1 || Math.abs(l5) != 1){
                                this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(k3 + 4, l5, j4), boundingBox);
                                this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(k3 - 4, l5, j4), boundingBox);
                                this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(k3 , l5, j4 + 4), boundingBox);
                                this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(k3, l5, j4 - 4), boundingBox);
                            }
                        }
                    }
                }

                BlockPos blockpos3 = blockpos2.down(2);
                this.placeLeafAt(changedBlocks, worldIn, blockpos3.north(1), boundingBox);
                this.placeLeafAt(changedBlocks, worldIn, blockpos3.south(1), boundingBox);
                this.placeLeafAt(changedBlocks, worldIn, blockpos3.east(1), boundingBox);
                this.placeLeafAt(changedBlocks, worldIn, blockpos3.west(1), boundingBox);

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private void placeLogAt(Set<BlockPos> setPos, IWorldWriter writer, BlockPos pos, Direction.Axis axis, MutableBoundingBox boundingBox) {
        this.setLogState(setPos, writer, pos, TRUNK.with(RotatedPillarBlock.AXIS, axis), boundingBox);
    }

    private void placeLeafAt(Set<BlockPos> worldIn, IWorldGenerationReader writer, BlockPos pos, MutableBoundingBox boundingBox) {
        if (isAirOrLeaves(writer, pos)) {
            this.setLogState(worldIn, writer, pos, LEAF, boundingBox);
        }
    }
}
