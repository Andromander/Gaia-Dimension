package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.values.GaiaTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.tags.TimelineTags;
import net.minecraft.world.timeline.Timeline;

import java.util.concurrent.CompletableFuture;

public class GaiaTimelines extends KeyTagProvider<Timeline> {

    public GaiaTimelines(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, Registries.TIMELINE, provider, GaiaDimensionMod.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        this.tag(GaiaTags.Timelines.IN_GAIA).addTag(TimelineTags.UNIVERSAL);
    }
}
