package androsa.gaiadimension.entity.data;

import androsa.gaiadimension.registry.registration.ModItems;
import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.Util;
import net.minecraft.world.level.ItemLike;

import java.util.Map;
import java.util.function.IntFunction;

public enum SapperVariant implements StringRepresentable {

    COMMON(0, "pink", "common"),
    CHILLED(1, "blue", "chilled"),
    NUTRIENT(2, "green", "nutrient"),
    MYSTIFIED(3, "purple", "mystified");

    private static final IntFunction<SapperVariant> BY_ID = ByIdMap.continuous(SapperVariant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final EnumCodec<SapperVariant> CODEC = StringRepresentable.fromEnum(SapperVariant::values);
    public static final StreamCodec<ByteBuf, SapperVariant> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, SapperVariant::getId);

    private final int id;
    private final String name;
    private final String variant;

    SapperVariant(int id, String name, String variant) {
        this.id = id;
        this.name = name;
        this.variant = variant;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public String getVariant() {
        return this.variant;
    }

    public static SapperVariant getVariant(int id) {
        return BY_ID.apply(id);
    }

    public static SapperVariant getRandomVariant(RandomSource random) {
        return BY_ID.apply(random.nextInt(SapperVariant.values().length));
    }

    public interface SapperLoot {
        Map<SapperVariant, ItemLike> GEODE_BY_VARIANT = Util.make(Maps.newEnumMap(SapperVariant.class), map ->{
            map.put(SapperVariant.COMMON, ModItems.pink_geode);
            map.put(SapperVariant.CHILLED, ModItems.blue_geode);
            map.put(SapperVariant.NUTRIENT, ModItems.green_geode);
            map.put(SapperVariant.MYSTIFIED, ModItems.purple_geode);
        });
    }
}
