package androsa.gaiadimension.block.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum SaplingVariant implements IStringSerializable {
    PINK_AGATE,
    BLUE_AGATE,
    GREEN_AGATE,
    PURPLE_AGATE,
    FOSSILIZED,
    CORRUPTED,
    CRUSTY,
    HEATED;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
