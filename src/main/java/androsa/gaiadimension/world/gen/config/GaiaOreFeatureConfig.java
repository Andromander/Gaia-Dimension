package androsa.gaiadimension.world.gen.config;

import androsa.gaiadimension.registry.ModBlocks;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GaiaOreFeatureConfig implements IFeatureConfig {
    public final GaiaOreFeatureConfig.FillerBlockType target;
    public final int size;
    public final BlockState state;

    public GaiaOreFeatureConfig(GaiaOreFeatureConfig.FillerBlockType target, BlockState oreIn, int size) {
        this.size = size;
        this.state = oreIn;
        this.target = target;
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> file) {
        return new Dynamic<>(file, file.createMap(ImmutableMap.of(file.createString("size"), file.createInt(this.size), file.createString("target"), file.createString(this.target.getName()), file.createString("state"), BlockState.serialize(file, this.state).getValue())));
    }

    public static GaiaOreFeatureConfig deserialize(Dynamic<?> file) {
        int i = file.get("size").asInt(0);
        GaiaOreFeatureConfig.FillerBlockType orefeatureconfig$fillerblocktype = GaiaOreFeatureConfig.FillerBlockType.getFillerName(file.get("target").asString(""));
        BlockState blockstate = file.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
        return new GaiaOreFeatureConfig(orefeatureconfig$fillerblocktype, blockstate, i);
    }

    public enum FillerBlockType {
        STONE("stone", new BlockMatcher(ModBlocks.gaia_stone)),
        STATIC("wasteland", (stone) -> {
            if (stone == null) {
                return false;
            } else {
                Block block = stone.getBlock();
                return block == ModBlocks.gaia_stone || block == ModBlocks.wasteland_stone;
            }
        }),
        VOLCANIC("volcanic", (stone) -> {
            if (stone == null) {
                return false;
            } else {
                Block block = stone.getBlock();
                return block == ModBlocks.gaia_stone || block == ModBlocks.volcanic_rock;
            }
        });

        private static final Map<String, FillerBlockType> fillerMap = Arrays.stream(values()).collect(Collectors.toMap(GaiaOreFeatureConfig.FillerBlockType::getName, (filler) -> filler));
        private final String name;
        private final Predicate<BlockState> stonePredicate;

        FillerBlockType(String nameIn, Predicate<BlockState> stoneIn) {
            this.name = nameIn;
            this.stonePredicate = stoneIn;
        }

        public String getName() {
            return this.name;
        }

        public static GaiaOreFeatureConfig.FillerBlockType getFillerName(String name) {
            return fillerMap.get(name);
        }

        public Predicate<BlockState> getStonePredicate() {
            return this.stonePredicate;
        }
    }
}
