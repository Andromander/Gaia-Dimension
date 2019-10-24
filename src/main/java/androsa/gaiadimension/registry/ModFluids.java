package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.fluids.*;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {

    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, GaiaDimensionMod.MODID);

    public static final FlowingFluid mineral_water_s = new ForgeFlowingFluid.Source(GaiaFluidAttributes.mineral_water_properties);
    public static final FlowingFluid mineral_water_f = new ForgeFlowingFluid.Flowing(GaiaFluidAttributes.mineral_water_properties);
    public static final FlowingFluid superhot_magma_s = new SuperhotMagmaFluid.Source(GaiaFluidAttributes.superhot_magma_properties);
    public static final FlowingFluid superhot_magma_f = new SuperhotMagmaFluid.Flowing(GaiaFluidAttributes.superhot_magma_properties);
    public static final FlowingFluid sweet_muck_s = new SweetMuckFluid.Source(GaiaFluidAttributes.sweet_muck_properties);
    public static final FlowingFluid sweet_muck_f = new SweetMuckFluid.Flowing(GaiaFluidAttributes.sweet_muck_properties);
    public static final FlowingFluid liquid_bismuth_s = new LiquidBismuthFluid.Source(GaiaFluidAttributes.liquid_bismuth_properties);
    public static final FlowingFluid liquid_bismuth_f = new LiquidBismuthFluid.Flowing(GaiaFluidAttributes.liquid_bismuth_properties);
    public static final FlowingFluid liquid_aura_s = new ForgeFlowingFluid.Source(GaiaFluidAttributes.liquid_aura_properties);
    public static final FlowingFluid liquid_aura_f = new ForgeFlowingFluid.Flowing(GaiaFluidAttributes.liquid_aura_properties);

    public static final RegistryObject<FlowingFluid> mineral_water_still = FLUIDS.register("mineral_water_still", () -> mineral_water_s);
    public static final RegistryObject<FlowingFluid> mineral_water_flow = FLUIDS.register("mineral_water_flow", () -> mineral_water_f);
    public static final RegistryObject<FlowingFluid> superhot_magma_still = FLUIDS.register("superhot_magma_still", () -> superhot_magma_s);
    public static final RegistryObject<FlowingFluid> superhot_magma_flow = FLUIDS.register("superhot_magma_flow", () -> superhot_magma_f);
    public static final RegistryObject<FlowingFluid> sweet_muck_still = FLUIDS.register("sweet_muck_still", () -> sweet_muck_s);
    public static final RegistryObject<FlowingFluid> sweet_muck_flow = FLUIDS.register("sweet_muck_flow", () -> sweet_muck_f);
    public static final RegistryObject<FlowingFluid> liquid_bismuth_still = FLUIDS.register("liquid_bismuth_still", () -> liquid_bismuth_s);
    public static final RegistryObject<FlowingFluid> liquid_bismuth_flow = FLUIDS.register("liquid_bismuth_flow", () -> liquid_bismuth_f);
    public static final RegistryObject<FlowingFluid> liquid_aura_still = FLUIDS.register("liquid_aura_still", () -> liquid_aura_s);
    public static final RegistryObject<FlowingFluid> liquid_aura_flow = FLUIDS.register("liquid_aura_flow", () -> liquid_aura_f);
}