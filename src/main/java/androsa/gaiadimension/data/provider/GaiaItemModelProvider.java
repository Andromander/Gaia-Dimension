package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fmllegacy.RegistryObject;

public abstract class GaiaItemModelProvider extends ItemModelProvider {

    public GaiaItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, GaiaDimensionMod.MODID, helper);
    }

    public String blockName(RegistryObject<? extends Block> block) {
        return block.getId().getPath();
    }

    public ItemModelBuilder blockItem(RegistryObject<? extends Block> block) {
        return blockItem(block, blockName(block));
    }

    public ItemModelBuilder blockItem(RegistryObject<? extends Block> block, String model) {
        return withExistingParent(blockName(block), modLoc("block/" + model));
    }

    public ItemModelBuilder blockItemTexture(RegistryObject<? extends Block> block) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + blockName(block)));
    }

    public ItemModelBuilder basicItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    public ItemModelBuilder heldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    public ItemModelBuilder eggItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    public ItemModelBuilder geodeItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/geode"));
    }
}
