package androsa.gaiadimension.data;

import androsa.gaiadimension.registry.bootstrap.GaiaFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FeatureTagsProvider;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.tags.FeatureTags;

import java.util.concurrent.CompletableFuture;

public class GaiaFeatureTags extends FeatureTagsProvider {
    public GaiaFeatureTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(FeatureTags.CAN_SPAWN_FROM_BONE_MEAL)
                .add(
                        GaiaFeatures.Configured.common_bloom,
                        GaiaFeatures.Configured.rare_bloom,
                        GaiaFeatures.Configured.mutant_bloom,
                        GaiaFeatures.Configured.corrupt_bloom
                );
    }
}
