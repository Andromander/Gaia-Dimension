package androsa.gaiadimension.registry.values;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModFluids;
import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;

import java.util.function.Supplier;

/*
 * You might be looking in here, tempted to simplify those Suppliers. This is...actually the only way these can be registered
 * Compress to method reference, it'll throw an ExceptionInInitializerError
 * Compress to qualifier, it'll throw a NullPointerException
 */
public class GaiaFluidAttributes {

    public static final ResourceLocation mineral_still = makePath("mineral_water", "still");
    public static final ResourceLocation mineral_flow = makePath("mineral_water", "flow");
    public static final ResourceLocation superhot_still = makePath("superhot_magma", "still");
    public static final ResourceLocation superhot_flow = makePath("superhot_magma", "flow");
    public static final ResourceLocation sweet_still = makePath("sweet_muck", "still");
    public static final ResourceLocation sweet_flow = makePath("sweet_muck", "flow");
    public static final ResourceLocation bismuth_still = makePath("liquid_bismuth", "still");
    public static final ResourceLocation bismuth_flow = makePath("liquid_bismuth", "flow");
    public static final ResourceLocation aura_still = makePath("liquid_aura", "still");
    public static final ResourceLocation aura_flow = makePath("liquid_aura", "flow");

    public static final FluidType.Properties mineral_water_attributes =
            FluidType.Properties.create()
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canConvertToSource(true)
                    .canDrown(true)
                    .canExtinguish(true)
                    .canSwim(true)
                    .fallDistanceModifier(0.0F)
                    .supportsBoating(true)
                    .viscosity(750);
    public static final FluidType.Properties superhot_magma_attributes =
            FluidType.Properties.create()
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                    .density(4000)
                    .lightLevel(15)
                    .temperature(2000)
                    .viscosity(4000);
    public static final FluidType.Properties sweet_muck_attrubutes =
            FluidType.Properties.create()
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canConvertToSource(true)
                    .density(1000)
                    .viscosity(750);
    public static final FluidType.Properties liquid_bismuth_attributes =
            FluidType.Properties.create()
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                    .density(2500)
                    .lightLevel(3)
                    .temperature(300)
                    .viscosity(3500);
    public static final FluidType.Properties liquid_aura_attributes =
            FluidType.Properties.create()
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .viscosity(1500);

    public static final Supplier<ForgeFlowingFluid.Properties> mineral_water_properties =
            () -> new ForgeFlowingFluid.Properties(() -> ModFluids.MINERAL_WATER.get(), () -> ModFluids.mineral_water_still.get(), () -> ModFluids.mineral_water_flow.get())
                    .block(ModBlocks.mineral_water)
                    .bucket(ModItems.mineral_water_bucket)
                    .explosionResistance(100.0F);
    public static final Supplier<ForgeFlowingFluid.Properties> superhot_magma_properties =
            () -> new ForgeFlowingFluid.Properties(() -> ModFluids.SUPERHOT_MAGMA.get(), () -> ModFluids.superhot_magma_still.get(), () -> ModFluids.superhot_magma_flow.get())
                    .block(ModBlocks.superhot_magma)
                    .bucket(ModItems.superhot_magma_bucket)
                    .explosionResistance(100.0F)
                    .slopeFindDistance(2)
                    .tickRate(30);
    public static final Supplier<ForgeFlowingFluid.Properties> sweet_muck_properties =
            () -> new ForgeFlowingFluid.Properties(() -> ModFluids.SWEET_MUCK.get(), () -> ModFluids.sweet_muck_still.get(), () -> ModFluids.sweet_muck_flow.get())
                    .block(ModBlocks.sweet_muck)
                    .bucket(ModItems.sweet_muck_bucket)
                    .explosionResistance(100.0F)
                    .slopeFindDistance(2)
                    .tickRate(20);
    public static final Supplier<ForgeFlowingFluid.Properties> liquid_bismuth_properties =
            () -> new ForgeFlowingFluid.Properties(() -> ModFluids.LIQUID_BISMUTH.get(), () -> ModFluids.liquid_bismuth_still.get(), () -> ModFluids.liquid_bismuth_flow.get())
                    .block(ModBlocks.liquid_bismuth)
                    .bucket(ModItems.liquid_bismuth_bucket)
                    .explosionResistance(100.0F)
                    .slopeFindDistance(3)
                    .tickRate(20);
    public static final Supplier<ForgeFlowingFluid.Properties> liquid_aura_properties =
            () -> new ForgeFlowingFluid.Properties(() -> ModFluids.LIQUID_AURA.get(), () -> ModFluids.liquid_aura_still.get(), () -> ModFluids.liquid_aura_flow.get())
                    .block(ModBlocks.liquid_aura)
                    .bucket(ModItems.liquid_aura_bucket)
                    .explosionResistance(100.0F);

    private static ResourceLocation makePath(String name, String suffix) {
        return new ResourceLocation(GaiaDimensionMod.MODID, String.format("block/fluids/%s/%s_%s", name, name, suffix));
    }

    public static void registerFluidInteractions() {
        //Lava + Mineral Water
        FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
                ModFluids.MINERAL_WATER.get(),
                ModBlocks.gaia_cobblestone.get().defaultBlockState()));
        //Lava + Sweet Muck
        FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
                ModFluids.SWEET_MUCK.get(),
                ModBlocks.gaia_cobblestone.get().defaultBlockState()));
        //Lava + Liquid Aura
        FluidInteractionRegistry.addInteraction(NeoForgeMod.LAVA_TYPE.value(), new FluidInteractionRegistry.InteractionInformation(
                ModFluids.LIQUID_AURA.get(),
                ModBlocks.sparkling_rock.get().defaultBlockState()));
        //Superhot Magma + Water
        FluidInteractionRegistry.addInteraction(ModFluids.SUPERHOT_MAGMA.get(), new FluidInteractionRegistry.InteractionInformation(
                NeoForgeMod.WATER_TYPE.value(),
                ModBlocks.gaia_cobblestone.get().defaultBlockState()));
        //Superhot Magma + Mineral Water
        FluidInteractionRegistry.addInteraction(ModFluids.SUPERHOT_MAGMA.get(), new FluidInteractionRegistry.InteractionInformation(
                ModFluids.MINERAL_WATER.get(),
                ModBlocks.primal_mass.get().defaultBlockState()));
        //Superhot Magma + Sweet Muck
        FluidInteractionRegistry.addInteraction(ModFluids.SUPERHOT_MAGMA.get(), new FluidInteractionRegistry.InteractionInformation(
                ModFluids.SWEET_MUCK.get(),
                ModBlocks.primal_mass.get().defaultBlockState()));
        //Superhot Magma + Liquid Aura
        FluidInteractionRegistry.addInteraction(ModFluids.SUPERHOT_MAGMA.get(), new FluidInteractionRegistry.InteractionInformation(
                ModFluids.LIQUID_AURA.get(),
                ModBlocks.aura_block.get().defaultBlockState()));
        //Liquid Bismuth + Water
        FluidInteractionRegistry.addInteraction(ModFluids.LIQUID_BISMUTH.get(), new FluidInteractionRegistry.InteractionInformation(
                NeoForgeMod.WATER_TYPE.value(),
                ModBlocks.impure_rock.get().defaultBlockState()));
        //Liquid Bismuth + Mineral Water
        FluidInteractionRegistry.addInteraction(ModFluids.LIQUID_BISMUTH.get(), new FluidInteractionRegistry.InteractionInformation(
                ModFluids.MINERAL_WATER.get(),
                ModBlocks.active_rock.get().defaultBlockState()));
        //Liquid Bismuth + Sweet Muck
        FluidInteractionRegistry.addInteraction(ModFluids.LIQUID_BISMUTH.get(), new FluidInteractionRegistry.InteractionInformation(
                ModFluids.SWEET_MUCK.get(),
                ModBlocks.active_rock.get().defaultBlockState()));
        //Liquid Bismuth + Liquid Aura
        FluidInteractionRegistry.addInteraction(ModFluids.LIQUID_BISMUTH.get(), new FluidInteractionRegistry.InteractionInformation(
                ModFluids.LIQUID_AURA.get(),
                ModBlocks.bismuth_block.get().defaultBlockState()));
    }
}
