package androsa.gaiadimension.entity.data;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum LagrahkVariant implements StringRepresentable {

    BASE(0, "base"),
    SALTY(1, "salty"),
    STATIC(2, "static"),
    VOLCANIC(3, "volcanic");

    private static final IntFunction<LagrahkVariant> BY_ID = ByIdMap.continuous(LagrahkVariant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, LagrahkVariant> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, LagrahkVariant::getId);

    private final int id;
    private final String name;

    LagrahkVariant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public static LagrahkVariant getVariant(int id) {
        return BY_ID.apply(id);
    }
}
