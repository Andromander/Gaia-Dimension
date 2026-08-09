package androsa.gaiadimension.world.gen.feature.fungi;

import androsa.gaiadimension.world.gen.feature.config.CrystalFungiConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class HugeBulbousHobinaFeature extends HugeCrystalFungiFeature {

    public HugeBulbousHobinaFeature(Codec<CrystalFungiConfig> codec) {
        super(codec);
    }

    @Override
    protected BlockPos generateStalk(WorldGenLevel level, RandomSource random, BlockPos origin, CrystalFungiConfig config) {
        int height = config.stemHeight();

        for (int y = 0; y <= height; y++) {
            placeBlock(level, origin.above(y), config.primaryStem());
        }

        BlockPos.MutableBlockPos mutable = origin.mutable().setY(origin.getY() + height);
        Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        List<Direction> branches = new ArrayList<>(List.of(dir, dir.getClockWise(), dir.getCounterClockWise()));
        List<BlockPos> branchpos = new ArrayList<>();
        List<BlockPos> backpos = new ArrayList<>();

        for (int i = 1; i < 3; i++) {
            placeBlock(level, mutable.setWithOffset(mutable, dir.getUnitVec3i()), setRotated(config.primaryStem(), dir));
        }

        placeBlock(level, mutable.above(), config.primaryStem());

        mutable.setWithOffset(mutable, dir.getUnitVec3i());

        for (int i = 1; i <= 4; i++) {
            BlockPos pos = mutable.setY(mutable.above().getY());
            branchpos.add(pos.immutable());
            backpos.add(pos.immutable());
            placeBlock(level, pos, config.primaryStem());
        }

        placeBlock(level, mutable.offset(dir.getOpposite().getUnitVec3i()), config.primaryStem());

        mutable.setY(mutable.above().getY());

        for (int i = 1; i < 5; i++) {
            BlockPos pos = mutable.setWithOffset(mutable, dir.getOpposite().getUnitVec3i());
            branchpos.add(pos.immutable());
            placeBlock(level, pos, setRotated(config.primaryStem(), dir));
        }

        mutable.setY(mutable.below().getY());
        placeBlock(level, mutable.setWithOffset(mutable, dir.getOpposite().getUnitVec3i()), config.primaryStem());

        List<BlockPos> list = new ArrayList<>(branchpos);
        for (Direction branch : branches) {
            if (branch == dir) {
                BlockPos back = backpos.get(random.nextInt(backpos.size()));
                setBranch(level, random, back.offset(branch.getUnitVec3i()), config.primaryStem(), branch, config);
                list.remove(back);
            } else {
                BlockPos pos = list.get(random.nextInt(list.size()));
                if (list.contains(pos)) {
                    setBranch(level, random, pos.offset(branch.getUnitVec3i()), config.primaryStem(), branch, config);
                    list.remove(pos);
                }
            }
        }

        generateBulb(level, random, mutable.below(), config, dir.getOpposite());
        return mutable.below();
    }

    private void setBranch(WorldGenLevel level, RandomSource random, BlockPos pos, BlockState state, Direction dir, CrystalFungiConfig config) {
        BlockPos.MutableBlockPos mutable = pos.mutable();
        for (int i = 0; i < 2; i++) {
            placeBlock(level, mutable.setWithOffset(mutable, dir.getUnitVec3i().multiply(i)), setRotated(state, dir));
        }

        generateBulb(level, random, mutable.below(), config, dir);
    }

    private BlockState setRotated(BlockState state, Direction dir) {
        if (state.hasProperty(RotatedPillarBlock.AXIS)) {
            return state.setValue(RotatedPillarBlock.AXIS, dir.getAxis());
        }
        return state;
    }

    protected void generateBulb(WorldGenLevel level, RandomSource rand, BlockPos origin, CrystalFungiConfig config, Direction direction) {
        Vec3i outoffset = direction.getUnitVec3i();
        Vec3i sideoffset = rand.nextBoolean() ? direction.getClockWise().getUnitVec3i() : direction.getCounterClockWise().getUnitVec3i();
        for (int y = 0; y > -2; y--) {
            placeBlock(level, origin.offset(0, y, 0), config.primaryCap());
            placeBlock(level, origin.offset(outoffset.getX(), y, outoffset.getZ()), config.primaryCap());
            placeBlock(level, origin.offset(sideoffset.getX(), y, sideoffset.getZ()), config.primaryCap());
            placeBlock(level, origin.offset(outoffset.getX() + sideoffset.getX(), y, outoffset.getZ() + sideoffset.getZ()), config.primaryCap());
        }
    }

    @Override
    protected void generateCap(WorldGenLevel level, RandomSource rand, BlockPos origin, CrystalFungiConfig config) {

    }

    @Override
    public int getCapHeight() {
        return 6;
    }

    @Override
    protected int getMaxCapRadius() {
        return 4;
    }
}
