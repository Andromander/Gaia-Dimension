package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.EnumSkyColors;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenAuraShoots;
import androsa.gaiadimension.world.gen.GDGenAuraTree;
import androsa.gaiadimension.world.gen.GDGenCrystalPlants;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDShiningGrove extends GDBiomeBase {

    private GDGenAuraShoots auraShoots = new GDGenAuraShoots();
    private WorldGenAbstractTree auraTree = new GDGenAuraTree(false);

    public GDShiningGrove(BiomeProperties props) {
        super(props);

        this.topBlock = GDBlocks.soft_grass.getDefaultState();
        this.fillerBlock = GDBlocks.light_soil.getDefaultState();

        skyColor = EnumSkyColors.AURA;
        biomeDecorator.treesPerChunk = 1;
        biomeDecorator.grassPerChunk = 2;
        biomeDecorator.lakesPerChunk = -1;
        biomeDecorator.fungiPerChunk = -1;
        biomeDecorator.flowersPerChunk = -1;
        biomeDecorator.auraPondChance = 0.3F;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {
        super.decorate(world, rand, pos);

        int dx, dz;
        for (int i = 0; i < 1; i++) {
            dx = pos.getX() + rand.nextInt(16) + 8;
            dz = pos.getZ() + rand.nextInt(16) + 8;
            auraShoots.generate(world, rand, world.getHeight(new BlockPos(dx, 0, dz)));
        }
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rand) {
        return new GDGenCrystalPlants(GDBlocks.crystal_growth_aura);
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
        return auraTree;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x79CEAD;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0xDDF7FF;
    }
}
