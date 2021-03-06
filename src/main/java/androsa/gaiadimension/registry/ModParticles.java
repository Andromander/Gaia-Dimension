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

@Mod.EventBusSubscriber(modid = GaiaDimensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {

    public static final BasicParticleType GEYSER_SMOKE = registerBasicParticle("geyser_smoke");
    public static final BasicParticleType RESTRUCTURER_FIRE = registerBasicParticle("restructurer_fire");
    public static final BasicParticleType PURIFIER_FIRE = registerBasicParticle("purifier_fire");
    public static final BasicParticleType PORTAL = registerBasicParticle("portal");
    public static final BasicParticleType PYRITE = registerBasicParticle("pyrite");
    public static final BasicParticleType ITEM_PEBBLE = registerBasicParticle("item_pebble");
    public static final BasicParticleType SPAWNER_CORE = registerBasicParticle("spawner_core");
    public static final BasicParticleType MALACHITE_MAGIC = registerBasicParticle("malachite_magic");

    private static BasicParticleType registerBasicParticle(String name) {
        return RegistryHelper.registerParticle(name, new BasicParticleType(false));
    }

    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent e) {
        ParticleManager particles = Minecraft.getInstance().particleEngine;

        particles.register(GEYSER_SMOKE, GeyserSmokeParticle.Factory::new);
        particles.register(RESTRUCTURER_FIRE, RestructurerFireParticle.Factory::new);
        particles.register(PURIFIER_FIRE, PurifierFireParticle.Factory::new);
        particles.register(PORTAL, GaiaPortalParticle.Factory::new);
        particles.register(PYRITE, PyriteParticle.Factory::new);
        particles.register(SPAWNER_CORE, SpawnerCoreParticle.Factory::new);
        particles.register(MALACHITE_MAGIC, MalachiteMagicParticle.Factory::new);
    }

    @OnlyIn(Dist.CLIENT)
    public static void forgeClassLoadingIsFuckedThisShouldntBeHereButHereItIs() {
        Minecraft.getInstance().particleEngine.register(ITEM_PEBBLE, new GaiaBreakingParticle.PebbleFactory());
    }
}
