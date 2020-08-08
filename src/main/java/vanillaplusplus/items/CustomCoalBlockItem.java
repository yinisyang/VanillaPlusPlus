package vanillaplusplus.items;

import net.minecraft.item.ItemGroup;
import vanillaplusplus.VanillaPlusPlusInitializer;
import vanillaplusplus.common.FluidRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class CustomCoalBlockItem extends BlockItem {
    public CustomCoalBlockItem() {
        super(Blocks.COAL_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS).fireproof());
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos().offset(context.getSide());

        if (context.getWorld().getBlockState(pos).isOf(FluidRegistry.MOLTEN_IRON_BLOCK)) {
            context.getStack().decrement(1);
            context.getWorld().setBlockState(pos, FluidRegistry.MOLTEN_STEEL_BLOCK.getDefaultState());
            return ActionResult.SUCCESS;
        } else {
            return super.useOnBlock(context);
        }
    }
}
