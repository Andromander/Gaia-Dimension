package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHelper {

    public static final List<Block> BLOCKS = Lists.newArrayList();
    public static final List<Item> ITEMS = Lists.newArrayList();
    public static final List<EntityType<?>> ENTITY_TYPES = Lists.newArrayList();
    public static final List<Fluid> FLUIDS = Lists.newArrayList();
    public static final List<ParticleType<?>> PARTICLE_TYPES = Lists.newArrayList();
    public static final List<Feature<?>> FEATURES = Lists.newArrayList();
    public static final List<Structure<?>> STRUCTURES = Lists.newArrayList();
    public static final List<SurfaceBuilder<?>> SURFACE_BUILDERS = Lists.newArrayList();
    public static final List<WorldCarver<?>> WORLD_CARVERS = Lists.newArrayList();
    public static final List<Biome> BIOMES = Lists.newArrayList();

    public static <T extends Block> T registerBlockOnly(String name, T block) {
        block.setRegistryName(name);
        BLOCKS.add(block);
        return block;
    }

    private static ResourceLocation loc(String name) {
        return new ResourceLocation(GaiaDimensionMod.MODID, name);
    }

    public static <T extends Block> T registerBlock(String name, T block) {
        return registerBlock(name, block, 0);
    }

    public static <T extends Block> T registerBlock(String name, T block, int burntime) {
        return registerBlock(name, block, new BlockItem(block, new Item.Properties().group(GaiaItemGroups.GAIA_BLOCKS)) {
            @Override
            public int getBurnTime(ItemStack itemStack) {
                return burntime;
            }
        });
    }

    public static <T extends Block> T registerBlock(String name, T block, Item item) {
        block.setRegistryName(loc(name));
        BLOCKS.add(block);
        ITEMS.add(registerBlockItem(name, item));
        return block;
    }

    public static Item registerBlockItem(String name, Item item) {
        item.setRegistryName(loc(name));
        return item;
    }

    public static Item registerWallOrFloorItem(String name, Block floor, Block wall) {
        Item item = new WallOrFloorItem(wall, floor, new Item.Properties().group(GaiaItemGroups.GAIA_BLOCKS));
        return registerItem(name, item);
    }

    public static Item registerItem(String name, Item item) {
        item.setRegistryName(loc(name));
        ITEMS.add(item);
        return item;
    }

    public static <E extends Entity> EntityType<E> registerEntity(String name, EntityType<E> entity) {
        entity.setRegistryName(loc(name));
        ENTITY_TYPES.add(entity);
        return entity;
    }

    public static <T extends ParticleType<?>> T registerParticle(String name, T particle) {
        particle.setRegistryName(loc(name));
        PARTICLE_TYPES.add(particle);
        return particle;
    }

    public static <T extends Fluid> T registerFluid(String name, T fluid) {
        fluid.setRegistryName(name);
        FLUIDS.add(fluid);
        return fluid;
    }

    public static <C extends IFeatureConfig, T extends Feature<C>> T registerFeature(String name, T feature) {
        feature.setRegistryName("gaiadimension", name);
        FEATURES.add(feature);
        return feature;
    }

    public static <T extends Structure<NoFeatureConfig>> T registerStructure(String name, T structure) {
        Structure.field_236365_a_.put(name, structure);
        structure.setRegistryName(name);
        STRUCTURES.add(structure);
        return structure;
    }

    public static SurfaceBuilder<SurfaceBuilderConfig> registerSurfaceBuilder(String name, SurfaceBuilder<SurfaceBuilderConfig> surface) {
        surface.setRegistryName(name);
        SURFACE_BUILDERS.add(surface);
        return surface;
    }

    public static WorldCarver<ProbabilityConfig> registerWorldCarver(String name, WorldCarver<ProbabilityConfig> surface) {
        surface.setRegistryName(name);
        WORLD_CARVERS.add(surface);
        return surface;
    }

    public static Biome registerBiome(Biome biome) {
        BIOMES.add(biome);
        return biome;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        for (Block block : BLOCKS) {
            registry.register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        for (Item item : ITEMS) {
            registry.register(item);
        }
        }
    }

    @SubscribeEvent
    public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
        IForgeRegistry<EntityType<?>> registry = event.getRegistry();
        for (EntityType<?> entity : ENTITY_TYPES) {
            registry.register(entity);
        }
    }

    @SubscribeEvent
    public static void registerFluids(RegistryEvent.Register<Fluid> event) {
        IForgeRegistry<Fluid> registry = event.getRegistry();
        for (Fluid fluid : FLUIDS) {
            registry.register(fluid);
        }
    }

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        IForgeRegistry<ParticleType<?>> registry = event.getRegistry();
        for (ParticleType<?> particle : PARTICLE_TYPES) {
            registry.register(particle);
        }
    }

    @SubscribeEvent
    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();
        for (Feature<?> feature : FEATURES) {
            registry.register(feature);
        }
    }

    @SubscribeEvent
    public static void registerStructures(RegistryEvent.Register<Structure<?>> event) {
        IForgeRegistry<Structure<?>> registry = event.getRegistry();
        for (Structure<?> structures : STRUCTURES) {
            registry.register(structures);
        }
    }

    @SubscribeEvent
    public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        IForgeRegistry<SurfaceBuilder<?>> registry = event.getRegistry();
        for (SurfaceBuilder<?> surface : SURFACE_BUILDERS) {
            registry.register(surface);
        }
    }

    @SubscribeEvent
    public static void registerWorldCarvers(RegistryEvent.Register<WorldCarver<?>> event) {
        IForgeRegistry<WorldCarver<?>> registry = event.getRegistry();
        for (WorldCarver<?> carvers : WORLD_CARVERS) {
            registry.register(carvers);
        }
    }

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> registry = event.getRegistry();
        for (Biome biome : BIOMES) {
            registry.register(biome);
        }
    }
}
