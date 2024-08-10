package androsa.gaiadimension.entity.data;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum MookaitePartType implements StringRepresentable {
    NONE("none", 0, 0.25F, false),
    SCARLET("scarlet", 1, 0.5F, true),
    AUBURN("auburn", 2, 0.5F, true),
    GOLD("gold", 3, 0.5F, true),
    MAUVE("mauve", 4, 0.5F, true),
    BEIGE("beige", 5, 0.5F, true),
    IVORY("ivory", 6, 0.5F, true),
    OPALITE("opalite", 7, 1.0F, true);

    @Deprecated //idk, this is deprecated with no alternative
    private static final StringRepresentable.EnumCodec<MookaitePartType> CODEC = StringRepresentable.fromEnum(MookaitePartType::values);
    private static final IntFunction<MookaitePartType> BY_ID = ByIdMap.continuous(MookaitePartType::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, MookaitePartType> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, MookaitePartType::getId);

    private final String name;
    private final int id;
    private final float multiplier;
    private final boolean present;

    MookaitePartType(String name, int id, float multiplier, boolean present) {
        this.name = name;
        this.id = id;
        this.multiplier = multiplier;
        this.present = present;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public float getMultiplier() {
        return this.multiplier;
    }

    public boolean isPresent() {
        return this.present;
    }

    public static MookaitePartType fromName(String name) {
        return CODEC.byName(name, NONE);
    }

    public static MookaitePartType getRandom(RandomSource random) {
        return BY_ID.apply(random.nextInt(MookaitePartType.values().length - 1)); //minus 1 to ignore Opalite
    }
}
