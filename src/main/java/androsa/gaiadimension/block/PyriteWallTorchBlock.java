package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class PyriteWallTorchBlock extends WallTorchBlock {

    public PyriteWallTorchBlock(Properties props) {
        super(props, ModParticles.PYRITE);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        Direction direction = state.getValue(FACING);
        double dx = (double)pos.getX() + 0.5D;
        double dy = (double)pos.getY() + 0.7D;
        double dz = (double)pos.getZ() + 0.5D;
        double d0 = 0.22D;
        double d1 = 0.27D;
        Direction opposite = direction.getOpposite();
        world.addParticle(flameParticle, dx + d1 * (double)opposite.getStepX(), dy + d0, dz + d1 * (double)opposite.getStepZ(), 0.0D, 0.0D, 0.0D);
    }
}
