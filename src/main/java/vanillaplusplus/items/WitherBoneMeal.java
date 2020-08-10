package vanillaplusplus.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.Random;

public class WitherBoneMeal extends Item {

    public WitherBoneMeal(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return fertilize(context.getWorld(), context.getBlockPos(), context.getStack());
    }

    public static ActionResult fertilize(World world, BlockPos centerPos, ItemStack stack) {
        BlockState centerBlockState = world.getBlockState(centerPos);
        if (centerBlockState.getBlock().is(Blocks.NETHER_WART)) {
            // Try to apply the growth affect in a 3x3 area center on the
            // used block
            boolean didGrow = false;
            for (int i  = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    BlockPos pos = centerPos.add(new Vec3i(i, 0, j));
                    BlockState blockState = world.getBlockState(pos);

                    if (blockState.getBlock().is(Blocks.NETHER_WART)) {
                        int age = blockState.get(NetherWartBlock.AGE);
                        int increment = new Random().nextInt(2) + 1;

                        if (age + increment > 3) {
                            increment = 3 - age;
                        }

                        if (blockState.getBlock() == Blocks.NETHER_WART && age < 3) {
                            didGrow = true;
                            if (!world.isClient()) {
                                blockState = blockState.with(NetherWartBlock.AGE, age + increment);
                                world.setBlockState(pos, blockState, 2);
                            } else {
                                world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
                            }

                        }
                    }
                }
            }

            if (didGrow) {
                stack.decrement(1);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
