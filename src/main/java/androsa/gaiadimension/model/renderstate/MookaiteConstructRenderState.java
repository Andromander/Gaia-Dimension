package androsa.gaiadimension.model.renderstate;

import androsa.gaiadimension.entity.data.MookaitePartType;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

import java.util.List;

public class MookaiteConstructRenderState extends LivingEntityRenderState {
    public MookaitePartType rightHorn;
	public MookaitePartType leftHorn;
    public MookaitePartType rightEye;
	public MookaitePartType leftEye;
	public MookaitePartType rightShoulder;
	public MookaitePartType leftShoulder;
	public MookaitePartType rightArmBrace;
	public MookaitePartType leftArmBrace;
	public MookaitePartType rightLegBrace;
	public MookaitePartType leftLegBrace;
	public List<MookaitePartType> partList;
}
