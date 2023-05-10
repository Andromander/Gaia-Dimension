package androsa.gaiadimension.client;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.particle.*;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModParticles;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = GaiaDimensionMod.MODID)
@OnlyIn(Dist.CLIENT)
public class ClientEvents {

    private static final ImmutableList<RegistryObject<? extends Block>> emissiveBlocks = ImmutableList.of(
            ModBlocks.malachite_pulsing_bricks,
            ModBlocks.malachite_pulsing_chisel,
            ModBlocks.malachite_pulsing_tiles,
            ModBlocks.malachite_pulsing_brick_stairs,
            ModBlocks.malachite_pulsing_chisel_stairs,
            ModBlocks.malachite_pulsing_floor_stairs,
            ModBlocks.active_rock
    );

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
        event.register(new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), new GaiaDimensionRenderInfo());
    }

    //TODO: They put these into models
//    @SubscribeEvent
//    public static void bakeModels(ModelEvent.ModifyBakingResult event) {
//        Function<Map.Entry<Property<?>, Comparable<?>>, String> MAP_ENTRY_TO_STRING = ObfuscationReflectionHelper.getPrivateValue(StateHolder.class, null, "f_61110_");
//
//        for (RegistryObject<? extends Block> block : emissiveBlocks) {
//            for (int i = 0; i <= 1; i++) {
//                for (BlockState state : block.get().getStateDefinition().getPossibleStates()) {
//                    String variant = state.getValues().entrySet().stream().map(MAP_ENTRY_TO_STRING).collect(Collectors.joining(","));
//                    ModelResourceLocation modelLoc = new ModelResourceLocation(Objects.requireNonNull(block.getId()), i == 0 ? variant : "inventory");
//                    final BakedModel model = event.getModels().get(modelLoc);
//                    event.getModels().put(modelLoc, new EmissiveModel(model));
//                }
//            }
//        }
//    }
}
