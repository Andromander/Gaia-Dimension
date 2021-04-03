package androsa.gaiadimension.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GaiaPortalParticle extends SpriteTexturedParticle {
    private final double portalPosX;
    private final double portalPosY;
    private final double portalPosZ;

    public GaiaPortalParticle(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        this.xd = xSpeedIn;
        this.yd = ySpeedIn;
        this.zd = zSpeedIn;
        this.x = xCoordIn;
        this.y = yCoordIn;
        this.z = zCoordIn;
        this.portalPosX = this.x;
        this.portalPosY = this.y;
        this.portalPosZ = this.z;
        float f = this.random.nextFloat() * 0.6F + 0.4F;
        this.rCol = f * 0.9F;
        this.gCol = f * 0.3F;
        this.bCol = f;
        this.lifetime = (int)(Math.random() * 10.0D) + 40;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().move(x, y, z));
        this.setLocationFromBoundingbox();
    }

    @Override
    public float getQuadSize(float partialTick) {
        float f = ((float)this.age + partialTick) / (float)this.lifetime;
        f = 1.0F - f;
        f = f * f;
        f = 1.0F - f;
        return this.quadSize * f;
    }

    @Override
    public int getLightColor(float partialTick) {
        int i = super.getLightColor(partialTick);
        float f = (float)this.age / (float)this.lifetime;
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
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float f = (float)this.age / (float)this.lifetime;
            float f1 = -f + f * f * 2.0F;
            float f2 = 1.0F - f1;
            this.x = this.portalPosX + this.xd * (double)f2;
            this.y = this.portalPosY + this.yd * (double)f2 + (double)(1.0F - f);
            this.z = this.portalPosZ + this.zd * (double)f2;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        @Override
        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            GaiaPortalParticle portalparticle = new GaiaPortalParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            portalparticle.pickSprite(this.spriteSet);
            return portalparticle;
        }
    }
}
