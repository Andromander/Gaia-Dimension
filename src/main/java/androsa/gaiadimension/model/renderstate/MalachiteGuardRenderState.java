package androsa.gaiadimension.model.renderstate;

import androsa.gaiadimension.entity.data.GuardPhase;
import androsa.gaiadimension.entity.data.ThreeStagePhase;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class MalachiteGuardRenderState extends LivingEntityRenderState {
    public GuardPhase phase;
    public ThreeStagePhase chargePhase;
    public ThreeStagePhase stompPhase;
}
