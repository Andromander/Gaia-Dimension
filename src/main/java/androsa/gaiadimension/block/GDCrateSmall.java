package androsa.gaiadimension.block;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.block.tileentity.TileEntitySmallCrate;
import androsa.gaiadimension.proxy.CommonProxy;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class GDCrateSmall extends Block implements ModelRegisterCallback, ITileEntityProvider {

    public GDCrateSmall() {
        super(Material.ROCK);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setHarvestLevel("axe", 0);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySmallCrate();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote)
            FMLNetworkHandler.openGui(playerIn, GaiaDimension.instance, CommonProxy.GuiID.SMALL_CRATE.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (worldIn.getTileEntity(pos) instanceof TileEntitySmallCrate) {
            TileEntitySmallCrate tileEntitySmallCrate = (TileEntitySmallCrate)worldIn.getTileEntity(pos);
            tileEntitySmallCrate.setDestroyedByCreativePlayer(player.capabilities.isCreativeMode);
            tileEntitySmallCrate.fillWithLoot(player);
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntitySmallCrate) {
                ((TileEntitySmallCrate)tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntitySmallCrate) {
            TileEntitySmallCrate tileEntitySmallCrate = (TileEntitySmallCrate)tileentity;

            if (!tileEntitySmallCrate.isCleared() && tileEntitySmallCrate.shouldDrop()) {
                ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound.setTag("BlockEntityTag", ((TileEntitySmallCrate)tileentity).saveToNbt(nbttagcompound1));
                itemstack.setTagCompound(nbttagcompound);

                if (tileEntitySmallCrate.hasCustomName()) {
                    itemstack.setStackDisplayName(tileEntitySmallCrate.getName());
                    tileEntitySmallCrate.setCustomName("");
                }

                spawnAsEntity(worldIn, pos, itemstack);
            }

            worldIn.updateComparatorOutputLevel(pos, state.getBlock());
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null && nbttagcompound.hasKey("BlockEntityTag", 10)) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("BlockEntityTag");

            if (nbttagcompound1.hasKey("LootTable", 8)) {
                tooltip.add("???????");
            }

            if (nbttagcompound1.hasKey("Items", 9)) {
                NonNullList<ItemStack> nonnulllist = NonNullList.withSize(27, ItemStack.EMPTY);
                ItemStackHelper.loadAllItems(nbttagcompound1, nonnulllist);
                int i = 0;
                int j = 0;

                for (ItemStack itemstack : nonnulllist) {
                    if (!itemstack.isEmpty()) {
                        ++j;

                        if (i <= 4) {
                            ++i;
                            tooltip.add(String.format("%s x%d", itemstack.getDisplayName(), itemstack.getCount()));
                        }
                    }
                }

                if (j - i > 0) {
                    tooltip.add(I18n.format(TextFormatting.ITALIC + (getTranslationKey() + ".more"), j - i));
                }
            }
        }
    }

    @Override
    @Deprecated
    public EnumPushReaction getPushReaction(IBlockState state) {
        return EnumPushReaction.DESTROY;
    }

    @Override
    @Deprecated
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    @Deprecated
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstoneFromInventory((IInventory)worldIn.getTileEntity(pos));
    }

    @Override
    @Deprecated
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        ItemStack itemstack = super.getItem(worldIn, pos, state);
        TileEntitySmallCrate tileEntitySmallCrate = (TileEntitySmallCrate)worldIn.getTileEntity(pos);
        NBTTagCompound nbttagcompound = tileEntitySmallCrate.saveToNbt(new NBTTagCompound());

        if (!nbttagcompound.isEmpty()) {
            itemstack.setTagInfo("BlockEntityTag", nbttagcompound);
        }

        return itemstack;
    }
}
