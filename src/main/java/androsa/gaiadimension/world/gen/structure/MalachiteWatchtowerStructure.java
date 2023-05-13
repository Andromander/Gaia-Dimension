package androsa.gaiadimension.world.gen.structure;

import androsa.gaiadimension.registry.registration.ModStructures;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModEntities;
import androsa.gaiadimension.world.gen.structure.pieces.MalachiteWatchtowerPieces;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MalachiteWatchtowerStructure extends Structure {
    public static final Codec<MalachiteWatchtowerStructure> CODEC = simpleCodec(MalachiteWatchtowerStructure::new);

    public static final Map<MobCategory, StructureSpawnOverride> SPAWNS = Map.of(
            MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(
                    new MobSpawnSettings.SpawnerData(ModEntities.MALACHITE_DRONE.get(), 10, 1, 1),
                    new MobSpawnSettings.SpawnerData(ModEntities.SHALURKER.get(), 5, 1, 2),
                    new MobSpawnSettings.SpawnerData(ModEntities.ARCHAIC_WARRIOR.get(), 8, 1, 2),
                    new MobSpawnSettings.SpawnerData(ModEntities.CAVERN_TICK.get(), 3, 2, 3))));

    public MalachiteWatchtowerStructure(StructureSettings config) {
        super(config);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        Rotation rotation = Rotation.getRandom(context.random());
        BlockPos pos = this.getLowestYIn5by5BoxOffset7Blocks(context, rotation);
        return pos.getY() < 60 ? Optional.empty() : Optional.of(new GenerationStub(pos, (builder) -> this.generatePieces(builder, context, pos, rotation)));
    }

    private void generatePieces(StructurePiecesBuilder builder, GenerationContext context, BlockPos pos, Rotation rotation) {
        List<MalachiteWatchtowerPieces.Piece> list = Lists.newLinkedList();
        MalachiteWatchtowerPieces.buildStructure(context.structureTemplateManager(), pos, rotation, list, context.random());
        list.forEach(builder::addPiece);
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

                        world.setBlock(mutable, ModBlocks.malachite_bricks.get().defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.MALACHITE_WATCHTOWER_TYPE.get();
    }
}
