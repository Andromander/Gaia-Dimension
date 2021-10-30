package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryHelper {

    public static final List<ParticleType<?>> PARTICLE_TYPES = Lists.newArrayList();
    public static final List<SoundEvent> SOUND_EVENTS = Lists.newArrayList();
    public static final Map<ConfiguredSurfaceBuilder<?>, String> CONFIGURED_SURFACE_BUILDERS = Maps.newHashMap();
    public static final Map<ConfiguredStructureFeature<?,?>, String> CONFIGURED_STRUCTURE_FEATURES = Maps.newHashMap();
    public static final Map<ConfiguredWorldCarver<?>, String> CONFIGURED_WORLD_CARVERS = Maps.newHashMap();
    public static final Map<ConfiguredFeature<?,?>, String> CONFIGURED_FEATURES = Maps.newHashMap();

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
