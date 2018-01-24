package androsa.gaiadimension.fluid;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.List;

public interface IFaceFluidHandler extends IFluidHandler {

    List<EnumFacing> outputFaces();

    List<EnumFacing> inputFaces();

    default int fill(EnumFacing face, FluidStack resource, boolean doFill) {
        if (inputFaces().contains(face))
            return fill(resource, doFill);
        return 0;
    }

    default FluidStack drain(EnumFacing face, FluidStack resource, boolean doDrain) {
        if (outputFaces().contains(face))
            return drain(resource, doDrain);
        return null;
    }

    default FluidStack drain(EnumFacing face, int maxDrain, boolean doDrain) {
        if (outputFaces().contains(face))
            return drain(maxDrain, doDrain);
        return null;
    }
}
