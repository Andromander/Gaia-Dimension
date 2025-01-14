package androsa.gaiadimension.renderer;

import androsa.gaiadimension.registry.helpers.ModEntitiesRendering;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

public class BasicEntityRenderer<T extends Mob, M extends EntityModel<LivingEntityRenderState>> extends MobRenderer<T, LivingEntityRenderState, M> {

    private final String texture;

    public BasicEntityRenderer(EntityRendererProvider.Context context, M model, String texture, float shadow) {
        super(context, model, shadow);
        this.texture = texture;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState entity) {
        return ModEntitiesRendering.makeTexture(texture);
    }
}
