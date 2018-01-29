package androsa.gaiadimension.block.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum MalachiteBricksVariant implements IStringSerializable {
    NORMAL,
    CRACKED,
    CRUSTED,
    PULSING;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
