package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.GaiaWorld;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GDBiomeBase extends Biome {

    private int grassColor = -1;
    private int foliageColor = -1;
    //private short[] skyColorRGB = new short[] { 198, 157, 88 };
    protected static final IBlockState STONE_GAIA = GDBlocks.gaiaStone.getDefaultState();
    protected List<SpawnListEntry> undergroundMonsterList;

    public GDBiomeBase(BiomeProperties props) {
        super(props);

        spawnableCreatureList.clear();

        undergroundMonsterList = new ArrayList<SpawnListEntry>();

        //TODO: Replace with Gaia-Specific Monsters. Perhaps underground-exclusive?
        undergroundMonsterList.add(new SpawnListEntry(EntitySpider.class, 10, 4, 4));
        undergroundMonsterList.add(new SpawnListEntry(EntityZombie.class, 10, 4, 4));
        undergroundMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 10, 4, 4));
        undergroundMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 4, 4));
        undergroundMonsterList.add(new SpawnListEntry(EntityEnderman.class, 1, 1, 4)); //Keep this guy, though

        getGDBiomeDecorator().setTreesPerChunk(5);
        getGDBiomeDecorator().setGrassPerChunk(2);
        decorator.flowersPerChunk = -1;
        decorator.reedsPerChunk = -1;
    }

    @Override
    public float getSpawningChance() {
        return 0.12F;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new GDBiomeDecorator();
    }

    protected GDBiomeDecorator getGDBiomeDecorator() {
        return (GDBiomeDecorator) this.decorator;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        if (this.foliageColor < 0) {
            return super.getGrassColorAtPos(pos);
        }
        return this.grassColor;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        if (this.foliageColor < 0) {
            return super.getGrassColorAtPos(pos);
        }
        return this.foliageColor;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noiseVal) {
        this.genGaiaBiomeTerrain(world, rand, primer, x, z, noiseVal);
    }

    public final void genGaiaBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int i = GaiaWorld.SEALEVEL;
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        int j = -1;
        int k = (int)(noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
        int l = x & 15;
        int i1 = z & 15;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (int j1 = 255; j1 >= 0; --j1) {
            if (j1 <= rand.nextInt(5)) {
                chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
            } else {
                IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

                if (iblockstate2.getMaterial() == Material.AIR) {
                    j = -1;
                }
                else if (iblockstate2.getBlock() == GDBlocks.gaiaStone) {
                    if (j == -1) {
                        if (k <= 0) {
                            iblockstate = AIR;
                            iblockstate1 = STONE_GAIA;
                        } else if (j1 >= i - 4 && j1 <= i + 1) {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
                            if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
                                iblockstate = ICE;
                            } else {
                                iblockstate = WATER;
                            }
                        }

                        j = k;

                        if (j1 >= i - 1) {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                        }
                        else if (j1 < i - 7 - k) {
                            iblockstate = AIR;
                            iblockstate1 = STONE_GAIA;
                            chunkPrimerIn.setBlockState(i1, j1, l, GDBlocks.saltBlock.getDefaultState());
                        }
                        else {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
                        }
                    }
                }
            }
        }
    }

    public List<SpawnListEntry> getUndergroundMonsterList() {
        return this.undergroundMonsterList;
    }
}