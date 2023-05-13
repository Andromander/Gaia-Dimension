package androsa.gaiadimension.world.gen.structure.pieces;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.blockentity.SmallCrateBlockEntity;
import androsa.gaiadimension.registry.values.GaiaChestTables;
import androsa.gaiadimension.registry.registration.ModBlocks;
import androsa.gaiadimension.registry.registration.ModStructures;
import androsa.gaiadimension.world.gen.structure.processor.MalachiteDegradeProcessor;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.List;

public class MalachiteWatchtowerPieces {

    private static final ResourceLocation foyer = makePiece("foyer");
    private static final ResourceLocation floor1_1 = makePiece("floor1_1");
    private static final ResourceLocation floor1_2 = makePiece("floor1_2");
    private static final ResourceLocation floor1_3 = makePiece("floor1_3");
    private static final ResourceLocation floor_random1 = makePiece("floor_random1");
    private static final ResourceLocation floor_random2 = makePiece("floor_random2");
    private static final ResourceLocation floor_random3 = makePiece("floor_random3");
    private static final ResourceLocation floor_random4 = makePiece("floor_random4");
    private static final ResourceLocation floor_random5 = makePiece("floor_random5");
    private static final ResourceLocation floor_random1_m = makePiece("floor_random1_m");
    private static final ResourceLocation floor_random2_m = makePiece("floor_random2_m");
    private static final ResourceLocation floor_random3_m = makePiece("floor_random3_m");
    private static final ResourceLocation floor_random4_m = makePiece("floor_random4_m");
    private static final ResourceLocation floor_random5_m = makePiece("floor_random5_m");
    private static final ResourceLocation roof = makePiece("roof");
    private static final ResourceLocation roof_m = makePiece("roof_m");

    private static final ResourceLocation[] first_floors = new ResourceLocation[]{floor1_1, floor1_2, floor1_3};
    private static final ResourceLocation[] next_floors = new ResourceLocation[]{floor_random1, floor_random2, floor_random3, floor_random4, floor_random5};
    private static final ResourceLocation[] next_floors_m = new ResourceLocation[]{floor_random1_m, floor_random2_m, floor_random3_m, floor_random4_m, floor_random5_m};

    private static final BlockPos baseCenter = new BlockPos(15, 15, 15);
    private static final BlockPos f1Center = new BlockPos(12, 11, 12);
    private static final BlockPos roofCenter = new BlockPos(16, 23, 16);

    private static final BlockPos offsetNoneBig = new BlockPos(5, 0, 6);
    private static final BlockPos offsetC90Big = new BlockPos(0, 0, 5);
    private static final BlockPos offsetC180Big = new BlockPos(1, 0, 0);
    private static final BlockPos offsetCC90Big = new BlockPos(6, 0, 1);

    private static final BlockPos offsetNoneSmall = new BlockPos(0, 0, 1);
    private static final BlockPos offsetC90Small = new BlockPos(-3, 0, 0);
    private static final BlockPos offsetC180Small = new BlockPos(-2, 0, -3);
    private static final BlockPos offsetCC90Small = new BlockPos(1, 0, -2);

    private static final ImmutableMap<Rotation, BlockPos> offsetBig = ImmutableMap.of(
            Rotation.NONE, offsetNoneBig,
            Rotation.CLOCKWISE_90, offsetC90Big,
            Rotation.CLOCKWISE_180, offsetC180Big,
            Rotation.COUNTERCLOCKWISE_90, offsetCC90Big
    );

    private static final ImmutableMap<Rotation, BlockPos> offsetSmall = ImmutableMap.of(
            Rotation.NONE, offsetNoneSmall,
            Rotation.CLOCKWISE_90, offsetC90Small,
            Rotation.CLOCKWISE_180, offsetC180Small,
            Rotation.COUNTERCLOCKWISE_90, offsetCC90Small
    );

    protected static final ImmutableMap<ResourceLocation, BlockPos> PIVOTS = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(foyer, baseCenter)
            .put(floor1_1, f1Center).put(floor1_2, f1Center).put(floor1_3, f1Center)
            .put(floor_random1, f1Center).put(floor_random2, f1Center).put(floor_random3, f1Center).put(floor_random4, f1Center).put(floor_random5, f1Center)
            .put(floor_random1_m, f1Center).put(floor_random2_m, f1Center).put(floor_random3_m, f1Center).put(floor_random4_m, f1Center).put(floor_random5_m, f1Center)
            .put(roof, roofCenter)
            .put(roof_m, roofCenter)
            .build();

    public static void buildStructure(StructureTemplateManager manager, BlockPos pos, Rotation rotation, List<Piece> pieces, RandomSource random) {
        int i = 0;
        pieces.add(new MalachiteWatchtowerPieces.Piece(manager, foyer, pos, rotation, i));
        i += 14;
        pieces.add(new MalachiteWatchtowerPieces.Piece(manager, first_floors[random.nextInt(first_floors.length)], pos.offset(offsetBig.get(rotation)), rotation, i));

        int r = random.nextInt(2) + 4;
        for (int f = 0; f < r; f++) {
            i += 10;
            if (f == r - 1) {
                if (f % 2 == 0) {
                    pieces.add(new MalachiteWatchtowerPieces.Piece(manager, roof_m, pos.offset(offsetSmall.get(rotation)), rotation, i));
                } else {
                    pieces.add(new MalachiteWatchtowerPieces.Piece(manager, roof, pos.offset(offsetSmall.get(rotation)), rotation, i));
                }
            } else {
                if (f % 2 == 0) {
                    pieces.add(new MalachiteWatchtowerPieces.Piece(manager, next_floors[random.nextInt(next_floors.length)], pos.offset(offsetBig.get(rotation)), rotation, i));
                } else {
                    pieces.add(new MalachiteWatchtowerPieces.Piece(manager, next_floors_m[random.nextInt(next_floors_m.length)], pos.offset(offsetBig.get(rotation)), rotation, i));
                }
            }
        }
    }

    public static ResourceLocation makePiece(String part) {
        return new ResourceLocation(GaiaDimensionMod.MODID, "watchtower/" + part);
    }

    public static class Piece extends TemplateStructurePiece {
        public Piece(StructureTemplateManager manager, ResourceLocation pieceloc, BlockPos pos, Rotation rot, int offset) {
            super(ModStructures.MAWA.get(), 0, manager, pieceloc, pieceloc.toString(), loadTemplate(rot, pieceloc), loadPosition(pos, offset));
        }

        public Piece(StructureTemplateManager level, CompoundTag nbt) {
            super(ModStructures.MAWA.get(), nbt, level, (rl) ->
                    loadTemplate(Rotation.valueOf(nbt.getString("Rot")), rl));
        }

        private static StructurePlaceSettings loadTemplate(Rotation rotation, ResourceLocation pivot) {
            return (new StructurePlaceSettings())
                    .setRotation(rotation)
                    .setMirror(Mirror.NONE)
                    .setRotationPivot(MalachiteWatchtowerPieces.PIVOTS.get(pivot))
                    .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK)
                    .addProcessor(new MalachiteDegradeProcessor(0.2F));
        }

        private static BlockPos loadPosition(BlockPos pos, int offset) {
            return pos.offset(0, offset, 0);
        }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext level, CompoundTag nbt) {
            super.addAdditionalSaveData(level, nbt);
            nbt.putString("Rot", this.placeSettings.getRotation().name());
        }

        @Override
        protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor world, RandomSource random, BoundingBox mbb) {
            if ("ChestChance".equals(name)) {
                if (random.nextDouble() > 0.5D) {
                    world.setBlock(pos, ModBlocks.crude_storage_crate.get().defaultBlockState(), 3);
                    BlockEntity tileentity = world.getBlockEntity(pos);
                    if (tileentity instanceof SmallCrateBlockEntity) {
                        ((SmallCrateBlockEntity) tileentity).setLootTable(GaiaChestTables.CHESTS_MALACHITE_WATCHTOWER, random.nextLong());
                    }
                } else {
                    world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                }
            }
            if ("Chest".equals(name)) {
                world.setBlock(pos, ModBlocks.crude_storage_crate.get().defaultBlockState(), 3);
                BlockEntity tileentity = world.getBlockEntity(pos);
                if (tileentity instanceof SmallCrateBlockEntity) {
                    ((SmallCrateBlockEntity) tileentity).setLootTable(GaiaChestTables.CHESTS_MALACHITE_WATCHTOWER, random.nextLong());
                }
            }
            if ("Boss".equals(name)) {
                world.setBlock(pos, ModBlocks.malachite_guard_spawner.get().defaultBlockState(), 3);
            }
        }
    }
}
