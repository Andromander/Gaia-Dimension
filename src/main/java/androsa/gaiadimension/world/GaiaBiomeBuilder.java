package androsa.gaiadimension.world;

import androsa.gaiadimension.registry.ModBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public class GaiaBiomeBuilder {

    /*
     * Temperature: Frozen to Hot
     * Humidity: Arid to Wet
     * Continentalness: Far into the ocean to far inland
     * Erosion: Unstable terrain to flat terrain
     * Depth: Top of the world to bottom of the world. -1 for floating islands, 1 for underground, 0 for surface
     * Weirdness: Basic to unusual. Determines rivers at average
     * Offset: idk lol, doesn't appear to be important
     */

    private final Climate.Parameter FULL_RANGE = span(-1.0F, 1.0F);
    private final Climate.Parameter UPPER_RANGE = span(0.0F, 1.0F);
    private final Climate.Parameter LOWER_RANGE = span(-1.0F, 0.0F);
    private final Climate.Parameter EX_UPPER_RANGE = span(1.0F, 2.0F);
    private final Climate.Parameter EX_LOWER_RANGE = span(-2.0F, -1.0F);
    private final Climate.Parameter GOLD_CONTINENT = span(-1.2F, -1.05F);
    private final Climate.Parameter SEA_CONTINENT = span(-0.95F, -0.19F);
    private final Climate.Parameter COAST_CONTINENT = span(-0.19F, -0.11F);
    private final Climate.Parameter NEAR_INLAND_CONTINENT = span(-0.11F, 0.03F);
    private final Climate.Parameter MID_INLAND_CONTINENT = span(0.03F, 0.3F);
    private final Climate.Parameter PEAK_CONTINENT = span(0.3F, 1.0F);
    private final Climate.Parameter INLAND_CONTINENT = span(-0.11F, 1.0F);
    private final Climate.Parameter LAND_CONTINENT = Climate.Parameter.span(COAST_CONTINENT, PEAK_CONTINENT);
    private final Climate.Parameter[] TEMPERATURES = new Climate.Parameter[]{ span(-1.0F, -0.45F), span(-0.45F, -0.15F), span(-0.15F, 0.2F), span(0.2F, 0.55F), span(0.55F, 1.0F) };
    private final Climate.Parameter[] EROSIONS = new Climate.Parameter[]{ span(-1.0F, -0.78F), span(-0.78F, -0.375F), span(-0.375F, -0.2225F), span(-0.2225F, 0.05F), span(0.05F, 0.45F), span(0.45F, 0.55F), span(0.55F, 1.0F) };
    private final ResourceKey<Biome>[] COMMON_BIOMES = new ResourceKey[]{ ModBiomes.blue_agate_taiga, ModBiomes.purple_agate_swamp, ModBiomes.pink_agate_forest, ModBiomes.crystal_plains, ModBiomes.green_agate_jungle };


    public void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
        this.addOffCoastBiomes(consumer);
        this.addInlandBiomes(consumer);
        this.addUnderground(consumer);
    }

    private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
        this.addGoldBiomes(consumer, this.GOLD_CONTINENT);
        this.addSeaBiomes(consumer, this.SEA_CONTINENT);
    }

    private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
        this.addMidSlice(consumer, Climate.Parameter.span(-1.0F, -0.93333334F));
        this.addHighSlice(consumer, Climate.Parameter.span(-0.93333334F, -0.7666667F));
        this.addPeaks(consumer, Climate.Parameter.span(-0.7666667F, -0.56666666F));
        this.addHighSlice(consumer, Climate.Parameter.span(-0.56666666F, -0.4F));
        this.addMidSlice(consumer, Climate.Parameter.span(-0.4F, -0.26666668F));
        this.addLowSlice(consumer, Climate.Parameter.span(-0.26666668F, -0.05F));
        this.addRivers(consumer, Climate.Parameter.span(-0.05F, 0.05F));
        this.addLowSlice(consumer, Climate.Parameter.span(0.05F, 0.26666668F));
        this.addMidSlice(consumer, Climate.Parameter.span(0.26666668F, 0.4F));
        this.addHighSlice(consumer, Climate.Parameter.span(0.4F, 0.56666666F));
        this.addPeaks(consumer, Climate.Parameter.span(0.56666666F, 0.7666667F));
        this.addHighSlice(consumer, Climate.Parameter.span(0.7666667F, 0.93333334F));
        this.addMidSlice(consumer, Climate.Parameter.span(0.93333334F, 1.0F));
    }

    private void addGoldBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter continent) {
        addSurfaceBiome(consumer, LOWER_RANGE, LOWER_RANGE, continent, LOWER_RANGE, LOWER_RANGE, ModBiomes.golden_forest);
        addSurfaceBiome(consumer, UPPER_RANGE, LOWER_RANGE, continent, LOWER_RANGE, LOWER_RANGE, ModBiomes.golden_sands);
        addSurfaceBiome(consumer, LOWER_RANGE, UPPER_RANGE, continent, LOWER_RANGE, LOWER_RANGE, ModBiomes.golden_marsh);
        addSurfaceBiome(consumer, LOWER_RANGE, LOWER_RANGE, continent, UPPER_RANGE, LOWER_RANGE, ModBiomes.golden_plains);
        addSurfaceBiome(consumer, LOWER_RANGE, LOWER_RANGE, continent, LOWER_RANGE, UPPER_RANGE, ModBiomes.golden_hills);
        addUndergroundBiome(consumer, FULL_RANGE, FULL_RANGE, continent, FULL_RANGE, FULL_RANGE, span(0.2F, 0.5F), ModBiomes.golden_caves);
    }

    private void addSeaBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter continent) {
        addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, continent, FULL_RANGE, LOWER_RANGE, ModBiomes.mineral_reservoir);
        addSurfaceBiome(consumer, FULL_RANGE, FULL_RANGE, continent, FULL_RANGE, UPPER_RANGE, ModBiomes.aquamarine_trench);
    }

    private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
        this.addSurfaceBiome(consumer, this.EX_UPPER_RANGE, this.FULL_RANGE, this.LAND_CONTINENT, this.erosion(3), weirdness, ModBiomes.hotspot);
        this.addSurfaceBiome(consumer, this.EX_LOWER_RANGE, this.FULL_RANGE, this.LAND_CONTINENT, this.erosion(3), weirdness, ModBiomes.prismatic_steppe);

        for(int i = 0; i < this.TEMPERATURES.length; ++i) {
            Climate.Parameter temperature = this.TEMPERATURES[i];
            ResourceKey<Biome> commonbiome = this.pickCommonBiome(i, weirdness);

            if (i > 3) {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.COAST_CONTINENT, this.NEAR_INLAND_CONTINENT), erosions(0, 1), weirdness, ModBiomes.igneous_plains);
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosions(0, 1), weirdness, ModBiomes.volcanic_lands);
            } else {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.LAND_CONTINENT, erosions(0, 1), weirdness, commonbiome);
            }

            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.LAND_CONTINENT, erosions(2, 4), weirdness, commonbiome);

            if (i < 1) {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.NEAR_INLAND_CONTINENT, erosions(5, 6), weirdness, ModBiomes.wasteland_hills);
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosions(5, 6), weirdness, ModBiomes.static_wasteland);
            } else {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.LAND_CONTINENT, erosions(5, 6), weirdness, commonbiome);
            }
        }
    }

    private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
        this.addSurfaceBiome(consumer, this.EX_UPPER_RANGE, this.FULL_RANGE, this.LAND_CONTINENT, this.erosion(3), weirdness, ModBiomes.hotspot);
        this.addSurfaceBiome(consumer, this.EX_LOWER_RANGE, this.FULL_RANGE, this.LAND_CONTINENT, this.erosion(3), weirdness, ModBiomes.prismatic_steppe);

        for(int i = 0; i < this.TEMPERATURES.length; ++i) {
            Climate.Parameter temperature = this.TEMPERATURES[i];
            ResourceKey<Biome> commonbiome = this.pickCommonBiome(i, weirdness);

            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.COAST_CONTINENT, erosions(0, 1), weirdness, commonbiome);

            if (i > 3) {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.NEAR_INLAND_CONTINENT, erosions(0, 1), weirdness, ModBiomes.igneous_plains);
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosions(0, 1), weirdness, ModBiomes.volcanic_lands);
            } else {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.INLAND_CONTINENT, erosions(0, 1), weirdness, commonbiome);
            }

            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.LAND_CONTINENT, erosion(2), weirdness, commonbiome);

            if (weirdness.max() > 0L) {
                if (i > 3) {
                    this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.LAND_CONTINENT, erosion(3), weirdness, ModBiomes.smoldering_bog);
                } else {
                    this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.LAND_CONTINENT, erosion(3), weirdness, ModBiomes.shining_grove);
                }
            } else {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, LAND_CONTINENT, erosion(3), weirdness, commonbiome);
            }

            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.LAND_CONTINENT, erosion(4), weirdness, commonbiome);

            if (i < 1) {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.NEAR_INLAND_CONTINENT, erosions(5, 6), weirdness, ModBiomes.wasteland_hills);
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosions(5, 6), weirdness, ModBiomes.static_wasteland);
            } else {
                if (weirdness.max() > 0L) {
                    this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.NEAR_INLAND_CONTINENT, erosion(5), weirdness, ModBiomes.weirded_goldstone_lands);
                    this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosion(5), weirdness, ModBiomes.goldstone_lands);
                } else {
                    this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.INLAND_CONTINENT, erosion(5), weirdness, commonbiome);
                }

                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.LAND_CONTINENT, erosion(6), weirdness, commonbiome);
            }
        }
    }

    private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
        this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.COAST_CONTINENT, erosions(0, 2), weirdness, ModBiomes.tourmaline_coast);
        this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.INLAND_CONTINENT, erosion(6), weirdness, ModBiomes.crystal_plains);

        for(int i = 0; i < this.TEMPERATURES.length; ++i) {
            Climate.Parameter temperature = this.TEMPERATURES[i];

            ResourceKey<Biome> commonbiome = this.pickCommonBiome(i, weirdness);
            ResourceKey<Biome> beachbiome = this.pickBeachBiome(weirdness);
            ResourceKey<Biome> beachorcommonbiome = this.pickBeachOrCommon(i, weirdness);

            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.INLAND_CONTINENT, erosions(0, 2), weirdness, commonbiome);

            if (weirdness.max() > 0L) {
                if (i > 3) {
                    this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.LAND_CONTINENT, erosion(3), weirdness, ModBiomes.smoldering_bog);
                } else {
                    this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.LAND_CONTINENT, erosion(3), weirdness, ModBiomes.shining_grove);
                }
            } else {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, LAND_CONTINENT, erosion(3), weirdness, commonbiome);
            }

            if (i >= 3) {
                this.addSurfaceBiome(consumer, temperature, span(-1.0F, 0.3F), this.INLAND_CONTINENT, erosion(4), FULL_RANGE, commonbiome);
                this.addSurfaceBiome(consumer, temperature, span(0.3F, 0.55F), this.INLAND_CONTINENT, erosion(4), FULL_RANGE, ModBiomes.salt_dunes);
                if (weirdness.max() > 0L) {
                    this.addSurfaceBiome(consumer, temperature, span(0.55F, 1.0F), this.INLAND_CONTINENT, erosion(4), weirdness, ModBiomes.mookaite_mesa);
                } else {
                    this.addSurfaceBiome(consumer, temperature, span(0.55F, 1.0F), this.INLAND_CONTINENT, erosion(4), weirdness, ModBiomes.crystal_salt_dunes);
                }
            } else {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.INLAND_CONTINENT, erosion(4), weirdness, commonbiome);
            }

            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.COAST_CONTINENT, erosion(5), weirdness, beachbiome);

            if (weirdness.max() > 0L) {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.NEAR_INLAND_CONTINENT, erosion(5), weirdness, ModBiomes.weirded_goldstone_lands);
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosion(5), weirdness, ModBiomes.goldstone_lands);
            } else {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.NEAR_INLAND_CONTINENT, erosion(5), weirdness, beachorcommonbiome);
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosion(5), weirdness, commonbiome);
            }

            if (weirdness.max() < 0L) {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.COAST_CONTINENT, erosion(6), weirdness, beachbiome);
            } else {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.COAST_CONTINENT, erosion(6), weirdness, commonbiome);
            }
        }
    }

    private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
        this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.COAST_CONTINENT, erosions(0, 2), weirdness, ModBiomes.tourmaline_coast);
        this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.NEAR_INLAND_CONTINENT, this.PEAK_CONTINENT), erosion(6), weirdness, ModBiomes.crystal_plains);

        for(int i = 0; i < this.TEMPERATURES.length; ++i) {
            Climate.Parameter temperature = this.TEMPERATURES[i];

            ResourceKey<Biome> commonbiome = this.pickCommonBiome(i, weirdness);
            ResourceKey<Biome> beachbiome = this.pickBeachBiome(weirdness);
            ResourceKey<Biome> beachorcommonbiome = this.pickBeachOrCommon(i, weirdness);

            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.NEAR_INLAND_CONTINENT, erosions(0, 1), weirdness, commonbiome);
            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosions(0, 1), weirdness, commonbiome);
            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.NEAR_INLAND_CONTINENT, erosions(2, 3), weirdness, commonbiome);
            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosions(2, 3), weirdness, commonbiome);
            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.COAST_CONTINENT, erosions(3, 4), weirdness, beachbiome);

            if (i >= 3) {
                this.addSurfaceBiome(consumer, temperature, span(-1.0F, 0.3F), this.INLAND_CONTINENT, erosion(4), FULL_RANGE, commonbiome);
                this.addSurfaceBiome(consumer, temperature, span(0.3F, 0.55F), this.INLAND_CONTINENT, erosion(4), FULL_RANGE, ModBiomes.salt_dunes);
                if (weirdness.max() > 0L) {
                    this.addSurfaceBiome(consumer, temperature, span(0.55F, 1.0F), this.INLAND_CONTINENT, erosion(4), weirdness, ModBiomes.mookaite_mesa);
                } else {
                    this.addSurfaceBiome(consumer, temperature, span(0.55F, 1.0F), this.INLAND_CONTINENT, erosion(4), weirdness, ModBiomes.crystal_salt_dunes);
                }
            } else {
                this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.INLAND_CONTINENT, erosion(4), weirdness, commonbiome);
            }

            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.COAST_CONTINENT, erosion(5), weirdness, beachbiome);
            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.NEAR_INLAND_CONTINENT, erosion(5), weirdness, beachorcommonbiome);
            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosion(5), weirdness, commonbiome);
            this.addSurfaceBiome(consumer, temperature, this.FULL_RANGE, this.COAST_CONTINENT, erosion(6), weirdness, beachbiome);
        }
    }

    private void addRivers(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness) {
        this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.COAST_CONTINENT, erosions(0, 1), weirdness, weirdness.max() < 0L ? ModBiomes.tourmaline_coast : ModBiomes.mineral_river);
        this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.NEAR_INLAND_CONTINENT, erosions(0, 1), weirdness, ModBiomes.mineral_river);
        this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.LAND_CONTINENT, erosions(2, 5), weirdness, ModBiomes.mineral_river);
        this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.COAST_CONTINENT, erosion(6), weirdness, ModBiomes.mineral_river);
        this.addSurfaceBiome(consumer, this.FULL_RANGE, this.FULL_RANGE, this.INLAND_CONTINENT, erosion(6), weirdness, ModBiomes.purple_agate_swamp);

        for(int i = 0; i < this.TEMPERATURES.length; ++i) {
            ResourceKey<Biome> biome = this.pickCommonBiome(i, weirdness);
            Climate.Parameter temperature = this.TEMPERATURES[i];
            this.addSurfaceBiome(consumer, temperature, FULL_RANGE, Climate.Parameter.span(this.MID_INLAND_CONTINENT, this.PEAK_CONTINENT), erosions(0, 1), weirdness, biome);
        }
    }

    private ResourceKey<Biome> pickCommonBiome(int temp, Climate.Parameter weirdness) {
        if (weirdness.max() > 0L) {
            if (temp == 1) {
                return ModBiomes.fossil_woodland;
            } else if (temp == 2) {
                return ModBiomes.mutant_agate_wildwood;
            } else {
                return COMMON_BIOMES[temp];
            }
        } else {
            return COMMON_BIOMES[temp];
        }
    }

    private ResourceKey<Biome> pickBeachOrCommon(int temp, Climate.Parameter weirdness) {
        return weirdness.max() >= 0L ? pickCommonBiome(temp, weirdness) : pickBeachBiome(weirdness);
    }

    private ResourceKey<Biome> pickBeachBiome(Climate.Parameter weirdness) {
        if (weirdness.max() < 0L) {
            return ModBiomes.salty_coast;
        } else {
            return ModBiomes.tourmaline_coast;
        }
    }

    private void addUnderground(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer) {
        addUndergroundBiome(consumer, FULL_RANGE, FULL_RANGE, FULL_RANGE, FULL_RANGE, LOWER_RANGE, span(0.2F, 0.9F), ModBiomes.glitter_caves);
        addUndergroundBiome(consumer, FULL_RANGE, FULL_RANGE, FULL_RANGE, FULL_RANGE, UPPER_RANGE, span(0.2F, 0.9F), ModBiomes.energy_caves);
    }

    private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temp, Climate.Parameter humid, Climate.Parameter continent, Climate.Parameter erosion, Climate.Parameter weirdness, ResourceKey<Biome> biome) {
        consumer.accept(Pair.of(Climate.parameters(temp, humid, continent, erosion, Climate.Parameter.point(0.0F), weirdness, 0.0F), biome));
        consumer.accept(Pair.of(Climate.parameters(temp, humid, continent, erosion, Climate.Parameter.point(1.0F), weirdness, 0.0F), biome));
    }

    private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter temp, Climate.Parameter humid, Climate.Parameter continent, Climate.Parameter erosion, Climate.Parameter weirdness, Climate.Parameter depth, ResourceKey<Biome> biome) {
        consumer.accept(Pair.of(Climate.parameters(temp, humid, continent, erosion, depth, weirdness, 0.0F), biome));
    }

    private Climate.Parameter span(float min, float max) {
        return Climate.Parameter.span(min, max);
    }

    private Climate.Parameter erosion(int level) {
        return EROSIONS[level];
    }

    private Climate.Parameter erosions(int min, int max) {
        return Climate.Parameter.span(EROSIONS[min], EROSIONS[max]);
    }
}
