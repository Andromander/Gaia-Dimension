package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SmolderingBogBiome extends BaseGaiaBiome implements IDryBiome {

    public SmolderingBogBiome(Builder props) {
        super(props);

        GaiaBiomeFeatures.addCarverUncoated(this);
        GaiaBiomeFeatures.addBismuthLakes(this);
        GaiaBiomeFeatures.addPocketsUnderground(this);
        GaiaBiomeFeatures.addBasicOres(this);
        GaiaBiomeFeatures.addBogPatches(this);
        GaiaBiomeFeatures.addBismuthSpires(this);
        GaiaBiomeFeatures.addBismuthGeysers(this);
        GaiaBiomeFeatures.addUndergroundMushrooms(this);
        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.BISMUTH_ULETRUS, 15, 2, 3));

        //this.topBlock = GDBlocks.murky_grass.getDefaultState();
        //this.fillerBlock = GDBlocks.boggy_soil.getDefaultState();
        //flowers.clear();
        skyColor = GaiaSkyColors.BISMUTH;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos) {
        return 0x262627;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0x111112;
    }
}
