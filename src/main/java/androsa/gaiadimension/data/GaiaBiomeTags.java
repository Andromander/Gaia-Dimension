package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaBiomeTagProvider;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GaiaBiomeTags extends GaiaBiomeTagProvider {

    public GaiaBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, GaiaDimensionMod.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.addTag(GaiaTags.Biomes.HAS_MINI_TOWER, ModBiomes.pink_agate_forest, ModBiomes.blue_agate_taiga, ModBiomes.green_agate_jungle, ModBiomes.purple_agate_swamp, ModBiomes.fossil_woodland, ModBiomes.mutant_agate_wildwood, ModBiomes.crystal_plains);
        this.addTag(GaiaTags.Biomes.HAS_MALACHITE_WATCHTOWER, ModBiomes.pink_agate_forest, ModBiomes.green_agate_jungle, ModBiomes.crystal_plains);
    }
}
