package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHelper {

    public static final List<EntityType<?>> ENTITY_TYPES = Lists.newArrayList();
    public static final List<ParticleType<?>> PARTICLE_TYPES = Lists.newArrayList();
    public static final List<SoundEvent> SOUND_EVENTS = Lists.newArrayList();
    public static final Map<ConfiguredSurfaceBuilder<?>, String> CONFIGURED_SURFACE_BUILDERS = Maps.newHashMap();
    public static final Map<StructureFeature<?,?>, String> CONFIGURED_STRUCTURE_FEATURES = Maps.newHashMap();
    public static final Map<ConfiguredCarver<?>, String> CONFIGURED_WORLD_CARVERS = Maps.newHashMap();
    public static final Map<ConfiguredFeature<?,?>, String> CONFIGURED_FEATURES = Maps.newHashMap();

    public static <E extends Entity> EntityType<E> registerEntity(EntityType<E> entity) {
        ENTITY_TYPES.add(entity);
        return entity;
    }

    public static <T extends ParticleType<?>> T registerParticle(String name, T particle) {
        particle.setRegistryName(new ResourceLocation(GaiaDimensionMod.MODID, name));
        PARTICLE_TYPES.add(particle);
        return particle;
    }

    public static SoundEvent registerSoundEvent(String name) {
        ResourceLocation location = new ResourceLocation(GaiaDimensionMod.MODID, name);
        SoundEvent sound = new SoundEvent(location);
        sound.setRegistryName(location);
        SOUND_EVENTS.add(sound);
        return sound;
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

    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
        IForgeRegistry<SoundEvent> registry = event.getRegistry();
        for (SoundEvent sound : SOUND_EVENTS) {
            registry.register(sound);
        }
    }
}
