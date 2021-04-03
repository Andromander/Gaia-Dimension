package androsa.gaiadimension.data.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ConfiguredFeaturesProvider implements IDataProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    public final DataGenerator generator;

    public ConfiguredFeaturesProvider(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    public String getName() {
        return "Configured Features";
    }

    @Override
    public void run(DirectoryCache cache) {
        LOGGER.info("Starting Feature Gen");
        Path output = this.generator.getOutputFolder();

        for (Map.Entry<RegistryKey<ConfiguredFeature<?,?>>, ConfiguredFeature<?,?>> feature : registerFeatures().entrySet()) {
            Path path = getPath(output, feature.getKey().location());
            Function<Supplier<ConfiguredFeature<?,?>>, DataResult<JsonElement>> featuredata = JsonOps.INSTANCE.withEncoder(ConfiguredFeature.CODEC);

            try {
                Optional<JsonElement> optional = featuredata.apply(feature::getValue).result();

                if (optional.isPresent()) {
                    IDataProvider.save(GSON, cache, optional.get(), path);
                } else {
                    LOGGER.error("Couldn't serialize feature {}", path);
                }
            } catch (IOException e) {
                LOGGER.error("Couldn't save biome {}", path, e);
            }
        }
        LOGGER.info("Finished Feature Gen");
    }

    protected abstract Map<RegistryKey<ConfiguredFeature<?,?>>, ConfiguredFeature<?,?>> registerFeatures();

    private static Path getPath(Path path, ResourceLocation loc) {
        return path.resolve("data/" + loc.getNamespace() + "/worldgen/configured_feature/" + loc.getPath() + ".json");
    }
}
