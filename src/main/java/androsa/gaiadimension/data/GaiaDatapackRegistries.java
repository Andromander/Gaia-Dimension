package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.GaiaDamage;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GaiaDatapackRegistries extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder REGISTRIES = new RegistrySetBuilder()
            .add(Registries.DAMAGE_TYPE, GaiaDamage::init);

    public GaiaDatapackRegistries(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, REGISTRIES, Set.of("minecraft", GaiaDimensionMod.MODID));
    }

    public static void generate(boolean server, DataGenerator generator, PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        generator.addProvider(server, new GaiaDatapackRegistries(output, provider));
    }
}
