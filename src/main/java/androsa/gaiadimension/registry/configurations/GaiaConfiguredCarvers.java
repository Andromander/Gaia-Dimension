package androsa.gaiadimension.registry.configurations;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.registry.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.BiasedToBottomHeight;

import java.util.Map;

public final class GaiaConfiguredCarvers extends GaiaBiomeFeatures {

    //ResourceKeys
    public static final ResourceKey<ConfiguredWorldCarver<?>> CRYSTAL_CAVES = makeKey("crystal_caves");
    public static final ResourceKey<ConfiguredWorldCarver<?>> CHASMS = makeKey("chasms");

    //Holders
    public static final Holder<ConfiguredWorldCarver<CaveCarverConfiguration>> crystal_caves = registerCarver("crystal_caves", ModWorldgen.CRYSTAL_CAVES.get().configured(new CaveCarverConfiguration(
            0.15F,
            BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(256), 8),
            ConstantFloat.of(0.5F),
            VerticalAnchor.aboveBottom(10),
            CarverDebugSettings.DEFAULT,
            ConstantFloat.of(1.0F),
            ConstantFloat.of(1.0F),
            ConstantFloat.of(-0.7F))));
    public static final Holder<ConfiguredWorldCarver<CaveCarverConfiguration>> chasms = registerCarver("chasms", ModWorldgen.CHASMS.get().configured(new CaveCarverConfiguration(
            0.03F,
            BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(24), 8),
            ConstantFloat.of(0.5F),
            VerticalAnchor.aboveBottom(10),
            CarverDebugSettings.DEFAULT,
            ConstantFloat.of(1.0F),
            ConstantFloat.of(1.0F),
            ConstantFloat.of(-0.7F))));

    private static ResourceKey<ConfiguredWorldCarver<?>> makeKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, new ResourceLocation(GaiaDimensionMod.MODID, name));
    }

    private static <WC extends CarverConfiguration> Holder<ConfiguredWorldCarver<WC>> registerCarver(String name, ConfiguredWorldCarver<WC> carver) {
        RegistryHelper.CONFIGURED_WORLD_CARVERS.put(carver, name);
        return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_CARVER, new ResourceLocation(GaiaDimensionMod.MODID, name).toString(), carver);
    }

    public static void registerCarvers(Registry<ConfiguredWorldCarver<?>> registry) {
        for (Map.Entry<ConfiguredWorldCarver<?>, String> entry : RegistryHelper.CONFIGURED_WORLD_CARVERS.entrySet()) {
            Registry.register(registry, new ResourceLocation(GaiaDimensionMod.MODID, entry.getValue()), entry.getKey());
        }
    }

    public static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> context) {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        context.register(CRYSTAL_CAVES, ModWorldgen.CHASMS.get().configured(new CaveCarverConfiguration(
                0.03F,
                BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(24), 8),
                ConstantFloat.of(0.5F),
                VerticalAnchor.aboveBottom(10),
                blocks.getOrThrow(GaiaTags.Blocks.GAIA_CARVER_REPLACEABLES),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(-0.7F))));
        context.register(CHASMS, ModWorldgen.CHASMS.get().configured(new CaveCarverConfiguration(
                0.03F,
                BiasedToBottomHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(24), 8),
                ConstantFloat.of(0.5F),
                VerticalAnchor.aboveBottom(10),
                blocks.getOrThrow(GaiaTags.Blocks.GAIA_CARVER_REPLACEABLES),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(1.0F),
                ConstantFloat.of(-0.7F))));
    }
}
