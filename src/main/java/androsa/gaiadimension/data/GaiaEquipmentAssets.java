package androsa.gaiadimension.data;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.bootstrap.GaiaArmorMaterials;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.function.BiConsumer;

public class GaiaEquipmentAssets extends EquipmentAssetProvider {

    public GaiaEquipmentAssets(PackOutput output) {
        super(output);
    }

    @Override
    protected void registerModels(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        output.accept(GaiaArmorMaterials.SUGILITE_MODEL, makeBasic("sugilite"));
        output.accept(GaiaArmorMaterials.PROUSTITE_MODEL, makeBasic("proustite"));
        output.accept(GaiaArmorMaterials.ALBITE_MODEL, makeBasic("albite"));
        output.accept(GaiaArmorMaterials.CARNELIAN_MODEL, makeBasic("carnelian"));
        output.accept(GaiaArmorMaterials.DIOPSIDE_MODEL, makeBasic("diopside"));
        output.accept(GaiaArmorMaterials.GOSHENITE_MODEL, makeBasic("goshenite"));

        output.accept(GaiaArmorMaterials.MALACHITE_MODEL, makeBasic("malachite_guard"));
        output.accept(GaiaArmorMaterials.TIGER_EYE_MODEL, makeBasic("apex_predator"));
        output.accept(GaiaArmorMaterials.SPINEL_MODEL, makeBasic("spinel_princess"));
        output.accept(GaiaArmorMaterials.ZIRCON_MODEL, makeBasic("zircon_prince"));
        output.accept(GaiaArmorMaterials.CORRUPT_MODEL, makeBasic("corrupt_warrior"));
        output.accept(GaiaArmorMaterials.BIXBITE_MODEL, makeBasic("gaia_duchess"));
        output.accept(GaiaArmorMaterials.TSAVORITE_MODEL, makeBasic("gaia_baron"));
        output.accept(GaiaArmorMaterials.LARVIKITE_MODEL, makeBasic("gaia_duke"));
        output.accept(GaiaArmorMaterials.GAIA_CHAMP_MODEL, makeBasic("gaia_champion"));
    }

    public static EquipmentClientInfo makeBasic(String name) {
        return EquipmentClientInfo.builder().addHumanoidLayers(Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, name)).build();
    }
}
