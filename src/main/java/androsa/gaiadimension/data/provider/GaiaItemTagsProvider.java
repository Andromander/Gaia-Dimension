package androsa.gaiadimension.data.provider;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class GaiaItemTagsProvider extends ItemTagsProvider {

    public GaiaItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blocktags, String modid, ExistingFileHelper existingFileHelper) {
        super(output, provider, blocktags, modid, existingFileHelper);
    }

    protected void addTagFromBlock(TagKey<Item> tag, Block... blocks) {
        IntrinsicHolderTagsProvider.IntrinsicTagAppender<Item> builder = this.tag(tag);
        for (Block block : blocks) {
            builder.add(block.asItem());
        }
    }

    protected void addTag(TagKey<Item> tag, ImmutableList<Supplier<Item>> list) {
        IntrinsicHolderTagsProvider.IntrinsicTagAppender<Item> builder = this.tag(tag);
        for (Supplier<Item> item : list) {
            builder.add(item.get());
        }
    }
}
