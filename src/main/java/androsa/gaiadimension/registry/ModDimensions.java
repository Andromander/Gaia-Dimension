package androsa.gaiadimension.registry;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.client.GaiaSkyRender;
import androsa.gaiadimension.world.GaiaChunkGenerator;
import androsa.gaiadimension.world.layer.GaiaBiomeProvider;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.client.ISkyRenderHandler;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

public class ModDimensions {

    public static final PointOfInterestType GAIA_PORTAL = RegistryHelper.registerPOI("gaia_portal", PointOfInterestType.getAllStates(ModBlocks.gaia_portal), 0, 1);
    public static final RegistryKey<World> gaia_world = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final RegistryKey<DimensionType> gaia_dimension = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"));
    public static final DimensionRenderInfo gaia = new DimensionRenderInfo(255.0F, true, DimensionRenderInfo.FogType.NORMAL, false, false) {
        @Override
        public Vector3d func_230494_a_(Vector3d vector3d, float v) {
            return vector3d;
        }

        @Override
        public boolean func_230493_a_(int i, int i1) {
            return false;
        }

        @Nullable
        @Override
        public ISkyRenderHandler getSkyRenderHandler() {
            return new GaiaSkyRender();
        }
    };

    public static void initDimension() {
        DimensionRenderInfo.field_239208_a_.put(new ResourceLocation(GaiaDimensionMod.MODID, "gaia"), gaia);
        Registry.register(Registry.BIOME_PROVIDER_CODEC, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_dimension"), GaiaBiomeProvider.CODEC);
        Registry.register(Registry.CHUNK_GENERATOR_CODEC, new ResourceLocation(GaiaDimensionMod.MODID, "gaia_gen"), GaiaChunkGenerator.CODEC);
    }
}
