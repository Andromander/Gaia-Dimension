package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.TagsProvider;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class GaiaBlockTagsProvider extends BlockTagsProvider {

    public GaiaBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, GaiaDimensionMod.MODID, existingFileHelper);
    }

    protected void addTag(ITag.INamedTag<Block> tag, ImmutableList<Supplier<? extends Block>> list) {
        TagsProvider.Builder<Block> builder = this.tag(tag);
        for (Supplier<? extends Block> block : list) {
            builder.add(block.get());
        }
    }
}
