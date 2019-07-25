package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class PyriteWallTorchBlock extends WallTorchBlock {

    public PyriteWallTorchBlock() {
        super(Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F).lightValue(14).doesNotBlockMovement());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
        Direction direction = state.get(HORIZONTAL_FACING);
        double dx = (double)pos.getX() + 0.5D;
        double dy = (double)pos.getY() + 0.7D;
        double dz = (double)pos.getZ() + 0.5D;
        double d0 = 0.22D;
        double d1 = 0.27D;
        Direction opposite = direction.getOpposite();
        world.addParticle(ModParticles.PYRITE, dx + d1 * (double)opposite.getXOffset(), dy + d0, dz + d1 * (double)opposite.getZOffset(), 0.0D, 0.0D, 0.0D);
    }
}
