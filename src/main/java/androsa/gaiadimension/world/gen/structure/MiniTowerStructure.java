package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.structure.pieces.MiniTowerPieces;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Random;

public class MiniTowerStructure extends StructureFeature<NoneFeatureConfiguration> {

    public MiniTowerStructure(Codec<NoneFeatureConfiguration> config) {
        super(config, PieceGeneratorSupplier.simple(PieceGeneratorSupplier.checkForBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG), MiniTowerStructure::pieceGenerator), MiniTowerStructure::afterPlace);
    }

//    @Override
//    public String getStructureName() {
//        return GaiaDimensionMod.MODID + ":GaiaMiniTower";
//    }

    private static void pieceGenerator(StructurePiecesBuilder builder, PieceGenerator.Context<NoneFeatureConfiguration> context) {
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), 90, context.chunkPos().getMinBlockZ());
        Rotation rotation = Rotation.getRandom(context.random());
        MiniTowerPieces.buildStructure(context.structureManager(), blockpos, rotation, builder, context.random());
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

                        world.setBlock(mutable, ModBlocks.heavy_soil.get().defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }
}
