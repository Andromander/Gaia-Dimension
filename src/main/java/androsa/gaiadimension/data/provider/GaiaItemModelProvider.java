package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.registration.ModDataComponents;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public abstract class GaiaItemModelProvider {

    protected final ItemModelGenerators itemModels;

    public GaiaItemModelProvider(ItemModelGenerators itemModels) {
        this.itemModels = itemModels;
    }

    public void flatItem(DeferredItem<Item> item) {
        itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_ITEM);
    }

    public void heldItem(DeferredItem<Item> item) {
        itemModels.generateFlatItem(item.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    }

    public void eggItem(DeferredItem<Item> item) {
        itemModels.itemModelOutput.accept(item.get(), ItemModelUtils.plainModel(
                ModelTemplates.FLAT_ITEM.create(
                        ModelLocationUtils.getModelLocation(item.get()),
                        TextureMapping.layer0(ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "item/spawn_egg/" + item.getId().getPath())),
                        itemModels.modelOutput)));
    }

    public void geodeItem(DeferredItem<Item> item) {
        itemModels.itemModelOutput.accept(item.get(), ItemModelUtils.plainModel(
                ModelTemplates.FLAT_ITEM.create(
                        ModelLocationUtils.getModelLocation(item.get()),
                        TextureMapping.layer0(ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "item/geode")),
                        itemModels.modelOutput)));
    }

    public void constructCharm(DeferredItem<Item> item, ItemModelGenerators generator) {
        ItemModel.Unbaked base = ItemModelUtils.plainModel(this.itemModels.createFlatItemModel(item.get(), ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked mookaite = ItemModelUtils.plainModel(this.itemModels.createFlatItemModel(item.get(), "_mookaite", ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked opalite = ItemModelUtils.plainModel(this.itemModels.createFlatItemModel(item.get(), "_opalite", ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked both = ItemModelUtils.plainModel(this.itemModels.createFlatItemModel(item.get(), "_both", ModelTemplates.FLAT_ITEM));

        generator.itemModelOutput.accept(item.get(),
                ItemModelUtils.conditional(
                        ItemModelUtils.hasComponent(ModDataComponents.MOOKAITE_UUID.get()),
                        ItemModelUtils.conditional(
                                ItemModelUtils.hasComponent(ModDataComponents.OPALITE_UUID.get()),
                                both,
                                mookaite),
                        ItemModelUtils.conditional(
                                ItemModelUtils.hasComponent(ModDataComponents.OPALITE_UUID.get()),
                                opalite,
                                base)));
    }
}
