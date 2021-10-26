package androsa.gaiadimension.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.BaseDiskFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;

public class DiskNoWaterFeature extends BaseDiskFeature {

    public DiskNoWaterFeature(Codec<DiskConfiguration> config) {
        super(config);
    }
}

