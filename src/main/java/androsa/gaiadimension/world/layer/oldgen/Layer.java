package androsa.gaiadimension.world.layer.oldgen;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

public class Layer {

    public final LazyArea area;

    public Layer(AreaFactory<LazyArea> area) {
        this.area = area.make();
    }

    public Holder<Biome> get(Registry<Biome> registry, int x, int z) {
        int i = this.area.get(x, z);
        Biome biome = registry.byId(i);
        if (biome == null) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw Util.pauseInIde(new IllegalStateException("Unknown biome id: " + i));
            } else {
                GaiaDimensionMod.LOGGER.warn("Unknown biome id: ", i);
                return Holder.direct(registry.get(Biomes.OCEAN.getRegistryName()));
            }
        } else {
            return Holder.direct(biome);
        }
    }
}
