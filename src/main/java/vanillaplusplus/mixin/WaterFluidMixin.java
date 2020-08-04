package vanillaplusplus.mixin;

import vanillaplusplus.fluids.CoolableFluid;
import vanillaplusplus.common.FluidRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WaterFluid.class)
public abstract class WaterFluidMixin extends FlowableFluid {

    @Override
    protected void flow(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState) {
        FluidState fluidState2 = world.getFluidState(pos);
        if (fluidState2.getFluid().isIn(FluidRegistry.MOLTEN_METAL_TAG)) {
            if (state.getBlock() instanceof FluidBlock) {
                CoolableFluid cFluid = (CoolableFluid)(fluidState2.getFluid());
                world.setBlockState(pos, cFluid.getCooledBlock().getDefaultState(), 3);
            }
            return;
        }

        super.flow(world, pos, state, direction, fluidState);
    }
}
