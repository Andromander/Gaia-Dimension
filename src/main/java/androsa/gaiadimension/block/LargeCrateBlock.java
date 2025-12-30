package androsa.gaiadimension.block;

import androsa.gaiadimension.block.blockentity.LargeCrateBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.List;

public class LargeCrateBlock extends Block implements EntityBlock {

    public static final MapCodec<? extends LargeCrateBlock> CODEC = simpleCodec(LargeCrateBlock::new);
    public static final Identifier NAME = Identifier.withDefaultNamespace("contents");

    public LargeCrateBlock(Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new LargeCrateBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    @Deprecated
    public InteractionResult useWithoutItem(BlockState state, Level worldIn, BlockPos pos, Player player, BlockHitResult hit) {
        if (worldIn.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else if (player.isSpectator()) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof LargeCrateBlockEntity crate) {
                player.openMenu(crate);
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    @Override
    public BlockState playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof LargeCrateBlockEntity crate) {
            if (!worldIn.isClientSide() && player.isCreative() && !crate.isEmpty()) {
                ItemStack itemstack = new ItemStack(this);
                itemstack.applyComponents(tileentity.collectComponents());
                ItemEntity itementity = new ItemEntity(worldIn, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, itemstack);
                itementity.setDefaultPickUpDelay();
                worldIn.addFreshEntity(itementity);
            } else {
                crate.unpackLootTable(player);
            }
        }

        return super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Override
    @Deprecated
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        BlockEntity tileentity = builder.getParameter(LootContextParams.BLOCK_ENTITY);
        if (tileentity instanceof LargeCrateBlockEntity crate) {
            builder = builder.withDynamicDrop(NAME, (stack) -> {
                for(int i = 0; i < crate.getContainerSize(); ++i) {
                    stack.accept(crate.getItem(i));
                }
            });
        }

        return super.getDrops(state, builder);
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean isMoving) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

//    @Override
//    @Deprecated
//    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
//        if (state.getBlock() != newState.getBlock()) {
//            BlockEntity tileentity = worldIn.getBlockEntity(pos);
//            if (tileentity instanceof LargeCrateBlockEntity) {
//                worldIn.updateNeighbourForOutputSignal(pos, state.getBlock());
//            }
//
//            super.onRemove(state, worldIn, pos, newState, isMoving);
//        }
//    }

    @Override
    @Deprecated
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos, Direction dir) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer((Container)worldIn.getBlockEntity(pos));
    }
}
