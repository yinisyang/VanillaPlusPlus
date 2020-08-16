package vanillaplusplus.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vanillaplusplus.common.FluidRegistry;
import vanillaplusplus.fluids.CoolableFluid;

@Mixin(FluidBlock.class)
public abstract class FluidBlockMixin {

    @Shadow
    private
    FlowableFluid fluid;

    @Inject(method="receiveNeighborFluids", at=@At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z", ordinal = 0), cancellable = true)
    private void onReceiveNeighborFluids(World world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cbi) {
        if (this.fluid.isIn(FluidRegistry.MOLTEN_METAL_TAG)) {
            CoolableFluid fluid = (CoolableFluid)world.getFluidState(pos).getFluid();
            Block block = world.getFluidState(pos).isStill() ? fluid.getCooledBlock() : Blocks.COBBLESTONE;
            world.setBlockState(pos, block.getDefaultState());
            cbi.setReturnValue(false);
        }
    }
}
