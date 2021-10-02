package androsa.gaiadimension.block;

import androsa.gaiadimension.block.blockentity.SmallCrateBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class SmallCrateBlock extends Block implements EntityBlock {

    public static final ResourceLocation NAME = new ResourceLocation("contents");

    public SmallCrateBlock(Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SmallCrateBlockEntity(pos, state);
    }

    @Override
    @Deprecated
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (worldIn.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else if (player.isSpectator()) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof SmallCrateBlockEntity) {
                SmallCrateBlockEntity smallcratetileentity = (SmallCrateBlockEntity)tileentity;
                player.openMenu(smallcratetileentity);
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }
    }

    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof SmallCrateBlockEntity) {
            SmallCrateBlockEntity smallcratetileentity = (SmallCrateBlockEntity)tileentity;
            if (!worldIn.isClientSide() && player.isCreative() && !smallcratetileentity.isEmpty()) {
                ItemStack itemstack = new ItemStack(this);
                CompoundTag compoundnbt = smallcratetileentity.saveToNbt(new CompoundTag());
                if (!compoundnbt.isEmpty()) {
                    itemstack.addTagElement("BlockEntityTag", compoundnbt);
                }

                if (smallcratetileentity.hasCustomName()) {
                    itemstack.setHoverName(smallcratetileentity.getCustomName());
                }

                ItemEntity itementity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemstack);
                itementity.setDefaultPickUpDelay();
                worldIn.addFreshEntity(itementity);
            } else {
                smallcratetileentity.unpackLootTable(player);
            }
        }

        super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Override
    @Deprecated
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        BlockEntity tileentity = builder.getParameter(LootContextParams.BLOCK_ENTITY);
        if (tileentity instanceof SmallCrateBlockEntity) {
            SmallCrateBlockEntity smallcratetileentity = (SmallCrateBlockEntity)tileentity;
            builder = builder.withDynamicDrop(NAME, (loot, stack) -> {
                for(int i = 0; i < smallcratetileentity.getContainerSize(); ++i) {
                    stack.accept(smallcratetileentity.getItem(i));
                }
            });
        }

        return super.getDrops(state, builder);
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof SmallCrateBlockEntity) {
                ((SmallCrateBlockEntity)tileentity).setCustomName(stack.getHoverName());
            }
        }
    }

    @Override
    @Deprecated
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof SmallCrateBlockEntity) {
                worldIn.updateNeighbourForOutputSignal(pos, state.getBlock());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        CompoundTag compoundnbt = stack.getTagElement("BlockEntityTag");
        if (compoundnbt != null) {
            if (compoundnbt.contains("LootTable", 8)) {
                tooltip.add(new TextComponent("???????"));
            }

            if (compoundnbt.contains("Items", 9)) {
                NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
                ContainerHelper.loadAllItems(compoundnbt, nonnulllist);
                int i = 0;
                int j = 0;

                for(ItemStack itemstack : nonnulllist) {
                    if (!itemstack.isEmpty()) {
                        ++j;
                        if (i <= 4) {
                            ++i;
                            MutableComponent itextcomponent = itemstack.getHoverName().copy();
                            itextcomponent.append(" x").append(String.valueOf(itemstack.getCount()));
                            tooltip.add(itextcomponent);
                        }
                    }
                }

                if (j - i > 0) {
                    tooltip.add((new TranslatableComponent("container.shulkerBox.more", j - i)).withStyle(ChatFormatting.ITALIC));
                }
            }
        }
    }

    @Override
    @Deprecated
    public PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }

    @Override
    @Deprecated
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer((Container)worldIn.getBlockEntity(pos));
    }

    @Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
        ItemStack itemstack = super.getCloneItemStack(worldIn, pos, state);
        SmallCrateBlockEntity smallcratetileentity = (SmallCrateBlockEntity)worldIn.getBlockEntity(pos);
        CompoundTag compoundnbt = smallcratetileentity.saveToNbt(new CompoundTag());
        if (!compoundnbt.isEmpty()) {
            itemstack.addTagElement("BlockEntityTag", compoundnbt);
        }

        return itemstack;
    }
}
