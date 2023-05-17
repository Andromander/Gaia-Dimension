package androsa.gaiadimension.registry.bootstrap;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.values.GaiaTags;
import androsa.gaiadimension.registry.registration.ModWorldgen;
import androsa.gaiadimension.registry.values.GaiaBiomeFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public final class GaiaWorldCarvers extends GaiaBiomeFeatures {

    public static final ResourceKey<ConfiguredWorldCarver<?>> CRYSTAL_CAVES = makeKey("crystal_caves");
    public static final ResourceKey<ConfiguredWorldCarver<?>> CHASMS = makeKey("chasms");

    private static ResourceKey<ConfiguredWorldCarver<?>> makeKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, new ResourceLocation(GaiaDimensionMod.MODID, name));
    }

    public static void init(BootstapContext<ConfiguredWorldCarver<?>> context) {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        context.register(CRYSTAL_CAVES, ModWorldgen.CRYSTAL_CAVES.get().configured(new CaveCarverConfiguration(
                0.03F,
                UniformHeight.of(VerticalAnchor.aboveBottom(10), VerticalAnchor.absolute(120)),
                ConstantFloat.of(0.5F),
                VerticalAnchor.aboveBottom(10),
                blocks.getOrThrow(GaiaTags.Blocks.GAIA_CARVER_REPLACEABLES),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(-0.7F))));
        context.register(CHASMS, ModWorldgen.CHASMS.get().configured(new CaveCarverConfiguration(
                0.03F,
                BiasedToBottomHeight.of(VerticalAnchor.aboveBottom(5), VerticalAnchor.absolute(24), 8),
                ConstantFloat.of(0.5F),
                VerticalAnchor.aboveBottom(10),
                blocks.getOrThrow(GaiaTags.Blocks.GAIA_CARVER_REPLACEABLES),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(-0.7F))));
    }
}
