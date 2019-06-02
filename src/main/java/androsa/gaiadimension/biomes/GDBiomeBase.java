package androsa.gaiadimension.biomes;

import androsa.gaiadimension.entity.*;
import androsa.gaiadimension.registry.EnumSkyColors;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.GaiaWorld;
import androsa.gaiadimension.world.gen.GDGenCrystalPlants;
import androsa.gaiadimension.world.gen.GDGenNoTrees;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class GDBiomeBase extends Biome {

    public GDBiomeDecorator biomeDecorator;
    public EnumSkyColors skyColor = EnumSkyColors.GENERAL;

    public GDBiomeBase(BiomeProperties props) {
        super(props);
        biomeDecorator = getBiomeDecorator();

        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();

        spawnableMonsterList.add(new SpawnListEntry(GDCavernTick.class, 100, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(GDShalurker.class, 100, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(GDArchaicWarrior.class, 100, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(GDMuckling.class, 100, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 5, 1, 2));
        spawnableMonsterList.add(new SpawnListEntry(GDPrimalBeast.class, 25, 1, 2));

        spawnableCreatureList.add(new SpawnListEntry(GDNomadicLagrahk.class, 15, 1, 2));

        spawnableWaterCreatureList.add(new SpawnListEntry(GDShallowArenthis.class, 10, 4, 4));

        biomeDecorator.treesPerChunk = 5;
        biomeDecorator.grassPerChunk = 3;
        biomeDecorator.flowersPerChunk = 2;
        biomeDecorator.fungiPerChunk = 1;

        this.topBlock = GDBlocks.glitter_grass.getDefaultState();
        this.fillerBlock = GDBlocks.heavy_soil.getDefaultState();
    }

    public GDBiomeDecorator getBiomeDecorator() {
        return new GDBiomeDecorator();
    }

    @SideOnly(Side.CLIENT)
    public final short[] getSkyRGB() {
        return skyColor.getSkyColor();
    }

    @SideOnly(Side.CLIENT)
    public final short[] getFogRGB() {
        return skyColor.getFogColor();
    }

    @Override
    public float getSpawningChance() {
        return 0.12F;
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        this.biomeDecorator.decorate(worldIn, rand, this, pos);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return new GDGenNoTrees();
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new GDGenCrystalPlants(GDBlocks.crystal_growth);
    }

    public WorldGenerator getRandomFungus(Random rand) {
        return new GDGenCrystalPlants(GDBlocks.spotted_kersei);
    }

    public WorldGenerator getRandomBloom(Random rand) {
        if (rand.nextInt(4) == 0) {
            return new GDGenCrystalPlants(GDBlocks.ouzium);
        } else {
            return new GDGenCrystalPlants(GDBlocks.thiscus);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0xF2A3B4;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0xF2A3B4;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noiseVal) {
        this.genGaiaBiomeTerrain(world, rand, primer, x, z, noiseVal);
    }

    /**
     * Here's the rundown:
     *
     * We are checking layers from the very top of the world down
     * Anything at or below 5 gets Bedrock
     * Air blocks stay as air. Gaia Stone blocks will be prepared for Top and Filler Blocks
     * If the stone blocks are below sea level, then we prepare the Mineral Water, or Ice, but that never happens
     * Also it gets set to Salt
     * If there is Air below Salt, prepare it with Saltstone
     */
    public final void genGaiaBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int seaLevel = GaiaWorld.SEALEVEL;
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        IBlockState stone = getStoneReplacement();
        int j = -1;
        int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int posZ = x & 15;
        int posX = z & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int posY = 255; posY >= 0; --posY) {
            if (posY <= rand.nextInt(5)) {
                chunkPrimerIn.setBlockState(posX, posY, posZ, BEDROCK);
            } else {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(posX, posY, posZ);

                if (iblockstate2.getMaterial() == Material.AIR) {
                    j = -1;
                } else if (iblockstate2.getBlock() == GDBlocks.gaia_stone) {
                    if (stone != null && posY > seaLevel + 10) {
                        chunkPrimerIn.setBlockState(posX, posY, posZ, stone);
                    }


                    if (j == -1) {
                        if (k <= 0) {
                            iblockstate = AIR;
                            iblockstate1 = GDBlocks.gaia_stone.getDefaultState();
                        } else if (posY >= seaLevel - 4 && posY <= seaLevel + 1) {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (posY < seaLevel && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
                            if (this.getTemperature(blockpos$mutableblockpos.setPos(x, posY, z)) < 0.15F) {
                                iblockstate = ICE;
                            } else {
                                iblockstate = GDBlocks.mineral_water_block.getDefaultState();
                            }
                        }

                        j = k;

                        if (posY >= seaLevel - 1) {
                            chunkPrimerIn.setBlockState(posX, posY, posZ, iblockstate);
                        } else if (posY < seaLevel - 7 - k) {
                            iblockstate = AIR;
                            iblockstate1 = GDBlocks.gaia_stone.getDefaultState();
                            chunkPrimerIn.setBlockState(posX, posY, posZ, GDBlocks.salt.getDefaultState());
                        } else {
                            chunkPrimerIn.setBlockState(posX, posY, posZ, iblockstate1);
                        }
                    } else if (j > 0) {
                        --j;
                        chunkPrimerIn.setBlockState(posX, posY, posZ, iblockstate1);

                        if (j == 0 && iblockstate1.getBlock() == GDBlocks.salt && k > 1) {
                            j = rand.nextInt(4) + Math.max(0, posY - 63);
                            iblockstate1 = GDBlocks.saltstone.getDefaultState();
                        }
                    }
                }
            }
        }
    }

    @Nullable
    public IBlockState getStoneReplacement() {
        return null;
    }
}