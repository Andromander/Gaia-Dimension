package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class CrystalFungusBlock extends BushBlock {

    private boolean cavernous;

    public CrystalFungusBlock(MaterialColor color, boolean isCave) {
        super(Properties.create(Material.PLANTS, color).hardnessAndResistance(0.0F).sound(SoundType.PLANT).doesNotBlockMovement());

        cavernous = isCave;
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos){
        BlockState ground = worldIn.getBlockState(pos.down());

        if (cavernous) {
            return ground.getBlock() == ModBlocks.gaia_stone ||
                    ground.getBlock() == ModBlocks.primal_mass ||
                    ground.getBlock() == ModBlocks.wasteland_stone ||
                    ground.getBlock() == ModBlocks.volcanic_rock;
        } else {
            return ground.getBlock() instanceof AbstractGaiaGrassBlock;
        }
    }

    //TODO: Grow into giant Fungus?

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double)pos.getX() + rand.nextDouble() * 0.6D + 0.2D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 0.6D + 0.2D;
        double d2 = (double)pos.getZ() + rand.nextDouble() * 0.6D + 0.2D;

        worldIn.addParticle(ParticleTypes.UNDERWATER, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
