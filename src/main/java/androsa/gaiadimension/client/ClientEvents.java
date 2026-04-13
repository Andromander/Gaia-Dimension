package androsa.gaiadimension.client;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.screen.*;
import androsa.gaiadimension.client.properties.Behavior;
import androsa.gaiadimension.client.properties.Element;
import androsa.gaiadimension.client.properties.Stat;
import androsa.gaiadimension.item.inventory.GemPouchScreen;
import androsa.gaiadimension.particle.*;
import androsa.gaiadimension.registry.registration.*;
import androsa.gaiadimension.registry.values.GaiaFluidAttributes;
import net.minecraft.client.Camera;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.fluid.FluidTintSource;
import org.joml.Vector4f;

import java.util.List;

@EventBusSubscriber(value = Dist.CLIENT, modid = GaiaDimensionMod.MODID)
public class ClientEvents {

    public static void init(IEventBus bus) {
        bus.addListener(ClientEvents::registerBlockColors);
        bus.addListener(ClientEvents::registerFluidModels);
        bus.addListener(ClientEvents::registerFactories);
        bus.addListener(ClientEvents::registerDimensionEffects);
        bus.addListener(ClientEvents::registerScreens);
        bus.addListener(ClientEvents::registerClientExtensions);
        bus.addListener(ClientEvents::registerSelectProperties);
    }

    public static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(BlockTints.grassTinting(0xF2A3B4)),
                ModBlocks.glitter_grass.get(),
                ModBlocks.crystal_growth.get());

        event.register(List.of(BlockTints.grassTinting(0x606060)),
                ModBlocks.murky_grass.get());

        event.register(List.of(BlockTints.grassTinting(0xA0A0A0)),
                ModBlocks.soft_grass.get());

        event.register(List.of(BlockTints.auraLeaves()),
                ModBlocks.aura_leaves.get());

        event.register(List.of(BlockTints.auraShoot()),
                ModBlocks.aura_shoot.get());

        event.register(List.of(BlockTintSources.constant(0xFFFFFF, 0x00AA00)),
                ModBlocks.malachite_guard_spawner.get());
    }

    public static void registerFluidModels(RegisterFluidModelsEvent event) {
        ModFluids.MINERAL_WATER.asOptional().ifPresent(f -> fluidModel(
                event,
                ModFluids.mineral_water_still.value(), ModFluids.mineral_water_flow.value(),
                GaiaFluidAttributes.mineral_still, GaiaFluidAttributes.mineral_flow,
                true,
                null));
        ModFluids.SUPERHOT_MAGMA.asOptional().ifPresent(f -> fluidModel(
                event,
                ModFluids.superhot_magma_still.value(), ModFluids.superhot_magma_flow.value(),
                GaiaFluidAttributes.superhot_still, GaiaFluidAttributes.superhot_flow,
                false,
                null));
        ModFluids.SWEET_MUCK.asOptional().ifPresent(f -> fluidModel(
                event,
                ModFluids.sweet_muck_still.value(), ModFluids.sweet_muck_flow.value(),
                GaiaFluidAttributes.sweet_still, GaiaFluidAttributes.sweet_flow,
                true,
                null));
        ModFluids.LIQUID_BISMUTH.asOptional().ifPresent(f -> fluidModel(
                event,
                ModFluids.liquid_bismuth_still.value(), ModFluids.liquid_bismuth_flow.value(),
                GaiaFluidAttributes.bismuth_still, GaiaFluidAttributes.bismuth_flow,
                false,
                BlockTints.liquidBismuth()));
        ModFluids.LIQUID_AURA.asOptional().ifPresent(f -> fluidModel(
                event,
                ModFluids.liquid_aura_still.value(), ModFluids.liquid_aura_flow.value(),
                GaiaFluidAttributes.aura_still, GaiaFluidAttributes.aura_flow,
                true,
                BlockTints.liquidAura()));
    }

    public static void fluidModel(RegisterFluidModelsEvent e, Fluid still, Fluid flow, Identifier stillTex, Identifier flowingTex, boolean overlay, FluidTintSource tint) {
        e.register(new FluidModel.Unbaked(
                new Material(stillTex),
                new Material(flowingTex),
                overlay ? new Material(Identifier.withDefaultNamespace("block/water_overlay")) : null,
                tint),
                still, flow);
    }

    public static void registerFactories(RegisterParticleProvidersEvent e) {
        e.registerSpriteSet(ModParticles.GEYSER_SMOKE.get(), GeyserSmokeParticle.Factory::new);
        e.registerSpriteSet(ModParticles.RESTRUCTURER_FIRE.get(), RestructurerFireParticle.Factory::new);
        e.registerSpriteSet(ModParticles.PURIFIER_FIRE.get(), PurifierFireParticle.Factory::new);
        e.registerSpriteSet(ModParticles.PORTAL.get(), GaiaPortalParticle.Factory::new);
        e.registerSpriteSet(ModParticles.PYRITE.get(), PyriteParticle.Factory::new);
        e.registerSpecial(ModParticles.ITEM_PEBBLE.get(), new GaiaBreakingParticle.PebbleFactory());
        e.registerSpriteSet(ModParticles.SPAWNER_CORE.get(), SpawnerCoreParticle.Factory::new);
        e.registerSpriteSet(ModParticles.MALACHITE_MAGIC.get(), MalachiteMagicParticle.Factory::new);
        e.registerSpriteSet(ModParticles.MAGIC_STAFF_TRAIL.get(), StaffMagicParticle.Provider::new);
    }

    public static void registerDimensionEffects(RegisterCustomEnvironmentEffectRendererEvent event) {
        event.registerSkyboxRenderer(Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "gaia"), new GaiaDimensionRenderInfo());
    }

    public static void registerScreens(RegisterMenuScreensEvent e) {
        e.register(ModMenus.AGATE_CRAFTING_TABLE.get(), AgateCraftingScreen::new);
        e.register(ModMenus.GAIA_STONE_FURNACE.get(), GaiaStoneFurnaceScreen::new);
        e.register(ModMenus.GEMSTONE_POUCH.get(), GemPouchScreen::new);
        e.register(ModMenus.SMALL_CRATE.get(), SmallCrateScreen::new);
        e.register(ModMenus.LARGE_CRATE.get(), LargeCrateScreen::new);
        e.register(ModMenus.RESTRUCTURER.get(), RestructurerScreen::new);
        e.register(ModMenus.PURIFIER.get(), PurifierScreen::new);
        e.register(ModMenus.AUGMENTER.get(), AugmenterScreen::new);
    }

    public static void registerClientExtensions(RegisterClientExtensionsEvent e) {
        e.registerFluidType(makeFluidType(new Vector4f(0.6875F, 0.75F, 1.0F, 1.0F)),
                ModFluids.MINERAL_WATER.get());
        e.registerFluidType(makeFluidType(new Vector4f(0.0F, 1.0F, 1.0F, 1.0F)),
                ModFluids.SUPERHOT_MAGMA.get());
        e.registerFluidType(makeFluidType(new Vector4f(0.5F, 0.0F, 0.5F, 1.0F)),
                ModFluids.SWEET_MUCK.get());
        e.registerFluidType(makeFluidType(new Vector4f(0.5F, 0.5F, 0.5F, 1.0F)),
                ModFluids.LIQUID_BISMUTH.get());
        e.registerFluidType(makeFluidType(new Vector4f(1.0F, 1.0F, 1.0F, 1.0F)),
                ModFluids.LIQUID_AURA.get());
    }

    private static IClientFluidTypeExtensions makeFluidType(Vector4f fog) {
        return new IClientFluidTypeExtensions() {
            @Override
            public void modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector4f fluidFogColor) {
                fluidFogColor.set(fog);
            }
        };
    }

    public static void registerSelectProperties(RegisterSelectItemModelPropertyEvent e) {
        e.register(Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "element"), Element.TYPE);
        e.register(Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "behavior"), Behavior.TYPE);
        e.register(Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "stat"), Stat.TYPE);
    }
}
