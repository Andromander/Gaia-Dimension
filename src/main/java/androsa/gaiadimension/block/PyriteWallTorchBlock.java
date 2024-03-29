package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.registration.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PyriteWallTorchBlock extends WallTorchBlock {

    public PyriteWallTorchBlock(Properties props) {
        super(ParticleTypes.SMALL_FLAME, props);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
        Direction direction = state.getValue(FACING);
        double dx = (double)pos.getX() + 0.5D;
        double dy = (double)pos.getY() + 0.7D;
        double dz = (double)pos.getZ() + 0.5D;
        double d0 = 0.22D;
        double d1 = 0.27D;
        Direction opposite = direction.getOpposite();
        world.addParticle(ModParticles.PYRITE.get(), dx + d1 * (double)opposite.getStepX(), dy + d0, dz + d1 * (double)opposite.getStepZ(), 0.0D, 0.0D, 0.0D);
    }
}
