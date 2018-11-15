package androsa.gaiadimension.world;

import androsa.gaiadimension.registry.GDFeature;
import androsa.gaiadimension.structure.StructureGDMajorFeatureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import javax.annotation.Nullable;

public class MapGenGDMajorFeature extends MapGenStructure {

    public MapGenGDMajorFeature() {
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        return GDFeature.getFeatureDirectlyAt(chunkX, chunkZ, world).isStructureEnabled;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        this.rand.setSeed(world.getSeed());
        long rand1 = this.rand.nextLong();
        long rand2 = this.rand.nextLong();
        long chunkXr1 = (long) (chunkX) * rand1;
        long chunkZr2 = (long) (chunkZ) * rand2;
        this.rand.setSeed(chunkXr1 ^ chunkZr2 ^ world.getSeed());
        this.rand.nextInt();

        return new StructureGDMajorFeatureStart(world, rand, chunkX, chunkZ);
    }

    @Override
    public String getStructureName() {
        return "GDFeature";
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, BlockPos pos, boolean findUnexplored) {
        this.world = worldIn;
        return findNearestStructurePosBySpacing(worldIn, this, pos, 20, 11, 10387313, true, 100, findUnexplored);
    }


}
