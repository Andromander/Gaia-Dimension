package androsa.gaiadimension.biomes;

import androsa.gaiadimension.registry.GaiaBiomeFeatures;
import androsa.gaiadimension.registry.ModEntities;
import androsa.gaiadimension.registry.GaiaSkyColors;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class BaseGaiaBiome extends Biome {

    public GaiaSkyColors skyColor = GaiaSkyColors.GENERAL;

    public BaseGaiaBiome(SurfaceBuilder<SurfaceBuilderConfig> surface, SurfaceBuilderConfig config, Category category, float depth, float scale, float temp) {
        super(new Biome.Builder().surfaceBuilder(surface, config).precipitation(RainType.NONE).category(category).depth(depth).scale(scale).temperature(temp).downfall(0.0F).waterColor(0x6C99B1).waterFogColor(0x92BED4));

        GaiaBiomeFeatures.addPrimalMassUnderground(this);
        GaiaBiomeFeatures.addCarver(this);
        //this.addSpawn(EntityClassification.CREATURE, new SpawnListEntry(ModEntities.NOMADIC_LAGRAHK, 15, 1, 2));
        this.addSpawn(EntityClassification.WATER_CREATURE, new SpawnListEntry(ModEntities.SHALLOW_ARENTHIS, 10, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.CAVERN_TICK, 75, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.SHALURKER, 75, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.ARCHAIC_WARRIOR, 75, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.MUCKLING, 75, 4, 4));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 5, 1, 2));
        this.addSpawn(EntityClassification.MONSTER, new SpawnListEntry(ModEntities.PRIMAL_BEAST, 25, 1, 2));

        //biomeDecorator.treesPerChunk = 5;
        //biomeDecorator.grassPerChunk = 3;
        //biomeDecorator.flowersPerChunk = 2;
        //biomeDecorator.fungiPerChunk = 1;

        //this.flowers.clear();
        //this.flowers.add(new FlowerEntry(GDBlocks.thiscus.getDefaultState(), 20));
        //this.flowers.add(new FlowerEntry(GDBlocks.ouzium.getDefaultState(), 10));
    }

    @OnlyIn(Dist.CLIENT)
    public final short[] getSkyRGB() {
        return skyColor.getSkyColor();
    }

    @OnlyIn(Dist.CLIENT)
    public final short[] getFogRGB() {
        return skyColor.getFogColor();
    }

    @Override
    public float getSpawningChance() {
        return 0.12F;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getGrassColor(BlockPos pos) {
        return 0xF2A3B4;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public int getFoliageColor(BlockPos pos) {
        return 0xF2A3B4;
    }
}