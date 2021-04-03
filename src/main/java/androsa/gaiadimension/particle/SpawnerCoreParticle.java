package androsa.gaiadimension.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class SpawnerCoreParticle extends SpriteTexturedParticle {

    private static final Random RANDOM = new Random();

    public SpawnerCoreParticle(ClientWorld world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, IAnimatedSprite sprite) {
        super(world, posX, posY, posZ, 0.5D - RANDOM.nextDouble(), motionY, 0.5D - RANDOM.nextDouble());
        if (motionX == 0.0D && motionZ == 0.0D) {
            this.xd *= (double)0.2F;
            this.zd *= (double)0.2F;
        }
        this.yd = 0.0F;

        this.quadSize *= 0.75F;
        this.lifetime = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.hasPhysics = false;
        this.setSpriteFromAge(sprite);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getLightColor(float partialTicks) {
        return 15728880;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.move(this.xd, this.yd, this.zd);
            if (this.y == this.yo) {
                this.xd *= 1.1D;
                this.zd *= 1.1D;
            }

            this.xd *= (double)0.96F;
            this.zd *= (double)0.96F;
            if (this.onGround) {
                this.xd *= (double)0.7F;
                this.zd *= (double)0.7F;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        public Particle createParticle(BasicParticleType type, ClientWorld world, double posX, double posY, double posZ, double r, double g, double b) {
            Particle particle = new SpawnerCoreParticle(world, posX, posY, posZ, r, g, b, this.spriteSet);
            particle.setColor((float)r, (float)g, (float)b);
            return particle;
        }
    }
}
