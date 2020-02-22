package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class BaseGaiaBiome extends Biome {

    public GaiaSkyColors skyColor = GaiaSkyColors.GENERAL;

    public BaseGaiaBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(new Biome.Builder()
                .surfaceBuilder(surface, config)
                .precipitation(RainType.NONE)
                .category(category)
                .depth(depth)
                .scale(scale)
                .temperature(temp)
                .downfall(0.0F)
                .waterColor(0x6C99B1)
                .waterFogColor(0x92BED4));
    }

    public void addFeatures() {
        GaiaBiomeFeatures.addPrimalMassUnderground(this);
        GaiaBiomeFeatures.addCarver(this);

        this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.NOMADIC_LAGRAHK.get(), 15, 1, 2));
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(ModEntities.SHALLOW_ARENTHIS.get(), 10, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.CAVERN_TICK.get(), 65, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.SHALURKER.get(), 65, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.ARCHAIC_WARRIOR.get(), 65, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.MUCKLING.get(), 65, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 5, 1, 2));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.PRIMAL_BEAST.get(), 15, 1, 2));
    }

    @Override
    public int getSkyColor() {
        return skyColor.getSkyColor();
    }

    @OnlyIn(Dist.CLIENT)
    public final short[] getFogRGB() {
        return skyColor.getFogColor();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColorAt(double p_225528_1_, double p_225528_3_) {
        return 0xF2A3B4;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor() {
        return 0xF2A3B4;
    }
}