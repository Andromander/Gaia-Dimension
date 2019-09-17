package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class GaiaFluidAttributes {

    private static final String mineralDir = "fluids/mineralwater/mineral_water_";
    private static final String superhotDir = "fluids/superhotmagma/superhot_magma_";
    private static final String sweetDir = "fluids/sweetmuck/sweet_muck_";
    private static final String bismuthDir = "fluids/liquidbismuth/liquid_bismuth_";
    private static final String auraDir = "fluids/liquidaura/liquid_aura_";

    public static final FluidAttributes.Builder mineral_water_attributes =
            FluidAttributes.builder(new ResourceLocation(GaiaDimensionMod.MODID, mineralDir + "still"), new ResourceLocation(GaiaDimensionMod.MODID, mineralDir + "flow"))
                    .color(0xCEB0C0FF)
                    .overlay(new ResourceLocation("block/water_overlay"))
                    .viscosity(750);
    public static final FluidAttributes.Builder superhot_magma_attributes =
            FluidAttributes.builder(new ResourceLocation(GaiaDimensionMod.MODID, superhotDir + "still"), new ResourceLocation(GaiaDimensionMod.MODID, superhotDir + "flow"))
                    .color(0xFF00FFFF)
                    .density(4000)
                    .luminosity(15)
                    .temperature(2000)
                    .viscosity(4000);
    public static final FluidAttributes.Builder sweet_muck_attrubutes =
            FluidAttributes.builder(new ResourceLocation(GaiaDimensionMod.MODID, sweetDir + "still"), new ResourceLocation(GaiaDimensionMod.MODID, sweetDir + "flow"))
                    .color(0xFF800080)
                    .density(1000)
                    .overlay(new ResourceLocation(GaiaDimensionMod.MODID, sweetDir + "overlay"))
                    .viscosity(750);
    public static final FluidAttributes.Builder liquid_bismuth_attributes =
            FluidAttributes.builder(new ResourceLocation(GaiaDimensionMod.MODID, bismuthDir + "still"), new ResourceLocation(GaiaDimensionMod.MODID, bismuthDir + "flow"))
                    .density(2500)
                    .luminosity(3)
                    .overlay(new ResourceLocation(GaiaDimensionMod.MODID, bismuthDir + "overlay"))
                    .temperature(300)
                    .viscosity(3500);
    public static final FluidAttributes.Builder liquid_aura_attributes =
            FluidAttributes.builder(new ResourceLocation(GaiaDimensionMod.MODID, auraDir + "still"), new ResourceLocation(GaiaDimensionMod.MODID, auraDir + "flow"))
                    .color(0xCEB0C0FF)
                    .overlay(new ResourceLocation(GaiaDimensionMod.MODID, auraDir + "overlay"))
                    .viscosity(1500);

    public static final ForgeFlowingFluid.Properties mineral_water_properties =
            new ForgeFlowingFluid.Properties(() -> ModFluids.mineral_water_still, () -> ModFluids.mineral_water_flow, mineral_water_attributes)
                    .block(() -> ModBlocks.mineral_water)
                    .bucket(() -> ModItems.mineral_water_bucket)
                    .canMultiply()
                    .explosionResistance(100.0F);
    public static final ForgeFlowingFluid.Properties superhot_magma_properties =
            new ForgeFlowingFluid.Properties(() -> ModFluids.superhot_magma_still, () -> ModFluids.superhot_magma_flow, superhot_magma_attributes)
                    .block(() -> ModBlocks.superhot_magma)
                    .bucket(() -> ModItems.superhot_magma_bucket)
                    .explosionResistance(100.0F)
                    .renderLayer(BlockRenderLayer.SOLID)
                    .slopeFindDistance(2);
    public static final ForgeFlowingFluid.Properties sweet_muck_properties =
            new ForgeFlowingFluid.Properties(() -> ModFluids.sweet_muck_still, () -> ModFluids.sweet_muck_flow, sweet_muck_attrubutes)
                    .block(() -> ModBlocks.sweet_muck)
                    .bucket(() -> ModItems.sweet_muck_bucket)
                    .canMultiply()
                    .explosionResistance(100.0F)
                    .slopeFindDistance(2);
    public static final ForgeFlowingFluid.Properties liquid_bismuth_properties =
            new ForgeFlowingFluid.Properties(() -> ModFluids.liquid_bismuth_still, () -> ModFluids.liquid_bismuth_flow, liquid_bismuth_attributes)
                    .block(() -> ModBlocks.liquid_bismuth)
                    .bucket(() -> ModItems.liquid_bismuth_bucket)
                    .explosionResistance(100.0F)
                    .renderLayer(BlockRenderLayer.SOLID)
                    .slopeFindDistance(3);
    public static final ForgeFlowingFluid.Properties liquid_aura_properties =
            new ForgeFlowingFluid.Properties(() -> ModFluids.liquid_aura_still, () -> ModFluids.liquid_aura_flow, liquid_aura_attributes)
                    .block(() -> ModBlocks.liquid_aura)
                    .bucket(() -> ModItems.liquid_aura_bucket)
                    .explosionResistance(100.0F)
                    .renderLayer(BlockRenderLayer.SOLID);
}
