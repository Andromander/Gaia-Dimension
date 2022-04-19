package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class GaiaBlockTagsProvider extends BlockTagsProvider {

    public GaiaBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, GaiaDimensionMod.MODID, existingFileHelper);
    }

    protected void addTag(TagKey<Block> tag, ImmutableList<Supplier<? extends Block>> list) {
        TagsProvider.TagAppender<Block> builder = this.tag(tag);
        for (Supplier<? extends Block> block : list) {
            builder.add(block.get());
        }
    }
}
