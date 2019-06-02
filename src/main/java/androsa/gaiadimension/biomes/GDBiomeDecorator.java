package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGeneratorSettings;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class GDBiomeDecorator {
    //private WorldGenLakes extraLakeGen = new WorldGenLakes(GDFluids.mineralWaterBlock);
    //private GDGenLavaLake caveLavaGen = new GDGenLavaLake(GDBlocks.superhot_magma_block);
    /* Uncomment these if needed */

    private GDGenLavaLake extraLavaPoolGen = new GDGenLavaLake(GDBlocks.superhot_magma_block);
    private GDGenLavaLake extraMuckPoolGen = new GDGenLavaLake(GDBlocks.sweet_muck_block);
    private GDGenBismuthBog extraBismuthBogGen = new GDGenBismuthBog();
    /** Section for Fungi */
    public WorldGenerator goldorbShroomGen = new GDGenCrystalFungi(GDBlocks.gold_orb_tucher);
    public WorldGenerator elderShroomGen = new GDGenCrystalFungi(GDBlocks.elder_imklia);

    public World world;
    public Random rand;
    public BlockPos chunkPos;
    public ChunkGeneratorSettings chunkProviderSettings;
    public GDBiomeBase gaiaBiome;

    public int lakesPerChunk;
    public int fungiPerChunk;
    public int flowersPerChunk;
    public int treesPerChunk = 0;
    public int grassPerChunk = 0;
    public float lavaPoolChance = 0;
    public float muckPoolChance;
    public float bismuthBogChance;

    public void decorate(World world, Random rand, Biome biome, BlockPos pos) {
        this.chunkProviderSettings = ChunkGeneratorSettings.Factory.jsonToFactory(world.getWorldInfo().getGeneratorOptions()).build();
        this.chunkPos = pos;
        this.gaiaBiome = (GDBiomeBase)biome;

        genLakes(world, rand);

        for (int i = 0; i < treesPerChunk; ++i) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            WorldGenAbstractTree worldgenabstracttree = biome.getRandomTreeFeature(rand);
            worldgenabstracttree.setDecorationDefaults();
            BlockPos blockpos = world.getHeight(this.chunkPos.add(k6, 0, l));

            if (worldgenabstracttree.generate(world, rand, blockpos)) {
                worldgenabstracttree.generateSaplings(world, rand, blockpos);
            }
        }

        for (int i = 0; i < this.grassPerChunk; ++i) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int height = world.getHeight(this.chunkPos.add(x, 0, z)).getY() * 2;

            if (height > 0) {
                int y = rand.nextInt(height);
                biome.getRandomWorldGenForGrass(rand).generate(world, rand, this.chunkPos.add(x, y, z));
            }
        }

        for (int i = 0; i < fungiPerChunk; ++i) {
            if (rand.nextInt(8) == 0) {
                int x = rand.nextInt(16) + 8;
                int z = rand.nextInt(16) + 8;
                int height = world.getHeight(this.chunkPos.add(x, 0, z)).getY() + 32;

                if (height > 0) {
                    int y = rand.nextInt(height);
                    BlockPos blockpos1 = this.chunkPos.add(x, y, z);
                    gaiaBiome.getRandomFungus(rand).generate(world, rand, blockpos1);
                }
            }
        }

        for (int i = 0; i < flowersPerChunk; ++i) {
            if (rand.nextInt(4) == 0) {
                int x = rand.nextInt(16) + 8;
                int z = rand.nextInt(16) + 8;
                int height = world.getHeight(this.chunkPos.add(x, 0, z)).getY() + 32;

                if (height > 0) {
                    int y = rand.nextInt(height);
                    BlockPos blockpos1 = this.chunkPos.add(x, y, z);
                    gaiaBiome.getRandomBloom(rand).generate(world, rand, blockpos1);
                }
            }
        }

        decorateUnderground(world, rand, pos);
    }

    protected void genLakes(World world, Random randomGenerator) {
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

        //Handles the number of Bismuth Bogs per chunk
        if (randomGenerator.nextFloat() <= bismuthBogChance) {
            int rx = chunkPos.getX() + randomGenerator.nextInt(16) + 8;
            int rz = chunkPos.getZ() + randomGenerator.nextInt(16) + 8;
            extraBismuthBogGen.generate(world, randomGenerator, world.getHeight(new BlockPos(rx, 0, rz)));
        }
    }

    /**
     * Method used to decorate the underground
     */
    protected void decorateUnderground(World world, Random rand, BlockPos pos) {
        int Xcoord, Ycoord, Zcoord;

        for (int i = 0; i < 5; i++) {
            int x = rand.nextInt(16) + 8;
            int z = rand.nextInt(16) + 8;
            int height = world.getHeight(this.chunkPos.add(x, 0, z)).getY() * 2;

            if (rand.nextInt(4) == 0) {
                if (height > 0) {
                    int y = rand.nextInt(height);
                    BlockPos blockpos1 = this.chunkPos.add(x, y, z);
                    this.elderShroomGen.generate(world, rand, blockpos1);
                }
            } else {
                if (height > 0) {
                    int y = rand.nextInt(height);
                    BlockPos blockpos1 = this.chunkPos.add(x, y, z);
                    this.goldorbShroomGen.generate(world, rand, blockpos1);
                }
            }
        }

        //Pebbles gen
        for (int i = 0; i < 25; i++) {
            Xcoord = pos.getX() + rand.nextInt(16);
            Zcoord = pos.getZ() + rand.nextInt(16);
            Ycoord = rand.nextInt(120);
            new WorldGenMinable(GDBlocks.pebbles.getDefaultState(), 25,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }

        //Speckled Rock gen
        for (int i = 0; i < 10; i++) {
            Xcoord = pos.getX() + rand.nextInt(16);
            Zcoord = pos.getZ() + rand.nextInt(16);
            Ycoord = rand.nextInt(120);
            new WorldGenMinable(GDBlocks.speckled_rock.getDefaultState(), chunkProviderSettings.diamondSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Coarse Rock gen
        for (int i = 0; i < 10; i++) {
            Xcoord = pos.getX() + rand.nextInt(16);
            Zcoord = pos.getZ() + rand.nextInt(16);
            Ycoord = rand.nextInt(60);
            new WorldGenMinable(GDBlocks.coarse_rock.getDefaultState(), chunkProviderSettings.diamondSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Precious Rock gen
        for (int i = 0; i < 10; i++) {
            Xcoord = pos.getX() + rand.nextInt(16);
            Zcoord = pos.getZ() + rand.nextInt(16);
            Ycoord = rand.nextInt(30);
            new WorldGenMinable(GDBlocks.precious_rock.getDefaultState(), chunkProviderSettings.diamondSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Sugilite Ore gen
        for (int i = 0; i < 8; i++) {
            Xcoord = pos.getX() + rand.nextInt(16);
            Zcoord = pos.getZ() + rand.nextInt(16);
            Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.sugilite_ore.getDefaultState(), chunkProviderSettings.coalSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Hematite Ore gen
        for (int i = 0; i < 8; i++) {
            Xcoord = pos.getX() + rand.nextInt(16);
            Zcoord = pos.getZ() + rand.nextInt(16);
            Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.hematite_ore.getDefaultState(), chunkProviderSettings.coalSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Pyrite Ore gen
        for (int i = 0; i < 8; i++) {
            Xcoord = pos.getX() + rand.nextInt(16);
            Zcoord = pos.getZ() + rand.nextInt(16);
            Ycoord = rand.nextInt(80);
            new WorldGenMinable(GDBlocks.pyrite_ore.getDefaultState(), chunkProviderSettings.ironSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Cinnabar Ore gen
        for (int i = 0; i < 7; i++) {
            Xcoord = pos.getX() + rand.nextInt(16);
            Zcoord = pos.getZ() + rand.nextInt(16);
            Ycoord = rand.nextInt(60);
            new WorldGenMinable(GDBlocks.cinnabar_ore.getDefaultState(), chunkProviderSettings.ironSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Labradorite Ore gen
        for (int i = 0; i < 6; i++) {
            Xcoord = pos.getX() + rand.nextInt(16);
            Zcoord = pos.getZ() + rand.nextInt(16);
            Ycoord = rand.nextInt(40);
            new WorldGenMinable(GDBlocks.labradorite_ore.getDefaultState(), chunkProviderSettings.goldSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //Moonstone Ore gen
        for (int i = 0; i < 6; i++) {
            Xcoord = pos.getX() + rand.nextInt(16);
            Zcoord = pos.getZ() + rand.nextInt(16);
            Ycoord = rand.nextInt(40);
            new WorldGenMinable(GDBlocks.moonstone_ore.getDefaultState(), chunkProviderSettings.goldSize,
                    input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
        //White Opal Ore gen
        if (world.getBiome(pos) instanceof GDMutantAgateWildwood) {
            for (int i = 0; i < 4; i++) {
                Xcoord = pos.getX() + rand.nextInt(16);
                Zcoord = pos.getZ() + rand.nextInt(16);
                Ycoord = rand.nextInt(20);
                new WorldGenMinable(GDBlocks.opal_ore_white.getDefaultState(), chunkProviderSettings.diamondSize,
                        input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
            }
        } else {
            for (int i = 0; i < 3; i++) {
                Xcoord = pos.getX() + rand.nextInt(16);
                Zcoord = pos.getZ() + rand.nextInt(16);
                Ycoord = rand.nextInt(25);
                new WorldGenMinable(GDBlocks.opal_ore_white.getDefaultState(), chunkProviderSettings.diamondSize,
                        input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
            }
        }

        /* Until I can find a good use of this, I'm commenting it out...
        if (this.generateFalls) {
            for (int i = 0; i < 40; ++i) {
                int rx = pos.getX() + rand.nextInt(16) + 8;
                int ry = rand.nextInt(14) + 4;
                int rz = pos.getZ() + rand.nextInt(16) + 8;
                new WorldGenLiquids(GDBlocks.superhot_magma_block).generate(world, rand, new BlockPos(rx, ry, rz));
            }
        } */
    }
}
