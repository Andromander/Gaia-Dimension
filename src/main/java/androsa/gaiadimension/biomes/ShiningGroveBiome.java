package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ShiningGroveBiome extends BaseGaiaBiome {

    public ShiningGroveBiome(Builder props) {
        super(props);

        GaiaBiomeFeatures.addCarverNormal(this);
        GaiaBiomeFeatures.addAuraLake(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addAuraTrees(this);
        GaiaBiomeFeatures.addAuraShoots(this);
        GaiaBiomeFeatures.addCrystalGrowthAura(this);
        GaiaBiomeFeatures.addBloomsNormal(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);

        //this.topBlock = GDBlocks.soft_grass.getDefaultState();
        //this.fillerBlock = GDBlocks.light_soil.getDefaultState();
        skyColor = GaiaSkyColors.AURA;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos) {
        return 0x79CEAD;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0xDDF7FF;
    }
}
