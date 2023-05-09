package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaBiomeTagProvider;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GaiaBiomeTags extends GaiaBiomeTagProvider {

    public GaiaBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, GaiaDimensionMod.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.addTag(GaiaTags.Biomes.AGATE_BIOMES, ModBiomes.pink_agate_forest, ModBiomes.blue_agate_taiga, ModBiomes.green_agate_jungle, ModBiomes.purple_agate_swamp, ModBiomes.mutant_agate_wildwood);
        this.addTag(GaiaTags.Biomes.GOLDEN_BIOMES, ModBiomes.golden_forest, ModBiomes.golden_plains, ModBiomes.golden_hills, ModBiomes.golden_marsh, ModBiomes.golden_sands);
        this.tag(GaiaTags.Biomes.GAIA_BIOMES).addTags(GaiaTags.Biomes.AGATE_BIOMES, GaiaTags.Biomes.GOLDEN_BIOMES)
                .add(ModBiomes.fossil_woodland, ModBiomes.volcanic_lands, ModBiomes.static_wasteland, ModBiomes.goldstone_lands, ModBiomes.crystal_plains,
                        ModBiomes.salt_dunes, ModBiomes.mookaite_mesa, ModBiomes.shining_grove, ModBiomes.smoldering_bog, ModBiomes.mineral_reservoir, ModBiomes.mineral_river);
        this.tag(GaiaTags.Biomes.HAS_MINI_TOWER).addTags(GaiaTags.Biomes.AGATE_BIOMES).add(ModBiomes.fossil_woodland, ModBiomes.crystal_plains);
        this.addTag(GaiaTags.Biomes.HAS_MALACHITE_WATCHTOWER, ModBiomes.pink_agate_forest, ModBiomes.green_agate_jungle, ModBiomes.crystal_plains);
        this.tag(GaiaTags.Biomes.PORTAL_BIOMES).addTags(Tags.Biomes.IS_HOT, Tags.Biomes.IS_DRY, Tags.Biomes.IS_MOUNTAIN, GaiaTags.Biomes.GAIA_BIOMES);

        //no to vanilla
        this.tag(BiomeTags.WITHOUT_WANDERING_TRADER_SPAWNS).addTags(GaiaTags.Biomes.GAIA_BIOMES);
        this.tag(BiomeTags.WITHOUT_PATROL_SPAWNS).addTags(GaiaTags.Biomes.GAIA_BIOMES);
        this.tag(BiomeTags.WITHOUT_ZOMBIE_SIEGES).addTags(GaiaTags.Biomes.GAIA_BIOMES);
    }
}
