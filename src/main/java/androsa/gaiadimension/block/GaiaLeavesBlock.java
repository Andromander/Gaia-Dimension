package androsa.gaiadimension.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;

public class GaiaLeavesBlock extends LeavesBlock {

    public static final MapCodec<GaiaLeavesBlock> CODEC = simpleCodec(GaiaLeavesBlock::new);

    public GaiaLeavesBlock(Properties props) {
        super(0.0F, props);
    }

    @Override
    public MapCodec<? extends LeavesBlock> codec() {
        return CODEC;
    }

    @Override
    protected void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random) { }
}
