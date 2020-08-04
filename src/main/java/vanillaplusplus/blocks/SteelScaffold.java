package vanillaplusplus.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.Random;

public class SteelScaffold extends ScaffoldingBlock {
    public SteelScaffold(Settings settings) {
        super(settings);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int i = calculateDistance(world, pos);
        BlockState blockState = (BlockState)((BlockState)state.with(DISTANCE, i)).with(BOTTOM, this.shouldBeBottom(world, pos, i));
        if ((Integer)blockState.get(DISTANCE) == 7) {
            if ((Integer)state.get(DISTANCE) == 7) {
                //world.spawnEntity(new FallingBlockEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, (BlockState)blockState.with(WATERLOGGED, false)));
            } else {
                world.breakBlock(pos, true);
            }
        } else if (state != blockState) {
            world.setBlockState(pos, blockState, 3);
        }

    }

    private boolean shouldBeBottom(BlockView world, BlockPos pos, int distance) {
        return distance > 0 && !world.getBlockState(pos.down()).isOf(this);
    }
}
