package vanillaplusplus.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.MagmaBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vanillaplusplus.blocks.meltable.MeltableBlock;
import vanillaplusplus.common.BlockRegistry;

import java.util.Random;

@Mixin(MagmaBlock.class)
public class MagmaBlockMixin {

    @Inject(method="randomTick", at=@At("HEAD"))
    public void injectRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo info) {

        // Check each direction for a heatable block
        for (Direction direction: Direction.values()) {
            if (world.getBlockState(pos.offset(direction)).isIn(BlockRegistry.MELTABLE_BLOCK_TAG)) {
                BlockState blockState = world.getBlockState(pos.offset(direction));
                MeltableBlock meltableBlock = (MeltableBlock)blockState.getBlock();

                // Melt if at max heat
                if (blockState.get(MeltableBlock.HEAT) >= 4) {
                    world.setBlockState(pos.offset(direction), meltableBlock.getMoltenBlock().getDefaultState());
                } else {
                    // Heat up block
                    int newHeat = blockState.get(MeltableBlock.HEAT) + 1;
                    world.setBlockState(pos.offset(direction), blockState.with(MeltableBlock.HEAT, newHeat));
                }
            }
        }
    }
}
