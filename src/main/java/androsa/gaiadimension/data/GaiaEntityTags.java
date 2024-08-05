package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaEntityTagsProvider;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.registry.values.GaiaTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GaiaEntityTags extends GaiaEntityTagsProvider {
    public GaiaEntityTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, GaiaDimensionMod.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addTag(GaiaTags.Entities.GAIAN, ImmutableList.of(ModEntities.ARCHAIC_WARRIOR, ModEntities.LESSER_SHOCKSHOOTER, ModEntities.LESSER_SPITFIRE));
        addTag(GaiaTags.Entities.CORRUPT, ImmutableList.of(ModEntities.CORRUPT_SAPPER, ModEntities.CONTORTED_NAGA));
        tag(GaiaTags.Entities.CORRUPTION_IMMUNE).addTags(GaiaTags.Entities.CORRUPT);
    }
}
