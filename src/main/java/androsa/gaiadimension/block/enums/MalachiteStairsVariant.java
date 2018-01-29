package androsa.gaiadimension.block.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum MalachiteStairsVariant implements IStringSerializable {
    BRICKS,
    PILLAR;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
