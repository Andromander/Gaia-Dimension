package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.data.provider.GaiaFluidTagsProvider;
import androsa.gaiadimension.registry.registration.ModFluids;
import androsa.gaiadimension.registry.values.GaiaTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.FluidTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GaiaFluidTags extends GaiaFluidTagsProvider {

    public GaiaFluidTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper existingFileHelper) {
        super(output, provider, GaiaDimensionMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addTag(GaiaTags.Fluids.MINERAL_WATER, ImmutableList.of(ModFluids.mineral_water_still, ModFluids.mineral_water_flow));
        addTag(GaiaTags.Fluids.SUPERHOT_MAGMA, ImmutableList.of(ModFluids.superhot_magma_still, ModFluids.superhot_magma_flow));
        addTag(GaiaTags.Fluids.SWEET_MUCK, ImmutableList.of(ModFluids.sweet_muck_still, ModFluids.sweet_muck_flow));
        addTag(GaiaTags.Fluids.LIQUID_BISMUTH, ImmutableList.of(ModFluids.liquid_bismuth_still, ModFluids.liquid_bismuth_flow));
        addTag(GaiaTags.Fluids.LIQUID_AURA, ImmutableList.of(ModFluids.liquid_aura_still, ModFluids.liquid_aura_flow));

        addTag(FluidTags.WATER, ImmutableList.of(
                ModFluids.mineral_water_still, ModFluids.mineral_water_flow,
                ModFluids.sweet_muck_still, ModFluids.sweet_muck_flow,
                ModFluids.liquid_aura_still, ModFluids.liquid_aura_flow
        ));
        addTag(FluidTags.LAVA, ImmutableList.of(
                ModFluids.superhot_magma_still, ModFluids.superhot_magma_flow,
                ModFluids.liquid_bismuth_still, ModFluids.liquid_bismuth_flow
        ));
    }
}
