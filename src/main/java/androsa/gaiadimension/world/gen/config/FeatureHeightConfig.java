package androsa.gaiadimension.world.gen.config;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class FeatureHeightConfig implements IFeatureConfig {

    public final int startHeight;

    public FeatureHeightConfig(int height) {
        this.startHeight = height;
    }

    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> dynamicOps) {
        return new Dynamic<>(dynamicOps, dynamicOps.createMap(ImmutableMap.of(dynamicOps.createString("start_height"), dynamicOps.createInt(startHeight))));
    }

    public static <T> FeatureHeightConfig deserialize(Dynamic<T> dynamic) {
        int height = dynamic.get("start_height").asInt(0);
        return new FeatureHeightConfig(height);
    }
}
