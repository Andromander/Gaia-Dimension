package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public abstract class GaiaItemModelProvider extends ItemModelProvider {

    public GaiaItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, GaiaDimensionMod.MODID, helper);
    }

    public String blockName(Block block) {
        return block.getRegistryName().getPath();
    }

    public ItemModelBuilder blockItem(Block block) {
        return blockItem(block, blockName(block));
    }

    public ItemModelBuilder blockItem(Block block, String model) {
        return withExistingParent(blockName(block), modLoc("block/" + model));
    }

    public ItemModelBuilder blockItemTexture(Block block) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + blockName(block)));
    }

    public ItemModelBuilder basicItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + item.getRegistryName().getPath()));
    }

    public ItemModelBuilder eggItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(), mcLoc("item/template_spawn_egg"));
    }

    public ItemModelBuilder geodeItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/geode"));
    }
}
