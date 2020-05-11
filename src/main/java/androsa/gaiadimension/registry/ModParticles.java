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

    public static final BasicParticleType geyser_smoke = new BasicParticleType(false);
    public static final BasicParticleType yellow_fire = new BasicParticleType(false);
    public static final BasicParticleType green_fire = new BasicParticleType(false);
    public static final BasicParticleType portal = new BasicParticleType(false);
    public static final BasicParticleType pyrite = new BasicParticleType(false);
    public static final BasicParticleType spawner_core = new BasicParticleType(false);

    public static final RegistryObject<BasicParticleType> GEYSER_SMOKE = PARTICLE_TYPES.register("geyser_smoke", () -> geyser_smoke);
    public static final RegistryObject<BasicParticleType> RESTRUCTURER_FIRE = PARTICLE_TYPES.register("restructurer_fire", () -> yellow_fire);
    public static final RegistryObject<BasicParticleType> PURIFIER_FIRE = PARTICLE_TYPES.register("purifier_fire", () -> green_fire);
    public static final RegistryObject<BasicParticleType> PORTAL = PARTICLE_TYPES.register("portal", () -> portal);
    public static final RegistryObject<BasicParticleType> PYRITE = PARTICLE_TYPES.register("pyrite", () -> pyrite);
    public static final RegistryObject<BasicParticleType> ITEM_PEBBLE = PARTICLE_TYPES.register("item_pebble", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> SPAWNER_CORE = PARTICLE_TYPES.register("spawner_core", () -> spawner_core);

    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent e) {
        ParticleManager particles = Minecraft.getInstance().particles;

        particles.registerFactory(geyser_smoke, GeyserSmokeParticle.Factory::new);
        particles.registerFactory(yellow_fire, RestructurerFireParticle.Factory::new);
        particles.registerFactory(green_fire, PurifierFireParticle.Factory::new);
        particles.registerFactory(portal, GaiaPortalParticle.Factory::new);
        particles.registerFactory(pyrite, PyriteParticle.Factory::new);
        particles.registerFactory(spawner_core, SpawnerCoreParticle.Factory::new);
    }

    @OnlyIn(Dist.CLIENT)
    public static void forgeClassLoadingIsFuckedThisShouldntBeHereButHereItIs() {
        Minecraft.getInstance().particles.registerFactory(ITEM_PEBBLE.get(), new GaiaBreakingParticle.PebbleFactory());
    }
}
