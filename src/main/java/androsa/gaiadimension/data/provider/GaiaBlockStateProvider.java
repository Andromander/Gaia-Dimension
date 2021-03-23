package androsa.gaiadimension.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

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

    protected String blockName(RegistryObject<? extends Block> block) {
        return block.getId().getPath();
    }

    public void basicBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public void sidedBlock(RegistryObject<Block> block, String top, String bottom, String north, String south, String east, String west) {
        simpleBlock(block.get(), models().cube(blockName(block), tLocGaia(bottom), tLocGaia(top), tLocGaia(north), tLocGaia(south), tLocGaia(east), tLocGaia(west)));
    }

    public void basicBlockRotated(Supplier<Block> block) {
        simpleBlock(block.get(), model -> ConfiguredModel.allYRotations(model, 0, false));
    }

    public void basicBlockLayered(RegistryObject<Block> block, String bottom, String top) {
        simpleBlock(block.get(), models().basicLayered(block, tLocGaia(bottom), tLocGaia(top)));
    }

    public void logBlock(Supplier<RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), tLocGaia(name));
    }

    public void strippedLogBlock(Supplier<RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), tLocGaia("stripped_" + name));
    }

    public void strippedWoodBlock(RegistryObject<RotatedPillarBlock> block, String name) {
        ModelFile model = models().cubeColumn(blockName(block), tLocGaia("stripped_" + name + "_log_side"), tLocGaia("stripped_" + name + "_log_side"));
        axisBlock(block.get(), model, model);
    }

    public void woodBlock(RegistryObject<RotatedPillarBlock> block, String name) {
        ModelFile model = models().cubeColumn(blockName(block), tLocGaia(name + "_side"), tLocGaia(name + "_side"));
        axisBlock(block.get(), model, model);
    }

    public void stairsBlock(RegistryObject<StairsBlock> block, String name) {
        stairsBlock(block.get(), tLocGaia(name));
    }

    public void stairsBlockLayered(RegistryObject<StairsBlock> block, String inner, String outer) {
        ModelFile stairs = models().stairsBasicLayer(block, tLocGaia(inner), tLocGaia(outer));
        ModelFile stairsInner = models().stairsInnerBasicLayer(block, tLocGaia(inner), tLocGaia(outer));
        ModelFile stairsOuter = models().stairsOuterBasicLayer(block, tLocGaia(inner), tLocGaia(outer));
        stairsBlock(block.get(), stairs, stairsInner, stairsOuter);
    }

    public void slabBlock(RegistryObject<SlabBlock> block, RegistryObject<Block> doubleBlock) {
        slabBlock(block.get(), tLocGaia(blockName(doubleBlock)), tLocGaia(blockName(doubleBlock)));
    }

    public void crossBlock(RegistryObject<? extends Block> block) {
        crossBlock(block, models().cross(blockName(block), tLocGaia(blockName(block))));
    }

    public void crossBlockTinted(RegistryObject<Block> block) {
        crossBlock(block, models().tintedCross(blockName(block), tLocGaia(blockName(block))));
    }

    public void orientableBlockLit(RegistryObject<Block> block) {
        ModelFile off = models().orientable(blockName(block), tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front"), tLocGaia(blockName(block) + "_top"));
        ModelFile on = models().orientable(blockName(block) + "_lit", tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front_lit"), tLocGaia(blockName(block) + "_top"));
        orientableBlock(block, off, on);
    }

    public void orientableBlockBasicLit(RegistryObject<Block> block) {
        ModelFile off = models().orientable(blockName(block), tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front"), tLocGaia(blockName(block) + "_side"));
        ModelFile on = models().orientable(blockName(block) + "_lit", tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front_lit"), tLocGaia(blockName(block) + "_side"));
        orientableBlock(block, off, on);
    }

    public void grassBlock(RegistryObject<Block> block, String bottom) {
        String baseName = blockName(block);
        ModelFile model = models().grass(
                block,
                tLocGaia(baseName + "_top"),
                tLocGaia(bottom),
                tLocGaia(baseName + "_side"),
                tLocGaia(baseName + "_overlay"));
        grassBlock(block, model);
    }

    public void pottedPlantBlock(RegistryObject<FlowerPotBlock> block) {
        simpleBlock(block.get(), models().flowerPot(block));
    }

    public void torchBlock(RegistryObject<Block> block, RegistryObject<Block> wall) {
        ModelFile torch = models().torch(blockName(block), tLocGaia(blockName(block)));
        ModelFile torchwall = models().torchWall(blockName(wall), tLocGaia(blockName(block)));
        simpleBlock(block.get(), torch);
        getVariantBuilder(wall.get()).forAllStates(state ->
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

    private void orientableBlock(RegistryObject<Block> block, ModelFile off, ModelFile on) {
        getVariantBuilder(block.get()).forAllStates(state -> {
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

    private void crossBlock(Supplier<? extends Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state ->
                ConfiguredModel.builder()
                .modelFile(model)
                .build());
    }

    private void grassBlock(Supplier<Block> block, ModelFile model) {
        getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.allYRotations(model, 0, false));
    }
}
