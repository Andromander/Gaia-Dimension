package androsa.gaiadimension.block.tileentity;

import androsa.gaiadimension.registry.ModParticles;
import androsa.gaiadimension.registry.ModTileEntities;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class GeyserTileEntity extends TileEntity implements ITickableTileEntity {

    private long counter = 0;
    private Random random = new Random();

    public GeyserTileEntity() {
        super(ModTileEntities.GEYSER.get());
    }

    @Override
    public void tick() {
        if (level.isClientSide() && ++counter % 2 == 0) {
            double xPos = (double)worldPosition.getX() + 0.5D;
            double yPos = (double)worldPosition.getY() + 1.0D;
            double zPos = (double)worldPosition.getZ() + 0.5D;

            double xVel = 0.0D + random.nextDouble() - 0.5D;
            double zVel = 0.0D + random.nextDouble() - 0.5D;

            xVel = MathHelper.clamp(xVel, -0.02D, 0.02D);
            zVel = MathHelper.clamp(zVel, -0.02D, 0.02D);

            level.addParticle(ModParticles.GEYSER_SMOKE, xPos, yPos, zPos, xVel, 0.3D, zVel);
        }
    }
}
