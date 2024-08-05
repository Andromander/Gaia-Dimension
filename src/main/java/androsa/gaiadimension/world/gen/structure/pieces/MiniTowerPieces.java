package androsa.gaiadimension.world.gen.structure.pieces;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.blockentity.SmallCrateBlockEntity;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModStructures;
import androsa.gaiadimension.world.gen.structure.processor.BlockDegradeProcessor;
import androsa.gaiadimension.world.gen.structure.processor.MiniTowerType;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class MiniTowerPieces {
    private static final ResourceLocation am_base = makePiece("amethyst", "base");
    private static final ResourceLocation am_floor_1 = makePiece("amethyst", "floor_1");
    private static final ResourceLocation am_floor_2 = makePiece("amethyst", "floor_2");
    private static final ResourceLocation am_roof = makePiece("amethyst", "roof");
    private static final ResourceLocation co_base = makePiece("copal", "base");
    private static final ResourceLocation co_floor_1 = makePiece("copal", "floor_1");
    private static final ResourceLocation co_floor_2 = makePiece("copal", "floor_2");
    private static final ResourceLocation co_roof = makePiece("copal", "roof");
    private static final ResourceLocation ja_base = makePiece("jade", "base");
    private static final ResourceLocation ja_floor_1 = makePiece("jade", "floor_1");
    private static final ResourceLocation ja_floor_2 = makePiece("jade", "floor_2");
    private static final ResourceLocation ja_roof = makePiece("jade", "roof");
    private static final ResourceLocation je_base = makePiece("jet", "base");
    private static final ResourceLocation je_floor_1 = makePiece("jet", "floor_1");
    private static final ResourceLocation je_floor_2 = makePiece("jet", "floor_2");
    private static final ResourceLocation je_roof = makePiece("jet", "roof");

    private static final ResourceLocation[] amethyst_pieces = new ResourceLocation[]{am_base, am_floor_1, am_floor_2, am_roof};
    private static final ResourceLocation[] copal_pieces = new ResourceLocation[]{co_base, co_floor_1, co_floor_2, co_roof};
    private static final ResourceLocation[] jade_pieces = new ResourceLocation[]{ja_base, ja_floor_1, ja_floor_2, ja_roof};
    private static final ResourceLocation[] jet_pieces = new ResourceLocation[]{je_base, je_floor_1, je_floor_2, je_roof};

    private static final BlockPos baseCenter = new BlockPos(8, 7, 8);
    private static final BlockPos f1Center = new BlockPos(7, 11, 7);
    private static final BlockPos f2Center = new BlockPos(7, 10, 7);
    private static final BlockPos roofCenter = new BlockPos(8, 9, 8);
    private static final BlockPos blockpos_1 = new BlockPos(0, 1, 0);
    private static final BlockPos blockpos_2 = new BlockPos(1, 0, 1);

    private static final ImmutableMap<ResourceLocation, BlockPos> PIVOTS = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(am_base, baseCenter).put(co_base, baseCenter).put(ja_base, baseCenter).put(je_base, baseCenter)
            .put(am_floor_1, f1Center).put(co_floor_1, f1Center).put(ja_floor_1, f1Center).put(je_floor_1, f1Center)
            .put(am_floor_2, f2Center).put(co_floor_2, f2Center).put(ja_floor_2, f2Center).put(je_floor_2, f2Center)
            .put(am_roof, roofCenter).put(co_roof, roofCenter).put(ja_roof, roofCenter).put(je_roof, roofCenter)
            .build();
    private static final ImmutableMap<ResourceLocation, BlockPos> OFFSETS = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(am_base, blockpos_1).put(co_base, blockpos_1).put(ja_base, blockpos_1).put(je_base, blockpos_1)
            .put(am_floor_1, blockpos_2).put(co_floor_1, blockpos_2).put(ja_floor_1, blockpos_2).put(je_floor_1, blockpos_2)
            .put(am_floor_2, blockpos_2).put(co_floor_2, blockpos_2).put(ja_floor_2, blockpos_2).put(je_floor_2, blockpos_2)
            .put(am_roof, blockpos_1).put(co_roof, blockpos_1).put(ja_roof, blockpos_1).put(je_roof, blockpos_1)
            .build();

    public static void buildStructure(StructureTemplateManager manager, BlockPos pos, Rotation rotation, StructurePieceAccessor pieces, RandomSource random) {
        ResourceLocation[] piecearray;
        MiniTowerType type;
        switch (random.nextInt(MiniTowerType.values().length)) {
            case 0 -> {
                piecearray = amethyst_pieces;
                type = MiniTowerType.AMETHYST;
            }
            case 1 -> {
                piecearray = copal_pieces;
                type = MiniTowerType.COPAL;
            }
            case 2 -> {
                piecearray = jade_pieces;
                type = MiniTowerType.JADE;
            }
            default -> {
                piecearray = jet_pieces;
                type = MiniTowerType.JET;
            }
        }

        int i = 0;
        pieces.addPiece(new MiniTowerPieces.Piece(manager, piecearray[0], pos, rotation, type, i));
        i += 8;
        pieces.addPiece(new MiniTowerPieces.Piece(manager, piecearray[1], pos, rotation, type, i));
        i += 11;
        pieces.addPiece(new MiniTowerPieces.Piece(manager, piecearray[2], pos, rotation, type, i));
        i += 9;
        pieces.addPiece(new MiniTowerPieces.Piece(manager, piecearray[3], pos, rotation, type, i));
    }

    public static ResourceLocation makePiece(String material, String part) {
        return ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "minitower/" + material + "/" + part);
    }

    public static class Piece extends TemplateStructurePiece {
        private final MiniTowerType towerType;

        public Piece(StructureTemplateManager manager, ResourceLocation pieceloc, BlockPos pos, Rotation rot, MiniTowerType type, int offset) {
            super(ModStructures.MITO.get(), 0, manager, pieceloc, pieceloc.toString(), loadTemplate(rot, pieceloc), loadPosition(pieceloc, pos, offset));
            this.towerType = type;
        }

        public Piece(StructureTemplateManager level, CompoundTag nbt) {
            super(ModStructures.MITO.get(), nbt, level, (rl) ->
                    loadTemplate(Rotation.valueOf(nbt.getString("Rot")), rl));
            this.towerType = MiniTowerType.valueOf(nbt.getString("TowerType"));
        }

        private static StructurePlaceSettings loadTemplate(Rotation rotation, ResourceLocation pivot) {
            return (new StructurePlaceSettings())
                    .setRotation(rotation)
                    .setMirror(Mirror.NONE)
                    .setRotationPivot(MiniTowerPieces.PIVOTS.get(pivot))
                    .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);

//            switch (towerType) {
//                case AMETHYST:
//                    settings.addProcessor(BlockDegradeProcessor.AMETHYST_DECAY);
//                    break;
//                case COPAL:
//                    settings.addProcessor(BlockDegradeProcessor.COPAL_DECAY);
//                    break;
//                case JADE:
//                    settings.addProcessor(BlockDegradeProcessor.JADE_DECAY);
//                    break;
//                case JET:
//                    settings.addProcessor(BlockDegradeProcessor.JET_DECAY);
//                    break;
//            }
        }

        private static BlockPos loadPosition(ResourceLocation rl, BlockPos pos, int offset) {
            return pos.offset(MiniTowerPieces.OFFSETS.get(rl)).above(offset);
        }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext level, CompoundTag nbt) {
            super.addAdditionalSaveData(level, nbt);
            nbt.putString("Rot", this.placeSettings.getRotation().name());
            nbt.putString("TowerType", this.towerType.toString());
        }

        @Override
        protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor world, RandomSource random, BoundingBox mbb) {
            if ("Chest".equals(name)) {
                if (random.nextDouble() > 0.5D) {
                    world.setBlock(pos, ModBlocks.crude_storage_crate.get().defaultBlockState(), 3);
                    BlockEntity tileentity = world.getBlockEntity(pos);
                    if (tileentity instanceof SmallCrateBlockEntity) {
                        ((SmallCrateBlockEntity) tileentity).setLootTable(towerType.getChestLoot(), random.nextLong());
                    }
                } else {
                    world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }

        @Override
        public void postProcess(WorldGenLevel world, StructureManager manager, ChunkGenerator generator, RandomSource random, BoundingBox mbb, ChunkPos chunkpos, BlockPos pos) {
            ResourceLocation location = ResourceLocation.parse(this.templateName);

            switch (towerType) {
                case AMETHYST -> placeSettings.addProcessor(BlockDegradeProcessor.AMETHYST_DECAY);
                case COPAL -> placeSettings.addProcessor(BlockDegradeProcessor.COPAL_DECAY);
                case JADE -> placeSettings.addProcessor(BlockDegradeProcessor.JADE_DECAY);
                case JET -> placeSettings.addProcessor(BlockDegradeProcessor.JET_DECAY);
            }

            BlockPos offsetpos = MiniTowerPieces.OFFSETS.get(location);
            BlockPos tempoffset = this.templatePosition.offset(StructureTemplate.calculateRelativePosition(placeSettings, new BlockPos(3 - offsetpos.getX(), 0, -offsetpos.getZ())));
            int height = world.getHeight(Heightmap.Types.WORLD_SURFACE_WG, tempoffset.getX(), tempoffset.getZ());
            BlockPos temppos = this.templatePosition;
            this.templatePosition = this.templatePosition.offset(0, height - 90 - 1, 0);
            super.postProcess(world, manager, generator, random, mbb, chunkpos, pos);
            this.templatePosition = temppos;
        }
    }
}
