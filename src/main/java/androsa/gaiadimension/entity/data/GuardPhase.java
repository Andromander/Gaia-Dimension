package androsa.gaiadimension.entity.data;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

import java.util.function.IntFunction;

public enum GuardPhase {
    DEFENCE(0, false, true),
    ATTACK(1, true, false),
    RESIST(2, true, true);

    private static final IntFunction<GuardPhase> BY_ID = ByIdMap.continuous(GuardPhase::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, GuardPhase> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, GuardPhase::getId);

    private final int id;
    private final boolean canMove;
    private final boolean resistant;

    GuardPhase(int id, boolean move, boolean resist) {
        this.id = id;
        this.canMove = move;
        this.resistant = resist;
    }

    public int getId() {
        return this.id;
    }

    public boolean canMove() {
        return this.canMove;
    }

    public boolean isResistant() {
        return this.resistant;
    }

    public static GuardPhase getPhase(int id) {
        return BY_ID.apply(id);
    }
}
