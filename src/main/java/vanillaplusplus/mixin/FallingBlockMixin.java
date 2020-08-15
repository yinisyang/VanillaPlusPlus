package vanillaplusplus.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vanillaplusplus.common.BlockRegistry;

import java.util.Random;

@Mixin(FallingBlock.class)
public class FallingBlockMixin {

    @Inject(method="scheduledTick", at=@At("HEAD"), cancellable = true)
    public void onScheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo cbi) {
        if (shouldStick(world, pos)) {
            cbi.cancel();
        }
    }

    private boolean shouldStick(ServerWorld world, BlockPos pos) {
        for (Direction d: Direction.values()) {
            if (world.getBlockState(pos.offset(d)).isIn(BlockRegistry.STICKY_BLOCK_TAG)) {
                return true;
            }
        }
        return false;
    }
}
