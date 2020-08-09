package vanillaplusplus.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vanillaplusplus.common.BlockRegistry;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {


    @Inject(method="applyClimbingSpeed", at=@At("NEW"), cancellable = true)
    public void onApplyClimbingSpeed(Vec3d motion, CallbackInfoReturnable<Vec3d> cbi) {
        if (this.getBlockState().isOf(BlockRegistry.STEEL_SCAFFOLD)) {
            double d = MathHelper.clamp(motion.x, -0.15000000596046448D, 0.15000000596046448D);
            double e = MathHelper.clamp(motion.z, -0.15000000596046448D, 0.15000000596046448D);
            double g = Math.max(motion.y, -0.15000000596046448D);
            cbi.setReturnValue(new Vec3d(d, g, e));
        }
    }

    @Shadow
    public abstract BlockState getBlockState();
}
