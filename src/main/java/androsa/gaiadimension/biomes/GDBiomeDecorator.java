package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.registry.GDFluids;
import androsa.gaiadimension.world.layer.GDGenLavaLake;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.WorldGenMinable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GDBiomeDecorator extends BiomeDecorator {
   // private WorldGenLakes extraLakeGen = new WorldGenLakes(GDFluids.mineralWaterBlock); //Bring this back if necessary, but I doubt that
    private GDGenLavaLake extraLavaPoolGen = new GDGenLavaLake(GDFluids.superhotMagmaBlock);

    private GDGenLavaLake caveLavaGen = new GDGenLavaLake(GDFluids.superhotMagmaBlock);

    public World world;
    public Random rand;
   // public int lakesPerChunk = 0;
    public float lavaPoolChance = 0;

    @Override
    public void decorate(World world, Random rand, Biome biome, BlockPos pos) {

        super.decorate(world, rand, biome, pos);

        this.chunkProviderSettings = ChunkGeneratorSettings.Factory.jsonToFactory(world.getWorldInfo().getGeneratorOptions()).build();

        //Hematite Ore gen
        for (int i = 0; i < 8; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.hematiteOre.getDefaultState(), chunkProviderSettings.coalSize, input -> input == GDBlocks.gaiaStone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Pyrite Ore gen
        for (int i = 0; i < 8; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(60);
            new WorldGenMinable(GDBlocks.pyriteOre.getDefaultState(), chunkProviderSettings.ironSize, input -> input == GDBlocks.gaiaStone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Cinnabar Ore gen
        for (int i = 0; i < 7; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.cinnabarOre.getDefaultState(), chunkProviderSettings.ironSize, input -> input == GDBlocks.gaiaStone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Labradorite Ore gen
        for (int i = 0; i < 6; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.labradoriteOre.getDefaultState(), chunkProviderSettings.goldSize, input -> input == GDBlocks.gaiaStone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Moonstone Ore gen
        for (int i = 0; i < 6; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.moonstoneOre.getDefaultState(), chunkProviderSettings.goldSize, input -> input == GDBlocks.gaiaStone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }

        decorateUnderground(world, rand, pos);
    }
        //GDFeature nearFeature = GDFeature.getNearestFeature(pos.getX() >> 4, pos.getZ() >> 4, world);
/*
        if (!nearFeature.areChunkDecorationsEnabled) {

        } else {
            super.decorate(world, rand, biome, pos);
        }
    }
*/
    @Override
    protected void genDecorations(Biome biome, World world, Random randomGenerator) {
        if (randomGenerator.nextInt(6) == 0) {
            int rx = chunkPos.getX() + randomGenerator.nextInt(14) + 8;
            int rz = chunkPos.getZ() + randomGenerator.nextInt(14) + 8;
        }

        //More magma in volcanic biomes
        if (randomGenerator.nextFloat() <= lavaPoolChance) {
            int rx = chunkPos.getX() + randomGenerator.nextInt(16) + 8;
            int rz = chunkPos.getZ() + randomGenerator.nextInt(16) + 8;
            extraLavaPoolGen.generate(world, randomGenerator, world.getHeight(new BlockPos(rx, 0, rz)));
        }

        super.genDecorations(biome, world, randomGenerator);

        decorateUnderground(world, randomGenerator, chunkPos);
    }

    protected void decorateUnderground(World world, Random rand, BlockPos pos) {
        //magma, magma everywhere
        if (this.generateFalls) {
            for (int i = 0; i < 50; ++i) {
                int rx = pos.getX() + rand.nextInt(16) + 8;
                int ry = rand.nextInt(24) + 4;
                int rz = pos.getZ() + rand.nextInt(16) + 8;
                caveLavaGen.generate(world, rand, new BlockPos(rx, ry, rz));
            }
        }
    }

    public void setTreesPerChunk(int treesPerChunk) {
        this.treesPerChunk = treesPerChunk;
    }

    public void setGrassPerChunk(int grassPerChunk) {
        this.grassPerChunk = grassPerChunk;
    }
}
