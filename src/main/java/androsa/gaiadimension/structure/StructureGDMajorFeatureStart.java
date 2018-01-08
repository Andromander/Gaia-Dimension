package androsa.gaiadimension.structure;

import androsa.gaiadimension.registry.GDFeature;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureStart;

import java.util.Random;

public class StructureGDMajorFeatureStart extends StructureStart {

    public static int NUM_LOCKS = 4;

            public GDFeature feature;
    public byte[] lockBytes = new byte[NUM_LOCKS];

    static {
        MapGenStructureIO.registerStructure(StructureGDMajorFeatureStart.class, "GDFeature");
    }

    public StructureGDMajorFeatureStart() {
    }

    public StructureGDMajorFeatureStart(World world, Random rand, int chunkX, int chunkZ) {
        this.feature = GDFeature.getFeatureDirectlyAt(chunkX, chunkZ, world);
    }
}
