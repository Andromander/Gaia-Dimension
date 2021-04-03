package androsa.gaiadimension.block;

import androsa.gaiadimension.block.tileentity.LargeCrateTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.*;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class LargeCrateBlock extends Block {

    public static final ResourceLocation NAME = new ResourceLocation("contents");

    public LargeCrateBlock(Properties props) {
        super(props);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new LargeCrateTileEntity();
    }

    @Override
    @Deprecated
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isClientSide()) {
            return ActionResultType.SUCCESS;
        } else if (player.isSpectator()) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof LargeCrateTileEntity) {
                LargeCrateTileEntity largecratetileentity = (LargeCrateTileEntity)tileentity;
                player.openMenu(largecratetileentity);
                return ActionResultType.SUCCESS;
            } else {
                return ActionResultType.PASS;
            }
        }
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        TileEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof LargeCrateTileEntity) {
            LargeCrateTileEntity largecratetileentity = (LargeCrateTileEntity)tileentity;
            if (!worldIn.isClientSide() && player.isCreative() && !largecratetileentity.isEmpty()) {
                ItemStack itemstack = new ItemStack(this);
                CompoundNBT compoundnbt = largecratetileentity.saveToNbt(new CompoundNBT());
                if (!compoundnbt.isEmpty()) {
                    itemstack.addTagElement("BlockEntityTag", compoundnbt);
                }

                if (largecratetileentity.hasCustomName()) {
                    itemstack.setHoverName(largecratetileentity.getCustomName());
                }

                ItemEntity itementity = new ItemEntity(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), itemstack);
                itementity.setDefaultPickUpDelay();
                worldIn.addFreshEntity(itementity);
            } else {
                largecratetileentity.unpackLootTable(player);
            }
        }

        super.playerWillDestroy(worldIn, pos, state, player);
    }

    @Override
    @Deprecated
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        TileEntity tileentity = builder.getParameter(LootParameters.BLOCK_ENTITY);
        if (tileentity instanceof LargeCrateTileEntity) {
            LargeCrateTileEntity largecratetileentity = (LargeCrateTileEntity)tileentity;
            builder = builder.withDynamicDrop(NAME, (loot, stack) -> {
                for(int i = 0; i < largecratetileentity.getContainerSize(); ++i) {
                    stack.accept(largecratetileentity.getItem(i));
                }
            });
        }

        return super.getDrops(state, builder);
    }

    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (stack.hasCustomHoverName()) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof LargeCrateTileEntity) {
                ((LargeCrateTileEntity)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }

    @Override
    @Deprecated
    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof LargeCrateTileEntity) {
                worldIn.updateNeighbourForOutputSignal(pos, state.getBlock());
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        CompoundNBT compoundnbt = stack.getTagElement("BlockEntityTag");
        if (compoundnbt != null) {
            if (compoundnbt.contains("LootTable", 8)) {
                tooltip.add(new StringTextComponent("???????"));
            }

            if (compoundnbt.contains("Items", 9)) {
                NonNullList<ItemStack> nonnulllist = NonNullList.withSize(54, ItemStack.EMPTY);
                ItemStackHelper.loadAllItems(compoundnbt, nonnulllist);
                int i = 0;
                int j = 0;

                for(ItemStack itemstack : nonnulllist) {
                    if (!itemstack.isEmpty()) {
                        ++j;
                        if (i <= 4) {
                            ++i;
                            IFormattableTextComponent itextcomponent = itemstack.getHoverName().copy();
                            itextcomponent.append(" x").append(String.valueOf(itemstack.getCount()));
                            tooltip.add(itextcomponent);
                        }
                    }
                }

                if (j - i > 0) {
                    tooltip.add((new TranslationTextComponent("container.shulkerBox.more", j - i)).withStyle(TextFormatting.ITALIC));
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
    public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.getRedstoneSignalFromContainer((IInventory)worldIn.getBlockEntity(pos));
    }

    @Override
    @Deprecated
    @OnlyIn(Dist.CLIENT)
    public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
        ItemStack itemstack = super.getCloneItemStack(worldIn, pos, state);
        LargeCrateTileEntity largecratetileentity = (LargeCrateTileEntity)worldIn.getBlockEntity(pos);
        CompoundNBT compoundnbt = largecratetileentity.saveToNbt(new CompoundNBT());
        if (!compoundnbt.isEmpty()) {
            itemstack.addTagElement("BlockEntityTag", compoundnbt);
        }

        return itemstack;
    }
}
