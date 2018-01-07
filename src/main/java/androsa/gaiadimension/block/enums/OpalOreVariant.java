package androsa.gaiadimension.block.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum OpalOreVariant implements IStringSerializable {
    RED,
    BLUE,
    GREEN,
    WHITE;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}

