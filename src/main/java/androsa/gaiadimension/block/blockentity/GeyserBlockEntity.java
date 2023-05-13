package androsa.gaiadimension.block.blockentity;

import androsa.gaiadimension.registry.registration.ModBlockEntities;
import androsa.gaiadimension.registry.registration.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class GeyserBlockEntity extends BlockEntity {

    private long counter = 0;
    private final Random random = new Random();

    public GeyserBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEYSER.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GeyserBlockEntity entity) {
        if (level.isClientSide() && ++entity.counter % 2 == 0) {
            double xPos = (double)pos.getX() + 0.5D;
            double yPos = (double)pos.getY() + 1.0D;
            double zPos = (double)pos.getZ() + 0.5D;

            double xVel = 0.0D + entity.random.nextDouble() - 0.5D;
            double zVel = 0.0D + entity.random.nextDouble() - 0.5D;

            xVel = Mth.clamp(xVel, -0.02D, 0.02D);
            zVel = Mth.clamp(zVel, -0.02D, 0.02D);

            level.addParticle(ModParticles.GEYSER_SMOKE.get(), xPos, yPos, zPos, xVel, 0.3D, zVel);
        }
    }
}
