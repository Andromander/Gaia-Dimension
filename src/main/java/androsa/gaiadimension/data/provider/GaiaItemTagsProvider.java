package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class GaiaItemTagsProvider extends ItemTagsProvider {

    public GaiaItemTagsProvider(DataGenerator generatorIn, BlockTagsProvider provider, ExistingFileHelper existingFileHelper) {
        super(generatorIn, provider, GaiaDimensionMod.MODID, existingFileHelper);
    }

    protected void addTag(ITag.INamedTag<Item> tag, ImmutableList<Supplier<Item>> list) {
        Builder<Item> builder = this.tag(tag);
        for (Supplier<Item> item : list) {
            builder.add(item.get());
        }
    }
}
