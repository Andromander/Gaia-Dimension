package androsa.gaiadimension.world.gen.structure.pieces;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.tileentity.SmallCrateTileEntity;
import androsa.gaiadimension.registry.GaiaChestTables;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.gen.structure.processor.MalachiteDegradeProcessor;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

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
    private static final BlockPos blockpos_1 = new BlockPos(0, 0, 0);
    private static final BlockPos blockpos_2 = new BlockPos(1, 0, 0);
    private static final BlockPos blockpos_3 = new BlockPos(-2, 0, -3);

    protected static final ImmutableMap<ResourceLocation, BlockPos> centerList = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(foyer, baseCenter)
            .put(floor1_1, f1Center).put(floor1_2, f1Center).put(floor1_3, f1Center)
            .put(floor_random1, f1Center).put(floor_random2, f1Center).put(floor_random3, f1Center).put(floor_random4, f1Center).put(floor_random5, f1Center)
            .put(floor_random1_m, f1Center).put(floor_random2_m, f1Center).put(floor_random3_m, f1Center).put(floor_random4_m, f1Center).put(floor_random5_m, f1Center)
            .put(roof, roofCenter)
            .put(roof_m, roofCenter)
            .build();
    private static final ImmutableMap<ResourceLocation, BlockPos> piecePos = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(foyer, blockpos_1)
            .put(floor1_1, blockpos_2).put(floor1_2, blockpos_2).put(floor1_3, blockpos_2)
            .put(floor_random1, blockpos_2).put(floor_random2, blockpos_2).put(floor_random3, blockpos_2).put(floor_random4, blockpos_2).put(floor_random5, blockpos_2)
            .put(floor_random1_m, blockpos_2).put(floor_random2_m, blockpos_2).put(floor_random3_m, blockpos_2).put(floor_random4_m, blockpos_2).put(floor_random5_m, blockpos_2)
            .put(roof, blockpos_3)
            .put(roof_m, blockpos_3)
            .build();

    public static void buildStructure(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, Random random) {
        int i = 0;
        pieces.add(new MalachiteWatchtowerPieces.Piece(manager, foyer, pos, rotation, i));
        i += 12;
        pieces.add(new MalachiteWatchtowerPieces.Piece(manager, first_floors[random.nextInt(first_floors.length)], pos, rotation, i));

        int r = random.nextInt(2) + 4;
        for (int f = 0; f < r; f++) {
            i += 10;
            if (f == r - 1) {
                if (f % 2 == 0) {
                    pieces.add(new MalachiteWatchtowerPieces.Piece(manager, roof_m, pos, rotation, i));
                } else {
                    pieces.add(new MalachiteWatchtowerPieces.Piece(manager, roof, pos, rotation, i));
                }
            } else {
                if (f % 2 == 0) {
                    pieces.add(new MalachiteWatchtowerPieces.Piece(manager, next_floors[random.nextInt(next_floors.length)], pos, rotation, i));
                } else {
                    pieces.add(new MalachiteWatchtowerPieces.Piece(manager, next_floors_m[random.nextInt(next_floors_m.length)], pos, rotation, i));
                }
            }
        }
    }

    public static ResourceLocation makePiece(String part) {
        return new ResourceLocation(GaiaDimensionMod.MODID, "watchtower/" + part);
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation pieceLocation;
        private final Rotation rotation;

        public Piece(TemplateManager manager, ResourceLocation pieceloc, BlockPos pos, Rotation rot, int offset) {
            super(ModWorldgen.MAWA, 0);
            this.pieceLocation = pieceloc;
            BlockPos blockpos = MalachiteWatchtowerPieces.piecePos.get(pieceloc);
            this.templatePosition = pos.add(blockpos.getX(), blockpos.getY() + offset, blockpos.getZ());
            this.rotation = rot;
            this.loadTemplate(manager);
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {
            super(ModWorldgen.MAWA, nbt);
            this.pieceLocation = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.valueOf(nbt.getString("Rot"));
            this.loadTemplate(manager);
        }

        private void loadTemplate(TemplateManager manager) {
            Template template = manager.getTemplateDefaulted(this.pieceLocation);
            PlacementSettings settings = (new PlacementSettings())
                    .setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(MalachiteWatchtowerPieces.centerList.get(this.pieceLocation))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK)
                    .addProcessor(new MalachiteDegradeProcessor(0.2F));
            this.setup(template, this.templatePosition, settings);
        }

        @Override
        protected void readAdditional(CompoundNBT nbt) {
            super.readAdditional(nbt);
            nbt.putString("Template", this.pieceLocation.toString());
            nbt.putString("Rot", this.rotation.name());
        }

        @Override
        protected void handleDataMarker(String name, BlockPos pos, IServerWorld world, Random random, MutableBoundingBox mbb) {
            if ("ChestChance".equals(name)) {
                if (random.nextDouble() > 0.5D) {
                    world.setBlockState(pos, ModBlocks.crude_storage_crate.getDefaultState(), 3);
                    TileEntity tileentity = world.getTileEntity(pos);
                    if (tileentity instanceof SmallCrateTileEntity) {
                        ((SmallCrateTileEntity) tileentity).setLootTable(GaiaChestTables.CHESTS_MALACHITE_WATCHTOWER, random.nextLong());
                    }
                } else {
                    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                }
            }
            if ("Chest".equals(name)) {
                world.setBlockState(pos, ModBlocks.crude_storage_crate.getDefaultState(), 3);
                TileEntity tileentity = world.getTileEntity(pos);
                if (tileentity instanceof SmallCrateTileEntity) {
                    ((SmallCrateTileEntity) tileentity).setLootTable(GaiaChestTables.CHESTS_MALACHITE_WATCHTOWER, random.nextLong());
                }
            }
            if ("Boss".equals(name)) {
                world.setBlockState(pos, ModBlocks.malachite_guard_spawner.getDefaultState(), 3);
            }
        }

        @Override
        public boolean func_230383_a_(ISeedReader world, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox mbb, ChunkPos chunkpos, BlockPos pos) {
            this.placeSettings
                    .setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(MalachiteWatchtowerPieces.centerList.get(this.pieceLocation))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK)
                    .addProcessor(new MalachiteDegradeProcessor(0.2F));

            BlockPos blockpos = MalachiteWatchtowerPieces.piecePos.get(this.pieceLocation);
            BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(placeSettings, new BlockPos(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
            int height = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, height - 90 - 1, 0);
            boolean flag = super.func_230383_a_(world, manager, generator, random, mbb, chunkpos, pos);
            this.templatePosition = blockpos2;
            return flag;
        }
    }
}
