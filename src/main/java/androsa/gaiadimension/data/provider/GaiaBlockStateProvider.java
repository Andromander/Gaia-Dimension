package androsa.gaiadimension.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

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

    protected String blockName(Block block) {
        return block.getRegistryName().getPath();
    }

    public void sidedBlock(Block block, String top, String bottom, String north, String south, String east, String west) {
        simpleBlock(block, models().cube(blockName(block), tLocGaia(bottom), tLocGaia(top), tLocGaia(north), tLocGaia(south), tLocGaia(east), tLocGaia(west)));
    }

    public void basicBlockRotated(Block block) {
        simpleBlock(block, model -> ConfiguredModel.allYRotations(model, 0, false));
    }

    public void basicBlockLayered(Block block, String bottom, String top) {
        simpleBlock(block, models().basicLayered(block, tLocGaia(bottom), tLocGaia(top)));
    }

    public void logBlock(RotatedPillarBlock block, String name) {
        axisBlock(block, tLocGaia(name));
    }

    public void strippedLogBlock(RotatedPillarBlock block, String name) {
        axisBlock(block, tLocGaia("stripped_" + name));
    }

    public void strippedWoodBlock(RotatedPillarBlock block, String name) {
        ModelFile model = models().cubeColumn(blockName(block), tLocGaia("stripped_" + name + "_log_side"), tLocGaia("stripped_" + name + "_log_side"));
        axisBlock(block, model, model);
    }

    public void woodBlock(RotatedPillarBlock block, String name) {
        ModelFile model = models().cubeColumn(blockName(block), tLocGaia(name + "_side"), tLocGaia(name + "_side"));
        axisBlock(block, model, model);
    }

    public void stairsBlock(StairsBlock block, String name) {
        stairsBlock(block, tLocGaia(name));
    }

    public void stairsBlockLayered(StairsBlock block, String inner, String outer) {
        ModelFile stairs = models().stairsBasicLayer(block, tLocGaia(inner), tLocGaia(outer));
        ModelFile stairsInner = models().stairsInnerBasicLayer(block, tLocGaia(inner), tLocGaia(outer));
        ModelFile stairsOuter = models().stairsOuterBasicLayer(block, tLocGaia(inner), tLocGaia(outer));
        stairsBlock(block, stairs, stairsInner, stairsOuter);
    }

    public void slabBlock(SlabBlock block, Block doubleBlock) {
        slabBlock(block, tLocGaia(blockName(doubleBlock)), tLocGaia(blockName(doubleBlock)));
    }

    public void crossBlock(Block block) {
        crossBlock(block, models().cross(blockName(block), tLocGaia(blockName(block))));
    }

    public void crossBlockTinted(Block block) {
        crossBlock(block, models().tintedCross(blockName(block), tLocGaia(blockName(block))));
    }

    public void orientableBlockLit(Block block) {
        ModelFile off = models().orientable(blockName(block), tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front"), tLocGaia(blockName(block) + "_top"));
        ModelFile on = models().orientable(blockName(block) + "_lit", tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front_lit"), tLocGaia(blockName(block) + "_top"));
        orientableBlock(block, off, on);
    }

    public void orientableBlockBasicLit(Block block) {
        ModelFile off = models().orientable(blockName(block), tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front"), tLocGaia(blockName(block) + "_side"));
        ModelFile on = models().orientable(blockName(block) + "_lit", tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front_lit"), tLocGaia(blockName(block) + "_side"));
        orientableBlock(block, off, on);
    }

    public void grassBlock(Block block, String bottom) {
        String baseName = blockName(block);
        ModelFile model = models().grass(
                block,
                tLocGaia(baseName + "_top"),
                tLocGaia(bottom),
                tLocGaia(baseName + "_side"),
                tLocGaia(baseName + "_overlay"));
        grassBlock(block, model);
    }

    public void pottedPlantBlock(FlowerPotBlock block) {
        simpleBlock(block, models().flowerPot(block));
    }

    public void torchBlock(Block block, Block wall) {
        ModelFile torch = models().torch(blockName(block), tLocGaia(blockName(block)));
        ModelFile torchwall = models().torchWall(blockName(wall), tLocGaia(blockName(block)));
        simpleBlock(block, torch);
        getVariantBuilder(wall).forAllStates(state ->
                ConfiguredModel.builder()
                .modelFile(torchwall)
                .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 90) % 360)
                .build());
    }

    private void orientableBlock(Block block, ModelFile off, ModelFile on) {
        getVariantBuilder(block).forAllStates(state -> {
            ModelFile model = state.get(BlockStateProperties.LIT) ? on : off;

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + 180) % 360)
                    .build();
        });
    }

    private void crossBlock(Block block, ModelFile model) {
        getVariantBuilder(block).forAllStates(state ->
                ConfiguredModel.builder()
                .modelFile(model)
                .build());
    }

    private void grassBlock(Block block, ModelFile model) {
        getVariantBuilder(block).forAllStates(state -> ConfiguredModel.allYRotations(model, 0, false));
    }
}
