package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class GaiaFluidTagsProvider extends FluidTagsProvider {

    public GaiaFluidTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, GaiaDimensionMod.MODID, existingFileHelper);
    }

    protected void addTag(ITag.INamedTag<Fluid> tag, ImmutableList<Supplier<FlowingFluid>> list) {
        Builder<Fluid> builder = this.getOrCreateBuilder(tag);
        for (Supplier<FlowingFluid> fluid : list) {
            builder.add(fluid.get());
        }
    }
}
