package vanillaplusplus.entities;

import vanillaplusplus.blocks.meltable.MeltableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class MeltableBlockEntity extends BlockEntity implements Tickable {

    private static final Set<Block> BLOCKS_THAT_HEAT = new HashSet<>();
    static {
        BLOCKS_THAT_HEAT.add(Blocks.MAGMA_BLOCK);
    }

    public MeltableBlockEntity(BlockEntityType<?> type) {
        super(type);
    }

    protected abstract Block getMoltenBlock();
    protected abstract double getHeatUpChance();

    @Override
    public void tick() {
        if (!getWorld().isClient()) {
            BlockPos checkedPos = this.pos.add(Direction.DOWN.getVector());
            if (BLOCKS_THAT_HEAT.contains(getWorld().getBlockState(checkedPos).getBlock()) && !isTouchingWater(this.pos)) {
                Random random = new Random();
                double next = random.nextDouble();
                BlockState state = getWorld().getBlockState(this.pos);

                // If block is at max heat, melt into molten form
                if (state.get(MeltableBlock.HEAT).equals(8)) {
                    getWorld().setBlockState(this.pos, getMoltenBlock().getDefaultState());
                    return;
                }

                // See if block heats up
                if (next < getHeatUpChance()) {
                    int newHeat = state.get(MeltableBlock.HEAT) + 1;
                    getWorld().setBlockState(this.pos, state.with(MeltableBlock.HEAT, newHeat));
                }
            }
        }
    }

    private boolean isTouchingWater(BlockPos pos) {
        for (Direction d: Direction.values()) {
            if (getWorld().getFluidState(pos.add(d.getVector())).isIn(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }
}
