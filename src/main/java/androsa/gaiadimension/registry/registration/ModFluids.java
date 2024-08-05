package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.client.ClientEvents;
import androsa.gaiadimension.fluids.LiquidBismuthFluid;
import androsa.gaiadimension.fluids.SuperhotMagmaFluid;
import androsa.gaiadimension.registry.values.GaiaFluidAttributes;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModFluids {
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, GaiaDimensionMod.MODID);
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, GaiaDimensionMod.MODID);

	//FluidTypes
	public static final DeferredHolder<FluidType, FluidType> MINERAL_WATER = FLUID_TYPES.register("mineral_water",
			() -> new FluidType(GaiaFluidAttributes.mineral_water_attributes));
	public static final DeferredHolder<FluidType, FluidType> SUPERHOT_MAGMA = FLUID_TYPES.register("superhot_magma",
			() -> new FluidType(GaiaFluidAttributes.superhot_magma_attributes));
	public static final DeferredHolder<FluidType, FluidType> SWEET_MUCK = FLUID_TYPES.register("sweet_muck",
			() -> new FluidType(GaiaFluidAttributes.sweet_muck_attrubutes));
	public static final DeferredHolder<FluidType, FluidType> LIQUID_BISMUTH = FLUID_TYPES.register("liquid_bismuth",
			() -> new FluidType(GaiaFluidAttributes.liquid_bismuth_attributes));
	public static final DeferredHolder<FluidType, FluidType> LIQUID_AURA = FLUID_TYPES.register("liquid_aura",
			() -> new FluidType(GaiaFluidAttributes.liquid_aura_attributes));

	//Fluids
    public static final DeferredHolder<Fluid, FlowingFluid> mineral_water_still = FLUIDS.register("mineral_water_still",
			() -> new BaseFlowingFluid.Source(GaiaFluidAttributes.mineral_water_properties.get()));
    public static final DeferredHolder<Fluid, FlowingFluid> mineral_water_flow = FLUIDS.register("mineral_water_flow",
			() -> new BaseFlowingFluid.Flowing(GaiaFluidAttributes.mineral_water_properties.get()));
    public static final DeferredHolder<Fluid, FlowingFluid> superhot_magma_still = FLUIDS.register("superhot_magma_still",
			() -> new SuperhotMagmaFluid.Source(GaiaFluidAttributes.superhot_magma_properties.get()));
    public static final DeferredHolder<Fluid, FlowingFluid> superhot_magma_flow = FLUIDS.register("superhot_magma_flow",
			() -> new SuperhotMagmaFluid.Flowing(GaiaFluidAttributes.superhot_magma_properties.get()));
    public static final DeferredHolder<Fluid, FlowingFluid> sweet_muck_still = FLUIDS.register("sweet_muck_still",
			() -> new BaseFlowingFluid.Source(GaiaFluidAttributes.sweet_muck_properties.get()));
    public static final DeferredHolder<Fluid, FlowingFluid> sweet_muck_flow = FLUIDS.register("sweet_muck_flow",
			() -> new BaseFlowingFluid.Flowing(GaiaFluidAttributes.sweet_muck_properties.get()));
    public static final DeferredHolder<Fluid, FlowingFluid> liquid_bismuth_still = FLUIDS.register("liquid_bismuth_still",
			() -> new LiquidBismuthFluid.Source(GaiaFluidAttributes.liquid_bismuth_properties.get()));
    public static final DeferredHolder<Fluid, FlowingFluid> liquid_bismuth_flow = FLUIDS.register("liquid_bismuth_flow",
			() -> new LiquidBismuthFluid.Flowing(GaiaFluidAttributes.liquid_bismuth_properties.get()));
    public static final DeferredHolder<Fluid, FlowingFluid> liquid_aura_still = FLUIDS.register("liquid_aura_still",
			() -> new BaseFlowingFluid.Source(GaiaFluidAttributes.liquid_aura_properties.get()));
    public static final DeferredHolder<Fluid, FlowingFluid> liquid_aura_flow = FLUIDS.register("liquid_aura_flow",
			() -> new BaseFlowingFluid.Flowing(GaiaFluidAttributes.liquid_aura_properties.get()));
}