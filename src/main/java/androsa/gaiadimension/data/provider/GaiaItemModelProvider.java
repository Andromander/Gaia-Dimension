package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
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

    public void eggItem(DeferredItem<Item> item, int primary, int secondary) {
        itemModels.generateSpawnEgg(item.get(), primary, secondary);
    }

    public void geodeItem(DeferredItem<Item> item) {
        itemModels.itemModelOutput.accept(item.get(), ItemModelUtils.plainModel(
                ModelTemplates.FLAT_ITEM.create(
                        ModelLocationUtils.getModelLocation(item.get()),
                        TextureMapping.layer0(ResourceLocation.fromNamespaceAndPath(GaiaDimensionMod.MODID, "item/geode")),
                        itemModels.modelOutput)));
    }
}
