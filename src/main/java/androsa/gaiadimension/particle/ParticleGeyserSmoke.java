package androsa.gaiadimension.particle;

import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.world.World;

public class ParticleGeyserSmoke extends ParticleSmokeNormal {

    public ParticleGeyserSmoke(World world, double x, double y, double z, double vX, double vY, double vZ) {
        super(world, x, y, z, vX, vY, vZ, 4.0F);
    }
}
