package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.world.GaiaGenerationSettings;
import net.minecraft.world.biome.provider.IBiomeProviderSettings;
import net.minecraft.world.storage.WorldInfo;

public class GaiaBiomeProviderSettings implements IBiomeProviderSettings {

    private WorldInfo worldInfo;
    private GaiaGenerationSettings generatorSettings;

    public GaiaBiomeProviderSettings() { }

    public GaiaBiomeProviderSettings setWorldInfo(WorldInfo info) {
        this.worldInfo = info;
        return this;
    }

    public GaiaBiomeProviderSettings setGeneratorSettings(GaiaGenerationSettings settings) {
        this.generatorSettings = settings;
        return this;
    }

    public WorldInfo getWorldInfo() {
        return this.worldInfo;
    }

    public GaiaGenerationSettings getGeneratorSettings() {
        return this.generatorSettings;
    }
}
