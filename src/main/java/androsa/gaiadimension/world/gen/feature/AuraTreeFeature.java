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
public class AuraTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    private static final BlockState TRUNK = ModBlocks.aura_log.get().getDefaultState();
    private static final BlockState LEAF = ModBlocks.aura_leaves.get().getDefaultState();

    public AuraTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean flag) {
        super(configIn, flag);
        setSapling((IPlantable)ModBlocks.aura_sapling.get());
    }

    @Override
    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox boundingBox) {
        int baseHeight = rand.nextInt(3) + rand.nextInt(3) + 10;
        boolean canGrow = true;

        if (position.getY() >= 1 && position.getY() + baseHeight + 1 <= 256) {
            for (int yMark = position.getY(); yMark <= position.getY() + 1 + baseHeight; ++yMark) {
                int offset = 1;

                if (yMark == position.getY()) {
                    offset = 0;
                }

                if (yMark >= position.getY() + 1 + baseHeight - 2) {
                    offset = 2;
                }

                BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

                for (int xMark = position.getX() - offset; xMark <= position.getX() + offset && canGrow; ++xMark) {
                    for (int zMark = position.getZ() - offset; zMark <= position.getZ() + offset && canGrow; ++zMark) {
                        if (yMark >= 0 && yMark < 256) {
                            if (!func_214587_a(worldIn,mutablePos.setPos(xMark, yMark, zMark))) {
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
            } else if (isSoil(worldIn, position.down(), getSapling()) && position.getY() < worldIn.getMaxHeight() - baseHeight - 1) {
                this.setDirtAt(worldIn, position.down(), position); //TODO: evaluate if this will set the right block
                int posX = position.getX();
                int posZ = position.getZ();
                int k1 = 0;
                int offset = 1;

                for (int base = 0; base < baseHeight; ++base) {
                    int i2 = position.getY() + base;
                    BlockPos blockpos = new BlockPos(posX, i2, posZ);

                    if (isAirOrLeaves(worldIn, blockpos)) {
                        this.placeLogAt(changedBlocks, worldIn, blockpos, boundingBox);
                        k1 = i2;
                    }

                    if (base > baseHeight / 2) {
                        if (isAirOrLeaves(worldIn, blockpos)) {
                            this.placeLogAt(changedBlocks, worldIn, new BlockPos(posX - offset, i2, posZ), boundingBox);
                            this.placeLogAt(changedBlocks, worldIn, new BlockPos(posX + offset, i2, posZ), boundingBox);
                            this.placeLogAt(changedBlocks, worldIn, new BlockPos(posX, i2, posZ - offset), boundingBox);
                            this.placeLogAt(changedBlocks, worldIn, new BlockPos(posX, i2, posZ + offset), boundingBox);
                            if (base % 2 == 0) {
                                offset += 1;
                            }
                        }
                    }
                }

                BlockPos blockpos2 = new BlockPos(posX, k1 + 1, posZ);

                for (int j3 = -1; j3 <= 1; ++j3) {
                    for (int i4 = -1; i4 <= 1; ++i4) {
                        if (Math.abs(j3) != 1 || Math.abs(i4) != 1){
                            this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(j3 - 3, 0, i4), boundingBox);
                            this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(j3 + 3, 0, i4), boundingBox);
                            this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(j3, 0, i4 - 3), boundingBox);
                            this.placeLeafAt(changedBlocks, worldIn, blockpos2.add(j3, 0, i4 + 3), boundingBox);
                        }
                    }
                }

                BlockPos blockpos3 = new BlockPos(posX, k1, posZ);

                for (int j3 = -2; j3 <= 2; ++j3) {
                    for (int i4 = -2; i4 <= 2; ++i4) {
                        if (Math.abs(j3) != 2 || Math.abs(i4) != 2){
                            this.placeLeafAt(changedBlocks, worldIn, blockpos3.add(j3 - 3, 0, i4), boundingBox);
                            this.placeLeafAt(changedBlocks, worldIn, blockpos3.add(j3 + 3, 0, i4), boundingBox);
                            this.placeLeafAt(changedBlocks, worldIn, blockpos3.add(j3, 0, i4 - 3), boundingBox);
                            this.placeLeafAt(changedBlocks, worldIn, blockpos3.add(j3, 0, i4 + 3), boundingBox);
                        }
                    }
                }

                this.placeLeafAt(changedBlocks, worldIn, blockpos3.up(), boundingBox);

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
