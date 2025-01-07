package androsa.gaiadimension.particle;

import androsa.gaiadimension.registry.registration.ModItems;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.BreakingItemParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;

public class GaiaBreakingParticle extends BreakingItemParticle {

    protected GaiaBreakingParticle(ClientLevel world, double x, double y, double z, ItemStackRenderState stack) {
        super(world, x, y, z, stack);
    }

    public static class PebbleFactory extends BreakingItemParticle.ItemParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType particle, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new GaiaBreakingParticle(world, x, y, z, this.calculateState(new ItemStack(ModItems.sturdy_pebble.get()), world));
        }
    }
}
