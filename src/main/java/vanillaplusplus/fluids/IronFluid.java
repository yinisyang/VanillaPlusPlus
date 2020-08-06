package vanillaplusplus.fluids;

import vanillaplusplus.common.BlockRegistry;
import vanillaplusplus.common.FluidRegistry;
import vanillaplusplus.common.ItemRegistry;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class IronFluid extends CoolableFluid {
    public Block getCooledBlock() {
        return Blocks.IRON_BLOCK;
    }

    @Override
    public Fluid getStill() {
        return FluidRegistry.STILL_IRON;
    }

    @Override
    public Fluid getFlowing() {
        return FluidRegistry.FLOWING_IRON;
    }

    @Override
    public Item getBucketItem() {
        return ItemRegistry.MOLTEN_IRON_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState fluidState) {
        return FluidRegistry.MOLTEN_IRON_BLOCK.getDefaultState().with(FluidBlock.LEVEL, method_15741(fluidState));
    }

    public static class Flowing extends IronFluid {
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

    public static class Still extends IronFluid {
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
