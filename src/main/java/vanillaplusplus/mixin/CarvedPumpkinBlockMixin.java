package vanillaplusplus.mixin;

import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CarvedPumpkinBlock.class)
public abstract class CarvedPumpkinBlockMixin {

    @Inject(method="trySpawnEntity", at=@At("HEAD"), cancellable = true)
    private void onTrySpawnEntity(World world, BlockPos pos, CallbackInfo cbi) {
        cbi.cancel();
    }
}
