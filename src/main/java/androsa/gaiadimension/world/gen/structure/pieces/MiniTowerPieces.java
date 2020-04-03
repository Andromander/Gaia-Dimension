package androsa.gaiadimension.world.gen.structure.pieces;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.block.tileentity.SmallCrateTileEntity;
import androsa.gaiadimension.registry.ModBlocks;
import androsa.gaiadimension.registry.ModWorldgen;
import androsa.gaiadimension.world.gen.structure.processor.BlockDegradeProcessor;
import androsa.gaiadimension.world.gen.structure.processor.MiniTowerType;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.storage.loot.LootTables;

import java.util.List;
import java.util.Random;

public class MiniTowerPieces {
    private static final ResourceLocation am_base = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/amethyst/base");
    private static final ResourceLocation am_floor_1 = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/amethyst/floor_1");
    private static final ResourceLocation am_floor_2 = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/amethyst/floor_2");
    private static final ResourceLocation am_roof = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/amethyst/roof");
    private static final ResourceLocation co_base = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/copal/base");
    private static final ResourceLocation co_floor_1 = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/copal/floor_1");
    private static final ResourceLocation co_floor_2 = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/copal/floor_2");
    private static final ResourceLocation co_roof = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/copal/roof");
    private static final ResourceLocation ja_base = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/jade/base");
    private static final ResourceLocation ja_floor_1 = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/jade/floor_1");
    private static final ResourceLocation ja_floor_2 = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/jade/floor_2");
    private static final ResourceLocation ja_roof = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/jade/roof");
    private static final ResourceLocation je_base = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/jet/base");
    private static final ResourceLocation je_floor_1 = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/jet/floor_1");
    private static final ResourceLocation je_floor_2 = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/jet/floor_2");
    private static final ResourceLocation je_roof = new ResourceLocation(GaiaDimensionMod.MODID, "minitower/jet/roof");

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

    private static final ImmutableMap<ResourceLocation, BlockPos> centerList = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(am_base, baseCenter).put(co_base, baseCenter).put(ja_base, baseCenter).put(je_base, baseCenter)
            .put(am_floor_1, f1Center).put(co_floor_1, f1Center).put(ja_floor_1, f1Center).put(je_floor_1, f1Center)
            .put(am_floor_2, f2Center).put(co_floor_2, f2Center).put(ja_floor_2, f2Center).put(je_floor_2, f2Center)
            .put(am_roof, roofCenter).put(co_roof, roofCenter).put(ja_roof, roofCenter).put(je_roof, roofCenter)
            .build();
    private static final ImmutableMap<ResourceLocation, BlockPos> piecePos = ImmutableMap.<ResourceLocation, BlockPos>builder()
            .put(am_base, blockpos_1).put(co_base, blockpos_1).put(ja_base, blockpos_1).put(je_base, blockpos_1)
            .put(am_floor_1, blockpos_2).put(co_floor_1, blockpos_2).put(ja_floor_1, blockpos_2).put(je_floor_1, blockpos_2)
            .put(am_floor_2, blockpos_2).put(co_floor_2, blockpos_2).put(ja_floor_2, blockpos_2).put(je_floor_2, blockpos_2)
            .put(am_roof, blockpos_1).put(co_roof, blockpos_1).put(ja_roof, blockpos_1).put(je_roof, blockpos_1)
            .build();

    public static void buildStructure(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, Random random) {
        ResourceLocation[] piecearray;
        MiniTowerType type;
        switch (random.nextInt(MiniTowerType.values().length)) {
            case 0:
                piecearray = amethyst_pieces;
                type = MiniTowerType.AMETHYST;
                break;
            case 1:
                piecearray = copal_pieces;
                type = MiniTowerType.COPAL;
                break;
            case 2:
                piecearray = jade_pieces;
                type = MiniTowerType.JADE;
                break;
            case 3:
            default:
                piecearray = jet_pieces;
                type = MiniTowerType.JET;
                break;
        }

        int i = 0;
        pieces.add(new MiniTowerPieces.Piece(manager, piecearray[0], pos, rotation, type, i));
        i += 8;
        pieces.add(new MiniTowerPieces.Piece(manager, piecearray[1], pos, rotation, type, i));
        i += 11;
        pieces.add(new MiniTowerPieces.Piece(manager, piecearray[2], pos, rotation, type, i));
        i += 9;
        pieces.add(new MiniTowerPieces.Piece(manager, piecearray[3], pos, rotation, type, i));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation pieceLocation;
        private final Rotation rotation;
        private final MiniTowerType towerType;

        public Piece(TemplateManager manager, ResourceLocation pieceloc, BlockPos pos, Rotation rot, MiniTowerType type, int offset) {
            super(ModWorldgen.MITO, 0);
            this.pieceLocation = pieceloc;
            BlockPos blockpos = MiniTowerPieces.piecePos.get(pieceloc);
            this.templatePosition = pos.add(blockpos.getX(), blockpos.getY() + offset, blockpos.getZ());
            this.rotation = rot;
            this.towerType = type;
            this.loadTemplate(manager);
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {
            super(ModWorldgen.MITO, nbt);
            this.pieceLocation = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.valueOf(nbt.getString("Rot"));
            this.towerType = MiniTowerType.valueOf(nbt.getString("TowerType"));
            this.loadTemplate(manager);
        }

        private void loadTemplate(TemplateManager manager) {
            Template template = manager.getTemplateDefaulted(this.pieceLocation);
            PlacementSettings settings = (new PlacementSettings())
                    .setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(MiniTowerPieces.centerList.get(this.pieceLocation))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);

            switch (towerType) {
                case AMETHYST:
                    settings.addProcessor(BlockDegradeProcessor.AMETHYST_DECAY);
                    break;
                case COPAL:
                    settings.addProcessor(BlockDegradeProcessor.COPAL_DECAY);
                    break;
                case JADE:
                    settings.addProcessor(BlockDegradeProcessor.JADE_DECAY);
                    break;
                case JET:
                    settings.addProcessor(BlockDegradeProcessor.JET_DECAY);
                    break;
            }

            this.setup(template, this.templatePosition, settings);
        }

        @Override
        protected void readAdditional(CompoundNBT nbt) {
            super.readAdditional(nbt);
            nbt.putString("Template", this.pieceLocation.toString());
            nbt.putString("Rot", this.rotation.name());
            nbt.putString("TowerType", this.towerType.getName());
        }

        @Override
        protected void handleDataMarker(String name, BlockPos pos, IWorld world, Random random, MutableBoundingBox mbb) {
            if ("Chest".equals(name)) {
                if (random.nextDouble() > 0.5D) {
                    world.setBlockState(pos, ModBlocks.crude_storage_crate.get().getDefaultState(), 3);
                    TileEntity tileentity = world.getTileEntity(pos);
                    if (tileentity instanceof SmallCrateTileEntity) {
                        ((SmallCrateTileEntity) tileentity).setLootTable(towerType.getChestLoot(), random.nextLong());
                    }
                } else {
                    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                }
            }
        }

        @Override
        public boolean generate(IWorld world, ChunkGenerator<?> generator, Random random, MutableBoundingBox mbb, ChunkPos chunkpos) {
            this.placeSettings
                    .setRotation(this.rotation)
                    .setMirror(Mirror.NONE)
                    .setCenterOffset(MiniTowerPieces.centerList.get(this.pieceLocation))
                    .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);

            switch (towerType) {
                case AMETHYST:
                    this.placeSettings.addProcessor(BlockDegradeProcessor.AMETHYST_DECAY);
                    break;
                case COPAL:
                    this.placeSettings.addProcessor(BlockDegradeProcessor.COPAL_DECAY);
                    break;
                case JADE:
                    this.placeSettings.addProcessor(BlockDegradeProcessor.JADE_DECAY);
                    break;
                case JET:
                    this.placeSettings.addProcessor(BlockDegradeProcessor.JET_DECAY);
                    break;
            }

            BlockPos blockpos = MiniTowerPieces.piecePos.get(this.pieceLocation);
            BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(placeSettings, new BlockPos(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
            int height = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.templatePosition;
            this.templatePosition = this.templatePosition.add(0, height - 90 - 1, 0);
            boolean flag = super.generate(world, generator, random, mbb, chunkpos);
            this.templatePosition = blockpos2;
            return flag;
        }
    }
}
