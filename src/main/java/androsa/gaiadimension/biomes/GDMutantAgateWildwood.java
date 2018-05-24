package androsa.gaiadimension.biomes;

import androsa.gaiadimension.block.GDCrystalBloom;
import androsa.gaiadimension.block.GDCrystalGrowth;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDMutantAgateWildwood extends GDBiomeBase {

    private static final GDGenPinkAgateTree GaiaGenPinkTrees = new GDGenPinkAgateTree(false);           //30% chance
    private static final GDGenBlueAgateTreeSmall GaiaGenBlueTrees = new GDGenBlueAgateTreeSmall(false); //30% chance
    private static final GDGenGreenAgateTree GaiaGenGreenTrees = new GDGenGreenAgateTree(false);        //30% chance
    private static final GDGenPurpleAgateTree GaiaGenPurpleTrees = new GDGenPurpleAgateTree(false);     //10% chance
    private short[] skyColorRGB = new short[] { 241, 154, 193 };

    public GDMutantAgateWildwood(BiomeProperties props) {
        super(props);

        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDCommonGrowthSapper.class, 10, 3, 5));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDChilledGrowthSapper.class, 10, 3, 5));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDNutrientGrowthSapper.class, 10, 3, 5));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDMystifiedGrowthSapper.class, 10, 3, 5));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDMutantGrowthExtractor.class, 5, 2, 4));

        topBlock = GDBlocks.mutantGrass.getDefaultState();
        fillerBlock = GDBlocks.heavySoil.getDefaultState();
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {

        if (par1Random.nextInt(8) == 0) {
            if (par1Random.nextInt(7) == 0) {
                return GaiaGenPurpleTrees;
            } else if (par1Random.nextInt(3) == 0) {
                return GaiaGenGreenTrees;
            } else if (par1Random.nextInt(3) == 0) {
                return GaiaGenBlueTrees;
            } else {
                return GaiaGenPinkTrees;
            }
        } else {
                return new GDGenNoTrees();
        }
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {

        if (rand.nextInt(16) == 0) {
            if (rand.nextInt(4) == 0) {
                return new GDGenCrystalBloom(GDCrystalBloom.CrystalBloomVariant.AGATHUM);
            } else {
                return new GDGenCrystalBloom(GDCrystalBloom.CrystalBloomVariant.OUZIUM);
            }
        } else {
            return new GDGenCrystalGrowth(GDCrystalGrowth.CrystalGrowthVariant.MUTANT);
        }
    }

    @SideOnly(Side.CLIENT)
    public final short[] getSkyRGB() {
        return skyColorRGB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0xE0EDCB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0xE0EDCB;
    }
}
