package androsa.gaiadimension.world;

import androsa.gaiadimension.registry.ModBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public class GaiaBiomeBuilder {

    private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

    public void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, -1.0F,  FULL_RANGE, FULL_RANGE, ModBiomes.pink_agate_forest);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, -0.80F, FULL_RANGE, FULL_RANGE, ModBiomes.blue_agate_taiga);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, -0.64F, FULL_RANGE, FULL_RANGE, ModBiomes.green_agate_jungle);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, -0.48F, FULL_RANGE, FULL_RANGE, ModBiomes.purple_agate_swamp);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, -0.32F, FULL_RANGE, FULL_RANGE, ModBiomes.fossil_woodland);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, -0.16F, FULL_RANGE, FULL_RANGE, ModBiomes.mutant_agate_wildwood);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, 0.0F,   FULL_RANGE, FULL_RANGE, ModBiomes.volcanic_lands);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, 0.16F,  FULL_RANGE, FULL_RANGE, ModBiomes.static_wasteland);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, 0.32F,  FULL_RANGE, FULL_RANGE, ModBiomes.goldstone_lands);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, 0.48F,  FULL_RANGE, FULL_RANGE, ModBiomes.crystal_plains);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, 0.64F,  FULL_RANGE, FULL_RANGE, ModBiomes.salt_dunes);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, 0.80F,  FULL_RANGE, FULL_RANGE, ModBiomes.shining_grove);
        this.addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, 1.0F,   FULL_RANGE, FULL_RANGE, ModBiomes.smoldering_bog);
    }

    private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temp, Climate.Parameter humid, float continent, Climate.Parameter erosion, Climate.Parameter weirdness, ResourceKey<Biome> biome) {
        consumer.accept(Pair.of(Climate.parameters(temp, humid, Climate.Parameter.point(continent), erosion, Climate.Parameter.point(0.0F), weirdness, 0.0F), biome));
        consumer.accept(Pair.of(Climate.parameters(temp, humid, Climate.Parameter.point(continent), erosion, Climate.Parameter.point(1.0F), weirdness, 0.0F), biome));
    }
}
