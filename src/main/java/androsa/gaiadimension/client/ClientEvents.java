package androsa.gaiadimension.client;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.screen.*;
import androsa.gaiadimension.item.inventory.GemPouchScreen;
import androsa.gaiadimension.particle.*;
import androsa.gaiadimension.registry.registration.*;
import androsa.gaiadimension.registry.values.GaiaFluidAttributes;
import net.minecraft.client.Camera;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Function;
import java.util.function.Supplier;

@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD, modid = GaiaDimensionMod.MODID)
public class ClientEvents {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, worldIn, pos, tintIndex) ->
                        worldIn != null && pos != null ?
                                /*worldIn.getColor(pos, (biome, x, z) -> biome instanceof BaseGaiaBiome ?*/ BiomeColors.getAverageGrassColor(worldIn, pos) : 0xF2A3B4,
                ModBlocks.glitter_grass.get(),
                ModBlocks.crystal_growth.get());

        event.register((state, worldIn, pos, tintIndex) ->
                        worldIn != null && pos != null ?
                                /*worldIn.getColor(pos, (biome, x, z) -> biome instanceof BaseGaiaBiome ?*/ BiomeColors.getAverageGrassColor(worldIn, pos) : 0x606060,
                ModBlocks.murky_grass.get());

        event.register((state, worldIn, pos, tintIndex) ->
                        worldIn != null && pos != null ?
                                /*worldIn.getColor(pos, (biome, x, z) -> biome instanceof BaseGaiaBiome ?*/ BiomeColors.getAverageGrassColor(worldIn, pos) : 0xA0A0A0,
                ModBlocks.soft_grass.get());

        event.register((state, worldIn, pos, tintIndex) -> {
            if (worldIn != null && pos != null) {
                return getAuraColor(pos);
            } else {
                return 0xFFFFFF;
            }

        }, ModBlocks.aura_leaves.get());

        event.register((state, worldIn, pos, tintIndex) -> {
            int hex;

            if (worldIn != null && pos != null) {
                int location = (Math.abs(pos.getX() % 5)) + (Math.abs(pos.getZ() % 5));
                hex = switch (location) {
                    case 0 -> 0xEA500D;
                    case 1 -> 0xFFC24C;
                    case 2 -> 0xC1ED26;
                    case 3 -> 0x67FFB9;
                    case 4 -> 0x265AEf;
                    case 5 -> 0x5C0AD7;
                    case 7 -> 0xC330E8;
                    case 8 -> 0xFF6CAE;
                    default -> 0x5D3883;
                };
            } else {
                hex = 0x1109B7;
            }

            return hex;
        }, ModBlocks.aura_shoot.get());

        event.register((state, worldIn, pos, tintindex) -> worldIn != null && pos != null ? 0x00AA00 : 0xFFFFFF,
                ModBlocks.malachite_guard_spawner.get());
    }

    public static int getBismuthColor(BlockPos pos) {
        int red = (int) ((Mth.cos((float) Math.toRadians(pos.getX() * 4)) + 1F) / 2F * 0xFF);
        int green = (int) ((Mth.cos((float) Math.toRadians(pos.getY() * 8)) + 1F) / 3F * 0xFF);
        int blue = (int) ((Mth.cos((float) Math.toRadians(pos.getZ() * 4)) + 1F) / 2F * 0xFF);

        red = Mth.clamp(red, 20, 170);
        green = Mth.clamp(green, 20, 160);
        blue = Mth.clamp(blue, 20, 200);

        return (red << 16) | (green << 8) | blue;
    }

    public static int getAuraColor(BlockPos pos) {
        int red = (int) ((Mth.cos((float) Math.toRadians((pos.getX() + 100) * 8)) + 1F) / 2F * 0xFF);
        int green = (int) ((Mth.cos((float) Math.toRadians((pos.getY() + 100) * 32)) + 1F) / 2F * 0xFF);
        int blue = (int) ((Mth.cos((float) Math.toRadians((pos.getZ() + 100) * 8)) + 1F) / 2F * 0xFF);

        red = Mth.clamp(red, 150, 256);
        green = Mth.clamp(green, 100, 220);
        blue = Mth.clamp(blue, 150, 256);

        return (red << 16) | (green << 8) | blue;
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> event.getBlockColors().getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, tintIndex),
                ModBlocks.glitter_grass.get(),
                ModBlocks.crystal_growth.get(),
                ModBlocks.murky_grass.get(),
                ModBlocks.aura_shoot.get(),
                ModBlocks.soft_grass.get());
    }

    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent e) {
        e.registerSpriteSet(ModParticles.GEYSER_SMOKE.get(), GeyserSmokeParticle.Factory::new);
        e.registerSpriteSet(ModParticles.RESTRUCTURER_FIRE.get(), RestructurerFireParticle.Factory::new);
        e.registerSpriteSet(ModParticles.PURIFIER_FIRE.get(), PurifierFireParticle.Factory::new);
        e.registerSpriteSet(ModParticles.PORTAL.get(), GaiaPortalParticle.Factory::new);
        e.registerSpriteSet(ModParticles.PYRITE.get(), PyriteParticle.Factory::new);
        e.registerSpecial(ModParticles.ITEM_PEBBLE.get(), new GaiaBreakingParticle.PebbleFactory());
        e.registerSpriteSet(ModParticles.SPAWNER_CORE.get(), SpawnerCoreParticle.Factory::new);
        e.registerSpriteSet(ModParticles.MALACHITE_MAGIC.get(), MalachiteMagicParticle.Factory::new);
    }

    @SubscribeEvent
    public static void registerDimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
        new GaiaSkyRender();
        event.register(ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "gaia"), new GaiaDimensionRenderInfo());
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent e) {
        e.register(ModMenus.AGATE_CRAFTING_TABLE.get(), AgateCraftingScreen::new);
        e.register(ModMenus.GAIA_STONE_FURNACE.get(), GaiaStoneFurnaceScreen::new);
        e.register(ModMenus.GEMSTONE_POUCH.get(), GemPouchScreen::new);
        e.register(ModMenus.SMALL_CRATE.get(), SmallCrateScreen::new);
        e.register(ModMenus.LARGE_CRATE.get(), LargeCrateScreen::new);
        e.register(ModMenus.RESTRUCTURER.get(), RestructurerScreen::new);
        e.register(ModMenus.PURIFIER.get(), PurifierScreen::new);
    }

    public static void registerBlockRenderers() {
        renderFluid(ModFluids.mineral_water_flow);
        renderFluid(ModFluids.mineral_water_still);
        renderFluid(ModFluids.sweet_muck_flow);
        renderFluid(ModFluids.sweet_muck_still);
    }

    private static void renderFluid(Supplier<? extends Fluid> fluid) {
        ItemBlockRenderTypes.setRenderLayer(fluid.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void registerRecipeCategories(RegisterRecipeBookCategoriesEvent e) {
        e.registerRecipeCategoryFinder(ModRecipes.RESTRUCTURING.get(), recipe -> RecipeBookCategories.valueOf("RESTRUCTURING_CATEGORY"));
        e.registerRecipeCategoryFinder(ModRecipes.PURIFYING.get(), recipe -> RecipeBookCategories.valueOf("PURIFYING_CATEGORY"));
    }

    public static void registerClientExtensions(RegisterClientExtensionsEvent e) {
        e.registerFluidType(makeFluidType(GaiaFluidAttributes.mineral_still, GaiaFluidAttributes.mineral_flow, true, null, new Vector3f(0.6875F, 0.75F, 1.0F)),
                ModFluids.MINERAL_WATER.get());
        e.registerFluidType(makeFluidType(GaiaFluidAttributes.superhot_still, GaiaFluidAttributes.superhot_flow, false, null, new Vector3f(0.0F, 1.0F, 1.0F)),
                ModFluids.SUPERHOT_MAGMA.get());
        e.registerFluidType(makeFluidType(GaiaFluidAttributes.sweet_still, GaiaFluidAttributes.sweet_flow, true, null, new Vector3f(0.5F, 0.0F, 0.5F)),
                ModFluids.SWEET_MUCK.get());
        e.registerFluidType(makeFluidType(GaiaFluidAttributes.bismuth_still, GaiaFluidAttributes.bismuth_flow, false, () -> ClientEvents::getBismuthColor, new Vector3f(0.5F, 0.5F, 0.5F)),
                ModFluids.LIQUID_BISMUTH.get());
        e.registerFluidType(makeFluidType(GaiaFluidAttributes.aura_still, GaiaFluidAttributes.aura_flow, true, () -> ClientEvents::getAuraColor, new Vector3f(1.0F, 1.0F, 1.0F)),
                ModFluids.LIQUID_AURA.get());
    }

    private static IClientFluidTypeExtensions makeFluidType(ResourceLocation stillpath, ResourceLocation flowingpath, boolean overlay, Supplier<Function<BlockPos, Integer>> color, Vector3f fog) {
        return new IClientFluidTypeExtensions() {
            @Override
            public ResourceLocation getStillTexture() {
                return stillpath;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return flowingpath;
            }

            @Override
            public @Nullable ResourceLocation getOverlayTexture() {
                return overlay ? ResourceLocation.withDefaultNamespace("block/water_overlay") : null;
            }

            @Override
            public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
                return color != null ? color.get().apply(pos) | 0xFF000000 : this.getTintColor();
            }

            @Override
            public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return fog;
            }
        };
    }
}
