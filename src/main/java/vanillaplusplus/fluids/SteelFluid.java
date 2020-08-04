package vanillaplusplus.fluids;

import vanillaplusplus.common.BlockRegistry;
import vanillaplusplus.common.FluidRegistry;
import vanillaplusplus.common.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class SteelFluid extends CoolableFluid {
    public Block getCooledBlock() {
        return BlockRegistry.STEEL_BLOCK;
    }

    @Override
    public Fluid getStill() {
        return FluidRegistry.STILL_STEEL;
    }

    @Override
    public Fluid getFlowing() {
        return FluidRegistry.FLOWING_STEEL;
    }

    @Override
    public Item getBucketItem() {
        return ItemRegistry.MOLTEN_STEEL_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return FluidRegistry.MOLTEN_STEEL_BLOCK.getDefaultState().with(FluidBlock.LEVEL, method_15741(fluidState));
    }

    public static class Flowing extends SteelFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState fluidState) {
            return fluidState.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return false;
        }
    }

    public static class Still extends SteelFluid {
        @Override
        public int getLevel(FluidState fluidState) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState fluidState) {
            return true;
        }


    }
}
