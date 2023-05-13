package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, GaiaDimensionMod.MODID);

    public static final RegistryObject<SimpleParticleType> GEYSER_SMOKE = PARTICLE_TYPES.register("geyser_smoke", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> RESTRUCTURER_FIRE = PARTICLE_TYPES.register("restructurer_fire", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> PURIFIER_FIRE = PARTICLE_TYPES.register("purifier_fire", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> PORTAL = PARTICLE_TYPES.register("portal", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> PYRITE = PARTICLE_TYPES.register("pyrite", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ITEM_PEBBLE = PARTICLE_TYPES.register("item_pebble", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> SPAWNER_CORE = PARTICLE_TYPES.register("spawner_core", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> MALACHITE_MAGIC = PARTICLE_TYPES.register("malachite_magic", () -> new SimpleParticleType(false));
}
