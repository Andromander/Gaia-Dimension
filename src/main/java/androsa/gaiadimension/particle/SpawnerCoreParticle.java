package androsa.gaiadimension.particle;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class SpawnerCoreParticle extends SpriteTexturedParticle {

    private static final Random RANDOM = new Random();

    public SpawnerCoreParticle(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ, IAnimatedSprite sprite) {
        super(world, posX, posY, posZ, 0.5D - RANDOM.nextDouble(), motionY, 0.5D - RANDOM.nextDouble());
//        this.motionY *= (double)0.1F;
        if (motionX == 0.0D && motionZ == 0.0D) {
            this.motionX *= (double)0.2F;
            this.motionZ *= (double)0.2F;
        }
        this.motionY = 0.0F;

        this.particleScale *= 0.75F;
        this.maxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.canCollide = false;
        this.selectSpriteWithAge(sprite);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public int getBrightnessForRender(float partialTicks) {
        return 15728880;
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
//            this.motionY += 0.004D;
            this.move(this.motionX, this.motionY, this.motionZ);
            if (this.posY == this.prevPosY) {
                this.motionX *= 1.1D;
                this.motionZ *= 1.1D;
            }

            this.motionX *= (double)0.96F;
//            this.motionY *= (double)0.96F;
            this.motionZ *= (double)0.96F;
            if (this.onGround) {
                this.motionX *= (double)0.7F;
                this.motionZ *= (double)0.7F;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        public Particle makeParticle(BasicParticleType type, World world, double posX, double posY, double posZ, double r, double g, double b) {
            Particle particle = new SpawnerCoreParticle(world, posX, posY, posZ, r, g, b, this.spriteSet);
            particle.setColor((float)r, (float)g, (float)b);
            return particle;
        }
    }
}
