package vanillaplusplus.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import vanillaplusplus.common.FluidRegistry;
import vanillaplusplus.fluids.CoolableFluid;

@Mixin(FluidBlock.class)
public abstract class FluidBlockMixin {

    @Shadow
    FlowableFluid fluid;

    @Shadow
    abstract void playExtinguishSound(WorldAccess world, BlockPos pos);

    private boolean receiveNeighborFluids(World world, BlockPos pos, BlockState state) {
        if (this.fluid.isIn(FluidTags.LAVA)) {
            boolean bl = world.getBlockState(pos.down()).isOf(Blocks.SOUL_SOIL);
            Direction[] var5 = Direction.values();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Direction direction = var5[var7];
                if (direction != Direction.DOWN) {
                    BlockPos blockPos = pos.offset(direction);
                    if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                        Block block;
                        if (this.fluid.isIn(FluidRegistry.MOLTEN_METAL_TAG)) {
                            CoolableFluid fluid = (CoolableFluid)world.getFluidState(pos).getFluid();
                            block = world.getFluidState(pos).isStill() ? fluid.getCooledBlock() : Blocks.COBBLESTONE;
                        } else {
                            block = world.getFluidState(pos).isStill() ? Blocks.OBSIDIAN : Blocks.COBBLESTONE;
                        }
                        world.setBlockState(pos, block.getDefaultState());
                        this.playExtinguishSound(world, pos);
                        return false;
                    }

                    if (bl && world.getBlockState(blockPos).isOf(Blocks.BLUE_ICE)) {
                        world.setBlockState(pos, Blocks.BASALT.getDefaultState());
                        this.playExtinguishSound(world, pos);
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
