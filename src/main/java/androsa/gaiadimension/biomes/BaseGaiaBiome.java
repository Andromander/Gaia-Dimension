package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class BaseGaiaBiome extends Biome {

    public GaiaSkyColors skyColor = GaiaSkyColors.GENERAL;

    public BaseGaiaBiome(Builder props) {
        super(props);

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.NOMADIC_LAGRAHK, 15, 1, 2));
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(ModEntities.SHALLOW_ARENTHIS, 10, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.CAVERN_TICK, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.SHALURKER, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.ARCHAIC_WARRIOR, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.MUCKLING, 100, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 5, 1, 2));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.PRIMAL_BEAST, 25, 1, 2));

        //biomeDecorator.treesPerChunk = 5;
        //biomeDecorator.grassPerChunk = 3;
        //biomeDecorator.flowersPerChunk = 2;
        //biomeDecorator.fungiPerChunk = 1;

        //this.flowers.clear();
        //this.flowers.add(new FlowerEntry(GDBlocks.thiscus.getDefaultState(), 20));
        //this.flowers.add(new FlowerEntry(GDBlocks.ouzium.getDefaultState(), 10));

        //this.topBlock = GDBlocks.glitter_grass.getDefaultState();
        //this.fillerBlock = GDBlocks.heavy_soil.getDefaultState();
    }

    @OnlyIn(Dist.CLIENT)
    public final short[] getSkyRGB() {
        return skyColor.getSkyColor();
    }

    @OnlyIn(Dist.CLIENT)
    public final short[] getFogRGB() {
        return skyColor.getFogColor();
    }

    @Override
    public float getSpawningChance() {
        return 0.12F;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos) {
        return 0xF2A3B4;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0xF2A3B4;
    }

    /*@Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noiseVal) {
        this.genGaiaBiomeTerrain(world, rand, primer, x, z, noiseVal);
    }*/

    /**
     * Here's the rundown:
     *
     * We are checking layers from the very top of the world down
     * Anything at or below 5 gets Bedrock
     * Air blocks stay as air. Gaia Stone blocks will be prepared for Top and Filler Blocks
     * If the stone blocks are below sea level, then we prepare the Mineral Water, or Ice, but that never happens
     * Also it gets set to Salt
     * If there is Air below Salt, prepare it with Saltstone
     *
     * FIXME: This is now no longer the case. Look up how vanilla does this. Appears to use SurfaceBuilder
     */
    /*public final void genGaiaBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
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
    }*/
}