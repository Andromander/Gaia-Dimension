package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public abstract class GaiaBlockModelProvider extends BlockModelProvider {

    public GaiaBlockModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, GaiaDimensionMod.MODID, helper);
    }

    public BlockModelBuilder grass(RegistryObject<Block> block, ResourceLocation top, ResourceLocation bottom, ResourceLocation side, ResourceLocation overlay) {
        return withExistingParent(block.getId().getPath(), modLoc("block/util/grass_block"))
                .texture("top", top)
                .texture("bottom", bottom)
                .texture("side", side)
                .texture("overlay", overlay);
    }

    public BlockModelBuilder flowerPot(RegistryObject<FlowerPotBlock> plant) {
        return withExistingParent("potted_" + plant.getId().getPath(), mcLoc("block/flower_pot_cross"))
                .texture("plant", "block/" + plant.get().getContent().getRegistryName().getPath());
    }

    public BlockModelBuilder basicLayered(RegistryObject<Block> block, ResourceLocation bottom, ResourceLocation top) {
        return withExistingParent(block.getId().getPath(), modLoc("block/util/cube_all_2_layer"))
                .texture("all", bottom)
                .texture("all2", top);
    }

    public BlockModelBuilder stairsBasicLayer(RegistryObject<? extends Block> block, ResourceLocation inner, ResourceLocation outer) {
        return stairsLayer(block, inner, inner, inner, outer, outer, outer);
    }

    public BlockModelBuilder stairsLayer(RegistryObject<? extends Block> block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation bottom2, ResourceLocation top2, ResourceLocation side2) {
        return withExistingParent(block.getId().getPath(), modLoc("block/util/stairs_2_layer"))
                .texture("bottom", bottom)
                .texture("top", top)
                .texture("side", side)
                .texture("bottom2", bottom2)
                .texture("top2", top2)
                .texture("side2", side2);
    }

    public BlockModelBuilder stairsInnerBasicLayer(RegistryObject<? extends Block> block, ResourceLocation inner, ResourceLocation outer) {
        return stairsInnerLayer(block, inner, inner, inner, outer, outer, outer);
    }

    public BlockModelBuilder stairsInnerLayer(RegistryObject<? extends Block> block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation bottom2, ResourceLocation top2, ResourceLocation side2) {
        return withExistingParent(block.getId().getPath() + "_inner", modLoc("block/util/inner_stairs_2_layer"))
                .texture("bottom", bottom)
                .texture("top", top)
                .texture("side", side)
                .texture("bottom2", bottom2)
                .texture("top2", top2)
                .texture("side2", side2);
    }

    public BlockModelBuilder stairsOuterBasicLayer(RegistryObject<? extends Block> block, ResourceLocation inner, ResourceLocation outer) {
        return stairsOuterLayer(block, inner, inner, inner, outer, outer, outer);
    }

    public BlockModelBuilder stairsOuterLayer(RegistryObject<? extends Block> block, ResourceLocation bottom, ResourceLocation top, ResourceLocation side, ResourceLocation bottom2, ResourceLocation top2, ResourceLocation side2) {
        return withExistingParent(block.getId().getPath() + "_outer", modLoc("block/util/outer_stairs_2_layer"))
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
