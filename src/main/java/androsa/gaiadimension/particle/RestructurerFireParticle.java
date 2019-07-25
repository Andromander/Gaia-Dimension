package androsa.gaiadimension.particle;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class RestructurerFireParticle extends SpriteTexturedParticle {

    public RestructurerFireParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
        this.motionX = this.motionX * 0.009999999776482582D + xSpeedIn;
        this.motionY = this.motionY * 0.009999999776482582D + ySpeedIn;
        this.motionZ = this.motionZ * 0.009999999776482582D + zSpeedIn;
        this.posX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        this.posY += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        this.posZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.05F);
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
        this.maxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D)) + 4;
        this.setSprite(Minecraft.getInstance().getTextureMap().getAtlasSprite(new ResourceLocation(GaiaDimensionMod.MODID, "particle/yellow_fire").toString()));
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
    public int getBrightnessForRender(float partialTick) {
        float f = ((float)this.age + partialTick) / (float)this.maxAge;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        int i = super.getBrightnessForRender(partialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        j = j + (int)(f * 15.0F * 16.0F);

        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        }

        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.onGround) {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
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
            RestructurerFireParticle flameparticle = new RestructurerFireParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            flameparticle.selectSpriteRandomly(this.spriteSet);
            return flameparticle;
        }
    }
}
