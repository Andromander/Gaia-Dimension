package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModFluids;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class MarshLakeFeature extends Feature<NoneFeatureConfiguration> {

    public MarshLakeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return place(context.level(), context.random(), context.origin());
    }

    public boolean place(WorldGenLevel level, Random random, BlockPos origin) {
        int radius = 14;
        int rad = (radius / 2) + 1;
        Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);

        //Check area
        for (int x = -rad; x < rad; x++) {
            for (int z = -rad; z < rad; z++) {
                if (!(level.getBlockState(origin.offset(x, -1, z)).isFaceSturdy(EmptyBlockGetter.INSTANCE, origin.offset(x, 0, z), Direction.DOWN) && level.isEmptyBlock(origin))) {
                    return false;
                }
            }
        }

        BlockPos pos = origin.below();

        //Lake
        switch (random.nextInt(3)) {
            case 1 -> generateSquare(level, pos.offset(-(radius / 2), 0, -(radius / 2)), radius);
            case 2 -> generateTriangle(level, pos.offset(0, 0, -(radius / 2 + 1)), radius);
            default -> generateCircle(level, pos, radius / 2);
        }

        //Mound
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                level.setBlock(pos.offset(x, 0, z), ModBlocks.tough_golden_stone.get().defaultBlockState(), 2);
                level.setBlock(pos.offset(x, 1, z), ModBlocks.tough_golden_stone.get().defaultBlockState(), 2);
            }
        }

        if (dir != Direction.NORTH) level.setBlock(pos.offset(0, 2, -1), ModBlocks.tough_golden_stone.get().defaultBlockState(), 2);
        if (dir != Direction.EAST) level.setBlock(pos.offset(-1, 2, 0), ModBlocks.tough_golden_stone.get().defaultBlockState(), 2);
        if (dir != Direction.SOUTH) level.setBlock(pos.offset(0, 2, 1), ModBlocks.tough_golden_stone.get().defaultBlockState(), 2);
        if (dir != Direction.WEST) level.setBlock(pos.offset(1, 2, 0), ModBlocks.tough_golden_stone.get().defaultBlockState(), 2);

        level.setBlock(pos.offset(0, 2, 0), ModFluids.mineral_water_still.get().defaultFluidState().createLegacyBlock(), 2);
        level.scheduleTick(pos.offset(0, 2, 0), ModFluids.mineral_water_still.get().defaultFluidState().getType(), 0);

        return true;
    }

    private void generateSquare(WorldGenLevel level, BlockPos origin, int radius) {
        for (int x = 0; x <= radius; x++) {
            for (int z = 0; z <= radius; z++) {
                genAir(level, origin.offset(x, 1, z));
                level.setBlock(origin.offset(x, 0, z), ModFluids.mineral_water_still.get().defaultFluidState().createLegacyBlock(), 2);
            }
        }
    }

    private void generateCircle(WorldGenLevel level, BlockPos origin, int radius) {
        for(int i1 = origin.getX() - radius; i1 <= origin.getX() + radius; ++i1) {
            for(int j1 = origin.getZ() - radius; j1 <= origin.getZ() + radius; ++j1) {
                int k1 = i1 - origin.getX();
                int l1 = j1 - origin.getZ();
                if (k1 * k1 + l1 * l1 <= radius * radius) {
                    BlockPos blockpos1 = new BlockPos(i1, origin.getY(), j1);
                    genAir(level, blockpos1.above());
                    level.setBlock(blockpos1, ModFluids.mineral_water_still.get().defaultFluidState().createLegacyBlock(), 2);
                }
            }
        }
    }

    private void generateTriangle(WorldGenLevel level, BlockPos origin, int radius) {
        int inc = 1;
        int width = 0;
        for (int z = 0; z <= radius; z++) {
            for (int x = -width; x <= width; x++) {
                genAir(level, origin.offset(x, 1, z));
                level.setBlock(origin.offset(x, 0, z), ModFluids.mineral_water_still.get().defaultFluidState().createLegacyBlock(), 2);
            }

            if (inc++ % 2 == 0) {
                width++;
            }
        }
    }

    private void genAir(WorldGenLevel level, BlockPos pos) {
        if (!level.getBlockState(pos).isAir()) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            this.markAboveForPostProcessing(level, pos);
        }
    }
}
