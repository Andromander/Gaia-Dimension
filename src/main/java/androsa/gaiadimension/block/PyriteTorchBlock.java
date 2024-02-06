package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.registration.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PyriteTorchBlock extends TorchBlock {

    public PyriteTorchBlock(Properties props) {
        super(ParticleTypes.SMALL_FLAME, props);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {
        double d0 = (double)pos.getX() + rand.nextDouble() * 0.5D + 0.2D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 0.7D + 0.2D;
        double d2 = (double)pos.getZ() + rand.nextDouble() * 0.5D + 0.2D;

        world.addParticle(ModParticles.PYRITE.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
}
