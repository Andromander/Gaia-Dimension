package androsa.gaiadimension.block;

import androsa.gaiadimension.registry.registration.ModBlocks;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CrystalFungusBlock extends BushBlock {
    public static final MapCodec<? extends CrystalFungusBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(Codec.BOOL.fieldOf("cavernous").forGetter(obj -> obj.cavernous), propertiesCodec())
            .apply(instance, CrystalFungusBlock::new));
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    private final boolean cavernous;

    public CrystalFungusBlock(boolean isCave, Properties props) {
        super(props);

        cavernous = isCave;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos){
        if (cavernous) {
            return state.getBlock() == ModBlocks.gaia_stone.get() ||
                    state.getBlock() == ModBlocks.primal_mass.get() ||
                    state.getBlock() == ModBlocks.wasteland_stone.get() ||
                    state.getBlock() == ModBlocks.volcanic_rock.get();
        } else {
            return state.getBlock() instanceof AbstractGaiaGrassBlock;
        }
    }

    //TODO: Grow into giant Fungus?

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        double d0 = (double)pos.getX() + rand.nextDouble() * 0.6D + 0.2D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 0.6D + 0.2D;
        double d2 = (double)pos.getZ() + rand.nextDouble() * 0.6D + 0.2D;

        worldIn.addParticle(ParticleTypes.MYCELIUM, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    @Deprecated
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
