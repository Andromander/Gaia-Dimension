package androsa.gaiadimension.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class OpaliteOreConfiguration implements FeatureConfiguration {
   public static final Codec<OpaliteOreConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                   Codec.intRange(0, 64).fieldOf("size").forGetter((obj) -> obj.size),
                   Codec.floatRange(0.0F, 1.0F).fieldOf("discard_chance_on_air_exposure").forGetter((obj) -> obj.discardChanceOnAirExposure))
           .apply(instance, OpaliteOreConfiguration::new));
   public final int size;
   public final float discardChanceOnAirExposure;

   public OpaliteOreConfiguration(int size, float chance) {
      this.size = size;
      this.discardChanceOnAirExposure = chance;
   }

   public OpaliteOreConfiguration(int size) {
      this(size, 0.0F);
   }
}