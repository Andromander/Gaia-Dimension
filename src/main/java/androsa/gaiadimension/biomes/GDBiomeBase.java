package androsa.gaiadimension.biomes;

import androsa.gaiadimension.entity.GDMuckling;
import androsa.gaiadimension.entity.GDShalurker;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.GaiaWorld;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GDBiomeBase extends Biome {

    public GDBiomeBase(BiomeProperties props) {
        super(props);

        spawnableCreatureList.clear();
        spawnableMonsterList.clear();

        //TODO: Replace with Gaia-Specific Monsters. Perhaps underground-exclusive?
        //Well, they have to be underground exclusive, it's always day time
        spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 20, 1, 2));
        spawnableMonsterList.add(new SpawnListEntry(GDShalurker.class, 20, 1, 2));
        spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 20, 1, 2));
        spawnableMonsterList.add(new SpawnListEntry(GDMuckling.class, 20, 1, 2));
        spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 5, 1, 2)); //Keep this guy, though

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
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        this.decorator.decorate(worldIn, rand, this, pos);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0xED7A9E;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0xED7A9E;
    }

    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noiseVal) {
        this.genGaiaBiomeTerrain(world, rand, primer, x, z, noiseVal);
    }

    public final void genGaiaBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
        int i = GaiaWorld.SEALEVEL;
        IBlockState iblockstate = this.topBlock;
        IBlockState iblockstate1 = this.fillerBlock;
        IBlockState stone = getStoneReplacement();
        int j = -1;
        int k = (int) (noiseVal / 3.0D + 3.0D + rand.nextDouble() * 0.25D);
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
                } else if (iblockstate2.getBlock() == GDBlocks.gaia_stone) {
                    if (stone != null)
                        chunkPrimerIn.setBlockState(i1, j1, l, stone);

                    if (j == -1) {
                        if (k <= 0) {
                            iblockstate = AIR;
                            iblockstate1 = GDBlocks.gaia_stone.getDefaultState();
                        } else if (j1 >= i - 4 && j1 <= i + 1) {
                            iblockstate = this.topBlock;
                            iblockstate1 = this.fillerBlock;
                        }

                        if (j1 < i && (iblockstate == null || iblockstate.getMaterial() == Material.AIR)) {
                            if (this.getTemperature(blockpos$mutableblockpos.setPos(x, j1, z)) < 0.15F) {
                                iblockstate = ICE;
                            } else {
                                iblockstate = GDBlocks.mineral_water_block.getDefaultState();
                            }
                        }

                        j = k;

                        if (j1 >= i - 1) {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
                        } else if (j1 < i - 7 - k) {
                            iblockstate = AIR;
                            iblockstate1 = GDBlocks.gaia_stone.getDefaultState();
                            chunkPrimerIn.setBlockState(i1, j1, l, GDBlocks.salt.getDefaultState());
                        } else {
                            chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
                        }
                    } else if (j > 0) {
                        --j;
                        chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

                        if (j == 0 && iblockstate1.getBlock() == GDBlocks.salt && k > 1) {
                            j = rand.nextInt(4) + Math.max(0, j1 - 63);
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