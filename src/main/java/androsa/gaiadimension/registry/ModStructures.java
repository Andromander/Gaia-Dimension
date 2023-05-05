package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.world.gen.structure.MalachiteWatchtowerStructure;
import androsa.gaiadimension.world.gen.structure.MiniTowerStructure;
import androsa.gaiadimension.world.gen.structure.pieces.MalachiteWatchtowerPieces;
import androsa.gaiadimension.world.gen.structure.pieces.MiniTowerPieces;
import androsa.gaiadimension.world.gen.structure.processor.BlockDegradeProcessor;
import androsa.gaiadimension.world.gen.structure.processor.MalachiteDegradeProcessor;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.function.Supplier;

public final class ModStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, GaiaDimensionMod.MODID);
    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, GaiaDimensionMod.MODID);
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECES = DeferredRegister.create(Registries.STRUCTURE_PIECE, GaiaDimensionMod.MODID);

    public static final RegistryObject<StructureType<MiniTowerStructure>> MINI_TOWER_TYPE = STRUCTURE_TYPES.register("mini_tower", () -> () -> MiniTowerStructure.CODEC);
    public static final RegistryObject<StructureType<MalachiteWatchtowerStructure>> MALACHITE_WATCHTOWER_TYPE = STRUCTURE_TYPES.register("malachite_watchtower", () -> () -> MalachiteWatchtowerStructure.CODEC);

    public static final ResourceKey<Structure> MINI_TOWER = makeStructure("mini_tower");
    public static final ResourceKey<Structure> MALACHITE_WATCHTOWER = makeStructure("malachite_watchtower");

    public static final ResourceKey<StructureSet> MINI_TOWER_SET = makeSet("mini_tower");
    public static final ResourceKey<StructureSet> MALACHITE_WATCHTOWER_SET = makeSet("malachite_watchtower");

    public static final RegistryObject<StructureProcessorType<BlockDegradeProcessor>> BLOCK_DEGRADE = STRUCTURE_PROCESSORS.register("block_degrade", () -> () -> BlockDegradeProcessor.CODEC);
    public static final RegistryObject<StructureProcessorType<MalachiteDegradeProcessor>> MALACHITE_DEGRADE = STRUCTURE_PROCESSORS.register("malachite_degrade", () -> () -> MalachiteDegradeProcessor.CODEC);

    public static final RegistryObject<StructurePieceType> MITO = STRUCTURE_PIECES.register("mito", piece(MiniTowerPieces.Piece::new));
    public static final RegistryObject<StructurePieceType> MAWA = STRUCTURE_PIECES.register("mawa", piece(MalachiteWatchtowerPieces.Piece::new));

    private static ResourceKey<Structure> makeStructure(String name) {
        return ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(GaiaDimensionMod.MODID, name));
    }

    private static ResourceKey<StructureSet> makeSet(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(GaiaDimensionMod.MODID, name));
    }

    public static Supplier<StructurePieceType> piece(StructurePieceType.StructureTemplateType piece) {
        return () -> piece;
    }

    public static void initStructures(BootstapContext<Structure> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        context.register(MINI_TOWER, new MiniTowerStructure(new Structure.StructureSettings(biomes.getOrThrow(GaiaTags.Biomes.HAS_MINI_TOWER), Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE)));
        context.register(MALACHITE_WATCHTOWER, new MalachiteWatchtowerStructure(new Structure.StructureSettings(biomes.getOrThrow(GaiaTags.Biomes.HAS_MALACHITE_WATCHTOWER), MalachiteWatchtowerStructure.SPAWNS, GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE)));
    }

    public static void initSets(BootstapContext<StructureSet> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);
        context.register(MINI_TOWER_SET, new StructureSet(structures.getOrThrow(MINI_TOWER), new RandomSpreadStructurePlacement(30, 10, RandomSpreadType.LINEAR, 420)));
        context.register(MALACHITE_WATCHTOWER_SET, new StructureSet(structures.getOrThrow(MALACHITE_WATCHTOWER), new RandomSpreadStructurePlacement(35, 15, RandomSpreadType.TRIANGULAR, 621)));
    }
}
