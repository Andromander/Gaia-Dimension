package androsa.gaiadimension.registry;

import androsa.gaiadimension.fluids.*;
import net.minecraft.fluid.FlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class ModFluids {

    public static final FlowingFluid mineral_water_still = RegistryHelper.registerFluid("mineral_water_still", new ForgeFlowingFluid.Source(GaiaFluidAttributes.mineral_water_properties));
    public static final FlowingFluid mineral_water_flow = RegistryHelper.registerFluid("mineral_water_flow", new ForgeFlowingFluid.Flowing(GaiaFluidAttributes.mineral_water_properties));
    public static final FlowingFluid superhot_magma_still = RegistryHelper.registerFluid("superhot_magma_still", new SuperhotMagmaFluid.Source(GaiaFluidAttributes.superhot_magma_properties));
    public static final FlowingFluid superhot_magma_flow = RegistryHelper.registerFluid("superhot_magma_flow", new SuperhotMagmaFluid.Flowing(GaiaFluidAttributes.superhot_magma_properties));
    public static final FlowingFluid sweet_muck_still = RegistryHelper.registerFluid("sweet_muck_still", new SweetMuckFluid.Source(GaiaFluidAttributes.sweet_muck_properties));
    public static final FlowingFluid sweet_muck_flow = RegistryHelper.registerFluid("sweet_muck_flow", new SweetMuckFluid.Flowing(GaiaFluidAttributes.sweet_muck_properties));
    public static final FlowingFluid liquid_bismuth_still = RegistryHelper.registerFluid("liquid_bismuth_still", new LiquidBismuthFluid.Source(GaiaFluidAttributes.liquid_bismuth_properties));
    public static final FlowingFluid liquid_bismuth_flow = RegistryHelper.registerFluid("liquid_bismuth_flow", new LiquidBismuthFluid.Flowing(GaiaFluidAttributes.liquid_bismuth_properties));
    public static final FlowingFluid liquid_aura_still = RegistryHelper.registerFluid("liquid_aura_still", new ForgeFlowingFluid.Source(GaiaFluidAttributes.liquid_aura_properties));
    public static final FlowingFluid liquid_aura_flow = RegistryHelper.registerFluid("liquid_aura_flow", new ForgeFlowingFluid.Flowing(GaiaFluidAttributes.liquid_aura_properties));
}