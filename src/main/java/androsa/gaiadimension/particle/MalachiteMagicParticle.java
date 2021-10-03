package androsa.gaiadimension.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

import javax.annotation.Nullable;

public class MalachiteMagicParticle extends TextureSheetParticle {

    protected MalachiteMagicParticle(ClientLevel world, double xPos, double yPos, double zPos, double xDist, double yDist, double zDist) {
        super(world, xPos, yPos, zPos, xDist, yDist, zDist);
        this.xd = xDist;
        this.yd = yDist + this.random.nextFloat() * 0.05F;
        this.zd = zDist;
        this.lifetime = this.random.nextInt(20) + 40;
        this.hasPhysics = false;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            this.xd *= 0.9F;
            this.zd *= 0.9F;
            this.move(this.xd, this.yd, this.zd);
            if (this.age >= this.lifetime - 30 && this.alpha > 0.01F) {
                this.alpha -= 0.03F;
            }
        } else {
            this.remove();
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprite;

        public Factory(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double xPos, double yPos, double zPos, double xDist, double yDist, double zDist) {
            MalachiteMagicParticle particle = new MalachiteMagicParticle(level, xPos, yPos, zPos, xDist, yDist, zDist);
            particle.pickSprite(sprite);
            return particle;
        }
    }
}
