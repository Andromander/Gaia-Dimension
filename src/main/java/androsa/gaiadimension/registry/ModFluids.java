package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.fluids.*;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFluids {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, GaiaDimensionMod.MODID);

    public static final RegistryObject<FlowingFluid> mineral_water_still = FLUIDS.register("mineral_water_still",
			() -> new ForgeFlowingFluid.Source(GaiaFluidAttributes.mineral_water_properties));
    public static final RegistryObject<FlowingFluid> mineral_water_flow = FLUIDS.register("mineral_water_flow",
			() -> new ForgeFlowingFluid.Flowing(GaiaFluidAttributes.mineral_water_properties));
    public static final RegistryObject<FlowingFluid> superhot_magma_still = FLUIDS.register("superhot_magma_still",
			() -> new SuperhotMagmaFluid.Source(GaiaFluidAttributes.superhot_magma_properties));
    public static final RegistryObject<FlowingFluid> superhot_magma_flow = FLUIDS.register("superhot_magma_flow",
			() -> new SuperhotMagmaFluid.Flowing(GaiaFluidAttributes.superhot_magma_properties));
    public static final RegistryObject<FlowingFluid> sweet_muck_still = FLUIDS.register("sweet_muck_still",
			() -> new ForgeFlowingFluid.Source(GaiaFluidAttributes.sweet_muck_properties));
    public static final RegistryObject<FlowingFluid> sweet_muck_flow = FLUIDS.register("sweet_muck_flow",
			() -> new ForgeFlowingFluid.Flowing(GaiaFluidAttributes.sweet_muck_properties));
    public static final RegistryObject<FlowingFluid> liquid_bismuth_still = FLUIDS.register("liquid_bismuth_still",
			() -> new LiquidBismuthFluid.Source(GaiaFluidAttributes.liquid_bismuth_properties));
    public static final RegistryObject<FlowingFluid> liquid_bismuth_flow = FLUIDS.register("liquid_bismuth_flow",
			() -> new LiquidBismuthFluid.Flowing(GaiaFluidAttributes.liquid_bismuth_properties));
    public static final RegistryObject<FlowingFluid> liquid_aura_still = FLUIDS.register("liquid_aura_still",
			() -> new ForgeFlowingFluid.Source(GaiaFluidAttributes.liquid_aura_properties));
    public static final RegistryObject<FlowingFluid> liquid_aura_flow = FLUIDS.register("liquid_aura_flow",
			() -> new ForgeFlowingFluid.Flowing(GaiaFluidAttributes.liquid_aura_properties));
}