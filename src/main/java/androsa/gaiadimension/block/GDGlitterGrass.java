package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDTabs;
import androsa.gaiadimension.registry.ModelRegisterCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDGlitterGrass extends Block implements ModelRegisterCallback {

    public GDGlitterGrass() {
        super(Material.GRASS);

        this.setSoundType(SoundType.PLANT);
        this.setHardness(0.9F);
        this.setTickRandomly(true);
        this.setCreativeTab(GDTabs.tabBlock);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.isRemote)
            if (world.getLightFromNeighbors(pos.up()) < 4 && world.getBlockState(pos.up()).getBlock().getLightOpacity(world.getBlockState(pos.up()), world, pos.up()) > 2)
                world.setBlockState(pos, GDBlocks.heavySoil.getDefaultState());
            else if (world.getLightFromNeighbors(pos.up()) >= 9)
                for (int i = 0; i < 4; ++i) {
                    BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                    Block block = world.getBlockState(blockpos.up()).getBlock();
                    IBlockState iblockstate = world.getBlockState(blockpos);

                    if (iblockstate.getBlock() == GDBlocks.heavySoil && world.getLightFromNeighbors(blockpos.up()) >= 4 && block.getLightOpacity(world.getBlockState(blockpos.up()), world, blockpos.up()) <= 2)
                        world.setBlockState(blockpos, GDBlocks.glitterGrass.getDefaultState());
                }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
