package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.world.gen.structure.pieces.MalachiteWatchtowerPieces;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class MalachiteWatchtowerStructure<T extends NoFeatureConfig> extends Structure<T> {

    public MalachiteWatchtowerStructure(Codec<T> config) {
        super(config);
    }

//    @Override
//    public String getStructureName() {
//        return GaiaDimensionMod.MODID + ":MalachiteWatchtower";
//    }

    @Override
    public List<MobSpawnInfo.Spawners> getCreatureSpawnList() {
        return Lists.newArrayList(
                new MobSpawnInfo.Spawners(ModEntities.MALACHITE_DRONE, 10, 1, 1),
                new MobSpawnInfo.Spawners(ModEntities.SHALURKER, 5, 1, 2),
                new MobSpawnInfo.Spawners(ModEntities.ARCHAIC_WARRIOR, 8, 1, 2),
                new MobSpawnInfo.Spawners(ModEntities.CAVERN_TICK, 3, 2, 3));
    }

    @Override
    public IStartFactory<T> getStartFactory() {
        return MalachiteWatchtowerStructure.Start::new;
    }

    @Override
    public GenerationStage.Decoration func_236396_f_() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    //TODO
//    @Override
//    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> generator, Random random, int minX, int minZ, int maxX, int maxZ) {
//        int dist = this.getBiomeFeatureDistance(generator);
//        int sep = this.getBiomeFeatureSeparation(generator);
//        int k = minX + dist * maxX;
//        int l = minZ + dist * maxZ;
//        int i1 = k < 0 ? k - dist + 1 : k;
//        int j1 = l < 0 ? l - dist + 1 : l;
//        int k1 = i1 / dist;
//        int l1 = j1 / dist;
//        ((SharedSeedRandom)random).setLargeFeatureSeedWithSalt(generator.getSeed(), k1, l1, 1294754);
//        k1 = k1 * dist;
//        l1 = l1 * dist;
//        k1 = k1 + random.nextInt(dist - sep);
//        l1 = l1 + random.nextInt(dist - sep);
//        return new ChunkPos(k1, l1);
//    }

    @Override
    protected boolean func_230363_a_(ChunkGenerator generator, BiomeProvider provider, long seed, SharedSeedRandom random, int chunkX, int chunkZ, Biome biomeIn, ChunkPos chunkpos, T config) {
        for(Biome biome : provider.getBiomes(chunkX * 16 + 9, generator.func_230356_f_(), chunkZ * 16 + 9, 16)) {
            if (!biome.getGenerationSettings().hasStructure(this)) {
                return false;
            }
        }

        return true;
    }

//    protected int getBiomeFeatureDistance(ChunkGenerator<?> generator) {
//        GenerationSettings settings = generator.getSettings();
//
//        if (settings instanceof GaiaGenerationSettings)
//            return ((GaiaGenerationSettings) settings).getWatchtowerFeatureDistance();
//        else
//            return settings.getBiomeFeatureDistance();
//    }

//    protected int getBiomeFeatureSeparation(ChunkGenerator<?> generator) {
//        GenerationSettings settings = generator.getSettings();
//
//        if (settings instanceof GaiaGenerationSettings)
//            return ((GaiaGenerationSettings) settings).getWatchtowerFeatureSeparation();
//        else
//            return settings.getBiomeFeatureSeparation();
//    }

    public static class Start<T extends NoFeatureConfig> extends StructureStart<T> {

        public Start(Structure<T> structure, int chunkX, int chunkZ, MutableBoundingBox mbb, int ref, long seed) {
            super(structure, chunkX, chunkZ, mbb, ref, seed);
        }

        @Override
        public void func_230364_a_(DynamicRegistries registries, ChunkGenerator generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome, T config) {
            int x = chunkX * 16;
            int z = chunkZ * 16;
            BlockPos blockpos = new BlockPos(x, 90, z);
            Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
            MalachiteWatchtowerPieces.buildStructure(manager, blockpos, rotation, this.components, this.rand);
            this.recalculateStructureSize();
        }

        @Override
        public void func_230366_a_(ISeedReader world, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox mbb, ChunkPos chunkpos) {
            super.func_230366_a_(world, manager, generator, random, mbb, chunkpos);
            int minY = this.bounds.minY;

            //Let me ask: do towers overhang cliffs? I didn't think so
            for(int x = mbb.minX; x <= mbb.maxX; ++x) {
                for(int z = mbb.minZ; z <= mbb.maxZ; ++z) {
                    BlockPos blockpos = new BlockPos(x, minY, z);
                    if (!world.isAirBlock(blockpos) && this.bounds.isVecInside(blockpos)) {
                        boolean isAirBelow = false;

                        for(StructurePiece structurepiece : this.components) {
                            if (structurepiece.getBoundingBox().isVecInside(blockpos)) {
                                isAirBelow = true;
                                break;
                            }
                        }

                        if (isAirBelow) {
                            for(int lowY = minY - 1; lowY > 1; --lowY) {
                                BlockPos blockpos1 = new BlockPos(x, lowY, z);
                                if (!world.isAirBlock(blockpos1) && !world.getBlockState(blockpos1).getMaterial().isLiquid()) {
                                    break;
                                }

                                world.setBlockState(blockpos1, world.getBiome(blockpos1).getGenerationSettings().getSurfaceBuilderConfig().getUnder(), 2);
                            }
                        }
                    }
                }
            }
        }
    }
}
