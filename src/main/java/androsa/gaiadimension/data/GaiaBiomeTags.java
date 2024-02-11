package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaBiomeTagProvider;
import androsa.gaiadimension.registry.bootstrap.GaiaBiomes;
import androsa.gaiadimension.registry.values.GaiaTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GaiaBiomeTags extends GaiaBiomeTagProvider {

    public GaiaBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, GaiaDimensionMod.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.addTag(GaiaTags.Biomes.AGATE_BIOMES, GaiaBiomes.pink_agate_forest, GaiaBiomes.blue_agate_taiga, GaiaBiomes.green_agate_jungle, GaiaBiomes.purple_agate_swamp, GaiaBiomes.mutant_agate_wildwood);
        this.addTag(GaiaTags.Biomes.GOLDEN_BIOMES, GaiaBiomes.golden_forest, GaiaBiomes.golden_plains, GaiaBiomes.golden_hills, GaiaBiomes.golden_marsh, GaiaBiomes.golden_sands);
        this.tag(GaiaTags.Biomes.GAIA_BIOMES).addTags(GaiaTags.Biomes.AGATE_BIOMES, GaiaTags.Biomes.GOLDEN_BIOMES)
                .add(GaiaBiomes.fossil_woodland, GaiaBiomes.volcanic_lands, GaiaBiomes.static_wasteland, GaiaBiomes.goldstone_lands, GaiaBiomes.crystal_plains,
                        GaiaBiomes.salt_dunes, GaiaBiomes.mookaite_mesa, GaiaBiomes.shining_grove, GaiaBiomes.smoldering_bog, GaiaBiomes.mineral_reservoir, GaiaBiomes.mineral_river);
        this.tag(GaiaTags.Biomes.HAS_MINI_TOWER).addTags(GaiaTags.Biomes.AGATE_BIOMES).add(GaiaBiomes.fossil_woodland, GaiaBiomes.crystal_plains);
        this.addTag(GaiaTags.Biomes.HAS_MALACHITE_WATCHTOWER, GaiaBiomes.pink_agate_forest, GaiaBiomes.green_agate_jungle, GaiaBiomes.crystal_plains);
        this.tag(GaiaTags.Biomes.PORTAL_BIOMES).addTags(Tags.Biomes.IS_HOT, Tags.Biomes.IS_DRY, Tags.Biomes.IS_MOUNTAIN, GaiaTags.Biomes.GAIA_BIOMES);

        //no to vanilla
        this.tag(BiomeTags.WITHOUT_WANDERING_TRADER_SPAWNS).addTags(GaiaTags.Biomes.GAIA_BIOMES);
        this.tag(BiomeTags.WITHOUT_PATROL_SPAWNS).addTags(GaiaTags.Biomes.GAIA_BIOMES);
        this.tag(BiomeTags.WITHOUT_ZOMBIE_SIEGES).addTags(GaiaTags.Biomes.GAIA_BIOMES);
    }
}
