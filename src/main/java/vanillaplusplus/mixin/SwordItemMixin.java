package vanillaplusplus.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.TntBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SwordItem.class)
public class SwordItemMixin {

    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getSide() == Direction.DOWN) {
            return ActionResult.PASS;
        }

        BlockPos spawnPos = context.getBlockPos().offset(context.getSide());
        World world = context.getWorld();
        int level = EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT, context.getStack());

        if (level > 0) {
            // Campfire Interactions
            if (world.getBlockState(context.getBlockPos()).isIn(BlockTags.CAMPFIRES)) {
                BlockState campfireBlockState = world.getBlockState(context.getBlockPos());

                if (!campfireBlockState.get(CampfireBlock.LIT)) {
                    world.setBlockState(context.getBlockPos(), campfireBlockState.with(CampfireBlock.LIT, true));
                    damageSword(context);
                    return ActionResult.SUCCESS;
                }
            } else if (world.getBlockState(context.getBlockPos()).isOf(Blocks.TNT)) {
                world.removeBlock(context.getBlockPos(), false);
                TntBlock.primeTnt(context.getWorld(), context.getBlockPos());
                damageSword(context);
                return ActionResult.SUCCESS;
            // Spawn fire interaction
            } else if (world.getBlockState(spawnPos).isAir()) {
                if (world.getBlockState(context.getBlockPos()).isIn(BlockTags.SOUL_FIRE_BASE_BLOCKS)) {
                    world.setBlockState(spawnPos, Blocks.SOUL_FIRE.getDefaultState());
                } else {
                    world.setBlockState(spawnPos, Blocks.FIRE.getDefaultState());
                }
                context.getStack().setDamage(context.getStack().getDamage() - 1);
                damageSword(context);
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

    private void damageSword(ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            context.getStack().damage(2, context.getPlayer(), (p) -> p.sendToolBreakStatus(context.getHand()));
        }
    }
}
