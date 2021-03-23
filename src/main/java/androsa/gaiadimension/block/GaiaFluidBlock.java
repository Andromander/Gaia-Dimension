package androsa.gaiadimension.block;

import androsa.gaiadimension.fluids.*;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModFluids;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.function.Supplier;

public class GaiaFluidBlock extends FlowingFluidBlock {

    public GaiaFluidBlock(Supplier<? extends FlowingFluid> fluid, Properties builder) {
        super(fluid, builder.doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops());
    }

    @Override
    public boolean reactWithNeighbors(World world, BlockPos pos, BlockState state) {
        if (this.getFluid() == ModFluids.liquid_aura_still.get() || this.getFluid() == ModFluids.liquid_aura_flow.get()) {

            for (Direction side : Direction.values()) {
                if (side != Direction.DOWN) {
                    FluidState offset = world.getFluidState(pos.offset(side));

                    if (offset.isTagged(FluidTags.LAVA) && (!(offset.getFluid() instanceof SuperhotMagmaFluid) && !(offset.getFluid() instanceof LiquidBismuthFluid))) {
                        world.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, ModBlocks.sparkling_rock.get().getDefaultState()));
                        this.triggerMixEffects(world, pos);
                        return false;
                    }
                }
            }

        } else if (this.getFluid() instanceof SuperhotMagmaFluid) {

            for (Direction side : Direction.values()) {
                if (side != Direction.DOWN) {
                    FluidState offset = world.getFluidState(pos.offset(side));

                    if (offset.getFluid() instanceof SweetMuckFluid) {
                        world.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, ModBlocks.primal_mass.get().getDefaultState()));
                        this.triggerMixEffects(world, pos);
                        return false;
                    } else if (offset.getFluid() == ModFluids.liquid_aura_still.get() || offset.getFluid() == ModFluids.liquid_aura_flow.get()) {
                        world.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, ModBlocks.aura_block.get().getDefaultState()));
                        this.triggerMixEffects(world, pos);
                        return false;
                    } else if (offset.isTagged(FluidTags.WATER)) {
                        world.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, ModBlocks.gaia_cobblestone.get().getDefaultState()));
                        this.triggerMixEffects(world, pos);
                        return false;
                    }
                }
            }

        } else if (this.getFluid() instanceof LiquidBismuthFluid) {

            for (Direction side : Direction.values()) {
                if (side != Direction.DOWN) {
                    FluidState offset = world.getFluidState(pos.offset(side));

                    if (offset.getFluid() instanceof SweetMuckFluid || offset.getFluid() instanceof SuperhotMagmaFluid) {
                        world.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, ModBlocks.active_rock.get().getDefaultState()));
                        this.triggerMixEffects(world, pos);
                        return false;
                    } else if (offset.getFluid() == ModFluids.liquid_aura_still.get() || offset.getFluid() == ModFluids.liquid_aura_flow.get()) {
                        world.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, ModBlocks.tektite_block.get().getDefaultState()));
                        this.triggerMixEffects(world, pos);
                        return false;
                    } else if (offset.isTagged(FluidTags.WATER)) {
                        world.setBlockState(pos, ForgeEventFactory.fireFluidPlaceBlockEvent(world, pos, pos, ModBlocks.impure_rock.get().getDefaultState()));
                        this.triggerMixEffects(world, pos);
                        return false;
                    }
                }
            }

        }

        return true;
    }

    private void triggerMixEffects(IWorld worldIn, BlockPos pos) {
        worldIn.playEvent(Constants.WorldEvents.LAVA_EXTINGUISH, pos, 0);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (this.getFluid().isIn(FluidTags.LAVA)) {
            if (this.getFluid() instanceof SuperhotMagmaFluid && !entityIn.isImmuneToFire()) {
                entityIn.attackEntityFrom(DamageSource.IN_FIRE, 5.0F);
            }
            entityIn.setFire(15);
        }
    }
}
