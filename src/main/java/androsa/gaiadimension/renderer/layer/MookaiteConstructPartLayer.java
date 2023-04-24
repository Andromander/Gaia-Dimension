package androsa.gaiadimension.renderer.layer;

import androsa.gaiadimension.GaiaDimensionMod;
import androsa.gaiadimension.entity.MookaiteConstructEntity;
import androsa.gaiadimension.model.MookaiteConstructModel;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class MookaiteConstructPartLayer<T extends MookaiteConstructEntity, M extends MookaiteConstructModel<T>> extends RenderLayer<T, M> {

    private static final String dir = GaiaDimensionMod.MODEL_DIR + "mookaite_construct/";
    public static final Map<EntityDataAccessor<Integer>, String> DIR_MAP = Util.make(Maps.newHashMap(), (map) -> {
        map.put(MookaiteConstructEntity.LEFT_HORN_TYPE, "left_horn");
        map.put(MookaiteConstructEntity.RIGHT_HORN_TYPE, "right_horn");
        map.put(MookaiteConstructEntity.LEFT_EYE_TYPE, "left_eye");
        map.put(MookaiteConstructEntity.RIGHT_EYE_TYPE, "right_eye");
        map.put(MookaiteConstructEntity.LEFT_SHOULDER_TYPE, "left_shoulder");
        map.put(MookaiteConstructEntity.RIGHT_SHOULDER_TYPE, "right_shoulder");
        map.put(MookaiteConstructEntity.LEFT_ARM_BRACE_TYPE, "left_arm_brace");
        map.put(MookaiteConstructEntity.RIGHT_ARM_BRACE_TYPE, "right_arm_brace");
        map.put(MookaiteConstructEntity.LEFT_LEG_BRACE_TYPE, "left_leg_brace");
        map.put(MookaiteConstructEntity.RIGHT_LEG_BRACE_TYPE, "right_leg_brace");
    });

    public MookaiteConstructPartLayer(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        for (EntityDataAccessor<Integer> part : DIR_MAP.keySet()) {
            if (entity.isPresent(part)) {
                String color = MookaiteConstructEntity.INT_TO_COLOR.getOrDefault(entity.getPart(part), MookaiteConstructEntity.INT_TO_COLOR.get(1));
                ResourceLocation location = new ResourceLocation(dir + DIR_MAP.get(part) + "/" + color + ".png");
                if (location != null && !entity.isInvisible()) {
                    VertexConsumer vertex = buffer.getBuffer(RenderType.entityCutout(location));
                    this.getParentModel().renderToBuffer(stack, vertex, light, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
                }
            }
        }
    }
}
