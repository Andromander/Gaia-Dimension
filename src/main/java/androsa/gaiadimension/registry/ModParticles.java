package androsa.gaiadimension.registry;

import androsa.gaiadimension.particle.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModParticles {
    public static final BasicParticleType GEYSER_SMOKE = new BasicParticleType(false);
    public static final BasicParticleType RESTRUCTURER_FIRE = new BasicParticleType(false);
    public static final BasicParticleType PURIFIER_FIRE = new BasicParticleType(false);
    public static final BasicParticleType PORTAL = new BasicParticleType(false);
    public static final BasicParticleType PYRITE = new BasicParticleType(false);

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
