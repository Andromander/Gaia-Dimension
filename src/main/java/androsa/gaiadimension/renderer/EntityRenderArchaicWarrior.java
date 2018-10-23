package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.GDArchaicWarrior;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class EntityRenderArchaicWarrior extends RenderLiving<GDArchaicWarrior> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "archaicwarrior.png");

    public EntityRenderArchaicWarrior(RenderManager manager, ModelBase model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(GDArchaicWarrior par1Entity) {
        return textureLoc;
    }
}
