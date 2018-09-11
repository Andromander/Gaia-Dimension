package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenLavaLake;
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
    private GDGenLavaLake extraLavaPoolGen = new GDGenLavaLake(GDBlocks.superhot_magma_block);
    private GDGenLavaLake extraMuckPoolGen = new GDGenLavaLake(GDBlocks.sweet_muck_block);

    private GDGenLavaLake caveLavaGen = new GDGenLavaLake(GDBlocks.superhot_magma_block);

    public World world;
    public Random rand;

    public int lakesPerChunk = 0;
    public float lavaPoolChance = 0;
    public float muckPoolChance = -100;

    @Override
    @SuppressWarnings("deprecated")
    public void decorate(World world, Random rand, Biome biome, BlockPos pos) {

        super.decorate(world, rand, biome, pos);

        this.chunkProviderSettings = ChunkGeneratorSettings.Factory.jsonToFactory(world.getWorldInfo().getGeneratorOptions()).build();

        //Speckled Rock gen
        for (int i = 0; i < 10; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(120);
            new WorldGenMinable(GDBlocks.speckled_rock.getDefaultState(), chunkProviderSettings.diamondSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Coarse Rock gen
        for (int i = 0; i < 10; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(60);
            new WorldGenMinable(GDBlocks.coarse_rock.getDefaultState(), chunkProviderSettings.diamondSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Precious Rock gen
        for (int i = 0; i < 10; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(30);
            new WorldGenMinable(GDBlocks.precious_rock.getDefaultState(), chunkProviderSettings.diamondSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Sugilite Ore gen
        for (int i = 0; i < 8; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.sugilite_ore.getDefaultState(), chunkProviderSettings.coalSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Hematite Ore gen
        for (int i = 0; i < 8; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.hematite_ore.getDefaultState(), chunkProviderSettings.coalSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Pyrite Ore gen
        for (int i = 0; i < 8; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(80);
            new WorldGenMinable(GDBlocks.pyrite_ore.getDefaultState(), chunkProviderSettings.ironSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Cinnabar Ore gen
        for (int i = 0; i < 7; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(60);
            new WorldGenMinable(GDBlocks.cinnabar_ore.getDefaultState(), chunkProviderSettings.ironSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Labradorite Ore gen
        for (int i = 0; i < 6; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(40);
            new WorldGenMinable(GDBlocks.labradorite_ore.getDefaultState(), chunkProviderSettings.goldSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Moonstone Ore gen
        for (int i = 0; i < 6; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(40);
            new WorldGenMinable(GDBlocks.moonstone_ore.getDefaultState(), chunkProviderSettings.goldSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //White Opal Ore gen
        if (biome instanceof GDMutantAgateWildwood) {
            for (int i = 0; i < 4; i++) {
                int Xcoord = pos.getX() + rand.nextInt(16);
                int Zcoord = pos.getZ() + rand.nextInt(16);
                int Ycoord = rand.nextInt(20);
                new WorldGenMinable(GDBlocks.opal_ore_white.getDefaultState(), chunkProviderSettings.diamondSize,
                        input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
            }
        } else {
            for (int i = 0; i < 3; i++) {
                int Xcoord = pos.getX() + rand.nextInt(16);
                int Zcoord = pos.getZ() + rand.nextInt(16);
                int Ycoord = rand.nextInt(25);
                new WorldGenMinable(GDBlocks.opal_ore_white.getDefaultState(), chunkProviderSettings.diamondSize,
                        input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
            }
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

        //Handles the number of Lava Pools per chunk
        if (randomGenerator.nextFloat() <= lavaPoolChance) {
            int rx = chunkPos.getX() + randomGenerator.nextInt(16) + 8;
            int rz = chunkPos.getZ() + randomGenerator.nextInt(16) + 8;
            extraLavaPoolGen.generate(world, randomGenerator, world.getHeight(new BlockPos(rx, 0, rz)));
        }

        //Handles the number of Muck Pools per chunk
        if (randomGenerator.nextFloat() <= muckPoolChance) {
            int rx = chunkPos.getX() + randomGenerator.nextInt(16) + 8;
            int rz = chunkPos.getZ() + randomGenerator.nextInt(16) + 8;
            extraMuckPoolGen.generate(world, randomGenerator, world.getHeight(new BlockPos(rx, 0, rz)));
        }

        super.genDecorations(biome, world, randomGenerator);

        decorateUnderground(world, randomGenerator, chunkPos);
    }

    protected void decorateUnderground(World world, Random rand, BlockPos pos) {
        //magma, magma everywhere
        if (this.generateFalls) {
            for (int i = 0; i < 25; ++i) {
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
