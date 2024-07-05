package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.item.ConstructKitItem;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.UUID;

public class ModDataComponents {

    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(GaiaDimensionMod.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UUID>> MOOKAITE_UUID = DATA_COMPONENTS.registerComponentType("mookaite_uuid",
            builder -> builder.persistent(UUIDUtil.CODEC).networkSynchronized(UUIDUtil.STREAM_CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UUID>> OPALITE_UUID = DATA_COMPONENTS.registerComponentType("opalite_uuid",
            builder -> builder.persistent(UUIDUtil.CODEC).networkSynchronized(UUIDUtil.STREAM_CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ConstructKitItem.Part>> KIT_PART = DATA_COMPONENTS.registerComponentType("kit_part",
            builder -> builder.persistent(ConstructKitItem.Part.CODEC).networkSynchronized(ConstructKitItem.Part.STREAM_CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemContainerContents>> POUCH_CONTENTS = DATA_COMPONENTS.registerComponentType("pouch_contents",
            builder -> builder.persistent(ItemContainerContents.CODEC).networkSynchronized(ItemContainerContents.STREAM_CODEC).cacheEncoding());
}
