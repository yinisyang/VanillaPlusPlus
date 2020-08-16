package vanillaplusplus.mixin;

import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import vanillaplusplus.common.FluidRegistry;

@Mixin(MilkBucketItem.class)
public class MilkBucketMixin {

    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos().offset(context.getSide());
        context.getWorld().setBlockState(pos, FluidRegistry.MILK_BLOCK.getDefaultState());

        return ActionResult.SUCCESS;
    }
}
