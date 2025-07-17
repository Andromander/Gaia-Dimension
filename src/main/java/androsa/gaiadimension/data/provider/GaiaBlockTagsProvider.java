package androsa.gaiadimension.data.provider;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class GaiaBlockTagsProvider extends BlockTagsProvider {

    public GaiaBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid) {
        super(output, provider, modid);
    }

    protected void addTag(TagKey<Block> tag, ImmutableList<Supplier<? extends Block>> list) {
        TagAppender<Block, Block> builder = this.tag(tag);
        for (Supplier<? extends Block> block : list) {
            builder.add(block.get());
        }
    }
}
