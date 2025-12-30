package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public class ModDataMaps {

    public static final DataMapType<Item, Integer> GLITTERING_FUEL = DataMapType.builder(
                    Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "glittering_fuel"),
                    Registries.ITEM,
                    ExtraCodecs.POSITIVE_INT)
            .synced(ExtraCodecs.POSITIVE_INT, false)
            .build();

    public static final DataMapType<Item, Integer> SHINING_FUEL = DataMapType.builder(
                    Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "shining_fuel"),
                    Registries.ITEM,
                    ExtraCodecs.POSITIVE_INT)
            .synced(ExtraCodecs.POSITIVE_INT, false)
            .build();

    public static final DataMapType<Item, Integer> NULLING_FUEL = DataMapType.builder(
                    Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, "nulling_fuel"),
                    Registries.ITEM,
                    ExtraCodecs.POSITIVE_INT)
            .synced(ExtraCodecs.POSITIVE_INT, false)
            .build();

    public static void registerDataMaps(RegisterDataMapTypesEvent event) {
        event.register(GLITTERING_FUEL);
        event.register(SHINING_FUEL);
        event.register(NULLING_FUEL);
    }
}
