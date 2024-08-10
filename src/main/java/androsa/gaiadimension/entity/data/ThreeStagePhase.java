package androsa.gaiadimension.entity.data;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;

import java.util.function.IntFunction;

public enum ThreeStagePhase {
    IDLE(0),
    CHARGE(1),
    EXECUTE(2);

    private final int id;
    private static final IntFunction<ThreeStagePhase> BY_ID = ByIdMap.continuous(ThreeStagePhase::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, ThreeStagePhase> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, ThreeStagePhase::getId);

    ThreeStagePhase(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ThreeStagePhase getStage(int id) {
        return BY_ID.apply(id);
    }
}
