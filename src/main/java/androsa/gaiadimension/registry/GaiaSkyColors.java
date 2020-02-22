package androsa.gaiadimension.registry;

public enum GaiaSkyColors {
    GENERAL(198, 157, 88, 234, 178, 224),
    BLUE_AGATE(149, 197, 231, 234, 178, 224),
    GREEN_AGATE(128, 191, 158, 234, 178, 224),
    PURPLE_AGATE(171, 109, 241, 234, 178, 224),
    MUTANT_AGATE(241, 154, 193, 234, 178, 224),
    SALT_DUNES(230, 193, 110, 187, 211, 255),
    STATIC_WASTELAND(40, 47, 82, 86, 213, 170),
    VOLCANICLAND(75, 30, 25, 245, 119, 112),
    GOLDSTONE(34, 34, 34, 188, 122, 44),
    BISMUTH(50, 43, 51, 126, 105, 182),
    AURA(228, 243, 211, 255, 206, 73);

    private final short skyRed;
    private final short skyGreen;
    private final short skyBlue;
    private final short fogRed;
    private final short fogGreen;
    private final short fogBlue;

    GaiaSkyColors(int skyR, int skyG, int skyB, int fogR, int fogG, int fogB) {
        skyRed = (short) skyR;
        skyGreen = (short) skyG;
        skyBlue = (short) skyB;
        fogRed = (short) fogR;
        fogGreen = (short) fogG;
        fogBlue = (short) fogB;
    }

    public final int getSkyColor() {
        return (skyRed << 16) | (skyGreen << 8) | skyBlue;
    }

    public final short[] getFogColor() {
        return new short[] { fogRed, fogGreen, fogBlue };
    }
}
