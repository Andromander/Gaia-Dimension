package androsa.gaiadimension.world.gen.feature;

import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.values.GaiaTags;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MookaiteMoundFeature extends Feature<NoneFeatureConfiguration> {

    public MookaiteMoundFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return place(context.level(), context.random(), context.origin());
    }

    public boolean place(WorldGenLevel level, RandomSource random, BlockPos origin) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (!level.getBlockState(origin.offset(z, -1, z)).is(GaiaTags.Blocks.MOOKAITE)) {
                    return false;
                }
            }
        }

        Optional<HolderSet.Named<Block>> tag = BuiltInRegistries.BLOCK.getTag(GaiaTags.Blocks.MOOKAITE);
        if (tag.isEmpty()) {
            return false;
        }

        List<Block> mookaiteBlocks = tag.map(holders -> holders.stream().map(Holder::get).filter(block -> !block.defaultBlockState().isAir()).toList()).orElseGet(ArrayList::new);
        if (mookaiteBlocks.isEmpty()) {
            return false;
        }

        int height = 8 + random.nextInt(10);
        BlockState state = mookaiteBlocks.get(random.nextInt(mookaiteBlocks.size())).defaultBlockState();

        for (int y = 0; y <= height; y++) {
            if (random.nextInt(2) == 0) {
                state = mookaiteBlocks.get(random.nextInt(mookaiteBlocks.size())).defaultBlockState();
            }

            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    if (y <= 3) {
                        state = level.getBlockState(origin.offset(x, -1, z));
                    }

                    if (y == height && (Mth.abs(x) != 2 && Mth.abs(z) != 2)) {
                        level.setBlock(origin.offset(x, y, z), state, 2);
                    } else if (y != height && y >= height / 2 && (Mth.abs(x) != 2 || Mth.abs(z) != 2)) {
                        level.setBlock(origin.offset(x, y, z), state, 2);
                    } else if (y < height / 2) {
                        level.setBlock(origin.offset(x, y, z), state, 2);
                    }

                    if (x == 0 && z == 0) {
                        if (y > 2 && y < 6) {
                            if (random.nextInt(10) == 0) {
                                level.setBlock(origin.offset(x, y, z), ModBlocks.opalite_block.get().defaultBlockState(), 2);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
