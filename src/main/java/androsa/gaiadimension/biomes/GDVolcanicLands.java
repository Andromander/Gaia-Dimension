package androsa.gaiadimension.biomes;

import androsa.gaiadimension.block.GDCrystalGrowth;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDVolcanicLands extends GDBiomeBase {

    private WorldGenTrees GaiaGenBurntTrees = new GDGenBurntAgateTree(false);
    private short[] skyColorRGB = new short[] { 75, 30, 25 };

    public GDVolcanicLands(Biome.BiomeProperties props) {
        super(props);

        topBlock = GDBlocks.singedGrass.getDefaultState();
        fillerBlock = GDBlocks.heavySoil.getDefaultState();

        getGDBiomeDecorator().lavaPoolChance = 0.25F;
    }

    //TODO: Generate Volcanic Rock as an ore

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
        return par1Random.nextInt(2) == 0 ? new GDGenNoTrees() : par1Random.nextInt(16) == 0 ? GaiaGenBurntTrees : new GDGenNoTrees();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new GDGenCrystalGrowth(GDCrystalGrowth.CrystalGrowthVariant.SEARED);
    }

    @SideOnly(Side.CLIENT)
    public final short[] getSkyRGB() {
        return skyColorRGB;
    }
}
