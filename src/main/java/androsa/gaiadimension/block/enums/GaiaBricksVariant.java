package androsa.gaiadimension.block.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum GaiaBricksVariant implements IStringSerializable {
    NORMAL,
    CRACKED,
    MOSSY;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
