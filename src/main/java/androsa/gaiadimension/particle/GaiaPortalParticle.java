package androsa.gaiadimension.particle;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GaiaPortalParticle extends SpriteTexturedParticle {
    private final double portalPosX;
    private final double portalPosY;
    private final double portalPosZ;

    public GaiaPortalParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        this.motionX = xSpeedIn;
        this.motionY = ySpeedIn;
        this.motionZ = zSpeedIn;
        this.posX = xCoordIn;
        this.posY = yCoordIn;
        this.posZ = zCoordIn;
        this.portalPosX = this.posX;
        this.portalPosY = this.posY;
        this.portalPosZ = this.posZ;
        float f = this.rand.nextFloat() * 0.6F + 0.4F;
        this.particleRed = f * 0.9F;
        this.particleGreen = f * 0.3F;
        this.particleBlue = f;
        this.maxAge = (int)(Math.random() * 10.0D) + 40;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
        this.resetPositionToBB();
    }

    @Override
    public float getScale(float partialTick) {
        float f = ((float)this.age + partialTick) / (float)this.maxAge;
        f = 1.0F - f;
        f = f * f;
        f = 1.0F - f;
        return this.particleScale * f;
    }

    @Override
    public int getBrightnessForRender(float partialTick) {
        int i = super.getBrightnessForRender(partialTick);
        float f = (float)this.age / (float)this.maxAge;
        f = f * f;
        f = f * f;
        int j = i & 255;
        int k = i >> 16 & 255;
        k = k + (int)(f * 15.0F * 16.0F);

        if (k > 240) {
            k = 240;
        }

        return j | k << 16;
    }

    @Override
    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            float f = (float)this.age / (float)this.maxAge;
            float f1 = -f + f * f * 2.0F;
            float f2 = 1.0F - f1;
            this.posX = this.portalPosX + this.motionX * (double)f2;
            this.posY = this.portalPosY + this.motionY * (double)f2 + (double)(1.0F - f);
            this.posZ = this.portalPosZ + this.motionZ * (double)f2;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            GaiaPortalParticle portalparticle = new GaiaPortalParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            portalparticle.selectSpriteRandomly(this.spriteSet);
            return portalparticle;
        }
    }
}
