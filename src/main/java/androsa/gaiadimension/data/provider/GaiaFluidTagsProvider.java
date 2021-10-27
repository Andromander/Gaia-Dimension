package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.Tag;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class GaiaFluidTagsProvider extends FluidTagsProvider {

    public GaiaFluidTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, GaiaDimensionMod.MODID, existingFileHelper);
    }

    protected void addTag(Tag.Named<Fluid> tag, ImmutableList<Supplier<FlowingFluid>> list) {
        TagAppender<Fluid> builder = this.tag(tag);
        for (Supplier<FlowingFluid> fluid : list) {
            builder.add(fluid.get());
        }
    }
}
