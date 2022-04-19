package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GaiaBiomeTagProvider extends BiomeTagsProvider {

    public GaiaBiomeTagProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, GaiaDimensionMod.MODID, helper);
    }

    protected void addTag(TagKey<Biome> tag, ResourceKey<Biome>... keys) {
        TagsProvider.TagAppender<Biome> builder = this.tag(tag);
        builder.add(keys);
    }
}
