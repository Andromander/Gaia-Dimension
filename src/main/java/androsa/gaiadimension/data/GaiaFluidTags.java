package androsa.gaiadimension.data;

import androsa.gaiadimension.data.provider.GaiaFluidTagsProvider;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModFluids;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GaiaFluidTags extends GaiaFluidTagsProvider {

    public GaiaFluidTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, existingFileHelper);
    }

    @Override
    protected void addTags() {
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
