package androsa.gaiadimension.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.function.Supplier;

public class GaiaChunkGenerator extends NoiseBasedChunkGenerator {

    public static final Codec<GaiaChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource),
                    Codec.LONG.fieldOf("seed")
                            .orElseGet(() -> GaiaChunkGenerator.hackSeed)
                            .forGetter((obj) -> obj.seed),
                    NoiseGeneratorSettings.CODEC.fieldOf("settings")
                            .forGetter(GaiaChunkGenerator::getDimensionSettings)
            ).apply(instance, instance.stable(GaiaChunkGenerator::new)));

    private long seed;
    public static long hackSeed; //DON'T TOUCH

    public GaiaChunkGenerator(BiomeSource provider, long seed, Supplier<NoiseGeneratorSettings> settingsIn) {
//        super(worldIn, provider, 4, 8, 256, settingsIn, true);
        super(provider, seed, settingsIn);
        this.seed = seed;
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new GaiaChunkGenerator(biomeSource.withSeed(seed), seed, getDimensionSettings());
    }

    private Supplier<NoiseGeneratorSettings> getDimensionSettings() {
        return settings;
    }
}
