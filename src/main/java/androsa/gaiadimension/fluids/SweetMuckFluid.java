package androsa.gaiadimension.fluids;

import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class SweetMuckFluid extends ForgeFlowingFluid {

    public SweetMuckFluid(Properties props) {
        super(props);
    }

    @Override
    public int getTickRate(IWorldReader reader) {
        return 20;
    }
}
