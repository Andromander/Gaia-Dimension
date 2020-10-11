package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.particle.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(value = GaiaDimensionMod.MODID)
@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {

    public static final BasicParticleType GEYSER_SMOKE = registerBasicParticle("geyser_smoke");
    public static final BasicParticleType RESTRUCTURER_FIRE = registerBasicParticle("restructurer_fire");
    public static final BasicParticleType PURIFIER_FIRE = registerBasicParticle("purifier_fire");
    public static final BasicParticleType PORTAL = registerBasicParticle("portal");
    public static final BasicParticleType PYRITE = registerBasicParticle("pyrite");
    public static final BasicParticleType ITEM_PEBBLE = registerBasicParticle("item_pebble");
    public static final BasicParticleType SPAWNER_CORE = registerBasicParticle("spawner_core");

    private static BasicParticleType registerBasicParticle(String name) {
        return RegistryHelper.registerParticle(name, new BasicParticleType(false));
    }

    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent e) {
        ParticleManager particles = Minecraft.getInstance().particles;

        particles.registerFactory(GEYSER_SMOKE, GeyserSmokeParticle.Factory::new);
        particles.registerFactory(RESTRUCTURER_FIRE, RestructurerFireParticle.Factory::new);
        particles.registerFactory(PURIFIER_FIRE, PurifierFireParticle.Factory::new);
        particles.registerFactory(PORTAL, GaiaPortalParticle.Factory::new);
        particles.registerFactory(PYRITE, PyriteParticle.Factory::new);
        particles.registerFactory(SPAWNER_CORE, SpawnerCoreParticle.Factory::new);
    }

    @OnlyIn(Dist.CLIENT)
    public static void forgeClassLoadingIsFuckedThisShouldntBeHereButHereItIs() {
        Minecraft.getInstance().particles.registerFactory(ITEM_PEBBLE, new GaiaBreakingParticle.PebbleFactory());
    }
}
