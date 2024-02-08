package androsa.gaiadimension.particle;

import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;

public class GaiaBreakingParticle extends BreakingItemParticle {

    protected GaiaBreakingParticle(ClientLevel world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    public static class PebbleFactory implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new GaiaBreakingParticle(world, x, y, z, new ItemStack(ModItems.sturdy_pebble.get()));
        }
    }
}
