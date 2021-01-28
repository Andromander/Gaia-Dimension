package androsa.gaiadimension.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.*;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.Random;
import java.util.function.Supplier;

public class GaiaChunkGenerator extends NoiseChunkGenerator {

    public static final Codec<GaiaChunkGenerator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    BiomeProvider.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeProvider),
                    Codec.LONG.fieldOf("seed")
                            .orElseGet(() -> GaiaChunkGenerator.hackSeed)
                            .forGetter((obj) -> obj.seed),
                    DimensionSettings.field_236098_b_.fieldOf("settings")
                            .forGetter(GaiaChunkGenerator::getSettings)
            ).apply(instance, instance.stable(GaiaChunkGenerator::new)));

    private long seed;
    public static long hackSeed; //DON'T TOUCH

    public GaiaChunkGenerator(BiomeProvider provider, long seed, Supplier<DimensionSettings> settingsIn) {
//        super(worldIn, provider, 4, 8, 256, settingsIn, true);
        super(provider, seed, settingsIn);
        this.seed = seed;
    }

    @Override
    protected Codec<? extends ChunkGenerator> func_230347_a_() {
        return CODEC;
    }

    @Override
    public ChunkGenerator func_230349_a_(long seed) {
        return new GaiaChunkGenerator(biomeProvider.getBiomeProvider(seed), seed, getSettings());
    }

    private Supplier<DimensionSettings> getSettings() {
        return field_236080_h_;
    }
}
