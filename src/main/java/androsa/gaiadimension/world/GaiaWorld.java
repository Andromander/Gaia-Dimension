package androsa.gaiadimension.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.IChunkGenerator;

public class GaiaWorld {
    public static int SEALEVEL = 127;
    public static int CHUNKHEIGHT = 256;
    public static int MAXHEIGHT = 256;

    public static IChunkGenerator getChunkGenerator(World world) {
        return ((WorldServer) world).getChunkProvider().chunkGenerator;
    }
}
