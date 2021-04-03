package androsa.gaiadimension.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PyriteParticle extends SpriteTexturedParticle {

    public PyriteParticle(ClientWorld worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double speedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, speedIn);
        this.rCol = 1.0F;
        this.gCol = 0.8F;
        this.bCol = 0.0F;
        this.setSize(0.02F, 0.02F);
        this.quadSize *= this.random.nextFloat() * 0.6F + 0.5F;
        this.xd *= 0.019999999552965164D;
        this.yd *= 0.019999999552965164D;
        this.zd *= 0.019999999552965164D;
        this.lifetime = (int)(20.0D / (Math.random() * 0.8D + 0.2D));
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
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        this.move(this.xd, this.yd, this.zd);
        this.xd *= 0.99D;
        this.yd *= 0.99D;
        this.zd *= 0.99D;

        if (this.lifetime-- <= 0) {
            this.remove();
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite) {
            this.spriteSet = sprite;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            PyriteParticle pyriteparticle = new PyriteParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            pyriteparticle.pickSprite(this.spriteSet);
            pyriteparticle.setColor(1.0F, 0.8F, 0.0F);
            return pyriteparticle;
        }
    }
}
