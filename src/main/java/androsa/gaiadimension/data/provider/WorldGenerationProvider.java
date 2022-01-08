package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.serialization.*;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.RegistryWriteOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

// Referenced from TF
public abstract class WorldGenerationProvider<T> extends RegistryWriteOps<T> implements DataProvider {
    protected static final Logger LOGGER = LogManager.getLogger();
    protected static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    protected final DataGenerator generator;
    private final Function<T, String> fileWriter;
    protected final RegistryAccess registries;
    private HashCache cache;
    private final HashSet<Object> serializationCache = new HashSet<>();

    public WorldGenerationProvider(DataGenerator generator, DynamicOps<T> ops, Function<T, String> writer, RegistryAccess registries) {
        super(ops, registries);
        this.generator = generator;
        this.fileWriter = writer;
        this.registries = registries;
    }

    @Override
    public String getName() {
        return "Gaia Dimension World Generation";
    }

    @Override
    public final void run(final HashCache cache) {
        this.cache = cache;

        generate(cache);
    }

    public abstract void generate(final HashCache cache);

    @Override
    protected <R> DataResult<T> encode(R resource, T registry, ResourceKey<? extends Registry<R>> resourceKey, Codec<R> codec) {
        Optional<ResourceLocation> location = getResourceLocation(resource, resourceKey);

        if (location.isPresent()) {
            if (GaiaDimensionMod.MODID.equals(location.get().getNamespace()))
                serialize(resourceKey, location.get(), resource, codec);

            return ResourceLocation.CODEC.encode(location.get(), this.delegate, registry);
        }

        // Inline instead. This does cause weird feature registry issues
        return codec.encode(resource, this, registry);
    }

    private <R> Optional<ResourceLocation> getResourceLocation(R resource, ResourceKey<? extends Registry<R>> resourceKey) {
        Optional<ResourceLocation> location = Optional.empty();

        // Find key as registry entry
        if (resource instanceof IForgeRegistryEntry entry) {
            location = Optional.ofNullable(entry.getRegistryName());
        }

        // Find key in registry access
        if (location.isEmpty()) {
            try {
                Registry<R> registry = registries.registryOrThrow(resourceKey);
                location = registry.getResourceKey(resource).map(ResourceKey::location);
            } catch (Throwable t) {
                // skip, apparently this comment is not considered "empty"
            }
        }

        // Find key in built in registries
        if (location.isEmpty()) {
            Registry<R> registry = getFromVanillaRegistryIllegally(BuiltinRegistries.REGISTRY, resourceKey);

            if (registry != null) {
                location = registry.getResourceKey(resource).map(ResourceKey::location);
            }
        }

        // Bruteforce check all vanilla registires
        if (location.isEmpty()) {
            Registry<R> registry = getFromVanillaRegistryIllegally(Registry.REGISTRY, resourceKey);

            if (registry != null) {
                location = registry.getResourceKey(resource).map(ResourceKey::location);
            }
        }

        // Bruteforce check all forge registries
        if (location.isEmpty()) {
            location = getFromForgeRegistryIllegally(resourceKey, resource);
        }

        return location;
    }

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

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <R> Optional<ResourceLocation> getFromForgeRegistryIllegally(ResourceKey<? extends Registry<R>> resourceKey, R resource) {
        if (resource instanceof IForgeRegistryEntry entry) {
            ResourceLocation location = entry.getRegistryName();

            if (location != null) {
                return Optional.of(location);
            }

            IForgeRegistry registry = RegistryManager.ACTIVE.getRegistry(resourceKey.location());
            return Optional.ofNullable(registry.getKey(entry));
        }

        return Optional.empty();
    }

    public <R> void serialize(ResourceKey<? extends Registry<R>> resourceKey, ResourceLocation location, R resource, Encoder<R> encoder) {
        if (this.serializationCache.contains(resource)) {
            LOGGER.debug("Skipping " + location + " as it has been serialized.");
            return;
        }

        this.serializationCache.add(resource);

        Optional<T> output = this
                .withEncoder(encoder)
                .apply(resource)
                .setLifecycle(Lifecycle.experimental())
                .resultOrPartial(e -> LOGGER.error("Object [" + resourceKey.getRegistryName() + "] " + location + " not serialized within recursive serialization: " + e));

        if (output.isPresent()) {
            try {
                save(resourceKey, cache, output.get(), makePath(generator.getOutputFolder(), resourceKey, location));
            } catch (IOException e) {
                LOGGER.error("Could not save resource `" + location + "` (Resource Type `" + resourceKey.location() + "`)", e);
            }
        }
    }

    /** VanillaCopy: IDataProvider.save */
    private void save(ResourceKey<?> key, HashCache cache, T registry, Path path) throws IOException {
        registry = intercept(key, registry);
        String write = fileWriter.apply(registry);
        String encode = SHA1.hashUnencodedChars(write).toString();

        if (!Objects.equals(cache.getHash(path), encode) || !Files.exists(path)) {
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(write);
            }
        }

        cache.putNew(path, encode);
    }

    protected T intercept(ResourceKey<?> key, T registry) {
        return registry;
    }

    private static Path makePath(Path path, ResourceKey<?> key, ResourceLocation location) {
        return path.resolve("data") // "data..."
                .resolve(location.getNamespace()) // ".../modid..."
                .resolve(key.location().getPath()) // ".../registry..."
                .resolve(location.getPath() + ".json"); // ".../object.json"
    }
}
