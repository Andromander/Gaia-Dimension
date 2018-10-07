package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenStaticPatch;
import androsa.gaiadimension.world.gen.GDGenStaticSpike;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDStaticWasteland extends GDBiomeBase {

    private GDGenStaticSpike genStaticJunk = new GDGenStaticSpike(GDBlocks.charged_mineral, 8);
    private GDGenStaticPatch staticPatch = new GDGenStaticPatch(6);
    private short[] skyColorRGB = new short[]{40, 47, 82};

    public GDStaticWasteland(BiomeProperties props) {
        super(props);

        this.topBlock = GDBlocks.wasteland_stone.getDefaultState();
        this.fillerBlock = GDBlocks.wasteland_stone.getDefaultState();

        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDNomadicLagrahk.class, 15, 1, 2));

        biomeDecorator.treesPerChunk = -1;
        biomeDecorator.grassPerChunk = -1;
        biomeDecorator.lakesPerChunk = -1;
        biomeDecorator.fungiPerChunk = -1;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {
        super.decorate(world, rand, pos);

        int dx, dy, dz;
        int maxSpike = rand.nextInt(2);
        int staticPerChunk = 2;

        for (int i = 0; i < staticPerChunk; i++) {
            int rx = pos.getX() + rand.nextInt(16) + 8;
            int rz = pos.getZ() + rand.nextInt(16) + 8;
            staticPatch.generate(world, rand, world.getHeight(new BlockPos(rx, 0, rz)));
        }

        for (int i = 0; i < maxSpike; ++i) {
            dx = pos.getX() + rand.nextInt(16) + 8;
            dz = pos.getZ() + rand.nextInt(16) + 8;
            genStaticJunk.generate(world, rand, world.getHeight(new BlockPos(dx, 0, dz)));
        }

        for (int i = 0; i < 9; i++) {
            int Xcoord = pos.getX() + rand.nextInt(16);
            int Zcoord = pos.getZ() + rand.nextInt(16);
            int Ycoord = rand.nextInt(100);
            new WorldGenMinable(GDBlocks.static_stone.getDefaultState(), 33, input -> input == GDBlocks.wasteland_stone.getDefaultState()).generate(world, rand, new BlockPos(Xcoord, Ycoord, Zcoord));
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

    @Override
    public IBlockState getStoneReplacement() {
        return GDBlocks.wasteland_stone.getDefaultState();
    }
}
