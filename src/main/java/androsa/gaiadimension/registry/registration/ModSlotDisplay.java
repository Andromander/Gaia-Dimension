package androsa.gaiadimension.registry.registration;

import androsa.gaiadimension.GaiaDimensionMod;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.context.ContextMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

import java.util.Optional;
import java.util.stream.Stream;

public class ModSlotDisplay {
    public static final DeferredRegister<SlotDisplay.Type<?>> SLOT_DISPLAYS = DeferredRegister.create(Registries.SLOT_DISPLAY, GaiaDimensionMod.MODID);

    public static final DeferredHolder<SlotDisplay.Type<?>, SlotDisplay.Type<GlitterFuel>> GLITTER_FUEL = SLOT_DISPLAYS.register("glitter_fuel", () -> GlitterFuel.TYPE);
    public static final DeferredHolder<SlotDisplay.Type<?>, SlotDisplay.Type<ShineFuel>> SHINE_FUEL = SLOT_DISPLAYS.register("shine_fuel", () -> ShineFuel.TYPE);
    public static final DeferredHolder<SlotDisplay.Type<?>, SlotDisplay.Type<NullingFuel>> NULLING_FUEL = SLOT_DISPLAYS.register("nulling_fuel", () -> NullingFuel.TYPE);

    public static abstract class CustomFuel implements SlotDisplay {
        @Override
        public <T> Stream<T> resolve(ContextMap context, DisplayContentsFactory<T> factory) {
            if (factory instanceof DisplayContentsFactory.ForStacks<T> stacks) {
                return BuiltInRegistries.ITEM.getDataMap(fuelMap()).keySet()
                        .stream()
                        .map(BuiltInRegistries.ITEM::get)
                        .filter(Optional::isPresent)
                        .map(o -> stacks.forStack(o.get()));
            }
            return Stream.empty();
        }

        protected abstract DataMapType<Item, Integer> fuelMap();
    }

    public static class GlitterFuel extends CustomFuel {
        public static final GlitterFuel INSTANCE = new GlitterFuel();
        private static final MapCodec<GlitterFuel> MAP_CODEC = MapCodec.unit(INSTANCE);
        private static final StreamCodec<RegistryFriendlyByteBuf, GlitterFuel> STREAM_CODEC = StreamCodec.unit(INSTANCE);
        private static final SlotDisplay.Type<GlitterFuel> TYPE = new SlotDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

        @Override
        protected DataMapType<Item, Integer> fuelMap() {
            return ModDataMaps.GLITTERING_FUEL;
        }

        @Override
        public Type<? extends SlotDisplay> type() {
            return TYPE;
        }
    }

    public static class ShineFuel extends CustomFuel {
        public static final ShineFuel INSTANCE = new ShineFuel();
        private static final MapCodec<ShineFuel> MAP_CODEC = MapCodec.unit(INSTANCE);
        private static final StreamCodec<RegistryFriendlyByteBuf, ShineFuel> STREAM_CODEC = StreamCodec.unit(INSTANCE);
        private static final SlotDisplay.Type<ShineFuel> TYPE = new SlotDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

        @Override
        protected DataMapType<Item, Integer> fuelMap() {
            return ModDataMaps.SHINING_FUEL;
        }

        @Override
        public Type<? extends SlotDisplay> type() {
            return TYPE;
        }
    }

    public static class NullingFuel extends CustomFuel {
        public static final NullingFuel INSTANCE = new NullingFuel();
        private static final MapCodec<NullingFuel> MAP_CODEC = MapCodec.unit(INSTANCE);
        private static final StreamCodec<RegistryFriendlyByteBuf, NullingFuel> STREAM_CODEC = StreamCodec.unit(INSTANCE);
        private static final SlotDisplay.Type<NullingFuel> TYPE = new SlotDisplay.Type<>(MAP_CODEC, STREAM_CODEC);

        @Override
        protected DataMapType<Item, Integer> fuelMap() {
            return ModDataMaps.NULLING_FUEL;
        }

        @Override
        public Type<? extends SlotDisplay> type() {
            return TYPE;
        }
    }
}
