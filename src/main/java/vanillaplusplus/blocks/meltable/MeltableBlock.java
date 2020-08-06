package vanillaplusplus.blocks.meltable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public abstract class MeltableBlock extends Block {

    public static final IntProperty HEAT = IntProperty.of("heat", 1, 4);

    MeltableBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(HEAT, 1));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(HEAT);
    }

    public abstract Block getMoltenBlock();
}
