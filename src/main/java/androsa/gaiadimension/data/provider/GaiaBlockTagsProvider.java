package androsa.gaiadimension.data.provider;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class GaiaBlockTagsProvider extends BlockTagsProvider {

    public GaiaBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid, ExistingFileHelper existingFileHelper) {
        super(output, provider, modid, existingFileHelper);
    }

    protected void addTag(TagKey<Block> tag, ImmutableList<Supplier<? extends Block>> list) {
        IntrinsicHolderTagsProvider.IntrinsicTagAppender<Block> builder = this.tag(tag);
        for (Supplier<? extends Block> block : list) {
            builder.add(block.get());
        }
    }
}
