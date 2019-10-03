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
public class GreenAgateTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    private static final BlockState TRUNK = ModBlocks.green_agate_log.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.green_agate_leaves.get().getDefaultState();

    public GreenAgateTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean flag) {
        super(configIn, flag);
        this.setSapling((IPlantable)ModBlocks.green_agate_sapling.get());
    }

    @Override
    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox boundingBox) {
        int height = rand.nextInt(3) + rand.nextInt(3) + 10;
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
                this.setDirtAt(worldIn, position.down(), position); //TODO: evaluate if this will set the right block
                int posX = position.getX();
                int posZ = position.getZ();
                int posY = 0;

                for (int base = 0; base < height; ++base) {
                    int currentY = position.getY() + base;

                    BlockPos blockpos = new BlockPos(posX, currentY, posZ);
                    if (isAirOrLeaves(worldIn, blockpos)) {
                        if (base == 0) {
                            this.placeLogAt(changedBlocks, worldIn, blockpos.north(2), boundingBox);
                            this.placeLogAt(changedBlocks, worldIn, blockpos.south(2), boundingBox);
                            this.placeLogAt(changedBlocks, worldIn, blockpos.east(2), boundingBox);
                            this.placeLogAt(changedBlocks, worldIn, blockpos.west(2), boundingBox);
                        }
                        if (base < height / 4) {
                            this.placeLogAt(changedBlocks, worldIn, blockpos.add(1, 0, 1), boundingBox);
                            this.placeLogAt(changedBlocks, worldIn, blockpos.add(1, 0, -1), boundingBox);
                            this.placeLogAt(changedBlocks, worldIn, blockpos.add(-1, 0, 1), boundingBox);
                            this.placeLogAt(changedBlocks, worldIn, blockpos.add(-1, 0, -1), boundingBox);
                        }
                        this.placeLogAt(changedBlocks, worldIn, blockpos, boundingBox);
                        this.placeLogAt(changedBlocks, worldIn, blockpos.north(), boundingBox);
                        this.placeLogAt(changedBlocks, worldIn, blockpos.south(), boundingBox);
                        this.placeLogAt(changedBlocks, worldIn, blockpos.east(), boundingBox);
                        this.placeLogAt(changedBlocks, worldIn, blockpos.west(), boundingBox);
                        posY = currentY;
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, posY, posZ);
                for (int leafX1 = -3; leafX1 <= 3; ++leafX1) {
                    for (int leafZ1 = -3; leafZ1 <= 3; ++leafZ1) {
                        if (Math.abs(leafX1) != 3 || Math.abs(leafZ1) != 3) {
                            this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(leafX1, 0, leafZ1), boundingBox);
                            this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(leafX1, -1, leafZ1), boundingBox);
                        }
                    }
                }

                blockpos2 = blockpos2.up();

                for (int leafX2 = -1; leafX2 <= 1; ++leafX2) {
                    for (int leafZ = -1; leafZ <= 1; ++leafZ) {
                        this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(leafX2, 0, leafZ), boundingBox);
                    }
                }

                this.placeLeafAt(changedBlocks, worldIn, blockpos2.east(2), boundingBox);
                this.placeLeafAt(changedBlocks, worldIn, blockpos2.west(2), boundingBox);
                this.placeLeafAt(changedBlocks, worldIn, blockpos2.south(2), boundingBox);
                this.placeLeafAt(changedBlocks, worldIn, blockpos2.north(2), boundingBox);

                blockpos2 = blockpos2.down(3);
                for (int leafX3 = -2; leafX3 <= 2; ++leafX3) {
                    for (int leafZ3 = -2; leafZ3 <= 2; ++leafZ3) {
                        if (Math.abs(leafX3) != 2 || Math.abs(leafZ3) != 2) {
                            this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(leafX3, 0, leafZ3), boundingBox);
                        }
                    }
                }

                blockpos2 = blockpos2.down();

                this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(1, 0, 1), boundingBox);
                this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(1, 0, -1), boundingBox);
                this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(-1, 0, 1), boundingBox);
                this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(-1, 0, -1), boundingBox);

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
