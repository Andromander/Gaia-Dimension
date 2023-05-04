package androsa.gaiadimension.world.layer.util;

import androsa.gaiadimension.GaiaDimensionMod;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.Optional;

public class Layer {

    public final LazyArea area;

    public Layer(AreaFactory<LazyArea> area) {
        this.area = area.make();
    }

    public Holder<Biome> get(HolderGetter<Biome> registry, int x, int z) {
        int i = this.area.get(x, z);
        Optional<Holder.Reference<Biome>> biome = ServerLifecycleHooks.getCurrentServer().registryAccess().registryOrThrow(Registries.BIOME).getHolder(i);
        if (biome.isEmpty()) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw Util.pauseInIde(new IllegalStateException("Unknown biome id: " + i));
            } else {
                GaiaDimensionMod.LOGGER.warn("Unknown biome id: " + i);
                return registry.getOrThrow(Biomes.OCEAN);
            }
        } else {
            return biome.get();
        }
    }
}
