package androsa.gaiadimension.data.provider;

import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;

import java.util.function.Supplier;

public abstract class GaiaBlockStateProvider extends BlockStateProvider {

    public GaiaBlockStateProvider(DataGenerator generator, String modid, ExistingFileHelper helper) {
        super(generator, modid, helper);
    }

    protected ResourceLocation tLocGaia(String name) {
        return modLoc("block/" + name);
    }

    protected String blockName(Supplier<? extends Block> block) {
        return block.get().getRegistryName().getPath();
    }

    public void sidedBlock(Supplier<? extends Block> block, String top, String bottom, String north, String south, String east, String west) {
        simpleBlock(block.get(), cube(blockName(block), tLocGaia(bottom), tLocGaia(top), tLocGaia(north), tLocGaia(south), tLocGaia(east), tLocGaia(west)));
    }

    public void basicBlock(Supplier<? extends Block> block) {
        simpleBlock(block.get());
    }

    public void basicBlockRotated(Supplier<? extends Block> block) {
        simpleBlock(block.get(), model -> ConfiguredModel.allYRotations(model, 0, false));
    }

    public void basicBlockLayered(Supplier<? extends Block> block, String bottom, String top) {
        simpleBlock(block.get(), basicLayered(block.get(), tLocGaia(bottom), tLocGaia(top)));
    }

    public void logBlock(Supplier<? extends RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), tLocGaia(name));
    }

    public void strippedLogBlock(Supplier<? extends RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), tLocGaia("stripped_" + name));
    }

    public void strippedWoodBlock(Supplier<? extends RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), cubeColumn(blockName(block), tLocGaia("stripped_" + name + "_log_side"), tLocGaia("stripped_" + name + "_log_side")));
    }

    public void woodBlock(Supplier<? extends RotatedPillarBlock> block, String name) {
        axisBlock(block.get(), cubeColumn(blockName(block), tLocGaia(name + "_side"), tLocGaia(name + "_side")));
    }

    public void stairsBlock(Supplier<? extends StairsBlock> block, String name) {
        stairsBlock(block.get(), tLocGaia(name));
    }

    public void stairsBlockLayered(Supplier<? extends StairsBlock> block, String inner, String outer) {
        ModelFile stairs = stairsBasicLayer(block.get(), tLocGaia(inner), tLocGaia(outer));
        ModelFile stairsInner = stairsInnerBasicLayer(block.get(), tLocGaia(inner), tLocGaia(outer));
        ModelFile stairsOuter = stairsOuterBasicLayer(block.get(), tLocGaia(inner), tLocGaia(outer));
        stairsBlock(block.get(), stairs, stairsInner, stairsOuter);
    }

    public void slabBlock(Supplier<? extends SlabBlock> block, Supplier<? extends Block> doubleBlock) {
        slabBlock(block.get(), tLocGaia(blockName(doubleBlock)), tLocGaia(blockName(doubleBlock)));
    }

    public void crossBlock(Supplier<? extends Block> block) {
        crossBlock(block, cross(blockName(block), tLocGaia(blockName(block))));
    }

    public void crossBlockTinted(Supplier<? extends Block> block) {
        crossBlock(block, tintedCross(blockName(block), tLocGaia(blockName(block))));
    }

    public void orientableBlockLit(Supplier<? extends Block> block) {
        ModelFile off = orientable(blockName(block), tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front"), tLocGaia(blockName(block) + "_top"));
        ModelFile on = orientable(blockName(block) + "_lit", tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front_lit"), tLocGaia(blockName(block) + "_top"));
        orientableBlock(block, off, on);
    }

    public void orientableBlockBasicLit(Supplier<? extends Block> block) {
        ModelFile off = orientable(blockName(block), tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front"), tLocGaia(blockName(block) + "_side"));
        ModelFile on = orientable(blockName(block) + "_lit", tLocGaia(blockName(block) + "_side"), tLocGaia(blockName(block) + "_front_lit"), tLocGaia(blockName(block) + "_side"));
        orientableBlock(block, off, on);
    }

    public void grassBlock(Supplier<? extends Block> block, String bottom) {
        String baseName = blockName(block);
        ModelFile model = grass(
                block.get(),
                tLocGaia(baseName + "_top"),
                tLocGaia(bottom),
                tLocGaia(baseName + "_side"),
                tLocGaia(baseName + "_overlay"));
        grassBlock(block, model);
    }

    public void pottedPlantBlock(Supplier<? extends FlowerPotBlock> block) {
        simpleBlock(block.get(), flowerPot(block.get()));
    }

    public void torchBlock(Supplier<? extends Block> block, Supplier<? extends Block> wall) {
        ModelFile torch = torch(blockName(block), tLocGaia(blockName(block)));
        ModelFile torchwall = torchWall(blockName(wall), tLocGaia(blockName(block)));
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

    //== MODELS START HERE ==//

    public BlockModelBuilder grass(Block block, ResourceLocation top, ResourceLocation bottom, ResourceLocation side, ResourceLocation overlay) {
        return withExistingParent(block.getRegistryName().getPath(), modLoc("block/util/grass_block"))
                .texture("top", top)
                .texture("bottom", bottom)
                .texture("side", side)
                .texture("overlay", overlay);
    }

    public BlockModelBuilder flowerPot(FlowerPotBlock plant) {
        return withExistingParent("potted_" + plant.getRegistryName().getPath(), mcLoc("block/flower_pot_cross"))
                .texture("plant", "block/" + plant.func_220276_d().getRegistryName().getPath());
    }

    public BlockModelBuilder basicLayered(Block block, ResourceLocation bottom, ResourceLocation top) {
        return withExistingParent(block.getRegistryName().getPath(), modLoc("block/util/cube_all_2_layer"))
                .texture("all", bottom)
                .texture("all2", top);
    }

    public BlockModelBuilder stairsBasicLayer(Block block, ResourceLocation inner, ResourceLocation outer) {
        return stairsLayer(block, inner, inner, inner, outer, outer, outer);
    }

    public BlockModelBuilder stairsLayer(Block block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation bottom2, ResourceLocation top2, ResourceLocation side2) {
        return withExistingParent(block.getRegistryName().getPath(), modLoc("block/util/stairs_2_layer"))
                .texture("bottom", bottom)
                .texture("top", top)
                .texture("side", side)
                .texture("bottom2", bottom2)
                .texture("top2", top2)
                .texture("side2", side2);
    }

    public BlockModelBuilder stairsInnerBasicLayer(Block block, ResourceLocation inner, ResourceLocation outer) {
        return stairsInnerLayer(block, inner, inner, inner, outer, outer, outer);
    }

    public BlockModelBuilder stairsInnerLayer(Block block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation bottom2, ResourceLocation top2, ResourceLocation side2) {
        return withExistingParent(block.getRegistryName().getPath() + "_inner", modLoc("block/util/inner_stairs_2_layer"))
                .texture("bottom", bottom)
                .texture("top", top)
                .texture("side", side)
                .texture("bottom2", bottom2)
                .texture("top2", top2)
                .texture("side2", side2);
    }

    public BlockModelBuilder stairsOuterBasicLayer(Block block, ResourceLocation inner, ResourceLocation outer) {
        return stairsOuterLayer(block, inner, inner, inner, outer, outer, outer);
    }

    public BlockModelBuilder stairsOuterLayer(Block block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation bottom2, ResourceLocation top2, ResourceLocation side2) {
        return withExistingParent(block.getRegistryName().getPath() + "_outer", modLoc("block/util/outer_stairs_2_layer"))
                .texture("bottom", bottom)
                .texture("top", top)
                .texture("side", side)
                .texture("bottom2", bottom2)
                .texture("top2", top2)
                .texture("side2", side2);
    }

    public BlockModelBuilder tintedCross(String block, ResourceLocation texture) {
        return withExistingParent(block, mcLoc("block/tinted_cross"))
                .texture("cross", texture);
    }
}
