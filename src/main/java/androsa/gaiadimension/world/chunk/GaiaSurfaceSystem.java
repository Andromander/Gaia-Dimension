package androsa.gaiadimension.world.chunk;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.registry.registration.ModBlocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.SurfaceSystem;

import java.util.Arrays;

//This only exists to get the clay bands
public class GaiaSurfaceSystem extends SurfaceSystem {

    public static final BlockState SCARLET_MOOKAITE = ModBlocks.scarlet_mookaite.get().defaultBlockState();
    public static final BlockState AUBURN_MOOKAITE = ModBlocks.auburn_mookaite.get().defaultBlockState();
    public static final BlockState GOLD_MOOKAITE = ModBlocks.gold_mookaite.get().defaultBlockState();
    public static final BlockState IVORY_MOOKAITE = ModBlocks.ivory_mookaite.get().defaultBlockState();
    public static final BlockState MAUVE_MOOKAITE = ModBlocks.mauve_mookaite.get().defaultBlockState();
    public static final BlockState BEIGE_MOOKAITE = ModBlocks.beige_mookaite.get().defaultBlockState();
    private final BlockState[] mookaiteBands;

    public GaiaSurfaceSystem(RandomState random, BlockState defaultstone, int sealevel, PositionalRandomFactory posrandom) {
        super(random, defaultstone, sealevel, posrandom);

        this.mookaiteBands = generateBands(posrandom.fromHashOf(new ResourceLocation(GaiaDimensionMod.MODID, "mookaite_bands")));
    }

    @Override
    protected BlockState getBand(int x, int y, int z) {
        int i = (int)Math.round(this.clayBandsOffsetNoise.getValue(x, 0.0D, z) * 4.0D);
        return this.mookaiteBands[(y + i + this.mookaiteBands.length) % this.mookaiteBands.length];
    }

    private static BlockState[] generateBands(RandomSource random) {
        BlockState[] ablockstate = new BlockState[192];
        Arrays.fill(ablockstate, AUBURN_MOOKAITE);

        for(int k = 0; k < ablockstate.length; ++k) {
            k += random.nextInt(5) + 1;
            if (k < ablockstate.length) {
                ablockstate[k] = BEIGE_MOOKAITE;
            }
        }
        int l = random.nextIntBetweenInclusive(9, 15);
        int i = 0;

        for(int j = 0; i < l && j < ablockstate.length; j += random.nextInt(16) + 4) {
            ablockstate[j] = IVORY_MOOKAITE;
            if (j - 1 > 0 && random.nextBoolean()) {
                ablockstate[j - 1] = BEIGE_MOOKAITE;
            }

            if (j + 1 < ablockstate.length && random.nextBoolean()) {
                ablockstate[j + 1] = BEIGE_MOOKAITE;
            }

            ++i;
        }

        makeBands(random, ablockstate, 1, MAUVE_MOOKAITE);
        makeBands(random, ablockstate, 2, GOLD_MOOKAITE);
        makeBands(random, ablockstate, 1, IVORY_MOOKAITE);

        return ablockstate;
    }

    private static void makeBands(RandomSource random, BlockState[] states, int offset, BlockState state) {
        int rand = random.nextIntBetweenInclusive(6, 15);

        for(int j = 0; j < rand; ++j) {
            int k = offset + random.nextInt(3);
            int randstate = random.nextInt(states.length);

            for(int i1 = 0; randstate + i1 < states.length && i1 < k; ++i1) {
                states[randstate + i1] = state;
            }
        }

    }
}
