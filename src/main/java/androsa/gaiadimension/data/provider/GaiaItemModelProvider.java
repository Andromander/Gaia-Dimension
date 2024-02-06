package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public abstract class GaiaItemModelProvider extends ItemModelProvider {

    public GaiaItemModelProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, GaiaDimensionMod.MODID, helper);
    }

    public String blockName(DeferredBlock<? extends Block> block) {
        return block.getId().getPath();
    }

    public ItemModelBuilder blockItem(DeferredBlock<? extends Block> block) {
        return blockItem(block, blockName(block));
    }

    public ItemModelBuilder blockItem(DeferredBlock<? extends Block> block, String model) {
        return withExistingParent(blockName(block), modLoc("block/" + model));
    }

    public ItemModelBuilder blockItemTexture(DeferredBlock<? extends Block> block) {
        return blockItemTexture(block, blockName(block));
    }

    public ItemModelBuilder blockItemTexture(DeferredBlock<? extends Block> block, String texture) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + texture));
    }

    public ItemModelBuilder basicItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    public ItemModelBuilder heldItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    public ItemModelBuilder eggItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/template_spawn_egg"));
    }

    public ItemModelBuilder geodeItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/geode"));
    }
}
