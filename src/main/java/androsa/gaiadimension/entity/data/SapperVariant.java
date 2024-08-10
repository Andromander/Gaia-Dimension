package androsa.gaiadimension.entity.data;

import androsa.gaiadimension.registry.values.GaiaBuiltinTables;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.IntFunction;

public enum SapperVariant implements StringRepresentable {

    COMMON(0, "pink", GaiaBuiltinTables.PINK_SAPPER_TABLE),
    CHILLED(1, "blue", GaiaBuiltinTables.BLUE_SAPPER_TABLE),
    NUTRIENT(2, "green", GaiaBuiltinTables.GREEN_SAPPER_TABLE),
    MYSTIFIED(3, "purple", GaiaBuiltinTables.PURPLE_SAPPER_TABLE);

    private static final IntFunction<SapperVariant> BY_ID = ByIdMap.continuous(SapperVariant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, SapperVariant> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, SapperVariant::getId);

    private final int id;
    private final String name;
    private final ResourceKey<LootTable> lootTable;

    SapperVariant(int id, String name, ResourceKey<LootTable> table) {
        this.id = id;
        this.name = name;
        this.lootTable = table;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public ResourceKey<LootTable> getLootTable() {
        return this.lootTable;
    }

    public static SapperVariant getVariant(int id) {
        return BY_ID.apply(id);
    }

    public static SapperVariant getRandomVariant(RandomSource random) {
        return BY_ID.apply(random.nextInt(SapperVariant.values().length));
    }
}
