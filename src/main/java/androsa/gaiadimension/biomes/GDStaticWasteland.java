package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenStaticSpike;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDStaticWasteland extends GDBiomeBase {

    private GDGenStaticSpike genStaticJunk = new GDGenStaticSpike(GDBlocks.charged_mineral, 8);
    private short[] skyColorRGB = new short[]{40, 47, 82};

    public GDStaticWasteland(BiomeProperties props) {
        super(props);

        this.topBlock = GDBlocks.wasteland_stone.getDefaultState();
        this.fillerBlock = GDBlocks.wasteland_stone.getDefaultState();

        this.decorator.treesPerChunk = -1;
        this.decorator.grassPerChunk = -1;

        getGDBiomeDecorator().lakesPerChunk = -1;
        getGDBiomeDecorator().staticPerChunk = 2;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {
        super.decorate(world, rand, pos);

        int dx, dy, dz;
        int maxBoulder = rand.nextInt(2);
        for (int i = 0; i < maxBoulder; ++i) {
            dx = pos.getX() + rand.nextInt(16) + 8;
            dz = pos.getZ() + rand.nextInt(16) + 8;
            genStaticJunk.generate(world, rand, world.getHeight(new BlockPos(dx, 0, dz)));
        }

        for (int i = 0; i < 9; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.wasteland_stone.getDefaultState(), 33, input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }

        for (int i = 0; i < 9; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.static_stone.getDefaultState(), 33, input -> input == GDBlocks.gaia_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
    }

    @SideOnly(Side.CLIENT)
    public final short[] getSkyRGB() {
        return skyColorRGB;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x2B4D96;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0x2B4D96;
    }
}
