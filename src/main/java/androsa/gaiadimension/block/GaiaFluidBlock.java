package androsa.gaiadimension.block;

import androsa.gaiadimension.fluids.*;
import androsa.gaiadimension.registry.GaiaTags;
import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.function.Supplier;

public class GaiaFluidBlock extends LiquidBlock {

    public GaiaFluidBlock(Supplier<? extends FlowingFluid> fluid, Properties builder) {
        super(fluid, builder.noCollission().strength(100.0F).noDrops());
    }

    @Override
    public boolean shouldSpreadLiquid(Level world, BlockPos pos, BlockState state) {
        for (Direction side : Direction.values()) {
            if (side != Direction.DOWN) {
                FluidState offset = world.getFluidState(pos.relative(side));

                if (this.getFluid().is(GaiaTags.Fluids.LIQUID_AURA)) {
                    if (offset.is(FluidTags.LAVA) && (!offset.is(GaiaTags.Fluids.SUPERHOT_MAGMA) && !offset.is(GaiaTags.Fluids.LIQUID_BISMUTH))) {
                        this.setMixedBlock(world, pos, ModBlocks.sparkling_rock);
                        return false;
                    }
                } else if (this.getFluid().is(GaiaTags.Fluids.SUPERHOT_MAGMA)) {
                    if (offset.is(GaiaTags.Fluids.SWEET_MUCK)) {
                        this.setMixedBlock(world, pos, ModBlocks.primal_mass);
                        return false;
                    } else if (offset.is(GaiaTags.Fluids.LIQUID_AURA)) {
                        this.setMixedBlock(world, pos, ModBlocks.aura_block);
                        return false;
                    } else if (offset.is(FluidTags.WATER)) {
                        this.setMixedBlock(world, pos, ModBlocks.gaia_cobblestone);
                        return false;
                    }
                } else if (this.getFluid().is(GaiaTags.Fluids.LIQUID_BISMUTH)) {
                    if (offset.is(GaiaTags.Fluids.SWEET_MUCK) || offset.is(GaiaTags.Fluids.SUPERHOT_MAGMA)) {
                        this.setMixedBlock(world, pos, ModBlocks.active_rock);
                        return false;
                    } else if (offset.is(GaiaTags.Fluids.LIQUID_AURA)) {
                        this.setMixedBlock(world, pos, ModBlocks.tektite_block);
                        return false;
                    } else if (offset.is(FluidTags.WATER)) {
                        this.setMixedBlock(world, pos, ModBlocks.impure_rock);
                        return false;
                    }
                }
            }
        }

        return super.shouldSpreadLiquid(world, pos, state);
    }

    private void setMixedBlock(Level world, BlockPos pos, Supplier<Block> block) {
        world.setBlockAndUpdate(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, block.get().defaultBlockState()));
        this.triggerMixEffects(world, pos);
    }

    private void triggerMixEffects(LevelAccessor worldIn, BlockPos pos) {
        worldIn.levelEvent(Constants.WorldEvents.LAVA_EXTINGUISH, pos, 0);
    }

    @Override
    @Deprecated
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        if (this.getFluid().is(FluidTags.LAVA)) {
            if (this.getFluid() instanceof SuperhotMagmaFluid && !entityIn.fireImmune()) {
                entityIn.hurt(DamageSource.IN_FIRE, 5.0F);
            }
            entityIn.setSecondsOnFire(15);
        }
    }
}
