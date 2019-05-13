package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.registry.EnumParticlesGD;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class TileEntityGeyser extends TileEntity implements ITickable {

    private long counter = 0;
    Random random = new Random();

    @Override
    public void update() {
        if (world.isRemote && ++counter % 2 == 0) {
            double xPos = (double)pos.getX() + 0.5D;
            double yPos = (double)pos.getY() + 1.0D;
            double zPos = (double)pos.getZ() + 0.5D;

            double xVel = 0.0D + random.nextDouble() - 0.5D;
            double zVel = 0.0D + random.nextDouble() - 0.5D;

            xVel = MathHelper.clamp(xVel, -0.02D, 0.02D);
            zVel = MathHelper.clamp(zVel, -0.02D, 0.02D);

            GaiaDimension.proxy.spawnParticle(EnumParticlesGD.GEYSER_SMOKE, xPos, yPos, zPos, xVel, 0.3D, zVel);
        }
    }
}
