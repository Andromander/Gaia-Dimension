package androsa.gaiadimension.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.IPlantable;
import net.neoforged.neoforge.common.PlantType;

public class AbstractGaiaGrassBlock extends Block implements BonemealableBlock {

    public static final MapCodec<? extends AbstractGaiaGrassBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(BlockState.CODEC.fieldOf("dirt_block").forGetter(obj -> obj.dirt),
                            propertiesCodec())
                    .apply(instance, AbstractGaiaGrassBlock::new)
    );
    private final BlockState dirt;

    public AbstractGaiaGrassBlock(BlockState state, Properties props) {
        super(props);

        dirt = state;
    }

    @Override
    @Deprecated
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 3)) return;
        if (!isLightEnough(state, level, pos)) {
            level.setBlockAndUpdate(pos, dirt);
        } else if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
            BlockState blockstate = this.defaultBlockState();

            for (int i = 0; i < 4; ++i) {
                BlockPos blockpos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if (level.getBlockState(blockpos) == dirt && isValidBonemealTargetGrass(blockstate, level, blockpos)) {
                    level.setBlockAndUpdate(blockpos, blockstate);
                }
            }
        }
    }

    private static boolean isLightEnough(BlockState state, LevelAccessor reader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        BlockState blockstate = reader.getBlockState(blockpos);

        int i = LightEngine.getLightBlockInto(reader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(reader, blockpos));
        return i < reader.getMaxLightLevel();
    }

    private static boolean isValidBonemealTargetGrass(BlockState state, LevelAccessor reader, BlockPos pos) {
        BlockPos blockpos = pos.above();
        return isLightEnough(state, reader, pos) && !reader.getFluidState(blockpos).is(FluidTags.WATER);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, IPlantable plantable) {
        boolean hasWater = false;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockState facingState = world.getBlockState(pos.relative(dir));
            FluidState facingFluid = world.getFluidState(pos.relative(dir));
            hasWater = hasWater || facingState.is(Blocks.FROSTED_ICE) || facingFluid.is(FluidTags.WATER);
        }
        return plantable.getPlantType(world, pos.relative(facing)) == PlantType.PLAINS ||
                plantable.getPlantType(world, pos.relative(facing)) == PlantType.BEACH && hasWater;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader reader, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) { }
}
