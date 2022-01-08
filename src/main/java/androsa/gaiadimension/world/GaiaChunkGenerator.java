package androsa.gaiadimension.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryLookupCodec;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.function.Supplier;

public class GaiaChunkGenerator extends NoiseBasedChunkGenerator {

    public static final Codec<GaiaChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    RegistryLookupCodec.create(Registry.NOISE_REGISTRY).forGetter(GaiaChunkGenerator::getNoiseRegistry),
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource),
                    Codec.LONG.fieldOf("seed")
                            .orElseGet(() -> GaiaChunkGenerator.hackSeed)
                            .forGetter((obj) -> obj.seed),
                    NoiseGeneratorSettings.CODEC.fieldOf("settings")
                            .forGetter(GaiaChunkGenerator::getDimensionSettings)
            ).apply(instance, instance.stable(GaiaChunkGenerator::new)));

    private long seed;
    public static long hackSeed; //DON'T TOUCH

    public GaiaChunkGenerator(Registry<NormalNoise.NoiseParameters> registry, BiomeSource provider, long seed, Supplier<NoiseGeneratorSettings> settingsIn) {
//        super(worldIn, provider, 4, 8, 256, settingsIn, true);
        super(registry, provider, seed, settingsIn);
        this.seed = seed;
    }

    @Override
    protected Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public ChunkGenerator withSeed(long seed) {
        return new GaiaChunkGenerator(getNoiseRegistry(), biomeSource.withSeed(seed), seed, getDimensionSettings());
    }

    private Supplier<NoiseGeneratorSettings> getDimensionSettings() {
        return settings;
    }

    public Registry<NormalNoise.NoiseParameters> getNoiseRegistry() {
        return this.noises;
    }
}
