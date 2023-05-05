package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.GaiaDamage;
import androsa.gaiadimension.registry.ModStructures;
import androsa.gaiadimension.registry.ModDimensions;
import androsa.gaiadimension.registry.configurations.GaiaConfiguredCarvers;
import androsa.gaiadimension.registry.configurations.GaiaFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GaiaDatapackRegistries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder REGISTRIES = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_CARVER, GaiaConfiguredCarvers::init)
            .add(Registries.PLACED_FEATURE, GaiaFeatures.Placed::init)
            .add(Registries.CONFIGURED_FEATURE, GaiaFeatures.Configured::init)
            .add(Registries.BIOME, GaiaBiomes::init)
            .add(Registries.STRUCTURE, ModStructures::initStructures)
            .add(Registries.STRUCTURE_SET, ModStructures::initSets)
            .add(Registries.NOISE_SETTINGS, ModDimensions::initNoise)
            .add(Registries.DIMENSION_TYPE, ModDimensions::initType)
            .add(Registries.LEVEL_STEM, ModDimensions::initStem)
            .add(Registries.DAMAGE_TYPE, GaiaDamage::init);

    public GaiaDatapackRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, REGISTRIES, Set.of("minecraft", GaiaDimensionMod.MODID));
    }

    public static void generate(boolean server, DataGenerator generator, PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        generator.addProvider(server, new GaiaDatapackRegistries(output, provider));
        generator.addProvider(server, new GaiaBiomeTags(output, provider.thenApply(r -> apply(r, REGISTRIES)), helper));
    }

    private static HolderLookup.Provider apply(HolderLookup.Provider provider, RegistrySetBuilder builder) {
        return builder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), provider);
    }
}
