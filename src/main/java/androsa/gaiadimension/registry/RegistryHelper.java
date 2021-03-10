package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHelper {

    public static final List<Block> BLOCKS = Lists.newArrayList();
    public static final List<Item> BLOCK_ITEMS = Lists.newArrayList();
    public static final List<EntityType<?>> ENTITY_TYPES = Lists.newArrayList();
    public static final List<ParticleType<?>> PARTICLE_TYPES = Lists.newArrayList();
    public static final List<PointOfInterestType> POI_TYPES = Lists.newArrayList();
    public static final Map<ConfiguredSurfaceBuilder<?>, String> CONFIGURED_SURFACE_BUILDERS = Maps.newHashMap();
    public static final Map<StructureFeature<?,?>, String> CONFIGURED_STRUCTURE_FEATURES = Maps.newHashMap();
    public static final Map<ConfiguredCarver<?>, String> CONFIGURED_WORLD_CARVERS = Maps.newHashMap();
    public static final Map<ConfiguredFeature<?,?>, String> CONFIGURED_FEATURES = Maps.newHashMap();

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
        BLOCK_ITEMS.add(registerBlockItem(name, item));
        return block;
    }

    public static Item registerBlockItem(String name, Item item) {
        item.setRegistryName(name);
        return item;
    }

    public static <E extends Entity> EntityType<E> registerEntity(EntityType<E> entity) {
        ENTITY_TYPES.add(entity);
        return entity;
    }

    public static <T extends ParticleType<?>> T registerParticle(String name, T particle) {
        particle.setRegistryName(name);
        PARTICLE_TYPES.add(particle);
        return particle;
    }

    public static PointOfInterestType registerPOI(String name, Set<BlockState> states, int free, int range) {
        PointOfInterestType poi = new PointOfInterestType(name, states, free, range);
        poi.setRegistryName(name);
        POI_TYPES.add(poi);
        return poi;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        for (Block block : BLOCKS) {
            registry.register(block);
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
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        IForgeRegistry<ParticleType<?>> registry = event.getRegistry();
        for (ParticleType<?> particle : PARTICLE_TYPES) {
            registry.register(particle);
        }
    }
}
