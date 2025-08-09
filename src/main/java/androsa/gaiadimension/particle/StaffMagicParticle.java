package androsa.gaiadimension.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ColorParticleOption;
import org.jetbrains.annotations.Nullable;

public class StaffMagicParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    public StaffMagicParticle(ClientLevel level, double x, double y, double z, double velX, double velY, double velZ, ColorParticleOption option, SpriteSet sprites) {
        super(level, x, y, z, velX, velY, velZ);

        this.xd = velX;
        this.yd = velY;
        this.zd = velZ;
        this.gravity = 0.0125F;
        this.quadSize *= 0.5F;
        this.lifetime = 30 + this.random.nextInt(12);
        this.sprites = sprites;
        this.rCol = option.getRed();
        this.gCol = option.getGreen();
        this.bCol = option.getBlue();
        this.setSpriteFromAge(sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().move(x, y, z));
        this.setLocationFromBoundingbox();
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        if (this.age > this.lifetime / 2) {
            this.setAlpha(1.0F - ((float)this.age - this.lifetime / 2) / this.lifetime);
        }
    }

    public static class Provider implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(ColorParticleOption option, ClientLevel level, double x, double y, double z, double velX, double velY, double velZ) {
            return new StaffMagicParticle(level, x, y, z, velX, velY, velZ, option, sprites);
        }
    }
}
