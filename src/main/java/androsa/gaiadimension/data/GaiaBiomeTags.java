package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaBiomeTagProvider;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModBiomes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GaiaBiomeTags extends GaiaBiomeTagProvider {

    public GaiaBiomeTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, helper);
    }

    @Override
    protected void addTags() {
        this.addTag(GaiaTags.Biomes.HAS_MINI_TOWER, ModBiomes.pink_agate_forest, ModBiomes.blue_agate_taiga, ModBiomes.green_agate_jungle, ModBiomes.purple_agate_swamp, ModBiomes.fossil_woodland, ModBiomes.mutant_agate_wildwood, ModBiomes.crystal_plains);
        this.addTag(GaiaTags.Biomes.HAS_MALACHITE_WATCHTOWER, ModBiomes.pink_agate_forest, ModBiomes.green_agate_jungle, ModBiomes.crystal_plains);
    }
}
