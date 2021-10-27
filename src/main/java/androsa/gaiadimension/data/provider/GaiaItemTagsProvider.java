package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class GaiaItemTagsProvider extends ItemTagsProvider {

    public GaiaItemTagsProvider(DataGenerator generatorIn, BlockTagsProvider provider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, provider, GaiaDimensionMod.MODID, existingFileHelper);
    }

    protected void addTagFromBlock(Tag.Named<Item> tag, Block... blocks) {
        TagAppender<Item> builder = this.tag(tag);
        for (Block block : blocks) {
            builder.add(block.asItem());
        }
    }

    protected void addTag(Tag.Named<Item> tag, ImmutableList<Supplier<Item>> list) {
        TagAppender<Item> builder = this.tag(tag);
        for (Supplier<Item> item : list) {
            builder.add(item.get());
        }
    }
}
