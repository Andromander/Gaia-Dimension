package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.projectile.AgateArrow;
import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class AgateArrowRenderer<T extends AgateArrow> extends ArrowRenderer<T, ArrowRenderState> {
    public static final Identifier textureLoc = Identifier.fromNamespaceAndPath(GaiaDimensionMod.MODID, ModEntitiesRendering.TEXTURE_DIRECTORY + "projectiles/agate_arrow.png");

    public AgateArrowRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    public Identifier getTextureLocation(ArrowRenderState entity) {
        return textureLoc;
    }
}
