package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.world.GaiaGenerationSettings;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class GaiaBiomeProviderSettings implements IBiomeProviderSettings {

    private long seed;
    private GaiaGenerationSettings generatorSettings;

    public GaiaBiomeProviderSettings(WorldInfo info) {
        this.seed = info.getSeed();
    }

//    public GaiaBiomeProviderSettings setWorldInfo(WorldInfo info) {
//        this.worldInfo = info;
//        return this;
//    }

    public GaiaBiomeProviderSettings setGeneratorSettings(GaiaGenerationSettings settings) {
        this.generatorSettings = settings;
        return this;
    }

    public long getSeed() {
        return seed;
    }

    public GaiaGenerationSettings getGeneratorSettings() {
        return this.generatorSettings;
    }
}
