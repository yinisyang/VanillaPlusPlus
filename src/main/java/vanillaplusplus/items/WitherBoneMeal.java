package vanillaplusplus.items;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WitherBoneMeal extends Item {

    public WitherBoneMeal(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        BlockPos pos = context.getBlockPos();

        BlockState blockState = world.getBlockState(pos);
        int age = blockState.get(NetherWartBlock.AGE);
        int increment = new Random().nextInt(2) + 1;

        if (age + increment > 3) {
            increment = 3 - age;
        }

        if (blockState.getBlock() == Blocks.NETHER_WART && age < 3) {

            if (!world.isClient()) {
                blockState = blockState.with(NetherWartBlock.AGE, age + increment);
                world.setBlockState(pos, blockState, 2);
            } else {
                world.addParticle(ParticleTypes.HAPPY_VILLAGER, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
            }
            context.getStack().decrement(1);
        }
        return ActionResult.SUCCESS;
    }
}
