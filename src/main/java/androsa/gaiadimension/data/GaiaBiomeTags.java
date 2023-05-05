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
        this.addTag(GaiaTags.Biomes.HAS_MINI_TOWER, ModBiomes.pink_agate_forest, ModBiomes.blue_agate_taiga, ModBiomes.green_agate_jungle, ModBiomes.purple_agate_swamp, ModBiomes.fossil_woodland, ModBiomes.mutant_agate_wildwood, ModBiomes.crystal_plains);
        this.addTag(GaiaTags.Biomes.HAS_MALACHITE_WATCHTOWER, ModBiomes.pink_agate_forest, ModBiomes.green_agate_jungle, ModBiomes.crystal_plains);
        this.addTag(BiomeTags.IS_BADLANDS, ModBiomes.mookaite_mesa);
        this.addTag(GaiaTags.Biomes.IS_DRY_GAIA, ModBiomes.salt_dunes, ModBiomes.mookaite_mesa, ModBiomes.volcanic_lands);
        this.tag(Tags.Biomes.IS_DRY).addTags(GaiaTags.Biomes.IS_DRY_GAIA);
        this.addTag(BiomeTags.IS_FOREST, ModBiomes.pink_agate_forest, ModBiomes.mutant_agate_wildwood, ModBiomes.shining_grove, ModBiomes.golden_forest);
        this.addTag(BiomeTags.IS_HILL, ModBiomes.golden_hills);
        this.addTag(GaiaTags.Biomes.IS_HOT_GAIA, ModBiomes.salt_dunes, ModBiomes.mookaite_mesa, ModBiomes.smoldering_bog, ModBiomes.volcanic_lands);
        this.tag(Tags.Biomes.IS_HOT).addTags(GaiaTags.Biomes.IS_HOT_GAIA);
        this.addTag(BiomeTags.IS_JUNGLE, ModBiomes.green_agate_jungle);
        this.addTag(Tags.Biomes.IS_LUSH, ModBiomes.mutant_agate_wildwood, ModBiomes.shining_grove);
        this.addTag(Tags.Biomes.IS_MAGICAL, ModBiomes.purple_agate_swamp, ModBiomes.shining_grove, ModBiomes.golden_forest, ModBiomes.golden_plains, ModBiomes.golden_hills, ModBiomes.golden_sands, ModBiomes.golden_marsh);
        this.addTag(Tags.Biomes.IS_MODIFIED, ModBiomes.mutant_agate_wildwood);
        this.addTag(Tags.Biomes.IS_MOUNTAIN, ModBiomes.volcanic_lands);
        this.addTag(BiomeTags.IS_OCEAN, ModBiomes.mineral_reservoir);
        this.addTag(Tags.Biomes.IS_PLAINS, ModBiomes.crystal_plains, ModBiomes.goldstone_lands, ModBiomes.golden_plains);
        this.addTag(Tags.Biomes.IS_PLATEAU, ModBiomes.mookaite_mesa, ModBiomes.static_wasteland);
        this.addTag(Tags.Biomes.IS_RARE, ModBiomes.golden_forest, ModBiomes.golden_plains, ModBiomes.golden_hills, ModBiomes.golden_sands, ModBiomes.golden_marsh);
        this.addTag(BiomeTags.IS_RIVER, ModBiomes.mineral_river);
        this.addTag(Tags.Biomes.IS_SANDY, ModBiomes.salt_dunes, ModBiomes.golden_sands);
        this.addTag(BiomeTags.IS_SAVANNA, ModBiomes.fossil_woodland);
        this.addTag(GaiaTags.Biomes.IS_SPARSE_GAIA, ModBiomes.salt_dunes, ModBiomes.mookaite_mesa, ModBiomes.volcanic_lands);
        this.tag(Tags.Biomes.IS_SPARSE).addTags(GaiaTags.Biomes.IS_SPARSE_GAIA);
        this.addTag(Tags.Biomes.IS_SWAMP, ModBiomes.purple_agate_swamp, ModBiomes.golden_marsh);
        this.addTag(BiomeTags.IS_TAIGA, ModBiomes.blue_agate_taiga);
        this.addTag(Tags.Biomes.IS_WASTELAND, ModBiomes.smoldering_bog, ModBiomes.static_wasteland, ModBiomes.goldstone_lands);
        this.tag(GaiaTags.Biomes.PORTAL_BIOMES).addTags(Tags.Biomes.IS_HOT, Tags.Biomes.IS_DRY, Tags.Biomes.IS_MOUNTAIN);
    }
}
