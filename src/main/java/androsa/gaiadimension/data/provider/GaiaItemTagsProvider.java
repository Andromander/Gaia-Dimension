package androsa.gaiadimension.data.provider;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class GaiaItemTagsProvider extends ItemTagsProvider {

    public GaiaItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid) {
        super(output, provider, modid);
    }

    protected void addTag(TagKey<Item> tag, ImmutableList<Supplier<Item>> list) {
        TagAppender<Item, Item> builder = this.tag(tag);
        for (Supplier<Item> item : list) {
            builder.add(item.get());
        }
    }
}
