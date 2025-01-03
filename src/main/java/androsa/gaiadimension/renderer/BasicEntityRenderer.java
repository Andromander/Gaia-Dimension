package androsa.gaiadimension.renderer;

import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class BasicEntityRenderer<T extends Mob, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends MobRenderer<T, S, M> {

    private final S renderState;
    private final String texture;

    public BasicEntityRenderer(EntityRendererProvider.Context context, M model, String texture, float shadow) {
        this(context, model, (S) new LivingEntityRenderState(), texture, shadow);
    }

    public BasicEntityRenderer(EntityRendererProvider.Context context, M model, S state, String texture, float shadow) {
        super(context, model, shadow);
        this.renderState = state;
        this.texture = texture;
    }

    @Override
    public S createRenderState() {
        return renderState;
    }

    @Override
    public ResourceLocation getTextureLocation(S entity) {
        return ModEntitiesRendering.makeTexture(texture);
    }
}
