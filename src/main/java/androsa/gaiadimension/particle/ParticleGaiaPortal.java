package androsa.gaiadimension.particle;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleGaiaPortal extends Particle {

    private final float portalParticleScale;
    private final double portalPosX;
    private final double portalPosY;
    private final double portalPosZ;

    public ParticleGaiaPortal(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
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
        this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F;
        this.portalParticleScale = this.particleScale;
        this.particleRed = f * 0.9F;
        this.particleGreen = f * 0.3F;
        this.particleBlue = f;
        this.particleMaxAge = (int)(Math.random() * 10.0D) + 40;
        this.particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(new ResourceLocation(GaiaDimension.MODID, "particle/portal_sparkle").toString());
    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
        this.resetPositionToBB();
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        float f = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge;
        f = 1.0F - f;
        f = f * f;
        f = 1.0F - f;
        this.particleScale = this.portalParticleScale * f;
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    @Override
    public int getBrightnessForRender(float partialTick) {
        int i = super.getBrightnessForRender(partialTick);
        float f = (float)this.particleAge / (float)this.particleMaxAge;
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
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float f = (float)this.particleAge / (float)this.particleMaxAge;
        float f1 = -f + f * f * 2.0F;
        float f2 = 1.0F - f1;
        this.posX = this.portalPosX + this.motionX * (double)f2;
        this.posY = this.portalPosY + this.motionY * (double)f2 + (double)(1.0F - f);
        this.posZ = this.portalPosZ + this.motionZ * (double)f2;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setExpired();
        }
    }

    @Override
    public int getFXLayer() {
        return 1;
    }
}
