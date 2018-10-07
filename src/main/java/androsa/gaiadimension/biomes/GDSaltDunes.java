package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GDBlocks;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GDSaltDunes extends GDBiomeBase {

    private short[] skyColorRGB = new short[] { 230, 193, 110 };

    public GDSaltDunes(BiomeProperties props) {
        super(props);

        topBlock = GDBlocks.salt.getDefaultState();
        fillerBlock = GDBlocks.salt.getDefaultState();

        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDNomadicLagrahk.class, 15, 1, 2));
        spawnableCreatureList.add(new SpawnListEntry(androsa.gaiadimension.entity.GDSaltion.class, 15, 1, 3));

        biomeDecorator.treesPerChunk = -1;
        biomeDecorator.grassPerChunk = -1;
        biomeDecorator.lakesPerChunk = -1;
        biomeDecorator.fungiPerChunk = -1;
    }

    @SideOnly(Side.CLIENT)
    public final short[] getSkyRGB() {
        return skyColorRGB;
    }
}
