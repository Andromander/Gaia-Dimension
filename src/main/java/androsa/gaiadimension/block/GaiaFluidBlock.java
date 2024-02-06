package androsa.gaiadimension.block;

import androsa.gaiadimension.fluids.SuperhotMagmaFluid;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

import java.util.function.Supplier;

public class GaiaFluidBlock extends LiquidBlock {

    public GaiaFluidBlock(Supplier<? extends FlowingFluid> fluid, Properties builder) {
        super(fluid, builder.noCollission().strength(100.0F));
    }

    //TODO: Revise
    @Override
    @Deprecated
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if (this.getFluid().is(FluidTags.LAVA)) {
            if (this.getFluid() instanceof SuperhotMagmaFluid && !entityIn.fireImmune()) {
                entityIn.hurt(worldIn.damageSources().lava(), 5.0F);
            }
            entityIn.setSecondsOnFire(15);
        }
    }
}
