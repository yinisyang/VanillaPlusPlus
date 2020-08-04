package vanillaplusplus.blocks.meltable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public abstract class MeltableBlock extends Block implements BlockEntityProvider {

    public static final IntProperty HEAT = IntProperty.of("heat", 1, 8);

    MeltableBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(HEAT, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(HEAT);
    }
}
