package androsa.gaiadimension.world.layer;

import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class GaiaBiomeProviderSettings implements IBiomeProviderSettings {

    private long seed;

    public GaiaBiomeProviderSettings(WorldInfo info) {
        this.seed = info.getSeed();
    }

    public long getSeed() {
        return seed;
    }
}
