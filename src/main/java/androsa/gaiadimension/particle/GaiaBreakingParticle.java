package androsa.gaiadimension.particle;

import androsa.gaiadimension.registry.ModItems;
import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GaiaBreakingParticle extends BreakingParticle {

    protected GaiaBreakingParticle(ClientWorld world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    @OnlyIn(Dist.CLIENT)
    public static class PebbleFactory implements IParticleFactory<BasicParticleType> {
        public Particle createParticle(BasicParticleType particle, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new GaiaBreakingParticle(world, x, y, z, new ItemStack(ModItems.sturdy_pebble.get()));
        }
    }
}
