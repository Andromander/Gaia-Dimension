package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.particle.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, GaiaDimensionMod.MODID);

    public static final RegistryObject<BasicParticleType> GEYSER_SMOKE = PARTICLE_TYPES.register("geyser_smoke", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> RESTRUCTURER_FIRE = PARTICLE_TYPES.register("restructurer_fire", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> PURIFIER_FIRE = PARTICLE_TYPES.register("purifier_fire", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> PORTAL = PARTICLE_TYPES.register("portal", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> PYRITE = PARTICLE_TYPES.register("pyrite", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> ITEM_PEBBLE = PARTICLE_TYPES.register("item_pebble", () -> new BasicParticleType(false));

    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent e) {
        ParticleManager particles = Minecraft.getInstance().particles;

        particles.registerFactory(GEYSER_SMOKE.get(), GeyserSmokeParticle.Factory::new);
        particles.registerFactory(RESTRUCTURER_FIRE.get(), RestructurerFireParticle.Factory::new);
        particles.registerFactory(PURIFIER_FIRE.get(), PurifierFireParticle.Factory::new);
        particles.registerFactory(PORTAL.get(), GaiaPortalParticle.Factory::new);
        particles.registerFactory(PYRITE.get(), PyriteParticle.Factory::new);
        particles.registerFactory(ITEM_PEBBLE.get(), new GaiaBreakingParticle.PebbleFactory());
    }
}
