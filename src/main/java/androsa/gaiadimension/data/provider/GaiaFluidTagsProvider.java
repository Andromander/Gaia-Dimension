package androsa.gaiadimension.data.provider;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class GaiaFluidTagsProvider extends FluidTagsProvider {

    public GaiaFluidTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, String modid) {
        super(output, provider, modid);
    }

    protected void addTag(TagKey<Fluid> tag, ImmutableList<Supplier<FlowingFluid>> list) {
        IntrinsicTagAppender<Fluid> builder = this.tag(tag);
        for (Supplier<FlowingFluid> fluid : list) {
            builder.add(fluid.get());
        }
    }
}
