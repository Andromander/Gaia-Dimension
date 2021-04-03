package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class PyriteTorchBlock extends TorchBlock {

    public PyriteTorchBlock(Properties props) {
        super(props, ModParticles.PYRITE);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        double d0 = (double)pos.getX() + rand.nextDouble() * 0.5D + 0.2D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 0.7D + 0.2D;
        double d2 = (double)pos.getZ() + rand.nextDouble() * 0.5D + 0.2D;

        world.addParticle(flameParticle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
}
