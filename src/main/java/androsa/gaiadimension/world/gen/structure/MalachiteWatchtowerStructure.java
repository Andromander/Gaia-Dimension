package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.structure.pieces.MalachiteWatchtowerPieces;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class MalachiteWatchtowerStructure extends StructureFeature<NoneFeatureConfiguration> {

    public MalachiteWatchtowerStructure(Codec<NoneFeatureConfiguration> config) {
        super(config, MalachiteWatchtowerStructure::pieceGenerator, MalachiteWatchtowerStructure::afterPlace);
    }

//    @Override
//    public String getStructureName() {
//        return GaiaDimensionMod.MODID + ":MalachiteWatchtower";
//    }

    @NonNull
    private static Optional<PieceGenerator<NoneFeatureConfiguration>> pieceGenerator(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context) {
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(0L));
        random.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        Rotation rotation = Rotation.getRandom(random);
        int oX = 5;
        int oZ = 5;
        if (rotation == Rotation.CLOCKWISE_90) {
            oX = -5;
        } else if (rotation == Rotation.CLOCKWISE_180) {
            oX = -5;
            oZ = -5;
        } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
            oZ = -5;
        }

        int cX = context.chunkPos().getBlockX(7);
        int cZ = context.chunkPos().getBlockZ(7);
        int[] heights = context.getCornerHeights(cX, oX, cZ, oZ);
        int level = Math.min(Math.min(heights[0], heights[1]), Math.min(heights[2], heights[3]));

        if (level < 60) {
            return Optional.empty();
        } else if (!context.validBiome().test(context.chunkGenerator().getNoiseBiome(QuartPos.fromBlock(cX), QuartPos.fromBlock(heights[0]), QuartPos.fromBlock(cZ)))) {
            return Optional.empty();
        } else {
            BlockPos blockpos = new BlockPos(context.chunkPos().getMiddleBlockX(), level + 1, context.chunkPos().getMiddleBlockZ());
            return Optional.of((builder, config) -> {
                List<MalachiteWatchtowerPieces.Piece> list = Lists.newLinkedList();
                MalachiteWatchtowerPieces.buildStructure(config.structureManager(), blockpos, rotation, list, random);
                list.forEach(builder::addPiece);
            });
        }
    }

    private static void afterPlace(WorldGenLevel world, StructureFeatureManager manager, ChunkGenerator generator, Random random, BoundingBox mbb, ChunkPos pos, PiecesContainer container) {
        BoundingBox boundingbox = container.calculateBoundingBox();
        int minY = boundingbox.minY();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        //Let me ask: do towers overhang cliffs? I didn't think so
        for(int x = mbb.minX(); x <= mbb.maxX(); ++x) {
            for(int z = mbb.minZ(); z <= mbb.maxZ(); ++z) {
                mutable.set(x, minY, z);
                if (!world.isEmptyBlock(mutable) && boundingbox.isInside(mutable) && container.isInsidePiece(mutable)) {

                    for(int lowY = minY - 1; lowY > 1; --lowY) {
                        mutable.setY(lowY);
                        if (!world.isEmptyBlock(mutable) && !world.getBlockState(mutable).getMaterial().isLiquid()) {
                            break;
                        }

                        world.setBlock(mutable, ModBlocks.malachite_bricks.get().defaultBlockState(), 2); //TODO
                    }
                }
            }
        }
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

//    @Override
//    protected boolean linearSeparation() {
//        return false;
//    }
}
