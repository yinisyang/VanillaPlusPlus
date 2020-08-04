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

public abstract class GoldFluid extends CoolableFluid {
    @Override
    public Block getCooledBlock() {
        return BlockRegistry.MELTABLE_GOLD_BLOCK;
    }

    @Override
    public Fluid getFlowing() {
        return FluidRegistry.FLOWING_GOLD;
    }

    @Override
    public Fluid getStill() {
        return FluidRegistry.STILL_GOLD;
    }

    @Override
    public Item getBucketItem() {
        return ItemRegistry.MOLTEN_GOLD_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return FluidRegistry.MOLTEN_GOLD_BLOCK.getDefaultState().with(FluidBlock.LEVEL, method_15741(state));

    }

    public static class Flowing extends GoldFluid {
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

    public static class Still extends GoldFluid {
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
