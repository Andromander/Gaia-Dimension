package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDCrystalFungus extends BlockBush implements ModelRegisterCallback {

    private final MapColor mapColor;
    private boolean cavernous;

    public GDCrystalFungus(MapColor color, boolean isCave) {
        super(Material.PLANTS);

        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.setCreativeTab(GDTabs.tabBlock);
        this.setTickRandomly(true);
        mapColor = color;
        cavernous = isCave;
    }

    @Override
    @Deprecated
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return mapColor;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        IBlockState ground = worldIn.getBlockState(pos.down());

        if (cavernous) {
            return this.canBlockStay(worldIn, pos, this.getDefaultState());
        } else {
            return super.canPlaceBlockAt(worldIn, pos) && ground.getBlock().canSustainPlant(ground, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this);
        }
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
        if (cavernous) {
            if (pos.getY() >= 0 && pos.getY() < 256) {
                IBlockState iblockstate = worldIn.getBlockState(pos.down());

                if (iblockstate.getBlock() == GDBlocks.gaia_stone ||
                        iblockstate.getBlock() == GDBlocks.primal_mass ||
                        iblockstate.getBlock() == GDBlocks.wasteland_stone ||
                        iblockstate.getBlock() == GDBlocks.volcanic_rock) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return super.canBlockStay(worldIn, pos, this.getDefaultState());
        }
    }

    //TODO: Grow into giant Fungus?

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double)pos.getX() + rand.nextDouble() * 0.6D + 0.2D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 0.6D + 0.2D;
        double d2 = (double)pos.getZ() + rand.nextDouble() * 0.6D + 0.2D;

        worldIn.spawnParticle(EnumParticleTypes.SUSPENDED_DEPTH, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation((Item.getItemFromBlock(this)).getRegistryName(), "inventory"));
    }
}
