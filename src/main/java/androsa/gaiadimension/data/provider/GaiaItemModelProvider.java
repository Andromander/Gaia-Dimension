package androsa.gaiadimension.data.provider;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.client.properties.Behavior;
import androsa.gaiadimension.client.properties.Element;
import androsa.gaiadimension.client.properties.Stat;
import androsa.gaiadimension.item.tools.GaiaStaffItem;
import androsa.gaiadimension.registry.registration.ModDataComponents;
import com.google.common.collect.Lists;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;
import java.util.Optional;

public abstract class GaiaItemModelProvider {

    protected final ItemModelGenerators itemModels;

    public static final ModelTemplate THREE_LAYERED_TOOL = new ModelTemplate(Optional.of(ModelLocationUtils.decorateItemModelLocation("handheld")), Optional.empty(), TextureSlot.LAYER0, TextureSlot.LAYER1, TextureSlot.LAYER2);

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

    public void magicStaff(DeferredItem<Item> item, ItemModelGenerators generator) {
        ResourceLocation base = ModelLocationUtils.getModelLocation(item.get());

        generator.itemModelOutput.accept(item.get(), elementArray(generator, base));
    }

    private ItemModel.Unbaked elementArray(ItemModelGenerators generator, ResourceLocation base) {
        List<SelectItemModel.SwitchCase<GaiaStaffItem.Element>> cases = Lists.newArrayList();

        for (GaiaStaffItem.Element element : GaiaStaffItem.Element.values()) {
            cases.add(ItemModelUtils.when(element, behaviorArray(generator, base, base.withSuffix("_" + element.getSerializedName()), base.withSuffix("/core/" + element.getSerializedName()))));
        }
        return ItemModelUtils.select(
                new Element(),
                cases);
    }

    private ItemModel.Unbaked behaviorArray(ItemModelGenerators generator, ResourceLocation base, ResourceLocation combo, ResourceLocation layer2) {
        List<SelectItemModel.SwitchCase<GaiaStaffItem.Behavior>> cases = Lists.newArrayList();

        for (GaiaStaffItem.Behavior behavior : GaiaStaffItem.Behavior.values()) {
            cases.add(ItemModelUtils.when(behavior, statArray(generator, base, combo.withSuffix("_" + behavior.getSerializedName()), base.withSuffix("/head/" + behavior.getSerializedName()), layer2)));
        }

        return ItemModelUtils.select(
                new Behavior(),
                cases);
    }

    private ItemModel.Unbaked statArray(ItemModelGenerators generator, ResourceLocation base, ResourceLocation combo, ResourceLocation layer1, ResourceLocation layer2) {
        List<SelectItemModel.SwitchCase<GaiaStaffItem.Stat>> cases = Lists.newArrayList();

        for (GaiaStaffItem.Stat stat : GaiaStaffItem.Stat.values()) {
            cases.add(ItemModelUtils.when(stat, makeStaffModel(stat.getSerializedName(), base, combo, layer1, layer2, generator)));
        }

        return ItemModelUtils.select(
                new Stat(),
                cases);
    }

    private ItemModel.Unbaked makeStaffModel(String stat, ResourceLocation base, ResourceLocation combo, ResourceLocation layer1, ResourceLocation layer2, ItemModelGenerators generator) {
        return ItemModelUtils.plainModel(THREE_LAYERED_TOOL.create(combo.withSuffix("_" + stat), TextureMapping.layered(base.withSuffix("/rod/" + stat), layer1, layer2), generator.modelOutput));
    }
}
