package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.info.WorldgenRegistryDumpReport;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

// Referenced from TF
public abstract class WorldGenerationProvider extends WorldgenRegistryDumpReport {
    protected static final Logger LOGGER = LogManager.getLogger();
    protected static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    protected final DataGenerator generator;

    public WorldGenerationProvider(DataGenerator generator) {
        super(generator);
        //Field is private in super
        this.generator = generator;
    }

    @Override
    public String getName() {
        return "Gaia Dimension World Generation";
    }

    protected <T> void dumpRegistryCap(HashCache cache, Path path, RegistryAccess access, DynamicOps<JsonElement> ops, RegistryAccess.RegistryData<T> data) {
        this.dumpRegistry(path, cache, ops, data.key(), access.ownedRegistryOrThrow(data.key()), data.codec());
    }

    protected <E, T extends Registry<E>> void dumpRegistry(Path path, HashCache cache, DynamicOps<JsonElement> ops, ResourceKey<? extends T> key, T registry, Encoder<E> encoder) {
        for (Map.Entry<ResourceKey<E>, E> entry : registry.entrySet()) {
            //Check if the object can serialize. Excludes any serialized or in the wrong namespace.
            if (!entry.getKey().location().getNamespace().equals(GaiaDimensionMod.MODID)) continue;
            Path fullpath = this.makePath(path, key, entry.getKey().location());
            this.dumpValue(fullpath, cache, ops, encoder, entry.getValue());
        }
    }

    protected <E> void dumpValue(Path path, HashCache cache, DynamicOps<JsonElement> ops, Encoder<E> encoder, E element) {
        try {
            Optional<JsonElement> optional = encoder.encodeStart(ops, element).resultOrPartial((error) ->
                    LOGGER.error("Couldn't serialize element {}: {}", path, error));
            if (optional.isPresent()) {
                DataProvider.save(GSON, cache, optional.get(), path);
            }
        } catch (IOException e) {
            LOGGER.error("Couldn't save element {}", path, e);
        }
    }

    @Override
    public void run(HashCache cache) {
        Path path = this.generator.getOutputFolder();
        RegistryAccess access = BuiltinRegistries.ACCESS;
        DynamicOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, access);
        //RegistryAccess.knownRegistries().forEach(registryData -> this.dumpRegistryCap(cache, path, access, ops, registryData));
        this.dumpRegistries(access, cache, path, ops);
    }

    protected abstract void dumpRegistries(RegistryAccess access, HashCache cache, Path path, DynamicOps<JsonElement> ops);

    protected <R> R getOrCreateRegistry(Registry<R> registry, ResourceKey<R> key, Supplier<R> creator) {
        R resource = getFromVanillaRegistryIllegally(registry, key);

        if (resource == null) {
            resource = Registry.register(registry, key.location(), creator.get());
        }

        return resource;
    }

    @SuppressWarnings({"unchecked", "rawtypes", "SameParameterValue"})
    @Nullable
    protected static <R> R getFromVanillaRegistryIllegally(Registry registry, ResourceKey<R> key) {
        return (R) registry.get(key);
    }

    private Path makePath(Path path, ResourceKey<?> key, ResourceLocation location) {
        return path.resolve("data") // "data..."
                .resolve(location.getNamespace()) // ".../modid..."
                .resolve(key.location().getPath()) // ".../registry..."
                .resolve(location.getPath() + ".json"); // ".../object.json"
    }
}
