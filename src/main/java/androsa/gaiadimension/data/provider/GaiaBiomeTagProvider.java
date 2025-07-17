package androsa.gaiadimension.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class GaiaBiomeTagProvider extends BiomeTagsProvider {

    public GaiaBiomeTagProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> provider, String modid) {
        super(generator, provider, modid);
    }

    protected void addTag(TagKey<Biome> tag, ResourceKey<Biome>... keys) {
        TagAppender<ResourceKey<Biome>, Biome> builder = this.tag(tag);
        builder.add(keys);
    }
}
