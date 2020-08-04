package vanillaplusplus.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Random;

public abstract class CoolableFluid extends FlowableFluid {

    // TODO: Make coolable fluid cool if it was covered by water

    public abstract Block getCooledBlock();

    @Override
    protected void onRandomTick(World world, BlockPos pos, FluidState state, Random random) {
        if (!world.getDimension().isUltrawarm() && isStill(state) && !isAboveMagma(world, pos)) {
            cool(world, pos);
        }
    }

    private void cool(World world, BlockPos pos) {
        world.setBlockState(pos, getCooledBlock().getDefaultState());
    }

    @Override
    protected boolean hasRandomTicks() {
        return true;
    }

    private boolean isAboveMagma(World world, BlockPos pos) {
        BlockPos checkedPos = pos.add(Direction.DOWN.getVector());
        return world.getBlockState(checkedPos).isOf(Blocks.MAGMA_BLOCK);
    }

    @Override
    protected boolean isInfinite() {
        return false;
    }


    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.getBlock().hasBlockEntity() ? world.getBlockEntity(pos): null;
        Block.dropStacks(state, world.getWorld(), pos, blockEntity);
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 2;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 2;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return state.getHeight(world, pos) >= 0.44444445F && fluid.isIn(FluidTags.WATER);
    }

    @Override
    public int getTickRate(WorldView world) {
        return 30;
    }

    @Override
    protected float getBlastResistance() {
        return 100.0f;
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

}
