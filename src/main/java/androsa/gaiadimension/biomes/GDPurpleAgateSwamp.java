package androsa.gaiadimension.biomes;

import androsa.gaiadimension.block.GDCrystalBloom;
import androsa.gaiadimension.block.GDCrystalGrowth;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.GDGenCrystalBloom;
import androsa.gaiadimension.world.GDGenCrystalGrowth;
import androsa.gaiadimension.world.GDGenNoTrees;
import androsa.gaiadimension.world.GDGenPurpleAgateTree;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDPurpleAgateSwamp extends GDBiomeBase {

    private WorldGenTrees GaiaGenPurpleTrees;
    private short[] skyColorRGB = new short[] { 171, 109, 241 };

    public GDPurpleAgateSwamp(BiomeProperties props) {
        super(props);

        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDMystifiedGrowthSapper.class, 9, 3, 5));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDSpellElement.class, 5, 1, 2));

        //TODO: Decorate with unique plants/mobs to stand out more as a swamp

        GaiaGenPurpleTrees = new GDGenPurpleAgateTree(false);

        topBlock = GDBlocks.scentedGrass.getDefaultState();
        fillerBlock = GDBlocks.heavySoil.getDefaultState();
    }

    @SideOnly(Side.CLIENT)
    public final short[] getSkyRGB() {
        return skyColorRGB;
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(5) == 0 ? new GDGenNoTrees() : par1Random.nextInt(3) == 0 ? GaiaGenPurpleTrees : new GDGenNoTrees();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {

        if (rand.nextInt(16) == 0) {
            if (rand.nextInt(4) == 0) {
                return new GDGenCrystalBloom(GDCrystalBloom.CrystalBloomVariant.THISCUS);
            } else {
                return new GDGenCrystalBloom(GDCrystalBloom.CrystalBloomVariant.OUZIUM);
            }
        } else {
            return new GDGenCrystalGrowth(GDCrystalGrowth.CrystalGrowthVariant.PURPLE);
        }
    }
}
