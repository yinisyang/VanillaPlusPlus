package vanillaplusplus.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(SaplingBlock.class)
public abstract class SaplingBlockMixin extends PlantBlock{

    protected SaplingBlockMixin(Settings settings) {
        super(settings);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.SAND) || floor.isOf(Blocks.RED_SAND) || super.canPlantOnTop(floor, world, pos);
    }

    @Inject(method="randomTick", at=@At("HEAD"), cancellable = true)
    public void onRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo cbi) {
        if (world.getBlockState(pos.down()).isIn(BlockTags.SAND)) {
            if (random.nextInt(2) == 0) {
                world.setBlockState(pos, Blocks.DEAD_BUSH.getDefaultState());
            }

            cbi.cancel();
        }
    }
}
