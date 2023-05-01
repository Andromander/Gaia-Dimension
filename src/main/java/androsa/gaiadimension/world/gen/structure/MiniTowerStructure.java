package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.registry.ModStructures;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.world.gen.structure.pieces.MiniTowerPieces;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Optional;

public class MiniTowerStructure extends Structure {
    public static final Codec<MiniTowerStructure> CODEC = simpleCodec(MiniTowerStructure::new);

    public MiniTowerStructure(StructureSettings config) {
        super(config);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        return onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG, (builder) -> this.generatePieces(builder, context));
    }

    private void generatePieces(StructurePiecesBuilder builder, GenerationContext context) {
        Rotation rotation = Rotation.getRandom(context.random());
        BlockPos blockpos = new BlockPos(context.chunkPos().getMinBlockX(), 90, context.chunkPos().getMinBlockZ());
        MiniTowerPieces.buildStructure(context.structureTemplateManager(), blockpos, rotation, builder, context.random());
    }

    @Override
    public void afterPlace(WorldGenLevel world, StructureManager manager, ChunkGenerator generator, RandomSource random, BoundingBox mbb, ChunkPos pos, PiecesContainer container) {
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
    public StructureType<?> type() {
        return ModStructures.MINI_TOWER_TYPE.get();
    }
}
