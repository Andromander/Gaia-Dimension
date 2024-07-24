package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.values.GaiaTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.TriState;

public class GaiaSoilBlock extends Block {

    public static final MapCodec<? extends GaiaSoilBlock> CODEC = simpleCodec(GaiaSoilBlock::new);

    public GaiaSoilBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    public TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
        return state.is(GaiaTags.Blocks.GAIA_PLANTS) ? TriState.TRUE : TriState.FALSE;
    }
}
