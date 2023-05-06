package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.client.ClientEvents;
import androsa.gaiadimension.fluids.LiquidBismuthFluid;
import androsa.gaiadimension.fluids.SuperhotMagmaFluid;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFluids {
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, GaiaDimensionMod.MODID);
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, GaiaDimensionMod.MODID);

	//FluidTypes
	public static final RegistryObject<FluidType> MINERAL_WATER = FLUID_TYPES.register("mineral_water",
			makeFluidType(GaiaFluidAttributes.mineral_water_attributes, GaiaFluidAttributes.mineralDir, true, new Vector3f(0.6875F, 0.75F, 1.0F), null));
	public static final RegistryObject<FluidType> SUPERHOT_MAGMA = FLUID_TYPES.register("superhot_magma",
			makeFluidType(GaiaFluidAttributes.superhot_magma_attributes, GaiaFluidAttributes.superhotDir, false, new Vector3f(0.0F, 1.0F, 1.0F), null));
	public static final RegistryObject<FluidType> SWEET_MUCK = FLUID_TYPES.register("sweet_muck",
			makeFluidType(GaiaFluidAttributes.sweet_muck_attrubutes, GaiaFluidAttributes.sweetDir, true, new Vector3f(0.5F, 0.0F, 0.5F), null));
	public static final RegistryObject<FluidType> LIQUID_BISMUTH = FLUID_TYPES.register("liquid_bismuth",
			makeFluidType(GaiaFluidAttributes.liquid_bismuth_attributes, GaiaFluidAttributes.bismuthDir, false, new Vector3f(0.5F, 0.5F, 0.5F), ClientEvents::getBismuthColor));
	public static final RegistryObject<FluidType> LIQUID_AURA = FLUID_TYPES.register("liquid_aura",
			makeFluidType(GaiaFluidAttributes.liquid_aura_attributes, GaiaFluidAttributes.auraDir, true, new Vector3f(1.0F, 1.0F, 1.0F), ClientEvents::getAuraColor));

	//Fluids
    public static final RegistryObject<FlowingFluid> mineral_water_still = FLUIDS.register("mineral_water_still",
			() -> new ForgeFlowingFluid.Source(GaiaFluidAttributes.mineral_water_properties.get()));
    public static final RegistryObject<FlowingFluid> mineral_water_flow = FLUIDS.register("mineral_water_flow",
			() -> new ForgeFlowingFluid.Flowing(GaiaFluidAttributes.mineral_water_properties.get()));
    public static final RegistryObject<FlowingFluid> superhot_magma_still = FLUIDS.register("superhot_magma_still",
			() -> new SuperhotMagmaFluid.Source(GaiaFluidAttributes.superhot_magma_properties.get()));
    public static final RegistryObject<FlowingFluid> superhot_magma_flow = FLUIDS.register("superhot_magma_flow",
			() -> new SuperhotMagmaFluid.Flowing(GaiaFluidAttributes.superhot_magma_properties.get()));
    public static final RegistryObject<FlowingFluid> sweet_muck_still = FLUIDS.register("sweet_muck_still",
			() -> new ForgeFlowingFluid.Source(GaiaFluidAttributes.sweet_muck_properties.get()));
    public static final RegistryObject<FlowingFluid> sweet_muck_flow = FLUIDS.register("sweet_muck_flow",
			() -> new ForgeFlowingFluid.Flowing(GaiaFluidAttributes.sweet_muck_properties.get()));
    public static final RegistryObject<FlowingFluid> liquid_bismuth_still = FLUIDS.register("liquid_bismuth_still",
			() -> new LiquidBismuthFluid.Source(GaiaFluidAttributes.liquid_bismuth_properties.get()));
    public static final RegistryObject<FlowingFluid> liquid_bismuth_flow = FLUIDS.register("liquid_bismuth_flow",
			() -> new LiquidBismuthFluid.Flowing(GaiaFluidAttributes.liquid_bismuth_properties.get()));
    public static final RegistryObject<FlowingFluid> liquid_aura_still = FLUIDS.register("liquid_aura_still",
			() -> new ForgeFlowingFluid.Source(GaiaFluidAttributes.liquid_aura_properties.get()));
    public static final RegistryObject<FlowingFluid> liquid_aura_flow = FLUIDS.register("liquid_aura_flow",
			() -> new ForgeFlowingFluid.Flowing(GaiaFluidAttributes.liquid_aura_properties.get()));

	private static Supplier<FluidType> makeFluidType(FluidType.Properties props, String directory, boolean overlay, Vector3f fog, Function<BlockPos, Integer> color) {
		return () -> new FluidType(props) {
			@Override
			public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
				consumer.accept(new IClientFluidTypeExtensions() {
					@Override
					public ResourceLocation getStillTexture() {
						return new ResourceLocation(GaiaDimensionMod.MODID, directory + "_still");
					}

					@Override
					public ResourceLocation getFlowingTexture() {
						return new ResourceLocation(GaiaDimensionMod.MODID, directory + "_flowing");
					}

					@Override
					public @Nullable ResourceLocation getOverlayTexture() {
						return overlay ? new ResourceLocation("block/water_overlay") : null;
					}

					@Override
					public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
						return color != null ? color.apply(pos) | 0xFF000000 : this.getTintColor();
					}

					@Override
					public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
						return fog;
					}
				});
			}
		};
	}
}