package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.world.GaiaWorld;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.MapGenBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GDFeature {

    public static final GDFeature[] featureList = new GDFeature[256];

    public static final GDFeature nothing = new GDFeature(0, 0, "no_feature").enableDecorations().disableStructure();
    public static final GDFeature malachiteWatchtower = new GDFeature(1, 2, "malachite_watchtower").enableDecorations().enableTerrainAlteration();
    //Gaia Apex Predator Feature
    //Princess of Fire Feature
    //Prince of Thunder Feature
    //Goldstone Twins Feature
    //Gaia Kingdom Town Feature
    //Gaia Castle

    public int featureID;
    public int size;
    public String name;
    public boolean areChunkDecorationsEnabled;
    public boolean isStructureEnabled;
    public boolean isTerrainAltered;
    private List<List<Biome.SpawnListEntry>> spawnableMonsterLists;
    private List<Biome.SpawnListEntry> ambientCreatureList;
    private List<Biome.SpawnListEntry> waterCreatureList;

    private long lastSpawnedHintMonsterTime;

    private static int maxSize;

    public GDFeature(int parID, int parSize, String parName) {
        this.featureID = parID;
        GDFeature.featureList[parID] = this;
        this.size = parSize;
        this.name = parName;
        this.areChunkDecorationsEnabled = false;
        this.isStructureEnabled = true;
        this.isTerrainAltered = false;
        this.spawnableMonsterLists = new ArrayList<>();
        this.ambientCreatureList = new ArrayList<>();
        this.waterCreatureList = new ArrayList<>();

        ambientCreatureList.add(new Biome.SpawnListEntry(EntityBat.class, 10, 8, 8));

        maxSize = Math.max(maxSize, parSize);
    }

    public static int getMaxSize() {
        return maxSize;
    }

    //No MODID
    public static GDFeature getFeatureByName(String name) {
        for (GDFeature feature : featureList) {
            if (feature != null && feature.name.equalsIgnoreCase(name))
                return feature;
        }
        return nothing;
    }

    //MODID
    public static GDFeature getFeatureByName(ResourceLocation name) {
        if (name.getResourceDomain().equalsIgnoreCase(GaiaDimension.MODID))
            return getFeatureByName(name.getResourcePath());
        return nothing;
    }

    public static GDFeature getFeatureByID(int id) {
        for (GDFeature feature : featureList) {
            if (feature != null && feature.featureID == id)
                return feature;
        }
        return nothing;
    }

    public static int getFeatureID(int mapX, int mapZ, World world) {
        return getFeatureAt(mapX, mapZ, world).featureID;
    }

    public static GDFeature getFeatureAt(int mapX, int mapZ, World world) {
        return generateFeature(mapX >> 4, mapZ >> 4, world);
    }

    public static boolean isInFeatureChunk(World world, int mapX, int mapZ) {
        int chunkX = mapX >> 4;
        int chunkZ = mapZ >> 4;
        BlockPos cc = getNearestCenterXYZ(chunkX, chunkZ, world);

        return chunkX == (cc.getX() >> 4) && chunkZ == (cc.getZ() >> 4);
    }

    public GDFeature enableDecorations() {
        this.areChunkDecorationsEnabled = true;
        return this;
    }

    public GDFeature disableStructure() {
        this.isStructureEnabled = false;
        return this;
    }

    //This will tell the Chunk Generator to alter terrain
    public GDFeature enableTerrainAlteration() {
        this.isTerrainAltered = true;
        return this;
    }

    public GDFeature addMonster(Class<? extends EntityLiving> monsterClass, int weight, int minGroup, int maxGroup) {
        this.addMonster(0, monsterClass, weight, minGroup, maxGroup);
        return this;
    }

    public GDFeature addMonster(int listIndex, Class<? extends EntityLiving> monsterClass, int weight, int minGroup, int maxGroup) {
        List<Biome.SpawnListEntry> monsterList;
        if (this.spawnableMonsterLists.size() > listIndex) {
            monsterList = this.spawnableMonsterLists.get(listIndex);
        } else {
            monsterList = new ArrayList<Biome.SpawnListEntry>();
            this.spawnableMonsterLists.add(listIndex, monsterList);
        }

        monsterList.add(new Biome.SpawnListEntry(monsterClass, weight, minGroup, maxGroup));
        return this;
    }

    public GDFeature addWaterCreature(Class<? extends EntityLiving> monsterClass, int weight, int minGroup, int maxGroup) {
        this.waterCreatureList.add(new Biome.SpawnListEntry(monsterClass, weight, minGroup, maxGroup));
        return this;
    }

    public static GDFeature getFeatureDirectlyAt(int chunkX, int chunkZ, World world) {
        return nothing;
    }

    public static GDFeature generateFeature(int chunkX, int chunkZ, World world) {
        chunkX = Math.round(chunkX / 16F) * 16;
        chunkZ = Math.round(chunkZ / 16F) * 16;

        Biome biomeAt = world.getBiome(new BlockPos((chunkX << 4) + 8, 0, (chunkZ << 4) + 8));

        int regionOffsetX = Math.abs((chunkX + 64 >> 4) % 8);
        int regionOffsetZ = Math.abs((chunkZ + 64 >> 4) % 8);

        return nothing;
    }

    public static GDFeature getNearestFeature(int cx, int cz, World world) {
        for (int rad = 1; rad <= 4; rad++) {
            for (int x = -rad; x <= rad; x++) {
                for (int z = -rad; z <= rad; z++) {
                    GDFeature directlyAt = getFeatureDirectlyAt(x + cx, z + cz, world);
                    if (directlyAt.size == rad) {
                        return directlyAt;
                    }
                }
            }
        }
        return nothing;
    }

    public static BlockPos findNearestFeaturePosBySpacing(World worldIn, GDFeature feature, BlockPos blockPos, int p_191069_3_, int p_191069_4_, int p_191069_5_, boolean p_191069_6_, int p_191069_7_, boolean findUnexplored) {
        int i = blockPos.getX() >> 4;
        int j = blockPos.getZ() >> 4;
        int k = 0;

        for (Random random = new Random(); k <= p_191069_7_; ++k) {
            for (int l = -k; l <= k; ++l) {
                boolean flag = l == -k || l == k;

                for (int i1 = -k; i1 <= k; ++i1) {
                    boolean flag1 = i1 == -k || i1 == k;

                    if (flag || flag1) {
                        int j1 = i + p_191069_3_ * l;
                        int k1 = j + p_191069_3_ * i1;

                        if (j1 < 0) {
                            j1 -= p_191069_3_ - 1;
                        }

                        if (k1 < 0) {
                            k1 -= p_191069_3_ - 1;
                        }

                        int l1 = j1 / p_191069_3_;
                        int i2 = k1 / p_191069_3_;
                        Random random1 = worldIn.setRandomSeed(l1, i2, p_191069_5_);
                        l1 = l1 * p_191069_3_;
                        i2 = i2 * p_191069_3_;

                        if (p_191069_6_) {
                            l1 = l1 + (random1.nextInt(p_191069_3_ - p_191069_4_) + random1.nextInt(p_191069_3_ - p_191069_4_)) / 2;
                            i2 = i2 + (random1.nextInt(p_191069_3_ - p_191069_4_) + random1.nextInt(p_191069_3_ - p_191069_4_)) / 2;
                        } else {
                            l1 = l1 + random1.nextInt(p_191069_3_ - p_191069_4_);
                            i2 = i2 + random1.nextInt(p_191069_3_ - p_191069_4_);
                        }

                        MapGenBase.setupChunkSeed(worldIn.getSeed(), random, l1, i2);
                        random.nextInt();

                        if (GDFeature.getFeatureAt(l1 << 4, i2 << 4, worldIn) == feature) {
                            if (!findUnexplored || !worldIn.isChunkGeneratedAt(l1, i2)) {
                                return new BlockPos((l1 << 4) + 8, 64, (i2 << 4) + 8);
                            }
                        } else if (k == 0) {
                            break;
                        }
                    }
                }

                if (k == 0) {
                    break;
                }
            }
        }

        return null;
    }

    public static GDFeature getFeatureForRegion(int chunkX, int chunkZ, World world) {
        int featureX = Math.round(chunkX / 16F) * 16;
        int featureZ = Math.round(chunkZ / 16F) * 16;

        return GDFeature.generateFeature(featureX, featureZ, world);
    }

    public static int[] getNearestCenter(int cx, int cz, World world) {
        for (int rad = 1; rad <= 4; rad++) {
            for (int x = -rad; x <= rad; x++) {
                for (int z = -rad; z <= rad; z++) {
                    if (getFeatureDirectlyAt(x + cx, z + cz, world).size == rad) {
                        int[] center = {x * 16 + 8, z * 16 + 8};
                        return center;
                    }
                }
            }
        }
        int[] no = {0, 0};
        return no;
    }

    public static BlockPos getNearestCenterXYZ(int cx, int cz, World world) {
        int chunkX = cx;
        int chunkZ = cz;

        int regionX = (chunkX + 8) >> 4;
        int regionZ = (chunkZ + 8) >> 4;

        long seed = (long) (regionX * 3129871) ^ (long) regionZ * 116129781L;
        seed = seed * seed * 42317861L + seed * 7L;

        int num0 = (int) (seed >> 12 & 3L);
        int num1 = (int) (seed >> 15 & 3L);
        int num2 = (int) (seed >> 18 & 3L);
        int num3 = (int) (seed >> 21 & 3L);

        int centerX = 8 + num0 - num1;
        int centerZ = 8 + num2 - num3;

        int ccz;
        if (regionZ >= 0) {
            ccz = (regionZ * 16 + centerZ - 8) * 16 + 8;
        } else {
            ccz = (regionZ * 16 + (16 - centerZ) - 8) * 16 + 9;
        }

        int ccx;
        if (regionX >= 0) {
            ccx = (regionX * 16 + centerX - 8) * 16 + 8;
        } else {
            ccx = (regionX * 16 + (16 - centerX) - 8) * 16 + 9;
        }

        return new BlockPos(ccx, GaiaWorld.SEALEVEL, ccz);
    }
}

