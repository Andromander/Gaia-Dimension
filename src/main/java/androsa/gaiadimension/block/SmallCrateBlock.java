package androsa.gaiadimension.block;

import androsa.gaiadimension.block.blockentity.SmallCrateBlockEntity;
import androsa.gaiadimension.registry.registration.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.List;

public class SmallCrateBlock extends Block implements EntityBlock {

    public static final MapCodec<SmallCrateBlock> CODEC = simpleCodec(SmallCrateBlock::new);
    public static final ResourceLocation NAME = new ResourceLocation("contents");

    public SmallCrateBlock(Properties props) {
        super(props);
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
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
            if (tileentity instanceof SmallCrateBlockEntity crate) {
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
        if (tileentity instanceof SmallCrateBlockEntity crate) {
            if (!worldIn.isClientSide() && player.isCreative() && !crate.isEmpty()) {
                ItemStack itemstack = new ItemStack(this);
                CompoundTag compoundnbt = crate.saveToNbt(new CompoundTag());
                if (!compoundnbt.isEmpty()) {
                    itemstack.addTagElement("BlockEntityTag", compoundnbt);
                }

                if (crate.hasCustomName()) {
                    itemstack.setHoverName(crate.getCustomName());
                }

                ItemEntity itementity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemstack);
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
        if (tileentity instanceof SmallCrateBlockEntity crate) {
            builder = builder.withDynamicDrop(NAME, (stack) -> {
                for(int i = 0; i < crate.getContainerSize(); ++i) {
                    stack.accept(crate.getItem(i));
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
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        CompoundTag compoundnbt = stack.getTagElement("BlockEntityTag");
        if (compoundnbt != null) {
            if (compoundnbt.contains("LootTable", 8)) {
                tooltip.add(Component.literal("???????"));
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
                    tooltip.add(Component.translatable("container.shulkerBox.more", j - i).withStyle(ChatFormatting.ITALIC));
                }
            }
        }
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
    public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
        ItemStack itemstack = super.getCloneItemStack(worldIn, pos, state);
        worldIn.getBlockEntity(pos, ModBlockEntities.SMALL_CRATE.get()).ifPresent(entity -> entity.saveToItem(itemstack));
        return itemstack;
    }
}
