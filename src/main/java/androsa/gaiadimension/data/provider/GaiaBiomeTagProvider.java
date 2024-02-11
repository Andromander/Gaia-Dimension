package androsa.gaiadimension.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GaiaBiomeTagProvider extends BiomeTagsProvider {

    public GaiaBiomeTagProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> provider, String modid, ExistingFileHelper helper) {
        super(generator, provider, modid, helper);
    }

    protected void addTag(TagKey<Biome> tag, ResourceKey<Biome>... keys) {
        TagsProvider.TagAppender<Biome> builder = this.tag(tag);
        builder.add(keys);
    }
}
