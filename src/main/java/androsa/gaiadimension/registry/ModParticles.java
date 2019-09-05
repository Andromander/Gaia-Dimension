package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.particle.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(GaiaDimensionMod.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {
    public static final BasicParticleType GEYSER_SMOKE = new BasicParticleType(false);
    public static final BasicParticleType RESTRUCTURER_FIRE = new BasicParticleType(false);
    public static final BasicParticleType PURIFIER_FIRE = new BasicParticleType(false);
    public static final BasicParticleType PORTAL = new BasicParticleType(false);
    public static final BasicParticleType PYRITE = new BasicParticleType(false);

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> e) {
        e.getRegistry().register(GEYSER_SMOKE.setRegistryName("geyser_smoke"));
        e.getRegistry().register(RESTRUCTURER_FIRE.setRegistryName("restructurer_fire"));
        e.getRegistry().register(PURIFIER_FIRE.setRegistryName("purifier_fire"));
        e.getRegistry().register(PORTAL.setRegistryName("portal"));
        e.getRegistry().register(PYRITE.setRegistryName("pyrite"));
    }

    @OnlyIn(Dist.CLIENT)
    public void registerFactories() {
        ParticleManager particles = Minecraft.getInstance().particles;

        particles.registerFactory(GEYSER_SMOKE, GeyserSmokeParticle.Factory::new);
        particles.registerFactory(RESTRUCTURER_FIRE, RestructurerFireParticle.Factory::new);
        particles.registerFactory(PURIFIER_FIRE, PurifierFireParticle.Factory::new);
        particles.registerFactory(PORTAL, GaiaPortalParticle.Factory::new);
        particles.registerFactory(PYRITE, PyriteParticle.Factory::new);
    }
}
