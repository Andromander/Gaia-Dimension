package androsa.gaiadimension.world.layer;

import androsa.gaiadimension.biomes.GDBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerGDRiverMix extends GenLayer {

    private GenLayer biomeLayer;
    private GenLayer riverLayer;

    public GenLayerGDRiverMix(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer) {
        super(par1);
        this.biomeLayer = par3GenLayer;
        this.riverLayer = par4GenLayer;
    }

    @Override
    public void initWorldGenSeed(long par1) {
        this.biomeLayer.initWorldGenSeed(par1);
        this.riverLayer.initWorldGenSeed(par1);
        super.initWorldGenSeed(par1);
    }

    @Override
    public int[] getInts(int par1, int par2, int par3, int par4) {
        int[] biomeInputs = this.biomeLayer.getInts(par1, par2, par3, par4);
        int[] riverInputs = this.riverLayer.getInts(par1, par2, par3, par4);
        int[] outputs = IntCache.getIntCache(par3 * par4);

        for (int i = 0; i < par3 * par4; ++i) {
            if (riverInputs[i] == Biome.getIdForBiome(GDBiomes.mineralRiver)) {
                outputs[i] = riverInputs[i] & 255;
            } else {
                outputs[i] = biomeInputs[i];
            }
        }

        return outputs;
    }
}
