package androsa.gaiadimension.block.enums;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum SpecialGaiaLogVariant implements IStringSerializable {
        FOSSILIZED,
        CORRUPTED,
        CRUSTY,
        HEATED;

        @Override
        public String getName() {
            return name().toLowerCase(Locale.ROOT);
        }
    }
