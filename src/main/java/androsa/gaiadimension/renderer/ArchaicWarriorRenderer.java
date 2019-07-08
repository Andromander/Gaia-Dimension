package androsa.gaiadimension.renderer;

import androsa.gaiadimension.GaiaDimension;
import androsa.gaiadimension.entity.ArchaicWarriorEntity;
import androsa.gaiadimension.model.ArchaicWarriorModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArchaicWarriorRenderer extends MobRenderer<ArchaicWarriorEntity, ArchaicWarriorModel> {
    private static final ResourceLocation textureLoc = new ResourceLocation(GaiaDimension.MODEL_DIR + "archaicwarrior.png");

    public ArchaicWarriorRenderer(EntityRendererManager manager, ArchaicWarriorModel model, float shadowSize) {
        super(manager, model, shadowSize);
    }

    @Override
    protected ResourceLocation getEntityTexture(ArchaicWarriorEntity entity) {
        return textureLoc;
    }
}
