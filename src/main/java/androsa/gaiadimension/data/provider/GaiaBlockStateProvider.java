package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.block.CurtainBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class GaiaBlockStateProvider extends BlockStateProvider {

    private final GaiaBlockModelProvider blockModels;

    public GaiaBlockStateProvider(PackOutput output, String modid, ExistingFileHelper helper) {
        super(output, modid, helper);

        blockModels = new GaiaBlockModelProvider(output, helper) {
            @Override
            protected void registerModels() { }

            @Override
            public String getName() {
                return GaiaBlockStateProvider.this.getName();
            }
        };
    }

    @Override
    public GaiaBlockModelProvider models() {
        return blockModels;
    }

    protected ResourceLocation tLocGaia(String name) {
        return modLoc("block/" + name);
    }

    protected String blockName(DeferredBlock<? extends Block> block) {
        return block.getId().getPath();
    }

    public void basicBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public void leavesBlock(DeferredBlock<? extends Block> block) {
        basicBlock(block, "cutout_mipped");
    }

    public void basicBlock(DeferredBlock<? extends Block> block, String type) {
        simpleBlock(block.get(), models().cubeAll(blockName(block), blockTexture(block.get())).renderType(type));
    }

    public void columnBlock(DeferredBlock<Block> block) {
        String basename = blockName(block);
        simpleBlock(block.get(), models().cubeColumn(basename, tLocGaia(basename), tLocGaia(basename + "_top")));
    }

    public void sidedBlock(DeferredBlock<Block> block, String top, String bottom, String north, String south, String east, String west) {
        simpleBlock(block.get(), models().cube(blockName(block), tLocGaia(bottom), tLocGaia(top), tLocGaia(north), tLocGaia(south), tLocGaia(east), tLocGaia(west)).texture("particle", tLocGaia(north)));
    }

    public void basicBlockRotated(DeferredBlock<Block> block) {
        basicBlockRotated(block, "solid");
    }

    public void basicBlockRotated(DeferredBlock<Block> block, String type) {
        Function<ModelFile, ConfiguredModel[]> expander = model -> ConfiguredModel.allYRotations(model, 0, false);
        simpleBlock(block.get(), expander.apply(models().cubeAll(blockName(block), blockTexture(block.get())).renderType(type)));
    }

    public void basicBlockLayered(DeferredBlock<Block> block, String bottom, String top, String type) {
        simpleBlock(block.get(), models().basicLayered(block, tLocGaia(bottom), tLocGaia(top)).renderType(type));
    }

    public void layeredEmissive(DeferredBlock<Block> block, String bottom, String top) {
        simpleBlock(block.get(), models().emissiveLayered(block, tLocGaia(bottom), tLocGaia(top)).renderType("cutout"));
    }

    public void logBlock(Supplier<RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), tLocGaia(name));
    }

    public void strippedLogBlock(Supplier<RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), tLocGaia("stripped_" + name));
    }

    public void strippedWoodBlock(DeferredBlock<RotatedPillarBlock> block, String name) {
        ModelFile model = models().cubeColumn(blockName(block), tLocGaia("stripped_" + name + "_log_side"), tLocGaia("stripped_" + name + "_log_side"));
        axisBlock(block.get(), model, model);
    }

    public void woodBlock(DeferredBlock<RotatedPillarBlock> block, String name) {
        ModelFile model = models().cubeColumn(blockName(block), tLocGaia(name + "_side"), tLocGaia(name + "_side"));
        axisBlock(block.get(), model, model);
    }

    public void stairsBlock(DeferredBlock<StairBlock> block, String name) {
        stairsBlock(block.get(), tLocGaia(name));
    }

    public void stairsBlockLayered(DeferredBlock<StairBlock> block, String inner, String outer, String type) {
        ModelFile stairs = models().stairsBasicLayer(block, tLocGaia(inner), tLocGaia(outer)).renderType(type);
        ModelFile stairsInner = models().stairsInnerBasicLayer(block, tLocGaia(inner), tLocGaia(outer)).renderType(type);
        ModelFile stairsOuter = models().stairsOuterBasicLayer(block, tLocGaia(inner), tLocGaia(outer)).renderType(type);
        stairsBlock(block.get(), stairs, stairsInner, stairsOuter);
    }

    public void slabBlock(DeferredBlock<SlabBlock> block, DeferredBlock<Block> doubleBlock) {
        slabBlock(block.get(), tLocGaia(blockName(doubleBlock)), tLocGaia(blockName(doubleBlock)));
    }

    public void crossBlock(DeferredBlock<? extends Block> block, String type) {
        crossBlock(block, models().cross(blockName(block), tLocGaia(blockName(block))).renderType(type));
    }

    public void crossBlockTinted(DeferredBlock<Block> block) {
        crossBlock(block, models().tintedCross(blockName(block), tLocGaia(blockName(block))).renderType("translucent"));
    }

    public void orientableBlockLit(DeferredBlock<Block> block) {
        ModelFile off = models().orientable(blockName(block), tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front"), tLocGaia(blockName(block) + "_top"));
        ModelFile on = models().orientable(blockName(block) + "_lit", tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front_lit"), tLocGaia(blockName(block) + "_top"));
        orientableBlock(block, off, on);
    }

    public void orientableBlockBasicLit(DeferredBlock<Block> block) {
        ModelFile off = models().orientable(blockName(block), tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front"), tLocGaia(blockName(block) + "_side"));
        ModelFile on = models().orientable(blockName(block) + "_lit", tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front_lit"), tLocGaia(blockName(block) + "_side"));
        orientableBlock(block, off, on);
    }

    public void grassBlock(DeferredBlock<Block> block, String bottom) {
        String baseName = blockName(block);
        ModelFile model = models().grass(
                        block,
                        tLocGaia(baseName + "_top"),
                        tLocGaia(bottom),
                        tLocGaia(baseName + "_side"),
                        tLocGaia(baseName + "_overlay"))
                .renderType("cutout_mipped");
        grassBlock(block, model);
    }

    public void pottedPlantBlock(DeferredBlock<FlowerPotBlock> block) {
        simpleBlock(block.get(), models().flowerPot(block).renderType("cutout"));
    }

    public void torchBlock(DeferredBlock<Block> block, DeferredBlock<Block> wall) {
        ModelFile torch = models().torch(blockName(block), tLocGaia(blockName(block))).renderType("cutout");
        ModelFile torchwall = models().torchWall(blockName(wall), tLocGaia(blockName(block))).renderType("cutout");
        simpleBlock(block.get(), torch);
        getVariantBuilder(wall.get()).forAllStates(state ->
                ConfiguredModel.builder()
                .modelFile(torchwall)
                .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 90) % 360)
                .build());
    }

    private void orientableBlock(DeferredBlock<Block> block, ModelFile off, ModelFile on) {
        getVariantBuilder(block.get()).forAllStates(state -> {
            ModelFile model = state.getValue(BlockStateProperties.LIT) ? on : off;

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360)
                    .build();
        });
    }

    private void crossBlock(Supplier<? extends Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state ->
                ConfiguredModel.builder()
                .modelFile(model)
                .build());
    }

    private void grassBlock(Supplier<Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.allYRotations(model, 0, false));
    }

    public void curtainBlock(DeferredBlock<? extends Block> block) {
        String name = block.getId().getPath();
        ModelFile upper = models().curtain(name + "_upper", tLocGaia(name + "_top"));
        ModelFile lower = models().curtain(name + "_lower", tLocGaia(name + "_bottom"));
        ModelFile upper_left = models().curtain(name + "_upper_left", tLocGaia(name + "_top_left"));
        ModelFile lower_left = models().curtain(name + "_lower_left", tLocGaia(name + "_bottom_left"));
        ModelFile upper_right = models().curtain(name + "_upper_right", tLocGaia(name + "_top_right"));
        ModelFile lower_right = models().curtain(name + "_lower_right", tLocGaia(name + "_bottom_right"));
        ModelFile upper_open = models().curtain(name + "_upper_open", tLocGaia(name + "_top_open"));
        ModelFile lower_open = models().curtain(name + "_lower_open", tLocGaia(name + "_bottom_open"));
        ModelFile upper_left_open = models().curtain(name + "_upper_left_open", tLocGaia(name + "_top_left_open"));
        ModelFile lower_left_open = models().curtain(name + "_lower_left_open", tLocGaia(name + "_bottom_left_open"));
        ModelFile upper_right_open = models().curtain(name + "_upper_right_open", tLocGaia(name + "_top_right_open"));
        ModelFile lower_right_open = models().curtain(name + "_lower_right_open", tLocGaia(name + "_bottom_right_open"));

        getVariantBuilder(block.get()).forAllStatesExcept(state -> {
            ModelFile file;
            if (state.getValue(CurtainBlock.HALF) == DoubleBlockHalf.UPPER) {
                file = switch (state.getValue(CurtainBlock.SIDE)) {
                    case SINGLE -> state.getValue(CurtainBlock.OPEN) ? upper_open : upper;
                    case LEFT -> state.getValue(CurtainBlock.OPEN) ? upper_left_open : upper_left;
                    case RIGHT -> state.getValue(CurtainBlock.OPEN) ? upper_right_open : upper_right;
                };
            } else {
                file = switch (state.getValue(CurtainBlock.SIDE)) {
                    case SINGLE -> state.getValue(CurtainBlock.OPEN) ? lower_open : lower;
                    case LEFT -> state.getValue(CurtainBlock.OPEN) ? lower_left_open : lower_left;
                    case RIGHT -> state.getValue(CurtainBlock.OPEN) ? lower_right_open : lower_right;
                };
            }

            return ConfiguredModel.builder()
                    .modelFile(file)
                    .rotationY(state.getValue(CurtainBlock.FACING) == Direction.Axis.Z ? 90 : 0)
                    .build();
        }, CurtainBlock.POWERED);
    }
}
