package androsa.gaiadimension.entity;

import androsa.gaiadimension.GaiaDimension;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

//TODO: Rewrite this
public class GaiaEntities {

    public static void registerEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, int id, int backgroundEggColour, int foregroundEggColour) {
        registerEntity(registryName, entityClass, id, backgroundEggColour, foregroundEggColour, 80, 3, true);
    }

    public static void registerEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, int id, int backgroundEggColour, int foregroundEggColour, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(registryName, entityClass, registryName.getResourceDomain() + "." + registryName.getResourcePath(), id, GaiaDimension.instance, trackingRange, updateFrequency, sendsVelocityUpdates, backgroundEggColour, foregroundEggColour);
    }

    public static void registerEntity(ResourceLocation registryName, Class<? extends  Entity> entityClass, int id) {
        EntityRegistry.registerModEntity(registryName, entityClass, registryName.getResourceDomain() + "." + registryName.getResourcePath(), id, GaiaDimension.instance, 80, 3, true);
    }

    public static void registerEntity(ResourceLocation registyName, Class<? extends Entity> clazz, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates) {
        EntityRegistry.registerModEntity(registyName, clazz, registyName.getResourceDomain() + "." + registyName.getResourcePath(), id, GaiaDimension.instance, trackingRange, updateFrequency, sendVelocityUpdates);
    }
}
