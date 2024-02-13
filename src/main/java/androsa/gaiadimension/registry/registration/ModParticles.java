package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, GaiaDimensionMod.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GEYSER_SMOKE = PARTICLE_TYPES.register("geyser_smoke", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> RESTRUCTURER_FIRE = PARTICLE_TYPES.register("restructurer_fire", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PURIFIER_FIRE = PARTICLE_TYPES.register("purifier_fire", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PORTAL = PARTICLE_TYPES.register("portal", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PYRITE = PARTICLE_TYPES.register("pyrite", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> ITEM_PEBBLE = PARTICLE_TYPES.register("item_pebble", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SPAWNER_CORE = PARTICLE_TYPES.register("spawner_core", () -> new SimpleParticleType(false));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MALACHITE_MAGIC = PARTICLE_TYPES.register("malachite_magic", () -> new SimpleParticleType(false));
}
