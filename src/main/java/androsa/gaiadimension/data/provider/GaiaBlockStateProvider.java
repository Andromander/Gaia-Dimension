package androsa.gaiadimension.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;

import java.util.function.Supplier;

public abstract class GaiaBlockStateProvider extends BlockStateProvider {

    private final GaiaBlockModelProvider blockModels;

    public GaiaBlockStateProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);

        blockModels = new GaiaBlockModelProvider(generator, helper) {
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

    protected String blockName(Supplier<? extends Block> block) {
        return block.get().getRegistryName().getPath();
    }

    public void sidedBlock(Supplier<? extends Block> block, String top, String bottom, String north, String south, String east, String west) {
        simpleBlock(block.get(), models().cube(blockName(block), tLocGaia(bottom), tLocGaia(top), tLocGaia(north), tLocGaia(south), tLocGaia(east), tLocGaia(west)));
    }

    public void basicBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public void basicBlockRotated(Supplier<? extends Block> block) {
        simpleBlock(block.get(), model -> ConfiguredModel.allYRotations(model, 0, false));
    }

    public void basicBlockLayered(Supplier<? extends Block> block, String bottom, String top) {
        simpleBlock(block.get(), models().basicLayered(block.get(), tLocGaia(bottom), tLocGaia(top)));
    }

    public void logBlock(Supplier<? extends RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), tLocGaia(name));
    }

    public void strippedLogBlock(Supplier<? extends RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), tLocGaia("stripped_" + name));
    }

    public void strippedWoodBlock(Supplier<? extends RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), models().cubeColumn(blockName(block), tLocGaia("stripped_" + name + "_log_side"), tLocGaia("stripped_" + name + "_log_side")));
    }

    public void woodBlock(Supplier<? extends RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), models().cubeColumn(blockName(block), tLocGaia(name + "_side"), tLocGaia(name + "_side")));
    }

    public void stairsBlock(Supplier<? extends StairsBlock> block, String name) {
        stairsBlock(block.get(), tLocGaia(name));
    }

    public void stairsBlockLayered(Supplier<? extends StairsBlock> block, String inner, String outer) {
        ModelFile stairs = models().stairsBasicLayer(block.get(), tLocGaia(inner), tLocGaia(outer));
        ModelFile stairsInner = models().stairsInnerBasicLayer(block.get(), tLocGaia(inner), tLocGaia(outer));
        ModelFile stairsOuter = models().stairsOuterBasicLayer(block.get(), tLocGaia(inner), tLocGaia(outer));
        stairsBlock(block.get(), stairs, stairsInner, stairsOuter);
    }

    public void slabBlock(Supplier<? extends SlabBlock> block, Supplier<? extends Block> doubleBlock) {
        slabBlock(block.get(), tLocGaia(blockName(doubleBlock)), tLocGaia(blockName(doubleBlock)));
    }

    public void crossBlock(Supplier<? extends Block> block) {
        crossBlock(block, models().cross(blockName(block), tLocGaia(blockName(block))));
    }

    public void crossBlockTinted(Supplier<? extends Block> block) {
        crossBlock(block, models().tintedCross(blockName(block), tLocGaia(blockName(block))));
    }

    public void orientableBlockLit(Supplier<? extends Block> block) {
        ModelFile off = models().orientable(blockName(block), tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front"), tLocGaia(blockName(block) + "_top"));
        ModelFile on = models().orientable(blockName(block) + "_lit", tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front_lit"), tLocGaia(blockName(block) + "_top"));
        orientableBlock(block, off, on);
    }

    public void orientableBlockBasicLit(Supplier<? extends Block> block) {
        ModelFile off = models().orientable(blockName(block), tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front"), tLocGaia(blockName(block) + "_side"));
        ModelFile on = models().orientable(blockName(block) + "_lit", tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front_lit"), tLocGaia(blockName(block) + "_side"));
        orientableBlock(block, off, on);
    }

    public void grassBlock(Supplier<? extends Block> block, String bottom) {
        String baseName = blockName(block);
        ModelFile model = models().grass(
                block.get(),
                tLocGaia(baseName + "_top"),
                tLocGaia(bottom),
                tLocGaia(baseName + "_side"),
                tLocGaia(baseName + "_overlay"));
        grassBlock(block, model);
    }

    public void pottedPlantBlock(Supplier<? extends FlowerPotBlock> block) {
        simpleBlock(block.get(), models().flowerPot(block.get()));
    }

    public void torchBlock(Supplier<? extends Block> block, Supplier<? extends Block> wall) {
        ModelFile torch = models().torch(blockName(block), tLocGaia(blockName(block)));
        ModelFile torchwall = models().torchWall(blockName(wall), tLocGaia(blockName(block)));
        simpleBlock(block.get(), torch);
        getVariantBuilder(wall.get()).forAllStates(state ->
                ConfiguredModel.builder()
                .modelFile(torchwall)
                .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 90) % 360)
                .build());
    }

    private void orientableBlock(Supplier<? extends Block> block, ModelFile off, ModelFile on) {
        getVariantBuilder(block.get()).forAllStates(state -> {
            ModelFile model = state.get(BlockStateProperties.LIT) ? on : off;

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 180) % 360)
                    .build();
        });
    }

    private void crossBlock(Supplier<? extends Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state ->
                ConfiguredModel.builder()
                .modelFile(model)
                .build());
    }

    private void grassBlock(Supplier<? extends Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.allYRotations(model, 0, false));
    }
}
