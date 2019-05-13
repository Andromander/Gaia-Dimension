package androsa.gaiadimension.biomes;

import androsa.gaiadimension.entity.GDBismuthUletrus;
import androsa.gaiadimension.registry.EnumSkyColors;
import androsa.gaiadimension.registry.GDBlocks;
import androsa.gaiadimension.world.gen.GDGenBismuthSpire;
import androsa.gaiadimension.world.gen.GDGenBogPatch;
import androsa.gaiadimension.world.gen.GDGenGeyser;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class GDSmolderingBog extends GDBiomeBase implements IDangerousBiome {

    private GDGenBismuthSpire genBismuthSpire = new GDGenBismuthSpire(7);
    private GDGenBogPatch bogPatch = new GDGenBogPatch(4);
    private GDGenGeyser geyser = new GDGenGeyser();

    public GDSmolderingBog(BiomeProperties props) {
        super(props);

        this.topBlock = GDBlocks.murky_grass.getDefaultState();
        this.fillerBlock = GDBlocks.boggy_soil.getDefaultState();

        spawnableCreatureList.add(new SpawnListEntry(GDBismuthUletrus.class, 15, 2, 3));

        skyColor = EnumSkyColors.BISMUTH;
        biomeDecorator.treesPerChunk = -1;
        biomeDecorator.grassPerChunk = -1;
        biomeDecorator.lakesPerChunk = -1;
        biomeDecorator.fungiPerChunk = -1;
        biomeDecorator.flowersPerChunk = -1;
        biomeDecorator.bismuthBogChance = 0.4F;
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {
        super.decorate(world, rand, pos);

        int dx, dz;
        for (int i = 0; i < 1; i++) {
            int rx = pos.getX() + rand.nextInt(16) + 8;
            int rz = pos.getZ() + rand.nextInt(16) + 8;
            bogPatch.generate(world, rand, world.getHeight(new BlockPos(rx, 0, rz)));
        }

        for (int i = 0; i < 2; ++i) {
            if (rand.nextInt(4) == 0) {
                dx = pos.getX() + rand.nextInt(16) + 8;
                dz = pos.getZ() + rand.nextInt(16) + 8;
                genBismuthSpire.generate(world, rand, world.getHeight(new BlockPos(dx, 0, dz)));
            }
        }

        for (int i = 0; i < 2; i++) {
            dx = pos.getX() + rand.nextInt(16) + 8;
            dz = pos.getZ() + rand.nextInt(16) + 8;
            geyser.generate(world, rand, world.getHeight(new BlockPos(dx, 0, dz)));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getGrassColorAtPos(BlockPos pos) {
        return 0x262627;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getFoliageColorAtPos(BlockPos pos) {
        return 0x111112;
    }
}
