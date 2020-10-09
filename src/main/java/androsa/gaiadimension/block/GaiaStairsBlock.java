package androsa.gaiadimension.block;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;

import java.util.function.Supplier;

public class GaiaStairsBlock extends StairsBlock {

    public GaiaStairsBlock(Supplier<? extends Block> state) {
        super(() -> state.get().getDefaultState(), Properties.from(state.get()));
    }
}
